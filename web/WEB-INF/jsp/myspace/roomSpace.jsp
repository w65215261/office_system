<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>群和成员管理</title>
  <script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
  <script type="text/javascript">
	var documentRoomid = "";
  // 查询按钮
  function search(){ 
  	  var sval=$("#TextValue").val();
  	  $('#mc').datagrid({
			url : '/dlFilesManager/findByName.do?filename=' + sval
		});  	  
  }


  
  
  //保存
/*   $('#roomForm').form({
	  url:'/dlFilesManager/uploadFile.do?roomid=' + roomid,
	  
  }); */
  $(document).ready(function(){
	  var id = $("#id").val();
	  $("#mc").datagrid({
			url : '/dlFilesManager/query.do?id=' + id
		});  	  
	  
  });
	function saveRoom(){
	  $("#roomForm").submit(function()//提交表单
			    {
			            var options = {
			                url:'/dlFilesManager/uploadFile.do', //提交给哪个执行
			                type:'POST',
			                success: function(msg){
			                	if(msg == "false"){
			                		$.messager.alert('温馨提示','操作失败，所传文件大小已超出所在群的空间大小，如需扩展空间，请续费！');
			                	}else{
			                		$.messager.alert('温馨提示','上传成功！');
			                		window.location.href = "/dlFilesManager/show.do?id="+ documentRoomid;
			                	}
			                   
			                    //$("#progress").hide();
			                    //alert($('#Tip').text());
			                } //显示操作提示
			            };
			            $('#roomForm').ajaxSubmit(options);
			           
			            return false; //为了不刷新页面,返回false，反正都已经在后台执行完了，没事！

			    });
		$('#roomForm').submit();
// 	  $.ajax({
// 			type:"POST",
// 			url:'/dlFilesManager/sizeVerification.do',
// 			data: {roomid : documentRoomid},
// 			success:function(data){
// 				alert(data);
// 				if(data == "false"){
// 					$.messager.alert('温馨提示','超出群空间所拥有内存，如需增加内存请联系管理员');
					
// 	        	}else if(data == "true"){
// 	        		//$('#roomForm').form('clear');
	        	
// 	        		//$.messager.alert('温馨提示','操作成功！');
// 	        	}
// 	        },
// 	        error: function(){
// 	        	$.messager.alert('温馨提示','不会走到这吧');
// 	        }         
// 	    });
	  }
  

  //删除
  function deleteNews(){
 	 var oneTest = $("#mc").datagrid("getSelections");
 	
 	 if(oneTest==null||oneTest.length==0){
 		 $.messager.alert('提示','请先选择要删除的新闻');
  	}else{
  			var ids="";
      		for(var i=0;i<oneTest.length;i++){
      			ids+=oneTest[i].fileid+",";
      		}
  	  	$.messager.confirm('温馨提示','是否确认删除？',function(r){
     		if(r){
     	
     			$.ajax({
 					type:"GET",
 					url:'/dlFilesManager/delete.do',
 					data : {
 						ids : ids
 					},
 					success:function(data){
 						if(data == "success"){
 							$.messager.alert('温馨提示','操作成功！');
 							window.location.href = "/dlFilesManager/show.do";
 	       	        	}else{
 	       	        		$.messager.alert('温馨提示','操作失败！');
 	       	        	}
 	       	        },
 	       	        error: function(){
 	       	        }         
 	       	    });
     		}
 		});
  	}
  }
  //上传
  function releaseNews(){
	  var roomid = $("#roomList").datagrid("getSelections");
	 
	  if(roomid == null || roomid.length == 0){
		  $.messager.alert('温馨提示','请选择上传群组');
	  }else{
		 
			  
		 
					 $('#addRoomWin').window('open');
				 }
				  
			     
		 
		  
			 
		 
		  
		  $('#roomid').val(documentRoomid);
	  }
  	 
  
  function closeRoomWin(){
      $('#addRoomWin').window('close');
    }
  
  function formatterdate(val,row){
   	return '<a href="/dlFilesManager/downFile.do?filename=' + row.filename+'" >下载</a> '
  	
  }
    
  
  //点击左侧出右侧
    $(document).ready(function(){
        $('#roomList').datagrid({
        	singleSelect:true,
        	url:'/ofMucRoom/selectMyRoomListByCurrentUser.do',
        	method:'get',
          onCheck: function(rowIndex,rowData) {
        	  documentRoomid = rowData.roomID;
            $('#mc').datagrid({url:'/dlFilesManager/query.do?roomid='+documentRoomid,method:'GET'});
          }
        });
      });




  </script>
</head>
<body class="easyui-layout">
<!--左侧群组管理-->
  <div  data-options="region:'west',split:true" style="width:200px;" title="群组管理" >
  <input type="hidden" id="id" name="id" value="${id}"/>
    <table class="easyui-datagrid" id="roomList" data-options="">
      <thead>
        <tr>
          <th data-options="field:'roomID',checkbox:true"></th> 
          <th data-options="field:'naturalName',width:160">群名称</th>
        </tr>
      </thead>
    </table>

  </div>
 <div  region="center" >
    <table class="easyui-datagrid" id="mc"  title="列表" style="height:450px;"
            data-options="checkbox:true,rownumbers:true,fit:true,singleSelect:false,pagination:true,method:'get',toolbar:'#tb1'">
        <thead>
        
            <tr>
            	
                <th data-options="field:'userorroomid',checkbox:true"></th>
                <th data-options="field:'filename',width:300">文件名称</th>
                <th data-options="field:'filesize',width:200">文件大小(M)</th>
                <th data-options="field:'createtime',width:150">上传时间</th>
              	<th data-options="field:'aaa',formatter : formatterdate,width:150">下载</th>
            </tr>
        </thead>
    </table>

    <div id="tb1" style="padding:5px;height:auto">
    	<div id="tb2" style="padding:5px;height:auto">
    		<div style="margin-bottom:5px">
    			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="releaseNews()" >上传</a>
	            &nbsp;&nbsp;
	           <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteNews()" >删除</a>
    		</div>
    	</div>
      <table width="100%">
        <tr>
          <td width="100%">
            文件名称:
          <div id="div_searchTextValue" style="width:200px;display:inline;">
    		<input id="TextValue" type="text" style="width:200px" />
    	   </div >
            <input type="button" value="查询" onclick="search()" />
            
          </td>
        </tr>
      </table>
    </div>
    </div>
    
  <!-- 上传 -->
  <div id="addRoomWin" class="easyui-window" data-options="closed:true,modal:true,title:'上传'" style="padding:5px;width:450px;height:240px;">
    <form id="roomForm" action=""  method="post" enctype="multipart/form-data">
      <input type="hidden"  name="roomid" id="roomid"/>    
      <table cellpadding="5" width="100%">
        <tr>
          <td>上传文件:</td>
          <td><input type="file" name="file"></td>
        </tr>
         <tr>
          <td>文件名称:</td>
          <td><input class="easyui-validatebox" id="filename" name="filename" data-options="required:false" validType="length[0,125]" style="width:60%"></td>
        </tr>
         <tr>
          <td>描述:</td>
          <td><input class="easyui-validatebox" id="bz1" name="bz1" data-options="required:false" validType="length[0,125]" style="width:60%"></td>
        </tr>
      </table>
    <div style="padding: 5px; text-align: center;">
      <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-ok" onclick="saveRoom()">确认</a>&nbsp;&nbsp;&nbsp;&nbsp;
      <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-cancel" onclick="closeRoomWin()" >取消</a>
    </div>
    </form>
  </div>
</body>
</html>