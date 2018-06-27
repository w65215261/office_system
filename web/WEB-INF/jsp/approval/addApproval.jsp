<%--
  Created by IntelliJ IDEA.
  User: Ai JingXian
  Date: 2016/4/21
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
<script type="text/javascript" src="/EasyUiCompoment/photosAttachment.js"></script>
<style>
  .redText{
    color: red;
  }
  #returnDiv{
    cursor: pointer;
    margin-top: 10px;
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
</style>
<div id="returnDiv" onclick="goBack()" style="width: 50px;">
  <span class="glyphicon glyphicon-circle-arrow-left"></span>返回
</div>
<hr>
<form action="" method="post" id="login-form" class="smart-form">
  <div class="row">
    ${resHtml}
      <input type="hidden" id="templateCode" name="templateCode" value="${templateCode}">
      <input type="hidden" id="uuid" name="uuid" value="${uuid}">
  <section>
    <label class="col col-6  no-padding">
      <span class="col col-4" style="text-align: right;color: #000000;padding-top: 5px;">图片:</span>
        <span class="col col-8  no-padding">
          <input  class="form-control" name="fileToUpload" id="fileToUpload" type="file" multiple="multiple"  onchange="fileSelected();" style="height: 30px;border: 1px solid #BDBDBD;background-color: ">
          <input type="hidden" name="fileTypeId" id="fileTypeId">
        </span>
    </label>

    <label class="col col-1" style="width: 3%;padding-left: 5px;" id="uploadFile">
      <span class="no-padding">
        <button class="btn btn-sm btn-success" type="button" onclick="uploadFile()" style="background-color: #5994E7;height: 30px;">
          <i class="glyphicon glyphicon-arrow-up" ></i>
        </button>
      </span>
    </label>
    <br><br>
  <section>
    <label class="label col col-2" style="text-align: right"></label>
    <section class="col col-12  no-padding">
      <ul class="inbox-download-list col-md-12 no-padding" id="systemAttachmentId">

      </ul>
    </section>
  </section>
  </section>
  <br><br><br><br>
  <section>
    <label class="label col col-2" style="text-align: right"><span class="redText">*</span>审批人:</label>
    <section class="col col-4  no-padding">
      <label class="input">
        <input type="text" class="text"  id="approvalPersonName" name="approvalPersonName" readonly="true" onclick="checkPerson()">
        <input type="hidden" class="text"  id="approvalPersonId" name="approvalPersonId" readonly="true">
      </label>
    </section>
  </section>
  <br><br>
  <section>
    <label class="label col col-2" style="text-align: right"></label>
    <section class="col col-4  no-padding">
      <div class="form-group">
          <button type="button" class="btn btn-primary btn-lg btn-block" onclick="submitFunction()">提交</button>
      </div>
    </section>
  </section>
  </div>
</form>
<script type="text/javascript">
  pageSetUp();
  var checkNum = false;
  $("#uploadFile").hide();
  function goBack(){
    history.go(-1);
  }


  function checkPerson(){
    var approvalPersonId = "";
    var approvalPersonName = "";

    swal({
              title: '<h4 class="modal-title"><p>审批人（选两个）</p></h4>',
              text: '<div class="modal-body no-padding"><div class="custom-scroll table-responsive" style="height:250px; overflow-y: auto;"><div id="organTree" style="text-align: left;"></div></div></div>',
              html: true,
              allowOutsideClick:true
            },
            function(){
              if(approvalPersonId.split(",").length != 2){
                swal("","","success");
                checkNum = true;
                return;
              }
              $('#approvalPersonId').val(approvalPersonId);
              $('#approvalPersonName').val(approvalPersonName);
              //不加这段代码，在点击其他弹出框的时候会导致organTree里的内容显示，
              // 加了之后消除organTree里的内容时弹出框正常显示
              swal("","","success");

            });
    loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
    loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTreeSweet);
    function renderTreeSweet() {
      $.ajax({
        type: "GET",
        url: '/organization/queryAlltree.do',
        data: {},
        success: function (data) {
          $('#organTree').treeview({
            data: data,
            multiSelect:true,
            onNodeSelected: function (event, data) {
              if(data.icon=="glyphicon glyphicon-tower"){
                return;
              }else{
                var treeFlag = true;
                for(var i = 0;i<approvalPersonId.split(",").length;i++){
                  if(data.href == approvalPersonId.split(",")[i]){
                    treeFlag = false;
                  }
                }
                if(treeFlag) {
                  if (approvalPersonName == "") {
                    approvalPersonName = data.text;
                  } else {
                    approvalPersonName += "," + data.text;
                  }

                  if (approvalPersonId == "") {
                    approvalPersonId = data.href;
                  } else {
                    approvalPersonId += "," + data.href;
                  }
                }
              }

            },
            onNodeUnselected:function(event, data){

              var treeFlag = true;
              var approvalPersonIds = approvalPersonId.split(",");
              var approvalPersonNames = approvalPersonName.split(",");
              approvalPersonName ="";
              approvalPersonId = "";
              for(var i = 0;i<approvalPersonIds.length;i++){
                treeFlag = true;
                if(data.href == approvalPersonIds[i]){
                  treeFlag = false;
                }
                if(treeFlag){
                  if(approvalPersonId == ""){
                    approvalPersonId = approvalPersonIds[i];
                    approvalPersonName = approvalPersonNames[i];
                  }else{
                    approvalPersonId += "," + approvalPersonIds[i];
                    approvalPersonName += "," + approvalPersonNames[i];
                  }
                }
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

  function checkNumFunction(){
    if(checkNum){
      swal("","不是图片格式","warning");
      return false;
    }
  }

  function submitFunction(){
    var approvalPersonName = $("#approvalPersonName").val();
    var fileToUpload = $("#fileToUpload").val();
    if(fileToUpload != ""){
      swal("","附件没有上传","warning");
      return;
    }
    if(approvalPersonName == ""){
      swal("","审批人不能为空","warning");
      return;
    }
    $.ajax({
      type: "POST",
      url: '/approval/save.do',
      data: $("form").serialize(),
      success: function(data) {
        goBack();
      }
    });
  }
</script>
