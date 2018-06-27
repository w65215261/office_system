<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>重置密码页面</title>

	<link href='/EasyUiCompoment/noticeModel/css/easyui.css' rel='stylesheet' type='text/css' />
	
	<script type="text/javascript" src="/EasyUiCompoment/jquery.min.js"></script>
	<script type="text/javascript" src="/EasyUiCompoment/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
	<script type="text/javascript" src="/EasyUiCompoment/commons.js"></script>

<script type="text/javascript">
	function findPassword() {
		var username = $('#username').val();
    	var email = $('#email').val();
    	if(username == '' || email == '') {
    		$.messager.alert('提示', '请将内容填写完整！');
    		return false;
    	}
    	var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/g;
    	if(!reg.test(email)) {
    		$.messager.alert('提示', '邮箱格式错误！');
    		return false;
    	}
    	
   	 $.messager.confirm("提示", "确认发送?", function(data){
	    	if(!data) return false;
   			$.ajax({
				type:"POST",
				url:'/mobileManager/sendMail.do',
				data:{'username':username, 'email':email},
				success:function(data){
					if(data.state == "0") {
						window.returnVal=data.content;
							OpenClose(); 
					} else if (data.state == "1") {
						$.messager.alert('提示', data.content);
       	        	}else{
       	        		$.messager.alert('提示', '操作失败！');
       	        	}
       	        },
       	           //调用出错执行的函数
       	        error: function(){
       	        }         
       	    });
		});
	}
	 function goBack() {
		 window.returnVal;
         OpenClose();   
	 }
	</script>
	<style>
	  .login-input{
	  	width:238px; 
	  	height:40px;
	  	line-height:40px;
	  	background-color:#eeeeee;
	  	border:0px;
	  	margin:0;
	  	padding:0;
	  	padding-left:6px;
	  }
	  .btn1 {
	  				background-color: #39a1ea;
	  				border: solid 1px #1a85bd;
	  				width: 88px;
	  				height: 28px;
	  				margin-left: 38px;
	  				
	  				color: #FFF;
	  				
	  				font-weight: bold;
	  				font-size: 16px;
	  			}
	  			.btn1:hover {
	  				background-color: #136cab;
	  				cursor: pointer;
	  			}
	 </style>
</head>

<body>
		<table>
			<tr>
				<td width="26%"><p style="font-size: 20px">用户名：</p></td>
				<td width="74%" >
					<input type="text" class="login-input" id="username" value="${username }"/>
				</td>
			</tr>
			<tr>
				<td width="26%"><p style="font-size: 20px">邮&nbsp;&nbsp;&nbsp;箱：</p></td>
				<td width="74%">
					<input type="text" class="login-input"  id="email"/>
				</td>
			</tr>
			<tr><td colspan="2">&nbsp;</td></tr>
			<tr>
				<td colspan="2" >
					<div style="text-align:center;">
					<input type="button" class="btn1" value="确定" onclick="findPassword()"/>&nbsp;&nbsp;
					<input type="button" class="btn1" id="backBtn" value="返回" onclick="goBack()"/>
					</div>
				</td>
			</tr>
		</table>
</body>
</html>