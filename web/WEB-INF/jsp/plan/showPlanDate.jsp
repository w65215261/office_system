<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="inbox-content" class="inbox-body no-content-padding"  style="min-height: 650px">

    <div class="inbox-side-bar">


        <nav id="inboxmenu">
            <ul class="inbox-menu-sm">
                <li>
                    <a href="#" onclick="" ><i class="fa fa-lg fa-fw fa-bar-chart-o"></i> <span class="menu-item-parent">计划</span></a>

                    <ul>

                        <select id="year" style="margin-left: 80px">
                            <option value="${year-2}" onclick="chooseYear('${year-2}')">${year-2}</option>
                            <option value="${year-1}" onclick="chooseYear('${year-1}')" >${year-1}</option>
                            <option value="${year}" selected="true" onclick="chooseYear('${year}')">${year}</option>
                            <option value="${year+1}" onclick="chooseYear('${year+1}')">${year+1}</option>
                        </select>
                        <li>
                            <a href="#" onclick=planUndetermined() >待定</a>
                        </li>
                        <li>
                            <a href="#" onclick="quarter()" >年/季</a>
                        </li>
                       <c:forEach items="${month}" var="m">
                           <li>
                               <a href="#" onclick=planDetail("${m}") >${m}月</a>
                           </li>
                       </c:forEach>

                    </ul>
                </li>
            </ul>
        </nav>
    </div>


    <div class="table-wrap   animated fast fadeInRight">
        <!-- ajax will fill this area -->
        LOADING...

    </div>
</div>



<script type="text/javascript">
    var closeOpenflag1 = "0";
var chooseChangeFlag = 0;
    pageSetUp();

    // PAGE RELATED SCRIPTS

    // pagefunction

    var pagefunction = function() {

        //初始化本Ajax页面的 nav组件，实现折叠与展开，参考代码app.min.js
        topmenu || $("#inboxmenu ul").jarvismenu({
            "accordion": menu_accordion || !0,
            "speed": menu_speed || !0,
            "closedSign": '<em class="fa fa-plus-circle"></em>',
            "openedSign": '<em class="fa fa-minus-circle"></em>'
        })
        /*
         * LOAD INBOX MESSAGES
         */
        loadInbox();
        function loadInbox() {
            //loadURL("/smartAdmin/ajax/email/email.html", $('#inbox-content > .table-wrap'))
            loadURL("/plan/showAddPlan.do?flag=0", $('#inbox-content > .table-wrap'))
        }

        /*
         * Buttons (compose mail and inbox load)
         */
        $(".inbox-load").click(function() {
            loadInbox();
        });


    };



    loadScript("/smartAdmin/js/plugin/delete-table-row/delete-table-row.min.js", pagefunction);

        function planDetail(month) {
            chooseChangeFlag=0;
            var year = $("#year").val()
            var week = $("#cWeek").val()
            loadURL("/plan/showAddPlan.do?year="+year+"&month="+month+"&flag=1"+"&week="+week+"&chooseWeek=1", $('#inbox-content > .table-wrap'))
            console.log(month);
            console.log(year);
            console.log(week);
            //loadURL("/plan/showAddPlan.do?year="+year+"&month="+month+"&flag=1", $('#inbox-content > .table-wrap'));
//            $.ajax({
//                type: "GET",
//                url: '/plan/showAddPlan.do?flag=1',
//                data:{
//                    year:year,
//                    month:month,
//                    week:week
//                },
//                success: function (data) {
//                        loadURL("/plan/showAddPlan.do?year="+year+"&month="+month+"&flag=1"+"&week="+week+"&chooseWeek=1", $('#inbox-content > .table-wrap'))
//                },
//                error: function () {
//                }
//            });
        }
    function planUndetermined(){
        chooseChangeFlag=1;
        if(closeOpenflag1 == 1){
            $(".toggler").trigger("click");
            closeOpenflag1 = 0;
        }
        var year=$("#year").val();
       $("#one").html("<table id='user' class='table table-bordered table-striped' style='clear: both' width='900px'>" +
                                    "<tr>" +
                                        "<td>" +
                                               "<div style='width: auto;float: left; 'id='divColor'>" +
                                                       "<div style='width: 60px;text-align: center;float: left' >" +
                                                            "<span class='week'  onclick='chooseUndetermined()'>待定计划</span><input type='hidden' id='timeType' value='4'>" +
                                                         "<input type='hidden' id='chooseYear' value='"+year+"'><input type='hidden' id='showFlag' value='show2'></div>" +
                                               "</div>" +
                                         "</td>" +
                                    "</tr>" +
                                "</table>") ;
        loadURL("/plan/showPlan.do?year="+year+"&planTimeType=4",$('#s1'));
    }
function chooseUndetermined(){
    var year=$("#year").val();
    $("#Undetermined").attr("value","4");
    if(closeOpenflag1 == 1){
        $(".toggler").trigger("click");
        closeOpenflag1 = 0;
    }
    loadURL("/plan/showPlan.do?year="+year+"&planTimeType=4",$('#s1'));
}
    function chooseYear(chooseYear) {
        $("#chooseYear").attr("value", chooseYear);
        if (chooseChangeFlag == 0) {
            var year = $("#year").val();
            var month = $("#month").val();
            var planTimeType = $("#timeType").val();
            var week = $("#cWeek").val();
            loadURL("/plan/showPlan.do?year=" + year + "&month=" + month + "&flag=1" + "&week=" + week + "&chooseWeek=0" + "&planTimeType=" + planTimeType, $('#s1'));
        } else if (chooseChangeFlag == 1) {
            var year = $("#year").val();
            loadURL("/plan/showPlan.do?year=" + year + "&planTimeType=4", $('#s1'));
        } else if (chooseChangeFlag == 2) {
            var year = $("#year").val();
            loadURL("/plan/showPlan.do?year=" + year + "&planTimeType=3", $('#s1'));
        }
        else if (chooseChangeFlag == 3) {
            var year = $("#year").val();
            var qua=$("#quarter").val();
            loadURL("/plan/showPlan.do?year=" + year + "&planTimeType=2&qua="+qua, $('#s1'));
        }else if (chooseChangeFlag == 4) {
            var year = $("#year").val();
            var qua=$("#quarter").val();
            var month=$("#month").val();
            loadURL("/plan/showPlan.do?year=" + year + "&planTimeType=1&month="+month, $('#s1'));
        }
    }
    function quarter(){
        var year=$("#year").val();
        chooseChangeFlag=2;
        loadURL("/plan/showAddPlanQuater.do?year="+year+"&planTimeType=3", $('#inbox-content > .table-wrap'));
    }
    function chooseQuarter(number) {
//
        chooseChangeFlag=3;
        $("#quarter").attr("value", number);
        $("#timeType").attr("value", "2");
        var qua=$("#quarter").val();
        var year=$("#year").val();
        $("#showFlag").val("show4");
////        for (var i = 1; i <5; i++) {
////            if (number == i) {
////                document.getElementById("c"+i).style.color = "red";
////            } else {
////                document.getElementById("c"+i).style.color = "black";
////                document.getElementById("c"+0).style.color = "black";
////            }
            loadURL("/plan/showAddPlanQuaterFlag.do?year="+year+"&qua="+qua+"&planTimeType=2",$('#inbox-content > .table-wrap'));
////        }
    }
//    function chooseYearD(){
//        $("#timeType").attr("value","3");
//        $("#quarter").attr("value","");
//        $("#showFlag").val("show3");
//        var year=$("#year").val();
//
//        document.getElementById("c"+0).style.color = "red";
//        document.getElementById("c"+1).style.color = "black";
//        document.getElementById("c"+2).style.color = "black";
//        document.getElementById("c"+3).style.color = "black";
//        document.getElementById("c"+4).style.color = "black";
////        loadURL("/plan/showAddPlanQuater.do?year="+year+"&planTimeType=3",$('#inbox-content > .table-wrap'));
////        loadURL("/plan/showAddPlanQuater.do?year="+year+"&planTimeType=3", $('#inbox-content > .table-wrap'));
//    }
</script>



