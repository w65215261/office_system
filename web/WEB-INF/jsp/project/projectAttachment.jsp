<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section class="no-padding">
  <div class="row" >
    <div class="inbox-download col-md-12" style="border-width: 0px 0px;">
      <ul class="inbox-download-list col-md-12">
        <c:forEach items="${taskAttachmentList}" var="atts">
        <li class="col-md-12">
          <span class="col-md-6" style="width: 50px;">
            <i class="fa fa-file-text-o" style="font-size: 40px;"></i>
          </span>
          <span class="col-md-10">
            <strong>${atts.fileName}</strong>
          </span>
          <span class="col-md-9">
            <strong>${atts.rptPerson}&nbsp;上传于&nbsp;</strong>
            <strong><fmt:formatDate pattern="yyyy/MM/dd" value="${atts.createDate}"/></strong>
          </span>
          <span class="col-md-2 pull-right">
            <a href="/${atts.fileUrl}" class="pull-right">
              下载
            </a>
          </span>
          <span class="col-md-12">
            <HR>
          </span>
        </li>
        </c:forEach>
      </ul>
    </div>
  </div>
</section>
<script type="text/javascript">
  $('#fileNumber').text('文件（'+'${taskAttachmentList.size()}'+'）');
</script>