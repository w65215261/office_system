<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- Bread crumb is created dynamically -->
<title>日程详情</title>
<div align="center">
    <div style="width: 680px; text-align: center"><h2>${programmeManagement.title}</h2></div>
    <div style="width: 680px;">
        <span style="float: left">开始时间:<fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${programmeManagement.startTime}"/></span><span style="float: right">结束时间:<fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${programmeManagement.endTime}"/></span>
    </div>

    <div id="clear" style="width: 680px;min-height: 300px;text-align: left">
        <hr/>
        ${programmeManagement.description}
    </div>
</div>
<style type="text/css">
    #clear{
        clear: both;
    }
</style>
