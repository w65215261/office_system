<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="row">
  <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
    <h1 class="page-title txt-color-blueDark">
      <i class="fa fa-table fa-fw "></i>
      Table 
			<span>> 
				Data Tables
			</span>
    </h1>
  </div>

</div>

<!-- widget grid -->
<section id="widget-grid" class="">

  <!-- row -->
  <div class="row">




    <!-- NEW WIDGET START -->
    <article class="col-sm-12 col-md-12 col-lg-3">
      <!-- new widget -->
      <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-3" data-widget-colorbutton="false">

        <!-- widget options:
        usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

        data-widget-colorbutton="false"
        data-widget-editbutton="false"
        data-widget-togglebutton="false"
        data-widget-deletebutton="false"
        data-widget-fullscreenbutton="false"
        data-widget-custombutton="false"
        data-widget-collapsed="true"
        data-widget-sortable="false"

        -->
        <header>
          <span class="widget-icon"> <i class="fa fa-calendar"></i> </span>
          <h2> My Events </h2>
          <div class="widget-toolbar">
            <!-- add: non-hidden - to disable auto hide -->
            <div class="btn-group">
              <button class="btn dropdown-toggle btn-xs btn-default" data-toggle="dropdown">
                Showing <i class="fa fa-caret-down"></i>
              </button>
              <ul class="dropdown-menu js-status-update pull-right">
                <li>
                  <a href="javascript:void(0);" id="mt">Month</a>
                </li>
                <li>
                  <a href="javascript:void(0);" id="ag">Agenda</a>
                </li>
                <li>
                  <a href="javascript:void(0);" id="td">Today</a>
                </li>
              </ul>
            </div>
          </div>
        </header>

        <!-- widget div-->
        <div>
          <!-- widget edit box -->
          <div class="jarviswidget-editbox">

            <input class="form-control" type="text">

          </div>
          <!-- end widget edit box -->

          <div class="widget-body no-padding">
            <!-- content goes here -->
            <div class="widget-body-toolbar">

              <div id="calendar-buttons">

                <div class="btn-group">
                  <a href="javascript:void(0)" class="btn btn-default btn-xs" id="btn-prev"><i class="fa fa-chevron-left"></i></a>
                  <a href="javascript:void(0)" class="btn btn-default btn-xs" id="btn-next"><i class="fa fa-chevron-right"></i></a>
                </div>
              </div>
            </div>
            <div id="calendar"></div>

            <!-- end content -->
          </div>

        </div>
        <!-- end widget div -->
      </div>
      <!-- end widget -->
</article>
      <!-- Widget ID (each widget will need unique ID)-->

    <article class="col-sm-12 col-md-12 col-lg-9">
      <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false">
        <!-- widget options:
				usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

				data-widget-colorbutton="false"
				data-widget-editbutton="false"
				data-widget-togglebutton="false"
				data-widget-deletebutton="false"
				data-widget-fullscreenbutton="false"
				data-widget-custombutton="false"
				data-widget-collapsed="true"
				data-widget-sortable="false"

				-->
        <header>
          <span class="widget-icon"> <i class="fa fa-table"></i> </span>
          <h2>当日工时</h2>
        </header>

        <!-- widget div-->
        <div>

          <!-- widget edit box -->
          <div class="jarviswidget-editbox">
            <!-- This area used as dropdown edit box -->

          </div>
          <!-- end widget edit box -->

          <!-- widget content -->
          <div class="widget-body no-padding">

            <table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
              <thead>
              <tr>
                <th data-hide="phone"  >id</th>
                <th data-class="expand"><i class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i> 姓名</th>
                <th data-hide="phone"><i class="fa fa-fw fa-phone text-muted hidden-md hidden-sm hidden-xs"></i> 性别</th>
                <th data-hide="phone">英文名</th>
                <th data-hide="phone">操作</th>
              </tr>
              </thead>
              <tbody>

              </tbody>
            </table>

          </div>
          <!-- end widget content -->

        </div>
        <!-- end widget div -->

      </div>
      <!-- end widget -->




    </article>
    <!-- WIDGET END -->

  </div>

  <!-- end row -->

  <!-- end row -->

</section>
<!-- end widget grid -->

<script type="text/javascript">

  pageSetUp();

  var pagefunction = function() {



    $('#dt_basic').dataTable({
      // "oLanguage": oLanguages,

      "dom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f>>"+
      "t"+
      "<'dt-toolbar-footer'<'col-sm-8	  hidden-xs'i><'col-xs-4 col-sm-1'l><'col-xs-8 col-sm-3'p>>",

      "bFilter": false,
      "aLengthMenu": [[10, 20, 50, 100], [10, 20, 50, 100]],
      "iDisplayLength" : 10,
      "aaSorting": [[ 0, "asc" ]],
      "bAutoWidth": true,

      "bServerSide": true,
      "bStateSave": true,
      "sAjaxSource": "/userInfo/query.do",
      "aoColumns": [
        { "bSortable": true, "bSearchable": true, "sClass": "center","bVisible":true},
        { "bSortable": true, "bSearchable": true, "sClass": "center" },
        { "bSortable": true, "bSearchable": true, "sClass": "center" },
        { "bSortable": true, "bSearchable": true, "sClass": "center" }
      ],
      "columnDefs": [
        {"targets":[4],"data":"salary","render":function(data,type,full){return "<a href=\"javascript:void(0)\" data-toggle=\"modal\" class=\"smart-mod-eg1\" onclick=\"addPool('"+data+"',event)\"> <i class=\"fa fa-lg-2 fa-plus-circle\"> </i> </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <a href=\"#myModal-edit\" data-toggle=\"modal\" onclick=\"preUpdate('"+data+"')\"> <i class=\"fa fa-1g-2 fa-pencil\"> </i> </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     <a href=\"javascript:void(0)\" data-toggle=\"modal\" class=\"smart-mod-eg1\" onclick=\"delPool('"+data+"',event)\"> <i class=\"fa fa-lg-2 fa-trash-o\"> </i> </a>    "; } },
        {"targets":[2],"render":function(data,type,full){if(data==0){return "男";}else{return "女";}}}
      ]

    });




  };

  function delPool(data){
    var oTable = $('#dt_basic').dataTable();
    oTable.$('tr').click(function (e) {
      var data = oTable.fnGetData(this);
//      $(e.target);
//      alert(data[0]);
          $.ajax({
            type:"GET",
            url:'/userInfo/delete.do',
            data : {
              id : data[0]
            },
            success:function(data){
              if(data == "success"){
                alert('删除成功！');
                window.location.reload();
                //$('#memberList').datagrid({url:'/ofMucMember/findByRoomId.do?roomID='+$('#roomList').datagrid('getSelected').roomID,method:'GET'});
              }else{
                alert('温馨提示','删除失败！');
              }
            },
            // 调用出错执行的函数
            error: function(){
            }
          });
      });

    }





  /*
   * FULL CALENDAR JS
   */

  // Load Calendar dependency then setup calendar

  loadScript("/smartAdmin/js/plugin/moment/moment.min.js", function(){
    loadScript("/smartAdmin/js/plugin/fullcalendar/jquery.fullcalendar.min.js", setupCalendar);
  });

  function setupCalendar() {

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
        defaultView:"basicWeek",
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

        events: [],

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
        dayClick: function (date, allDay, jsEvent, view){
              alert(date);
          //刷新列表

        }
      });

    };

    /* hide default buttons */
    $('.fc-header-right, .fc-header-center').hide();

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

  // calendar month
  $('#mt').click(function () {
    $('#calendar').fullCalendar('changeView', 'month');
  });

  // calendar agenda week
  $('#ag').click(function () {
    $('#calendar').fullCalendar('changeView', 'agendaWeek');
  });

  // calendar agenda day
  $('#td').click(function () {
    $('#calendar').fullCalendar('changeView', 'agendaDay');
  });

  loadScript("/smartAdmin/js/plugin/datatables/jquery.dataTables.min.js", function(){
    loadScript("/smartAdmin/js/plugin/datatables/dataTables.colVis.min.js", function(){
      loadScript("/smartAdmin/js/plugin/datatables/dataTables.tableTools.min.js", function(){
        loadScript("/smartAdmin/js/plugin/datatables/dataTables.bootstrap.min.js", function(){
          loadScript("/smartAdmin/js/plugin/datatable-responsive/datatables.responsive.min.js", pagefunction)
        });
      });
    });
  });


</script>
