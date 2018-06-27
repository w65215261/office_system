<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="/publicresource/btn.css">

<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
<script type="text/javascript" src="/EasyUiCompoment/commons.js"></script>
<script type="text/javascript">
	function saveWindow() {
		var _nodes= $("#menutree").tree("getChecked");
		if(_nodes==''){
			var msgStr="未选中菜单,";
		}else{
			msgStr='';
		}
		if($('#checkData').val()>0){
			$.messager.alert('提示','编码已存在,请重新输入编码');
			return;
		}

		$.messager.confirm('提示', msgStr+'确定保存?', function(r){
			if (r){
				var ids = []; 
				$.each(_nodes,function(i,e){
					ids.push(_nodes[i].id);
				});
				$("#menuItems").val(ids);
				var roleCode = $('#roleCode').val();
				var roleName = $('#roleName').val();
				var appSystem = $('#appSystem').val();
				var re = new RegExp("^([a-zA-Z]*?)((?:[0-9]+(?:[0-9]|[a-zA-Z])*)*)$");
				
				if(roleCode!='' && roleName!='' && appSystem!='' && re.test(roleCode)){
					  $("#editForm").submit(function()//提交表单
							    {
							            var options = {
							                url:'/authorityRole/save.do', //提交给哪个执行
							                type:'POST',
							                success: function(data){
							                	if(data == "success"){
							                		window.returnVal = true;
							            	         OpenClose();    
							                	}
							                } //显示操作提示
							            };
							            $("#editForm").ajaxSubmit(options);
							            return false; //为了不刷新页面,返回false，反正都已经在后台执行完了，没事！
							           
							    });
				      $("#editForm").submit(); 
					/* $('#editForm').form('submit', {
						url : 'save.do',
						onSubmit : function() {
						},
						success : function(data) {
							var data = eval('(' + data + ')');
							if (data.success) {
								$.messager.alert('温馨提示', '保存成功！');
								window.location.href="show.do";
								 window.returnVal = true;
			                     OpenClose();
							}
						}
					}); */
				}else{
					$.messager.alert('温馨提示', '请按正确格式将信息填写完整！');
					return;
				}
			}
		});
		
		
		
	
	}

	function clearWindow() {
		 var vid = $("#id").val();
		if (vid == '') {
			$('#editForm').form('clear');
		} else {
			$('#editForm').form('load', 'find.do?id=' + vid);
		} 
		showTree();
	}

	function showTree(){
		 $('#menutree').tree({
			 url:'/MenuItemInfo/getEditMenu.do?role='+$("#id").val()
		 }); 
	}
	
	
	
	$(function() {
		$('#menutree').tree({
			onLoadSuccess : function(node, data) {
				/* 展开所有节点 */  
	            $("#menutree").tree('expandAll');  
			},
			onCheck : function(node, checked) {
				if (checked) {
					$(this).tree('expand', node.target);
				} else {
					$(this).tree('collapse', node.target);
				}
			}
		});
		

		clearWindow();
	});
	
	function goBack(){
		window.returnVal = false;
		OpenClose();
	}
	
	function checkRoleCode(){
		  $.ajax({
	          //提交数据的类型 POST GET
	          type:"POST",
	          //提交的网址
	          url:"/authorityRole/checkData.do",
	          //提交的数据
	          data:{
	        	  roleCode:$('#roleCode').val(),
	        	 	    id:$('#id').val()
	        	 		  },
	          //返回数据的格式
	          //datatype: "html",//"xml", "html", "script", "json", "jsonp", "text".
	          //在请求之前调用的函数
	         // beforeSend:function(){$("#msg").html("logining");},
	          //成功返回之后调用的函数             
	          success:function(data){
	             if((data.num)*1>0){
	           		$.messager.alert('提示','编码已存在,请重新输入编码');
	           		$('#checkData').val((data.num)*1);
	           		
	             }        
	          },
	          //调用执行后调用的函数
	          complete: function(XMLHttpRequest, textStatus){
	             //alert(XMLHttpRequest.responseText);
	             //alert(textStatus);
	              //HideLoading();
	          },
	          //调用出错执行的函数
	          error: function(){
	              //请求出错处理
	          }         
	       });
	}
</script>

</head>
<body >
<div class="easyui-layout" style="width:700px;height:300px">
	<div data-options="region:'east',split:true,title:'菜单权限'"  style="width:200px;padding:5px">
	     	  <ul id="menutree" class="easyui-tree" data-options="lines:true,checkbox:true" ></ul>
	</div>
	<div data-options="region:'center',fit:true"  style="padding: 5px;">

		 <form id="editForm" method="post">
		    <br>
		    <br>
			<input type="hidden" id="id" name="id" value="${id}">
			<input type="hidden" id="menuItems"  name="menuItems">
			<input type="hidden" id="checkData"   >
			<table width="70%" border="0" cellspacing="10" cellpadding="0" height="90%">
				<tr>
					<td style="text-align:right;">英文名称:</td>
					<td><input type="text" name="roleCode" class="easyui-validatebox" data-options="required:true,validType:'english'" id="roleCode"  onblur="checkRoleCode()"/></td>
					<td style="text-align:right;">名称:</td>
					<td><input type="text" name="roleName" id="roleName" class="easyui-validatebox" data-options="required:true"/></td>
				</tr>
				<tr>
					<td style="text-align:right;">备注:</td>
					<td><textarea style="resize:none;width:151px;height:70px;" name="remark" ></textarea></td>
					<td style="text-align:right;">描述:</td>
					<td><textarea style="resize:none;width:151px;height:70px;" name="desc"></textarea></td>
				</tr>
				<tr>
					<td colspan="4" align="center" style="height:60px">
					<a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-ok" onclick="saveWindow();">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;	
            <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-cancel" onclick="goBack()" >返回</a>
					</td>
				</tr>
			</table>
		</form> 

	</div>
</div>
</body>
</html>