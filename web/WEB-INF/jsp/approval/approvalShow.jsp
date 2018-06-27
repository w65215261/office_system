<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
<style>
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
  <div id="approvalShow">
    <div class="row" id="showForm">
      <article class="col-sm-12 col-md-12 col-lg-12">
        <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false"
             data-widget-togglebutton="false"
             data-widget-deletebutton="false" data-widget-fullscreenbutton="false"
             data-widget-colorbutton="false"  data-widget-custombutton="true" data-widget-sortable="false">
          <header>
            <h2 id="myApproval"></h2>
            <div class="widget-toolbar" id="detailsButtonShow">
              <input type="button" id="detailsButton" class="btn  btn-success btn-xs " value="详情">
            </div>
          </header>
          <div>
            <div class="widget-body no-padding">

              <form action="" method="post" id="login-form" class="smart-form">
                <input type="hidden" id="queryFlag" name="queryFlag">
                <fieldset>
                  <div class="row">
                    <section>
                      <label class="label col col-1" style="width: 12%">开始时间：</label>
                      <section class="col col-4   no-padding">
                        <label class="input"> <i class="icon-append fa fa-calendar"></i>
                          <input type="text" name="startDate" id="startDate" readonly="true">
                        </label>
                      </section>
                      <label class="label col col-1" style="width: 12%">结束时间：</label>
                      <section class="col col-4   no-padding">
                        <label class="input"> <i class="icon-append fa fa-calendar"></i>
                          <input type="text" name="endDate" id="endDate" readonly="true">
                        </label>
                      </section>
                    </section>
                    <section>
                      <label class="label col col-1" style="width: 12%">审批类型：</label>
                      <section class="col col-4  no-padding">
                        <label class="select">
                          <select class="input-sm" id="approvalType"  name="approvalType">

                          </select><i class="icon-append fa fa-lock"></i>
                        </label>
                      </section>
                      <span  id="applicantShow">
                      <label class="label col col-1" style="width: 12%">申请人：</label>
                      <section class="col col-4  no-padding">
                        <label class="input">
                          <input type="text" class="text"  id="applicantName" name="applicantName" readonly="true" onclick="showModal()">
                          <input type="hidden" class="text"  id="applicantId" name="applicantId" readonly="true">
                        </label>
                      </section>
                      </span>
                      <span  id="approvalStatusShow">
                      <label class="label  col col-1" style="width: 12%">审批状态：</label>
                      <section class="col col-4  no-padding">
                        <label class="select">
                          <select class="input-sm" id="approvalStatus"  name="approvalStatus">
                            <option value=''></option>
                            <option value='2'>已拒绝</option>
                            <option value='1'>已同意</option>
                            <option value='3'>进行中</option>
                          </select> <i class="icon-append fa fa-lock"></i>
                        </label>
                      </section>
                      </span>
                    </section>

                    <section>

                    </section>
                  </div>
                  <footer style="background-color: white">
                    <button type="button" onclick="queryApproveData();" class="btn btn-primary">
                      检索
                    </button>

                  </footer>
                </fieldset>
              </form>

              <div id="home">

              </div>


            </div>
            <!-- end widget content -->

          </div>
          <!-- end widget div -->

        </div>
        <!-- end widget -->
      </article>
      <!-- WIDGET END -->
    </div>
  </div>
  <script type="text/javascript">
    pageSetUp();
    $.datepicker.regional["zh-CN"] = { closeText: "关闭", prevText: "&#x3c;上月", nextText: "下月&#x3e;", currentText: "今天", monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"], monthNamesShort: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"], dayNames: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"], dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"], dayNamesMin: ["日", "一", "二", "三", "四", "五", "六"], weekHeader: "周", dateFormat: "yy-mm-dd", firstDay: 1, isRTL: !1, showMonthAfterYear: !0, yearSuffix: "年" }
    $.datepicker.setDefaults($.datepicker.regional["zh-CN"]);
    $('#startDate').datepicker({
      dateFormat : 'yy-mm-dd',
      showOtherMonths:true,
      prevText : '<i class="fa fa-chevron-left"></i>',
      nextText : '<i class="fa fa-chevron-right"></i>',
      onSelect : function(selectedDate) {
        $('#endDate').datepicker('option', 'minDate', selectedDate);
      }
    });
    $('#endDate').datepicker({
      dateFormat : 'yy-mm-dd',
      showOtherMonths:true,
      prevText : '<i class="fa fa-chevron-left"></i>',
      nextText : '<i class="fa fa-chevron-right"></i>',
      onSelect : function(selectedDate) {
        $('#startDate').datepicker('option', 'maxDate', selectedDate);
      }
    });


    function approvalTypeFunction(){
      $("#queryFlag").val("4");
      $.ajax({
        type: "GET",
        url: '/approval/queryApprovalType.do',
        data:{
        },
        success: function (data) {
          if(data.length!=0){
            $("#approvalType").empty();
            $("#approvalType").append('<option value=""></option>');
            for(var i = 0;i<data.length;i++){
              $("#approvalType").append('<option value="'+data[i].templateCode+'">'+data[i].templateName+'</option>');
            }
          }
        },
        error: function () {

        }
      });
    }

    approvalTypeFunction();

    function queryApproveData(){
      var approvalType =$("#approvalType").val();
      if(approvalType == ""){
        swal("必须选择审批类型");
        return;
      }
      $('#home').empty();
      $('#detailsButtonShow').empty();
      $("#myApproval").text("");
      $('#detailsButtonShow').append('<input type="button" id="detailsButton" class="btn  btn-success btn-xs " value="详情">');
      loadURL("/approval/chooseApprovalTypeGoHtml.do?approvalType="+approvalType,$('#home'));
    }

    var applicantId;
    var applicantName;

    function showModal(){
      swal({
                title: '<h4 class="modal-title"><p>选择审批人</p></h4>',
                text: '<div class="modal-body no-padding"><div class="custom-scroll table-responsive" style="height:250px; overflow-y: auto;"><div id="organTree" style="text-align: left;"></div></div></div>',
                html: true,
                allowOutsideClick:true
              },
              function(){
                $("#applicantId").val(applicantId);
                $("#applicantName").val(applicantName);
                //不加这段代码，在点击其他弹出框的时候会导致organTree里的内容显示，
                // 加了之后消除organTree里的内容时弹出框正常显示
                swal("","","success");
              });
      loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
      loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", applicantTree);
      function applicantTree() {
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
                  applicantName = data.text;
                  applicantId=data.href;
                }
              },
              onNodeUnselected:function(event, data){
                if(data.icon=="glyphicon glyphicon-tower"){
                  applicantName = "";
                  applicantId="";
                  return;
                }else{
                  applicantName = "";
                  applicantId="";
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

  </script>
<style>
  table.dataTable tbody tr.selected {
    background-color: #b0bed9;
  }
</style>

