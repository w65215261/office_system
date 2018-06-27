<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>人员管理</title>
<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
<script type="text/javascript">
    function formSubmit(obj){
    	var newPassword = $('#password1').val();
    	var twoPassword = $('#password2').val();
    	if(newPassword!='' && twoPassword!=''){
    		if(newPassword!=twoPassword){
    			$.messager.alert('温馨提示',"两次输入的密码不一致！");
        		return;
    		}else{
    			$.ajax({
					type:"POST",
					url:'/personManage/updatePassword.do?md5Pwd='+newPassword,
					success:function(data){
						if(data == "success"){
							$.messager.alert('温馨提示','密码修改成功！');
							$('#passwordWin').window('close');
	       	        	}else{
	       	        		$.messager.alert('温馨提示','操作失败！');
	       	        	}
	       	        },
	       	           //调用出错执行的函数
	       	        error: function(){
	       	        }         
	       	    });
    		}
		}else{
			$.messager.alert('温馨提示',"请将内容填写完整！");
		}
    }    
    </script>
 
 
</head>
<body class="easyui-layout">
<center>
<div id="passwordWin" data-options="closed:true,modal:true,title:'修改密码'" position="absolute" style="width:300px;height:300px; margin-top:100px;">
    	
    	<form  method="post"  id="updateForm" >
	    <input type="hidden" value="" name="id" id="id"> 

	    <table cellpadding="5" width="100%">

   	 		<tr>
			    <td width="35%">新&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;码:</td>
			    <td><input type="password" id="password1" class="easyui-validatebox" ></td>
  			</tr>
   	 		<tr>
			    <td width="20%">新密码确认:</td>
			    <td><input type="password" id="password2" class="easyui-validatebox"></td>
  			</tr>
	    </table>
	    
	    <div style="text-align:center;padding:5px">
	    <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-ok" onclick="formSubmit(this.form);">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-cancel" onclick="closeWin()" >取消</a>	
		</div>
    	</form>
    	
    </div></center>
</body>
</html>