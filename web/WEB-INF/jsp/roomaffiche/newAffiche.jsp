<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.pmcc.soft.core.utils.AppUtils"%>
<%@page import="org.springframework.web.util.WebUtils"%>
<%@page import="com.pmcc.soft.core.common.OnlineUser"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>中平信通合同管理系统</title>
<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
<script type="text/javascript" src="/EasyUiCompoment/commons.js"></script>
<script type="text/javascript">
/* function saveRoom(){
	 var   roomid=$('#id').val();
	  $("#roomForm").submit(function()//提交表单
			    {
			            var options = {
			                url:'../../dlFilesManager/uploadFile.do', //提交给哪个执行
			                type:'POST',
			                success: function(msg){
			                	if(msg == "false"){
			                		$.messager.alert('温馨提示','操作失败，所传文件大小已超出所在群的空间大小，如需扩展空间，请联系管理员！');
			                	}else{
			                		//$.messager.alert('温馨提示','上传成功！');
			                		window.location.href = '/myRoomManager/getShow.do?roomid='+roomid +"&&title=113";
			                	}
			                } //显示操作提示
			            };
			            $('#roomForm').ajaxSubmit(options);
			           
			            return false; //为了不刷新页面,返回false，反正都已经在后台执行完了，没事！

			    });
		$('#roomForm').submit();
	  } */
//保存
function getContent(){
	var affichename = document.getElementById("affichename").value;
	if(!affichename == "" && affichename != null){
		$("#roomForm11").submit(function(){
			            var options = {
			                url:'../dlRoomAffiche/uploadFile.do', //提交给哪个执行
			                type:'POST',
			                success: function(msg){
			                	if(msg == "success"){
			                		window.returnVal = true;
			                		OpenClose();
			                	}else{
			                		//$.messager.alert('温馨提示','上传成功！');
			                		$.messager.alert('温馨提示','操作失败!');
			                	}
			                } //显示操作提示
			            };
			            $('#roomForm11').ajaxSubmit(options);
			           
			            return false; //为了不刷新页面,返回false，反正都已经在后台执行完了，没事！

			    });
		$('#roomForm11').submit();
		//$('#roomForm').submit();
		/* if(/\.(gif|jpg|jpeg|png|GIF|JPG|PNG|)$/.test(file) || file == "" || file == null){
	    	
	    }else{
	    	 $.messager.alert('温馨提示','图片类型必须是.gif,jpeg,jpg,png中的一种');
	    } */
	    /* $.ajax({
	    	type : 'post',
	    	url : '/dlRoomAffiche/uploadFile.do',
	    	data : $('#roomForm').serialize(),
	    	success : function(data){
	    		if(data == "success"){
	    			OpenClose();
	    		}
	    		
	    	}
	    }); */
		//$('#roomForm').submit();
		
	}else{
		 $.messager.alert('温馨提示','标题不能为空！');
	}
    
	
}



function goBack(){
	window.returnVal=false;
	OpenClose();
}
	
		
</script>
<style type="text/css">
.file-box{ position:relative;width:340px}
.txt{ height:22px; border:1px solid #cdcdcd; width:180px;}
.btn{ background-color:#FFF; border:1px solid #CDCDCD;height:24px; width:70px;}
.file{ position:absolute; top:0; right:80px; height:24px; filter:alpha(opacity:0);opacity: 0;width:260px }
</style>
</head>

<body class="easyui-layout">
	<div data-options="fit:true">
	<form id="roomForm11" action=""  method="post" enctype="multipart/form-data">
		 <input type="hidden" name="roomid" id="roomid" value="${da.roomid}">    
		<table width="85%" margin-left:10px; border="0" cellspacing="1" cellpadding="1" >
			<tr>
				<td>
					<div data-options="region:'north',height:30px" title="">
						标题：<input class="easyui-validatebox" name="affichename"
							style="width: 30%" data-options="required:true" validtype="length[0,50]" id="affichename">
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div class="file-box"> 

						上传：<input type='text' name='textfield' id='textfield' class='txt' /> 
						<input type='button' class='btn' value='浏览...' /> 
						<input type="file" name="file" class="file" id="file" size="28" onchange="document.getElementById('textfield').value=this.value" /> 
						
						
					</div> 
				</td>
			</tr>
			<tr>
				<td>内容：
				       <textarea id="content" rows=5 name="content"
						style="width: 550px; height: 250px;"
						class="textarea easyui-validatebox"></textarea>
				</td>
			</tr>
		</table>
	

		<div style="text-align:center;height:35px; margin-left:8%;margin-top:15px;">
		<a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-ok" onclick="getContent()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
      	<a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-cancel" onclick="goBack()" >返回</a>
			<!-- <input type="button" value="保存" onclick="getContent()" />
			&nbsp;&nbsp; <input type="button" value="返回" onclick="goBack()" /> -->
		</div>
		</form>
	</div>
</body>
</html>