<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="" style="width: 95%">
    <div class="row">
        <div class="">
            <div style="margin-left: 10px">
                标&nbsp;&nbsp;&nbsp;&nbsp;题：   ${message.messageTitle}
            </div>
            <div style="margin: 10px">
                发件人：  ${message.messageFromName}
            </div>
            <div style="margin: 10px">
                收件人：  ${message.messageToName}
            </div>
            <div style="margin: 10px">
                时&nbsp;&nbsp;&nbsp;&nbsp;间：  <fmt:formatDate value="${message.createDate}"  type="both" />
            </div>
            <div style="margin-top: 10px;margin-left:10px;padding-bottom:15px;border-bottom: solid 1px  #BFBFBF;">内&nbsp;&nbsp;&nbsp;&nbsp;容:
                <button class="btn btn-primary btn-sm  pull-right" id="replyButton" style="margin-bottom: 50px">
                    <i class="fa fa-reply"></i> 回复</button>

            </div>
            <div style="margin-left: 10px">
                ${message.messageContent}
            </div>
        </div>
    </div>
</div>
<div class="inbox-download" style="width: 95%;min-height: 200px">



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
