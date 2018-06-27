<%@ page import="com.pmcc.soft.week.domain.TreeTask" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/smartAdmin/css/BootSideMenu.css">
<link rel="stylesheet" href="/smartAdmin/css/normalize.css">
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
<!--  附件上传 -->
<script type="text/javascript" src="/EasyUiCompoment/taskAttachment.js"></script>
<style>
  .popover {
    z-index   : 1060;
    height: auto;
  }
  .summernote {
    border: 1px solid #a9a9a9;
    border-width: 1px 1px;
    min-height: 90px;
  }
  .summernote2{
    border:1px solid #a9a9a9; border-width:0px 1px 1px 1px;
    min-height: 90px;
  }
  .summernote3 {
    border: 1px solid #a9a9a9;
    border-width: 1px 1px;
    min-height: 90px;
  }
  .summernote4 {
    border: 1px solid #a9a9a9;
    border-width: 1px 1px;
    min-height: 90px;
  }
  .summernote5 {
    border: 1px solid #a9a9a9;
    border-width: 1px 1px;
    min-height: 90px;
  }
  .summernote6 {
    border: 1px solid #a9a9a9;
    border-width: 1px 1px;
    min-height: 90px;
  }
  .summernote7 {
    border: 1px solid #a9a9a9;
    border-width: 1px 1px;
    min-height: 90px;
  }
  .treeview .list-group-item {
    cursor: pointer;
  }
  .treeview span.indent {
    margin-left: 10px;
    margin-right: 10px;
  }
  .treeview span.icon {
    width: 12px;
    margin-right: 5px;
  }
  .treeview .node-disabled {
    color: silver;
    cursor: not-allowed;
  }
  .treeview .glyphicon {
    color: #1582F1;
  }
  #taskOpenClose{
    cursor: pointer;
  }
  #workHourOpenClose{
    cursor: pointer;
  }
  #experienceOpenClose{
    cursor: pointer;
  }
  #popoverSpan{
    cursor: pointer;
  }
  .box{height:30px; position:relative;}
  .box .text{float:left;border:none;background:none;height:100%;line-height:30px;border:solid 1px #ccc;}
  .box .float{height:100%;line-height:30px; text-align:center;float:left;position:absolute; top:2px;left:15px;}
</style>
<article>
  <!-- 遮罩层 div-->
  <div class="no-padding" id="taskSideBar" style="height: 99%;width: 700px;">

    <p class="alert alert-info" style="background-color:  #EEEEEE">
      <span class="semi-bold">
        <a href="javascript:void(0);"  class="btn btn-xs" style="box-shadow: none;font-size: 15px;" rel="popover" data-placement="bottom"  data-html="true" id="projectTaskStatus" onclick="loadPopoverTaskStatus()"><i class="fa fa-square-o" id="statusid"></i></a>
      </span>
      <span class="pull-right">
        <a href="javascript:void(0);" style="box-shadow: none;font-size: 15px;" class="btn btn-xs" onclick="closeTrigger()">X</a>
      </span>
      <span class="pull-right">
        <a href="javascript:void(0);" style="box-shadow: none;font-size: 15px;" class="btn btn-xs tasksOid"><i class="fa fa-trash-o" onclick="deleteTasks()"></i></a>
      </span>
    </p>

    <!-- start 任务详情 div-->
    <div class="col-sm-12">
      <div class="well well-sm well-light">
        <form class="smart-form" id="createProject2">
          <fieldset>
            <section>
              <label class="input" >
                <input type="hidden" name="projectOid2" id="projectOid2"/>
                <input type="hidden" name="oid" id="oid2"/>
                <input type="text" class="input-lg" name="taskName" id="taskName2">
              </label>
              <div class="summernote2" id="summernote2">
              </div>
            </section>
            <section>
              <div class="row">
                <section class="col col-6">
                  <label class="input"> <i class="icon-append fa fa-calendar"></i>
                    <input type="text" name="startTime" id="startTime2" placeholder="选择开始日期" readonly="true">
                  </label>
                </section>
                <section class="col col-6">
                  <label class="input"> <i class="icon-append fa fa-calendar"></i>
                    <input type="text" name="endTime" id="endTime2" placeholder="选择结束日期" readonly="true">
                  </label>
                </section>
                <section class="col col-6">
                  <label class="label">审批人：
                    <span href="javascript:void(0);" style="border-bottom: dashed 1px #39b3d7;color: #3498DB" id="receiverLabel2" onclick="showModal2()" >无</span>
                  </label>
                </section>
                <section class="col col-4">
                  <label class="label">负责人：
                    <a href="#" id="responsiblePersonOid2" data-type="select" data-pk="1" data-value="5" data-source="/projectTask/approvePerson.do">无</a>
                  </label>
                </section>
              </div>
            </section>
            <section style="padding: 15px;">
              <input type="hidden" id="coid2"/>
              <div class="row" >
                <div class="form-group col-md-12">
                  <label class="label">附件：</label>
                  <div class="col-md-6">
                    <input  class="form-control fileinput" name="fileToUpload" id="fileToUpload2" type="file" multiple="multiple"  onchange="fileSelected(2,'undefined');">
                  </div>
                  <div class="col-md-5" style="padding: 0 0 0 10px;">
                    <a id="uploadFile2"  class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="uploadFile('')">开始上传</a>
                  </div>
                  <div style="padding: 0 0 0 10px;" class="col-md-3" id="fileName2"></div>
                  <div class="col-md-2" id="fileSize2"></div>
                  <div class="col-md-2" id="fileType2"></div>
                  <div class="easyui-progressbar col-md-2" id="progressNumber2"></div>
                  <input type="hidden" name="fileTypeId" id="fileTypeId2">
                </div>
                <div class="inbox-download col-md-12" style="border-width: 0px 0px;" id="attachmentRead">
                  <%--附件详情--%>

                </div>
              </div>
            </section>
            <section>
              <div style="background-color: white;width: 96%;margin: 0 auto;">
                <div class="widget-body no-padding">

                  <div class="widget-footer smart-form" style="background-color: white">
                    <div class="btn-group">
                      <button class="btn btn-sm btn-success" type="button" onclick="postTopic2()">
                        <i class="fa fa-check"></i> 更新
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </section>
          </fieldset>
        </form>
      </div>
    </div>
    <!-- start 任务详情 div-->
    <span class="col-md-12">
        <HR style="border: none;">
    </span>
    <!-- start 分解任务 div-->
    <div class="col-sm-12">
      <div class="well well-sm well-light">
        <p class="alert alert-info" id="taskSize"></p>
        <!-- 分解任务 div-->
        <div id="closeTask">
            <form class="smart-form" id="createProject3">
              <fieldset>
                <section>
                  <label class="label">任务名称</label>
                  <label class="input">
                    <input type="text" class="input-lg" name="taskName" id="taskName3">
                  </label>
                </section>
                <section>
                  <div class="row">
                    <section class="col col-6">
                      <label class="input"> <i class="icon-append fa fa-calendar"></i>
                        <input type="text" name="startTime" id="startTime3" placeholder="选择开始日期" readonly="true">
                      </label>
                    </section>
                    <section class="col col-6">
                      <label class="input"> <i class="icon-append fa fa-calendar"></i>
                        <input type="text" name="endTime" id="endTime3" placeholder="选择结束日期" readonly="true">
                      </label>
                    </section>
                    <section class="col col-6">
                      <label class="label">审批人：
                          <span href="javascript:void(0);" style="border-bottom: dashed 1px #39b3d7;color: #3498DB" id="receiverLabel3" onclick="showModal3()" >无</span>
                      </label>
                    </section>
                    <section class="col col-4">
                      <label class="label">负责人：
                        <a href="#" id="responsiblePersonOid3" data-type="select" data-pk="1" data-value="5" data-source="/projectTask/approvePerson.do">无</a>
                      </label>
                    </section>
                  </div>
                </section>
                <section style="padding: 15px;" id="AttachmentID">
                  <input type="hidden" id="taskId3"/>
                  <input type="hidden" id="coid3"/>
                  <div class="row" >
                    <div class="form-group">
                      <label class="label">附件：</label>
                      <div class="col-md-6">
                        <input  class="form-control fileinput" name="fileToUpload" id="fileToUpload3" type="file" multiple="multiple"  onchange="fileSelected(3,'undefined');">
                      </div>
                      <div class="col-md-5" style="padding: 0 0 0 10px;">
                        <a id="uploadFile3"  class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="uploadFile('')">开始上传</a>
                      </div>
                      <div style="padding: 0 0 0 10px;" class="col-md-3" id="fileName3"></div>
                      <div class="col-md-2" id="fileSize3"></div>
                      <div class="col-md-2" id="fileType3"></div>
                      <div class="easyui-progressbar col-md-2" id="progressNumber3"></div>
                      <input type="hidden" name="fileTypeId" id="fileTypeId3">
                    </div>
                  </div>
                </section>
                <section>
                  <div style="background-color: white;width: 98%;margin: 0 auto;">
                    <div class="widget-body no-padding">
                      <label class="label">任务说明：</label>
                      <div class="summernote3" id="summernote3">
                      </div>
                      <div class="widget-footer smart-form" style="background-color: white">
                        <div class="btn-group">
                          <button class="btn btn-sm btn-success" type="button" onclick="postTopic3()">
                            <i class="fa fa-check"></i> 发布
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
                </section>
              </fieldset>
            </form>
          <!-- end widget content -->
          <div>
            <div class="col-sm-13 col-lg-13">
              <div class="col-sm-13 col-lg-13" id="nestable3">
                <ol class="dd-list" id="taskListTest">
                </ol>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- end 分解任务 div-->
    <span class="col-md-12">
        <HR style="border: none;">
    </span>
    <!-- 工时start -->
    <div class="col-sm-12">
      <div class="well well-sm well-light">
        <p class="alert alert-info" id="workHourSize"></p>
        <form class="smart-form" id="closeWorkHour">
          <fieldset>
            <section>
              <label class="input">
                <input type="hidden" name="projectOid" id="projectOid4" value="<%= request.getAttribute("projectOid")%>"/>
                <input type="text" class="input-lg" name="workHourName" id="workHourName" placeholder="工作内容">
              </label>
            </section>
            <section>
              <div class="row">
                <section class="col col-6">
                  <label class="input"> <i class="icon-append fa fa-calendar"></i>
                    <input type="text" name="workhourDate" id="workhourDate" placeholder="选择开始日期" readonly="true">
                  </label>
                </section>
                <section class="col col-4">
                  <p class="input-group">
                    <span onclick="loadPopover()" class="input-group-addon" id="inputTest" rel="popover" data-placement="bottom"  data-html="true">
                      <span class="fa fa-plus" ></span>
                      <span id="popoverSpan">工时</span>
                    </span>
                    <span class="icon-envelope"></span>
                  </p>
                </section>
              </div>
            </section>
            <section style="padding: 15px;">
              <input type="hidden" id="workHourOid"/>
              <input type="hidden" id="coid4"/>
              <div class="row" >
                <div class="form-group">
                  <label class="label">附件：</label>
                  <div class="col-md-6">
                    <input  class="form-control fileinput" name="fileToUpload" id="fileToUpload4" type="file" multiple="multiple"  onchange="fileSelected(4,'undefined');">
                  </div>
                  <div class="col-md-5" style="padding: 0 0 0 10px;">
                    <a id="uploadFile4"  class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="uploadFile('')">开始上传</a>
                  </div>
                  <div style="padding: 0 0 0 10px;" class="col-md-3" id="fileName4"></div>
                  <div class="col-md-2" id="fileSize4"></div>
                  <div class="col-md-2" id="fileType4"></div>
                  <div class="easyui-progressbar col-md-2" id="progressNumber4"></div>
                  <input type="hidden" name="fileTypeId" id="fileTypeId4">
                </div>
              </div>
            </section>
            <section>
              <div style="background-color: white;width: 96%;margin: 0 auto;">
                <div class="widget-body no-padding">
                  <div class="summernote4" id="summernote4">
                  </div>
                  <div class="widget-footer smart-form" style="background-color: white">
                    <div class="btn-group">
                      <button class="btn btn-sm btn-success" type="button" onclick="postTopic4()">
                        <i class="fa fa-check"></i> 发布
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </section>
            <section class="col-sm-13 col-lg-13">
              <div class="col-sm-13 col-lg-13" id="nestable4">
                <h6 id="workHourCollapse"><%= request.getAttribute("loginPerson")%>的工时总计：
                  <a class='btn btn-warning btn-xs' href='javascript:void(0);' id="workHourSum"><i class='fa fa-clock-o'></i></a>
                </h6>
                <ol class="dd-list" id="workhourlist">
                </ol>
              </div>
            </section>
          </fieldset>
        </form>
      </div>
    </div>
    <!-- 工时end -->
    <span class="col-md-12">
        <HR style="border: none;">
    </span>
    <!-- 心得start -->
    <div class="col-sm-12">
        <div class="well well-sm well-light">
          <p class="alert alert-info" id="experienceSize"></p>
          <div id="closeExperience">
            <form class="smart-form"  style="width: 99%;margin:0 auto">
              <fieldset>
                <section>
                  <div class="row">
                    <section class="col col-6">
                      <label class="input"> <i class="icon-append fa fa-calendar"></i>
                        <input type="text" name="startTime" id="startTime6" placeholder="选择开始日期" readonly="true">
                      </label>
                    </section>
                    <section class="col col-6">
                      <label class="input"> <i class="icon-append fa fa-calendar"></i>
                        <input type="text" name="endTime" id="endTime6" placeholder="选择结束日期" readonly="true">
                      </label>
                    </section>
                  </div>
                </section>
                <section style="padding: 15px;">
                  <input type="hidden" id="experienceOid"/>
                  <input type="hidden" id="coid5"/>
                  <div class="row" >
                    <div class="form-group">
                      <label class="label">附件：</label>
                      <div class="col-md-6">
                        <input  class="form-control fileinput" name="fileToUpload" id="fileToUpload5" type="file" multiple="multiple"  onchange="fileSelected(5,'undefined');">
                      </div>
                      <div class="col-md-5" style="padding: 0 0 0 10px;">
                        <a id="uploadFile5"  class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="uploadFile('')">&nbsp;开始上传</a>
                      </div>
                      <div style="padding: 0 0 0 10px;" class="col-md-3" id="fileName5"></div>
                      <div class="col-md-2" id="fileSize5"></div>
                      <div class="col-md-2" id="fileType5"></div>
                      <div class="easyui-progressbar col-md-2" id="progressNumber5"></div>
                      <input type="hidden" name="fileTypeId" id="fileTypeId5">
                    </div>
                  </div>
                </section>
                <section>
                  <div style="background-color: white;width: 96%;margin: 0 auto;">
                    <div class="widget-body no-padding">
                      <div class="summernote6" id="summernote6"></div>
                      <div class="widget-footer smart-form" style="background-color: white">
                        <div class="btn-group">
                          <button class="btn btn-sm btn-success" type="button" onclick="postTopic5()">
                            <i class="fa fa-check"></i> 发布
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
                </section>
              </fieldset>
            </form>
            <div>
              <div class="col-sm-13 col-lg-13">
                <div class="col-sm-13 col-lg-13" id="nestable5">
                  <ol class="dd-list" id="Experiencelist">
                  </ol>
                </div>
              </div>
            </div>
          </div>
      </div>
    </div>
    <!-- 心得end -->
  </div>
<!-- 遮罩层end div-->

  <!-- 项目 -->
  <div class="widget-body no-padding" style="width: 99%;margin: 0 auto;">
    <form class="smart-form" id="createProject">
      <fieldset>
        <section>
          <label class="label">任务名称</label>
          <label class="input">
            <input type="hidden" name="projectOid" id="projectOid" value="<%= request.getAttribute("projectOid")%>"/>
            <input type="text" class="input-lg" name="taskName" id="taskName">
          </label>
        </section>
        <section>
          <div class="row">
            <section class="col col-6">
              <label class="input"> <i class="icon-append fa fa-calendar"></i>
                <input type="text" name="startTime" id="startTime" placeholder="选择开始日期" readonly="true" readonly="true">
              </label>
            </section>
            <section class="col col-6">
              <label class="input"> <i class="icon-append fa fa-calendar"></i>
                <input type="text" name="endTime" id="endTime" placeholder="选择结束日期" readonly="true" readonly="true">
              </label>
            </section>
            <section class="col col-6">
              <label class="label">审批人：
                <input type="text" class="text"  id="receiverLabel" name="approvePersonId" readonly="true">
                <button type="button" class="btn btn-warning" onclick="showModal()"  id="approvePersonId1"  rel="popover"   data-html="true">选择</button>
              </label>
            </section>
            <section class="col col-6">
              <label class="label">负责人：
                <a href="#" id="responsiblePersonOid" data-type="select" data-pk="1" data-value="5" data-source="/projectTask/approvePerson.do">无</a>
              </label>
            </section>
          </div>
        </section>

        <section style="padding: 15px;">
          <input type="hidden" id="taskId1" value="<%= request.getAttribute("taskOid")%>"/>
          <input type="hidden" id="coid1"/>
          <div class="row" >
            <div class="form-group">
              <label class="label">附件：</label>
              <div class="col-md-6">
                <input  class="form-control fileinput" name="fileToUpload" id="fileToUpload1" type="file" multiple="multiple"  onchange="fileSelected(1,'undefined');">
              </div>
              <div class="col-md-5" style="padding: 0 0 0 10px;">
                <a id="uploadFile1"  class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="uploadFile('')">开始上传</a>
              </div>
              <div style="padding: 0 0 0 10px;" class="col-md-3" id="fileName1"></div>
              <div class="col-md-1" id="fileSize1"></div>
              <div class="col-md-2" id="fileType1"></div>
              <div class="easyui-progressbar col-md-2" id="progressNumber1"></div>
              <input type="hidden" name="fileTypeId" id="fileTypeId1">
            </div>
          </div>
        </section>
        <br>
        <section>
          <div style="background-color: white;width: 98%;margin: 0 auto;">
            <div class="widget-body no-padding">
              <label class="label">任务说明：</label>
              <div class="summernote" id="summernote">
              </div>
              <div class="widget-footer smart-form" style="background-color: white">
                <div class="btn-group">
                  <button class="btn btn-sm btn-success" type="button" onclick="postTopic()">
                    <i class="fa fa-check"></i> 发布
                  </button>
                </div>
              </div>
            </div>
          </div>
        </section>
      </fieldset>
    </form>
  </div>
  <!-- 项目end -->

  <!-- 项目任务一览 -->
  <div>
    <div class="col-sm-13 col-lg-13">
      <div class="col-sm-13 col-lg-13" id="nestable">
        <ol class="dd-list">
          <c:forEach items="${treeTasks}" var="task">
            <li class="dd-item dd3-item" data-id="1">
              <div class="dd3-content" style="background-color: white;border: 0px;">
                <c:if test="${task.tasks.size()==0}">
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </c:if>
                <span class="semi-bold">
                  <a href="javascript:void(0);" id="${task.oid}" class="btn btn-primary btn-xs" rel="popover" data-placement="bottom"  data-html="true" onclick="loadPopoverStatus('${task.oid}')">
                    <c:if test="${task.taskStatus==0}">
                      <i class="fa fa-square-o"></i>
                    </c:if>
                    <c:if test="${task.taskStatus==1}">
                      <i class="fa fa-check-square-o"></i>
                    </c:if>
                    <c:if test="${task.taskStatus==2}">
                      <i class="fa fa-spinner"></i>
                    </c:if>
                    <c:if test="${task.taskStatus==3}">
                      <i class="fa fa-times-circle-o"></i>
                    </c:if>
                    <c:if test="${task.taskStatus==4}">
                      <i class="fa fa-clock-o"></i>
                    </c:if>
                    <c:if test="${task.taskStatus==5}">
                      <i class="fa fa-pause"></i>
                    </c:if>
                  </a>
                </span>
                <span><a href="javascript:window.scrollTo( 0, 0 );" onclick="decompositionTask('${task.oid}')">${task.taskName}</a></span>
                <span class="pull-right">${task.responsiblePersonName}</span>
                <span class="pull-right"><fmt:formatDate pattern="yyyy-MM-dd" value="${task.endTime}"/>&nbsp;&nbsp;&nbsp;&nbsp;</span>
              </div>
              <c:choose>
                <c:when test="${task.tasks.size()!=0}">
                  <ol class="dd-list">
                    <c:forEach items="${task.tasks}" var="task2">
                      <li class="dd-item dd3-item" data-id="2">
                        <div class="dd3-content" style="background-color: white;border: 0px;">
                          <c:if test="${task2.tasks.size()==0}">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                          </c:if>
                          <span class="semi-bold">
                            <a href="javascript:void(0);" id="${task2.oid}" class="btn btn-primary btn-xs" rel="popover" data-placement="bottom"  data-html="true" onclick="loadPopoverStatus('${task2.oid}')">
                              <c:if test="${task2.taskStatus==0}">
                                <i class="fa fa-square-o"></i>
                              </c:if>
                              <c:if test="${task2.taskStatus==1}">
                                <i class="fa fa-check-square-o"></i>
                              </c:if>
                              <c:if test="${task2.taskStatus==2}">
                                <i class="fa fa-spinner"></i>
                              </c:if>
                              <c:if test="${task2.taskStatus==3}">
                                <i class="fa fa-times-circle-o"></i>
                              </c:if>
                              <c:if test="${task2.taskStatus==4}">
                                <i class="fa fa-clock-o"></i>
                              </c:if>
                              <c:if test="${task2.taskStatus==5}">
                                <i class="fa fa-pause"></i>
                              </c:if>
                            </a>
                          </span>
                          <span><a href="javascript:window.scrollTo( 0, 0 );" onclick="decompositionTask('${task2.oid}')">${task2.taskName}</a></span>
                          <span class="pull-right">${task2.responsiblePersonName}</span>
                          <span class="pull-right"><fmt:formatDate pattern="yyyy-MM-dd" value="${task2.endTime}"/>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        </div>
                        <c:choose>
                          <c:when test="${task2.tasks.size()!=0}">
                            <ol class="dd-list">
                              <c:forEach items="${task2.tasks}" var="task3">
                                <li class="dd-item dd3-item" data-id="3">
                                  <div class="dd3-content" style="background-color: white;border: 0px;">
                                    <c:if test="${task3.tasks.size()==0}">
                                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </c:if>
                                    <span class="semi-bold">
                                      <a href="javascript:void(0);" id="${task3.oid}" class="btn btn-primary btn-xs" rel="popover" data-placement="bottom"  data-html="true" onclick="loadPopoverStatus('${task3.oid}')">
                                        <c:if test="${task3.taskStatus==0}">
                                          <i class="fa fa-square-o"></i>
                                        </c:if>
                                        <c:if test="${task3.taskStatus==1}">
                                          <i class="fa fa-check-square-o"></i>
                                        </c:if>
                                        <c:if test="${task3.taskStatus==2}">
                                          <i class="fa fa-spinner"></i>
                                        </c:if>
                                        <c:if test="${task3.taskStatus==3}">
                                          <i class="fa fa-times-circle-o"></i>
                                        </c:if>
                                        <c:if test="${task3.taskStatus==4}">
                                          <i class="fa fa-clock-o"></i>
                                        </c:if>
                                        <c:if test="${task3.taskStatus==5}">
                                          <i class="fa fa-pause"></i>
                                        </c:if>
                                      </a>
                                    </span>
                                    <span><a href="javascript:window.scrollTo( 0, 0 );" onclick="decompositionTask('${task3.oid}')">${task3.taskName}</a></span>
                                    <span class="pull-right">${task3.responsiblePersonName}</span>
                                    <span class="pull-right"><fmt:formatDate pattern="yyyy-MM-dd" value="${task3.endTime}"/>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                  </div>
                                  <c:choose>
                                    <c:when test="${task3.tasks.size()!=0}">
                                      <ol class="dd-list">
                                        <c:forEach items="${task3.tasks}" var="task4">
                                          <li class="dd-item dd3-item" data-id="4">
                                            <div class="dd3-content" style="background-color: white;border: 0px;">
                                              <c:if test="${task4.tasks.size()==0}">
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                              </c:if>
                                              <span class="semi-bold">
                                                <a href="javascript:void(0);" id="${task4.oid}" class="btn btn-primary btn-xs" rel="popover" data-placement="bottom"  data-html="true" onclick="loadPopoverStatus('${task4.oid}')">
                                                  <c:if test="${task4.taskStatus==0}">
                                                    <i class="fa fa-square-o"></i>
                                                  </c:if>
                                                  <c:if test="${task4.taskStatus==1}">
                                                    <i class="fa fa-check-square-o"></i>
                                                  </c:if>
                                                  <c:if test="${task4.taskStatus==2}">
                                                    <i class="fa fa-spinner"></i>
                                                  </c:if>
                                                  <c:if test="${task4.taskStatus==3}">
                                                    <i class="fa fa-times-circle-o"></i>
                                                  </c:if>
                                                  <c:if test="${task4.taskStatus==4}">
                                                    <i class="fa fa-clock-o"></i>
                                                  </c:if>
                                                  <c:if test="${task4.taskStatus==5}">
                                                    <i class="fa fa-pause"></i>
                                                  </c:if>
                                                </a>
                                              </span>
                                              <span><a href="javascript:window.scrollTo( 0, 0 );" onclick="decompositionTask('${task4.oid}')">${task4.taskName}</a></span>
                                              <span class="pull-right">${task4.responsiblePersonName}</span>
                                              <span class="pull-right"><fmt:formatDate pattern="yyyy-MM-dd" value="${task4.endTime}"/>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                            </div>
                                          </li>
                                        </c:forEach>
                                      </ol>
                                    </c:when>
                                  </c:choose>
                                </li>
                              </c:forEach>
                            </ol>
                          </c:when>
                        </c:choose>
                      </li>
                    </c:forEach>
                  </ol>
                </c:when>
              </c:choose>
            </li>
          </c:forEach>
        </ol>
      </div>
    </div>
  </div>
<!-- 项目任务一览end -->
</article>
<script type="text/javascript">
  // 详情的展开与关闭
  var taskOpenCloseFlag = 0;
  var workHourOpenCloseFlag = 0;
  var experienceOpenCloseFlag = 0;
  $("#closeTask").hide();
  $("#closeWorkHour").hide();
  $("#closeExperience").hide();
  // 任务的详情的展开与关闭
  function taskOpenCloseShow(){
    if(taskOpenCloseFlag==0){
      $("#closeTask").show();
      taskOpenCloseFlag=1;
      $('#taskOpenClose').html("关闭任务");
    }else{
      $("#closeTask").hide();
      taskOpenCloseFlag=0;
      $('#taskOpenClose').html("展开任务");
    }
  }

  //工时的详情的展开与关闭
  function workHourOpenCloseShow(){
    if(workHourOpenCloseFlag==0){
      $("#closeWorkHour").show();
      workHourOpenCloseFlag=1;
      $('#workHourOpenClose').html("关闭工时");
    }else{
      $("#closeWorkHour").hide();
      workHourOpenCloseFlag=0;
      $('#workHourOpenClose').html("展开工时");
    }
  }

  //心得的详情的展开与关闭
  function experienceOpenCloseShow(){
    if(experienceOpenCloseFlag==0){
      $("#closeExperience").show();
      experienceOpenCloseFlag=1;
      $('#experienceOpenClose').html("关闭心得");
    }else{
      $("#closeExperience").hide();
      experienceOpenCloseFlag=0;
      $('#experienceOpenClose').html("展开心得");
    }
  }

  pageSetUp();
  //loadPopover的标识符
  var popoverFlag = 0;
  //开关遮罩层的标识符
  var openFlag = false;
  //保存工时的标识符
  var saveWorkHourOidFlag = 0;
  //保存心得的标识符
  var saveExperienceOidFlag = "0";

  $("#uploadFile1").hide();
  $("#uploadFile2").hide();
  $("#uploadFile3").hide();
  $("#uploadFile4").hide();
  $("#uploadFile5").hide();

  var pagefunction = function() {

    loadScript("/smartAdmin/js/plugin/x-editable/moment.min.js", loadMockJax);

    function loadMockJax() {
      loadScript("/smartAdmin/js/plugin/x-editable/jquery.mockjax.min.js", loadXeditable);
    }

    function loadXeditable() {
      loadScript("/smartAdmin/js/plugin/x-editable/x-editable.min.js", loadTypeHead);
    }

    function loadTypeHead() {
      loadScript("/smartAdmin/js/plugin/typeahead/typeahead.min.js", loadTypeaheadjs);
    }

    function loadTypeaheadjs() {
      loadScript("/smartAdmin/js/plugin/typeahead/typeaheadjs.min.js", runXEditDemo);
    }

    function runXEditDemo() {
      $('#responsiblePersonOid').editable({
        showbuttons: false
      });
    }

    $('.summernote').summernote({
      height:90,
      airMode: true
    });

    $('#nestable').nestable();
    $('#nestable').nestable("collapseAll");
    $('#nestable3').nestable();
    $('#nestable3').nestable("collapseAll");

  };
  function showTaskAttachment(oid){
    $('#oid2').val(oid);
    $('#attachmentRead').empty();
    $.ajax({
      type: "GET",
      url: '/taskAttachment/read.do',
      data:{
        taskOid:oid
      },
      success: function (data) {
        for(var i=0;i<data.length;i++){
          $('#attachmentRead').append(
                  '<ul class="inbox-download-list col-md-12">'+
                  '<li class="col-md-12">'+
                  '<span class="col-md-6" style="width: 50px;">'+
                  '<i class="fa fa-file-text-o" style="font-size: 40px;"></i>'+
                  '</span>'+
                  '<span class="col-md-6">'+
                  '<strong>'+data[i].fileName+'</strong>'+
                  '</span>'+
                  '<span class="col-md-6">'+
                  '<a href="/'+data[i].fileUrl+'"> 下载</a>'+
                  '<a href="JavaScript:deleteAttachment(\''+data[i].id+'\',1)"> 删除</a>'+
                  '</span>'+
                  '</li>'+
                  '</ul>'
          );
        }
      },
      // 调用出错执行的函数
      error: function () {

      }
    });
  }
  function showWorkHour(oid){
    $('#oid2').val(oid);
    $("#workhourlist").empty();
    var workHourSum=0;
    $.ajax({
      type: "POST",
      url: '/projectTask/findWorkHourByTaskOid.do',
      data:{
        taskOid:oid
      },
      success: function (data) {
        if(workHourOpenCloseFlag == 0){
          $('#workHourSize').html("工时("+data.length+")<span class='pull-right' id='workHourOpenClose' onclick='workHourOpenCloseShow()'>展开工时</span>");
        }else{
          $('#workHourSize').html("工时("+data.length+")<span class='pull-right' id='workHourOpenClose' onclick='workHourOpenCloseShow()'>关闭工时</span>");
        }
        if(data.length==0){
          $('#workHourCollapse').hide();
        }else{
          $('#workHourCollapse').show();
        }
        for(var i=0;i<data.length;i++){
          var foid = data[i].oid + ","+i;
          workHourSum +=data[i].workHour;
          $("#workhourlist").append(
                  "<li class='dd-item dd3-item' data-id='1'>"+
                  "<div class='dd3-content row' style='background-color: white;border: 0;'>"+
                  "<input type='text' class='input-Small col-md-7' name='workHourName'  id='workHourName"+i+"' onblur='saveWorkHour(\""+data[i].oid+"\","+i+")' style='border-width: 0px 0px;' placeholder='工作内容' value='"+data[i].workHourName+"'>"+
                  "<span>" +
                  "</span>"+
                  "<input type='text' readonly='true' name='workhourDate' id='workhourDate"+i+"' onchange='saveWorkHour(\""+data[i].oid+"\","+i+")'   class='workhourDate' style='border-width: 0px 0px;width: 100px' value='"+data[i].workHourDate+"'>"+
                  "<span>" +
                  "<a class='btn btn-warning btn-xs' rel='popover' data-placement='bottom'  data-html='true'    href='javascript:void(0);' id='workHour"+i+"' onclick='loadPopoverList(\""+data[i].oid+"\","+i+")'>" +
                  "<i class='fa fa-clock-o'></i>&nbsp;&nbsp;"+data[i].workHour+"h" +
                  "</a>" +
                  "&nbsp;&nbsp;" +
                  "</span>"+
                  "<span ><a class='accordion-toggle' href='#collapse"+i+"' data-toggle='collapse'>详情&nbsp;&nbsp;</a></span>"+
                  "<span ><a href='javascript:void(0);' onclick='deleteWorkHour(\""+data[i].oid+"\")'>删除</a></span>"+
                  "<table style='width: 96%;border: 1px dashed #a9a9a9;height: auto;' class='collapse' id='collapse"+i+"'>"+
                  "<tr>"+
                  "<td style='width: 90px;'>"+
                  "工时备注："+
                  "</td>"+
                  "<td>"+
                  "<section>"+
                  "<div style='background-color: white;width: 85%;margin: 25px; auto;'>"+
                  "<div class='widget-body no-padding'>"+
                  "<div class='summernote5' id='workhourBz"+i+"' onblur='saveWorkHour(\""+data[i].oid+"\","+i+")'>"+data[i].workHourContent+
                  "</div>"+
                  "</div>"+
                  "</div>"+
                  "</section>"+
                  "</td>"+
                  "</tr>"+
                  "<tr>"+
                  "<td style='width: 15%;'>"+
                  "附件："+
                  "</td>"+
                  "<td style='padding:0px 0px 10px 0px;'>"+
                  "<div class='col-md-6'>"+
                  "<input class='form-control fileinput' name='fileToUpload' id='fileToUpload6"+i+"' type='file' multiple='multiple'  onchange='fileSelected(6,"+i+");'>"+
                  "</div>"+
                  "<div class='col-md-5' style='padding: 0 0 0 10px;'>&nbsp;"+
                  "<a id='uploadFile6"+i+"'  class='uploadFile6 easyui-linkbutton' data-options='iconCls:\"icon-save\"' onclick='uploadFile(\""+foid+"\");'>开始上传</a>"+
                  "</div>"+
                  "<div class='col-md-6' id='fileName6"+i+"'></div>"+
                  "</td>"+
                  "</tr>"+
                  "<tr>"+
                  "<td style='width: 15%;'>"+
                  "</td>"+
                  "<td style='padding:0px 0px 10px 0px;'>"+
                  "<div class='inbox-download col-md-12' style='border-width: 0px 0px;' id='attachmentRead"+i+"'>"+
                  "</div>"+
                  "</td>"+
                  "</tr>"+
                  "</table>"+
                  "</div>"+
                  "</li>"
          );
          $.ajax({
            type: "GET",
            url: '/taskAttachment/read.do',
            data:{
              attachmentReadFlag:i,
              taskOid:oid,
              workHourOid:data[i].oid
            },
            success: function (data) {

              for(var k=0;k<data.length;k++){
                $('#attachmentRead'+data[0].attachmentReadFlag).append(
                        '<ul class="inbox-download-list col-md-12">'+
                        '<li class="col-md-12">'+
                        '<span class="col-md-6" style="width: 50px;">'+
                        '<i class="fa fa-file-text-o" style="font-size: 40px;"></i>'+
                        '</span>'+
                        '<span class="col-md-6">'+
                        '<strong>'+data[k].fileName+'</strong>'+
                        '</span>'+
                        '<span class="col-md-6">'+
                        '<a href="/'+data[k].fileUrl+'"> 下载</a>'+
                        '<a href="JavaScript:deleteAttachment(\''+data[k].id+'\',2)"> 删除</a>'+
                        '</span>'+
                        '</li>'+
                        '</ul>'
                );
              }
            },
            // 调用出错执行的函数
            error: function () {

            }
          });
        }
        $(".uploadFile6").hide();
        $('#workHourSum').html("<i class='fa fa-clock-o'></i>&nbsp;&nbsp;"+workHourSum+"h");
        $('.workhourDate').datepicker({
          dateFormat : 'yy-mm-dd',
          showOtherMonths:true,
          changeMonth:true,
          changeYear:true,
          prevText : '<i class="fa fa-chevron-left"></i>',
          nextText : '<i class="fa fa-chevron-right"></i>',
          beforeShow : function() {
            $('.workhourDate').datepicker('option', 'maxDate', new Date());
          }
        });
        $('.summernote5').summernote({
          height : 180,
          focus : false,
          tabsize : 2,
          airMode:true
        });
      },
      // 调用出错执行的函数
      error: function () {
      }
    });
  }
  function showExperience(oid){
    $('#oid2').val(oid);
    $('#Experiencelist').empty();
    $.ajax({
      type: "POST",
      url: '/projectTask/findExperienceByTaskOid.do',
      data:{
        taskOid:oid
      },
      success: function (data) {
        if(experienceOpenCloseFlag == 0){
          $('#experienceSize').html("心得("+data.length+")<span class='pull-right' id='experienceOpenClose' onclick='experienceOpenCloseShow()'>展开心得</span>");
        }else{
          $('#experienceSize').html("心得("+data.length+")<span class='pull-right' id='experienceOpenClose' onclick='experienceOpenCloseShow()'>关闭心得</span>");
        }
        for(var i=0;i<data.length;i++){
          $("#Experiencelist").append(
                  "<li class='dd-item dd3-item' data-id='1'>"+
                  "<div class='dd3-content' style='background-color: white;border: 0px;'>"+
                  "<label class='input'>"+
                  "心得日期："+
                  "<input type='text'  readonly='true' name='startTime' id='startExperienceDate"+i+"' onchange='saveExperience(\""+data[i].oid+"\","+i+")'   class='startExperienceDate' style='border-width: 0px 0px;width: 85px' value='"+data[i].startTime+"'>~"+
                  "<input type='text' readonly='true' name='endTime' id='endExperienceDate"+i+"' onchange='saveExperience(\""+data[i].oid+"\","+i+")'  class='endExperienceDate' style='border-width: 0px 0px;width: 85px' value='"+data[i].endTime+"'>"+
                  "</label>"+
                  "<span class='pull-right'><a href='javascript:void(0);' onclick='deleteExperience(\""+data[i].oid+"\")'>删除</a></span>"+
                  "<span class='pull-right' id='fillInPerson"+i+"'>填写人："+data[i].fillInPerson+"&nbsp;&nbsp;</span>"+
                  "<table style='width: 96%;height: auto;'>"+
                  "<tr>"+
                  "<td>"+
                  "<section>"+
                  "<div style='background-color: white;width: 96%;margin: 10px auto;'>"+
                  "<div class='widget-body no-padding'>"+
                  "<div class='summernote7' id='experienceContent"+i+"'  onblur='saveExperience(\""+data[i].oid+"\","+i+")'>"+data[i].experienceContent+
                  "</div>"+
                  "</div>"+
                  "</div>"+
                  "</section>"+
                  "</td>"+
                  "</tr>"+
                  "<tr>"+
                  "<td style='padding:0px 0px 0px 0px;'>"+
                  "<div class='inbox-download col-md-12' style='border-width: 0px 0px;' id='attachmentRead2"+i+"'>"+
                  "</div>"+
                  "</td>"+
                  "</tr>"+
                  "</table>"+
                  "</div>"+
                  "</li>"
          );
          $.ajax({
            type: "GET",
            url: '/taskAttachment/read.do',
            data:{
              attachmentReadFlag:i,
              taskOid:oid,
              experienceOid:data[i].oid
            },
            success: function (data) {

              for(var k=0;k<data.length;k++){
                $('#attachmentRead2'+data[0].attachmentReadFlag).append(
                        '<ul class="inbox-download-list col-md-12">'+
                        '<li class="col-md-12">'+
                        '<span class="col-md-6" style="width: 50px;">'+
                        '<i class="fa fa-file-text-o" style="font-size: 40px;"></i>'+
                        '</span>'+
                        '<span class="col-md-6">'+
                        '<strong>'+data[k].fileName+'</strong>'+
                        '</span>'+
                        '<span class="col-md-6">'+
                        '<a href="/'+data[k].fileUrl+'"> 下载</a>'+
                        '<a href="JavaScript:deleteAttachment(\''+data[k].id+'\',3)"> 删除</a>'+
                        '</span>'+
                        '</li>'+
                        '</ul>'
                );
              }
            },
            // 调用出错执行的函数
            error: function () {

            }
          });
        }
        $('.startExperienceDate').datepicker({
          dateFormat : 'yy-mm-dd',
          showOtherMonths:true,
          changeMonth:true,
          changeYear:true,
          prevText : '<i class="fa fa-chevron-left"></i>',
          nextText : '<i class="fa fa-chevron-right"></i>',
          onClose : function(selectedDate) {
            $('.endExperienceDate').datepicker('option', 'minDate', selectedDate);
          }
        });

        $('.endExperienceDate').datepicker({
          dateFormat : 'yy-mm-dd',
          showOtherMonths:true,
          changeMonth:true,
          changeYear:true,
          yearRange :'-0:+5',
          prevText : '<i class="fa fa-chevron-left"></i>',
          nextText : '<i class="fa fa-chevron-right"></i>',
          onClose : function(selectedDate) {
            $('.startExperienceDate').datepicker('option', 'maxDate', selectedDate);
          }
        });
        $('.summernote7').summernote({
          height : 180,
          focus : false,
          tabsize : 2,
          airMode:true
        });
      },
      // 调用出错执行的函数
      error: function () {

      }
    });
  }
  function showTaskList(oid){
    $('#oid2').val(oid);
    $("#taskListTest").empty();
    $.ajax({
      type: "POST",
      url: '/projectTask/findByOid.do',
      data:{
        oid:oid
      },
      success: function (data) {
        $('#statusid').removeClass();
        if(data.taskStatus == 0){
          $('#statusid').addClass("fa fa-square-o");
          $('#statusid').text("未开始");
        }
        if(data.taskStatus == 1){
          $('#statusid').addClass("fa fa-check-square-o");
          $('#statusid').text("已完成");
        }
        if(data.taskStatus == 2){
          $('#statusid').addClass("fa fa-spinner");
          $('#statusid').text("进行中");
        }
        if(data.taskStatus == 3){
          $('#statusid').addClass("fa fa-times-circle-o");
          $('#statusid').text("已取消");
        }
        if(data.taskStatus == 4){
          $('#statusid').addClass("fa fa-clock-o");
          $('#statusid').text("已延迟");
        }
        if(data.taskStatus == 5){
          $('#statusid').addClass("fa fa-pause");
          $('#statusid').text("暂停中");
        }
        $('#oid').val(oid);
        $('.summernote2').code(data.taskContent);
        $('#responsiblePersonOid2').editable('setValue',data.responsiblePersonOid,true);
        $('#responsiblePersonOid2').text(data.responsiblePersonName);
        $('#startTime2').val(data.startTime);
        $('#endTime2').val(data.endTime);
        $('#receiverLabel2').text(data.approvalPerson);
        $('#taskName2').val(data.taskName);
        $('#taskId3').val(data.taskOidFlag);
        $('#workHourOid').val(data.workHourOidFlag);
        $('#experienceOid').val(data.experienceOidFlag);
        $.ajax({
          type: "POST",
          url: '/projectTask/findTaskByOid.do',
          data:{
            oid:oid
          },
          success: function (data) {
            if(taskOpenCloseFlag == 0){
              $('#taskSize').html("任务("+data.length+")<span class='pull-right' id='taskOpenClose' onclick='taskOpenCloseShow()'>展开任务</span>");
            }else{
              $('#taskSize').html("任务("+data.length+")<span class='pull-right' id='taskOpenClose' onclick='taskOpenCloseShow()'>关闭任务</span>");
            }
            var statusAppend;
            if(data.length>0){
              for(var i=0;i<data.length;i++){
                var status = data[i].taskStatus;

                if(status==0){
                  statusAppend="<i class='fa fa-square-o'></i>";
                }
                if(status==1){
                  statusAppend="<i class='fa fa-check-square-o'></i>";
                }
                if(status==2){
                  statusAppend="<i class='fa fa-spinner'></i>";
                }
                if(status==3){
                  statusAppend="<i class='fa fa-times-circle-o'></i>";
                }
                if(status==4){
                  statusAppend="<i class='fa fa-clock-o'></i>";
                }
                if(status==5){
                  statusAppend="<i class='fa fa-pause'></i>";
                }

                $("#taskListTest").append(
                        "<li class='dd-item dd3-item' data-id='1'>"+
                        "<div class='dd3-content' style='background-color: white;border: 0px;'>"+
                        "<span class='semi-bold taskOidFlag'>"+
                        "<a href='javascript:void(0);' id='"+data[i].oid+i+"' class='btn btn-primary btn-xs' rel='popover' data-placement='bottom'  data-html='true' onclick='loadPopoverStatus(\""+data[i].oid+i+"\")'>" +
                        statusAppend +
                        "</a>"+
                        "</span>"+
                        "<span><a href='javascript:void(0);' onclick='showTaskList(\""+data[i].oid+"\")'>"+data[i].taskName+"</a></span>"+
                        "<span class='pull-right'><a href='javascript:void(0);' onclick='deleteTask(\""+data[i].oid+"\")'>删除</a></span>"+
                        "<span class='pull-right'>"+data[i].responsiblePersonName+"&nbsp;&nbsp;</span>"+
                        "<span class='pull-right'>"+data[i].endTime+"&nbsp;&nbsp;</span>"+
                        "</div>"+
                        "</li>"
                );
              }
            }
            showWorkHour(oid);
            showExperience(oid);
            showTaskAttachment(oid);
          },
          // 调用出错执行的函数
          error: function () {
          }
        });
      },
      // 调用出错执行的函数
      error: function () {
      }
    });
  }
  function decompositionTask(oid) {
    $('#oid2').val(oid);
    if (openFlag) {
      $("#projectOid2").val($("#projectOid").val());
      $("#taskListTest").empty();
      $("#workhourlist").empty();
      $('#Experiencelist').empty();
      $('#attachmentRead').empty();
      openFlag=false;
      trigger();
    } else {
      openFlag=true;
      trigger();
      var workHourSum = 0;
      $.ajax({
        type: "GET",
        url: '/taskAttachment/read.do',
        data: {
          taskOid: oid
        },
        success: function (data) {
          for (var i = 0; i < data.length; i++) {

            $('#attachmentRead').append(
                    '<ul class="inbox-download-list col-md-12">' +
                    '<li class="col-md-12">' +
                    '<span class="col-md-6" style="width: 50px;">' +
                    '<i class="fa fa-file-text-o" style="font-size: 40px;"></i>' +
                    '</span>' +
                    '<span class="col-md-6">' +
                    '<strong>' + data[i].fileName + '</strong>' +
                    '</span>' +
                    '<span class="col-md-6">' +
                    '<a href="/' + data[i].fileUrl + '"> 下载</a>' +
                    '<a href="JavaScript:deleteAttachment(\'' + data[i].id + '\',1)"> 删除</a>' +
                    '</span>' +
                    '</li>' +
                    '</ul>'
            );
          }
        },
        // 调用出错执行的函数
        error: function () {

        }
      });


      $.ajax({
        type: "POST",
        url: '/projectTask/findExperienceByTaskOid.do',
        data: {
          taskOid: oid
        },
        success: function (data) {
          $('#experienceSize').html("心得("+data.length+")<span class='pull-right' id='experienceOpenClose' onclick='experienceOpenCloseShow()'>展开心得</span>");
          for (var i = 0; i < data.length; i++) {
            $("#Experiencelist").append(
                    "<li class='dd-item dd3-item' data-id='1'>" +
                    "<div class='dd3-content' style='background-color: white;border: 0px;'>" +
                    "<label class='input'>" +
                    "心得日期：" +
                    "<input type='text' readonly='true' name='startTime' id='startExperienceDate" + i + "' onchange='saveExperience(\"" + data[i].oid + "\"," + i + ")'   class='startExperienceDate' style='border-width: 0px 0px;width: 85px' value='" + data[i].startTime + "'>~" +
                    "<input type='text' readonly='true' name='endTime' id='endExperienceDate" + i + "' onchange='saveExperience(\"" + data[i].oid + "\"," + i + ")'  class='endExperienceDate' style='border-width: 0px 0px;width: 85px' value='" + data[i].endTime + "'>" +
                    "</label>" +
                    "<span class='pull-right'><a href='javascript:void(0);' onclick='deleteExperience(\"" + data[i].oid + "\")'>删除</a></span>" +
                    "<span class='pull-right' id='fillInPerson" + i + "'>填写人：" + data[i].fillInPerson + "&nbsp;&nbsp;</span>" +
                    "<table style='width: 96%;height: auto;'>"+
                    "<tr>" +
                    "<td>" +
                    "<section>" +
                    "<div style='background-color: white;width: 96%;margin: 10px auto;'>" +
                    "<div class='widget-body no-padding'>" +
                    "<div class='summernote7' id='experienceContent" + i + "'  onblur='saveExperience(\"" + data[i].oid + "\"," + i + ")'>" + data[i].experienceContent +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "</section>" +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td style='padding:0px 0px 0px 0px;'>" +
                    "<div class='inbox-download col-md-12' style='border-width: 0px 0px;' id='attachmentRead2" + i + "'>" +
                    "</div>" +
                    "</td>" +
                    "</tr>" +
                    "</table>" +
                    "</div>" +
                    "</li>"
            );
            $.ajax({
              type: "GET",
              url: '/taskAttachment/read.do',
              data: {
                attachmentReadFlag: i,
                taskOid: oid,
                experienceOid: data[i].oid
              },
              success: function (data) {

                for (var k = 0; k < data.length; k++) {
                  $('#attachmentRead2' + data[0].attachmentReadFlag).append(
                          '<ul class="inbox-download-list col-md-12">' +
                          '<li class="col-md-12">' +
                          '<span class="col-md-6" style="width: 50px;">' +
                          '<i class="fa fa-file-text-o" style="font-size: 40px;"></i>' +
                          '</span>' +
                          '<span class="col-md-6">' +
                          '<strong>' + data[k].fileName + '</strong>' +
                          '</span>' +
                          '<span class="col-md-6">' +
                          '<a href="/' + data[k].fileUrl + '"> 下载</a>' +
                          '<a href="JavaScript:deleteAttachment(\'' + data[k].id + '\',3)"> 删除</a>' +
                          '</span>' +
                          '</li>' +
                          '</ul>'
                  );
                }
              },
              // 调用出错执行的函数
              error: function () {

              }
            });
          }
          $('.startExperienceDate').datepicker({
            dateFormat: 'yy-mm-dd',
            showOtherMonths: true,
            changeMonth: true,
            changeYear: true,
            prevText: '<i class="fa fa-chevron-left"></i>',
            nextText: '<i class="fa fa-chevron-right"></i>',
            onClose: function (selectedDate) {
              $('.endExperienceDate').datepicker('option', 'minDate', selectedDate);
            }
          });

          $('.endExperienceDate').datepicker({
            dateFormat: 'yy-mm-dd',
            showOtherMonths: true,
            changeMonth: true,
            changeYear: true,
            yearRange: '-0:+5',
            prevText: '<i class="fa fa-chevron-left"></i>',
            nextText: '<i class="fa fa-chevron-right"></i>',
            onClose: function (selectedDate) {
              $('.startExperienceDate').datepicker('option', 'maxDate', selectedDate);
            }
          });
          $('.summernote7').summernote({
            height: 180,
            focus: false,
            tabsize: 2,
            airMode: true
          });
        },
        // 调用出错执行的函数
        error: function () {

        }
      });
      $.ajax({
        type: "POST",
        url: '/projectTask/findWorkHourByTaskOid.do',
        data: {
          taskOid: oid
        },
        success: function (data) {
          $('#workHourSize').html("工时("+data.length+")<span class='pull-right' id='workHourOpenClose' onclick='workHourOpenCloseShow()'>展开工时</span>");
          if (data.length == 0) {
            $('#workHourCollapse').hide();
          } else {
            $('#workHourCollapse').show();
          }
          for (var i = 0; i < data.length; i++) {
            var foid = data[i].oid + "," + i;
            workHourSum += data[i].workHour;
            $("#workhourlist").append(
                    "<li class='dd-item dd3-item' data-id='1'>" +
                    "<div class='dd3-content row' style='background-color: white;border: 0;'>" +
                    "<input type='text' class='input-Small col-md-7'  name='workHourName'  id='workHourName" + i + "' onblur='saveWorkHour(\"" + data[i].oid + "\"," + i + ")' style='border-width: 0px 0px;' placeholder='工作内容' value='" + data[i].workHourName + "'>" +
                    "<span>" +
                    "</span>" +
                    "<input type='text'  readonly='true' name='workhourDate' id='workhourDate" + i + "' onchange='saveWorkHour(\"" + data[i].oid + "\"," + i + ")'   class='workhourDate' style='border-width: 0px 0px;width: 100px' value='" + data[i].workHourDate + "'>" +
                    "<span>" +
                    "<a class='btn btn-warning btn-xs' rel='popover' data-placement='bottom'  data-html='true'    href='javascript:void(0);' id='workHour" + i + "' onclick='loadPopoverList(\"" + data[i].oid + "\"," + i + ")'>" +
                    "<i class='fa fa-clock-o'></i>&nbsp;&nbsp;" + data[i].workHour + "h" +
                    "</a>" +
                    "&nbsp;&nbsp;" +
                    "</span>" +
                    "<span ><a class='accordion-toggle' href='#collapse" + i + "' data-toggle='collapse'>详情&nbsp;&nbsp;</a></span>" +
                    "<span ><a href='javascript:void(0);' onclick='deleteWorkHour(\"" + data[i].oid + "\")'>删除</a></span>" +
                    "<table style='width: 96%;border: 1px dashed #a9a9a9;height: auto;' class='collapse' id='collapse" + i + "'>" +
                    "<tr>" +
                    "<td style='width: 90px;'>" +
                    "工时备注：" +
                    "</td>" +
                    "<td>" +
                    "<section>" +
                    "<div style='background-color: white;width: 85%;margin: 25px; auto;'>" +
                    "<div class='widget-body no-padding'>" +
                    "<div class='summernote5' id='workhourBz" + i + "' onblur='saveWorkHour(\"" + data[i].oid + "\"," + i + ")'>" + data[i].workHourContent +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "</section>" +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td style='width: 15%;'>" +
                    "附件：" +
                    "</td>" +
                    "<td style='padding:0px 0px 10px 0px;'>" +
                    "<div class='col-md-6'>" +
                    "<input style='width: 250px;' class='form-control fileinput' name='fileToUpload' id='fileToUpload6" + i + "' type='file' multiple='multiple'  onchange='fileSelected(6," + i + ");'>" +
                    "</div>" +
                    "<div class='col-md-5' style='padding: 0 0 0 10px;'>&nbsp;" +
                    "<a id='uploadFile6" + i + "'  class='uploadFile6 easyui-linkbutton' data-options='iconCls:\"icon-save\"' onclick='uploadFile(\"" + foid + "\");'>开始上传</a>" +
                    "</div>" +
                    "<div class='col-md-6' id='fileName6" + i + "'></div>" +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td style='width: 15%;'>" +
                    "</td>" +
                    "<td style='padding:0px 0px 10px 0px;'>" +
                    "<div class='inbox-download col-md-12' style='border-width: 0px 0px;' id='attachmentRead" + i + "'>" +
                    "</div>" +
                    "</td>" +
                    "</tr>" +
                    "</table>" +
                    "</div>" +
                    "</li>"
            );
            $.ajax({
              type: "GET",
              url: '/taskAttachment/read.do',
              data: {
                attachmentReadFlag: i,
                taskOid: oid,
                workHourOid: data[i].oid
              },
              success: function (data) {

                for (var k = 0; k < data.length; k++) {
                  $('#attachmentRead' + data[0].attachmentReadFlag).append(
                          '<ul class="inbox-download-list col-md-12">' +
                          '<li class="col-md-12">' +
                          '<span class="col-md-6" style="width: 50px;">' +
                          '<i class="fa fa-file-text-o" style="font-size: 40px;"></i>' +
                          '</span>' +
                          '<span class="col-md-6">' +
                          '<strong>' + data[k].fileName + '</strong>' +
                          '</span>' +
                          '<span class="col-md-6">' +
                          '<a href="/' + data[k].fileUrl + '"> 下载</a>' +
                          '<a href="JavaScript:deleteAttachment(\'' + data[k].id + '\',2)"> 删除</a>' +
                          '</span>' +
                          '</li>' +
                          '</ul>'
                  );
                }
              },
              // 调用出错执行的函数
              error: function () {

              }
            });
          }
          $(".uploadFile6").hide();
          $('#workHourSum').html("<i class='fa fa-clock-o'></i>&nbsp;&nbsp;" + workHourSum + "h");
          $('.workhourDate').datepicker({
            dateFormat: 'yy-mm-dd',
            showOtherMonths: true,
            changeMonth: true,
            changeYear: true,
            prevText: '<i class="fa fa-chevron-left"></i>',
            nextText: '<i class="fa fa-chevron-right"></i>',
            beforeShow: function () {
              $('.workhourDate').datepicker('option', 'maxDate', new Date());
            }
          });
          $('.summernote5').summernote({
            height: 180,
            focus: false,
            tabsize: 2,
            airMode: true
          });
        },
        // 调用出错执行的函数
        error: function () {
        }
      });
      $.ajax({
        type: "POST",
        url: '/projectTask/findByOid.do',
        data: {
          oid: oid
        },
        success: function (data) {
          $('#statusid').removeClass();
          if (data.taskStatus == 0) {
            $('#statusid').addClass("fa fa-square-o");
            $('#statusid').text("未开始");
          }
          if (data.taskStatus == 1) {
            $('#statusid').addClass("fa fa-check-square-o");
            $('#statusid').text("已完成");
          }
          if (data.taskStatus == 2) {
            $('#statusid').addClass("fa fa-spinner");
            $('#statusid').text("进行中");
          }
          if (data.taskStatus == 3) {
            $('#statusid').addClass("fa fa-times-circle-o");
            $('#statusid').text("已取消");
          }
          if (data.taskStatus == 4) {
            $('#statusid').addClass("fa fa-clock-o");
            $('#statusid').text("已延迟");
          }
          if (data.taskStatus == 5) {
            $('#statusid').addClass("fa fa-pause");
            $('#statusid').text("暂停中");
          }
          $('.summernote2').code(data.taskContent);
          $('#responsiblePersonOid2').editable('setValue', data.responsiblePersonOid, true);
          $('#responsiblePersonOid2').text(data.responsiblePersonName);
          $('#startTime2').val(data.startTime);
          $('#endTime2').val(data.endTime);
          if(data.approvalPerson == "" || data.approvalPerson ==null){
            $('#receiverLabel2').text("无");
          }else{
            $('#receiverLabel2').text(data.approvalPerson);
          }

          $('#taskName2').val(data.taskName);
          $('#taskId3').val(data.taskOidFlag);
          $('#workHourOid').val(data.workHourOidFlag);
          $('#experienceOid').val(data.experienceOidFlag);
          $.ajax({
            type: "POST",
            url: '/projectTask/findTaskByOid.do',
            data: {
              oid: oid
            },
            success: function (data) {

              $('#taskSize').html("任务("+data.length+")<span class='pull-right' id='taskOpenClose' onclick='taskOpenCloseShow()'>展开任务</span>");
              if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                  var status = data[i].taskStatus;

                  if (status == 0) {
                    statusAppend = "<i class='fa fa-square-o'></i>";
                  }
                  if (status == 1) {
                    statusAppend = "<i class='fa fa-check-square-o'></i>";
                  }
                  if (status == 2) {
                    statusAppend = "<i class='fa fa-spinner'></i>";
                  }
                  if (status == 3) {
                    statusAppend = "<i class='fa fa-times-circle-o'></i>";
                  }
                  if (status == 4) {
                    statusAppend = "<i class='fa fa-clock-o'></i>";
                  }
                  if (status == 5) {
                    statusAppend = "<i class='fa fa-pause'></i>";
                  }

                  $("#taskListTest").append(
                          "<li class='dd-item dd3-item' data-id='1'>" +
                          "<div class='dd3-content' style='background-color: white;border: 0px;'>" +
                          "<span class='semi-bold taskOidFlag'>" +
                          "<a href='javascript:void(0);' id='" + data[i].oid + i + "' class='btn btn-primary btn-xs' rel='popover' data-placement='bottom'  data-html='true' onclick='loadPopoverStatus(\"" + data[i].oid + i + "\")'>" +
                          statusAppend +
                          "</a>" +
                          "</span>" +
                          "<span><a href='javascript:void(0);' onclick='showTaskList(\"" + data[i].oid + "\")'>" + data[i].taskName + "</a></span>" +
                          "<span class='pull-right'><a href='javascript:void(0);' onclick='deleteTask(\"" + data[i].oid + "\")'>删除</a></span>" +
                          "<span class='pull-right'>" + data[i].responsiblePersonName + "&nbsp;&nbsp;</span>" +
                          "<span class='pull-right'>" + data[i].endTime + "&nbsp;&nbsp;</span>" +
                          "</div>" +
                          "</li>"
                  );
                }
              }
            },
            // 调用出错执行的函数
            error: function () {
            }
          });
        },
        // 调用出错执行的函数
        error: function () {
        }
      });
    }
  }
  function showModal(){
    swal({
      title: '<h4 class="modal-title"><p>选择审批人</p></h4>',
      text: '<div class="modal-body no-padding"><div class="custom-scroll table-responsive" style="height:250px; overflow-y: auto;"><div id="organTree" style="text-align: left;"></div></div></div>',
      html: true,
      allowOutsideClick:true
            },
      function(){
        //不加这段代码，在点击其他弹出框的时候会导致organTree里的内容显示，
        // 加了之后消除organTree里的内容时弹出框正常显示
        swal("","","success");
      });
    loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
    loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTreeswal1);
    function renderTreeswal1() {
      $.ajax({
        type: "GET",
        url: '/organization/queryAlltree.do',
        data: {},
        success: function (data) {
          $('#organTree').treeview({
            data: data,
            onNodeSelected: function (event, data) {
              if(data.icon=="glyphicon glyphicon-tower"){
                return;
              }else{
                $('#receiverLabel').val(data.text);
                var personId=data.href;
              }
            }
          });
        },
        // 调用出错执行的函数
        error: function () {
        }
      });
    }
  }
  function showModal2(){
    swal({
              title: '<h4 class="modal-title"><p>选择审批人</p></h4>',
              text: '<div class="modal-body no-padding"><div class="custom-scroll table-responsive" style="height:250px; overflow-y: auto;"><div id="organTree2" style="text-align: left;"></div></div></div>',
              html: true,
              allowOutsideClick:true
            },
            function(){
              //不加这段代码，在点击其他弹出框的时候会导致organTree里的内容显示，
              // 加了之后消除organTree里的内容时弹出框正常显示
              swal("","","success");
            });
    loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
    loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTreeswal2);
    function renderTreeswal2() {
      $.ajax({
        type: "GET",
        url: '/organization/queryAlltree.do',
        data: {},
        success: function (data) {
          $('#organTree2').treeview({
            data: data,
            onNodeSelected: function (event, data) {
              if(data.icon=="glyphicon glyphicon-tower"){
                return;
              }else{
                $('#receiverLabel2').text(data.text);
                var personId=data.href;
              }
            }
          });
        },
        // 调用出错执行的函数
        error: function () {
        }
      });
    }
  }
  function showModal3(){
    swal({
              title: '<h4 class="modal-title"><p>选择审批人</p></h4>',
              text: '<div class="modal-body no-padding"><div class="custom-scroll table-responsive" style="height:250px; overflow-y: auto;"><div id="organTree3" style="text-align: left;"></div></div></div>',
              html: true,
              allowOutsideClick:true
            },
            function(){
              //不加这段代码，在点击其他弹出框的时候会导致organTree里的内容显示，
              // 加了之后消除organTree里的内容时弹出框正常显示
              swal("","","success");
            });
    loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
    loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTreeswal3);
    function renderTreeswal3() {
      $.ajax({
        type: "GET",
        url: '/organization/queryAlltree.do',
        data: {},
        success: function (data) {
          $('#organTree3').treeview({
            data: data,
            onNodeSelected: function (event, data) {
              if(data.icon=="glyphicon glyphicon-tower"){
                return;
              }else{
                $('#receiverLabel3').text(data.text);
                var personId=data.href;
              }
            }
          });
        },
        // 调用出错执行的函数
        error: function () {
        }
      });
    }
  }
  function postTopic(){
    var oid = $("#taskId1").val();
    var projectOid = $("#projectOid").val();
    var taskContent = $('.summernote').code();
    var responsiblePersonOid = $('#responsiblePersonOid').editable('getValue', true);
    var responsiblePersonName = $('#responsiblePersonOid').text();
    var startTime = $('#startTime').val();
    var endTime = $('#endTime').val();
    var approvalPerson = $('#receiverLabel').val();
    var taskName = $('#taskName').val();
    if(taskName.replace( /^\s*/, '') == "" || taskName == null){
      swal("","请输入任务名称","warning");
      return;
    }
    if(taskName.length>50){
      swal("","任务名称字数不能超过50","warning");
      return;
    }

    if(startTime == "" || startTime == null){
      swal("","请输入开始时间","warning");
      return;
    }

    if(endTime == "" || endTime == null){
      swal("","请输入结束时间","warning");
      return;
    }

    if(responsiblePersonName == "" || responsiblePersonName == null || responsiblePersonName == "无"){
      swal("","请输入负责人","warning");
      return;
    }

    if(taskName.length>50){
      swal("","任务名称不能超过50个字符","warning");
      return;
    }

    if(taskContent.length>500){
      swal("","任务内容不能超过500个字符","warning");
      return;
    }

    $.ajax({
      type: "POST",
      url: '/projectTask/save.do',
      data:{
        oid:oid,
        taskContent:taskContent,
        projectOid:projectOid,
        startTime:startTime,
        endTime:endTime,
        taskName:taskName,
        responsiblePersonOid:responsiblePersonOid,
        responsiblePersonName:responsiblePersonName,
        approvalPerson:approvalPerson,
        flag:"1"
      },
      success: function (data) {
        if (data == "success") {
          loadURL("/projectTask/show.do?projectOid="+projectOid,$('#s2'))
        } else {
          swal("","保存失败","warning");
        }
      },
      // 调用出错执行的函数
      error: function () {
      }
    });
  }
  function postTopic2(){
    var oid = $("#oid2").val();
    var projectOid = $("#projectOid").val();
    var taskContent = $('.summernote2').code();
    var responsiblePersonOid = $('#responsiblePersonOid2').editable('getValue', true);
    var responsiblePersonName = $('#responsiblePersonOid2').text();
    var startTime = $('#startTime2').val();
    var endTime = $('#endTime2').val();
    var approvalPerson = $('#receiverLabel2').text();
    var taskName = $('#taskName2').val();
    if(taskName.replace( /^\s*/, '') == "" || taskName == null){
      swal("","请输入任务名称","warning");
      return;
    }
    if(taskName.length>50){
      swal("","任务名称字数不能超过50","warning");
      return;
    }
    if(startTime == "" || startTime == null){
      swal("","请输入开始时间","warning");
      return;
    }

    if(endTime == "" || endTime == null){
      swal("","请输入结束时间","warning");
      return;
    }

    if(responsiblePersonName == "" || responsiblePersonName == null || responsiblePersonName == "无"){
      swal("","请输入负责人","warning");
      return;
    }

    if(taskName.length>50){
      swal("","任务名称不能超过50个字符","warning");
      return;
    }

    if(taskContent.length>500){
      swal("","任务内容不能超过500个字符","warning");
      return;
    }

    $.ajax({
      type: "POST",
      url: '/projectTask/update.do',
      data:{
        oid:oid,
        taskContent:taskContent,
        projectOid:projectOid,
        startTime:startTime,
        endTime:endTime,
        taskName:taskName,
        responsiblePersonOid:responsiblePersonOid,
        responsiblePersonName:responsiblePersonName,
        approvalPerson:approvalPerson
      },
      success: function (data) {
        if (data == "success") {
          swal("","更新成功","success");
        } else {
          swal("","更新失败","warning");
        }
      },
      // 调用出错执行的函数
      error: function () {
      }
    });

  }
  function postTopic3(){
    var oid = $("#oid2").val();
    var projectOid = $("#projectOid").val();
    var taskContent = $('.summernote3').code();
    var responsiblePersonOid = $('#responsiblePersonOid3').editable('getValue', true);
    var responsiblePersonName = $('#responsiblePersonOid3').text();
    var startTime = $('#startTime3').val();
    var endTime = $('#endTime3').val();
    var approvalPerson = $('#receiverLabel3').text();
    var taskName = $('#taskName3').val();
    var taskOid = $('#taskId3').val();

    if(taskName.replace( /^\s*/, '') == "" || taskName == null){
      swal("","请输入任务名称","warning");
      return;
    }
    if(taskName.length>50){
      swal("","任务名称字数不能超过50","warning");
      return;
    }
    if(startTime == "" || startTime == null){
      swal("","请输入开始时间","warning");
      return;
    }

    if(endTime == "" || endTime == null){
      swal("","请输入结束时间","warning");
      return;
    }

    if(responsiblePersonName == "" || responsiblePersonName == null || responsiblePersonName == "无"){
      swal("","请输入负责人","warning");
      return;
    }

    if(taskName.length>50){
      swal("","任务名称不能超过50个字符","warning");
      return;
    }

    if(taskContent.length>500){
      swal("","任务内容不能超过500个字符","warning");
      return;
    }

    $.ajax({
      type: "POST",
      url: '/projectTask/save.do',
      data:{
        oid:oid,
        taskOid:taskOid,
        taskContent:taskContent,
        projectOid:projectOid,
        startTime:startTime,
        endTime:endTime,
        taskName:taskName,
        responsiblePersonOid:responsiblePersonOid,
        responsiblePersonName:responsiblePersonName,
        approvalPerson:approvalPerson
      },
      success: function (data) {
        if (data == "success") {
          $('.summernote3').code("");
          $('#responsiblePersonOid3').editable('setValue','',true);
          $('#responsiblePersonOid3').text("无");
          $('#startTime3').val("");
          $('#endTime3').val("");
          $('#receiverLabel3').text("无");
          $('#taskName3').val("");
          showTaskList(oid);
          showTaskAttachment(oid);
          showWorkHour(oid);
          showExperience(oid);
        } else {
          swal("","保存失败","warning");
        }
      },
      // 调用出错执行的函数
      error: function () {
      }
    });
  }
  function postTopic4(){
    var taskOid = $("#oid2").val();
    var workHourName = $("#workHourName").val();
    var workHourContent = $('.summernote4').code();
    var workHourDate = $('#workhourDate').val();
    var oid = $('#workHourOid').val();
    var workHour = $('#popoverSpan').text().split("小")[0];
    var workHourSum=0;

    if(workHourName.replace( /^\s*/, '') == "" || workHourName == null){
      swal("","请输入工时名称","warning");
      return;
    }
    if(workHourName.length>50){
      swal("","工时名称字数不能超过50","warning");
      return;
    }
    if(workHour == "" || workHour == null || workHour == "工时"){
      swal("","请输入工时","warning");
      return;
    }

    if(workHourName.length>50){
      swal("","工时名称字数不能超过50","warning");
      return;
    }

    if(workHourContent.length>500){
      swal("","工时内容字数不能超过50","warning");
      return;
    }

    $("#workhourlist").empty();
    $.ajax({
      type: "POST",
      url: '/projectTask/saveWorkHour.do',
      data:{
        oid:oid,
        taskOid:taskOid,
        workHourName:workHourName,
        workHourContent:workHourContent,
        workHourDate:workHourDate,
        workHour:workHour
      },
      success: function (data) {
        $("#workHourName").val("");
        $('.summernote4').code("");
        $('#workhourDate').val("");
        $('#popoverSpan').text("工时");
        saveWorkHourOidFlag = 1;
        $('#workHourOid').val(data.workHourOid);
        if (data.success == "success") {
          $.ajax({
            type: "POST",
            url: '/projectTask/findWorkHourByTaskOid.do',
            data:{
              taskOid:taskOid
            },
            success: function (data) {
              $('#workHourSize').html("工时("+data.length+")<span class='pull-right' id='workHourOpenClose' onclick='workHourOpenCloseShow()'>关闭工时</span>");
              if(data.length==0){
                $('#workHourCollapse').hide();
              }else{
                $('#workHourCollapse').show();
              }
              for(var i=0;i<data.length;i++){
                var foid = data[i].oid + ","+i;
                workHourSum +=data[i].workHour;
                $("#workhourlist").append(
                        "<li class='dd-item dd3-item' data-id='1'>"+
                        "<div class='dd3-content row' style='background-color: white;border: 0;'>"+
                        "<input type='text' class='input-Small col-md-7' name='workHourName'  id='workHourName"+i+"' onblur='saveWorkHour(\""+data[i].oid+"\","+i+")' style='border-width: 0px 0px;' placeholder='工作内容' value='"+data[i].workHourName+"'>"+
                        "<span>" +
                        "</span>"+
                        "<input type='text' readonly='true' name='workhourDate' id='workhourDate"+i+"' onchange='saveWorkHour(\""+data[i].oid+"\","+i+")'   class='workhourDate' style='border-width: 0px 0px;width: 100px' value='"+data[i].workHourDate+"'>"+
                        "<span>" +
                        "<a class='btn btn-warning btn-xs' rel='popover' data-placement='bottom'  data-html='true'    href='javascript:void(0);' id='workHour"+i+"' onclick='loadPopoverList(\""+data[i].oid+"\","+i+")'>" +
                        "<i class='fa fa-clock-o'></i>&nbsp;&nbsp;"+data[i].workHour+"h" +
                        "</a>" +
                        "&nbsp;&nbsp;" +
                        "</span>"+
                        "<span ><a class='accordion-toggle' href='#collapse"+i+"' data-toggle='collapse'>详情&nbsp;&nbsp;</a></span>"+
                        "<span ><a href='javascript:void(0);' onclick='deleteWorkHour(\""+data[i].oid+"\")'>删除</a></span>"+
                        "<table style='width: 96%;border: 1px dashed #a9a9a9;height: auto;' class='collapse' id='collapse"+i+"'>"+
                        "<tr>"+
                        "<td style='width: 90px;'>"+
                        "工时备注："+
                        "</td>"+
                        "<td>"+
                        "<section>"+
                        "<div style='background-color: white;width: 85%;margin: 25px; auto;'>"+
                        "<div class='widget-body no-padding'>"+
                        "<div class='summernote5' id='workhourBz"+i+"' onblur='saveWorkHour(\""+data[i].oid+"\","+i+")'>"+data[i].workHourContent+
                        "</div>"+
                        "</div>"+
                        "</div>"+
                        "</section>"+
                        "</td>"+
                        "</tr>"+
                        "<tr>"+
                        "<td style='width: 15%;'>"+
                        "附件："+
                        "</td>"+
                        "<td style='padding:0px 0px 10px 0px;'>"+
                        "<div class='col-md-6'>"+
                        "<input style='width: 250px;' class='form-control fileinput' name='fileToUpload' id='fileToUpload6"+i+"' type='file' multiple='multiple'  onchange='fileSelected(6,"+i+");'>"+
                        "</div>"+
                        "<div class='col-md-5' style='padding: 0 0 0 10px;'>&nbsp;"+
                        "<a id='uploadFile6"+i+"'  class='uploadFile6 easyui-linkbutton' data-options='iconCls:\"icon-save\"' onclick='uploadFile(\""+foid+"\");'>开始上传</a>"+
                        "</div>"+
                        "<div class='col-md-6' id='fileName6"+i+"'></div>"+
                        "</td>"+
                        "</tr>"+
                        "<tr>"+
                        "<td style='width: 15%;'>"+
                        "</td>"+
                        "<td style='padding:0px 0px 10px 0px;'>"+
                        "<div class='inbox-download col-md-12' style='border-width: 0px 0px;' id='attachmentRead"+i+"'>"+
                        "</div>"+
                        "</td>"+
                        "</tr>"+
                        "</table>"+
                        "</div>"+
                        "</li>"
                );
                $.ajax({
                  type: "GET",
                  url: '/taskAttachment/read.do',
                  data:{
                    attachmentReadFlag:i,
                    taskOid:taskOid,
                    workHourOid:data[i].oid
                  },
                  success: function (data) {
                    for(var k=0;k<data.length;k++){
                      $('#attachmentRead'+data[0].attachmentReadFlag).append(
                              '<ul class="inbox-download-list col-md-12">'+
                              '<li class="col-md-12">'+
                              '<span class="col-md-6" style="width: 50px;">'+
                              '<i class="fa fa-file-text-o" style="font-size: 40px;"></i>'+
                              '</span>'+
                              '<span class="col-md-6">'+
                              '<strong>'+data[k].fileName+'</strong>'+
                              '</span>'+
                              '<span class="col-md-6">'+
                              '<a href="/'+data[k].fileUrl+'"> 下载</a>'+
                              '<a href="JavaScript:deleteAttachment(\''+data[k].id+'\',2)"> 删除</a>'+
                              '</span>'+
                              '</li>'+
                              '</ul>'
                      );
                    }
                  },
                  // 调用出错执行的函数
                  error: function () {

                  }
                });
              }
              $(".uploadFile6").hide();
              $('#workHourSum').html("<i class='fa fa-clock-o'></i>&nbsp;&nbsp;"+workHourSum+"h");
              $('.workhourDate').datepicker({
                dateFormat : 'yy-mm-dd',
                showOtherMonths:true,
                changeMonth:true,
                changeYear:true,
                prevText : '<i class="fa fa-chevron-left"></i>',
                nextText : '<i class="fa fa-chevron-right"></i>',
                beforeShow : function() {
                  $('.workhourDate').datepicker('option', 'maxDate', new Date());
                }
              });
              $('.summernote5').summernote({
                height : 180,
                focus : false,
                tabsize : 2,
                airMode:true
              });
            },
            // 调用出错执行的函数
            error: function () {
            }
          });
        } else {
          swal("","保存失败","warning");
        }
      },
      // 调用出错执行的函数
      error: function () {
      }
    });
  }
  function postTopic5(){
    var taskOid = $("#oid2").val();
    var oid = $('#experienceOid').val();
    var startTime = $("#startTime6").val();
    var endTime = $('#endTime6').val();
    var experienceContent = $('.summernote6').code();
    if(startTime == "" || startTime ==null){
      swal("","请输入开始时间","warning");
      return;
    }
    if(endTime == "" || endTime ==null){
      swal("","请输入结束时间","warning");
      return;
    }
    if(experienceContent == "" || experienceContent ==null){
      swal("","请输入心得内容","warning");
      return;
    }

    if(experienceContent.length>500){
      swal("","心得内容不能超过500个字符","warning");
      return;
    }

    $('#Experiencelist').empty();
    $.ajax({
      type: "POST",
      url: '/projectTask/saveExperience.do',
      data:{
        oid:oid,
        taskOid:taskOid,
        startTime:startTime,
        endTime:endTime,
        experienceContent:experienceContent
      },
      success: function (data) {
        saveExperienceOidFlag = 1;
        $('#experienceOid').val(data.experienceOid);
        $("#startTime6").val("");
        $('#endTime6').val("");
        $('.summernote6').code("");
        if (data.success == "success") {
          $.ajax({
            type: "POST",
            url: '/projectTask/findExperienceByTaskOid.do',
            data:{
              taskOid:taskOid
            },
            success: function (data) {
              if(experienceOpenCloseFlag == 0){
                $('#experienceSize').html("心得("+data.length+")<span class='pull-right' id='experienceOpenClose' onclick='experienceOpenCloseShow()'>展开心得</span>");
              }else{
                $('#experienceSize').html("心得("+data.length+")<span class='pull-right' id='experienceOpenClose' onclick='experienceOpenCloseShow()'>关闭心得</span>");
              }
              for(var i=0;i<data.length;i++){
                $("#Experiencelist").append(
                        "<li class='dd-item dd3-item' data-id='1'>"+
                        "<div class='dd3-content' style='background-color: white;border: 0px;'>"+
                        "<label class='input'>"+
                        "心得日期："+
                        "<input type='text' readonly='true' name='startTime' id='startExperienceDate"+i+"' onchange='saveExperience(\""+data[i].oid+"\","+i+")'   class='startExperienceDate' style='border-width: 0px 0px;width: 85px' value='"+data[i].startTime+"'>~"+
                        "<input type='text' readonly='true' name='endTime' id='endExperienceDate"+i+"' onchange='saveExperience(\""+data[i].oid+"\","+i+")'  class='endExperienceDate' style='border-width: 0px 0px;width: 85px' value='"+data[i].endTime+"'>"+
                        "</label>"+
                        "<span class='pull-right'><a href='javascript:void(0);' onclick='deleteExperience(\""+data[i].oid+"\")'>删除</a></span>"+
                        "<span class='pull-right' id='fillInPerson"+i+"'>填写人："+data[i].fillInPerson+"&nbsp;&nbsp;</span>"+
                        "<table style='width: 96%;height: auto;'>"+
                        "<tr>"+
                        "<td>"+
                        "<section>"+
                        "<div style='background-color: white;width: 96%;margin: 10px auto;'>"+
                        "<div class='widget-body no-padding'>"+
                        "<div class='summernote7' id='experienceContent"+i+"'  onblur='saveExperience(\""+data[i].oid+"\","+i+")'>"+data[i].experienceContent+
                        "</div>"+
                        "</div>"+
                        "</div>"+
                        "</section>"+
                        "</td>"+
                        "</tr>"+
                        "<tr>"+
                        "<td style='padding:0px 0px 0px 0px;'>"+
                        "<div class='inbox-download col-md-12' style='border-width: 0px 0px;' id='attachmentRead2"+i+"'>"+
                        "</div>"+
                        "</td>"+
                        "</tr>"+
                        "</table>"+
                        "</div>"+
                        "</li>"
                );
                $.ajax({
                  type: "GET",
                  url: '/taskAttachment/read.do',
                  data:{
                    attachmentReadFlag:i,
                    taskOid:taskOid,
                    experienceOid:data[i].oid
                  },
                  success: function (data) {

                    for(var k=0;k<data.length;k++){
                      $('#attachmentRead2'+data[0].attachmentReadFlag).append(
                              '<ul class="inbox-download-list col-md-12">'+
                              '<li class="col-md-12">'+
                              '<span class="col-md-6" style="width: 50px;">'+
                              '<i class="fa fa-file-text-o" style="font-size: 40px;"></i>'+
                              '</span>'+
                              '<span class="col-md-6">'+
                              '<strong>'+data[k].fileName+'</strong>'+
                              '</span>'+
                              '<span class="col-md-6">'+
                              '<a href="/'+data[k].fileUrl+'"> 下载</a>'+
                              '<a href="JavaScript:deleteAttachment(\''+data[k].id+'\',3)"> 删除</a>'+
                              '</span>'+
                              '</li>'+
                              '</ul>'
                      );
                    }
                  },
                  // 调用出错执行的函数
                  error: function () {

                  }
                });
              }
              $('.startExperienceDate').datepicker({
                dateFormat : 'yy-mm-dd',
                showOtherMonths:true,
                changeMonth:true,
                changeYear:true,
                prevText : '<i class="fa fa-chevron-left"></i>',
                nextText : '<i class="fa fa-chevron-right"></i>',
                onClose : function(selectedDate) {
                  $('.endExperienceDate').datepicker('option', 'minDate', selectedDate);
                }
              });

              $('.endExperienceDate').datepicker({
                dateFormat : 'yy-mm-dd',
                showOtherMonths:true,
                changeMonth:true,
                changeYear:true,
                yearRange :'-0:+5',
                prevText : '<i class="fa fa-chevron-left"></i>',
                nextText : '<i class="fa fa-chevron-right"></i>',
                onClose : function(selectedDate) {
                  $('.startExperienceDate').datepicker('option', 'maxDate', selectedDate);
                }
              });
              $('.summernote7').summernote({
                height : 180,
                focus : false,
                tabsize : 2,
                airMode:true
              });
            },
            // 调用出错执行的函数
            error: function () {

            }
          });
        } else {
          swal("","更新失败","warning");
        }
      },
      // 调用出错执行的函数
      error: function () {

      }
    });
  }
  //删除心得
  function deleteExperience(oid){
    var findOid = $("#oid2").val();
    $.ajax({
      type: "POST",
      url: '/projectTask/deleteExperience.do',
      data:{
        oid:oid
      },
      success: function (data) {
        if (data == "success") {
          showExperience(findOid);
        } else {
          swal("","删除失败","warning");
        }
      },
      // 调用出错执行的函数
      error: function () {

      }
    });
  }
  //删除附件
  function deleteAttachment(oid,flag){
    var findOid = $("#oid2").val();
    $.ajax({
      type: "POST",
      url: '/taskAttachment/deleteAttachment.do',
      data:{
        id:oid
      },
      success: function (data) {
        if (data == "success") {
          if(flag==1){
            showTaskAttachment(findOid);
          }else if(flag==2){
            showWorkHour(findOid);
          }else{
            showExperience(findOid);
          }

        } else {
          swal("","删除失败","warning");
        }
      },
      // 调用出错执行的函数
      error: function () {

      }
    });
  }
  //删除工时
  function deleteWorkHour(oid){
    var findOid = $("#oid2").val();
    $.ajax({
      type: "POST",
      url: '/projectTask/deleteWorkHour.do',
      data:{
        oid:oid
      },
      success: function (data) {
        if (data == "success") {
          showWorkHour(findOid);
        } else {
          swal("","删除失败","warning");
        }
      },
      // 调用出错执行的函数
      error: function () {

      }
    });
  }
  //保存工时
  function saveWorkHour(oid,i){
    var findOid = $("#oid2").val();
    var workHourName= "#workHourName"+i;
    var workHour = "#workHour"+i;
    var workhourDate = "#workhourDate"+i;
    var workHourNameVal = $(workHourName).val();//名字
    var workHourVal = $(workHour).text();//工时
    var workhourDateVal = $(workhourDate).val();//日期
    var workhourBzVal = $('.summernote5').eq(i).code();//备注
    workHourVal = $.trim(workHourVal.substr(0, workHourVal.length-1));

    if(workHourNameVal.length>50){
      swal("","工时名称不能超过50个字符","warning");
      return;
    }

    if(workhourBzVal.length>500){
      swal("","工时内容不能超过500个字符","warning");
      return;
    }

    $.ajax({
      type: "POST",
      url: '/projectTask/updateWorkHour.do',
      data:{
        taskOid:findOid,
        oid:oid,
        workHourName:workHourNameVal,
        workHour:workHourVal,
        workHourDate:workhourDateVal,
        workHourContent:workhourBzVal
      },
      success: function (data) {
        if (data == "success") {
        } else {
          swal("","更新失败","warning");
        }
      },
      // 调用出错执行的函数
      error: function () {

      }
    });
  }
  //保存心得
  function saveExperience(oid,i){
    var findOid = $("#oid2").val();
    var startExperienceDate = "#startExperienceDate"+i;
    var endExperienceDate = "#endExperienceDate"+i;
    var fillInPerson = "#fillInPerson"+i;
    var fillInPersonVal = $.trim($(fillInPerson).text().split("：")[1]);//填写人
    var startExperienceDateVal = $(startExperienceDate).val();//开始日期
    var endExperienceDateVal = $(endExperienceDate).val();//结束日期
    var experienceContent = $('.summernote7').eq(i).code();//心得内容

    if(experienceContent.length>500){
      swal("","心得内容不能超过500个字符","warning");
      return;
    }

    $.ajax({
      type: "POST",
      url: '/projectTask/updateExperience.do',
      data:{
        taskOid:findOid,
        oid:oid,
        fillInPerson:fillInPersonVal,
        startTime:startExperienceDateVal,
        endTime:endExperienceDateVal,
        experienceContent:experienceContent
      },
      success: function (data) {
        if (data == "success") {

        } else {
          swal("","更新失败","warning");
        }
      },
      // 调用出错执行的函数
      error: function () {

      }
    });
  }
  //删除任务
  function deleteTask(deleteOid){
    var findOid = $("#oid2").val();
    $.ajax({
      type: "POST",
      url: '/projectTask/deleteTask.do',
      data:{
        oid:deleteOid
      },
      success: function (data) {
        if (data == "success") {
          showTaskList(findOid);
        } else {
          swal("","删除失败","warning");
        }
      },
      // 调用出错执行的函数
      error: function () {

      }
    });
  }
  //删除任务2
  function deleteTasks(){
    var oid = $("#oid2").val();
    var projectOid = $("#projectOid").val();
    $.ajax({
      type: "POST",
      url: '/projectTask/deleteTask.do',
      data:{
        oid:oid
      },
      success: function (data) {
        if (data == "success") {
          swal("","删除成功","success");
          loadURL("/projectTask/show.do?projectOid="+projectOid,$('#s2'));
        } else {
          swal("","删除失败","warning");
        }
      },
      // 调用出错执行的函数
      error: function () {

      }
    });
  }
  //右侧滑出的遮罩层的弹出弹入方法
  function trigger(){
    $(".toggler").trigger("click");
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var date1 = date.getDate();
    date = year+"-"+month+"-"+date1;
    $('#workhourDate').val(date);

    $('#startTime2').datepicker({
      dateFormat : 'yy-mm-dd',
      showOtherMonths:true,
      changeMonth:true,
      changeYear:true,
      prevText : '<i class="fa fa-chevron-left"></i>',
      nextText : '<i class="fa fa-chevron-right"></i>',
      onSelect : function(selectedDate) {
        $('#endTime2').datepicker('option', 'minDate', selectedDate);
      }
    });

    $('#endTime2').datepicker({
      dateFormat : 'yy-mm-dd',
      showOtherMonths:true,
      changeMonth:true,
      changeYear:true,
      yearRange :'-0:+5',
      prevText : '<i class="fa fa-chevron-left"></i>',
      nextText : '<i class="fa fa-chevron-right"></i>',
      onSelect : function(selectedDate) {
        $('#startTime2').datepicker('option', 'maxDate', selectedDate);
      }
    });

    $('#startTime3').datepicker({
      dateFormat : 'yy-mm-dd',
      showOtherMonths:true,
      changeMonth:true,
      changeYear:true,
      prevText : '<i class="fa fa-chevron-left"></i>',
      nextText : '<i class="fa fa-chevron-right"></i>',
      onSelect : function(selectedDate) {
        $('#endTime3').datepicker('option', 'minDate', selectedDate);
      }
    });

    $('#endTime3').datepicker({
      dateFormat : 'yy-mm-dd',
      showOtherMonths:true,
      changeMonth:true,
      changeYear:true,
      yearRange :'-0:+5',
      prevText : '<i class="fa fa-chevron-left"></i>',
      nextText : '<i class="fa fa-chevron-right"></i>',
      onSelect : function(selectedDate) {
        $('#startTime3').datepicker('option', 'maxDate', selectedDate);
      }
    });
    $('#workhourDate').datepicker({
      dateFormat : 'yy-mm-dd',
      showOtherMonths:true,
      changeMonth:true,
      changeYear:true,
      prevText : '<i class="fa fa-chevron-left"></i>',
      nextText : '<i class="fa fa-chevron-right"></i>',
      beforeShow : function() {
        $('#workhourDate').datepicker('option', 'maxDate', new Date());
      }
    });
    $('.workhourDate').datepicker({
      dateFormat : 'yy-mm-dd',
      showOtherMonths:true,
      changeMonth:true,
      changeYear:true,
      prevText : '<i class="fa fa-chevron-left"></i>',
      nextText : '<i class="fa fa-chevron-right"></i>',
      beforeShow : function() {
        $('.workhourDate').datepicker('option', 'maxDate', new Date());
      }
    });


    $('#startTime6').datepicker({
      dateFormat : 'yy-mm-dd',
      showOtherMonths:true,
      changeMonth:true,
      changeYear:true,
      prevText : '<i class="fa fa-chevron-left"></i>',
      nextText : '<i class="fa fa-chevron-right"></i>',
      onSelect : function(selectedDate) {
        $('#endTime6').datepicker('option', 'minDate', selectedDate);
      }
    });

    $('#endTime6').datepicker({
      dateFormat : 'yy-mm-dd',
      showOtherMonths:true,
      changeMonth:true,
      changeYear:true,
      yearRange :'-0:+5',
      prevText : '<i class="fa fa-chevron-left"></i>',
      nextText : '<i class="fa fa-chevron-right"></i>',
      onSelect : function(selectedDate) {
        $('#startTime6').datepicker('option', 'maxDate', selectedDate);
      }
    });




    $('#responsiblePersonOid2').editable({
      showbuttons: false
    });
    $('.summernote2').summernote({
      height : 90,
      focus : false,
      tabsize : 2,
      airMode:true
    });
    $('#responsiblePersonOid3').editable({
      showbuttons: false
    });
    $('.summernote3').summernote({
      height : 180,
      focus : false,
      tabsize : 2,
      airMode:true
    });
    $('.summernote4').summernote({
      height : 180,
      focus : false,
      tabsize : 2,
      airMode:true
    });
    $('.summernote6').summernote({
      height : 180,
      focus : false,
      tabsize : 2,
      airMode:true
    });
    $('.summernote7').summernote({
      height : 180,
      focus : false,
      tabsize : 2,
      airMode:true
    });
  }
  function closeTrigger() {
    $("#projectOid2").val($("#projectOid").val());
    $("#taskListTest").empty();
    $("#workhourlist").empty();
    $('#Experiencelist').empty();
    $('#attachmentRead').empty();
    openFlag=false;
    $(".toggler").trigger("click");
  }
  //遮罩层的初始化
  function triggerInit(){
    $('#taskSideBar').BootSideMenu({side:"right",autoClose:"true"});
  }
  //加载下拉页面
  function loadPopover(){
    if(popoverFlag == 0){
      $.ajax({
        type: 'GET',
        url: '/projectTask/loadPopover.do',
        dataType: 'html',
        success: function(data) {
          $('#inputTest').attr('data-content', data);
          $('#inputTest').popover('show');
        }
      });
      popoverFlag = 1;
    }else{
      return;
    }
  }
  // 任务进度状态的修改
  function loadPopoverStatus(taskOid){
    var id = "#"+taskOid;
    $.ajax({
      type: 'POST',
      url: '/projectTask/loadPopoverStatus.do',
      dataType: 'html',
      data:{
        taskOid:taskOid
      },
      success: function(data) {
        $(id).attr('data-content', data);
        $(id).popover('show');
      }
    });
  }
  function loadPopoverTaskStatus(){
    $.ajax({
      type: 'POST',
      url: '/projectTask/loadPopoverStatus.do',
      dataType: 'html',
      data:{
        taskOid:"1"
      },
      success: function(data) {
        $('#projectTaskStatus').attr('data-content', data);
        $('#projectTaskStatus').popover('show');
      }
    });
  }

  function loadPopoverList(oid,i){
    var workHour= "#workHour"+i;
    $.ajax({
      type: 'GET',
      url: '/projectTask/loadPopover.do?flag='+i+'&oid='+oid,
      dataType: 'html',
      success: function(data) {
        $(workHour).attr('data-content', data);
        $(workHour).popover('show');
      }
    });
  }
  loadScript("/smartAdmin/js/plugin/summernote/summernote.min.js", function(){
    loadScript("/smartAdmin/js/plugin/jquery-nestable/jquery.nestable.min.js", pagefunction)
  });
  //引入遮罩层所需要的jar包
  loadScript("/smartAdmin/js/BootSideMenu.js",triggerInit);
  $('#startTime').datepicker({
    dateFormat : 'yy-mm-dd',
    showOtherMonths:true,
    changeMonth:true,
    changeYear:true,
    prevText : '<i class="fa fa-chevron-left"></i>',
    nextText : '<i class="fa fa-chevron-right"></i>',
    onSelect : function(selectedDate) {
      $('#endTime').datepicker('option', 'minDate', selectedDate);
    }
  });
  $('#endTime').datepicker({
    dateFormat : 'yy-mm-dd',
    showOtherMonths:true,
    changeMonth:true,
    changeYear:true,
    yearRange :'-0:+5',
    prevText : '<i class="fa fa-chevron-left"></i>',
    nextText : '<i class="fa fa-chevron-right"></i>',
    onSelect : function(selectedDate) {
      $('#startTime').datepicker('option', 'maxDate', selectedDate);
    }
  });
</script>