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
	    <!--  附件上传 -->
    	<script type="text/javascript" src="/EasyUiCompoment/upload.js"></script>
    	<script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
	    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"> </script>
	    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
	</head>
	<body data-options="fit:true">
	<div>
	 <h1 ><center>${newsInfo.title}</center></h1>
	<br>
	${newsInfo.content}
	</div>
	</body>
</html>