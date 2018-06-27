<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
<div class="row">
    <div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false">
      <ul class="nav nav-tabs bordered" id="myTab" style="background-color: #f0efee">
        <li class="active">
          <a data-toggle="tab" href="#s1" onclick="showReport()">&nbsp;&nbsp;&nbsp;日报查询&nbsp;&nbsp;&nbsp;</a>
          <input type="hidden" id="times" value="${cDate}">
        </li>
        <li>
          <a data-toggle="tab" href="#s2" onclick="showWeekReport()">&nbsp;&nbsp;&nbsp;周报查询&nbsp;&nbsp;&nbsp;</a>
        </li>
        <li>
          <input type="hidden" value="0" id="flag">
        </li>
      </ul>
      <div class="widget-body" style="border: none">
        <div id="myTabContent" class="tab-content">
          <div class="tab-pane fade active in padding-10 no-padding-bottom" id="s1">
            <section id="widget-grid">
              <div class="row">
                <%--日历--%>
                <article class="col-sm-12 col-md-12 col-lg-4">
                  <div style=""><span style="font-weight: 500"></span>&nbsp;&nbsp;&nbsp;<input type="hidden" id="time" value="${nowTime}"></div>
                  <div class="jarviswidget" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-editbutton="false">
                    <div>
                      <div class="widget-body no-padding">
                        <div class="widget-body-toolbar">
                          <div id="calendar-buttons">
                            <div class="btn-group">
                              <a href="javascript:void(0)" class="btn btn-default btn-xs" id="btn-prev"><i class="fa fa-chevron-left"></i></a>
                              <a href="javascript:void(0)" class="btn btn-default btn-xs" id="btn-next"><i class="fa fa-chevron-right"></i></a>
                            </div>
                          </div>
                        </div>
                        <div id="calendar"></div>
                      </div>
                    </div>
                  </div>
                  <div style="border: none">
                    &nbsp;&nbsp;统计：
                   <font color="#8AAD46" size="2px"><i class="fa fa-fw fa-square "></i>有填报信息</font>&nbsp;&nbsp;
                  <font color="#DB4B68" size="2px"><i class="fa fa-fw fa-square "></i>无填报信息&nbsp;&nbsp;</font>
                  </div>
                </article>
                  <article class="col-xs-12 col-sm-12 col-md-12 col-lg-8">
                    <div class="jarviswidget jarviswidget-color-grey" id="wid-id-1" data-widget-editbutton="false"
                         data-widget-deletebutton="false" data-widget-fullscreenbutton="false"
                         data-widget-colorbutton="false" data-widget-editbutton="false"  data-widget-custombutton="true">
                      <header>
                        <span class="widget-icon"> <i class="fa fa-user"></i> </span>
                        <span style="display:block;width: 94%;"><input type="hidden" id="personId"><h2 id="workHourH2" style="margin-left: 10px">所有人员</h2> <h2 id="workHourDate" style="margin-left: 10px"></h2><span style="float: right;display: block"><input style="height: 30px" type="button" onclick="checkPerson()" value="选择提交人"/><input style="height: 30px" type="button" onclick="checkAllPerson()" value="查询所有"/></span></span>
                      </header>
                      <div style="height: auto">
                        <div class="widget-body">
                          <table id="report_table" class="table table-striped table-bordered table-hover" width="100%">
                              <thead>
                              <tr>
                                <th>提交人</th>
                                <th>所属时间</th>
                                <th>内容</th>
                                <th>状态</th>
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
            <div style="background-color: #F0EFEE;text-align: center;height: 40px;line-height: 40px;border-radius: 5px">
              <font size="3px">以上只检索已填报人员日报信息</font>
            </div>
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

    var  s=$("#times").val();
    var personId=$("#personId").val();
    if(s!=null){
      var table=  $('#report_table').DataTable({
        "dom": "<'toolbar'<'col-xs-12 col-sm-6'f>>"+
        "t"+
        "<'footer'<'col-sm-4	  hidden-xs'i><'col-xs-4 col-sm-3'><'col-xs-6 col-sm-5'p>>",

        "bFilter": false,//是否启用客户端过滤功能
        "aLengthMenu": [10, 20, 50, 100],//页条数
        "aaSorting": [[ 0, "asc" ]],//指定按多列数据排序的依据
        "bAutoWidth": true,//是否自动计算表格各列宽度
        "bLengthChange": true,//开关，是否显示一个每页长度的选择条（需要分页器支持）
        "iDisplayLength": 10,//一页长度
        "bServerSide": true,
        "bStateSave": false,//开关，是否打开客户端状态记录功能。这个数据是记录在cookies中的，打开了这个记录后，即使刷新一次页面，或重新打开浏览器，之前的状态都是保存下来的
        "sAjaxSource": '/search/query.do?belongsDate='+s+'&personId='+personId+'',//指定要从哪个URL获取数据
        "oLanguage": {                          //汉化
          "sLengthMenu": "每页 _MENU_ 条记录",
          "sZeroRecords": "没有检索到数据",
          "sInfo": "当前页 :_START_-_END_,总数 : _TOTAL_",
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
            "data": "belongsDate"
          },
          {"targets": 2,
            "data": "content"
          },
          {"targets": 3,
            "data": "status",
            "render":function (odata) {
              if(odata==0){
                return "按时提交";
              }else if(odata==1){
                return "逾期提交";
              }
            }
          },
        ]

      });
    }else{
      var table=  $('#report_table').DataTable({
        "dom": "<'toolbar'<'col-xs-12 col-sm-6'f>>"+
        "t"+
        "<'footer'<'col-sm-4	  hidden-xs'i><'col-xs-4 col-sm-3'><'col-xs-6 col-sm-5'p>>",

        "bFilter": false,//是否启用客户端过滤功能
        "aLengthMenu": [10, 20, 50, 100],//页条数
        "aaSorting": [[ 0, "asc" ]],//指定按多列数据排序的依据
        "bAutoWidth": true,//是否自动计算表格各列宽度
        "bLengthChange": true,//开关，是否显示一个每页长度的选择条（需要分页器支持）
        "iDisplayLength": 10,//一页长度
        "bServerSide": true,
        "bStateSave": false,//开关，是否打开客户端状态记录功能。这个数据是记录在cookies中的，打开了这个记录后，即使刷新一次页面，或重新打开浏览器，之前的状态都是保存下来的
        "sAjaxSource": '/search/query.do?belongsDate='+s+'&personId='+personId+'',//指定要从哪个URL获取数据
        "oLanguage": {                          //汉化
          "sLengthMenu": "每页 _MENU_ 条记录",
          "sZeroRecords": "没有检索到数据",
          "sInfo": "当前页 :_START_-_END_,总数 : _TOTAL_",
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
            "data": "belongsDate"
          },
          {"targets": 2,
            "data": "content"
          },
          {"targets": 3,
            "data": "status",
            "render":function (odata) {
              if(odata==0){
                return "按时提交";
              }else if(odata==1){
                return "逾期提交";
              }
            }
          },
        ]
      });
    }
  };

  //系统时间
  function nowTime() {
    var time=moment(new Date()).format("YYYY-MM-DD");
    return (time);
  };
  loadScript("/smartAdmin/js/plugin/moment/moment.min.js", function () {
    loadScript("/smartAdmin/js/plugin/fullcalendar/jquery.fullcalendar.min.js", searchCalendar);
  });
  function searchCalendar() {
    var rptPersonId=$("#rptPersonId").val();
    $.ajax({
      type: "POST",
      url: '/search/color.do',
      data: {
        type:0,
        belongsDate: nowTime(),
        rptPersonId:rptPersonId
      },
      success: function (data) {
        if ($("#calendar").length) {
          var date = new Date();
          var d = date.getDate();
          var m = date.getMonth();
          var y = date.getFullYear();
          calendar = $('#calendar').fullCalendar({
            editable: false,
            draggable: true,
            selectable: false,
            selectHelper: true,
            unselectAuto: false,
            disableResizing: false,
            header: {
              left: 'title', //,today
              center: 'prev, next, today',
              right: 'month, agendaWeek, agenDay' //month, agendaDay,
            },
            defaultView: "month",
            select: function (start, end, allDay) {
              var title = prompt('Event Title:');
              if (title) {
                calendar.fullCalendar('renderEvent', {
                          title: title,
                          start: start,
                          end: end,
                          allDay: allDay
                        }, true // make the event "stick"
                );
              }
              calendar.fullCalendar('unselect');
            },
            eventRender: function (event, element, icon) {
              if (!event.description == "") {
                element.find('.fc-event-title').append("<br/><span class='ultra-light'>" + event.description +
                "</span>");
              }
              if (!event.icon == "") {
                element.find('.fc-event-title').append("<i class='air air-top-right fa " + event.icon +
                " '></i>");
              }
            },
            eventClick: function (calEvent, jsEvent, view) {//点击刷新数据
              var date = calEvent.start;
              var belongsDate = date.format();
              $("#workHourDate")[0].innerHTML=date.format("YYYY-MM-DD");
              var personId=$("#personId").val();
              $('#report_table').DataTable().ajax.url('/search/query.do?personId'+personId+'&belongsDate='+belongsDate+'&type='+0+'').load();
            },
            dayClick: function (date, allDay, jsEvent, view) {
              date.stripTime();
              var belongsDate = date.format();
              console.log(belongsDate)
              var dates = nowTime();
              $("#workHourDate")[0].innerHTML=date.format("YYYY-MM-DD");
              var belongsDates=moment(belongsDate).format("YYYY-MM-DD");
              var personId=$("#personId").val();
              $('#report_table').DataTable().ajax.url('/search/query.do?personId'+personId+'&belongsDate='+belongsDate+'').load();
              if(belongsDate<dates||belongsDates==dates){
                var personId=$("#personId").val();
                $('#report_table').DataTable().ajax.url('/search/query.do?personId'+personId+'&belongsDate='+belongsDate+'').load();
              };
            }, events: eval(data)

          });
        }

        //跳转到需要显示的日历时间
        if(sDate='${cDate}') {
          var m= $.fullCalendar.moment(sDate);
          $('#calendar').fullCalendar( 'gotoDate', m);
        }
        /* hide default buttons */
        $('.fc-header-right, .fc-header-center').hide();
      }
    });
  }

  // calendar prev
  $('#calendar-buttons #btn-prev').click(function () {
    $('.fc-button-prev').click();
    return false;
  });

  // calendar next
  $('#calendar-buttons #btn-next').click(function () {
    $('.fc-button-next').click();
    return false;
  });

  // calendar today
  $('#calendar-buttons #btn-today').click(function () {
    $('.fc-button-today').click();
    return false;
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
                $('#workHourH2')[0].innerHTML=data.text;
                $('#calendar').fullCalendar('destroy');
                var personId=data.href;
                $('#personId').val(personId);
                searchCalendar1(personId);
                var belongsDate=$("#workHourDate").html();
                console.log(belongsDate+"belongsDatebelongsDatebelongsDate");
                $('#report_table').DataTable().ajax.url('/search/query.do?belongsDate='+belongsDate+'&personId='+personId+'&type='+0+'').load();
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
function checkAllPerson(){
  $('#workHourH2')[0].innerHTML="所有人员";
  $('#calendar').fullCalendar('destroy');
  searchCalendar();
  var belongsDate=$("#workHourDate").html();
  console.log(belongsDate+"belongsDatebelongsDatebelongsDatessssssss");
  $("#personId").val("");
  var personId=$("#personId").val();
  $('#report_table').DataTable().ajax.url('/search/query.do?belongsDate='+belongsDate+'&personId='+personId+'&type='+0+'').load();
}

  function searchCalendar1(rptPersonId) {
    $.ajax({
      type: "POST",
      url: '/search/color.do',
      data: {
        type:0,
        belongsDate: nowTime(),
        rptPersonId:rptPersonId
      },
      success: function (data) {
        if ($("#calendar").length) {
          var date = new Date();
          var d = date.getDate();
          var m = date.getMonth();
          var y = date.getFullYear();
          calendar = $('#calendar').fullCalendar({
            editable: false,
            draggable: true,
            selectable: false,
            selectHelper: true,
            unselectAuto: false,
            disableResizing: false,
            header: {
              left: 'title', //,today
              center: 'prev, next, today',
              right: 'month, agendaWeek, agenDay' //month, agendaDay,
            },
            defaultView: "month",
            select: function (start, end, allDay) {
              var title = prompt('Event Title:');
              if (title) {
                calendar.fullCalendar('renderEvent', {
                          title: title,
                          start: start,
                          end: end,
                          allDay: allDay
                        }, true // make the event "stick"
                );
              }
              calendar.fullCalendar('unselect');
            },
            eventRender: function (event, element, icon) {
              if (!event.description == "") {
                element.find('.fc-event-title').append("<br/><span class='ultra-light'>" + event.description +
                "</span>");
              }
              if (!event.icon == "") {
                element.find('.fc-event-title').append("<i class='air air-top-right fa " + event.icon +
                " '></i>");
              }
            },
            eventClick: function (calEvent, jsEvent, view) {//点击刷新数据
              var date = calEvent.start;
              var belongsDate = date.format();
              $("#workHourDate")[0].innerHTML=date.format("YYYY-MM-DD");
              var personId=$("#personId").val();
              $('#report_table').DataTable().ajax.url('/search/query.do?belongsDate='+belongsDate+'&personId='+personId+'&type='+0+'').load();
            },
            dayClick: function (date, allDay, jsEvent, view) {
              date.stripTime();
              var belongsDate = date.format();
              console.log(belongsDate)
              var dates = nowTime();
              $("#workHourDate")[0].innerHTML=date.format("YYYY-MM-DD");
              var belongsDates=moment(belongsDate).format("YYYY-MM-DD");
              var personId=$("#personId").val();
              $('#report_table').DataTable().ajax.url('/search/query.do?personId'+personId+'&belongsDate='+belongsDate+'').load();
              if(belongsDate<dates||belongsDates==dates){
                var personId=$("#personId").val();
                $('#report_table').DataTable().ajax.url('/search/query.do?belongsDate='+belongsDate+'&personId='+personId).load();
              };
            }, events: eval(data)

          });
        }

        //跳转到需要显示的日历时间
        if(sDate='${cDate}') {
          var m= $.fullCalendar.moment(sDate);
          $('#calendar').fullCalendar( 'gotoDate', m);
        }
        /* hide default buttons */
        $('.fc-header-right, .fc-header-center').hide();
      }
    });
  }
</script>