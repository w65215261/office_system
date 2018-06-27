<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=uft-8">
   <title>中平信通合同管理系统</title> 
</head>

<body>
<div style="height:450px;overflow:auto;">
<h1 align="center">${da.affichename}</h1>
<%-- <br>
<c:if test="${not empty da.imagepath}">
<img style="height:200px;width:200px;padding-left:244px;" src="${da.imagepath }"/>
</c:if>
<br> --%>
<h3 align="center">${da.content}</h3>

        <div class="gg_files">
                <font style="font-weight: bold;">
                    附件：
                </font>
                <ul>
                    <li>
                        <a class="fileDownload" href="../../dlFilesManager/downFile.do?fileName=${da.imagename }">${da.imagename } </a>
                    </li>
                </ul>
            </div>

</div>
</body>
</html>