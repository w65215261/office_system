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
	    <!--  附件上传 -->
    	<script type="text/javascript" src="/EasyUiCompoment/upload.js"></script>
    	<script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
	    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"> </script>
	    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
    	<script type="text/javascript">
	
			function goBack(){
				history.go(-1);
			}

			
		</script>
    	
	
	</head>

	<body class="easyui-layout" data-options="fit:true">
	
	<div  style="overflow:scroll;height:450px;">${newsInfo.content}</div>
	
	</body>
</html>