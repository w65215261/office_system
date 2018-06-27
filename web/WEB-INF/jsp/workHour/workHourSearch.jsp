<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<div class="row">


</div>
<!-- widget grid -->
<section id="widget-grid" class="">

    <!-- row -->
    <div class="row">
        <div class="col-sm-12 col-md-12 col-lg-3">
        <!-- NEW WIDGET START -->
        <article class="col-sm-12 col-md-12 col-lg-12">

            <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0"  data-widget-togglebutton="false"
                 data-widget-deletebutton="false" data-widget-fullscreenbutton="false"
                 data-widget-colorbutton="false" data-widget-editbutton="false"  data-widget-custombutton="true">

                <header>
                    <span class="widget-icon"> <i class="fa fa-lg fa-bank"></i> </span>
                    &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                    <input type="button" value="查看所有" id="queryAlla"  class="btn  btn-primary btn-xs " >

                </header>
                <div>
                <div>
                    <!-- widget edit box -->
                    <div class="jarviswidget-editbox">
                        <!-- This area used as dropdown edit box -->

                    </div>
                    <!-- end widget edit box -->

                    <!-- widget content -->
                    <div class="widget-body no-padding">


                        <div class="custom-scroll table-responsive" style="height:250px; overflow-y: scroll;">
                            <div id="organTree">


                            </div>
                        </div>

                    </div>
                    <!-- end widget content -->

                </div>
                <!-- end widget div -->

            </div>
            <!-- end widget -->

        </article>
            <!--calender WIDGET START -->

            <!-- NEW WIDGET START -->
            <article class="col-sm-12 col-md-12 col-lg-12">


                <!-- new widget -->
                <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-3" data-widget-togglebutton="false"
                     data-widget-deletebutton="false" data-widget-fullscreenbutton="false"
                     data-widget-colorbutton="false" data-widget-editbutton="false"  data-widget-custombutton="true">

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
                        <h2> 日历 </h2>
                        <div class="widget-toolbar">
                            <!-- add: non-hidden - to disable auto hide -->
                            <div class="btn-group">
                                <button class="btn dropdown-toggle btn-xs btn-default" data-toggle="dropdown">
                                    展示模式 <i class="fa fa-caret-down"></i>
                                </button>
                                <ul class="dropdown-menu js-status-update pull-right">
                                    <li>
                                        <a href="javascript:void(0);" id="mt">月模式</a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);" id="ag">周模式</a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);" id="td">日模式</a>
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




            <!--calender WIDGET END  -->

        </div>
        <!-- WIDGET END -->



        <!--WORKHOUR DATATABLE WIDGET START -->
        <div class="col-sm-12 col-md-12 col-lg-9">


            <article class="col-sm-12 col-md-12 col-lg-12">
                <div class="jarviswidget jarviswidget-color-darken" id="wid-id-1" data-widget-editbutton="false" data-widget-togglebutton="false"
                     data-widget-deletebutton="false" data-widget-fullscreenbutton="false"
                     data-widget-colorbutton="false" data-widget-editbutton="false"  data-widget-custombutton="true">
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
                        <h2 id="workHourH2">工时</h2>
                        <input type="hidden" id="hiddenName">
                        <div class="widget-toolbar">


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
                                    <th data-class="expand"><i class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i> 开始时间</th>
                                    <th data-hide="phone"><i class="fa fa-fw fa-phone text-muted hidden-md hidden-sm hidden-xs"></i> 结束时间</th>
                                    <th data-class="expand"><i class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i>申报人</th>
                                    <th data-class="expand"><i class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i>所属项目</th>
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

        <!--WORKHOUR DATATABLE WIDGET END -->



    </div>

    <!-- end row -->

    <!-- row -->

    <div class="row">

    </div>

    <!-- end row -->

</section>
<!-- end widget grid -->

<script type="text/javascript">


    pageSetUp();



    var pagefunction = function () {

        var table=  $('#dt_basic').DataTable({
            // "oLanguage": oLanguages,

            "dom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f>>"+
            "t"+
            "<'dt-toolbar-footer'<'col-sm-4	  hidden-xs'i><'col-xs-4 col-sm-3'l><'col-xs-6 col-sm-5'p>>",


            "bFilter": false,
            "aLengthMenu": [[10, 20, 50, 100], [10, 20, 50, 100]],
            "aaSorting": [[ 0, "asc" ]],
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

                {"targets": 0,
                    "data": "startTime"
                },
                {"targets":1,
                    "data": "endTime"
                },
                {"targets": 2,
                    "data": "personId"
                },
                {"targets": 3,
                    "data": "belongsProject"
                },
                {"targets": 4,
                    "data": "workType",
                    "render":function (odata) {
                        if(odata==0){
                            return "开发";
                        }else if(odata==1){
                            return "运维";
                        }else if(odata==2){
                            return "测试";
                        }else if(odata==3){
                            return "需求沟通";
                        }else if(odata==4){
                            return "报表开发";
                        }else if(odata==5){
                            return "学习";
                        }

                    }
                },

                {"targets": 5,
                    "data": "workContent"
                },
                {
                    "targets":6,
                    "data":"remark",
                    "sWidth": "10%"
                }
            ]

        });

        $('#dt_basic tbody').on( 'click', 'tr', function () {
            $(this).toggleClass('selected');
        } );











        loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
        loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTree);


        function renderTree() {


            $.ajax({
                type: "GET",
                url: '/organization/queryAlltree.do',
                data: {},
                success: function (data) {
                    $('#organTree').treeview({
                        data: data,
                        onNodeSelected: function (event, data) {
                            if(data.icon=="glyphicon glyphicon-tower"){
                                return ;
                            }else{

                                $("#hiddenName").val(data.text+"                           ");
                                $('#workHourH2')[0].innerHTML=data.text

                                $('#calendar').fullCalendar('destroy');
                                var personId=data.href;
                                setupCalendar(personId);
                                $('#calendar').fullCalendar( 'refetchEvents' )

                            }



                        }
                    });
                },
                // 调用出错执行的函数
                error: function () {
                }
            });
        }
    };
    //END PAGEFUNCTION





    /*
     * FULL CALENDAR JS
     */

    // Load Calendar dependency then setup calendar  setupCalendar

    loadScript("/smartAdmin/js/plugin/moment/moment.min.js", function(){
        loadScript("/smartAdmin/js/plugin/fullcalendar/jquery.fullcalendar.min.js", setupCalendartwo());
    });




    function setupCalendar(personId) {
        $.ajax({
            type : "POST",
            url : '../workHour/count.do',
            data : {
                'delFlag' : '3',
                'personId':personId
            },
            success : function(data) {
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
                        defaultView:"month",
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
                        eventClick: function(calEvent, jsEvent, view) {
                            // console.log(calEvent.start.format());
                            // console.log(jsEvent);
                            // console.log(view);
                            var date=calEvent.start;
                            $('#workHourH2')[0].innerHTML=date.format()+"            "+ $("#hiddenName").val();
                            $('#belongsDate').val(date.format());
                            var belongsDate=date.format()+' '+"00:00";
                            console.log($('#organTree').treeview('getSelected')[0]);
                           var personId=$('#organTree').treeview('getSelected')[0].href;
                            $('#dt_basic').DataTable().ajax.url( '/workHour/query.do?belongsDate='+belongsDate+'&personId='+personId+'' ).load();



                        },
                        dayClick: function (date, allDay, jsEvent, view){
                            // alert($('#calendar').fullCalendar.formatDate(date, "yyyy-MM-dd"));
                            date.stripTime();
                            $('#belongsDate').val(date.format());
                            $('#workHourH2')[0].innerHTML=date.format()+"            "+ $("#hiddenName").val();
                            var belongsDate=date.format()+' '+"00:00";
                            var personId=$('#organTree').treeview('getSelected')[0].href;
                            $('#dt_basic').DataTable().ajax.url( '/workHour/query.do?belongsDate='+belongsDate+'&personId='+personId+'' ).load();
                        }, events:eval(data)

                    });
                };
                /* hide default buttons */
                $('.fc-header-right, .fc-header-center').hide();


            }



        });
    }

    function setupCalendartwo() {
        $.ajax({
            type : "POST",
            url : '../workHour/count.do',
            data : {
                'delFlag' : '3'
            },
            success : function(data) {
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
                        defaultView:"month",
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
                        eventClick: function(calEvent, jsEvent, view) {
                            // console.log(calEvent.start.format());
                            // console.log(jsEvent);
                            // console.log(view);
                            var date=calEvent.start;
                            $('#workHourH2')[0].innerHTML=date.format()+"            "+ $("#hiddenName").val();
                            $('#belongsDate').val(date.format());
                            var belongsDate=date.format()+' '+"00:00";
                            $('#dt_basic').DataTable().ajax.url( '/workHour/queryAll.do?belongsDate='+belongsDate+'' ).load();



                        },
                        dayClick: function (date, allDay, jsEvent, view){
                            // alert($('#calendar').fullCalendar.formatDate(date, "yyyy-MM-dd"));
                            date.stripTime();
                            $('#belongsDate').val(date.format());
                            $('#workHourH2')[0].innerHTML=date.format()+"            "+ $("#hiddenName").val();
                            var belongsDate=date.format()+' '+"00:00";
                            $('#dt_basic').DataTable().ajax.url( '/workHour/queryAll.do?belongsDate='+belongsDate+'' ).load();
                        }, events:eval(data)

                    });
                };
                /* hide default buttons */
                $('.fc-header-right, .fc-header-center').hide();


            }



        });
    }

    $('#queryAlla').click(function () {
        $('#calendar').fullCalendar('destroy');
        setupCalendartwo();
        $('#calendar').fullCalendar( 'refetchEvents' );
    });
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

    //pagefunction();

    loadScript("/smartAdmin/js/plugin/datatables/jquery.dataTables.min.js", function () {
        loadScript("/smartAdmin/js/plugin/datatables/dataTables.colVis.min.js", function () {
            loadScript("/smartAdmin/js/plugin/datatables/dataTables.tableTools.min.js", function () {
                loadScript("/smartAdmin/js/plugin/datatables/dataTables.bootstrap.min.js", function () {
                    loadScript("/smartAdmin/js/plugin/datatable-responsive/datatables.responsive.min.js", pagefunction)
                });
            });
        });
    });




</script>
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