<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
<div class="row">

  <div class="col-sm-12 col-md-12 col-lg-4">
    <!-- new widget -->
    <div class="jarviswidget jarviswidget-color-blueDark">
      <header>
        <h2> 日程 </h2>
        <div class="widget-toolbar">
          <input type="hidden" name="oid" id="oid"/>
        </div>
      </header>

      <!-- widget div-->
      <div>

        <div class="widget-body">
          <!-- content goes here -->

          <form id="add-event-form">
            <fieldset>

              <div class="form-group">
                <label>选择日程标识</label>
                <div class="btn-group btn-group-sm btn-group-justified" data-toggle="buttons">
                  <label class="btn btn-default active">
                    <input type="radio" name="iconselect" id="icon-1" value="fa-info" checked>
                    <i class="fa fa-info text-muted"></i> </label>
                  <label class="btn btn-default">
                    <input type="radio" name="iconselect" id="icon-2" value="fa-warning">
                    <i class="fa fa-warning text-muted"></i> </label>
                  <label class="btn btn-default">
                    <input type="radio" name="iconselect" id="icon-3" value="fa-check">
                    <i class="fa fa-check text-muted"></i> </label>
                  <label class="btn btn-default">
                    <input type="radio" name="iconselect" id="icon-4" value="fa-user">
                    <i class="fa fa-user text-muted"></i> </label>
                  <label class="btn btn-default">
                    <input type="radio" name="iconselect" id="icon-5" value="fa-lock">
                    <i class="fa fa-lock text-muted"></i> </label>
                  <label class="btn btn-default">
                    <input type="radio" name="iconselect" id="icon-6" value="fa-clock-o">
                    <i class="fa fa-clock-o text-muted"></i> </label>
                </div>
              </div>
              <div class="form-group">
                <label class="input">
                  开始时间：
                  <input type="text" name="startTime" id="startTime" placeholder="选择开始日期" readonly="true" readonly="true" style="width: 40%">
                  <input id="startTimepicker" name="startTimepicker" type="text" placeholder="选择开始时间" style="width: 30%">
                </label>
              </div>
              <div class="form-group">
                <label class="input">
                  结束时间：
                  <input type="text" name="endTime" id="endTime" placeholder="选择结束日期" readonly="true" readonly="true" style="width: 40%">
                  <input id="endTimepicker" name="endTimepicker" type="text" placeholder="选择结束时间" style="width: 30%">
                </label>
              </div>
              <div class="form-group">
                <label>日程名称</label>
                <input class="form-control"  id="title" name="title" maxlength="40" type="text" placeholder="日程名称">
              </div>
              <div class="form-group">
                <label>日程内容</label>
                <textarea class="form-control" placeholder="日程内容" rows="3" maxlength="40" id="description" style="resize: none;"></textarea>
                <p class="note">不能超过40个字符</p>
              </div>

              <div class="form-group">
                <label>选择日程背景色</label>
                <div class="btn-group btn-group-justified btn-select-tick" data-toggle="buttons">
                  <label class="btn bg-color-darken active">
                    <input type="radio" name="priority" id="option1" value="bg-color-darken txt-color-white" checked>
                    <i class="fa fa-check txt-color-white"></i> </label>
                  <label class="btn bg-color-blue">
                    <input type="radio" name="priority" id="option2" value="bg-color-blue txt-color-white">
                    <i class="fa fa-check txt-color-white"></i> </label>
                  <label class="btn bg-color-orange">
                    <input type="radio" name="priority" id="option3" value="bg-color-orange txt-color-white">
                    <i class="fa fa-check txt-color-white"></i> </label>
                  <label class="btn bg-color-greenLight">
                    <input type="radio" name="priority" id="option4" value="bg-color-greenLight txt-color-white">
                    <i class="fa fa-check txt-color-white"></i> </label>
                  <label class="btn bg-color-blueLight">
                    <input type="radio" name="priority" id="option5" value="bg-color-blueLight txt-color-white">
                    <i class="fa fa-check txt-color-white"></i> </label>
                  <label class="btn bg-color-red">
                    <input type="radio" name="priority" id="option6" value="bg-color-red txt-color-white">
                    <i class="fa fa-check txt-color-white"></i> </label>
                </div>
              </div>

            </fieldset>
            <div class="form-actions">
              <div class="row">
                <div class="col-md-12">
                  <button class="btn btn-success btn-xs" type="button" id="add-event" >
                    添加
                  </button>
                  <button class="btn btn-success btn-xs" type="button" id="update-event" style="display: none">
                    更新
                  </button>
                  <button class="btn btn-danger btn-xs" type="button" id="delete-event" style="display: none">
                    删除
                  </button>
                  <button class="btn btn-primary  btn-xs" type="button" id="cancel-event" style="display: none">
                    取消
                  </button>
                </div>
              </div>
            </div>

          </form>

          <!-- end content -->
        </div>

      </div>
      <!-- end widget div -->
    </div>
    <!-- end widget -->
  </div>
  <div class="col-sm-12 col-md-12 col-lg-8">

    <!-- new widget -->
    <div class="jarviswidget jarviswidget-color-blueDark">
      <header>
        <span class="widget-icon"> <i class="fa fa-calendar"></i> </span>
        <h2> 我的日程 </h2>
        <div class="widget-toolbar">
          <div class="btn-group">
            <button class="btn dropdown-toggle btn-xs btn-default" data-toggle="dropdown">
              展示 <i class="fa fa-caret-down"></i>
            </button>
            <ul class="dropdown-menu js-status-update pull-right">
              <li>
                <a href="javascript:void(0);" id="mt">月份</a>
              </li>
              <li>
                <a href="javascript:void(0);" id="ag">日程</a>
              </li>
              <li>
                <a href="javascript:void(0);" id="td">今天</a>
              </li>
            </ul>
          </div>
        </div>
      </header>

      <!-- widget div-->
      <div>

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

  </div>

</div>

<!-- end row -->

<script type="text/javascript">
  pageSetUp();
  var fullviewcalendar;

  var pagefunction = function() {

    // full calendar

    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();

    var hdr = {
      left: 'title',
      center: 'month,agendaWeek,agendaDay',
      right: 'prev,today,next'
    };

    var initDrag = function (e) {

      var eventObject = {
        title: $.trim(e.children().text()), // use the element's text as the event title
        description: $.trim(e.children('span').attr('data-description')),
        icon: $.trim(e.children('span').attr('data-icon')),
        className: $.trim(e.children('span').attr('class')) // use the element's children as the event class
      };
      e.data('eventObject', eventObject);

      e.draggable({
        zIndex: 999,
        revert: true, // will cause the event to go back to its
        revertDuration: 0 //  original position after the drag
      });
    };
    /* initialize the external events
     -----------------------------------------------------------------*/

    $('#external-events > li').each(function () {
      initDrag($(this));
    });

    $('#add-event').click(function () {
      var startDate;
      var endDate;
      var title = $('#title').val();
      var priority = $('input:radio[name=priority]:checked').val().split(" ")[0];
      var description = $('#description').val();
      var icon = $('input:radio[name=iconselect]:checked').val();
      var startTime = $('#startTime').val();
      var endTime = $('#endTime').val();
      var startTimepicker = $('#startTimepicker').val();
      var endTimepicker = $("#endTimepicker").val();
      if(startTime == "" || startTime == null){
        swal("","开始日期不能为空","warning");
        return;
      }
      if(endTime == "" || endTime == null){
        swal("","结束日期不能为空","warning");
        return;
      }
      if(startTimepicker != "" && startTimepicker.split(":")[1]==""){
        swal("","开始时间格式不正确","warning");
        return;
      }
      if(endTimepicker != "" && endTimepicker.split(":")[1]==""){
        swal("","结束时间格式不正确","warning");
        return;
      }
      if(title == "" || title == null){
        swal("","日程名称不能为空","warning");
        return;
      }
      if(startTime !="" && startTimepicker == ""){
        startDate = startTime+" "+"0:00";
      }
      if(startTime !="" && startTimepicker != ""){
        startDate = startTime+" "+startTimepicker;
      }
      if(endTime !="" && endTimepicker == ""){
        endDate = endTime+" "+"23:59";
      }
      if(endTime !="" && endTimepicker != ""){
        endDate = endTime+" "+endTimepicker;
      }
      addEvent(title, priority, description, icon,startDate,endDate);
    });

    function addEvent(title, priority, description, icon,startTime,endTime){
      $.ajax({
        type: "POST",
        url: '/programmeManagement/insert.do',
        data:{
          title:title,
          startTime:startTime,
          endTime:endTime,
          className:priority,
          icon:icon,
          description:description
        },
        success: function (data) {
          $('#calendar').fullCalendar('refetchEvents');
        },
        // 调用出错执行的函数
        error: function () {

        }
      });
    }

    /* initialize the calendar
     -----------------------------------------------------------------*/

    fullviewcalendar = $('#calendar').fullCalendar({

      header: hdr,
      buttonText: {
        prev: '<i class="fa fa-chevron-left"></i>',
        next: '<i class="fa fa-chevron-right"></i>'
      },
      editable: false,
      droppable: true, // this allows things to be dropped onto the calendar !!!

      drop: function (date, allDay) { // this function is called when something is dropped
        // retrieve the dropped element's stored Event Object
        var originalEventObject = $(this).data('eventObject');

        // we need to copy it, so that multiple events don't have a reference to the same object
        var copiedEventObject = $.extend({}, originalEventObject);

        // assign it the date that was reported
        copiedEventObject.start = date;
        copiedEventObject.allDay = allDay;

        // render the event on the calendar
        // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
        $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

        // is the "remove after drop" checkbox checked?
        if ($('#drop-remove').is(':checked')) {
          // if so, remove the element from the "Draggable Events" list
          $(this).remove();
        }

      },

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
      eventClick: function (event, jsEvent, view) {
        $("#update-event").show();
        $("#delete-event").show();
        $("#cancel-event").show();
        $("#add-event").hide();
        var start = event.start;
        var end = event.end;
        var startDateTime = start.format("YYYY-MM-DD");
        var endDateTime = end.format("YYYY-MM-DD");
        var className = event.className[1] + " txt-color-white"
        $('#title').val(event.title);
        $('#description').val(event.description);
        $('#startTime').val(startDateTime);
        $('#endTime').val(endDateTime);
        $('#startTimepicker').val(start.format('HH:mm'));
        $("#endTimepicker").val(end.format('HH:mm'));
        $('#oid').val(event.id);
        $('input:radio[name=priority]').parent().removeClass("active");
        $('input:radio[value="'+className+'"]').parent().addClass("active");
        $('input:radio[name=priority]:checked').removeAttr("checked");
        $('input:radio[value="'+className+'"]').prop("checked",'checked');
        $('input:radio[name=iconselect]').parent().removeClass("active");
        $('input:radio[value="'+event.icon+'"]').parent().addClass("active");
        $('input:radio[name=iconselect]').removeAttr("checked");
        $('input:radio[value="'+event.icon+'"]').prop("checked",'checked');
      },
      events: function(start, end, timezone, callback) {
        $.ajax({
          type: "POST",
          url: '/programmeManagement/query.do',
          data:{
          },
          success: function (data) {
                var events = [];
                for(var i=0;i<data.length;i++){
                  events.push({
                    id:data[i].oid,
                    title: data[i].title,
                    description: data[i].description,
                    start: data[i].startTime,
                    end:data[i].endTime,
                    allDay: false,
                    className: ["event", data[i].className],
                    icon: data[i].icon
                  });
                }
                callback(events);
          },
          // 调用出错执行的函数
          error: function () {

          }
        });
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

      windowResize: function (event, ui) {
        $('#calendar').fullCalendar('render');
      }
    });

    /* hide default buttons */
    $('.fc-header-right, .fc-header-center').hide();



    $('#calendar-buttons #btn-prev').click(function () {
      $('.fc-button-prev').click();
      return false;
    });

    $('#calendar-buttons #btn-next').click(function () {
      $('.fc-button-next').click();
      return false;
    });

    $('#calendar-buttons #btn-today').click(function () {
      $('.fc-button-today').click();
      return false;
    });

    $('#mt').click(function () {
      $('#calendar').fullCalendar('changeView', 'month');
    });

    $('#ag').click(function () {
      $('#calendar').fullCalendar('changeView', 'agendaWeek');
    });

    $('#td').click(function () {
      $('#calendar').fullCalendar('changeView', 'agendaDay');
    });

  };

  var pagedestroy = function(){

    fullviewcalendar.fullCalendar( 'destroy' );
    fullviewcalendar = null;
    $("#add-event").off();
    $("#add-event").remove();

    $('#external-events > li').off();
    $('#external-events > li').remove();
    $('#add-event').off();
    $('#add-event').remove();
    $('#calendar-buttons #btn-prev').off();
    $('#calendar-buttons #btn-prev').remove();
    $('#calendar-buttons #btn-next').off();
    $('#calendar-buttons #btn-next').remove();
    $('#calendar-buttons #btn-today').off();
    $('#calendar-buttons #btn-today').remove();
    $('#mt').off();
    $('#mt').remove();
    $('#ag').off();
    $('#ag').remove();
    $('#td').off();
    $('#td').remove();

    if (debugState){
      root.console.log("✔ Calendar destroyed");
    }
  }


  $('#cancel-event').click(function () {
    $("#update-event").hide();
    $("#delete-event").hide();
    $("#cancel-event").hide();
    $("#add-event").show();
    $('#title').val("");
    $('#description').val("");
    $('#startTime').val("");
    $('#endTime').val("");
    $('#startTimepicker').val("");
    $("#endTimepicker").val("");
    $('#oid').val("");

    $('input:radio[name=priority]').parent().removeClass("active");
    $('#option1').parent().addClass("active");
    $('input:radio[name=priority]:checked').removeAttr("checked");
    $('#option1').prop("checked",'checked');

    $('input:radio[name=iconselect]').parent().removeClass("active");
    $('#icon-1').parent().addClass("active");
    $('input:radio[name=iconselect]').removeAttr("checked");
    $('#icon-1').prop("checked",'checked');

  });

  $('#update-event').click(function () {
    var startDate;
    var endDate;
    var title = $('#title').val();
    var priority = $('input:radio[name=priority]:checked').val().split(" ")[0];
    var description = $('#description').val();
    var icon = $('input:radio[name=iconselect]:checked').val();
    var startTime = $('#startTime').val();
    var endTime = $('#endTime').val();
    var startTimepicker = $('#startTimepicker').val();
    var endTimepicker = $("#endTimepicker").val();
    if(startTime == "" || startTime == null){
      swal("","开始日期不能为空","warning");
      return;
    }
    if(endTime == "" || endTime == null){
      swal("","结束日期不能为空","warning");
      return;
    }
    if(startTimepicker != "" && startTimepicker.split(":")[1]==""){
      swal("","开始时间格式不正确","warning");
      return;
    }
    if(endTimepicker != "" && endTimepicker.split(":")[1]==""){
      swal("","结束时间格式不正确","warning");
      return;
    }
    if(title == "" || title == null){
      swal("","日程名称不能为空","warning");
      return;
    }
    if(startTime !="" && startTimepicker == ""){
      startDate = startTime+" "+"0:00";
    }
    if(startTime !="" && startTimepicker != ""){
      startDate = startTime+" "+startTimepicker;
    }
    if(endTime !="" && endTimepicker == ""){
      endDate = endTime+" "+"23:59";
    }
    if(endTime !="" && endTimepicker != ""){
      endDate = endTime+" "+endTimepicker;
    }
    updateEvent(title, priority, description, icon,startDate,endDate);
  });

  function updateEvent(title, priority, description, icon,startTime,endTime){
    var oid = $("#oid").val();
    $.ajax({
      type: "POST",
      url: '/programmeManagement/update.do',
      data:{
        oid:oid,
        title:title,
        startTime:startTime,
        endTime:endTime,
        className:priority,
        icon:icon,
        description:description
      },
      success: function (data) {
        $('#title').val("");
        $('#description').val("");
        $('#startTime').val("");
        $('#endTime').val("");
        $('#startTimepicker').val("");
        $("#endTimepicker").val("");
        $('#oid').val("");

        $('input:radio[name=priority]').parent().removeClass("active");
        $('#option1').parent().addClass("active");
        $('input:radio[name=priority]:checked').removeAttr("checked");
        $('#option1').prop("checked",'checked');

        $('input:radio[name=iconselect]').parent().removeClass("active");
        $('#icon-1').parent().addClass("active");
        $('input:radio[name=iconselect]').removeAttr("checked");
        $('#icon-1').prop("checked",'checked');

        $("#update-event").hide();
        $("#delete-event").hide();
        $("#cancel-event").hide();
        $("#add-event").show();

        $('#calendar').fullCalendar('refetchEvents');
      },
      // 调用出错执行的函数
      error: function () {

      }
    });
  }

  $('#delete-event').click(function () {
    var oid = $("#oid").val();
    $.ajax({
      type: "POST",
      url: '/programmeManagement/delete.do',
      data:{
        oid:oid
      },
      success: function (data) {
        $('#title').val("");
        $('#description').val("");
        $('#startTime').val("");
        $('#endTime').val("");
        $('#startTimepicker').val("");
        $("#endTimepicker").val("");
        $('#oid').val("");

        $('input:radio[name=priority]').parent().removeClass("active");
        $('#option1').parent().addClass("active");
        $('input:radio[name=priority]:checked').removeAttr("checked");
        $('#option1').prop("checked",'checked');

        $('input:radio[name=iconselect]').parent().removeClass("active");
        $('#icon-1').parent().addClass("active");
        $('input:radio[name=iconselect]').removeAttr("checked");
        $('#icon-1').prop("checked",'checked');

        $("#update-event").hide();
        $("#delete-event").hide();
        $("#cancel-event").hide();
        $("#add-event").show();

        $('#calendar').fullCalendar('refetchEvents');
      },
      // 调用出错执行的函数
      error: function () {

      }
    });
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

  // loadscript and run pagefunction
  loadScript("/smartAdmin/js/plugin/moment/moment.min.js", function () {
    loadScript("/smartAdmin/js/plugin/fullcalendar/jquery.fullcalendar.min.js", pagefunction);
  });

  loadScript("/smartAdmin/js/plugin/bootstrap-timepicker/bootstrap-timepicker.min.js", runTimePicker);

  function runTimePicker() {
    $('#startTimepicker').timepicker({
      showMeridian: false
    });
    $('#endTimepicker').timepicker({
      showMeridian: false
    });
    $('#startTimepicker').val("");
    $("#endTimepicker").val("");
  }
</script>
