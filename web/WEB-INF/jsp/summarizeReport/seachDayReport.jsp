<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
<div class="row">
    <div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false">
      <ul class="nav nav-tabs bordered" id="myTab" style="background-color: #f0efee">
        <li class="active">
          <a data-toggle="tab" href="#s1" onclick="showReport()">&nbsp;&nbsp;&nbsp;日志查询&nbsp;&nbsp;&nbsp;</a>
          <input type="hidden" id="times" value="${cDate}">
        </li>

        <li>
          <input type="hidden" value="0" id="flag">
        </li>
      </ul>
      <div class="widget-body" style="border: none">
        <div id="myTabContent" class="tab-content">
          <div class="tab-pane fade active in padding-10 no-padding-bottom" id="s1" style="min-height: 370px">
            <section id="widget-grid">
              <div class="row" >
                <div style="text-align: right">
                  <table>
                    <tr>
                      <td>部门/人员</td>
                      <td><input style="margin-left: 10px" type="text" onfocus="checkPerson()" id="workHourH2"  value="${organizationInfo.orgCname}"/></td>
                      <td>&nbsp;&nbsp;日志类型&nbsp;&nbsp;</td>
                      <td><label class="select">
                        <select class="input-sm" id="reportType"  name="reportType" >
                          <option value="0" selected="selected">日报</option>
                          <option value='1'>周报</option>
                          <option value='2'>月报</option>
                        </select>
                      </label></td>
                      <td >&nbsp;&nbsp;日期范围&nbsp;&nbsp;&nbsp;</td>
                      <td> <input type="text" name="startTime" id="startTime" placeholder="选择开始日期" readonly="true"  value="${sDate}">&nbsp;&nbsp;-&nbsp;&nbsp;
                        <input type="text" name="endTime" id="endTime" placeholder="选择结束日期" readonly="true" value="${sDate}"></td>

                      <td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" style="" value="查询" onclick="checkReport()">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" style="margin-right: 30px" value="生成报表" onclick="report()"></td>
                    </tr>
                  </table>

                </div>
                  <article class="">
                    <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false"
                         data-widget-togglebutton="false"
                         data-widget-deletebutton="false" data-widget-fullscreenbutton="false"
                         data-widget-colorbutton="false"  data-widget-custombutton="true" data-widget-sortable="false">
                      <header>
                        <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                        <h2 id="leaveShow">日志查询</h2>
                      </header>
                      <input type="hidden" id="personId">
                      <input type="hidden" id="orgId" value="${orgId}">
                      <input type="hidden" id="type" value="1">
                      <input type="hidden" id="orgCode" value="${orgCode}">
                      <div style="height: auto">
                        <div class="widget-body">
                          <table id="report_table" class="table table-striped table-bordered table-hover" width="100%">
                              <thead>
                              <tr>
                                <th width="20%">填报人</th>
                                <th width="20%">填报时间</th>
                                <th width="20%">今日完成工作</th>
                                <th width="20%">未完成工作</th>
                                <th width="20%">需协调工作</th>
                              </tr>
                              </thead>
                              <tbody>
                              </tbody>
                            </table>
                          </div>
                      </div>
                    </div>
                  </article>
              </div>
            </section>

            </div>
            <div class="tab-pane fade" id="s2">
            <div class="row no-space">
            </div>
            </div>
        </div>
      </div>
    </div>
  </div>
<script type="text/javascript">

  pageSetUp();
  var pagefunction = function () {
    $.datepicker.regional["zh-CN"] = { closeText: "关闭", prevText: "&#x3c;上月", nextText: "下月&#x3e;", currentText: "今天", monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"], monthNamesShort: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"], dayNames: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"], dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"], dayNamesMin: ["日", "一", "二", "三", "四", "五", "六"], weekHeader: "周", dateFormat: "yy-mm-dd", firstDay: 1, isRTL: !1, showMonthAfterYear: !0, yearSuffix: "年" }
    $.datepicker.setDefaults($.datepicker.regional["zh-CN"]);
    var  s=$("#times").val();
    var personId=$("#personId").val();
    var startTime=$("#startTime").val();
    var endTime=$("#endTime").val();
    var orgId=$("#orgId").val();
    var type=$("#reportType").find("option:selected").val();
    console.log(endTime);

      var table=  $('#report_table').DataTable({
        "dom": "<'dt-toolbar'<'col-xs-12 col-sm-6'>>" +
        "t" +
        "<'dt-toolbar-footer'<'col-sm-4	  hidden-xs'i><'col-xs-4 col-sm-3'l><'col-xs-6 col-sm-5'p>>",
        "bFilter": true,
        "aLengthMenu": [[10, 20, 50, 100], [10, 20, 50, 100]],
        "aaSorting": [[0, "asc"]],
        "bAutoWidth": true,
        "bLengthChange": true,
        "iDisplayLength": 10,
        "bServerSide": true,
        "bStateSave": false,
        "sAjaxSource": '/summarizeReport/queryAllReport.do?startTime='+startTime+'&personId='+personId+'&endTime='+endTime+"&orgId="+orgId+"&type="+type,//指定要从哪个URL获取数据
        "oLanguage": {                          //汉化
          "sLengthMenu": "每页 _MENU_ 条记录",
          "sZeroRecords": "没有检索到数据",
          "sInfo": "第 _START_ 到第 _END_ 条数据,共有 _TOTAL_ 条",
          "sInfoEmpty": "<span class='text-danger'>没有数据</span>",
          "sProcessing": "正在加载数据...",
          "oPaginate": {
            "sFirst": "首页",
            "sPrevious": "前页",
            "sNext": "后页",
            "sLast": "尾页"
          }
        },
        "columnDefs": [

          {"targets": 0,
            "data": "rptPersonName"
          },
          {"targets":1,
            "data": "rptDate"
          },
          {"targets": 2,
            "data": "doneWork"
          },
          {"targets": 3,
            "data": "undoneWork"
          },
          {"targets": 4,
            "data": "teamWork"
          }
        ]
      });
  };

  //系统时间
  function nowTime() {
    var time=moment(new Date()).format("YYYY-MM-DD");
    return (time);
  };
  loadScript("/smartAdmin/js/plugin/moment/moment.min.js", function () {
  });
  // 点击tab加载日报,周报,月报
  function showReport() {//日报
    $('#s2').empty();
    $('#flag').val("0");
    loadURL('/search/load.do',$('#inbox-content > .table-wrap'));
  }
  function showWeekReport() {//周报
    $('#s1').empty();
    $('#flag').val("1");
    //加载周报会使遮罩层冲突,可以试着指控#s1
    loadURL('/search/weekLoad.do', $('#s2'));
  }
  loadScript("/smartAdmin/js/plugin/datatables/jquery.dataTables.min.js", function () {
    loadScript("/smartAdmin/js/plugin/datatables/dataTables.colVis.min.js", function () {
      loadScript("/smartAdmin/js/plugin/datatables/dataTables.tableTools.min.js", function () {
        loadScript("/smartAdmin/js/plugin/datatables/dataTables.bootstrap.min.js", function () {
          loadScript("/smartAdmin/js/plugin/datatable-responsive/datatables.responsive.min.js", pagefunction)
        });
      });
    });
  });
  function checkPerson(){
    swal({
              title: '<h4 class="modal-title"><p>选择部门或人员</p></h4>',
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
                var orgId=data.href;
                $('#orgId').val(orgId);
                $('#personId').val("");
                $('#workHourH2').val(data.text);
                $('#type').val("1");
                $('#orgCode').val(data.code);
              }else{
                $('#workHourH2').val(data.text);
                var personId=data.href;
                $('#personId').val(personId);
                $('#orgId').val("");
                $('#type').val("0");
                $('#orgCode').val("");
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


  $('#startTime').datepicker({
    dateFormat : 'yy-mm-dd',
    showOtherMonths:true,
    prevText : '<i class="fa fa-chevron-left"></i>',
    nextText : '<i class="fa fa-chevron-right"></i>',
    onSelect : function(selectedDate) {
      $('#endTime').datepicker('option', 'minDate', selectedDate);
    }
  });
  $('#endTime').datepicker({
    dateFormat : 'yy-mm-dd',
    showOtherMonths:true,
    prevText : '<i class="fa fa-chevron-left"></i>',
    nextText : '<i class="fa fa-chevron-right"></i>',
    onSelect : function(selectedDate) {
      $('#startTime').datepicker('option', 'maxDate', selectedDate);
    }
  });
  function checkReport(){
    var startTime=$("#startTime").val();
    var endTime =$("#endTime").val();
    var personId=$("#personId").val();
    var orgId=$("#orgId").val();
    var type=$("#reportType").find("option:selected").val();
    console.log(startTime+"startTime");
    console.log(endTime+"endTime");
    console.log(personId+"personId");
    if(startTime==""){
      swal("","请选择开始日期!","warning");
      return;
    }
    if(personId==""&&orgId==""){
      swal("","请选择人员或者部门!","warning");
      return;
    }
//    if(type=="1"){
//      $('#report_table').DataTable().ajax.url('/search/queryAllReport.do?startTime='+startTime+'&endTime='+endTime).load();
//    }else if(type=="0"){
    $('#report_table').DataTable().ajax.url('/summarizeReport/queryAllReport.do?startTime='+startTime+'&personId='+personId+'&endTime='+endTime+"&orgId="+orgId+"&type="+type).load();
//     }
    }
  function report(){
    var startTime=$("#startTime").val();
    var endTime =$("#endTime").val();
    var personId=$("#personId").val();
    var orgCode=$("#orgCode").val();
    var type=$("#type").val();
    if(type=="1"){
    var url='/jasperjsp/ToSummarizeReport.jsp?fid=2&type=excel&startTime='+startTime+'&endTime='+endTime+'&orgCode='+orgCode;
    window.location.href=url;
    }else{
      var url='/jasperjsp/ToSummarizeReport.jsp?fid=1&type=excel&startTime='+startTime+'&endTime='+endTime+'&personId='+personId;
      window.location.href=url;
    }
  }
</script>