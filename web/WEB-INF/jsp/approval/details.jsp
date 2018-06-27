<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <form action="" method="post" id="showDetails" class="smart-form">
    <input type="hidden" id="approvalTypeHidden" name="approvalTypeHidden" value="${approvalType}">
    <input type="hidden" id="taskId" name="approvalTypeHidden" value="${taskId}">
    <fieldset>
      <div class="row">
        <c:forEach items="${approvalList}" var="approvalList">
          <input type="hidden" id="approvalHeadIdHidden" name="approvalHeadIdHidden" value="${approvalList.approvalHeadId}">
          <section>
            <label class="label col col-1 no-padding" style="width: 15%;text-align: right;font-size: 25px">${approvalList.controlTitle}：</label>
            <section class="col col-4   no-padding">
              <label style="font-size: 20px;">
                ${approvalList.controlDisplay}
              </label>
            </section>
          </section>
        <br><br>
        </c:forEach>

        <section>
          <label class="label col col-1 no-padding" style="width: 15%;text-align: right;font-size: 25px">图片：</label>
          <section class="col col-10 no-padding">
            <ul class="inbox-download-list col-md-12 no-padding" id="systemAttachmentId">
              <c:forEach items="${systemAttachments}" var="systemAttachments">
                <li class="col-md-1 no-padding" style="width: 65px;" onclick="showPicture('${systemAttachments.fileUrl}')">
                    <span>
                    <img src="${systemAttachments.fileUrl}" height="60px" width="60px"/>
                    </span>
                </li>
              </c:forEach>
            </ul>
          </section>
        </section>
        <br><br><br><br>
        <hr>
        <label class="label col col-1 no-padding" style="width: 15%;text-align: right;font-size: 25px;margin-top: 10px;">审批状态：</label>
        <br><br><br>
        <c:if test="${queryFlag !='3'}">
          <section>
            <div class="col col-3" >
            <img  class="label col col-12 no-padding" src="/smartAdmin/img/approval/2222.png" style="margin-left: 80px;" align="absmiddle"/>
    　　 <div class="no-padding" style="position:absolute;">
    　　　　<span style="display: inline-block;width: 100px;margin-left: 5px;text-align: center;color: white">${systemAuditPersonConfigs[0].rptPersonName}</span>
    　　 </div>

            <hr style="width: 250px;height:2px;border:none;border-top:2px dotted #185598;" />
            <br><br>
            <span class="col col-12 no-padding" style="display: inline-block;margin-left: 60px;width: 100px;text-align: center">
            发起申请
            </span>
            </div>
            <%--<c:if test="${systemAudits.size()!=0}">--%>
            <div class="col col-3">
              <img  class="label col col-12 no-padding" src="/smartAdmin/img/approval/2222.png" style="margin-left: 80px;"/>
              　　 <div class="no-padding" style="position:absolute;margin-left:35px;">
                      <span style="display: inline-block;margin-left: 22px;width: 100px;text-align: center;color: white">${systemAuditPersonConfigs[0].auditPersonName}</span>
              　　　　
              　　 </div>
              <hr style="width: 250px;height:2px;border:none;border-top:2px dotted #185598;" />
              <br><br>
            <span class="col col-12 no-padding" style="display: inline-block;margin-left: 60px;width: 100px;text-align: center">
            <c:if test="${systemAudits.size()!=0}">
              <c:if test="${systemAudits[0].auditStatus ==1}">
                同意
              </c:if>
              <c:if test="${systemAudits[0].auditStatus ==2}">
                拒绝
              </c:if>
              <c:if test="${systemAudits[0].auditStatus !=2 &&systemAudits[0].auditStatus !=1}">
                进行中
              </c:if>
            </c:if>
              <c:if test="${systemAudits.size()==0}">
                未开始
              </c:if>
            </span>
                <br>
            <span class="col col-12 no-padding" style="display: inline-block;margin-left: 60px;width: 100px;text-align: center;">
            <c:if test="${systemAudits.size()!=0}">
              <c:if test="${systemAudits[0].auditRemark !=''}">
                （${systemAudits[0].auditRemark}）
              </c:if>
            </c:if>
            </span>
            </div>
            <%--</c:if>--%>
          <%--<c:if test="${systemAudits.size()!=0 && systemAudits.size() == 2}">--%>
            <div class="col col-3">
              <img  class="label col col-12 no-padding" src="/smartAdmin/img/approval/2222.png" style="margin-left: 80px;"/>
              　　 <div class="no-padding" style="position:absolute;">
              　　　　<span style="display: inline-block;width: 100px;margin-left: 5px;text-align: center;color: white">${systemAuditPersonConfigs[1].auditPersonName}</span>
              　　 </div>
              <br><br><br>
            <span class="col col-12 no-padding" style="display: inline-block;margin-left: 60px;width: 100px;text-align: center">
            <c:if test="${systemAudits.size()>1}">
              <c:if test="${systemAudits[1].auditStatus == 1}">
                同意
              </c:if>
              <c:if test="${systemAudits[1].auditStatus ==2}">
                拒绝
              </c:if>
              <c:if test="${systemAudits[1].auditStatus !=2 &&systemAudits[1].auditStatus !=1}">
                进行中
              </c:if>
            </c:if>
            <c:if test="${systemAudits.size() !=2}">
            未开始
            </c:if>
            </span>
                <br>
            <span class="col col-12 no-padding" style="display: inline-block;margin-left: 60px;width: 100px;text-align: center;">
              <c:if test="${systemAudits.size()>1}">
                <c:if test="${systemAudits[1].auditRemark !=''}">
                  （${systemAudits[1].auditRemark}）
                </c:if>
              </c:if>

            </span>

            </div>
            <%--</c:if>--%>
          </section>
          <br><br>
        </c:if>
        <c:if test="${queryFlag =='3'}">
          <hr >
          <section>
            <br><br>
            <label class="label col col-1  no-padding" style="width: 15%;text-align: right;font-size: 25px">审批类型：</label>
            <section class="col col-4  no-padding">
              <label class="select" style="width: 70px;">
                <select class="input-sm" id="chooseApprovalStatus"  name="chooseApprovalStatus">
                  <option value="1">同意</option>
                  <option value="2">拒绝</option>
                </select><i class="icon-append fa fa-lock"></i>
              </label>
            </section>
          </section>
          <br><br>
          <section>
            <label class="label col col-1  no-padding" style="width: 15%;text-align: right;font-size: 25px">备注：</label>
            <section class="col col-4  no-padding">
              <label class="textarea textarea-expandable">
                <textarea id="bz" name="bz"  rows="6"  cols="120" class="custom-scroll" placeholder="填写审批意见" style="height: 80px;"></textarea>
              </label>
            </section>
          </section>
        </c:if>
      </div>
      <footer style="background-color: white;" class="no-padding">
        <div style="width: 65%;">
            <c:if test="${queryFlag =='3'}">
              <button type="button" onclick="checkApprove();" class="btn btn-primary">
                提交
              </button>
            </c:if>
            <button type="button" onclick="goBack();" class="btn btn-warning">
              返回
            </button>
          </div>
      </footer>
    </fieldset>
  </form>
<script>
  $("#login-form").hide();

  function checkApprove(){
    var auditStatus = $("#chooseApprovalStatus").val();
    var businessModel = 1;
    var businessType = $("#approvalTypeHidden").val();
    var businessData =$("#approvalHeadIdHidden").val();
    var auditRemark = $("#bz").val();
    var taskId = $("#taskId").val();
    $.ajax({
          type: "POST",
          url: '/approval/check.do',
          data:{
            auditStatus:auditStatus,
            businessModel:businessModel,
            businessType:businessType,
            businessData:businessData,
            auditRemark:auditRemark,
            taskId:taskId
          },
          success: function (data) {
            $("#login-form").show();
            $('#home').empty();
            $("#myApproval").text("");
            $('#detailsButtonShow').append('<input type="button" id="detailsButton" class="btn  btn-success btn-xs " value="详情">');
            loadURL("/approval/chooseApprovalTypeGoHtml.do?approvalType="+businessType,$('#home'));
          },
          error: function () {

          }
    });
  }
  function goBack(){
    var businessType = $("#approvalTypeHidden").val();
    $("#login-form").show();
    $('#home').empty();
    $("#myApproval").text("");
    $('#detailsButtonShow').append('<input type="button" id="detailsButton" class="btn  btn-success btn-xs " value="详情">');
    loadURL("/approval/chooseApprovalTypeGoHtml.do?approvalType="+businessType,$('#home'));
  }

  function showPicture(url){
    window.open(url);
  }
</script>
