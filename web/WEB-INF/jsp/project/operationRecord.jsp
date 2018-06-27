<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
  <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <div class="well well-sm" style="background-color: white">
      <div class="smart-timeline">
        <ul class="smart-timeline-list" >
          <c:forEach items="${operationRecords}" var="ors">
            <c:if test="${ors.distinguish==0}">
              <div>
                  ${ors.modifyDate}
              </div>
              <li style="padding: 0;">
                <div class="smart-timeline-time">
                  <small>${ors.modifyTime}</small>
                </div>
                <div class="smart-timeline-content">
                  <p>
                    <i>${ors.operationPersonName}&nbsp;</i>
                    <i>将项目名称&nbsp;</i>
                    <i>修改为&nbsp;</i>
                    <strong>${ors.newContent}</strong>
                  </p>
                </div>
              </li>
            </c:if>
            <c:if test="${ors.distinguish==1}">
              <div>
                  ${ors.modifyDate}
              </div>
              <li style="padding: 0;">
                <div class="smart-timeline-time">
                  <small>${ors.modifyTime}</small>
                </div>
                <div class="smart-timeline-content">
                  <p>
                    <i>${ors.operationPersonName}&nbsp;</i>
                    <i>将项目&nbsp;</i>
                    <strong>${ors.newContent}</strong>
                  </p>
                </div>
              </li>
            </c:if>
            <c:if test="${ors.distinguish==2}">
              <div>
                  ${ors.modifyDate}
              </div>
              <li style="padding: 0;">
                <div class="smart-timeline-time">
                  <small>${ors.modifyTime}</small>
                </div>
                <div class="smart-timeline-content">
                  <p>
                    <i>${ors.operationPersonName}&nbsp;</i>
                    <i>将项目目标&nbsp;</i>
                    <strong>${ors.oldContent}&nbsp;</strong>
                    <i>修改为&nbsp;</i>
                    <strong>${ors.newContent}</strong>
                  </p>
                </div>
              </li>
            </c:if>
            <c:if test="${ors.distinguish==3}">
              <div>
                  ${ors.modifyDate}
              </div>
              <li style="padding: 0;">
                <div class="smart-timeline-time">
                  <small>${ors.modifyTime}</small>
                </div>
                <div class="smart-timeline-content">
                  <p>
                    <i>${ors.operationPersonName}&nbsp;</i>
                    <i>将项目的起止时间&nbsp;</i>
                    <strong>${ors.oldContent}&nbsp;</strong>
                    <i>修改为&nbsp;</i>
                    <strong>${ors.newContent}</strong>
                  </p>
                </div>
              </li>
            </c:if>
            <c:if test="${ors.distinguish==4}">
              <div>
                  ${ors.modifyDate}
              </div>
              <li style="padding: 0;">
                <div class="smart-timeline-time">
                  <small>${ors.modifyTime}</small>
                </div>
                <div class="smart-timeline-content">
                  <p>
                    <i>${ors.operationPersonName}&nbsp;</i>
                    <i>将项目负责人&nbsp;</i>
                    <i>转交给&nbsp;</i>
                    <strong>${ors.newContent}</strong>
                  </p>
                </div>
              </li>
            </c:if>
            <c:if test="${ors.distinguish==5}">
              <div>
                  ${ors.modifyDate}
              </div>
              <li style="padding: 0;">
                <div class="smart-timeline-time">
                  <small>${ors.modifyTime}</small>
                </div>
                <div class="smart-timeline-content">
                  <p>
                    <i>${ors.operationPersonName}&nbsp;</i>
                    <i>将项目私密性&nbsp;</i>
                    <strong>${ors.oldContent}&nbsp;</strong>
                    <i>修改为&nbsp;</i>
                    <strong>${ors.newContent}</strong>
                  </p>
                </div>
              </li>
            </c:if>
            <c:if test="${ors.distinguish==6}">
              <div>
                  ${ors.modifyDate}
              </div>
              <li style="padding: 0;">
                <div class="smart-timeline-time">
                  <small>${ors.modifyTime}</small>
                </div>
                <div class="smart-timeline-content">
                  <p>
                    <i>${ors.operationPersonName}&nbsp;</i>
                    <i>将项目进度改到&nbsp;</i>
                    <strong>${ors.newContent}</strong>
                  </p>
                </div>
              </li>
            </c:if>
            <c:if test="${ors.distinguish==7}">
              <div>
                  ${ors.modifyDate}
              </div>
              <li style="padding: 0;">
                <div class="smart-timeline-time">
                  <small>${ors.modifyTime}</small>
                </div>
                <div class="smart-timeline-content">
                  <p>
                    <i>${ors.operationPersonName}&nbsp;</i>
                    <strong>创建了项目</strong>
                  </p>
                </div>
              </li>
            </c:if>
          </c:forEach>
        </ul>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
  pageSetUp();
  var pagefunction = function() {

  };
  pagefunction();
</script>
