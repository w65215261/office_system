<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/smartAdmin/css/BootSideMenu.css">
<link rel="stylesheet" href="/smartAdmin/css/normalize.css">
<!--  附件上传 -->
<script type="text/javascript" src="/EasyUiCompoment/planAttachment.js"></script>
<script type="text/javascript" src="/EasyUiCompoment/planAttachment1.js"></script>
<script type="text/javascript" src="/EasyUiCompoment/planAttachment2.js"></script>
<script type="text/javascript" src="/EasyUiCompoment/planAttachment3.js"></script>
<script type="text/javascript" src="/EasyUiCompoment/planAttachment4.js"></script>
<script type="text/javascript" src="/EasyUiCompoment/taskAttachment.js"></script>
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
<style>
  .popover {
    z-index   : 1060;
    height: auto;
    min-width: 200px;
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
    height: 90px;
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
  #uploadFile{
    cursor: pointer;
  }
  #uploadFile1{
    cursor: pointer;
  }
  #uploadFile2{
    cursor: pointer;
  }
  #uploadFile3{
    cursor: pointer;
  }
  #uploadFile4,#uploadFile14{
    cursor: pointer;
  }
  .fileflag{
    cursor: pointer;
  }
  #receiverLabel2{
    cursor: pointer;
  }
   #responsiblePersonOid2{
       cursor: pointer;
     }
  #responsiblePersonOid3, #popoverSpan,#receiverLabel3{
    cursor: pointer;
  }
</style>
<!-- 日期DIV-->
<div  id="one">
  <table id="user" class="table table-bordered table-striped" style="clear: both" width="900px">
    <tr>
      <td>
        <div style="width: auto;float: left; "id="divColor">
          <c:if test="${qua==''}">
            <div style='border-right:dashed #808080 1px;width: 40px;text-align: center;float: left;' ><span class='week'    style='color: #ff0000' onclick='chooseYearD()'>年度</span></div>
          </c:if>
          <c:if test="${qua!=null&&qua!=''}">
            <div style='border-right:dashed #808080 1px;width: 40px;text-align: center;float: left;' ><span class='week'    style='color: #000000' onclick='chooseYearD()'>年度</span></div>
          </c:if>
          <c:if test="${qua==1}">

          <div style='border-right:dashed #808080 1px;width: 100px;text-align: center;float: left' ><span class='week'   onclick='chooseQuarter(1)' style="color: #ff0000">第1季度</span></div>
            <div style='border-right:dashed #808080 1px;width: 100px;text-align: center;float: left' ><span class='week'   onclick='chooseQuarter(2)'>第2季度</span></div>
            <div style='border-right:dashed #808080 1px;width: 100px;text-align: center;float: left' ><span class='week'    onclick='chooseQuarter(3)'>第3季度</span></div>
            <div style='width: 100px;text-align: center;float: left' ><span class='week'   onclick='chooseQuarter(4)'>第4季度</span> </div>
          </c:if>
          <c:if test="${qua==2}">
            <div style='border-right:dashed #808080 1px;width: 100px;text-align: center;float: left' ><span class='week'   onclick='chooseQuarter(1)' >第1季度</span></div>
            <div style='border-right:dashed #808080 1px;width: 100px;text-align: center;float: left' ><span class='week'   onclick='chooseQuarter(2)'style="color: #ff0000">第2季度</span></div>
            <div style='border-right:dashed #808080 1px;width: 100px;text-align: center;float: left' ><span class='week'    onclick='chooseQuarter(3)'>第3季度</span></div>
            <div style='width: 100px;text-align: center;float: left' ><span class='week'   onclick='chooseQuarter(4)'>第4季度</span> </div>
            </c:if>
          <c:if test="${qua==3}">
            <div style='border-right:dashed #808080 1px;width: 100px;text-align: center;float: left' ><span class='week'   onclick='chooseQuarter(1)' >第1季度</span></div>
            <div style='border-right:dashed #808080 1px;width: 100px;text-align: center;float: left' ><span class='week'   onclick='chooseQuarter(2)'>第2季度</span></div>
            <div style='border-right:dashed #808080 1px;width: 100px;text-align: center;float: left' ><span class='week'    onclick='chooseQuarter(3)'style="color: #ff0000">第3季度</span></div>
            <div style='width: 100px;text-align: center;float: left' ><span class='week'   onclick='chooseQuarter(4)'>第4季度</span> </div>
          </c:if>
          <c:if test="${qua==4}">
            <div style='border-right:dashed #808080 1px;width: 100px;text-align: center;float: left' ><span class='week'   onclick='chooseQuarter(1)' >第1季度</span></div>
            <div style='border-right:dashed #808080 1px;width: 100px;text-align: center;float: left' ><span class='week'   onclick='chooseQuarter(2)'>第2季度</span></div>
            <div style='border-right:dashed #808080 1px;width: 100px;text-align: center;float: left' ><span class='week'    onclick='chooseQuarter(3)'>第3季度</span></div>
            <div style='width: 100px;text-align: center;float: left' ><span class='week'   onclick='chooseQuarter(4)' style="color: #ff0000">第4季度</span> </div>
           </c:if>
                  <input type='hidden' id='quarter' value="${qua}">
                  <input type='hidden' id='timeType' value='2'><input type='hidden' id='showFlag' value='show3'>
                  <input type='hidden' id='chooseYear' value='${year}'>
                  <input type='hidden' id='chooseFlag' value='chooseFlag0'>

          </div>
      </td>
    </tr>
  </table>
</div>
<!-- 遮罩层 div-->
<div id="taskSideBar" style="height: 100%;width:650px">
  <%--<div class="modal fade" id="receieverModal2" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">--%>
    <%--<div class="modal-dialog"  style="width:300px ;height:600px">--%>
      <%--<div class="modal-content">--%>
        <%--<div class="modal-header">--%>
          <%--<h4 class="modal-title"><p>选择审批人</p></h4>--%>
        <%--</div>--%>
        <%--<div class="modal-body no-padding">--%>
          <%--<div class="custom-scroll table-responsive" style="height:250px; overflow-y: scroll;">--%>
            <%--<div id="organTree2">--%>
            <%--</div>--%>
          <%--</div>--%>
        <%--</div>--%>
        <%--<script>--%>
          <%--loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");--%>
          <%--loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTree);--%>
          <%--function renderTree() {--%>
            <%--$.ajax({--%>
              <%--type: "GET",--%>
              <%--url: '/organization/queryAlltree.do',--%>
              <%--data: {},--%>
              <%--success: function (data) {--%>
                <%--$('#organTree2').treeview({--%>
                  <%--data: data,--%>
                  <%--onNodeSelected: function (event, data) {--%>
                    <%--if(data.icon=="glyphicon glyphicon-tower"){--%>
                      <%--return ;--%>
                    <%--}else{--%>

                      <%--$('#receiverLabel2').html(data.text);--%>
                      <%--var personId=data.href;--%>
<%--//                      $('#approvePersonName2').val(personId);--%>
                      <%--$("#receieverModal2").modal("hide");--%>
                      <%--var projectId=$("#projectOid2").val();--%>
                      <%--var taskOid=$("#oid2").val();--%>
                      <%--console.log("projectIdsssssssss"+projectId);--%>
                      <%--$.ajax({--%>
                        <%--type: "POST",--%>
                        <%--url:'/plan/updateApprovalPerson.do',--%>
                        <%--data:--%>
                        <%--{--%>
                          <%--approvalPerson:data.text,--%>
                          <%--projectOid:projectId,--%>
                          <%--oid:taskOid--%>
                        <%--},--%>
                        <%--success: function (data1) {--%>
                          <%--if(data1=="success"){}--%>
                        <%--},--%>
                        <%--error: function () {--%>

                        <%--}--%>
                      <%--});--%>
                    <%--}--%>
                  <%--}--%>
                <%--});--%>
              <%--},--%>
              <%--// 调用出错执行的函数--%>
              <%--error: function () {--%>
              <%--}--%>
            <%--});--%>
          <%--}--%>
        <%--</script>--%>
      <%--</div>--%>
    <%--</div>--%>
  <%--</div>--%>

  <%--<div class="modal fade" id="responsiblePersonModel" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">--%>
    <%--<div class="modal-dialog"  style="width:300px ;height:600px">--%>
      <%--<div class="modal-content">--%>
        <%--<div class="modal-header">--%>
          <%--<h4 class="modal-title"><p>选择负责人</p></h4>--%>
        <%--</div>--%>
        <%--<div class="modal-body no-padding">--%>
          <%--<div class="custom-scroll table-responsive" style="height:250px; overflow-y: scroll;">--%>
            <%--<div id="organTrees3">--%>
            <%--</div>--%>
          <%--</div>--%>
        <%--</div>--%>
        <%--<script>--%>
          <%--loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");--%>
          <%--loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTree);--%>
          <%--function renderTree() {--%>
            <%--$.ajax({--%>
              <%--type: "GET",--%>
              <%--url: '/organization/queryAlltree.do',--%>
              <%--data: {},--%>
              <%--success: function (data) {--%>
                <%--$('#organTrees3').treeview({--%>
                  <%--data: data,--%>
                  <%--onNodeSelected: function (event, data) {--%>
                    <%--if(data.icon=="glyphicon glyphicon-tower"){--%>
                      <%--return ;--%>
                    <%--}else{--%>

                      <%--$('#responsiblePersonOid2').html(data.text);--%>
                      <%--var personId=data.href;--%>
<%--//                      $('#approvePersonName2').val(personId);--%>
                      <%--$("#responsiblePersonModel").modal("hide");--%>
                      <%--var projectId=$("#projectOid2").val();--%>
                      <%--var taskOid=$("#oid2").val();--%>
                      <%--$.ajax({--%>
                        <%--type: "POST",--%>
                        <%--url:'/plan/updateResponsiblePerson.do',--%>
                        <%--data:--%>
                        <%--{--%>
                          <%--responsiblePersonName:data.text,--%>
                          <%--projectOid:projectId,--%>
                          <%--oid:taskOid,--%>
                          <%--responsiblePersonOid:personId--%>
                        <%--},--%>
                        <%--success: function (data1) {--%>
                          <%--if(data1=="success"){}--%>
                        <%--},--%>
                        <%--error: function () {--%>

                        <%--}--%>
                      <%--});--%>
                    <%--}--%>
                  <%--}--%>
                <%--});--%>
              <%--},--%>
              <%--// 调用出错执行的函数--%>
              <%--error: function () {--%>
              <%--}--%>
            <%--});--%>
          <%--}--%>
        <%--</script>--%>
      <%--</div>--%>
    <%--</div>--%>
  <%--</div>--%>
  <p class="alert alert-info" style="background-color: #a9a9a9">
    <span class="semi-bold">
      <a href="javascript:void(0);"  class="btn btn-xs" style="box-shadow: none;font-size: 15px;" rel="popover" data-placement="bottom"  data-html="true" id="projectTaskStatus" onclick="loadPopoverTaskStatus()"><i class="fa fa-square-o" id="statusid"></i></a>

    </span>
    <span class="pull-right">
      <a href="javascript:void(0);" class="btn btn-xs" onclick="closeTrigger()">X</a>
    </span>
    <span class="pull-right">
      <a href="javascript:void(0);" class="btn btn-xs tasksOid"><i class="fa fa-trash-o" onclick="deleteTasks()"></i></a>
    </span>



  </p>
  <!-- 遮罩层 计划详情DIV-->
  <form class="smart-form" id="createProject2">
    <fieldset>
      <section>
        <label class="input" >
          <input type="hidden" name="projectOid2" id="projectOid2"/>
          <input type="hidden" name="oid" id="oid2"/>
          <input type="text" class="input-lg" name="taskName" id="taskName2" onchange="updtaskName2()">
        </label>
        <div class="summernote2" id="summernote2" onblur="updTaskCount()">
        </div>
      </section>
      <section>
        <div class="row">
          <section class="col col-6">
            <label class="input"> <i class="icon-append fa fa-calendar"></i>
              <input type="text" name="startTime" id="startTime2" onchange="updStartTime()" placeholder="选择开始日期" readonly="true">
            </label>
          </section>
          <section class="col col-6">
            <label class="input"> <i class="icon-append fa fa-calendar"></i>
              <input type="text" name="endTime" id="endTime2" placeholder="选择结束日期" onchange="updEndTime()" readonly="true">
            </label>
          </section>
          <section class="col col-6">
            <label class="label">审批人：<span href="javascript:void(0);" style="border-bottom: dashed 1px #39b3d7;color: #3498DB" id="receiverLabel2" onclick="updapprovePersonId()" data-type="text" data-pk="1" data-original-title="审批人" data-placement="bottom"></span>
            </label>
          </section>
          <section class="col col-4">
            <label class="label">负责人：<span href="javascript:void(0);" style="border-bottom: dashed 1px #39b3d7;color: #3498DB" id="responsiblePersonOid2" onclick="updResponsiblePerson()" ></span>
            </label>
          </section>
        </div>
      </section>
      <section style="padding: 15px;">
        <input type="hidden" id="coid2"/>
        <div class="row" >
          <div class="form-group col-md-12">
            <div class="col-md-6">
              <input style="width: 300px;" class="" name="fileToUpload" id="fileToUpload1" type="file" multiple="multiple"  onchange="fileSelected1();">
            </div>
            <div class="col-md-6">
              <a id="uploadFile1"  class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="uploadFile1()">开始上传</a>
            </div>
            <div class="col-md-3" id="fileName1"></div>
            <div class="col-md-2" id="fileSize1"></div>
            <div class="col-md-2" id="fileType1"></div>
            <div class="easyui-progressbar col-md-2" id="progressNumber1"></div>
            <input type="hidden" name="fileTypeId" id="fileTypeId1">
          </div>
          <div class="inbox-download col-md-12" style="border-top: solid 1px #E1E1E1 ;" id="files1">
            <%--附件详情--%>

          </div>
        </div>
      </section>
      <section>
        <a class="btn btn-default btn-sm" href="javascript:void(0);" onclick="plan()">分解计划</a>
        <a class="btn btn-default btn-sm" href="javascript:void(0);" onclick="plan1()">添加工时</a>
        <a class="btn btn-default btn-sm" href="javascript:void(0);" onclick="plan2()">添加心得</a>
      </section>
    </fieldset>
  </form>
  <!-- 遮罩层 分解任务DIV-->
  <div class="col-sm-12" id="zzc1">
    <div class="well well-sm well-light">
      <p class="alert alert-info">
        分解计划
      </p>
      <!-- 分解任务 div-->
      <%--<div class="modal fade" id="receieverModal3" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">--%>
        <%--<div class="modal-dialog"  style="width:300px ;height:600px">--%>
          <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
              <%--<h4 class="modal-title"><p>选择审批人</p></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body no-padding">--%>
              <%--<div class="custom-scroll table-responsive" style="height:250px; overflow-y: scroll;">--%>
                <%--<div id="organTree3">--%>
                <%--</div>--%>
              <%--</div>--%>
            <%--</div>--%>
            <%--<script>--%>
              <%--loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");--%>
              <%--loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTree);--%>
              <%--function renderTree() {--%>
                <%--$.ajax({--%>
                  <%--type: "GET",--%>
                  <%--url: '/organization/queryAlltree.do',--%>
                  <%--data: {},--%>
                  <%--success: function (data) {--%>
                    <%--$('#organTree3').treeview({--%>
                      <%--data: data,--%>
                      <%--onNodeSelected: function (event, data) {--%>
                        <%--if(data.icon=="glyphicon glyphicon-tower"){--%>
                          <%--return ;--%>
                        <%--}else{--%>
                          <%--$('#receiverLabel3').html(data.text);--%>
                          <%--var personId=data.href;--%>
                          <%--$('#approvePersonName3').val(personId);--%>
                          <%--$("#receieverModal3").modal("hide");--%>
                        <%--}--%>
                      <%--}--%>
                    <%--});--%>
                  <%--},--%>
                  <%--// 调用出错执行的函数--%>
                  <%--error: function () {--%>
                  <%--}--%>
                <%--});--%>
              <%--}--%>
            <%--</script>--%>
          <%--</div>--%>
        <%--</div>--%>
      <%--</div>--%>
      <div>
        <%--<div class="modal fade" id="choosePersonMondel" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">--%>
          <%--<div class="modal-dialog"  style="width:300px ;height:600px">--%>
            <%--<div class="modal-content">--%>
              <%--<div class="modal-header">--%>
                <%--<h4 class="modal-title"><p>选择负责人</p></h4>--%>
              <%--</div>--%>
              <%--<div class="modal-body no-padding">--%>
                <%--<div class="custom-scroll table-responsive" style="height:250px; overflow-y: scroll;">--%>
                  <%--<div id="organTrees4">--%>
                  <%--</div>--%>
                <%--</div>--%>
              <%--</div>--%>
              <%--<script>--%>
                <%--loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");--%>
                <%--loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTree);--%>
                <%--function renderTree() {--%>
                  <%--$.ajax({--%>
                    <%--type: "GET",--%>
                    <%--url: '/organization/queryAlltree.do',--%>
                    <%--data: {},--%>
                    <%--success: function (data) {--%>
                      <%--$('#organTrees4').treeview({--%>
                        <%--data: data,--%>
                        <%--onNodeSelected: function (event, data) {--%>
                          <%--if(data.icon=="glyphicon glyphicon-tower"){--%>
                            <%--return ;--%>
                          <%--}else{--%>

                            <%--$('#responsiblePersonOid3').html(data.text);--%>
                            <%--$('#responsiblePersonOids3').val(data.href);--%>
                            <%--$("#choosePersonMondel").modal("hide");--%>
                          <%--}--%>
                        <%--}--%>
                      <%--});--%>
                    <%--},--%>
                    <%--// 调用出错执行的函数--%>
                    <%--error: function () {--%>
                    <%--}--%>
                  <%--});--%>
                <%--}--%>
              <%--</script>--%>
            <%--</div>--%>
          <%--</div>--%>
        <%--</div>--%>
        <div class="widget-body no-padding">
          <form class="smart-form" id="createProject3">
            <fieldset>
              <section>
                <label class="label">计划名称</label>
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
                    <label class="label">审批人:
                      <span href="javascript:void(0);" style="border-bottom: dashed 1px #39b3d7;color: #3498DB" id="receiverLabel3" onclick="showModal3()" >无</span>
                    </label>
                  </section>
                  <section class="col col-4">
                    <label class="label">负责人：<span href="javascript:void(0);" style="border-bottom: dashed 1px #39b3d7;color: #3498DB" id="responsiblePersonOid3" onclick="choosePerson()" >无</span>
                      <input type="hidden" id="responsiblePersonOids3">
                    </label>
                  </section>
                </div>
              </section>
              <section>
                <div style="background-color: white;width: 98%;margin: 0 auto;">
                  <div class="widget-body no-padding">
                    <label class="label">计划详情：</label>
                    <div class="summernote3" id="summernote3">
                    </div>
                    <section style="padding: 15px;">
                      <input type="hidden" id="coid1" value="${taskOid1}"/>
                      <div class="row" >
                        <div class="form-group">
                          <div class="col-md-6">
                            <input style="width: 300px;" class="" name="fileToUpload" id="fileToUpload2" type="file" multiple="multiple"  onchange="fileSelected2();">
                          </div>
                          <div class="col-md-6">
                            <a id="uploadFile2"  class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="uploadFile2()">开始上传</a>
                          </div>
                          <div class="col-md-4" id="fileName2"></div>
                          <div class="col-md-2" id="fileSize2"></div>
                          <div class="col-md-2" id="fileType2"></div>
                          <div class="easyui-progressbar col-md-2" id="progressNumber2"></div>
                          <input type="hidden" name="fileTypeId" id="fileTypeId2">
                        </div>
                      </div>
                      <div id="files2" style="width:100%;border-top: solid 1px #dcdcdc ;">
                      </div>
                    </section>
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
        </div>
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

      <!-- end 分解任务 div-->
    </div>
  </div>
  <!-- 遮罩层 添加工时DIV-->
  <div class="col-sm-12" id="zzc2">
    <div class="well well-sm well-light">
      <p class="alert alert-info">
        添加工时
      </p>
      <div class="widget-body no-padding">
        <form class="smart-form" id="createProject4">
          <fieldset>
            <section>
              <label class="input">
                <input type="hidden" name="projectOid" id="projectOid4" value="<%= request.getAttribute("projectOid")%>"/>
                <input type="text" class="input-lg" name="workHourName" id="workHourName" placeholder="工时名称">
              </label>
            </section>
            <section>
              <div class="row">
                <section class="col col-6">
                  <label class="input"> <i class="icon-append fa fa-calendar"></i>
                    <input type="text" name="workhourDate" id="workhourDate" placeholder="选择开始日期">
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

            <section>
              <div style="background-color: white;width: 96%;margin: 0 auto;">
                <div class="widget-body no-padding">
                  <div class="summernote4" id="summernote4">
                  </div>
                  <section style="padding: 15px;">
                    <input type="hidden" id="coid3" value="${taskOid2}"/>
                    <div class="row">
                      <div class="form-group">
                        <div class="col-md-6">
                          <input style="width: 300px;" class="" name="fileToUpload" id="fileToUpload3" type="file" multiple="multiple"  onchange="fileSelected3();">
                        </div>
                        <div class="col-md-6">
                          <a id="uploadFile3"  class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="uploadFile3()">开始上传</a>
                        </div>
                        <div class="col-md-4" id="fileName3"></div>
                        <div class="col-md-2" id="fileSize3"></div>
                        <div class="col-md-2" id="fileType3"></div>
                        <div class="easyui-progressbar col-md-2" id="progressNumber3"></div>
                        <input type="hidden" name="fileTypeId" id="fileTypeId3">
                      </div>
                    </div>
                    <div id="files3" style="width:100%;border: solid 1px #dcdcdc ;height: 141px">
                    </div>
                  </section>
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
            <section>
              <div class="col-sm-13 col-lg-13">
                <div class="col-sm-13 col-lg-13" id="nestable4">
                  <h6 id="workHourHide">${userName}的工时总计：
                    <a class='btn btn-warning btn-xs' href='javascript:void(0);' id="workHourSum"><i class='fa fa-clock-o'></i></a>
                  </h6>
                  <ol class="dd-list" id="workhourlist">
                  </ol>
                </div>
              </div>
            </section>
          </fieldset>
        </form>
      </div>
    </div>
  </div>
  <!-- 遮罩层 添加心得DIV-->
  <div class="col-sm-12" id="zzc3">
    <div class="well well-sm well-light">
      <p class="alert alert-info">
        添加心得
      </p>
    </div>
    <div class="widget-body no-padding">
      <form class="smart-form" id="createProject6" style="width: 94%;margin:0 auto">
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
          <section>
            <div style="background-color: white;width: 96%;margin: 0 auto;">
              <div class="widget-body no-padding">
                <div class="summernote6" id="summernote6">

                </div>
                <section style="padding: 15px;">
                  <input type="hidden" id="coid4" value="${taskOid3}"/>
                  <div class="row" >
                    <div class="form-group">
                      <div class="col-md-6">
                        <input style="width: 300px;" class="" name="fileToUpload" id="fileToUpload4" type="file" multiple="multiple"  onchange="fileSelected4();">
                      </div>
                      <div class="col-md-6">
                        <a id="uploadFile4"  class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="uploadFile4()">开始上传</a>
                      </div>
                      <div class="col-md-4" id="fileName4"></div>
                      <div class="col-md-2" id="fileSize4"></div>
                      <div class="col-md-2" id="fileType4"></div>
                      <div class="easyui-progressbar col-md-2" id="progressNumber4"></div>
                      <input type="hidden" name="fileTypeId" id="fileTypeId4">
                    </div>
                  </div>
                  <div id="files4" style="width:100%;border: solid 1px #dcdcdc ;height: 141px">
                  </div>
                </section>
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
    </div>
    <div >
      <div class="col-sm-13 col-lg-13">
        <div class="col-sm-13 col-lg-13" id="nestable5">
          <ol class="dd-list" id="Experiencelist">
          </ol>
        </div>
      </div>
    </div>
  </div>
  <div class="col-sm-12" id="zzc4">
    <div class="well well-sm well-light">
      <p class="alert alert-info">
        历史操作记录
      </p>
    </div>
    <div class="widget-body no-padding">
      <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
          <div class="well well-sm" style="background-color: white">
            <div class="smart-timeline">
              <ul class="smart-timeline-list" id="operation" >

              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</div>
<!-- 遮罩层 divend-->
<!-- 主界面div-->
<div class="tab-content" style="border: 1px solid #EEEEEE;">
  <%--<div class="modal fade" id="receieverModal" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">--%>
    <%--<div class="modal-dialog"  style="width:300px ;height:600px">--%>
      <%--<div class="modal-content">--%>
        <%--<div class="modal-header">--%>
          <%--<h4 class="modal-title"><p>选择审批人</p></h4>--%>
        <%--</div>--%>
        <%--<div class="modal-body no-padding">--%>
          <%--<div class="custom-scroll table-responsive" style="height:250px; overflow-y: scroll;">--%>
            <%--<div id="organTree">--%>
            <%--</div>--%>
          <%--</div>--%>
        <%--</div>--%>
        <%--<script>--%>
          <%--loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");--%>
          <%--loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTree);--%>
          <%--function renderTree() {--%>
            <%--$.ajax({--%>
              <%--type: "GET",--%>
              <%--url: '/organization/queryAlltree.do',--%>
              <%--data: {},--%>
              <%--success: function (data) {--%>
                <%--$('#organTree').treeview({--%>
                  <%--data: data,--%>
                  <%--onNodeSelected: function (event, data) {--%>
                    <%--if(data.icon=="glyphicon glyphicon-tower"){--%>
                      <%--return ;--%>
                    <%--}else{--%>
                      <%--$('#receiverLabel').val(data.text);--%>
                      <%--var personId=data.href;--%>
                      <%--$('#approvePersonIds').val(personId);--%>
                      <%--$("#receieverModal").modal("hide");--%>
                    <%--}--%>
                  <%--}--%>
                <%--});--%>
              <%--},--%>
              <%--// 调用出错执行的函数--%>
              <%--error: function () {--%>
              <%--}--%>
            <%--});--%>
          <%--}--%>
        <%--</script>--%>
      <%--</div>--%>
    <%--</div>--%>
  <%--</div>--%>
  <!-- widget div-->
  <div >
    <!-- widget content -->
    <div class="widget-body"  >
      <form class="smart-form" id="createProject">
        <fieldset>
          <section>
            <p class="input-group">
                    <span onclick="loadPopover1()" class="input-group-addon" id="inputTest1" rel="popover" data-placement="bottom" data-original-title="<h4>项目分类</h4>" data-html="true">
                      <span id="popoverSpan1">分类/项目</span> <span class="glyphicon glyphicon-chevron-down" ></span></span>

              <span class="icon-envelope"></span></span>

              <input type="text" class="form-control" id="taskName" name="taskName"
                     placeholder="请输入计划名称"/>
            </p>
          </section>
          <!--折叠控件start-->

          <div id="collapseOne" class="accordion-body collapse" style="height: 0px; ">
            <div class="accordion-inner">
              <section>
                <label class="label">起止时间</label>
                <div class="row">
                  <section class="col col-6">
                    <label class="input"> <i class="icon-append fa fa-calendar"></i>
                      <input type="text" name="startTime" id="startTime" placeholder="选择开始日期" readonly="true">
                    </label>
                  </section>
                  <section class="col col-6">
                    <label class="input"> <i class="icon-append fa fa-calendar"></i>
                      <input type="text" name="endTime" id="endTime" placeholder="选择结束日期"readonly="true">
                    </label>
                  </section>
                  <section class="col col-6">
                    <label class="input"><button type="button" class="btn btn-warning" onclick="showModal()"  id="approvePersonId1">选择</button>审批人：
                      <input type="text"    id="receiverLabel" name="approvePerson" readonly="true"><input type="hidden" id="approvePersonIds">
                    </label>
                  </section>
                  <section class="col col-6">
                    <label class="input">负责人：
                      <input type="text"    id="approvePersonId" name="approvePersonId" readonly="true" value="${userName}">
                    </label>
                  </section>
                </div>
              </section>
              <section>
                <label class="label">计划详情</label>
                <label class="textarea">
                  <textarea rows="3" class="custom-scroll"  id="taskContent" name="taskContent"></textarea>
                </label>
              </section>
              <section>
                <label class="label"><i class="fa fa-paperclip fa-lg"></i>文件/图片</label>
                <input type="hidden" id="coid14" value="${taskOid}" />
                <div class="row" style="margin-left: 10px">
                  <div class="form-group">
                    <div class="col-md-6">
                      <input style="width: 300px;" class="" name="fileToUpload" id="fileToUpload14" type="file" multiple="multiple"  onchange="fileSelected14();">
                    </div>
                    <div class="col-md-6">
                      <a id="uploadFile14"  class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="uploadFile14()">开始上传</a>
                    </div>
                    <div class="col-md-4" id="fileName14"></div>
                    <div class="col-md-2" id="fileSize14"></div>
                    <div class="col-md-2" id="fileType14"></div>
                    <div class="easyui-progressbar col-md-2" id="progressNumber14"></div>
                    <input type="hidden" name="fileTypeId" id="fileTypeId14">
                  </div>
                </div>
                <div id="files14" style="width:100%;border-top:solid 1px lightgray;border-bottom: solid 1px lightgray ">
                </div>
              </section>
            </div>
          </div>
          <div class="accordion-heading" style="height: 30px">
            <div style="width: 48%;text-align: right;float: right;z-index: 1000;color: #ff0000">
              <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                展开/关闭
              </a>
            </div>
          </div>
          <!--折叠控件end-->
          <div class="widget-footer smart-form" style="background-color: white">
            <div class="btn-group">
              <button class="btn btn-sm btn-success" type="button" id="saveProject">
                <i class="fa fa-check"></i> 发布
              </button>
            </div>
          </div>
        </fieldset>
      </form>
    </div>
    <!-- end widget content -->
    <div style="max-height: 10000px">
      <!--根据条件查找plan-->
      <div id="myTabContent1" style="min-height:240px;overflow: visible;max-height: 10000px" class="tab-content">
        <hr class="simple">
        <div id="s1">

        </div>
      </div>
      <!--查找plan结束-->
    </div>
  </div>
  <!-- end widget div -->
  <!--  附件上传 -->
</div>


<script type="text/javascript">
  var planFlag=0;
  var plan1Flag=0;
  var plan2Flag=0;
  pageSetUp();

  var pagefunction = function() {
    projectfunction();

    $("#uploadFile14").hide();
    $("#uploadFile1").hide();

    $("#files14").hide();
    $("#files1").show();
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
      height : 90,
      focus : false,
      tabsize : 2,
      airMode:true
    });

//    $('#nestable').nestable();
//    $('#nestable').nestable("collapseAll");
    $('#nestable3').nestable();
    $('#nestable3').nestable("collapseAll");

  };


  loadScript("/smartAdmin/js/plugin/summernote/summernote.min.js", function(){
    loadScript("/smartAdmin/js/plugin/jquery-nestable/jquery.nestable.min.js", pagefunction)
  });



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
                $('#approvePersonIds').val(personId);
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
              text: '<div class="modal-body no-padding"><div class="custom-scroll table-responsive" style="height:250px; overflow-y: auto;"><div id="organTree4" style="text-align: left;"></div></div></div>',
              html: true,
              allowOutsideClick:true
            },
            function(){
              //不加这段代码，在点击其他弹出框的时候会导致organTree里的内容显示，
              // 加了之后消除organTree里的内容时弹出框正常显示
              swal("","","success");
            });
    loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
    loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTreeswal4);
    function renderTreeswal4() {
      $.ajax({
        type: "GET",
        url: '/organization/queryAlltree.do',
        data: {},
        success: function (data) {
          $('#organTree4').treeview({
            data: data,
            onNodeSelected: function (event, data) {
              if(data.icon=="glyphicon glyphicon-tower"){
                return;
              }else{
                $('#receiverLabel3').html(data.text);
                var personId=data.href;
                $('#approvePersonIds').val(personId);
                $('#approvePersonName3').val(personId);
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
  function choosePerson(){
    swal({
              title: '<h4 class="modal-title"><p>选择负责人</p></h4>',
              text: '<div class="modal-body no-padding"><div class="custom-scroll table-responsive" style="height:250px; overflow-y: auto;"><div id="organTree5" style="text-align: left;"></div></div></div>',
              html: true,
              allowOutsideClick:true
            },
            function(){
              //不加这段代码，在点击其他弹出框的时候会导致organTree里的内容显示，
              // 加了之后消除organTree里的内容时弹出框正常显示
              swal("","","success");
            });
    loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
    loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTreeswal5);
    function renderTreeswal5() {
      $.ajax({
        type: "GET",
        url: '/organization/queryAlltree.do',
        data: {},
        success: function (data) {
          $('#organTree5').treeview({
            data: data,
            onNodeSelected: function (event, data) {
              if(data.icon=="glyphicon glyphicon-tower"){
                return;
              }else{
                $('#responsiblePersonOid3').html(data.text);
                $('#responsiblePersonOids3').val(data.href);
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
  var popoverFlag = 0;
  //加载下拉页面
  function loadPopover(){
    if(popoverFlag == 0){
      $.ajax({
        type: 'GET',
        url: '/plan/loadPopover.do',
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
  <!-- 新建计划-->
  $("#saveProject").click(function (){
    var projectName=$('#popoverSpan1').html();
    var taskOid=$('#coid14').val();
    var taskName=$('#taskName').val();
    var startTime=$('#startTime').val();
    var endTime=$('#endTime').val();
    var approvePerson=$('#receiverLabel').val();
    var taskContent=$('#taskContent').val();
    var approvePersonId=$('#approvePersonIds').val();
    var year=$('#chooseYear').val();
    var month=$('#month').val();
    var week=$('#cWeek').val();
    var timeType=$('#timeType').val();
    var quarter=$('#quarter').val();
    if(projectName=="分类/项目"){
      swal("","分类/项目不能为空","warning");
    }else if(taskName.replace( /^\s*/, '')==""){
      swal("","计划名称不能为空","warning");
    }else if(taskName.length>50){
      swal("","计划名称字数不能超过50","warning");
    }else if(startTime==""||endTime==""){
      swal("","起止时间不能为空","warning");
    }else if(approvePerson.replace( /^\s*/, '')==""){
      swal("","审批人不能为空","warning");
    } else{
      $.ajax({
        type: "POST",
        url: '/plan/save.do',
        data:{
          projectName:projectName,
          taskName:taskName,
          startTime:startTime,
          endTime:endTime,
          approvalPerson:approvePerson,
          taskContent:taskContent,
          year:year,
          month:month,
          week:week,
          timeType:timeType,
          quarter:quarter,
          taskOid:taskOid
        },
        success: function (data) {
          console.log("data"+data);
          if(data.success=="success"){
            $('#popoverSpan1').html("分类/项目");
            $('#taskName').val("");
            $('#startTime').val("");
            $('#endTime').val("");
            $('#taskContent').val("");
            $('#receiverLabel').val("");
            $('#coid14').val(data.taskOid);
              var qua=$("#quarter").val();
              loadURL("/plan/showPlan.do?year="+year+"&qua="+qua+"&planTimeType=2",$('#s1'));
            $('#collapseOne').collapse("hide");
            $('#files14').empty();
            $('#files14').hide();
          }
        },
        error: function () {

        }
      });
    }



  });

  <!-- 初始加载-->
  function projectfunction() {
    var year = $("#year").val();
    var showFlag=$("#showFlag").val();
    var qua=$("#quarter").val();
    loadURL("/plan/showPlan.do?year="+year+"&planTimeType=2&qua="+qua,$('#s1'));
  }



  //工时加载下拉页面
  var flags=0;
  function loadPopover1(){
    if(flags==0){
      $.ajax({
        type: 'GET',
        url: '/plan/showProjectName.do',
        dataType: 'html',
        success: function(data) {
          $('#inputTest1').attr('data-content', data);
          $('#inputTest1').popover('show');
        }
      });
      flags=1
    }else{
      return;
    }
  }
  <!-- 添加心得-->
  <!-- 用于在主界面点开计划详情方法-->
  function decompositionTask(oid){
    if(closeOpenflag1 == 1){
      closeOpenflag1= 0;
    }else{
      closeOpenflag1= 1;
    }
    planFlag=0;
    plan1Flag=0;
    plan2Flag=0;
    $('#oid2').val(oid);
    $("#projectOid2").val($("#projectOid").val());
    $("#taskListTest").empty();
    $("#workhourlist").empty();
    $('#Experiencelist').empty();
    $('#attachmentRead').empty();
    $('#files1').empty();
    $("#operation").html("");
    $("#zzc1").hide();
    $("#zzc2").hide();
    $("#zzc3").hide();
    $("#uploadFile1").hide();
    $("#uploadFile2").hide();
    $("#uploadFile3").hide();
    $("#uploadFile4").hide();
    $("#files2").hide();
    $("#files3").hide();
    $("#files4").hide();
    var workHourSum=0;
    trigger(oid);
    <!--查找操作记录-->
    $.ajax({
      type: "POST",
      url: '/plan/findOperationRecoadPlan.do',
      data: {
        taskOid: oid
      },
      success: function (data)
      {
        if (data.length > 0)
        {
          for (var i = 0; i < data.length; i++)
          {
            if (data[i].distinguish == "1")
            {
              console.log(data[i].distinguish);
              $("#operation").append(
                      '<div>' + data[i].modifyDate + '</div>' +
                      '<li style="padding: 0;">' +
                      '<div class="smart-timeline-time">' +
                      '<small>' + data[i].modifyTime + '</small>' +
                      '</div>' +
                      '<div class="smart-timeline-content">' +
                      '<p>' +
                      '<i>' + data[i].operationPersonName + '&nbsp;</i><i>将计划名称&nbsp;&nbsp;&nbsp;</i><strong>'+data[i].oldContent+'</strong><i>&nbsp;&nbsp;&nbsp;修改为&nbsp;&nbsp;&nbsp;</i><strong>'+data[i].newContent+'</strong></p></div></li>'
              );
            } else if (data[i].distinguish == "2")
            {
              console.log(data[i].distinguish);
              $("#operation").append(
                      '<div>' + data[i].modifyDate + '</div>' +
                      '<li style="padding: 0;">' +
                      '<div class="smart-timeline-time">' +
                      '<small>' + data[i].modifyTime + '</small>' +
                      '</div>' +
                      '<div class="smart-timeline-content">' +
                      '<p>' +
                      '<i>' + data[i].operationPersonName + '&nbsp;</i><strong>修改了计划详情</strong><i>&nbsp;&nbsp;&nbsp;</p></div></li>'
              );
            }else if (data[i].distinguish == "3")
            {
              console.log(data[i].distinguish);
              $("#operation").append(
                      '<div>' + data[i].modifyDate + '</div>' +
                      '<li style="padding: 0;">' +
                      '<div class="smart-timeline-time">' +
                      '<small>' + data[i].modifyTime + '</small>' +
                      '</div>' +
                      '<div class="smart-timeline-content">' +
                      '<p>' +
                      '<i>' + data[i].operationPersonName + '&nbsp;</i><strong>设置了开始时间</strong><i>&nbsp;&nbsp;&nbsp;'+data[i].newContent+'</p></div></li>'
              );
            }  else if (data[i].distinguish == "4")
            {
              console.log(data[i].distinguish);
              $("#operation").append(
                      '<div>' + data[i].modifyDate + '</div>' +
                      '<li style="padding: 0;">' +
                      '<div class="smart-timeline-time">' +
                      '<small>' + data[i].modifyTime + '</small>' +
                      '</div>' +
                      '<div class="smart-timeline-content">' +
                      '<p>' +
                      '<i>' + data[i].operationPersonName + '&nbsp;</i><strong>设置了结束时间</strong><i>&nbsp;&nbsp;&nbsp;'+data[i].newContent+'</p></div></li>'
              );
            } else if (data[i].distinguish == "5")
            {
              console.log(data[i].distinguish);
              $("#operation").append(
                      '<div>' + data[i].modifyDate + '</div>' +
                      '<li style="padding: 0;">' +
                      '<div class="smart-timeline-time">' +
                      '<small>' + data[i].modifyTime + '</small>' +
                      '</div>' +
                      '<div class="smart-timeline-content">' +
                      '<p>' +
                      '<i>' + data[i].operationPersonName + '&nbsp;</i><strong>分解了新的计划</strong><i>&nbsp;&nbsp;&nbsp;'+data[i].newContent+'</p></div></li>'
              );
            }  else if (data[i].distinguish == "8")
            {
              console.log(data[i].distinguish);
              $("#operation").append(
                      '<div>' + data[i].modifyDate + '</div>' +
                      '<li style="padding: 0;">' +
                      '<div class="smart-timeline-time">' +
                      '<small>' + data[i].modifyTime + '</small>' +
                      '</div>' +
                      '<div class="smart-timeline-content">' +
                      '<p>' +
                      '<i>' + data[i].operationPersonName + '&nbsp;</i><i>将审批人&nbsp;&nbsp;&nbsp;</i><strong>'+data[i].oldContent+'</strong><i>&nbsp;&nbsp;&nbsp;修改为&nbsp;&nbsp;&nbsp;</i><strong>'+data[i].newContent+'</strong></p></div></li>'
              );
            } else if (data[i].distinguish == "7")
            {
              console.log(data[i].distinguish);
              $("#operation").append(
                      '<div>' + data[i].modifyDate + '</div>' +
                      '<li style="padding: 0;">' +
                      '<div class="smart-timeline-time">' +
                      '<small>' + data[i].modifyTime + '</small>' +
                      '</div>' +
                      '<div class="smart-timeline-content">' +
                      '<p>' +
                      '<i>' + data[i].operationPersonName + '&nbsp;</i><strong>创建了计划</strong></p></div></li>'
              );
            }else if (data[i].distinguish == "9")
            {
              console.log(data[i].distinguish);
              $("#operation").append(
                      '<div>' + data[i].modifyDate + '</div>' +
                      '<li style="padding: 0;">' +
                      '<div class="smart-timeline-time">' +
                      '<small>' + data[i].modifyTime + '</small>' +
                      '</div>' +
                      '<div class="smart-timeline-content">' +
                      '<p>' +
                      '<i>' + data[i].operationPersonName + '&nbsp;</i><i>将负责人&nbsp;&nbsp;&nbsp;</i><strong>'+data[i].oldContent+'</strong><i>&nbsp;&nbsp;&nbsp;修改为&nbsp;&nbsp;&nbsp;</i><strong>'+data[i].newContent+'</strong></p></div></li>'
              );
            }else if (data[i].distinguish == "10")
            {
              console.log(data[i].distinguish);
              $("#operation").append(
                      '<div>' + data[i].modifyDate + '</div>' +
                      '<li style="padding: 0;">' +
                      '<div class="smart-timeline-time">' +
                      '<small>' + data[i].modifyTime + '</small>' +
                      '</div>' +
                      '<div class="smart-timeline-content">' +
                      '<p>' +
                      '<i>' + data[i].operationPersonName + '&nbsp;</i><strong>'+data[i].newContent+'</strong></p></div></li>'
              );
            }
          }
        }
      }
    })


    $.ajax({
      type: "POST",
      url: '/plan/findAttachmentByTaskOid.do',
      data:{
        taskOid:oid
      },
      success: function (data) {
        console.log("data"+data.length);
        if(data.length>0){
          for(var i=0;i<data.length;i++){
            $("#files1").append(
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
                    '<a href="JavaScript:deleteAttachment0(\''+data[i].id+'\')"> 删除</a>'+
                    '</span>'+
                    '</li>'+
                    '</ul>'
            );

          }
        }
      },
      // 调用出错执行的函数
      error: function () {
      }
    });
    <!--查找心得-->
    $.ajax({
      type: "POST",
      url: '/projectTask/findExperienceByTaskOid.do',
      data:{
        taskOid:oid
      },
      success: function (data) {

        for(var i=0;i<data.length;i++){
          $("#Experiencelist").append(
                  "<li class='dd-item dd3-item' data-id='1'>"+
                  "<div class='dd3-content' style='background-color: white;border: 0px;'>"+
                  "<label class='input'>"+
                  "心得日期："+
                  "<input type='text' name='startTime' readonly='true' id='startExperienceDate"+i+"' onchange='saveExperience(\""+data[i].oid+"\","+i+")'   class='startExperienceDate' style='border-width: 0px 0px;width: 100px' value='"+data[i].startTime+"'>~"+
                  "<input type='text' name='endTime' readonly='true' id='endExperienceDate"+i+"' onchange='saveExperience(\""+data[i].oid+"\","+i+")'  class='endExperienceDate' style='border-width: 0px 0px;width: 100px' value='"+data[i].endTime+"'>"+
                  "</label>"+
                  "<span class='pull-right'><a href='javascript:void(0);' onclick='deleteExperience(\""+data[i].oid+"\")'>删除</a></span>"+
                  "<span class='pull-right' id='fillInPerson"+i+"'>填写人："+data[i].fillInPerson+"&nbsp;&nbsp;</span>"+
                  "<table style='width: 96%;height: 150px;'>"+
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
                  "<td style='padding:0px 0px 10px 0px;'>"+
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
                        '<a href="JavaScript:deleteAttachment(\''+data[k].id+'\')"> 删除</a>'+
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
    <!--查找工时-->
    $.ajax({
      type: "POST",
      url: '/projectTask/findWorkHourByTaskOid.do',
      data:{
        taskOid:oid
      },
      success: function (data) {
        if(data.length>0) {
          $("#workHourHide").show();
        }else{
          $("#workHourHide").hide();
        }
        for(var i=0;i<data.length;i++){
          var foid = data[i].oid + ","+i;
          workHourSum +=data[i].workHour;
          $("#workhourlist").append(
                  "<li class='dd-item dd3-item' data-id='1'>" +
                  "<div class='dd3-content row' style='background-color: white;border: 0;'>" +
                  "<input type='text' class='input-Small col-md-6'  name='workHourName'  id='workHourName" + i + "' onblur='saveWorkHour(\"" + data[i].oid + "\"," + i + ")' style='border-width: 0px 0px;' placeholder='工作内容' value='" + data[i].workHourName + "'>" +
                  "<span>" +
                  "</span>" +
                  "<input type='text' name='workhourDate' id='workhourDate" + i + "' onchange='saveWorkHour(\"" + data[i].oid + "\"," + i + ")'   class='workhourDate' style='border-width: 0px 0px;width: 100px' value='" + data[i].workHourDate + "'>" +
                  "<span>" +
                  "<a class='btn btn-warning btn-xs' rel='popover' data-placement='bottom'  data-html='true'    href='javascript:void(0);' id='workHour" + i + "' onclick='loadPopoverList(\"" + data[i].oid + "\"," + i + ")'>" +
                  "<i class='fa fa-clock-o'></i>&nbsp;&nbsp;" + data[i].workHour + "h" +
                  "</a>" +
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
                  "<div class='col-md-7'>" +
                  "<input style='width: 300px;' class='' name='fileToUpload' id='fileToUpload6" + i + "' type='file' multiple='multiple'  onchange='fileSelected(6," + i + ");'>" +
                  "</div>" +
                  "<div class='col-md-3' style='padding: 0 0 0 10px;'>&nbsp;" +
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
          )
          $.ajax({
            type: "GET",
            url: '/taskAttachment/read.do',
            data:{
              attachmentReadFlag:i,
              taskOid:oid,
              workHourOid:data[i].oid
            },
            success: function (data) {
              console.log("xxxxxxxxxxxxxxxxxxxx");
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
                        '<a href="JavaScript:deleteAttachment(\''+data[k].id+'\')"> 删除</a>'+
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
    <!--查找子任务-->
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
        $('.summernote2').code(data.taskContent);
//        $('#responsiblePersonOid2').editable('setValue',data.responsiblePersonOid,true);
        $('#responsiblePersonOid2').text(data.responsiblePersonName);
        $('#projectOid2').val(data.projectOid);
        $('#startTime2').val(data.startTime);
        $('#endTime2').val(data.endTime);
        $('#receiverLabel2').html(data.approvalPerson);
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
                        "<span><a href='javascript:void(0);' onclick='decompositionTask2(\""+data[i].oid+"\")'>"+data[i].taskName+"</a></span>"+
                        "<span class='pull-right'><a href='javascript:void(0);' onclick='deleteTask(\""+data[i].oid+"\")'>删除</a></span>"+
                        "<span class='pull-right'>"+data[i].responsiblePersonName+"&nbsp;&nbsp;</span>"+
                        "<span class='pull-right'>"+data[i].endTime+"&nbsp;&nbsp;</span>"+
                        "</div>"+
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
  <!-- 用于在遮罩层里点开计划详情所调用的方法-->
  function decompositionTask2(oid){
    closeOpenflag1 = 1;
    $('#oid2').val(oid);
    $("#taskListTest").empty();
    $("#workhourlist").empty();
    $('#Experiencelist').empty();
    $('#attachmentRead').empty();
    $("#files1").empty();
    $("#operation").html("");
    $.ajax({
      type: "POST",
      url: '/plan/findOperationRecoadPlan.do',
      data: {
        taskOid: oid
      },
      success: function (data)
      {
        if (data.length > 0)
        {
          for (var i = 0; i < data.length; i++)
          {
            if (data[i].distinguish == "1")
            {
              console.log(data[i].distinguish);
              $("#operation").append(
                      '<div>' + data[i].modifyDate + '</div>' +
                      '<li style="padding: 0;">' +
                      '<div class="smart-timeline-time">' +
                      '<small>' + data[i].modifyTime + '</small>' +
                      '</div>' +
                      '<div class="smart-timeline-content">' +
                      '<p>' +
                      '<i>' + data[i].operationPersonName + '&nbsp;</i><i>将计划名称&nbsp;&nbsp;&nbsp;</i><strong>'+data[i].oldContent+'</strong><i>&nbsp;&nbsp;&nbsp;修改为&nbsp;&nbsp;&nbsp;</i><strong>'+data[i].newContent+'</strong></p></div></li>'
              );
            } else if (data[i].distinguish == "2")
            {
              console.log(data[i].distinguish);
              $("#operation").append(
                      '<div>' + data[i].modifyDate + '</div>' +
                      '<li style="padding: 0;">' +
                      '<div class="smart-timeline-time">' +
                      '<small>' + data[i].modifyTime + '</small>' +
                      '</div>' +
                      '<div class="smart-timeline-content">' +
                      '<p>' +
                      '<i>' + data[i].operationPersonName + '&nbsp;</i><strong>修改了计划详情</strong><i>&nbsp;&nbsp;&nbsp;</p></div></li>'
              );
            }else if (data[i].distinguish == "3")
            {
              console.log(data[i].distinguish);
              $("#operation").append(
                      '<div>' + data[i].modifyDate + '</div>' +
                      '<li style="padding: 0;">' +
                      '<div class="smart-timeline-time">' +
                      '<small>' + data[i].modifyTime + '</small>' +
                      '</div>' +
                      '<div class="smart-timeline-content">' +
                      '<p>' +
                      '<i>' + data[i].operationPersonName + '&nbsp;</i><strong>设置了开始时间</strong><i>&nbsp;&nbsp;&nbsp;'+data[i].newContent+'</p></div></li>'
              );
            }  else if (data[i].distinguish == "4")
            {
              console.log(data[i].distinguish);
              $("#operation").append(
                      '<div>' + data[i].modifyDate + '</div>' +
                      '<li style="padding: 0;">' +
                      '<div class="smart-timeline-time">' +
                      '<small>' + data[i].modifyTime + '</small>' +
                      '</div>' +
                      '<div class="smart-timeline-content">' +
                      '<p>' +
                      '<i>' + data[i].operationPersonName + '&nbsp;</i><strong>设置了结束时间</strong><i>&nbsp;&nbsp;&nbsp;'+data[i].newContent+'</p></div></li>'
              );
            } else if (data[i].distinguish == "5")
            {
              console.log(data[i].distinguish);
              $("#operation").append(
                      '<div>' + data[i].modifyDate + '</div>' +
                      '<li style="padding: 0;">' +
                      '<div class="smart-timeline-time">' +
                      '<small>' + data[i].modifyTime + '</small>' +
                      '</div>' +
                      '<div class="smart-timeline-content">' +
                      '<p>' +
                      '<i>' + data[i].operationPersonName + '&nbsp;</i><strong>分解了新的计划</strong><i>&nbsp;&nbsp;&nbsp;'+data[i].newContent+'</p></div></li>'
              );
            }  else if (data[i].distinguish == "8")
            {
              console.log(data[i].distinguish);
              $("#operation").append(
                      '<div>' + data[i].modifyDate + '</div>' +
                      '<li style="padding: 0;">' +
                      '<div class="smart-timeline-time">' +
                      '<small>' + data[i].modifyTime + '</small>' +
                      '</div>' +
                      '<div class="smart-timeline-content">' +
                      '<p>' +
                      '<i>' + data[i].operationPersonName + '&nbsp;</i><i>将审批人&nbsp;&nbsp;&nbsp;</i><strong>'+data[i].oldContent+'</strong><i>&nbsp;&nbsp;&nbsp;修改为&nbsp;&nbsp;&nbsp;</i><strong>'+data[i].newContent+'</strong></p></div></li>'
              );
            } else if (data[i].distinguish == "7")
            {
              console.log(data[i].distinguish);
              $("#operation").append(
                      '<div>' + data[i].modifyDate + '</div>' +
                      '<li style="padding: 0;">' +
                      '<div class="smart-timeline-time">' +
                      '<small>' + data[i].modifyTime + '</small>' +
                      '</div>' +
                      '<div class="smart-timeline-content">' +
                      '<p>' +
                      '<i>' + data[i].operationPersonName + '&nbsp;</i><strong>创建了计划</strong></p></div></li>'
              );
            }else if (data[i].distinguish == "9")
            {
              console.log(data[i].distinguish);
              $("#operation").append(
                      '<div>' + data[i].modifyDate + '</div>' +
                      '<li style="padding: 0;">' +
                      '<div class="smart-timeline-time">' +
                      '<small>' + data[i].modifyTime + '</small>' +
                      '</div>' +
                      '<div class="smart-timeline-content">' +
                      '<p>' +
                      '<i>' + data[i].operationPersonName + '&nbsp;</i><i>将负责人&nbsp;&nbsp;&nbsp;</i><strong>'+data[i].oldContent+'</strong><i>&nbsp;&nbsp;&nbsp;修改为&nbsp;&nbsp;&nbsp;</i><strong>'+data[i].newContent+'</strong></p></div></li>'
              );
            }else if (data[i].distinguish == "10")
            {
              console.log(data[i].distinguish);
              $("#operation").append(
                      '<div>' + data[i].modifyDate + '</div>' +
                      '<li style="padding: 0;">' +
                      '<div class="smart-timeline-time">' +
                      '<small>' + data[i].modifyTime + '</small>' +
                      '</div>' +
                      '<div class="smart-timeline-content">' +
                      '<p>' +
                      '<i>' + data[i].operationPersonName + '&nbsp;&nbsp;</i><strong>'+data[i].newContent+'</strong></p></div></li>'
              );
            }
          }
        }
      }
    })
    $.ajax({
      type: "POST",
      url: '/plan/findAttachmentByTaskOid.do',
      data:{
        taskOid:oid
      },
      success: function (data) {
        console.log("data"+data.length);
        if(data.length>0){
          for(var i=0;i<data.length;i++){
            $("#files1").append(
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
                    '<a href="JavaScript:deleteAttachment0(\''+data[i].id+'\')"> 删除</a>'+
                    '</span>'+
                    '</li>'+
                    '</ul>'
            );

          }
        }
      },
      // 调用出错执行的函数
      error: function () {
      }
    });


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
                  '<a href="JavaScript:deleteAttachment(\''+data[i].id+'\')"> 删除</a>'+
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

    $.ajax({
      type: "POST",
      url: '/projectTask/findExperienceByTaskOid.do',
      data:{
        taskOid:oid
      },
      success: function (data) {
        for(var i=0;i<data.length;i++){
          $("#Experiencelist").append(
                  "<li class='dd-item dd3-item' data-id='1'>"+
                  "<div class='dd3-content' style='background-color: white;border: 0px;'>"+
                  "<label class='input'>"+
                  "心得日期："+
                  "<input type='text' name='startTime' readonly='true' id='startExperienceDate"+i+"' onchange='saveExperience(\""+data[i].oid+"\","+i+")'   class='startExperienceDate' style='border-width: 0px 0px;width: 100px' value='"+data[i].startTime+"'>~"+
                  "<input type='text' name='endTime' readonly='true' id='endExperienceDate"+i+"' onchange='saveExperience(\""+data[i].oid+"\","+i+")'  class='endExperienceDate' style='border-width: 0px 0px;width: 100px' value='"+data[i].endTime+"'>"+
                  "</label>"+
                  "<span class='pull-right'><a href='javascript:void(0);' onclick='deleteExperience(\""+data[i].oid+"\")'>删除</a></span>"+
                  "<span class='pull-right' id='fillInPerson"+i+"'>填写人："+data[i].fillInPerson+"&nbsp;&nbsp;</span>"+
                  "<table style='width: 96%;height: 150px;'>"+
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
                  "<td style='padding:0px 0px 10px 0px;'>"+
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
                        '<a href="JavaScript:deleteAttachment(\''+data[k].id+'\')"> 删除</a>'+
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



    var workHourSum=0;
    $.ajax({
      type: "POST",
      url: '/projectTask/findWorkHourByTaskOid.do',
      data:{
        taskOid:oid
      },
      success: function (data) {
        if(data.length>0){
          $("#workHourHide").show();
        }else{
          $("#workHourHide").hide();
        }
        for(var i=0;i<data.length;i++){
          var foid = data[i].oid + ","+i;
          workHourSum +=data[i].workHour;
          $("#workhourlist").append(
                  "<li class='dd-item dd3-item' data-id='1'>" +
                  "<div class='dd3-content row' style='background-color: white;border: 0;'>" +
                  "<input type='text' class='input-Small col-md-6'  name='workHourName'  id='workHourName" + i + "' onblur='saveWorkHour(\"" + data[i].oid + "\"," + i + ")' style='border-width: 0px 0px;' placeholder='工作内容' value='" + data[i].workHourName + "'>" +
                  "<span>" +
                  "</span>" +
                  "<input type='text' name='workhourDate' id='workhourDate" + i + "' onchange='saveWorkHour(\"" + data[i].oid + "\"," + i + ")'   class='workhourDate' style='border-width: 0px 0px;width: 100px' value='" + data[i].workHourDate + "'>" +
                  "<span>" +
                  "<a class='btn btn-warning btn-xs' rel='popover' data-placement='bottom'  data-html='true'    href='javascript:void(0);' id='workHour" + i + "' onclick='loadPopoverList(\"" + data[i].oid + "\"," + i + ")'>" +
                  "<i class='fa fa-clock-o'></i>&nbsp;&nbsp;" + data[i].workHour + "h" +
                  "</a>" +
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
                  "<div class='col-md-7'>" +
                  "<input style='width: 300px;' class='' name='fileToUpload' id='fileToUpload6" + i + "' type='file' multiple='multiple'  onchange='fileSelected(6," + i + ");'>" +
                  "</div>" +
                  "<div class='col-md-3' style='padding: 0 0 0 10px;'>&nbsp;" +
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
                        '<a href="JavaScript:deleteAttachment(\''+data[k].id+'\')"> 删除</a>'+
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
//        $('#responsiblePersonOid2').editable('setValue',data.responsiblePersonOid,true);
        $('#responsiblePersonOid2').text(data.responsiblePersonName);
        $('#projectOid2').val(data.projectOid);
        $('#startTime2').val(data.startTime);
        $('#endTime2').val(data.endTime);
        $('#receiverLabel2').html(data.approvalPerson);
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
                        "<span><a href='javascript:void(0);' onclick='decompositionTask2(\""+data[i].oid+"\")'>"+data[i].taskName+"</a></span>"+
                        "<span class='pull-right'><a href='javascript:void(0);' onclick='deleteTask(\""+data[i].oid+"\")'>删除</a></span>"+
                        "<span class='pull-right'>"+data[i].responsiblePersonName+"&nbsp;&nbsp;</span>"+
                        "<span class='pull-right'>"+data[i].endTime+"&nbsp;&nbsp;</span>"+
                        "</div>"+
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
  loadScript("/smartAdmin/js/plugin/summernote/summernote.min.js", function(){
    loadScript("/smartAdmin/js/plugin/jquery-nestable/jquery.nestable.min.js", pagefunction)
  });



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
  //引入遮罩层所需要的jar包
  loadScript("/smartAdmin/js/BootSideMenu.js",triggerInit);
  //遮罩层的初始化
  function triggerInit(){
    $('#taskSideBar').BootSideMenu({side:"right",autoClose:"true"});
  }
  //右侧滑出的遮罩层的弹出弹入方法
  function trigger(oid){
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
      onClose : function(selectedDate) {
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
      onClose : function(selectedDate) {
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
    $('#summernote6').summernote({
      height : 180,
      focus : false,
      tabsize : 2,
      airMode:true
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

//    $('#responsiblePersonOid2').editable({
//      showbuttons: false
//    });
    $('.summernote2').summernote({
      height : 90,
      focus : false,
      tabsize : 2,
      airMode:true
    });
//    $('#responsiblePersonOid3').editable({
//    });
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
  }
  <!-- 遮罩层分解任务-->
  function postTopic3(){
    var oid = $("#oid2").val();
    var coid=$("#coid1").val();
    var projectOid = $("#projectOid2").val();
    var taskContent = $('.summernote3').code();
    var responsiblePersonOid = $('#responsiblePersonOids3').val();
    var responsiblePersonName = $('#responsiblePersonOid3').text();
    var startTime = $('#startTime3').val();
    var endTime = $('#endTime3').val();
    var approvalPerson = $('#receiverLabel3').text();
    var taskName = $('#taskName3').val();
    var taskOid = $('#taskId3').val();
    console.log(responsiblePersonName);
    if(taskName.replace( /^\s*/, '')!="") {
      if(taskName.length<=50) {
      if (startTime != "" && startTime != null&&endTime!=""&&endTime!=null) {
        if(approvalPerson!="无"){
          if(responsiblePersonName!="无"){
            $.ajax({
              type: "POST",
              url: '/plan/savePlan.do',
              data: {
                oid: oid,
                coid: coid,
                taskOid: taskOid,
                taskContent: taskContent,
                projectOid: projectOid,
                startTime: startTime,
                endTime: endTime,
                taskName: taskName,
                responsiblePersonOid: responsiblePersonOid,
                responsiblePersonName: responsiblePersonName,
                approvalPerson: approvalPerson
              },
              success: function (data) {
                if (data.success == "success") {
//              swal("","发布成功!","success");
                  $("#files2").empty();
                  $('#coid1').val(data.projectPlanOid);
                  decompositionTask2(oid);
                  $("#taskName3").val("");
                  $("#startTime3").val("");
                  $("#endTime3").val("");
                  $("#approvePersonId3").val("");
                  $("#responsiblePersonOid3").html("无");
                  $("#receiverLabel3").html("无");
                  $('.summernote3').code("");
                } else {
                  swal("","保存失败!","warning");
                }
              },
              // 调用出错执行的函数
              error: function () {
              }
            });
          }else{swal("","负责人不能为空","warning");}
        }else{
          swal("","审批人不能为空","warning");}
      } else {
        swal("","开始时间和结束时间不能为空","warning");}
    }else {
        swal("","计划名称字数不能超过50","warning");}
    }else{
      swal("","计划名称不能为空","warning");
      return;
    }
  }
  <!-- 遮罩层添加工时-->
  var saveWorkHourOidFlag = 0;
  function postTopic4(){
    var taskOid = $("#oid2").val();
    var workHourName = $("#workHourName").val();
    var workHourContent = $('.summernote4').code();
    var workHourDate = $('#workhourDate').val();
    var oid = $('#coid3').val();
    var workHour = $('#popoverSpan').text().split("小")[0];
    console.log(workHour+"workHour");
    var workHourSum=0;
    $("#workhourlist").empty();

    if(workHourName.replace( /^\s*/, '')!=""){
      if(workHourName.length<=50){
      if(workHour!="工时") {
        console.log(workHour + "工时");

        $.ajax({
          type: "POST",
          url: '/plan/saveWorkHour.do',
          data: {
            oid: oid,
            taskOid: taskOid,
            workHourName: workHourName,
            workHourContent: workHourContent,
            workHourDate: workHourDate,
            workHour: workHour
          },
          success: function (data) {
            saveWorkHourOidFlag = 1;
            console.log("data" + data.workHourOid);
            $('#coid3').val(data.workHourOid);
            $('#files3').empty();
            $('#files3').hide();
            $('.summernote4').code("");
            $("#workHourName").val("");
            if (data.success == "success") {
//              swal("","新增工时成功!","success");
              $.ajax({
                type: "POST",
                url: '/projectTask/findWorkHourByTaskOid.do',
                data: {
                  taskOid: taskOid
                },
                success: function (data) {
                  for (var i = 0; i < data.length; i++) {
                    var foid = data[i].oid + "," + i;
                    workHourSum += data[i].workHour;
                    $("#workHourHide").show();
                    $("#workhourlist").append(
                            "<li class='dd-item dd3-item' data-id='1'>" +
                            "<div class='dd3-content row' style='background-color: white;border: 0;'>" +
                            "<input type='text' class='input-Small col-md-6'  name='workHourName'  id='workHourName" + i + "' onblur='saveWorkHour(\"" + data[i].oid + "\"," + i + ")' style='border-width: 0px 0px;' placeholder='工作内容' value='" + data[i].workHourName + "'>" +
                            "<span>" +
                            "</span>" +
                            "<input type='text' name='workhourDate' id='workhourDate" + i + "' onchange='saveWorkHour(\"" + data[i].oid + "\"," + i + ")'   class='workhourDate' style='border-width: 0px 0px;width: 100px' value='" + data[i].workHourDate + "'>" +
                            "<span>" +
                            "<a class='btn btn-warning btn-xs' rel='popover' data-placement='bottom'  data-html='true'    href='javascript:void(0);' id='workHour" + i + "' onclick='loadPopoverList(\"" + data[i].oid + "\"," + i + ")'>" +
                            "<i class='fa fa-clock-o'></i>&nbsp;&nbsp;" + data[i].workHour + "h" +
                            "</a>" +
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
                            "<div class='col-md-7'>" +
                            "<input style='width: 300px;' class='' name='fileToUpload' id='fileToUpload6" + i + "' type='file' multiple='multiple'  onchange='fileSelected(6," + i + ");'>" +
                            "</div>" +
                            "<div class='col-md-3' style='padding: 0 0 0 10px;'>&nbsp;" +
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
                        taskOid: taskOid,
                        workHourOid: data[i].oid
                      },
                      success: function (data) {
                        console.log("datagongshi "+data.length);
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
                                  '<a href="JavaScript:deleteAttachment(\'' + data[k].id + '\')"> 删除</a>' +
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
            } else {

            }
          },
          // 调用出错执行的函数
          error: function () {
          }
        });
      }else{
        swal("","请填写工时具体时间","warning");
      }
    }else{
        swal("","工时名称字数不能超过50","warning");
      }
    }else{
      swal("","请输入工时名称","warning");
      return;
    }
  }
  //删除工时
  function deleteWorkHour(oid){
    var findOid = $("#oid2").val();
    $.ajax({
      type: "POST",
      url: '/plan/deleteWorkHour.do',
      data:{
        oid:oid
      },
      success: function (data) {
        if (data == "success") {
          decompositionTask2(findOid);
        } else {
          swal("","删除失败!","warning");
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
          swal("","更新失败!","warning");
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
      url: '/plan/deleteTask.do',
      data:{
        oid:deleteOid
      },
      success: function (data) {
        if (data == "success") {
          decompositionTask2(findOid);
        } else {
          swal("","删除失败!","warning");
        }
      },
      // 调用出错执行的函数
      error: function () {

      }
    });
  }
  //遮罩层头删除计划
  function deleteTasks(){
    var oid = $("#oid2").val();
    var projectOid = $("#projectOid2").val();
    $.ajax({
      type: "POST",
      url: '/projectTask/deleteTask.do',
      data:{
        oid:oid
      },
      success: function (data) {
        if (data == "success") {
          var showFlag=$("#showFlag").val();
          var year=$('#chooseYear').val();
          var month=$('#month').val();
          var week=$('#cWeek').val();
          var timeType=$('#timeType').val();
          closeTrigger();
          if(showFlag=="show1"){
            loadURL("/plan/showPlan.do?year="+year+"&month="+month+"&week="+week,$('#s1'));
          }else if(showFlag=="show2"){
            loadURL("/plan/showPlan.do?year="+year+"&planTimeType=4",$('#s1'));
          }else if(showFlag=="show3"){
            loadURL("/plan/showPlan.do?year="+year+"&planTimeType=3",$('#s1'));
          }else if(showFlag=="show4"){
            var qua=$("#quarter").val();
            loadURL("/plan/showPlan.do?year="+year+"&qua="+qua+"&planTimeType=2",$('#s1'));
          }else if(showFlag=="show0"){
            loadURL("/plan/showPlan.do?year="+year+"&month="+month+"&planTimeType=1",$('#s1'));
          }

        } else {
        }
      },
      // 调用出错执行的函数
      error: function () {

      }
    });
  }
  function closeTrigger() {
    $(".toggler").trigger("click");
  }
  <!-- 添加心得-->
  var saveExperienceOidFlag = "0";
  function postTopic5(){
    var taskOid = $("#oid2").val();
    console.log(taskOid);
    var oid = $('#coid4').val();
    var startTime = $("#startTime6").val();
    var endTime = $('#endTime6').val();
    var experienceContent = $('.summernote6').code();
    $('#Experiencelist').empty();
    if(startTime!=""&&startTime!=null&&endTime!=null&&endTime!="") {
      if (experienceContent!=null) {
        $.ajax({
          type: "POST",
          url: '/plan/saveExperience.do',
          data: {
            saveExperienceOidFlag: saveExperienceOidFlag,
            oid: oid,
            taskOid: taskOid,
            startTime: startTime,
            endTime: endTime,
            experienceContent: experienceContent
          },
          success: function (data) {
//            swal("", "心得发布成功!", "warning");
            saveExperienceOidFlag = 1;
            if (data.coid4 != "") {
              console.log("coid4" + data.coid4);
              $('#files4').empty();
              $('#files4').hide();
              $('#coid4').val(data.coid4);
              $('#endTime6').val("");
              $('#startTime6').val("");
              $('.summernote6').code("");

            }
            if (data.success == "success") {
              console.log("success");
              $.ajax({

                type: "POST",
                url: '/projectTask/findExperienceByTaskOid.do',
                data: {
                  taskOid: taskOid
                },
                success: function (data) {
                  for (var i = 0; i < data.length; i++) {
                    $("#Experiencelist").append(
                            "<li class='dd-item dd3-item' data-id='1'>" +
                            "<div class='dd3-content' style='background-color: white;border: 0px;'>" +
                            "<label class='input'>" +
                            "心得日期：" +
                            "<input type='text' name='startTime' readonly='true' id='startExperienceDate" + i + "' onchange='saveExperience(\"" + data[i].oid + "\"," + i + ")'   class='startExperienceDate' style='border-width: 0px 0px;width: 100px' value='" + data[i].startTime + "'>~" +
                            "<input type='text' name='endTime' readonly='true' id='endExperienceDate" + i + "' onchange='saveExperience(\"" + data[i].oid + "\"," + i + ")'  class='endExperienceDate' style='border-width: 0px 0px;width: 100px' value='" + data[i].endTime + "'>" +
                            "</label>" +
                            "<span class='pull-right'><a href='javascript:void(0);' onclick='deleteExperience(\"" + data[i].oid + "\")'>删除</a></span>" +
                            "<span class='pull-right' id='fillInPerson" + i + "'>填写人：" + data[i].fillInPerson + "&nbsp;&nbsp;</span>" +
                            "<table style='width: 96%;height: 150px;'>" +
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
                            "<td style='padding:0px 0px 10px 0px;'>" +
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
                        taskOid: taskOid,
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
                                  '<a href="JavaScript:deleteAttachment(\'' + data[k].id + '\')"> 删除</a>' +
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
            } else {
              swal("", "更新失败!", "warning");

            }
          },
          // 调用出错执行的函数
          error: function () {

          }
        });
      }else{
        swal("", "心得详情不能为空!", "warning");
      }
    }else{
      swal("", "开始时间和结束时间不能为空!", "warning");
    }
  }

  //删除心得
  function deleteExperience(oid){
    var findOid = $("#oid2").val();
    $.ajax({
      type: "POST",
      url: '/plan/deleteExperience.do',
      data:{
        oid:oid
      },
      success: function (data) {
        if (data == "success") {
          decompositionTask2(findOid);
        } else {
          swal("","删除失败!","warning");
        }
      },
      // 调用出错执行的函数
      error: function () {
      }
    });
  }
  <!-- 删除附件-->
  function deleteAttachment(oid){
    var findOid = $("#oid2").val();
    $.ajax({
      type: "POST",
      url: '/plan/deleteAttachment.do',
      data:{
        id:oid
      },
      success: function (data) {
        if (data == "success") {
          decompositionTask2(findOid);
        } else {
          swal("","删除失败!","warning");
        }
      },
      // 调用出错执行的函数
      error: function () {

      }
    });
  }
  <!-- 遮罩层修改计划名称-->
  function updtaskName2(){
    var updTaskName=$("#taskName2").val();
    var projectOid=$("#projectOid2").val();
    var taskOid=$("#oid2").val();
    console.log("updTaskName"+updTaskName);

    if(updTaskName.length>50){
      swal("", "计划名称字数不能超过50,修改失败!", "warning");
    }else{

      $.ajax({
        type: "POST",
        url: '/plan/updateProjectTaskName.do',
        data: {
          oid: taskOid,
          taskName:updTaskName,
          projectOid:projectOid
        },
        success: function (data) {
        }
      })
    }
  }
  <!-- 遮罩层修改计划详情-->
  function updTaskCount(){
    console.log(11111);
    var projectOid=$("#projectOid2").val();
    var taskOid=$("#oid2").val();
    var taskContent=$(".summernote2").code();
    $.ajax({
      type: "POST",
      url: '/plan/updateProjectTaskCount.do',
      data: {
        oid: taskOid,
        taskContent:taskContent,
        projectOid:projectOid
      },
      success: function (data) {
      }
    })
  }
  <!-- 遮罩层修改开始时间-->
  function updStartTime(){
    var projectOid=$("#projectOid2").val();
    var taskOid=$("#oid2").val();
    var startTime=$("#startTime2").val();
    $.ajax({
      type: "POST",
      url: '/plan/updateProjectStartTime.do',
      data: {
        oid: taskOid,
        startTime:startTime,
        projectOid:projectOid
      },
      success: function (data) {
      }
    })
  }
  <!-- 遮罩层修改结束时间-->
  function updEndTime(){
    var projectOid=$("#projectOid2").val();
    var taskOid=$("#oid2").val();
    var endTime=$("#endTime2").val();
    $.ajax({
      type: "POST",
      url: '/plan/updateProjectEndTime.do',
      data: {
        oid: taskOid,
        endTime:endTime,
        projectOid:projectOid
      },
      success: function (data) {
      }
    })
  }
  <!-- 遮罩层修改审批人-->
  function updapprovePersonId(){
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
                $('#receiverLabel2').html(data.text);
                var personId=data.href;
                var projectId=$("#projectOid2").val();
                var taskOid=$("#oid2").val();
                console.log("projectIdsssssssss"+projectId);
                $.ajax({
                  type: "POST",
                  url:'/plan/updateApprovalPerson.do',
                  data:
                  {
                    approvalPerson:data.text,
                    projectOid:projectId,
                    oid:taskOid
                  },
                  success: function (data1) {
                    if(data1=="success"){}
                  },
                  error: function () {
                  }
                });
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
  <!-- 遮罩层修改负责人-->
  function updResponsiblePerson(){
    swal({
              title: '<h4 class="modal-title"><p>选择负责人</p></h4>',
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
                $('#responsiblePersonOid2').html(data.text);
                var personId=data.href;
                var projectId=$("#projectOid2").val();
                var taskOid=$("#oid2").val();
                $.ajax({
                  type: "POST",
                  url:'/plan/updateResponsiblePerson.do',
                  data:
                  {
                    responsiblePersonName:data.text,
                    projectOid:projectId,
                    oid:taskOid,
                    responsiblePersonOid:personId
                  },
                  success: function (data1) {
                    if(data1=="success"){}
                  },
                  error: function () {

                  }
                });
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
  <!--用于遮罩层里的分解计划展开关闭-->
  function plan(){
    console.log(planFlag+"planFlag初始的");
    if(planFlag==0){
      $("#zzc1").show();
      $("#taskName3").focus();
      planFlag=1;
      console.log(planFlag+"planFlag显示后的");
    }else{
      $("#zzc1").hide();
      planFlag=0;
      console.log(planFlag+"planFlag隐藏后的");
    }
  }
  <!--用于遮罩层里的添加工时展开关闭-->
  function plan1(){

    if(plan1Flag==0){
      $("#workHourName").focus();
      $("#zzc2").show();
      plan1Flag=1;
    }else{
      $("#zzc2").hide();
      plan1Flag=0;
    }
  }
  <!--用于遮罩层里的添加心得展开关闭-->
  function plan2(){

    if(plan2Flag==0){
      $("#summernote6").focus();
      $("#zzc3").show();
      plan2Flag=1;
    }else{
      $("#zzc3").hide();
      plan2Flag=0;
    }
  }
  <!--用于遮罩层里修改工时详情的加载-->
  function loadPopoverList(oid,i){
    var workHour= "#workHour"+i;
    $.ajax({
      type: 'GET',
      url: '/plan/loadPopover.do?flag='+i+'&oid='+oid,
      dataType: 'html',
      success: function(data) {
        $(workHour).attr('data-content', data);
        $(workHour).popover('show');
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
        $('#workHourSize').text("工时("+data.length+")");
        if(data.length==0){
          $('#workHourHide').hide();
        }else{
          $('#workHourHide').show();
        }
        for(var i=0;i<data.length;i++){
          var foid = data[i].oid + ","+i;
          workHourSum +=data[i].workHour;
          $("#workhourlist").append(
                  "<li class='dd-item dd3-item' data-id='1'>"+
                  "<div class='dd3-content row' style='background-color: white;border: 0;'>"+
                  "<input type='text' class='input-Small col-md-6' name='workHourName'  id='workHourName"+i+"' onblur='saveWorkHour(\""+data[i].oid+"\","+i+")' style='border-width: 0px 0px;' placeholder='工作内容' value='"+data[i].workHourName+"'>"+
                  "<span>" +
                  "</span>"+
                  "<input type='text' name='workhourDate' id='workhourDate"+i+"' onchange='saveWorkHour(\""+data[i].oid+"\","+i+")'   class='workhourDate' style='border-width: 0px 0px;width: 100px' value='"+data[i].workHourDate+"'>"+
                  "<span>" +
                  "<a class='btn btn-warning btn-xs' rel='popover' data-placement='bottom'  data-html='true'    href='javascript:void(0);' id='workHour"+i+"' onclick='loadPopoverList(\""+data[i].oid+"\","+i+")'>" +
                  "<i class='fa fa-clock-o'></i>&nbsp;&nbsp;"+data[i].workHour+"h" +
                  "</a>" +
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
                  "<div class='col-md-7'>"+
                  "<input class='' style='width: 300px;' name='fileToUpload' id='fileToUpload6"+i+"' type='file' multiple='multiple'  onchange='fileSelected(6,"+i+");'>"+
                  "</div>"+
                  "<div class='col-md-3' style='padding: 0 0 0 10px;'>&nbsp;"+
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
  <!--遮罩层修改计划的状态-->
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
  <!--选择年度-->
  function chooseYearD(){
    if(closeOpenflag1 == 1){
      closeOpenflag1 = 0;
    }
    $("#timeType").attr("value","3");
    $("#quarter").attr("value","");
    $("#showFlag").val("show3");
    var year=$("#year").val();
//        loadURL("/plan/showAddPlanQuater.do?year="+year+"&planTimeType=3",$('#inbox-content > .table-wrap'));
    loadURL("/plan/showAddPlanQuater.do?year="+year+"&planTimeType=3", $('#inbox-content > .table-wrap'));
  }
  function chooseQuarter(number) {
    if(closeOpenflag1 == 1){
      closeOpenflag1 = 0;
    }
    $("#quarter").val( number);
    var qua=$("#quarter").val();
    var year=$("#year").val();
    $("#showFlag").val("show4");
    loadURL("/plan/showAddPlanQuaterFlag.do?year="+year+"&qua="+qua+"&planTimeType=2",$('#inbox-content > .table-wrap'));
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
  function checkTaskName(){

    var taskName=$("#taskName").val();
    if(taskName.length>50){
      swal("", "计划名称字数不能超过50", "warning");
    }
  }
  function checkTaskName3(){
    var taskName=$("#taskName3").val();
    if(taskName.length>50){
      swal("", "计划名称字数不能超过50", "warning");
    }
  }
  function checkWorkHourName(){
    var workHourName=$("#workHourName").val();
    if(workHourName.length>50){
      swal("", "工时名称字数不能超过50", "warning");
    }
  }
</script>