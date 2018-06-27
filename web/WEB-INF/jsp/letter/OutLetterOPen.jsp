<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
<!-- widget content -->

<div class="" style="border: solid 1px red">
    <table id="user" class="table table-bordered table-striped" style="clear: both">
        <tbody>
        <div  >
            <tr class="trHide">
                <td style="width:15%;">项目目标</td>
                <td style="width:85%" colspan="2">
                    项目目标
                </td>
            </tr>
        </div>
        </tbody>
    </table>
        <div class="col-sm-9">
            标题：   <strong>${message.messageTitle}</strong><br>
            发件人： ${message.messageFromName}<br>
            收件人： ${message.messageToName}<br>
            时间：  <fmt:formatDate value="${message.createDate}"  type="both" />

    </div>
    <div class="row">

        <button class="btn btn-primary btn-sm replythis pull-right" id="replyButton">
            <i class="fa fa-reply"></i> 回复
        </button>

    </div>
</div>

<div class="inbox-message">

    <p>
        内容：
    </p>
    <p>
        ${message.messageContent}
    </p>
</div>

<div class="inbox-download">



    <c:forEach items="${attList}" var="att">
    <ul class="inbox-download-list">
        <li>
            <div class="well well-sm">
				<span>
					<i class="fa fa-file"></i>
				</span>
                <br>
                <strong>${att.fileName}</strong>
                <br>
                <a href="/${att.fileUrl}"> 下载</a>
            </div>
        </li>

        </c:forEach>

    </ul>
</div>


<script type="text/javascript">


    $("#replyButton").click(function (){


        loadURL("/message/showReplyEmail.do?messageTo=${message.messageFrom}", $('#inbox-content > .table-wrap'));
    });

</script>

<style>
</style>