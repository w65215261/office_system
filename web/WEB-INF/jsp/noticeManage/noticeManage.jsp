<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
<script type="text/javascript">
	
	//新增窗口
	function addNotice() {
	/* 	$('#addNotice1').form('clear');
		$('#logWin').window('open'); */
		top.openDialogWithCallback("/niceManagedit/add.jsp", top.NewGuid(), '窗体列表 - 添加',1000,500, function (data){
			if(data  == true){
				$('#noticeGrid').datagrid("reload");
			}else{
				return;
			}
			
    	} , true);
	}
	//关闭窗口
	function closeWin(id) {
		$('#test').val('');
		$('#' + id).window('close');
	}
	//新增保存
	function save() {
		if ($('#title').val() == '') {
			$.messager.alert('提示', '请填写标题！');
			return;
		}
		if ($('#content').val() == '') {
			$.messager.alert('提示', '请填写内容！');
			return;
		}
	/* 	  var file = document.getElementsByName('file')[0].value;	   	     
		     if (file == "") {
		    	   	$('#addNotice1').submit();
	            }else{
	                //定义允许上传的文件类型
	   	            var allow_ext = ".rar|.zip|.arj|.7z|";
	   	            //提取上传文件的类型
	   	            var ext_name = file.substring(file.lastIndexOf("."));
	   	            //判断上传文件类型是否允许上传
	   	            if (allow_ext.indexOf(ext_name + "|") != -1) {
	   	                var errMsg = "该文件不允许上传，文件类型为：" + allow_ext+"的文件不允许上传";
	   	         	$.messager.alert('提示',errMsg );
	   	                return false;
	   	            }else{ */
	   	         	$('#addNotice1').submit();
	   	      
	}
	//修改
	function updateNews() {
		var select = $("#noticeGrid").datagrid("getSelections");
		if (select == null || select.length != 1) {
			$.messager.alert('温馨提示', '请选择一条数据！');
		} else{
		/* 	document.getElementById('test2').style.display ='inline'; */
			var oid = select[0].id;
		/* 	$.ajax({
				type : "post",
				url : '/noticeManage/Valupdate.do',
				data : {
					ids : oid
				},
				success : function(data) {
					if (data == "success") {
						  $('#addNotice2').form('load', '/noticeManage/findById.do?oid=' + oid);
						 $('#file').val("${dnm.name}");
						$('#id').val(oid);
						$('#logWin1').window('open'); 
						 '/noticeManage/findById.do?oid=' + oid 
						openDialogWithCallback("/noticeManage/findById.do?oid=" + oid, NewGuid(), '窗体列表 - 修改',1000,500, function (){				    		
				    			$('#noticeGrid').datagrid("reload");
				    	} , true);	
						}else{
						$.messager.alert('温馨提示', '信息已发送不能修改！');
					}
				},
				error : function() {
				}
			}); */
			top.openDialogWithCallback("/noticeManage/findById.do?oid=" + oid, top.NewGuid(), '窗体列表 - 修改',1000,500, function (data){
				
				if(data == true){
					$('#noticeGrid').datagrid("reload");
					}else{
						return;
					}
    			
    	} , true);	
		} 
	}

	//删除
	function deleteNews() {
		var oneTest = $("#noticeGrid").datagrid("getSelections");
		if (oneTest == null || oneTest.length == 0) {
			$.messager.alert('提示', '请先选择要删除的通知');
		} else {
			var ids = "";
			for ( var i = 0; i < oneTest.length; i++) {
				ids += oneTest[i].id + ",";
			}
			$.messager.confirm('温馨提示', '是否确认删除？', function(r) {
				if (r) {
					$.ajax({
						type : "post",
						url : '/noticeManage/delete.do',
						data : {
							ids : ids
						},
						success : function(data) {
							if (data == "success") {
								$.messager.alert('温馨提示', '操作成功！');
								$('#noticeGrid').datagrid('reload');
 							} else if(data =="person"){
								$.messager.alert('温馨提示', '信息已发送不能删除！');
							}else {
								$.messager.alert('温馨提示', '操作失败！');
							}
						},
						error : function() {
						}
					});
				}
			});
		}
	}
	//选择人员窗口
	function sendPerson(){
		var id = $('#noticeGrid').datagrid("getSelections");
		if (id == null || id.length == 0) {
			$.messager.alert('提示', '请选择所要发送的信息');
			return;
		}else{
				$('#memberWin').window('open');
		}
		
	}
	
	//点击树查询
    function clickTree(node){
    	$('#personList').datagrid({
            url:'/personManage/query.do?groupOid=' + node.id,
            method:'get'
          });
    }
	
	//发送
	function send(){
		var id = $('#noticeGrid').datagrid("getSelections");
		var personId = $('#personList').datagrid("getSelections");
		var username = $('#personList').datagrid("getSelections");
		if (id == null || id.length == 0) {
			$.messager.alert('提示', '请选择所要发送的信息');
			return;
		}
		if (personId == null || personId.length == 0){
			$.messager.alert('提示','请选择要发送的人员');
			return;
		}
		if (username == null || username.length == 0){
			$.messager.alert('提示','请选择要发送的人员');
			return;
		}
		var ids = "";
		var personIds = "";
		var usernames = "";
		for(var i = 0 ; i < id.length ; i++){
			ids += id[i].id + ",";
		}
		for(var j = 0 ; j < personId.length ;j++){
			personIds += personId[j].id + ",";
		}
		for(var j = 0 ; j < username.length ;j++){
			usernames += username[j].id + ",";
		}
		$.messager.confirm('温馨提示','确认发送？',function(r){
			if(r){
				$.ajax({
					type : "post",
					url : '/noticePerson/send.do',
					data:{
						ids : ids,
						personIds : personIds,
						usernames : usernames
					},
					success : function(data){
						if(data == "success"){
							$('#memberWin').window('close');
							$.messager.alert('温馨提示','操作成功!');
							
							$('#noticeGrid').datagrid('reload');
							
						}else{
							$.messager.alert('温馨提示','操作失败!');
						}
					},
					error:function(){
					}
				});
			}
		});
	}
	
	function showView(id){
		/* var val = $('#noticeGrid').datagrid("getSelections");
		if (val == null||val.length>1 ) {
			$.messager.alert('温馨提示', '请选择一条数据！');
		} else {
			$('#viewWin').window('open');
			$('#ssss').datagrid({url:'/noticePerson/showView1.do?id='+val[0].id,method:'GET'});
		} */
		$('#viewWin').window('open');
		$('#ssss').datagrid({url:'/noticePerson/showView1.do?id='+id,method:'GET'});
	}
	
	
	
	
	
	
	
	
	
	
	
	function save2(){
		/*   var file = document.getElementsByName('file')[0].value;	   */
		/*   var file = document.getElementById("test").value;
		     if (file == "") {
		    	   	$('#addNotice2').submit();
	            }else{
	            //定义允许上传的文件类型
	            var allow_ext = ".rar|.zip|.arj|.7z|";
	            //提取上传文件的类型
	            var ext_name = file.substring(file.lastIndexOf("."));
	            //判断上传文件类型是否允许上传
	            if (allow_ext.indexOf(ext_name + "|") != -1) {
	                var errMsg = "该文件不允许上传，文件类型为：" + allow_ext+"的文件不允许上传";
	         	$.messager.alert('提示',errMsg );
	                return false;
	            }else{ */
	            	$('#addNotice2').submit();
	          /*   }} */
	}
	//是否发送转换
	function isOrNot(val, row) {
        if (val <= 0) {
            return "未发送";
        } else if (val >= 1) {
            return "已发送";
        }
    }
	
	function sendGroup(id){
/* 		var id = $('#noticeGrid').datagrid("getSelections");
		if (id == null || id.length == 0) {
			$.messager.alert('提示', '请选择所要发送的信息');
			return;
		}else{
	if(id.length >1){
				$.messager.alert('提示', '请选择一条信息');
		}else{
			$('#memberList').datagrid('loadData', { total: 0, rows: [] });
			 loadRoomList();
			$('#sendGroupWin').window('open');
	} 
	} */
		$('#memberList').datagrid('loadData', { total: 0, rows: [] });
		 loadRoomList(id);
		$('#sendGroupWin').window('open');
		}
	
 function loadRoomList(id){
	   $('#roomList').datagrid({
	        onCheck: function(rowIndex,rowData) {
	        	/* var message = $('#noticeGrid').datagrid("getSelected"); */
	          var roomID = rowData.roomID;
	           $('#messageid').val(id);
	          $('#memberList').datagrid({url:'/ofMucMember/findByRoom.do?roomID='+roomID+'&infoid='+id,method:'GET'});
	        }
	      });
 }
 
 function sousuo(){
	 var group = $('#roomList').datagrid("getSelections");
	var roomID=group[0].roomID;
	 var id=$('#messageid').val();
	 var name=$('#sso').val();
	 $('#memberList').datagrid({url:'/ofMucMember/findyRoom.do?roomID='+roomID+'&infoid='+id+'&name='+name,method:'GET'}); 
 }
 
 
 
 
 
 
	//群组发送
	function saveSendGroup(){
		/* var message = $('#noticeGrid').datagrid("getSelected"); */
		var message= $('#messageid').val();
		var group = $('#roomList').datagrid("getSelections");
		var person = $('#memberList').datagrid("getSelections");
		if(message == null){
			$.messager.alert('温馨提示','请选择一条信息!');
			return;
		}
		if(group == null){
			$.messager.alert('温馨提示','请选择一个群组！');
			return;
		}
		var groups = "";
		var persons = "";
		var usernames = "";
		for(var i = 0 ; i < group.length ; i++){
			groups += group[i].roomID + ",";
		}
		for(var j = 0 ; j < person.length ; j++){
			persons += person[j].jid + ",";	
			usernames += person[j].nickname + ",";
		}
		$.messager.confirm('温馨提示','确认发送？',function(r){
			if(r){
				$.messager.progress({
					text:'正在发送中，请稍后...' 
				}); 
				$.ajax({
					type : "post",
					url : '/noticePerson/sendGroup.do',
					data:{
						message : message,
						groups : groups,
						persons : persons,
						usernames : usernames
					},
					success : function(data){
							$.messager.progress('close');
						if(data == "success"){
							$.messager.alert('温馨提示','操作成功!');
							$('#noticeGrid').datagrid('reload');
							$('#sendGroupWin').window('close');
						}else if(data =="fail"){
								$.messager.alert('温馨提示','不能重复发送给一位人员!');
							}else if(data == "weiChatNo"){
								$.messager.alert('温馨提示','公众微信号有误!');	
							}else{
								$.messager.alert('温馨提示','操作失败!');	
							}
					},
					error:function(){
					}
				});
			}
		});
	}
	//下载
	function  go(val,row){
		return '<a href="#\'' + row.id+ '\'" onclick="downloadAttachments(\'' + row.id+ '\')">下载</a> '
	}
	//通知发送
	function  send(val,row){
		return '<a href="#\'' + row.id+ '\'" onclick="sendGroup(\'' + row.id+ '\')">通知发送</a> '
	}
	
	//查看状态
	function  View(val,row){
		return '<a href="#\'' + row.id+ '\'" onclick="showView(\'' + row.id+ '\')">查看状态</a> '
	}
	//查看通信状态
	function Mic(val,row){
		return '<a href="#\'' + row.id+ '\'" onclick="sendMic(\'' + row.id+ '\')">微信发送状态</a> '
	}
	
	
	
	function downloadAttachments(row){
		$.ajax({
			type : "post",
			url : '/noticeManage/findByFileId.do',
			data : {
				id : row
			},
			success : function(data) {
				if (data == "success") {
					window.location.href="/noticeManage/downFile.do?id="+row;
				}else {
					$.messager.alert('温馨提示', '未上传文件！');
				}
			},
			error : function() {
			}
		});
		//window.location.href="/noticeManage/downFile.do?id="+row;

	}
	
/* 	$(document).on('click','#test',function(){
		$(this).siblings('#test2').hide();
		console.log($(this).siblings('#test2'));
		console.log($('#test2').html());
		$(this).attr("type","file");
		$(this).removeAttr("value");
	}); */
	
	//关闭窗口
	function goBack(){
	history.go(-1);
	}
	
	  $(document).ready(function(){
		  var roomid=$('#id').val();
		  	$('#ssss').datagrid({
				url : '/noticePerson/showView1.do?id='+roomid
			});
	  }); 
		function formatterdate1(val, row) {
		 	if (val != null) {
		 		 val=parseInt(val,10);//转为整形
		 		var date =new Date(val);//正确
		 		return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
		 		+ date.getDate() + ' ' + date.getHours() + ':'
		 		+ date.getMinutes() + ':' + date.getSeconds();
			} 
		}
		
	
		 
		  function closeSelectWin(){
			  	$('#selectWin').window('close');
			  }
		  function sendMic(id){
			/* 	var val = $('#noticeGrid').datagrid("getSelections");
				if (val == null||val.length>1 ) {
					$.messager.alert('温馨提示', '请选择一条数据！');
				} else {
					$('#viewWin1').window('open');
					$('#tt').datagrid({url:'/noticePerson/showMic.do?id='+val[0].id,method:'GET'});
				} */
			  $('#viewWin1').window('open');
				$('#tt').datagrid({url:'/noticePerson/showMic.do?id='+id,method:'GET'});
			}	
		  
	//	    $(document).ready(function() {
	//	    	 $('#noticeGrid').datagrid({
		//    			onDblClickRow: function(rowIndex, rowData){
		//    				top.openDialogWithCallback("/noticeManage/fById.do?id="+rowData.id, top.NewGuid(), '窗体列表 - 查看详情', 1000,500, null, true);
//		    	 			addTab("公告详情","/newsInfo/see.do?id="+rowData.id);
		 //  				}
		    //			});
		    //	}); 
			 //双击人员信息弹出详情页面
			/*   $(document).ready(function(){
			  	
			      $('#noticeGrid').datagrid({
			      	onDblClickRow: function(rowIndex,rowData) {
			      		$('#selectWin').window('open');
			      		$('#noticeTitle').val(rowData.title);
			      		$('#noticeContent').val(rowData.content);

			        }
			      });
			    }); */
		  function  go1(val,row){
				return '<a href="#\'' + row.id+ '\'" onclick="constructionManager1(\'' + row.id+ '\')">'+val+'</a> '
				}
		  function constructionManager1(row){	
			  top.openDialogWithCallback("/noticeManage/fById.do?id="+row, top.NewGuid(), '窗体列表 - 查看详情', 1000,500, null, true);
			  }
		  function sdsd(val,row){
			  if(row.findNumber ==null&&row.sendNumber !=null){
					 return '<a href="#\'' + row.id+ '\'" onclick="constructionManager5(\'' + row.id+ '\')">'+'0'+'</a> '+'/'+'<a href="#\'' + row.id+ '\'" onclick="constructionManager6(\'' + row.id+ '\')">'+row.sendNumber+'</a> '
					  }else if(row.findNumber !=null&&row.sendNumber ==null){
						  return '<a href="#\'' + row.id+ '\'" onclick="constructionManager5(\'' + row.id+ '\')">'+row.findNumber+'</a> '+'/'+'<a href="#\'' + row.id+ '\'" onclick="constructionManager6(\'' + row.id+ '\')">'+'0'+'</a> '
					  }else if(row.findNumber ==null&&row.sendNumber ==null){
						  return '<a href="#\'' + row.id+ '\'" onclick="constructionManager5(\'' + row.id+ '\')">'+'0'+'</a> '+'/'+'<a href="#\'' + row.id+ '\'" onclick="constructionManager6(\'' + row.id+ '\')">'+'0'+'</a> '  
					  }else{
						  return '<a href="#\'' + row.id+ '\'" onclick="constructionManager5(\'' + row.id+ '\')">'+row.findNumber+'</a> '+'/'+'<a href="#\'' + row.id+ '\'" onclick="constructionManager6(\'' + row.id+ '\')">'+row.sendNumber+'</a> '
					  }
		  }  
		  function constructionManager5(id){
					$('#viewWin').window('open');
					$('#ssss').datagrid({url:'/noticePerson/showView1.do?id='+id+"&checkInfo="+1,method:'GET'});
		  }
		  function constructionManager6(id){
				$('#viewWin').window('open');
				$('#ssss').datagrid({url:'/noticePerson/showView1.do?id='+id,method:'GET'});
	  }
		  
		  
		  function dddd(val,row){
			  if(val ==null){
				  return '<a href="#\'' + row.id+ '\'" onclick="constructionManager7(\'' + row.id+ '\')">'+0+'</a> '
			  }else{
				  return '<a href="#\'' + row.id+ '\'" onclick="constructionManager7(\'' + row.id+ '\')">'+val+'</a> '
			  }
			 
		  }
		  
		  function adadda(val,row){
			if(row.a_type ==1&&row.a_bz2 ==1){
			 /*  return '<a href="#\'' + row.id+ '\'" onclick="cons(\'' + row.id+ '\')">'+'是'+'</a> '+'/'+'<a href="#\'' + row.id+ '\'" onclick="conss(\'' + row.id+ '\')">'+'是'+'</a> ' */
				return '是'+'/'+'是'
		  	  }else if(row.a_type ==1&&row.a_bz2 ==0){
		  		return '是'+'/'+'否'
		  	 
				 /*  return '<a href="#\'' + row.id+ '\'" onclick="cons(\'' + row.id+ '\')">'+'是'+'</a> '+'/'+'<a href="#\'' + row.id+ '\'" onclick="conss(\'' + row.id+ '\')">'+'否'+'</a> ' */
			  }else if(row.a_type ==0&&row.a_bz2 ==1){
				  
				  return '否'+'/'+'是'
				 /*  return '<a href="#\'' + row.id+ '\'" onclick="cons(\'' + row.id+ '\')">'+'否'+'</a> '+'/'+'<a href="#\'' + row.id+ '\'" onclick="conss(\'' + row.id+ '\')">'+'是'+'</a> ' */
			  }else if(row.a_type ==0&&row.a_bz2 ==0){
				 /*  return '<a href="#\'' + row.id+ '\'" onclick="cons(\'' + row.id+ '\')">'+'否'+'</a> '+'/'+'<a href="#\'' + row.id+ '\'" onclick="conss(\'' + row.id+ '\')">'+'否'+'</a> ' */
				  return '否'+'/'+'否'
			  }
		  }
		  
		   function format(val,row){
			  if(row.a_type ==0&&row.a_bz2 ==0){
						   return "未查看";
					  }else if(row.a_type ==1){
						  return "已查看";
					  }else if(row.a_bz2 ==1){
						  return "已查看";
					  }else if(row.a_bz2 ==1&&row.a_type ==1){
						  return "已查看";
					  }
		   }
		  function constructionManager7(id){
			  $('#viewWin').window('open');
			  $('#ssss').datagrid({url:'/noticePerson/showView1.do?id='+id+"&checkInfo="+2,method:'GET'});
		  }
	  
</script>
</head>
<body class="easyui-layout">
		<table class="easyui-datagrid" style="width: auto; height: auto"
			id="noticeGrid" title="通知管理"
			data-options="url:'/noticeManage/query.do',checkbox:true,fitColumns:true,fit:true,rownumbers:true,singleSelect:false,pagination:true,method:'post',toolbar:'#tb1'
			">
			<thead>
				<tr>
					<th data-options="field:'id',checkbox:true"></th>
					<th data-options="field:'title',align:'center',formatter:go1">通知标题</th>
					<th data-options="field:'createid',align:'center'">创建人</th>
					<th data-options="field:'createtime',align:'center'">创建时间</th>
					<th data-options="field:'sendNumber',align:'center'" formatter="isOrNot">是否发送</th>
					<th data-options="field:'send',align:'center',formatter:sdsd">查看/发送(人数)</th>
					<!-- <th data-options="field:'findNumber',align:'center'">查看人数</th> -->
					<th data-options="field:'noNumber',align:'center',formatter:dddd">未查看人数</th>
					<!-- <th data-options="field:'aa',formatter:go" align="center">下载附件</th> -->
					<th data-options="field:'bb',formatter:send" align="center">通知发送</th>
					<th data-options="field:'cc',formatter:View" align="center">查看状态</th>
					<!-- <th data-options="field:'dd',formatter:Mic" align="center">微信发送状态</th> -->
				</tr>
			</thead>
		</table>
		<div id="tb1" style="padding: 5px; height: auto">
			<div style="margin-bottom: 5px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addNotice()">新增</a> &nbsp;&nbsp; 
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateNews()">修改</a> &nbsp;&nbsp; 
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteNews()">删除</a> &nbsp;&nbsp; 
				<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="sendPerson()">组织机构人员名单</a>&nbsp;&nbsp; -->
				<!--  <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="sendMic()">微信发送状态</a>&nbsp;&nbsp;  -->
			<!-- 	<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="sendGroup()">通知发送</a>&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="showView()">查看状态</a> -->
			</div>
		</div>

	<!-- 新增通知 -->
	<!-- <div id="logWin" class="easyui-window"
		data-options="closed:true,modal:true,title:'新增通知'"
		style="width: 850px; height: 300px;">
		<form id="addNotice1" action="/noticeManage/insert.do" method="post"
			enctype="multipart/form-data" >
			<table cellpadding="5" width="100%">
				<tr>
					<td>
						<div data-options="region:'north',height:30px" title="通知标题：">
							标题：<input class="easyui-validatebox" name="title" style="width: 60%" data-options="required:true"  validtype="length[0,50]" id="title">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						上传文件:<input type="file" name="file" id="file">
					</td>
				</tr>
				<tr>
					<td>内容：<textarea id="content" rows=5 name="content"
							style="width: 750px; height: 100px" data-options="required:true"
							class="textarea easyui-validatebox"></textarea>
					</td>
				</tr>
			</table>
			<div style="padding: 5px; text-align: center;">
				<a href="#" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="save();">保存</a>&nbsp;&nbsp;&nbsp;&nbsp; 
				<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="closeWin('logWin');">取消</a>
			</div>
		</form>
	</div> -->
	<!-- 修改 -->
	<%-- <div id="logWin1" class="easyui-window"
		data-options="closed:true,modal:true,title:'修改 通知'"
		style="width: 850px; height: 300px;">
		<form id="addNotice2" action="/noticeManage/update.do" method="post" enctype="multipart/form-data">
			<input type="hidden" id="id" name="id"/>
			<table cellpadding="5" width="100%">
				<tr>
					<td>
						<div data-options="region:'north',height:30px" title="通知标题：">
							标题：<input class="easyui-validatebox" name="title"
								style="width:60%"  id="title" value="${dnm.title }"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						上传文件:
						<input name="name" disabled id="test2" value="${dnm.name }" >
						<input type="file" name="file" id="test" size="28"  value="上传新附件"  /> 
					</td>
				</tr>
				<tr>
					<td>内容：<textarea id="content" rows=5 name="content"
							style="width: 750px; height: 100px;"
							class="textarea easyui-validatebox" ></textarea>
					</td>
				</tr>
			</table>
			<div style="padding: 5px; text-align: center;">
				<a href="#" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="save2()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp; 
				<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="closeWin('logWin1')">取消</a>
			</div>
		</form>
	</div>	 --%>
	<!-- 发送群组消息 -->
	<div id="sendGroupWin" class="easyui-window" data-options="closed:true,modal:true,title:'群组成员名单'" style="width:900px;height:400px;">
   	<div class="easyui-layout" fit="true">
    <!-- 左侧组织机构树 -->
   	<div  data-options="region:'west'" style="width:200px;height:450px;" title="群组管理" >
   	<input type="hidden" id="messageid" name="messageid">
   		<table class="easyui-datagrid" style="width:200px;height:450px;" id="roomList" data-options="singleSelect:true,url:'/ofMucRoom/queryGroup.do',method:'get',toolbar:'#qztb'">
      		<thead>
		        <tr>
		          <th data-options="field:'roomID',checkbox:true"></th>
		          <th data-options="field:'naturalName',width:160">群名称</th>
		        </tr>
	      </thead>
    </table>
  </div>
  <div	data-options="region:'center'">
  	<table class="easyui-datagrid" id="memberList" title="群成员" style="height:450px;width:800px" data-options="checkbox:true,rownumbers:true,fit:true,singleSelect:false,pagination:true,toolbar:'#tb'">
  		<thead>
  			<tr>
  				<th data-options="field:'roomID',checkbox:true"></th>
      			<!-- <th data-options="field:'jid',width:180">成员ID</th> -->
      			<!-- <th data-options="field:'nickname',width:150">成员名称</th> -->
      			<th data-options="field:'firstName',width:90">成员中文名称</th>
      			<th data-options="field:'department',width:80">部门</th>
      			<th data-options="field:'duty',width:80">职务</th>
      			<th data-options="field:'age',width:50">年龄</th>
      			<th data-options="field:'telephone',width:100">手机号</th>
      			<th data-options="field:'officephone',width:100">办公电话</th>
      			<th data-options="field:'qq',width:100">QQ</th>
  			</tr>
  		</thead>
  	</table>
  	<div id="tb" style="padding: 5px; height: auto">
			<div style="margin-bottom: 5px">
				姓名:<input   type="text"  plain="true" id="sso" align="right" > 
         	      &nbsp;&nbsp;
            <input type="button" value="查询" onclick="sousuo()" />
			</div>
		</div>
  </div>
  	<div region="south" border="false" style="text-align:right;height:30px;line-height:25px;">
        <div align="center" >
          <a class="easyui-linkbutton"  icon="icon-ok" onclick="saveSendGroup();">发送</a>
          &nbsp;&nbsp;&nbsp;&nbsp;
          <a class="easyui-linkbutton" icon="icon-cancel" onclick="closeWin('sendGroupWin');">关闭</a>
        </div>
     </div>
  
  </div>
  </div>
	
	
	<!-- 查看详情页面 -->
	<div id="viewWin" class="easyui-window" data-options="closed:true,title:'查看详情'" style="width: 850px; height: 400px;"  >
		<table class="easyui-datagrid" id="ssss"  data-options="checkbox:true,rownumbers:true,fit:true,singleSelect:false,pagination:true,method:'get'">
		   <thead> 
		       <tr>
		          	<th data-options="field:'a_name',align:'center',sortable:true">姓名</th>
		          	<th data-options="field:'b_ORG_CNAME',align:'center',sortable:true">部门</th>
		          	<th data-options="field:'d_duty',align:'center',sortable:true">职务</th>
		          	<th data-options="field:'d_age',align:'center',sortable:true">年龄</th>
		          	<th data-options="field:'d_TELEPHONE',align:'center',sortable:true">手机号</th>
		          	<th data-options="field:'d_officephone',align:'center',sortable:true">办公电话</th>
		          	<th data-options="field:'d_USER_QQ',align:'center',sortable:true">QQ</th>
					<th data-options="field:'a_accepttime',align:'center',sortable:true,formatter : formatterdate1">发送时间</th> 
					<th data-options="field:'qq',align:'center',sortable:true,formatter : format">查看状态</th>
					<th data-options="field:'a_checktime',align:'center',sortable:true,formatter : formatterdate1">查看时间</th> 
					<th data-options="field:'sssssssss',align:'center',formatter : adadda">APP/微信</th> 
		       </tr>
		   </thead> 
    	</table>
    	
	</div>
	<!-- 查看微信详情页面 -->
	<div id="viewWin1" class="easyui-window" data-options="closed:true,title:'查看微信信息详情' " style="width: 850px; height: 230px;"  >
		<table class="easyui-datagrid" id="tt"  data-options="checkbox:true,rownumbers:true,fit:true,singleSelect:false,pagination:true,method:'get'">
		   <thead> 
		       <tr>
		          	<th data-options="field:'name',align:'center'">收件人姓名</th>
					<th data-options="field:'accepttime',align:'center',formatter : formatterdate1">发送时间</th>
					<th data-options="field:'checktime',align:'center'">查看微信状态</th>
		       </tr>
		   </thead> 
    	</table>
    	
	</div>
	<!-- <div id="viewWin2" class="easyui-window" data-options="closed:true,title:'APP查看详情'" style="width: 850px; height: 230px;"  >
		<table class="easyui-datagrid" id="cons"  data-options="checkbox:true,rownumbers:true,fit:true,singleSelect:false,pagination:true,method:'get'">
		   <thead> 
		       <tr>
		          	<th data-options="field:'name',align:'center'">姓名</th>
					<th data-options="field:'checktime',align:'center',formatter : formatterdate1">查看时间</th>
					<th data-options="field:'type',align:'center'">查看状态</th>
		       </tr>
		   </thead> 
    	</table>
	</div> -->
	
	
	
	
<!-- 	<div id="td" style="padding: 5px; height: auto">
			<div style="margin-bottom: 5px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addNotice()">新增</a> &nbsp;&nbsp; 
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateNews()">修改</a> &nbsp;&nbsp; 
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteNews()">删除</a> &nbsp;&nbsp; 
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="sendPerson()">组织机构人员名单</a>&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="sendGroup()">群组人员名单</a>&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="showView()">查看详情</a>
			</div>
		</div> -->
		<!-- 查看详情信息 -->
  <div id="selectWin" class="easyui-window" data-options="closed:true,modal:true,title:'详情信息'" style="padding:5px;width:550px;height:240px;">
      <table >
			<tr>
				<td>
					<div data-options="region:'north',height:30px" title="">
						标题：<input class="easyui-validatebox" name="affichename" 
							style="width: 40%"  id="noticeTitle" readonly>
					</div>
				</td>
			</tr>
			<tr>
				<td>内容：<textarea id="noticeContent"  rows=5 name="content" 
						style="width: 300px; height: 50px;margin-top:5px;	"
						class="textarea easyui-validatebox" readonly></textarea>
				</td>
			</tr>
		</table>
	
		

    <div style="padding: 5px; text-align: center;">
      <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-cancel" onclick="closeSelectWin()" >关闭</a>
    </div>
  </div>
</body>
</html>