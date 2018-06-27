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

//保存
function getContent(){
	var affichename = document.getElementById("affichename").value;
	if(!affichename == "" && affichename != null){
	     $("#roomForm").submit(function()//提交表单
				    {
				            var options = {
				                url:'/dlRoomAffiche/uploadFile.do', //提交给哪个执行
				                type:'POST',
				                success: function(data){
				                	if(data == "success"){
				                		window.returnVal = true;
				            	         OpenClose();    
				                	}
				                } //显示操作提示
				            };
				            $("#roomForm").ajaxSubmit(options);
				          
				            return false; //为了不刷新页面,返回false，反正都已经在后台执行完了，没事！
				           
				    });
	      $("#roomForm").submit(); 
	}else{
		 $.messager.alert('温馨提示','标题不能为空！');
	}
    
	
}

	
function goBack(){
	window.returnVal = false;
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
	<form id="roomForm" action="/dlRoomAffiche/uploadFile.do" method="post" enctype="multipart/form-data">
		 <input type="hidden" name="afficheid" id="afficheid" value="${da.afficheid}">    
		 <input type="hidden" name="roomid" id="roomid" value="${da.roomid}">    
		 <input type="hidden" name="imagesize" id="imagesize" value="${da.imagesize}">    
		 <input type="hidden" name="imagepath" id="imagepath" value="${da.imagepath}"> 
		<table >
			<tr>
				<td>群名称：<input id="naturalName" name="naturalName" style="width: 30%" class="easyui-validatebox" 
							value="${da.bz1 }" readonly/>
				</td>
			</tr>
			<tr>
				<td>
					<div data-options="region:'north',height:30px" title="">
						标&nbsp;&nbsp;&nbsp;题：<input class="easyui-validatebox" name="affichename" value="${da.affichename}"
							style="width: 30%"  id="affichename">
					</div>
				</td>
			</tr>

			<tr>
			
				<td>
					<div class="file-box"> 
						上&nbsp;&nbsp;&nbsp;传：<input type='text' value="${da.imagename }"  name='imagename' id='imagename' class='txt' /> 
						<input type='button' class='btn' value='浏览...' /> 
						<input type="file" name="file" class="file" id="file" size="28" onchange="document.getElementById('imagename').value=this.value" /> 
					</div> 
				</td>
			</tr>
			<tr>
				<td>内&nbsp;&nbsp;&nbsp;容:<textarea id="content"  rows=5 name="content" 
						style="width: 550px; height: 200px"
						class="textarea easyui-validatebox">${da.content}</textarea>
				</td>
			</tr>
		</table>
	</form>

		<div style="text-align:center;height:35px; margin-left:8%;margin-top:15px;">
		<a class="easyui-linkbutton"  icon="icon-ok" onclick="getContent()">保存</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a class="easyui-linkbutton" icon="icon-cancel" onclick="goBack()">返回</a> 

		</div>
	</div>
</body>
</html>