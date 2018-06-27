<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<div class="row">


    <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">


        <!-- MODAL PLACE HOLDER -->
        <div class="modal fade" id="remoteModal" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" style="width:800px ;height:600px">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title"><p>工时报送</p></h4>
                    </div>
                    <div class="modal-body no-padding">
                        <input type="hidden" id="belongsDate" name="belongsDate">


                        <form action="/workHour/save.do" method="post" id="login-form" class="smart-form">
                            <input type="hidden" id="workHourId" name="workHourId">
                            <fieldset>
                                <section>
                                    <div class="row">
                                        <label class="label col col-2">开始时间</label>

                                        <div class="col col-4">
                                            <label class="input"> <i class="icon-append  fa fa-clock-o"></i>
                                                <input class="form-control " id="startTimepicker" name="startTime"
                                                       type="text" placeholder="选择开始时间">
                                            </label>

                                        </div>

                                        <label class="label col col-2">结束时间</label>

                                        <div class="col col-4">
                                            <label class="input"> <i class="icon-append  fa fa-clock-o"></i>
                                                <input class="form-control " id="endTimepicker" name="endTime"
                                                       type="text" placeholder="选择开始时间">
                                            </label>

                                        </div>
                                    </div>
                                </section>

                                <section>
                                    <div class="row">

                                        <label class="label col col-2">所属项目</label>

                                        <div class="col col-4">
                                            <label class="input"> <i class="icon-append fa fa-lock"></i>
                                                <input type="text" id="belongsProject" name="belongsProject">
                                            </label>

                                        </div>

                                        <label class="label col col-2">工作类型</label>

                                        <div class="col col-4">
                                            <label class="select"> <i class="icon-append fa fa-lock"></i>
                                                <select class="input-sm" id="workType" name="workType">
                                                    <option value="0">开发</option>
                                                    <option value="1">运维</option>
                                                    <option value="2">测试</option>
                                                    <option value="3">需求沟通</option>
                                                    <option value="4">报表开发</option>
                                                    <option value="5">学习</option>
                                                </select> <i></i> </label>
                                        </div>


                                    </div>
                                </section>

                                <section>
                                    <div class="row">
                                        <label class="label col col-2">工作内容</label>

                                        <div class="col col-10">
                                            <label class="textarea textarea-expandable">
                                                <textarea rows="3" id="workContent" name="workContent"
                                                          class="custom-scroll"></textarea>
                                            </label>
                                        </div>
                                    </div>
                                </section>
                                <section>
                                    <div class="row">
                                        <label class="label col col-2">备注</label>

                                        <div class="col col-10">
                                            <label class="textarea textarea-expandable">
                                                <textarea rows="3" id="remark" name="remark"
                                                          class="custom-scroll "></textarea>
                                            </label>
                                        </div>
                                    </div>
                                </section>
                            </fieldset>

                            <footer>

                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                    取消
                                </button>
                                <button type="button" onclick="saveWorkHour()" class="btn btn-primary">
                                    提交
                                </button>
                            </footer>

                        </form>

                        <script>
                            // Load form valisation dependency
                            loadScript("/smartAdmin/js/plugin/jquery-form/jquery-form.min.js", function () {
                            });
                            // Registration validation script    var flag = $('#netForm').form('validate');
                            var $loginForm = $("#login-form").validate({
                                // Rules for form validation
                                rules: {
                                    startTimepicker: {
                                        required: true
                                    },
                                    endTimepicker: {
                                        required: true
                                    }, belongsProject: {
                                        required: true
                                    }, workType: {
                                        required: true
                                    }, workContent: {
                                        required: true
                                    }, remark: {
                                        required: false
                                    }
                                },

                                // Messages for form validation
                                messages: {
                                    startTimepicker: {
                                        required: "请将信息填写完整"
                                    },
                                    endTimepicker: {
                                        required: "请将信息填写完整"
                                    }, belongsProject: {
                                        required: "请将信息填写完整"
                                    }, workType: {
                                        required: "请将信息填写完整"
                                    }, workContent: {
                                        required: "请将信息填写完整"
                                    }, remark: {
                                        required: "请将信息填写完整"
                                    }
                                },

                                // Do not change code below
                                errorPlacement: function (error, element) {
                                    error.insertAfter(element.parent());
                                }
                            });

                        </script>

                    </div>


                </div>
            </div>
        </div>
        <!-- END MODAL -->


    </div>

</div>

<!-- widget grid -->
<section id="widget-grid" class="">


    <!-- row -->
    <div class="row">


        <!-- NEW WIDGET START -->
        <article class="col-sm-12 col-md-12 col-lg-3">


            <!-- new widget -->
            <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-3" data-widget-togglebutton="false"
                 data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false"
                 data-widget-editbutton="false">

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

                    <h2> 我的日历 </h2>

                    <div class="widget-toolbar">
                        <!-- add: non-hidden - to disable auto hide -->

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
                                    <a href="javascript:void(0)" class="btn btn-default btn-xs" id="btn-prev"><i
                                            class="fa fa-chevron-left"></i></a>
                                    <a href="javascript:void(0)" class="btn btn-default btn-xs" id="btn-next"><i
                                            class="fa fa-chevron-right"></i></a>
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
            <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false"
                 data-widget-togglebutton="false"
                 data-widget-deletebutton="false" data-widget-fullscreenbutton="false"
                 data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="true">
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

                    <h2 id="workHourH2"></h2>

                    <div class="widget-toolbar">
                        <input onclick="addWorkHour()" class="btn  btn-success btn-xs " type="button" value="新增">
                        <input id="updateButton" class="btn  btn-primary btn-xs " type="button" value="修改">
                        <input type="button" id="button" class="btn  btn-danger btn-xs " value="删除">


                    </div>
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
                                <th data-class="expand"> 开始时间</th>
                                <th data-hide="phone"> 结束时间</th>
                                <th data-class="expand">所属项目</th>
                                <th data-hide="phone">工作类型</th>
                                <th data-hide="phone">工作内容</th>
                                <th data-hide="phone">备注</th>
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

    var pagefunction = function () {


        var table = $('#dt_basic').DataTable({
            // "oLanguage": oLanguages,

            "dom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f>>" +
            "t" +
            "<'dt-toolbar-footer'<'col-sm-4	  hidden-xs'i><'col-xs-4 col-sm-3'l><'col-xs-6 col-sm-5'p>>",


            "bFilter": false,
            "aLengthMenu": [[10, 20, 50, 100], [10, 20, 50, 100]],
            "aaSorting": [[0, "asc"]],
            "bAutoWidth": true,
            "bLengthChange": true,
            "iDisplayLength": 10,
            "bServerSide": true,
            "bStateSave": true,
            "sAjaxSource": "/workHour/query.do",
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

                {
                    "targets": 0,
                    "data": "startTime",
                    "sWidth": "12%"
                },
                {
                    "targets": 1,
                    "data": "endTime",
                    "sWidth": "12%"
                },
                {
                    "targets": 2,
                    "data": "belongsProject",
                    "sWidth": "12%"
                },
                {
                    "targets": 3,
                    "data": "workType",
                    "sWidth": "12%",
                    "render": function (odata) {
                        if (odata == 0) {
                            return "开发";
                        } else if (odata == 1) {
                            return "运维";
                        } else if (odata == 2) {
                            return "测试";
                        } else if (odata == 3) {
                            return "需求沟通";
                        } else if (odata == 4) {
                            return "报表开发";
                        } else if (odata == 5) {
                            return "学习";
                        }

                    }
                },

                {
                    "targets": 4,
                    "data": "workContent"
                },
                {
                    "targets": 5,
                    "data": "remark",
                    "sWidth": "10%"
                }
            ]

        });

        $('#dt_basic tbody').on('click', 'tr', function () {
            $(this).toggleClass('selected');
            //console.log($("#dt_basic").find(".selected"));
            //alert(table.rows('.selected').data())
            //alert(table.row(0).data());
        });
        //删除
        $('#button').click(function () {




            // console.log( table.rows('.selected').row(0).data().id );
            // table.row('.selected').remove().draw( false );
            var ids = '';
            for (var i = 0; i < table.rows('.selected').data().length; i++) {
                //console.log(table.rows('.selected').row(i).data().id);
                ids += table.rows('.selected').data()[i].id + ",";
            }

            $.ajax({
                type: "GET",
                url: '/workHour/delete.do',
                data: {
                    ids: ids
                },
                success: function (data) {
                    if (data == "success") {
                        $('#dt_basic').DataTable().ajax.reload();

                    } else {
                    }
                },
                // 调用出错执行的函数
                error: function () {
                }
            });


        });


        $('#updateButton').click(function () {
            var ids = '';
            if (table.rows('.selected').data().length != 1) {
                alert("请选择一条工时数据")
            } else {
                workHourId = table.rows('.selected').data()[0].id;
                var belongsDate = $('#belongsDate').val() + " ";
                var today = CurentTime();
                console.log(belongsDate);
                console.log(today);
                if (belongsDate != today) {
                    alert("只能修改当天数据");
                    return;
                }


                $.ajax({
                    type: "GET",
                    url: '/workHour/findById.do',
                    data: {
                        id: workHourId
                    },
                    success: function (data) {
                        $("#remoteModal").modal("show");
                        $('#workHourId').val(workHourId);
                        $('#startTimepicker').val(data.startTime);
                        $('#endTimepicker').val(data.endTime);
                        $('#belongsProject').val(data.belongsProject);
                        $('#workType').val(data.workType);
                        $('#workContent').val(data.workContent);
                        $('#remark').val(data.remark);


                    },
                    // 调用出错执行的函数
                    error: function () {
                    }
                });


                $("#remoteModal").modal("show");

            }


        });


    };


    function CurentTime() {
        var now = new Date();

        var year = now.getFullYear();       //年
        var month = now.getMonth() + 1;     //月
        var day = now.getDate();            //日

        var hh = now.getHours();            //时
        var mm = now.getMinutes();          //分
        var ss = now.getSeconds();           //秒

        var clock = year + "-";

        if (month < 10)
            clock += "0";

        clock += month + "-";

        if (day < 10)
            clock += "0";

        clock += day + " ";


        return (clock);
    }


    function saveWorkHour() {
        var belongsDate = CurentTime();
        var startTime = $('#startTimepicker').val();
        var endTime = $('#endTimepicker').val();
        var belongsProject = $('#belongsProject').val();
        var workType = $('#workType').val();
        var workContent = $('#workContent').val();
        var remark = $('#remark').val();
        var workHourId = $('#workHourId').val();
        startTime = belongsDate + " " + startTime;
        endTime = belongsDate + " " + endTime;
        belongsDate = startTime;
        $('#login-form').submit(function (event) {
            event.preventDefault();
        });

        $('#login-form').submit(); //触发绑定事件；
        var validator = $('#login-form').validate();
        if (validator.numberOfInvalids() <= 0) { //判断加入所有校验都通过后再做ajax提交；
            $.ajax({
                type: "POST",
                url: '/workHour/save.do',
                data: {
                    startTime: startTime,
                    endTime: endTime,
                    belongsProject: belongsProject,
                    workType: workType,
                    workContent: workContent,
                    remark: remark,
                    id: workHourId

                },
                success: function (data) {
                    if (data == "success") {

                        $("#remoteModal").modal("hide");
                        $('#dt_basic').DataTable().ajax.reload();

                    } else {
                        alert('温馨提示', '失败！');
                    }
                },
                // 调用出错执行的函数
                error: function () {
                }
            });
        }

    }


    /*
     * FULL CALENDAR JS
     */

    // Load Calendar dependency then setup calendar  setupCalendar

    loadScript("/smartAdmin/js/plugin/moment/moment.min.js", function () {
        loadScript("/smartAdmin/js/plugin/fullcalendar/jquery.fullcalendar.min.js", setupCalendar);
    });

    function setupCalendar() {
        $.ajax({
            type: "POST",
            url: '../workHour/count.do',
            data: {
                'delFlag': '2'
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
                        eventClick: function (calEvent, jsEvent, view) {
                            // console.log(calEvent.start.format());
                            // console.log(jsEvent);
                            // console.log(view);
                            var date = calEvent.start;

                            $('#belongsDate').val(date.format());
                            $('#workHourH2')[0].innerHTML = date.format();
                            var belongsDate = date.format() + ' ' + "00:00";
                            $('#dt_basic').DataTable().ajax.url('/workHour/query.do?belongsDate=' + belongsDate + '').load();


                        },
                        dayClick: function (date, allDay, jsEvent, view) {
                            // alert($('#calendar').fullCalendar.formatDate(date, "yyyy-MM-dd"));
                            date.stripTime();
                            $('#belongsDate').val(date.format());
                            $('#workHourH2')[0].innerHTML = date.format();
                            var belongsDate = date.format() + ' ' + "00:00";
                            $('#dt_basic').DataTable().ajax.url('/workHour/query.do?belongsDate=' + belongsDate + '').load();
                        }, events: eval(data)

                    });
                }
                ;
                /* hide default buttons */
                $('.fc-header-right, .fc-header-center').hide();
            }
        });
    }


    function addWorkHour() {


        $("#remoteModal").modal("show");
        //$("#workType").val("");
        $("#login-form input").val("");//.removeAttr("checked").remove("selected");//核心
        $("#login-form textarea").val("");
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

    loadScript("/smartAdmin/js/plugin/datatables/jquery.dataTables.min.js", function () {
        loadScript("/smartAdmin/js/plugin/datatables/dataTables.colVis.min.js", function () {
            loadScript("/smartAdmin/js/plugin/datatables/dataTables.tableTools.min.js", function () {
                loadScript("/smartAdmin/js/plugin/datatables/dataTables.bootstrap.min.js", function () {
                    loadScript("/smartAdmin/js/plugin/datatable-responsive/datatables.responsive.min.js", pagefunction)
                });
            });
        });
    });


    /*
     * TIMEPICKER
     */

    //Load time picker script

    loadScript("/smartAdmin/js/plugin/bootstrap-timepicker/bootstrap-timepicker.min.js", runTimePicker);

    function runTimePicker() {
        $('#startTimepicker').timepicker({
            showMeridian: false
        });
        $('#endTimepicker').timepicker({
            showMeridian: false
        });
    }


</script>

<style>
    table.dataTable tbody tr.selected {
        background-color: #b0bed9;
    }

</style>


