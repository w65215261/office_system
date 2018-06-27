<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- Bread crumb is created dynamically -->
<title>公告详情</title>
<div align="center">
    <div style="width: 680px; text-align: center"><h2>${NewsInfo.title}</h2></div>
    <div style="width: 680px;">
        <span style="float: left">发布人:${NewsInfo.rptPerson}</span><span style="float: right">发布时间:<fmt:formatDate pattern="yyyy-MM-dd" value="${NewsInfo.rptDate}"/></span>
    </div>

    <div id="clear" style="width: 680px;min-height: 500px;text-align: left">
        <hr/>
        ${NewsInfo.content}
    </div>
</div>
<style type="text/css">
    #clear{
        clear: both;
    }
</style>
