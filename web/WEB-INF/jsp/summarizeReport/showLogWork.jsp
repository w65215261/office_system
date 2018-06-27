<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/smartAdmin/css/BootSideMenu.css">
<link rel="stylesheet" href="/smartAdmin/css/normalize.css">
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">

<div class="widget-body no-padding" style="width: 99%;margin: 0 auto;">
    <form class="smart-form" id="reportShow">
        <fieldset>
            <section >
                <div class="row  no-padding">
                    <div class="col-sm-12">
                        <label class="input col-sm-4">
                            <span class=" col-sm-2 no-padding" style="line-height:32px;height: 32px;color: #000000">发送人：</span>
                            <span  class=" col-sm-10  no-padding">
                                <input type="text" class="text"  id="rptPersonName" name="rptPersonName" readonly="true" placeholder="点击选择发送人" onclick="showRptTree()">
                                <input type="hidden" class="text"  id="rptPersonId" name="rptPersonId">
                            </span>
                        </label>
                        <label class="col-sm-1" style="padding-left: 5px;width: 4%">
                            <button type="button"  id="saveProject" class="btn btn-primary" style="height: 32px;width: 50px;">
                                搜索
                            </button>
                        </label>
                    </div>
                    <div class="col-sm-12" style="padding-top: 30px;">
                        <label class="input col-sm-5">
                            <span class=" col-sm-2 no-padding" style="line-height:32px;height: 32px;width: 13.5%;color: #000000">时间：</span>
                            <span  class=" col-sm-3" style="padding-right: 6%;">
                                <input type="text" class="text"  id="startRptTime" name="startRptTime" readonly="true">
                            </span>
                            <span  class=" col-sm-3" style="margin-top: 15px;width: 80px;padding-right: 6%;">
                                <hr style="border: 1px solid rgba(169, 169, 169, 0.44);width: 80px;" />
                            </span>
                            <span  class=" col-sm-3 no-padding">
                                <input type="text" class="text"  id="endRptTime" name="endRptTime" readonly="true">
                            </span>
                        </label>
                    </div>
                    <div class="col-sm-12" style="padding-top: 30px;" id="mouldChange">
                        <span style="color: #000000" class="col-sm-12">选择模板<span style="color: #a9a9a9">（当前模板为：<span id="moduleId">日报</span>）</span></span>
                        <span  class="col-md-4" style="width: 10%;">
                            <button id="dayReport" type="button" class="btn btn-default btn-xs" onclick="changeCheck('day')" style="background-color: #76A6E9;height: 20px;width: 100%;">
                                <span style="font-size: 10px;color: white">日报</span>
                            </button>
                        </span>
                        <span  class="col-md-4" style="width: 10%;padding-left: 10px;">
                            <button id="weekReport" type="button" class="btn btn-default btn-xs" onclick="changeCheck('week')" style="background-color: rgba(169, 169, 169, 0.44);height: 20px;width: 100%;">
                                <span style="font-size: 10px;color: #696969">周报</span>
                            </button>
                        </span>
                        <span  class="col-md-4" style="width: 10%;padding-left: 10px;">
                            <button id="monthReport" type="button" class="btn btn-default btn-xs" onclick="changeCheck('month')" style="background-color: rgba(169, 169, 169, 0.44);height: 20px;width: 100%;">
                                <span style="font-size: 10px;color: #696969">月报</span>
                            </button>
                        </span>

                    </div>
                    <div class="col-sm-12" style="padding-top: 10px;">
                        <hr style="border: 1px solid rgba(169, 169, 169, 0.44);" />
                    </div>
                    <div class="col-sm-12" style="padding-top: 10px;text-align: right">
                        <span  class="col-md-12" >
                            <button type="button" onclick="calendarShow();" style="font-size: 20px;background-color: white;border: inherit" id="showName">
                                <i class="fa fa-calendar" style="color: #428BCA"><span style="font-size: 10px">展示</span></i>
                            </button>
                            <button type="button" onclick="" style="font-size: 20px;background-color: white;border: inherit;padding: 5px;">
                                <i class="fa fa-external-link" style="color: #428BCA"><span style="font-size: 10px">导出</span></i>
                            </button>
                        </span>
                    </div>
                    <div class="col-sm-12" style="padding-top: 20px;">

                        <div class="jarviswidget jarviswidget-color-blueDark" id="calendarShowId">

                            <!-- widget div-->
                            <div class="row" style="width: 98%;margin: 0 auto;">

                                <div class="widget-body no-padding">
                                    <!-- content goes here -->
                                    <div class="widget-body-toolbar"  style="background-color: #76A6E9;">

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




                        <table id="dayReportTable" class="table table-striped table-bordered table-hover" width="100%">
                            <thead>
                            <tr>
                                <th data-class="phone">填报人</th>
                                <th data-hide="phone"> 填报时间</th>
                                <th data-hide="phone"> 今日完成工作</th>
                                <th data-hide="phone">未完成工作</th>
                                <th data-hide="phone">需协调工作</th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr style="background-color: white">
                                    <td>王宾</td>
                                    <td>04月14日 11:08	</td>
                                    <td>日报1</td>
                                    <td>日报1</td>
                                    <td>日报1</td>
                                </tr>
                                <tr style="background-color: white">
                                    <td>王宾2</td>
                                    <td>04月16日 11:08	</td>
                                    <td>日报2</td>
                                    <td>日报2</td>
                                    <td>日报2</td>
                                </tr>
                                <tr style="background-color: white">
                                    <td>总计：2</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>

                        <table id="weekReportTable" class="table table-striped table-bordered table-hover" width="100%">
                            <thead>
                                <tr>
                                    <th data-class="phone">填报人</th>
                                    <th data-hide="phone"> 填报时间</th>
                                    <th data-hide="phone"> 本周完成工作</th>
                                    <th data-hide="phone">本周工作总结</th>
                                    <th data-hide="phone">下周工作计划</th>
                                    <th data-hide="phone">需协调与帮助</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr style="background-color: white">
                                    <td>王宾1</td>
                                    <td>04月14日 11:08	</td>
                                    <td>周报1</td>
                                    <td>周报1</td>
                                    <td>周报1</td>
                                    <td>周报1</td>
                                </tr>
                                <tr style="background-color: white">
                                    <td>总计：1</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>

                        <table id="monthReportTable" class="table table-striped table-bordered table-hover" width="100%">
                            <thead>
                                <tr>
                                    <th data-class="phone">填报人</th>
                                    <th data-hide="phone"> 填报时间</th>
                                    <th data-hide="phone"> 本月完成工作</th>
                                    <th data-hide="phone">本月工作总结</th>
                                    <th data-hide="phone">下月工作计划</th>
                                    <th data-hide="phone">需帮助与支持</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr style="background-color: white">
                                    <td>王宾1</td>
                                    <td>04月14日 11:08	</td>
                                    <td>月报1</td>
                                    <td>月报1</td>
                                    <td>月报1</td>
                                    <td>月报1</td>
                                </tr>
                                <tr style="background-color: white">
                                    <td>王宾2</td>
                                    <td>04月16日 11:08	</td>
                                    <td>月报2</td>
                                    <td>月报2</td>
                                    <td>月报2</td>
                                    <td>月报2</td>
                                </tr>
                                <tr style="background-color: white">
                                    <td>王宾3</td>
                                    <td>04月16日 11:08	</td>
                                    <td>月报3</td>
                                    <td>月报3</td>
                                    <td>月报3</td>
                                    <td>月报3</td>
                                </tr>
                                <tr style="background-color: white">
                                    <td>王宾4</td>
                                    <td>04月16日 11:08	</td>
                                    <td>月报4</td>
                                    <td>月报4</td>
                                    <td>月报4</td>
                                    <td>月报4</td>
                                </tr>
                                <tr style="background-color: white">
                                    <td>王宾4</td>
                                    <td>04月16日 11:08	</td>
                                    <td>月报4</td>
                                    <td>月报4</td>
                                    <td>月报4</td>
                                    <td>月报4</td>
                                </tr>
                                <tr style="background-color: white">
                                    <td>王宾5</td>
                                    <td>04月16日 11:08	</td>
                                    <td>月报5</td>
                                    <td>月报5</td>
                                    <td>月报5</td>
                                    <td>月报5</td>
                                </tr>
                                <tr style="background-color: white">
                                    <td>王宾6</td>
                                    <td>04月16日 11:08	</td>
                                    <td>月报6</td>
                                    <td>月报6</td>
                                    <td>月报6</td>
                                    <td>月报6</td>
                                </tr>
                                <tr style="background-color: white">
                                    <td>王宾7</td>
                                    <td>04月16日 11:08	</td>
                                    <td>月报7</td>
                                    <td>月报7</td>
                                    <td>月报7</td>
                                    <td>月报7</td>
                                </tr>
                                <tr style="background-color: white">
                                    <td>王宾8</td>
                                    <td>04月16日 11:08	</td>
                                    <td>月报8</td>
                                    <td>月报8</td>
                                    <td>月报8</td>
                                    <td>月报8</td>
                                </tr>
                                <tr style="background-color: white">
                                    <td>总计：8</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>

                    </div>
                </div>
            </section>
        </fieldset>
    </form>
</div>
<script type="text/javascript">


    pageSetUp();

    $("#weekReportTable").hide();
    $("#monthReportTable").hide();

    var pageFunction = function () {
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth()+1+"";
        var day = date.getDate()+"";
        if(month.length!=2){
            month = 0+month;
        }
        if(day.length!=2){
            day = 0+day;
        }
        date = year+"-"+month+"-"+day;
        $('#endRptTime').val(date);
        $('#startRptTime').val(date);

        $.datepicker.regional["zh-CN"] = { closeText: "关闭", prevText: "&#x3c;上月", nextText: "下月&#x3e;", currentText: "今天", monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"], monthNamesShort: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"], dayNames: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"], dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"], dayNamesMin: ["日", "一", "二", "三", "四", "五", "六"], weekHeader: "周", dateFormat: "yy-mm-dd", firstDay: 1, isRTL: !1, showMonthAfterYear: !0, yearSuffix: "年" };
        $.datepicker.setDefaults($.datepicker.regional["zh-CN"]);
        $('#startRptTime').datepicker({
            dateFormat : 'yy-mm-dd',
            showOtherMonths:true,
            prevText : '<i class="fa fa-chevron-left"></i>',
            nextText : '<i class="fa fa-chevron-right"></i>',
            onSelect : function(selectedDate) {
                $('#endRptTime').datepicker('option', 'minDate', selectedDate);
            }
        });
        $('#endRptTime').datepicker({
            dateFormat: 'yy-mm-dd',
            showOtherMonths: true,
            prevText: '<i class="fa fa-chevron-left"></i>',
            nextText: '<i class="fa fa-chevron-right"></i>',
            onSelect: function (selectedDate) {
                $('#startRptTime').datepicker('option', 'maxDate', selectedDate);
            }
        });
    };

    loadScript("/smartAdmin/js/plugin/delete-table-row/delete-table-row.min.js", pageFunction);

    function showRptTree(){
        var rptPersonId = "";
        var rptPersonName = "";
        swal({
                    title: '<h4 class="modal-title"><p>日志发送人</p></h4>',
                    text: '<div class="modal-body no-padding"><div class="custom-scroll table-responsive" style="height:250px; overflow-y: auto;"><div id="rptTree" style="text-align: left;"></div></div></div>',
                    html: true,
                    allowOutsideClick:true
                },
                function(){
                    $('#rptPersonId').val(rptPersonId);
                    $('#rptPersonName').val(rptPersonName);
                    //不加这段代码，在点击其他弹出框的时候会导致organTree里的内容显示，
                    // 加了之后消除organTree里的内容时弹出框正常显示
                    swal("","","success");

                });
        loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
        loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", rptTreeSweet);
        function rptTreeSweet() {
            $.ajax({
                type: "GET",
                url: '/organization/queryAlltree.do',
                data: {},
                success: function (data) {
                    $('#rptTree').treeview({
                        data: data,
                        multiSelect:true,
                        onNodeSelected: function (event, data) {
                            if(data.icon=="glyphicon glyphicon-tower"){
                                return;
                            }else{
                                var treeFlag = true;
                                for(var i = 0;i<rptPersonId.split(",").length;i++){
                                    if(data.href == rptPersonId.split(",")[i]){
                                        treeFlag = false;
                                    }
                                }
                                if(treeFlag) {
                                    if (rptPersonName == "") {
                                        rptPersonName = data.text;
                                    } else {
                                        rptPersonName += "," + data.text;
                                    }

                                    if (rptPersonId == "") {
                                        rptPersonId = data.href;
                                    } else {
                                        rptPersonId += "," + data.href;
                                    }
                                }
                            }

                        },
                        onNodeUnselected:function(event, data){

                            var treeFlag = true;
                            var rptPersonIds = rptPersonId.split(",");
                            var rptPersonNames = rptPersonName.split(",");
                            rptPersonName ="";
                            rptPersonId = "";
                            for(var i = 0;i<rptPersonIds.length;i++){
                                treeFlag = true;
                                if(data.href == rptPersonIds[i]){
                                    treeFlag = false;
                                }
                                if(treeFlag){
                                    if(rptPersonId == ""){
                                        rptPersonId = rptPersonIds[i];
                                        rptPersonName = rptPersonNames[i];
                                    }else{
                                        rptPersonId += "," + rptPersonIds[i];
                                        rptPersonName += "," + rptPersonNames[i];
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

    //选择模块
   function changeCheck(flag){

       $("#mouldChange").find("button").removeAttr("style");
       $("#mouldChange").find("button").css({ "background-color": "rgba(169, 169, 169, 0.44)",height: "20px",width: "100%"});
       $("#mouldChange").find("button").children("span").removeAttr("style");
       $("#mouldChange").find("button").children("span").css({ "font-size": "10px",color: "#696969"});

       $("#dayReportTable").hide();
       $("#weekReportTable").hide();
       $("#monthReportTable").hide();
       $("#calendarShowId").hide();


        if(flag == "day"){
            $("#dayReport").removeAttr("style");
            $("#dayReport").css({ "background-color": "#76A6E9",height: "20px",width: "100%"});
            $("#dayReport").children().removeAttr("style");
            $("#dayReport").children().css({ "font-size": "10px",color: "white"});
            $("#dayReportTable").show();
            $("#moduleId").text("日报");
            $("#showName").show();
        }
       if(flag == "week"){
           $("#weekReport").removeAttr("style");
           $("#weekReport").css({ "background-color": "#76A6E9",height: "20px",width: "100%"});
           $("#weekReport").children().removeAttr("style");
           $("#weekReport").children().css({ "font-size": "10px",color: "white"});
           $("#weekReportTable").show();
           $("#moduleId").text("周报");
           $("#calendarShowId").hide();
           $("#showName").hide();
           calendarShowFlag = false;
           $("#showName").html('<i class="fa fa-calendar" style="color: #428BCA"><span style="font-size: 10px">展示</span></i>');

       }
       if(flag == "month"){
           $("#monthReport").removeAttr("style");
           $("#monthReport").css({ "background-color": "#76A6E9",height: "20px",width: "100%"});
           $("#monthReport").children().removeAttr("style");
           $("#monthReport").children().css({ "font-size": "10px",color: "white"});
           $("#monthReportTable").show();
           $("#moduleId").text("月报");
           $("#showName").hide();
           calendarShowFlag = false;
           $("#showName").html('<i class="fa fa-calendar" style="color: #428BCA"><span style="font-size: 10px">展示</span></i>');
       }
    }
    var calendarShowFlag = false;
    var initFlag = "0";
    var fullcalendarFunction = function () {
        var hdr = {
            left: 'title', //,today
            center: 'prev, next, today',
            right: 'month, agendaWeek, agenDay' //month, agendaDay,
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

        /* initialize the calendar
         -----------------------------------------------------------------*/

        fullviewcalendar = $('#calendar').fullCalendar({
            header: hdr,
            buttonText: {
                prev: '<i class="fa fa-chevron-left"></i>',
                next: '<i class="fa fa-chevron-right"></i>'
            },
            editable: false,
            defaultView: "month",
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
                                start: data[i].startTime.split(" ")[0],
                                title: "\n\n\n\n\n",
                                backgroundColor:"#8AAD46"
                            });
                        }
                        callback(events);
                        if(initFlag == "0"){
                            $("#calendarShowId").hide();
                            initFlag = "1";
                        }else{
                            $("#calendarShowId").show();
                        }

                    },
                    // 调用出错执行的函数
                    error: function () {

                    }
                });
            }
        });
        var pagedestroy = function(){

            fullviewcalendar.fullCalendar( 'destroy' );
            fullviewcalendar = null;

            $('#external-events > li').off();
            $('#external-events > li').remove();
            $('#calendar-buttons #btn-prev').off();
            $('#calendar-buttons #btn-prev').remove();
            $('#calendar-buttons #btn-next').off();
            $('#calendar-buttons #btn-next').remove();
            $('#calendar-buttons #btn-today').off();
            $('#calendar-buttons #btn-today').remove();

            if (debugState){
                root.console.log("✔ Calendar destroyed");
            }
        }
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
    }
    function calendarShow(){
        if(calendarShowFlag){
            $("#showName").html('<i class="fa fa-calendar" style="color: #428BCA"><span style="font-size: 10px">展示</span></i>');
            $("#calendarShowId").hide();
            $("#dayReportTable").show();
            calendarShowFlag = false;
        }else{
            $("#showName").html('<i class="fa fa-table" style="color: #428BCA"><span style="font-size: 10px">表单</span></i>');
            $("#dayReportTable").hide();
            $("#calendarShowId").show();
            calendarShowFlag = true;
        }
    }

    loadScript("/smartAdmin/js/plugin/moment/moment.min.js", function () {
        loadScript("/smartAdmin/js/plugin/fullcalendar/jquery.fullcalendar.min.js", fullcalendarFunction);
    });

</script>