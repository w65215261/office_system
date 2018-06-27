<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
        <ul class="nav nav-tabs bordered"  style="border: none"></ul>
        <div class="widget-body" style="border: none">
            <div id="myTabContent" class="tab-content">
                <input type="hidden" id="nowWeek" value="${nowWeek}">
                <div class="tab-pane fade active in padding-10 no-padding-bottom">
                    <section id="widget-grid" class="">
                        <div class="row">
                            <article class="col-sm-12 col-md-12 col-lg-9">
                                <div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false">
                                    <header style="border: none">
                                        <h2 style="font-size: large">我的周报</h2>
                                    </header>
                                    <div style="border: none;height: 30px"></div>
                                    <div style="height:200px;border: none">
                                        <div style="border: none">
                                            <header style="border: none">
                                                <h> 个人提交情况</h>
                                            </header>
                                            <div style="border: none;width: 30px;height: 100px;float: left"></div>
                                            <div style=" border:dotted 1px ;height: 100px;width: auto" id="show_week">
                                                <div style="border: none;height: 20px"></div>
                                                <div style="border: none">
                                                    <c:forEach items="${weekList}" var="list">
                                                        <c:if test="${list.status==0}">
                                                            <h4><font  size="4px" color="#8AAD46"><i class="fa fa-fw fa-check  "></i>按时提交</font></h4>
                                                        </c:if>
                                                        <c:if test="${list.status==1}">
                                                            <h4><font  size="4px" color="#3B88AD"><i class="fa fa-fw fa-check"></i>逾期提交</font></h4>
                                                        </c:if>
                                                        <div style="border: none;height: 10px"></div>
                                                        <h7>提交日期：<fmt:formatDate value="${list.rptDate}" pattern="yyyy年MM月dd日"/></h7>
                                                    </c:forEach>
                                                </div>
                                                <c:if test="${nullWeekList==0}">
                                                    <div  style="border: none">
                                                        <h4><font color="#DB4B68" size="3px"><i class="fa fa-fw fa-times  "></i></font>未提交</h4>
                                                    </div>
                                                    <div style="border: none;height: 10px"></div>
                                                    <h7>日期：<fmt:formatDate value="${title}" pattern="yyyy年MM月"/>第${weekNum}周</h7>
                                                </c:if>
                                            </div>
                                            <div style="border: none;height: 50px"></div>
                                            <div style="border: none">
                                                &nbsp;&nbsp;个人统计：
                                                <font color="#8AAD46" size="2px"><i class="fa fa-fw fa-square "></i>已交</font>&nbsp;&nbsp;
                                                <font color="#ffc0cb" size="2px"><i class="fa fa-fw fa-square "></i>未交</font>&nbsp;&nbsp;
                                                <font color="#3B88AD" size="2px"><i class="fa fa-fw fa-square "></i>补交</font>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </article>
                            <%--日历--%>
                            <article class="col-sm-12 col-md-12 col-lg-3">
                                <div class="jarviswidget" >
                                    <div id="weekViewBreak">
                                        <div class="widget-body no-padding">
                                            <div class="widget-body-toolbar">
                                                <div style="text-align: left;border: none;height: 25px;padding-top: 4px;font-weight: bold;font-size: 14px">
                                                        <fmt:formatDate value="${title}" pattern="yyyy年MM月"/>
                                                </div>
                                                <div id="calendar-buttons">
                                                    <div class="btn-group">
                                                        <a onclick="nextMonth(1)"  class="btn btn-default btn-xs"><i class="fa fa-chevron-left" ></i>
                                                           <input type="hidden" value="<fmt:formatDate value="${title}" pattern="yyyy-MM"/>" id="weekView">
                                                        </a>
                                                        <a onclick="nextMonth(0)" class="btn btn-default btn-xs"><i class="fa fa-chevron-right"></i>
                                                            <input type="hidden" value="<fmt:formatDate value="${title}" pattern="yyyy-MM"/>" id="weekViews">
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                            <c:forEach items="${ms}" var="ms" varStatus="t">
                                            <div style="text-align: center"  id="weekAppend">
                                                <div style="border: none;height: 5px"></div>
                                                <input  id="${t.count}" type="button" style="width:98%;height:30px;border: none ;font-size: 15px;text-align: center;border-radius: 5px;" value="${ms}" onclick="refeshWeek(${t.count})">
                                                <div style="border: none;height: 5px"></div>
                                            </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </article>
                        </div>
                    </section>
                    <div class="row" id="tab_close_week">
                        <c:if test="${nullWeekList==0}">
                            <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false"
                                     data-widget-fullscreenbutton="false" data-widget-colorbutton="false"
                                     data-widget-deletebutton="false">
                                    <header style="border: none">
                                        <h2 style="font-size: large"> 填写周报 </h2>
                                    <span>
                                        <input style="float: right;clear:right;background-color: yellowgreen"  class="btn" type="button" value="生成周报" onclick="createWeekReport()">
                                    </span>
                                    </header>
                                    <div style="border: none;height: 5px"></div>
                                    <div style="border: dotted 1px;border-radius: 4px" >
                                        <form id="login-form" class="smart-form">
                                            <input type="hidden" value="${title}"  id="saveWeek">
                                            <input type="hidden" value="${weekNum}" id="weekOfNum">
                                            <fieldset>
                                                <section id="autoShow">
                                                    <label class="textarea">
                                                        <textarea  rows="3" id="contents1" placeholder="填写内容" name="content"/>
                                                    </label>
                                                </section>
                                            </fieldset>
                                            <footer>
                                        <span><i class="fa fa-fw fa-unlock-alt"></i>
                                            <select id="visiblity1" name="visiblity" style="border: none">
                                                <option value="0">公开</option>
                                                <option value="1">私密</option>
                                            </select>
                                        </span>
                                                <button type="button" style="background-color: yellowgreen" onclick="saveWeekReport()" class="btn">发布</button>
                                            </footer>
                                        </form>
                                    </div>
                                </div>
                            </article>
                        </c:if>
                        <c:forEach items="${weekList}" var="week">
                            <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false"
                                     data-widget-fullscreenbutton="false" data-widget-colorbutton="false"
                                     data-widget-deletebutton="false" style="border: none">
                                    <header style="border: none">
                                        <h2 style="font-size: large"> 已写周报 </h2>
                                        <span><input style="float: right;background-color: yellowgreen" class="btn" type="button" value="修改" onclick="findWeekReport()"></span>
                                        <input type="hidden" id="weekId" name="id" value="${week.id}">
                                    </header>
                                </div>
                                <div style="border: none;height: 150px;float: left;width: 30px"></div>
                                <div style="border: none">
                                    <h3 style="font-size: large">
                                            ${week.rptPersonName}&nbsp;&nbsp;的周报
                                        <c:if test="${week.visiblity==0}">
                                            <span class="btn btn-default disabled" style="float: right"> 可见性:公开</span>
                                        </c:if>
                                        <c:if test="${week.visiblity==1}">
                                            <span class="btn btn-default disabled" style="float: right"> 可见性:私密</span>
                                        </c:if>
                                    </h3>
                                </div>
                                <div style="height:100px;border: none">
                                    <div style="border: none">
                                        <header style="border: none">
                                            <h4><font color="blue">周报</font>
                                                    <span>&nbsp;&nbsp;<fmt:formatDate value="${title}" pattern="yyyy年MM月"/>
                                                        <c:if test="${week.weekNum==1}"> 第一周周报</c:if>
                                                        <c:if test="${week.weekNum==2}"> 第二周周报</c:if>
                                                        <c:if test="${week.weekNum==3}"> 第三周周报</c:if>
                                                        <c:if test="${week.weekNum==4}"> 第四周周报</c:if>
                                                        <c:if test="${week.weekNum==5}"> 第五周周报</c:if>
                                                        <c:if test="${week.weekNum==6}"> 第六周周报</c:if>
                                                       &nbsp;</span>
                                                <c:if test="${week.status==0}">
                                                    <h><font  size="2px" color="#8AAD46"><i class="fa fa-fw fa-check  "></i>按时提交</font></h>
                                                </c:if>
                                                <c:if test="${week.status==1}">
                                                    <h><font  size="2px" color="#3B88AD"><i class="fa fa-fw fa-check"></i>逾期提交</font></h>
                                                </c:if></h4>
                                        </header>
                                        <div style="border: none;height: 20px"></div>
                                        <pre  style="height: auto;width: auto;border: none;font-size: 14px;background-color:#FFFFFF;white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word">${week.content}</pre>
                                    </div>
                                </div>
                            </article>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>


<%--点击修改侧边天除的div--%>
<div id="reportSideBarWeek" style="height:80%;width: 500px;align-items: baseline">
    <p class="alert alert-info" style="background-color: #f0efee">
    <span class="semi-bold">
      <a href="javascript:void(0);" class="btn btn-xs" onclick="weekTriggers()"><font color="black" size="2"><i
              class="fa  fa-times "></i></font></a>
    </span>
    </p>

    <div style="float: left;border: none;height: 500px;width: 20px"></div>
    <div class="jarviswidget" style="border: none">
        <div class="widget-body no-padding" id="findShowWeek" style="border: none">
        </div>
    </div>
</div>




<script type="text/javascript">

    pageSetUp();

    var pagefunction = function() {
        var time=$("#weekView").val();
        $.ajax({
            type: "POST",
            url: '/report/showWeekViewColor.do?nowTime='+time+'&sign='+2+'',
            success:function(data){
                var nowWeek = data.weekNum;
                var nowDates = moment(CurentTime()).format("YYYY年MM月");
                var resultDate = data.weekDate;
                if (nowDates == resultDate) {
                    for(var i=0;i<nowWeek;i++) {
                        s=parseInt(i)+1;
                        idColor=s;
                        var sui = data.list;
                        if (sui[i] == 1) {
                            $("#"+idColor).css("background-color","#3B88AD");
                        } else if (sui[i] == 0) {
                            $("#"+idColor).css("background-color","#8AAD46");
                        }else if(sui[i]==2){
                            $("#"+idColor).css("background-color","pink");
                        }
                    }
                }else if(nowDates > resultDate){
                    for(var i=0;i<data.list.length;i++) {
                        s=parseInt(i)+1;
                        idColor=s;
                        var sui = data.list;
                        if (sui[i] == 1) {
                            $("#"+idColor).css("background-color","#3B88AD");
                        } else if (sui[i] == 0) {
                            $("#"+idColor).css("background-color","#8AAD46");
                        }else if(sui[i]==2){
                            $("#"+idColor).css("background-color","pink");
                        }
                    }
                }else if(nowDates<resultDate){
                    for(var i=0;i<data.list.length;i++) {
                        s=parseInt(i)+1;
                        idColor=s;
                        var sui = data.list;
                        if (sui[i] == 1) {
                            $("#"+idColor).css("background-color","");
                        } else if (sui[i] == 0) {
                            $("#"+idColor).css("background-color","");
                        }else if(sui[i]==2){
                            $("#"+idColor).css("background-color","");
                        }
                    }
                }
            }
        })
    }
    loadScript("/smartAdmin/js/plugin/delete-table-row/delete-table-row.min.js", pagefunction);

    function nextMonth(sign){
        var time= $("#weekView").val();
        $.ajax({
            type: "POST",
            url: '/report/showWeekView.do?nowTime='+time+'&sign='+sign+'',
            success:function(data){
                $("#weekViewBreak").empty();
                $("#weekAppend").empty();
                if(data.title!=null){
                    var title=moment(data.title).format("YYYY年MM月");
                }
                if(data.title!=null){
                    var title1=moment(data.title).format("YYYY-MM");
                }
                $("#weekViewBreak").html(
                        '<div class="widget-body no-padding">'+
                        '<div class="widget-body-toolbar">'+
                        '<div style="text-align: left;border: none;height: 25px;padding-top: 4px;font-weight: bold;font-size: 14px">'+
                        ''+title+''+
                        '</div>'+
                        '<div id="calendar-buttons">'+
                        '<div class="btn-group">'+
                        '<a onclick="nextMonth(1)"  class="btn btn-default btn-xs"><i class="fa fa-chevron-left" ></i>'+
                        '<input type="hidden" value="'+title1+'" id="weekView">'+
                        '</a>'+
                        '<a onclick="nextMonth(0)" class="btn btn-default btn-xs"><i class="fa fa-chevron-right"></i>'+
                        '<input type="hidden" value="'+title1+'" id="weekViews">'+
                        '</a>'+
                        '</div>'+
                        '</div>'+
                        '</div>'+
                        '<div   id="weekAppend"></div>'+
                        '</div>'
                );
                if(data.allWeek!=null){
                    var weekNum=data.allWeek;
                    for(var index in weekNum){
                        var s=index;
                        s=parseInt(s)+1;
                        idColor=s+'color';
                        var sWeek=weekNum[index];
                        $("#weekAppend").append(
                                '<div style="text-align: center">'+
                                '<div style="border: none;height: 5px"></div>'+
                                '<input id='+idColor+' type="button" style="width:98%;height:30px;border: none;font-size: 15px;text-align: center;border-radius: 5px" value="'+sWeek+'" onclick="refeshWeek('+s+')"/>'+
                                '<div style="border: none;height: 5px"></div>'+
                                '</div>'
                        );
                    }
                }
                $.ajax({
                    type: "POST",
                    url: '/report/showWeekViewColor.do?nowTime='+time+'&sign='+sign+'',
                    success:function(data) {
                        var nowWeek = data.weekNum;
                        var nowDates = moment(CurentTime()).format("YYYY年MM月");
                        var resultDate = data.weekDate;
                        if (nowDates == resultDate) {
                            for (var i = 0; i < nowWeek; i++) {
                                s = parseInt(i) + 1;
                                idColor = s + 'color';
                                var sui = data.list;
                                if (sui[i] == 1) {
                                    $("#" + idColor).css("background-color", "#3B88AD");
                                } else if (sui[i] == 0) {
                                    $("#" + idColor).css("background-color", "#8AAD46");
                                } else if (sui[i] == 2) {
                                    $("#" + idColor).css("background-color", "pink");
                                }
                            }
                        } else if (nowDates < resultDate) {
                            for (var i = 0; i < data.list.length; i++) {
                                s = parseInt(i) + 1;
                                idColor = s + 'color';
                                var sui = data.list;
                                if (sui[i] == 1) {
                                    $("#" + idColor).css("background-color", "");
                                } else if (sui[i] == 0) {
                                    $("#" + idColor).css("background-color", "");
                                } else if (sui[i] == 2) {
                                    $("#" + idColor).css("background-color", "");
                                }
                            }
                        } else if (nowDates > resultDate) {
                            for (var i = 0; i < data.list.length; i++) {
                                s = parseInt(i) + 1;
                                idColor = s + 'color';
                                var sui = data.list;
                                if (sui[i] == 1) {
                                    $("#" + idColor).css("background-color", "#3B88AD");
                                } else if (sui[i] == 0) {
                                    $("#" + idColor).css("background-color", "#8AAD46");
                                } else if (sui[i] == 2) {
                                    $("#" + idColor).css("background-color", "pink");
                                }
                            }
                        }
                    }
                })
            }
        });
        $.ajax({
            type:"POST",
            url: '/report/nextMonth.do?nowTime='+time+'&sign='+sign+'',
            data:{
                type:1,
                weekNum:1
            },
            success:function(data){
                    if(data.list.length>0){
                        for (var i = 0; i < data.list.length; i++) {
                            if(data.list[i].visiblity == 0){
                                data.list[i].visiblity1='公开';
                            }else if(data.list[i].visiblity == 1){
                                data.list[i].visiblity1='私密';
                            }
                            if (data.list[i].status == 0) {
                                data.list[i].status1 = '<font size="2px" color="#8AAD46"><i class="fa fa-fw fa-check"></i>按时提交</font>';
                            } else if (data.list[i].status == 1) {
                                data.list[i].status1 = '<font size="2px" color="#3B88AD"><i class="fa fa-fw fa-check"></i>逾期提交</font>';
                            }
                            if (data.list[i].status == 0) {
                                data.list[i].status2 = '<font size="4px" color="#8AAD46"><i class="fa fa-fw fa-check"></i>按时提交</font>';
                            } else if (data.list[i].status == 1) {
                                data.list[i].status2 = '<font size="4px" color="#3B88AD"><i class="fa fa-fw fa-check"></i>逾期提交</font>';
                            }
                            if(data.list[i].rptDate!=null){
                                var rptDate=moment(data.list[i].rptDate).format('YYYY年MM月DD日')
                            }
                            if(data.list[i].weekNum==1){
                                var week='第一周'
                            }
                            if(data.list[i].weekNum==2){
                                var week='第二周'
                            }
                            if(data.list[i].weekNum==3){
                                var week='第三周'
                            }
                            if(data.list[i].weekNum==4){
                                var week='第四周'
                            }
                            if(data.list[i].weekNum==5){
                                var week='第五周'
                            }
                            if(data.list[i].weekNum==6){
                                var week='第六周'
                            }
                            $("#tab_close_week").html(
                                    '<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">' +
                                    '<div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false"data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false" style="border: none">' +
                                    '<header style="border: none">' +
                                    '<h2 style="font-size: large"> 已写周报 </h2>' +
                                    "<span><input style='float: right;background-color: yellowgreen' class='btn' type='button' value='修改' onclick='findWeekReport(\"" + data.list[i].id + "\")' ></span>" +
                                    '</header>' +
                                    '</div>' + '<div style="border: none;height: 150px;float: left;width: 30px">' + '</div>' +
                                    '<header style="border: none">' +
                                    '<h3 style="font-size: large">' + data.list[i].rptPersonName + '&nbsp;&nbsp;的周报 <span class="btn btn-default disabled" style="float: right">可见性: '+data.list[i].visiblity1+ '</span></h3>' +
                                    '</header>' +
                                    '<div style="height:100px;border: none">' +
                                    '<div style="border: none">' +
                                    '<header style="border: none">' +
                                    '<h4><font color="blue" >周报</font><span>&nbsp;&nbsp; ' +data.list[i].weekDate+ '&nbsp;' +week + '周报&nbsp;&nbsp;&nbsp;</span>'+data.list[i].status1+ '</h4>' +
                                    '</header>' +
                                    '<div style="border: none;height: 20px"></div>' +
                                    '<pre  style="height: auto;width: auto;border: none;font-size: 14px;background-color:#FFFFFF;white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word">' + data.list[i].content + '</pre>' +
                                    '</article>'
                            );
                            $("#show_week").html(
                                    '<div style="border: none;height: 20px"></div>' +
                                    '<div style="border: none">' +
                                    '<h4>'+data.list[i].status2+ '</h4>' +
                                    '<div style="border: none;height: 10px"></div>' +
                                    '<h>提交日期：' +rptDate+ '</h>' +
                                    '</div>'
                            );
                        }
                    }else{
                        $("#tab_close_week").html(
                                '<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> <div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false"data-widget-fullscreenbutton="false" data-widget-colorbutton="false"data-widget-deletebutton="false">'+
                                '<header style="border: none;background-color:#FAFAFA"> <h2 style="font-size: large"> 填写周报 </h2> ' +
                                '<span> <input style="float: right;clear:right;background-color: yellowgreen"  class="btn" type="button" value="生成周报" onclick="createWeekReport()"> </span>'+
                                '</header>'+
                                '<div style="border: none;height: 5px"></div>'+
                                '<div style="border: dotted 1px;border-radius: 4px">'+
                                '<form id="login-form" class="smart-form">'+
                                '<fieldset>' +
                                '<input type="hidden" value="'+data.weekDate+'"  id="saveWeek"> <input type="hidden" value="'+1+'" id="weekOfNum">'+
                                '<section id="autoShow">'+
                                '<label class="textarea">'+
                                '<textarea rows="3" id="contents1" placeholder="填写内容" name="content"/>'+
                                '</label>'+
                                '</section>'+
                                '</fieldset>'+
                                '<footer>'+
                                '<span><i class="fa fa-fw fa-unlock-alt"></i>'+
                                '<select id="visiblity1" name="visiblity" style="border: none">'+
                                '<option value="0">公开</option>'+
                                '<option value="1">私密</option>'+
                                '</select>'+
                                '</span>'+
                                '<button type="button" style="background-color: yellowgreen" onclick="saveWeekReport()" class="btn">发布</button>'+
                                '</footer>'+
                                '</form>'+
                                '</div>'+
                                '</article>'
                        );
                        $("#show_week").html(
                                '<div style="border: none;height: 20px"></div>'+
                                '<div style="border: none">'+
                                '<h4><font color="#DB4B68" size="3px"><i class="fa fa-fw fa-times  "></i></font>未提交</h4>'+
                                '</div>'+
                                '<div style="border: none;height: 10px"></div>'+
                                '<h7>日期：'+data.weekDates+'第'+1+'周</h7>'
                        );
                    }
                }
        });
    }
    //生成周报createWeekReport
    function createWeekReport() {
        var weekDate=moment($("#saveWeek").val()).format('YYYY-MM');
        var weekNum=$("#weekOfNum").val();
        $.ajax({
            type: "POST",
            url: '/projectTask/findTaskForWeek.do?weekDate='+weekDate+'&weekNum='+weekNum+'',
            success: function (data) {
                $("#autoShow").html(
                        '<label class="textarea">'+
                        '<textarea  rows="3" id="contents1" placeholder="填写内容" name="content"/>'+
                        '</label>'
                );
                if(data.length>0){
                    for (var i = 0; i < data.length; i++) {
                        if(data[i].taskStatus == 0){
                            data[i].taskStatus='未开始';
                        }else if(data[i].taskStatus == 1){
                            data[i].taskStatus='暂停中';
                        }else if(data[i].taskStatus == 2){
                            data[i].taskStatus='已延迟';
                        }else if(data[i].taskStatus == 3){
                            data[i].taskStatus='已取消';
                        }else if(data[i].taskStatus == 4){
                            data[i].taskStatus='进行中';
                        }else if(data[i].taskStatus == 5){
                            data[i].taskStatus='已完成';
                        }
                        $("#contents1").append(
                                '任务：'+data[i].taskName+ '[状态：'+data[i].taskStatus+ ']'+"\n"

                        )
                    }
                }else{
                    swal("","没有检索到当前任务信息,请查看任务详情!","warning");
                    return;
                }
            }
        })
    }
    //保存周报
    function CurentTime() {
        var now = new Date();
        var year = now.getFullYear();       //年
        var month = now.getMonth() + 1;     //月
        var day = now.getDate();            //日
        var clock = year + "-";
        if (month < 10)
            clock += "0";
        clock += month + "-";
        if (day < 10)
            clock += "0";
        clock += day;
        return (clock);
    }


    //保存周报
    function saveWeekReport() {
        var date = CurentTime();
        var weekDate=moment($("#saveWeek").val()).format('YYYY-MM')
        var weekDates=moment($("#saveWeek").val()).format('YYYY年MM月');
        var weekNum=$("#weekOfNum").val();
        var contents = $('#contents1').val();
        var visiblity = $('#visiblity1').val();
        var weekDate1= $("#weekViews").val();
        var stringTime = weekDate + '-0' + weekNum;
        var timestamp2 = Date.parse(new Date(stringTime));//点击得到时间
        var nowWeek = $("#nowWeek").val();
        var timestamp1 = Date.parse(new Date(nowWeek));//系统时间
        if ($.trim($("#contents1").val()) == '')
        {
            swal("","请输入内容","warning");
            return;
        }
        if(timestamp2>timestamp1){
            swal("","所填周报超出当前周","warning");
            return;
        }
        if(contents!=null){
            $.ajax({
                type: "POST",
                url: '/report/saveWeek.do?weekTime='+weekDate+'',
                data: {
                    rptDate: date,
                    type: 1,
                    content: contents,
                    visiblity: visiblity,
                    weekDate: weekDates,
                    weekNum:weekNum
                },
                success: function (data) {
                    if (data.length>0) {
                        $("#tab_close_week").empty();
                        $.ajax({
                            type: "POST",
                            url: '/report/queryWeek.do',//保存后直接刷新出周报
                            data: {
                                type:1,
                                weekNum: weekNum,
                                weekDate: weekDates
                            },
                            dataType: "text",
                            success: function (data) {
                                var dataObj = eval("(" + data + ")");
                                for (var i = 0; i < dataObj.length; i++) {
                                    if(dataObj[i].visiblity == 0){
                                        dataObj[i].visiblity1='公开';
                                    }else if(dataObj[i].visiblity == 1){
                                        dataObj[i].visiblity1='私密';
                                    }
                                    if (dataObj[i].status == 0) {
                                        dataObj[i].status1 = '<font size="2px" color="#8AAD46"><i class="fa fa-fw fa-check"></i>按时提交</font>';
                                    } else if (dataObj[i].status == 1) {
                                        dataObj[i].status1 = '<font size="2px" color="#3B88AD"><i class="fa fa-fw fa-check"></i>逾期提交</font>';
                                    }
                                    if (dataObj[i].status == 0) {
                                        dataObj[i].status2 = '<font size="4px" color="#8AAD46"><i class="fa fa-fw fa-check"></i>按时提交</font>';
                                    } else if (dataObj[i].status == 1) {
                                        dataObj[i].status2 = '<font size="4px" color="#3B88AD"><i class="fa fa-fw fa-check"></i>逾期提交</font>';
                                    }
                                    if(dataObj[i].rptDate!=null){
                                        var rptDate=moment(dataObj[i].rptDate).format('YYYY年MM月DD日')
                                    }
                                    if(dataObj[i].weekNum==1){
                                        var week='第一周'
                                    }
                                    if(dataObj[i].weekNum==2){
                                        var week='第二周'
                                    }
                                    if(dataObj[i].weekNum==3){
                                        var week='第三周'
                                    }
                                    if(dataObj[i].weekNum==4){
                                        var week='第四周'
                                    }
                                    if(dataObj[i].weekNum==5){
                                        var week='第五周'
                                    }
                                    if(dataObj[i].weekNum==6){
                                        var week='第六周'
                                    }
                                    $("#tab_close_week").html(
                                            '<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">' +
                                            '<div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false"data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false" style="border: none">' +
                                            '<header style="border: none">' +
                                            '<h2 style="font-size: large"> 已写周报 </h2>' +
                                            "<span><input style='float: right;background-color: yellowgreen' class='btn' type='button' value='修改' onclick='findWeekReport(\"" + dataObj[i].id + "\")' ></span>" +
                                            '</header>' +
                                            '</div>' + '<div style="border: none;height: 150px;float: left;width: 30px">' + '</div>' +
                                            '<header style="border: none">' +
                                            '<h3 style="font-size: large">' + dataObj[i].rptPersonName + '&nbsp;&nbsp;的周报 <span class="btn btn-default disabled" style="float: right">可见性: '+dataObj[i].visiblity1+ '</span></h3>' +
                                            '</header>' +
                                            '<div style="height:100px;border: none">' +
                                            '<div style="border: none">' +
                                            '<header style="border: none">' +
                                            '<h4><font color="blue" >周报</font><span>&nbsp;&nbsp; ' +dataObj[i].weekDate+ '&nbsp;' +week + '周报&nbsp;&nbsp;&nbsp;</span>'+dataObj[i].status1+ '</h4>' +
                                            '</header>' +
                                            '<div style="border: none;height: 20px"></div>' +
                                            '<pre  style="height: auto;width: auto;border: none;font-size: 14px;background-color:#FFFFFF;white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word">' + dataObj[i].content + '</pre>' +
                                            '</article>'
                                    );
                                    $("#show_week").html(
                                            '<div style="border: none;height: 20px"></div>' +
                                            '<div style="border: none">' +
                                            '<h4>'+dataObj[i].status2+ '</h4>' +
                                            '<div style="border: none;height: 10px"></div>' +
                                            '<h>提交日期：' +rptDate+ '</h>' +
                                            '</div>'
                                    );
                                }
                            }
                        });

                    } else {
                        alert('温馨提示', '失败！');
                    }
                },
                error: function () {
                }
            });
        }
    }
    //修改之前的查找日报
    function findWeekReport(wid) {//弹出侧拉遮罩层,加载数据
        triggersWeek();
        if(wid==null){
            var wid=$("#weekId").val();
        }
        $.ajax({
            type: "POST",
            url: '/report/findById.do',
            data: {
                id: wid
            },
            success: function (data) {
                var radioId=data.visiblity;
                var rptDate=moment(data.rptDate).format('YYYY年MM月DD日')
                if(data.weekNum==1){
                    var week='第一周'
                }
                if(data.weekNum==2){
                    var week='第二周'
                }
                if(data.weekNum==3){
                    var week='第三周'
                }
                if(data.weekNum==4){
                    var week='第四周'
                }
                if(data.weekNum==5){
                    var week='第五周'
                }
                if(data.weekNum==6){
                    var week='第六周'
                }
                $("#findShow").empty();
                $("#findShow").html(
                        '<form  class="smart-form" >' +
                        '<header>' +data.weekDate + '&nbsp;' +week + '周报</header>' +
                        '<fieldset>' +
                        '<div class="row">' +
                        '<section class="col col-6">' +
                        '<label class="input">提交人：&nbsp;&nbsp;  ' + data.rptPersonName + ' </label>' +
                        '</section>' +
                        '</div>' +
                        '<div class="row">' +
                        '<section class="col col-6">' +
                        '<label class="input">提交时间：&nbsp;&nbsp; ' + rptDate + '</label>' +
                        '</section>' +
                        '</div>' +
                        '<section>' +
                        '<label class="textarea">' +
                        '内容：<textarea rows="5" id="content2" name="content" >' + data.content + '</textarea>' +
                        '</label>' +
                        '</section>' +
                        '<section>' +
                        '可见性：<div  class="inline-group">' +
                        '<label class="radio">' +
                        '<input type="radio" id="radioId3" name="visiblity" value="0">' +
                        '<i></i>公开</label>' +
                        '<label class="radio">' +
                        '<input type="radio"id="radioId4" name="visiblity" value="1">' +
                        '<i></i>私密</label>' +
                        '</div>' +
                        '</section>' +
                        '</fieldset>' +
                        '<footer>' +
                        "<button type='button' style='background-color: yellowgreen' onclick='updateWeek(\"" + data.id + "\")' class='btn'>发布</button>" +
                        '</footer>' +
                        '</form>'
                );
                if(radioId==0){
                    $("#radioId3").attr("checked",'checked');
                    $("#radioId4").removeAttr("checked");

                }
                if(radioId==1){
                    $("#radioId3").attr("checked","checked");
                    $("#radioId4").removeAttr("checked");
                }
            },
            // 调用出错执行的函数
            error: function () {
            }
        });
    }
    ;
    //修改周报
    function updateWeek(uid) {
        var content2 = $('#content2').val();
        var selectR = $('input:radio:checked');
        var visiblity2 = selectR.val();
        if ($.trim($("#content2").val()) == '')
        {
            swal("","请输入内容","warning");
            return;
        }
        $.ajax({
            type: "POST",
            url: '/report/update.do',
            data: {
                id: uid,
                content: content2,
                visiblity: visiblity2
            },
            success: function (data) {
                weekTriggers();
                $.ajax({
                    type: "POST",
                    url: '/report/findById.do',
                    data: {
                        id: uid
                    },
                    dataType: "json",
                    success:function(data){
                        if (data.visiblity == 0) {
                            data.visiblity1 = '公开';
                        } else if (data.visiblity == 1) {
                            data.visiblity1 = '私密';
                        }
                        if (data.status == 0) {
                            data.status1 = '<font size="2px" color="#8AAD46"><i class="fa fa-fw fa-check"></i>按时提交</font>';
                        } else if (data.status == 1) {
                            data.status1 = '<font size="2px" color="#3B88AD"><i class="fa fa-fw fa-check"></i>逾期提交</font>';
                        }
                        if(data.rptDate!=null){
                            var rptDate=moment(data.rptDate).format('YYYY年MM月DD日')
                        }
                        if(data.weekNum==1){
                            var week='第一周'
                        }
                        if(data.weekNum==2){
                            var week='第二周'
                        }
                        if(data.weekNum==3){
                            var week='第三周'
                        }
                        if(data.weekNum==4){
                            var week='第四周'
                        }
                        if(data.weekNum==5){
                            var week='第五周'
                        }
                        if(data.weekNum==6){
                            var week='第六周'
                        }
                        $("#tab_close_week").empty();
                        $("#tab_close_week").html(
                                '<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">' +
                                '<div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false"data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false" style="border: none">' +
                                '<header style="border: none">' +
                                '<h2 style="font-size: large"> 已写周报 </h2>' +
                                "<span><input style='float: right;background-color: yellowgreen' class='btn' type='button' value='修改' onclick='findWeekReport(\"" + data.id + "\")' ></span>" +
                                '</header>' +
                                '</div>' + '<div style="border: none;height: 150px;float: left;width: 30px">' + '</div>' +
                                '<header style="border: none">' +
                                '<h3 style="font-size: large">' +data.rptPersonName + '&nbsp;&nbsp;的周报 <span class="btn btn-default disabled" style="float: right">可见性: ' + data.visiblity1 + '</span></h3>' +
                                '</header>' +
                                '<div style="height:100px;border: none">' +
                                '<div style="border: none">' +
                                '<header style="border: none">' +
                                '<h4><font color="blue" >周报</font><span>&nbsp;&nbsp; ' +data.weekDate + '&nbsp;' + week + '周报&nbsp;&nbsp;&nbsp;</span>' + data.status1 + '</h4>' +
                                '</header>' +
                                '<div style="border: none;height: 20px"></div>' +
                                '<pre  style="height: auto;width: auto;border: none;font-size: 14px;background-color:#FFFFFF;white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word">' + data.content + '</pre>' +
                                '</article>'
                        );
                    }
                });
            },
            // 调用出错执行的函数
            error: function () {
            }
        });
    }
    ;

    //侧拉遮罩层的js.
    loadScript("/smartAdmin/js/BootSideMenu.js", weekView);
    function triggersWeek() {
        $(".toggler").trigger("click");
    }
    function weekView() {
        $('#reportSideBarWeek').BootSideMenu({side: "right", autoClose: "true"});
    }
    function weekTriggers() {
        $(".toggler").trigger("click");
    }


    function refeshWeek(sid) {
        var weekDate = $("#weekViews").val();
        var stringTime = weekDate + '-0' + sid;
        var timestamp2 = Date.parse(new Date(stringTime));//点击得到时间
        var nowWeek = $("#nowWeek").val();
        var timestamp1 = Date.parse(new Date(nowWeek));//系统时间
        var weekDates = moment($("#weekViews").val()).format('YYYY年MM月');
        var date = moment(CurentTime()).format('YYYY年MM月');
        if (timestamp1 >= timestamp2) {
        $.ajax({
            type: "POST",
            url: '/report/queryWeek.do',//保存后直接刷新出周报
            data: {
                type: 1,
                weekNum: sid,
                weekDate: weekDates
            },
            dataType: "text",
            success: function (data) {
                if (data.length > 0) {
                    var dataObj = eval("(" + data + ")");
                    for (var i = 0; i < dataObj.length; i++) {
                        if (dataObj[i].visiblity == 0) {
                            dataObj[i].visiblity1 = '公开';
                        } else if (dataObj[i].visiblity == 1) {
                            dataObj[i].visiblity1 = '私密';
                        }
                        if (dataObj[i].status == 0) {
                            dataObj[i].status1 = '<font size="2px" color="#8AAD46"><i class="fa fa-fw fa-check"></i>按时提交</font>';
                        } else if (dataObj[i].status == 1) {
                            dataObj[i].status1 = '<font size="2px" color="#3B88AD"><i class="fa fa-fw fa-check"></i>逾期提交</font>';
                        }
                        if (dataObj[i].status == 0) {
                            dataObj[i].status2 = '<font size="4px" color="#8AAD46"><i class="fa fa-fw fa-check"></i>按时提交</font>';
                        } else if (dataObj[i].status == 1) {
                            dataObj[i].status2 = '<font size="4px" color="#3B88AD"><i class="fa fa-fw fa-check"></i>逾期提交</font>';
                        }
                        if (dataObj[i].rptDate != null) {
                            var rptDate = moment(dataObj[i].rptDate).format('YYYY年MM月DD日')
                        }
                        if (dataObj[i].weekNum == 1) {
                            var week = '第一周'
                        }
                        if (dataObj[i].weekNum == 2) {
                            var week = '第二周'
                        }
                        if (dataObj[i].weekNum == 3) {
                            var week = '第三周'
                        }
                        if (dataObj[i].weekNum == 4) {
                            var week = '第四周'
                        }
                        if (dataObj[i].weekNum == 5) {
                            var week = '第五周'
                        }
                        if (dataObj[i].weekNum == 6) {
                            var week = '第六周'
                        }
                        $("#tab_close_week").html(
                                '<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">' +
                                '<div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false"data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false" style="border: none">' +
                                '<header style="border: none">' +
                                '<h2 style="font-size: large"> 已写周报 </h2>' +
                                "<span><input style='float: right;background-color: yellowgreen' class='btn' type='button' value='修改' onclick='findWeekReport(\"" + dataObj[i].id + "\")' ></span>" +
                                '</header>' +
                                '</div>' + '<div style="border: none;height: 150px;float: left;width: 30px">' + '</div>' +
                                '<header style="border: none">' +
                                '<h3 style="font-size: large">' + dataObj[i].rptPersonName + '&nbsp;&nbsp;的周报 <span class="btn btn-default disabled" style="float: right">可见性: ' + dataObj[i].visiblity1 + '</span></h3>' +
                                '</header>' +
                                '<div style="height:100px;border: none">' +
                                '<div style="border: none">' +
                                '<header style="border: none">' +
                                '<h4><font color="blue" >周报</font><span>&nbsp;&nbsp; ' + dataObj[i].weekDate + '&nbsp;' + week + '周报&nbsp;&nbsp;&nbsp;</span>' + dataObj[i].status1 + '</h4>' +
                                '</header>' +
                                '<div style="border: none;height: 20px"></div>' +
                                '<pre  style="height: auto;width: auto;border: none;font-size: 14px;background-color:#FFFFFF;white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word">' + dataObj[i].content + '</pre>' +
                                '</article>'
                        );
                        $("#show_week").html(
                                '<div style="border: none;height: 20px"></div>' +
                                '<div style="border: none">' +
                                '<h4>' + dataObj[i].status2 + '</h4>' +
                                '<div style="border: none;height: 10px"></div>' +
                                '<h>提交日期：' + rptDate + '</h>' +
                                '</div>'
                        );
                    }
                } else {
                    $("#tab_close_week").html(
                            '<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> <div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false"data-widget-fullscreenbutton="false" data-widget-colorbutton="false"data-widget-deletebutton="false">' +
                            '<header style="border: none;background-color:#FAFAFA"> <h2 style="font-size: large"> 填写周报 </h2> ' +
                            '<span> <input style="float: right;clear:right;background-color: yellowgreen"  class="btn" type="button" value="生成周报" onclick="createWeekReport()"> </span>' +
                            '</header>' +
                            '<div style="border: none;height: 5px"></div>' +
                            '<div style="border: dotted 1px;border-radius: 4px">' +
                            '<form id="login-form" class="smart-form">' +
                            '<fieldset>' +
                            '<input type="hidden" value="' + weekDate + '"  id="saveWeek"> <input type="hidden" value="' + sid + '" id="weekOfNum">' +
                            '<section id="autoShow">' +
                            '<label class="textarea">' +
                            '<textarea rows="3" id="contents1" placeholder="填写内容" name="content"/>' +
                            '</label>' +
                            '</section>' +
                            '</fieldset>' +
                            '<footer>' +
                            '<span><i class="fa fa-fw fa-unlock-alt"></i>' +
                            '<select id="visiblity1" name="visiblity" style="border: none">' +
                            '<option value="0">公开</option>' +
                            '<option value="1">私密</option>' +
                            '</select>' +
                            '</span>' +
                            '<button type="button"style="background-color: yellowgreen" onclick="saveWeekReport()" class="btn">发布</button>' +
                            '</footer>' +
                            '</form>' +
                            '</div>' +
                            '</article>'
                    );
                    $("#show_week").html(
                            '<div style="border: none;height: 20px"></div>'+
                            '<div style="border: none">'+
                            '<h4><font color="#DB4B68" size="3px"><i class="fa fa-fw fa-times  "></i></font>未提交</h4>'+
                            '</div>'+
                            '<div style="border: none;height: 10px"></div>'+
                            '<h7>日期：'+weekDates+'第'+sid+'周</h7>'
                    );
                }
            }
        })
    }
    }
//    test
</script>
