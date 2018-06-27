<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
<div class="row">
    <div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false">
        <ul class="nav nav-tabs bordered" id="myTab" style="background-color: #f0efee">
            <li class="active">
                <a data-toggle="tab" href="#s1" onclick="showDay()">&nbsp;&nbsp;&nbsp;日报&nbsp;&nbsp;&nbsp;</a>

            </li>

            <li>
            <a data-toggle="tab" href="#s2" onclick="showWeek()">&nbsp;&nbsp;&nbsp;周报&nbsp;&nbsp;&nbsp;</a>
            </li>
            <li>
                <input type="hidden" value="0" id="flag">
            </li>

            <%--<li>--%>
                <%--<a data-toggle="tab" href="#s3" onclick="showMonth()">&nbsp;&nbsp;&nbsp;月报&nbsp;&nbsp;&nbsp;</a>--%>
            <%--</li>--%>
        </ul>
        <div class="widget-body" style="border: none">
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade active in padding-10 no-padding-bottom" id="s1">
                    <section id="widget-grid" class="">
                        <div class="row">
                            <article class="col-sm-12 col-md-12 col-lg-8">
                                <div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false">
                                    <header style="border: none">
                                        <h2 style="font-size: large">我的日报</h2>
                                    </header>
                                    <div style="border: none;height: 30px"></div>
                                    <div style="height:200px;border: none">
                                        <div style="border: none">
                                            <header style="border: none">
                                                <h> 个人提交情况</h>
                                            </header>
                                            <div style="border: none;width: 30px;height: 100px;float: left"></div>
                                            <div style=" border:dotted 1px ;height: 100px;width: auto" id="show_1">
                                                <div style="border: none;height: 20px"></div>
                                                <div style="border: none">
                                                    <c:forEach items="${list}" var="list">
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
                                                <c:if test="${nullList==0}">
                                                    <div  style="border: none">
                                                        <h4><font color="#DB4B68" size="3px"><i class="fa fa-fw fa-times  "></i></font>未提交</h4>
                                                    </div>
                                                    <div style="border: none;height: 10px"></div>
                                                    <h7>日期：${nowTime}</h7>
                                                </c:if>
                                            </div>
                                            <div style="border: none;height: 50px"></div>
                                            <div style="border: none">
                                                &nbsp;&nbsp;个人统计：<font color="#8AAD46" size="2px"><i
                                                    class="fa fa-fw fa-square "></i>已交</font>&nbsp;&nbsp;
                                                <font color="#DB4B68" size="2px"><i class="fa fa-fw fa-square "></i>未交&nbsp;&nbsp;
                                                </font><font color="#3B88AD" size="2px"><i
                                                    class="fa fa-fw fa-square "></i>补交</font>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </article>
                            <%--日历--%>
                            <article class="col-sm-12 col-md-12 col-lg-4">
                                <div class="jarviswidget" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-editbutton="false">
                                    <div>
                                        <div class="widget-body no-padding">
                                            <div class="widget-body-toolbar">
                                                <div id="calendar-buttons">
                                                    <div class="btn-group">
                                                        <a onclick="findOtherMonth(0)" href="javascript:void(0)" class="btn btn-default btn-xs" id="btn-prev"><i class="fa fa-chevron-left"></i></a>
                                                        <a onclick="findOtherMonth(1)" href="javascript:void(0)" class="btn btn-default btn-xs" id="btn-next"><i class="fa fa-chevron-right"></i></a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="calendar"></div>
                                        </div>
                                    </div>
                                </div>
                            </article>
                        </div>
                    </section>
                    <div class="row" id="tab_close">
                        <input type="hidden" style="border: none;font-size: large" id="belongsDate" name="belongsDate" value="${cDate}">
                        <c:if test="${nullList==0}">
                        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false"
                                 data-widget-fullscreenbutton="false" data-widget-colorbutton="false"
                                 data-widget-deletebutton="false">
                                <header style="border: none">
                                    <h2 style="font-size: large"> 填写日报 </h2>
                                    <span>
                                        <input style="float: right;clear:right;background-color: yellowgreen"  class="btn" type="button" value="生成日报" onclick="createReport()">
                                    </span>
                                </header>
                                <div style="border: none;height: 5px"></div>
                                <div style="border: dotted 1px;border-radius: 4px" >
                                    <form id="login-form" class="smart-form">
                                        <fieldset>
                                            <section id="autoShow">
                                                <label class="textarea">
                                                    <textarea  rows="3" id="contents" placeholder="填写内容" name="content"/>
                                                </label>
                                            </section>
                                        </fieldset>
                                        <footer>
                                        <span><i class="fa fa-fw fa-unlock-alt"></i>
                                            <select id="visiblity" name="visiblity" style="border: none">
                                                <option value="0">公开</option>
                                                <option value="1">私密</option>
                                            </select>
                                        </span>
                                            <button type="button" onclick="saveReport()" class="btn" style="background-color: yellowgreen">发布</button>
                                        </footer>
                                    </form>
                                </div>
                            </div>
                        </article>
                        </c:if>
                        <c:forEach items="${list}" var="list">
                                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false"
                                         data-widget-fullscreenbutton="false" data-widget-colorbutton="false"
                                         data-widget-deletebutton="false" style="border: none">
                                        <header style="border: none">
                                            <h2 style="font-size: large"> 已写日报 </h2>
                                                <span><input style="float: right;background-color: yellowgreen" class="btn" type="button" value="修改" onclick="findReport()"></span>
                                            <input type="hidden" id="fid" name="id" value="${list.id}">
                                        </header>
                                    </div>
                                    <div style="border: none;height: 150px;float: left;width: 30px"></div>
                                    <div style="border: none">
                                        <h3 style="font-size: large">
                                                ${list.rptPersonName}&nbsp;&nbsp;的日报
                                            <c:if test="${list.visiblity==0}">
                                                <span class="btn btn-default disabled" style="float: right"> 可见性:公开</span>
                                            </c:if>
                                            <c:if test="${list.visiblity==1}">
                                                <span class="btn btn-default disabled" style="float: right"> 可见性:私密</span>
                                            </c:if>
                                        </h3>
                                    </div>
                                    <div style="height:100px;border: none">
                                        <div style="border: none">
                                            <header style="border: none">
                                                <h4><font color="blue">日报</font>
                                                    <span>&nbsp;&nbsp;<fmt:formatDate value="${list.belongsDate}" pattern="yyyy年MM月dd日"/>
                                                        <c:if test="${list.week==0}"> 星期日日报</c:if>
                                                        <c:if test="${list.week==1}"> 星期一日报</c:if>
                                                        <c:if test="${list.week==2}"> 星期二日报</c:if>
                                                        <c:if test="${list.week==3}"> 星期三日报</c:if>
                                                        <c:if test="${list.week==4}"> 星期四日报</c:if>
                                                        <c:if test="${list.week==5}"> 星期五日报</c:if>
                                                        <c:if test="${list.week==6}"> 星期六日报</c:if>
                                                       &nbsp;</span>
                                                    <c:if test="${list.status==0}">
                                                    <h><font  size="2px" color="#8AAD46"><i class="fa fa-fw fa-check  "></i>按时提交</font></h>
                                                    </c:if>
                                                    <c:if test="${list.status==1}">
                                                    <h><font  size="2px" color="#3B88AD"><i class="fa fa-fw fa-check"></i>逾期提交</font></h>
                                                    </c:if></h4>
                                            </header>
                                            <div style="border: none;height: 20px"></div>
                                            <pre  style="height: auto;width: auto;border: none;font-size: 14px;background-color:#FFFFFF;white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word">${list.content}</pre>
                                        </div>
                                    </div>
                                </article>
                        </c:forEach>
                    </div>
                </div>

                <div class="tab-pane fade" id="s2">
                    <div class="row no-space">
                    </div>
                </div>

                <%--<div class="tab-pane fade" id="s3">--%>
                    <%--<div class="row no-space">--%>
                    <%--</div>--%>
                <%--</div>--%>
            </div>
        </div>
    </div>
</div>

<%--点击修改侧边天除的div--%>
<div id="reportSideBar" style="height:80%;width: 500px;align-items: baseline">
    <p class="alert alert-info" style="background-color: #f0efee">
    <span class="semi-bold">
      <a href="javascript:void(0);" class="btn btn-xs" onclick="closeTrigger()"><font color="black" size="2"><i
              class="fa  fa-times "></i></font></a>
    </span>
    </p>

    <div style="float: left;border: none;height: 500px;width: 20px"></div>
    <div class="jarviswidget" style="border: none">
        <div class="widget-body no-padding" id="findShow" style="border: none">
        </div>
    </div>
</div>




<script type="text/javascript">

    pageSetUp();

    var pagefunction = function() {
    }

    //保存日报
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

    //生成日报createReport
    function createReport() {
       var belongsDate=$("#belongsDate").val();
        if(belongsDate==''){
            var belongsDate=CurentTime();
        }
        $.ajax({
            type: "POST",
            url: '/projectTask/findTaskForReport.do?reportDate='+belongsDate+'',
            success: function (data) {
                $("#autoShow").html(
                        '<label class="textarea">'+
                        '<textarea  rows="3" id="contents" placeholder="填写内容" name="content"/>'+
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
                        $("#contents").append(
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

    //保存日报
    function saveReport() {
        var date = CurentTime();
        var belongsDate=$("#belongsDate").val();
        if(belongsDate==''){
            var belongsDate=CurentTime();
        }
        var contents = $('#contents').val();
        var visiblity = $('#visiblity').val();
        if ($.trim($("#contents").val()) == '')
        {
            swal("","请输入内容","warning");
            return;
        }
        if(belongsDate>date){
            swal("","所填日报超出当前时间","warning");
            return;
        }
        if(contents!=null){
            $.ajax({
                type: "POST",
                url: '/report/save.do',
                data: {
                    rptDate: date,
                    type: 0,
                    content: contents,
                    visiblity: visiblity,
                    belongsDate: belongsDate
                },
                success: function (data) {
                    if (data.length>0) {
                       $("#tab_close").empty();
                        $.ajax({
                            type: "GET",
                            url: '/report/query.do',//保存后直接刷新出日报
                            data: {
                                type:0,
                                belongsDate: belongsDate
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
                                         if(dataObj[i].belongsDate!=null){
                                        var belongsDates=moment(dataObj[i].belongsDate).format('YYYY年MM月DD日')
                                         }
                                         if(dataObj[i].rptDate!=null){
                                        var rptDate=moment(dataObj[i].rptDate).format('YYYY年MM月DD日')
                                         }
                                        if(dataObj[i].week==0){
                                            var week='星期日'
                                        }
                                        if(dataObj[i].week==1){
                                            var week='星期一'
                                        }
                                        if(dataObj[i].week==2){
                                            var week='星期二'
                                        }
                                        if(dataObj[i].week==3){
                                            var week='星期三'
                                        }
                                        if(dataObj[i].week==4){
                                            var week='星期四'
                                        }
                                        if(dataObj[i].week==5){
                                            var week='星期五'
                                        }
                                        if(dataObj[i].week==6){
                                            var week='星期六'
                                        }
                                        $("#tab_close").html(
                                                '<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">' +
                                                '<div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false"data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false" style="border: none">' +
                                                '<header style="border: none">' +
                                                '<h2 style="font-size: large"> 已写日报 </h2>' +
                                                '<input type="hidden" style="border: none;font-size: large" id="belongsDate" name="belongsDate" value='+dataObj[i].belongsDate+'>' +
                                                "<span><input style='float: right;background-color: yellowgreen' class='btn' type='button' value='修改' onclick='findReport(\"" + dataObj[i].id + "\")' ></span>" +
                                                '</header>' +
                                                '</div>' + '<div style="border: none;height: 150px;float: left;width: 30px">' + '</div>' +
                                                '<header style="border: none">' +
                                                '<h3 style="font-size: large">' + dataObj[i].rptPersonName + '&nbsp;&nbsp;的日报 <span class="btn btn-default disabled" style="float: right">可见性: '+dataObj[i].visiblity1+ '</span></h3>' +
                                                '</header>' +
                                                '<div style="height:100px;border: none">' +
                                                '<div style="border: none">' +
                                                '<header style="border: none">' +
                                                '<h4><font color="blue" >日报</font><span>&nbsp;&nbsp; ' +belongsDates+ '&nbsp;' +week + '日报&nbsp;&nbsp;&nbsp;</span>'+dataObj[i].status1+ '</h4>' +
                                                '</header>' +
                                                '<div style="border: none;height: 20px"></div>' +
                                                '<pre  style="height: auto;width: auto;border: none;font-size: 14px;background-color:#FFFFFF;white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word">' + dataObj[i].content + '</pre>' +
                                                '</article>'
                                        );
                                        $("#show_1").html(
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
    function findReport(id) {//弹出侧拉遮罩层,加载数据
        trigger();
        if(id==null){
            var id=$("#fid").val();
        }
        $.ajax({
            type: "POST",
            url: '/report/findById.do',
            data: {
                id: id
            },
            dataType: "json",
            success: function (data) {
                var radioId=data.visiblity;
                if(data.belongsDate!=null){
                    var belongsDates=moment(data.belongsDate).format('YYYY年MM月DD日')
                    var rptDate=moment(data.rptDate).format('YYYY年MM月DD日')
                }
                if(data.week==0){
                    var week='星期日'
                }
                if(data.week==1){
                    var week='星期一'
                }
                if(data.week==2){
                    var week='星期二'
                }
                if(data.week==3){
                    var week='星期三'
                }
                if(data.week==4){
                    var week='星期四'
                }
                if(data.week==5){
                    var week='星期五'
                }
                if(data.week==6){
                    var week='星期六'
                }
                $("#findShow").html(
                        '<form id="update-form"  class="smart-form" >' +
                        '<header>' +belongsDates + '&nbsp;' +week + '日报</header>' +
                        "<input type='hidden' value='\"" + data.id + "\"' id='ids' name='id'>" +
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
                        '内容：<textarea rows="5" id="content1" name="content" >' + data.content + '</textarea>' +
                        '</label>' +
                        '</section>' +
                        '<section>' +
                        '可见性：<div  class="inline-group">' +
                        '<label class="radio">' +
                        '<input type="radio" id="radioId1" name="visiblity" value="0">' +
                        '<i></i>公开</label>' +
                        '<label class="radio">' +
                        '<input type="radio"id="radioId2" name="visiblity" value="1">' +
                        '<i></i>私密</label>' +
                        '</div>' +
                        '</section>' +
                        '</fieldset>' +
                        '<footer>' +
                        "<button type='button' style='background-color: yellowgreen' onclick='updateReport(\"" + data.id + "\")' class='btn'>发布</button>" +
                        '</footer>' +
                        '</form>'
                );
                if(radioId==0){
                    $("#radioId1").attr("checked",'checked');
                    $("#radioId2").removeAttr("checked");

                }
                if(radioId==1){
                    $("#radioId2").attr("checked","checked");
                    $("#radioId1").removeAttr("checked");
                }
            },
            // 调用出错执行的函数
            error: function () {
            }
        });
    }
    ;

    //修改日报
    function updateReport(id) {
        var content1 = $('#content1').val();
        var selectR = $('input:radio:checked');
        var visiblity1 = selectR.val();
        if ($.trim($("#content1").val()) == '')
        {
            swal("","请输入内容","warning");
            return;
        }
        $.ajax({
            type: "POST",
            url: '/report/update.do',
            data: {
                id: id,
                content: content1,
                visiblity: visiblity1
            },
            success: function (data) {
                closeTrigger();
                $.ajax({
                    type: "POST",
                    url: '/report/findById.do',
                    data: {
                        id: id
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

                                if(data.belongsDate!=null){
                                    var belongsDates=moment(data.belongsDate).format('YYYY年MM月DD日')
                                }
                                if(data.rptDate!=null){
                                    var rptDate=moment(data.rptDate).format('YYYY年MM月DD日')
                                }
                                if(data.week==0){
                                    var week='星期日'
                                }
                                if(data.week==1){
                                    var week='星期一'
                                }
                                if(data.week==2){
                                    var week='星期二'
                                }
                                if(data.week==3){
                                    var week='星期三'
                                }
                                if(data.week==4){
                                    var week='星期四'
                                }
                                if(data.week==5){
                                    var week='星期五'
                                }
                                if(data.week==6){
                                    var week='星期六'
                                }
                                $("#tab_close").html(
                                        '<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">' +
                                        '<div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false"data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false" style="border: none">' +
                                        '<header style="border: none">' +
                                        '<h2 style="font-size: large"> 已写日报 </h2>' +
                                        '<input type="hidden" style="border: none;font-size: large" id="belongsDate" name="belongsDate" value='+data.belongsDate+'>' +
                                        "<span><input style='float: right;background-color: yellowgreen' class='btn' type='button' value='修改' onclick='findReport(\"" + data.id + "\")' ></span>" +
                                        '</header>' +
                                        '</div>' + '<div style="border: none;height: 150px;float: left;width: 30px">' + '</div>' +
                                        '<header style="border: none">' +
                                        '<h3 style="font-size: large">' +data.rptPersonName + '&nbsp;&nbsp;的日报 <span class="btn btn-default disabled" style="float: right">可见性: ' + data.visiblity1 + '</span></h3>' +
                                        '</header>' +
                                        '<div style="height:100px;border: none">' +
                                        '<div style="border: none">' +
                                        '<header style="border: none">' +
                                        '<h4><font color="blue" >日报</font><span>&nbsp;&nbsp; ' +belongsDates + '&nbsp;' + week + '日报&nbsp;&nbsp;&nbsp;</span>' + data.status1 + '</h4>' +
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
    loadScript("/smartAdmin/js/BootSideMenu.js", triggerInit);
    function trigger() {
        $(".toggler").trigger("click");
    }
    function triggerInit() {
        $('#reportSideBar').BootSideMenu({side: "right", autoClose: "true"});
    }
    function closeTrigger() {
        $(".toggler").trigger("click");
    }
    loadScript("/smartAdmin/js/plugin/moment/moment.min.js", function () {
        loadScript("/smartAdmin/js/plugin/fullcalendar/jquery.fullcalendar.min.js", countCalendar);
    });
    //日历颜色显示
    function countCalendar() {
        var date = CurentTime();
        $.ajax({
            type: "POST",
           url: '/report/color.do',
            data: {
                type:0,
                rptDate: date
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
                            $('#belongsDate').val(date.format());
                            var belongsDate = date.format() + ' ' + "00:00";
                            var belongsDates1=moment(belongsDate).format("YYYY年MM月DD日");
                            $.ajax({
                                type: "GET",
                                url: '/report/queryForDay.do',
                                data: {
                                    type: 0,
                                    belongsDate: belongsDate
                                },
                                dataType: "text",
                                success:function(data){
                                    if(data.length>0){
                                var dataText = eval("(" + data + ")");
                                for (var i = 0; i < dataText.length; i++) {
                                    if (dataText[i].visiblity == 0) {
                                        dataText[i].visiblity1 = '公开';
                                    } else if (dataText[i].visiblity == 1) {
                                        dataText[i].visiblity1 = '私密';
                                    }
                                    if (dataText[i].status == 0) {
                                        dataText[i].status1 = '<font size="2px" color="#8AAD46"><i class="fa fa-fw fa-check"></i>按时提交</font>';
                                    } else if (dataText[i].status == 1) {
                                        dataText[i].status1 = '<font size="2px" color="#3B88AD"><i class="fa fa-fw fa-check"></i>逾期提交</font>';
                                    }
                                    if (dataText[i].status == 0) {
                                        dataText[i].status2 = '<font size="4px" color="#8AAD46"><i class="fa fa-fw fa-check"></i>按时提交</font>';
                                    } else if (dataText[i].status == 1) {
                                        dataText[i].status2 = '<font size="4px" color="#3B88AD"><i class="fa fa-fw fa-check"></i>逾期提交</font>';
                                    }
                                    if(dataText[i].belongsDate!=null){
                                        var belongsDates=moment(dataText[i].belongsDate).format('YYYY年MM月DD日')
                                    }
                                    if(dataText[i].rptDate!=null){
                                        var rptDate=moment(dataText[i].rptDate).format('YYYY年MM月DD日')
                                    }
                                    if(dataText[i].week==0){
                                        var week='星期日'
                                    }
                                    if(dataText[i].week==1){
                                        var week='星期一'
                                    }
                                    if(dataText[i].week==2){
                                        var week='星期二'
                                    }
                                    if(dataText[i].week==3){
                                        var week='星期三'
                                    }
                                    if(dataText[i].week==4){
                                        var week='星期四'
                                    }
                                    if(dataText[i].week==5){
                                        var week='星期五'
                                    }
                                    if(dataText[i].week==6){
                                        var week='星期六'
                                    }
                                    $("#tab_close").html(
                                            '<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">' +
                                            '<div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false"data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false" style="border: none">' +
                                            '<header style="border: none">' +
                                            '<h2 style="font-size: large"> 已写日报 </h2>' +
                                            '<input type="hidden" style="border: none;font-size: large" id="belongsDate" name="belongsDate" value='+dataText[i].belongsDate+'>' +
                                            "<span><input style='float: right;background-color: yellowgreen' class='btn' type='button' value='修改' onclick='findReport(\"" + dataText[i].id + "\")' ></span>" +
                                            '</header>' +
                                            '</div>' + '<div style="border: none;height: 150px;float: left;width: 30px">' + '</div>' +
                                            '<header style="border: none">' +
                                            '<h3 style="font-size: large">' +dataText[i].rptPersonName + '&nbsp;&nbsp;的日报 <span class="btn btn-default disabled" style="float: right">可见性: ' + dataText[i].visiblity1 + '</span></h3>' +
                                            '</header>' +
                                            '<div style="height:100px;border: none">' +
                                            '<div style="border: none">' +
                                            '<header style="border: none">' +
                                            '<h4><font color="blue" >日报</font><span>&nbsp;&nbsp; ' +belongsDates + '&nbsp;' + week + '日报&nbsp;&nbsp;&nbsp;</span>' + dataText[i].status1 + '</h4>' +
                                            '</header>' +
                                            '<div style="border: none;height: 20px"></div>' +
                                            '<pre  style="height: auto;width: auto;border: none;font-size: 14px;background-color:#FFFFFF;white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word">' + dataText[i].content + '</pre>' +
                                            '</article>'
                                    );
                                    $("#show_1").html(
                                            '<div style="border: none;height: 20px"></div>' +
                                            '<div style="border: none">' +
                                            '<h4>' + dataText[i].status2 + '</h4>' +
                                            '<div style="border: none;height: 10px"></div>' +
                                            '<h>提交日期：' +rptDate + '</h>' +
                                            '</div>'
                                    );
                                }
                            }else{
                                        $("#tab_close").html(
                                                '<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> <div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false"data-widget-fullscreenbutton="false" data-widget-colorbutton="false"data-widget-deletebutton="false">' +
                                                '<header style="border: none"> <h2 style="font-size: large"> 填写日报 </h2> ' +
                                                '<span> <input style="float: right;clear:right;background-color: yellowgreen"  class="btn" type="button" value="生成日报" onclick="createReport()"> </span>'+
                                                '</header>' +
                                                '<div style="border: none;height: 5px"></div>' +
                                                '<div style="border: dotted 1px;border-radius: 4px">' +
                                                '<form id="login-form" class="smart-form">' +
                                                '<fieldset>' +
                                                '<input type="hidden" style="border: none;font-size: large"id="belongsDate"  name="belongsDate" value='+belongsDate+'>' +
                                                '<section id="autoShow">'+
                                                '<label class="textarea">' +
                                                '<textarea rows="3" id="contents" placeholder="填写内容" name="content"/>' +
                                                '</section>'+
                                                '</fieldset>' +
                                                '<footer>' +
                                                '<span><i class="fa fa-fw fa-unlock-alt"></i>' +
                                                '<select id="visiblity" name="visiblity" style="border: none">' +
                                                '<option value="0">公开</option>' +
                                                '<option value="1">私密</option>' +
                                                '</select>' +
                                                '</span>' +
                                                '<button type="button" style="background-color: yellowgreen" onclick="saveReport()" class="btn">发布</button>' +
                                                '</footer>' +
                                                '</form>' +
                                                '</div>' +
                                                '</article>'

                                );
                                $("#show_1").html(
                                        '<div style="border: none;height: 20px"></div>'+
                                        '<div style="border: none">'+
                                        '<h4><font color="#DB4B68" size="3px"><i class="fa fa-fw fa-times  "></i></font>未提交</h4>'+
                                        '</div>'+
                                        '<div style="border: none;height: 10px"></div>'+
                                        '<h7>日期：'+belongsDates1+'</h7>'
                                );
                            }
                        }
                            });
                        },
                        dayClick: function (date, allDay, jsEvent, view) {
                            date.stripTime();
                            $('#belongsDate').val(date.format());
                            var belongsDate = date.format() + ' ' + "00:00";
                            var dates = CurentTime();
                            var belongsDates=moment(belongsDate).format("YYYY-MM-DD");
                            var belongsDates1=moment(belongsDate).format("YYYY年MM月DD日");
                            if(belongsDate<dates||belongsDates==dates){
                            $.ajax({
                                type: "GET",
                                url: '/report/queryForDay.do',
                                data: {
                                    type: 0,
                                    belongsDate: belongsDate
                                },
                                dataType: "text",
                                success: function (data) {
                                    if(data.length>0){
                                        var dataText = eval("(" + data + ")");
                                        for (var i = 0; i < dataText.length; i++) {
                                            if (dataText[i].visiblity == 0) {
                                                dataText[i].visiblity1 = '公开';
                                            } else if (dataText[i].visiblity == 1) {
                                                dataText[i].visiblity1 = '私密';
                                            }
                                            if (dataText[i].status == 0) {
                                                dataText[i].status1 = '<font size="2px" color="#8AAD46"><i class="fa fa-fw fa-check"></i>按时提交</font>';
                                            } else if (dataText[i].status == 1) {
                                                dataText[i].status1 = '<font size="2px" color="#3B88AD"><i class="fa fa-fw fa-check"></i>逾期提交</font>';
                                            }
                                            if (dataText[i].status == 0) {
                                                dataText[i].status2 = '<font size="4px" color="#8AAD46"><i class="fa fa-fw fa-check"></i>按时提交</font>';
                                            } else if (dataText[i].status == 1) {
                                                dataText[i].status2 = '<font size="4px" color="#3B88AD"><i class="fa fa-fw fa-check"></i>逾期提交</font>';
                                            }
                                            if(dataText[i].belongsDate!=null){
                                                var belongsDates=moment(dataText[i].belongsDate).format('YYYY年MM月DD日')
                                            }
                                            if(dataText[i].rptDate!=null){
                                                var rptDate=moment(dataText[i].rptDate).format('YYYY年MM月DD日')
                                            }
                                            if(dataText[i].week==0){
                                                var week='星期日'
                                            }
                                            if(dataText[i].week==1){
                                                var week='星期一'
                                            }
                                            if(dataText[i].week==2){
                                                var week='星期二'
                                            }
                                            if(dataText[i].week==3){
                                                var week='星期三'
                                            }
                                            if(dataText[i].week==4){
                                                var week='星期四'
                                            }
                                            if(dataText[i].week==5){
                                                var week='星期五'
                                            }
                                            if(dataText[i].week==6){
                                                var week='星期六'
                                            }
                                            $("#tab_close").html(
                                                    '<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">' +
                                                    '<div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false"data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false" style="border: none">' +
                                                    '<header style="border: none">' +
                                                    '<h2 style="font-size: large"> 已写日报 </h2>' +
                                                    '<input type="hidden" style="border: none;font-size: large" id="belongsDate" name="belongsDate" value='+dataText[i].belongsDate+'>' +
                                                    "<span><input style='float: right;background-color: yellowgreen' class='btn' type='button' value='修改' onclick='findReport(\"" + dataText[i].id + "\")' ></span>" +
                                                    '</header>' +
                                                    '</div>' + '<div style="border: none;height: 150px;float: left;width: 30px">' + '</div>' +
                                                    '<header style="border: none">' +
                                                    '<h3 style="font-size: large">' +dataText[i].rptPersonName + '&nbsp;&nbsp;的日报 <span class="btn btn-default disabled" style="float: right">可见性: ' + dataText[i].visiblity1 + '</span></h3>' +
                                                    '</header>' +
                                                    '<div style="height:100px;border: none">' +
                                                    '<div style="border: none">' +
                                                    '<header style="border: none">' +
                                                    '<h4><font color="blue" >日报</font><span>&nbsp;&nbsp; ' +belongsDates+ '&nbsp;' + week + '日报&nbsp;&nbsp;&nbsp;</span>' + dataText[i].status1 + '</h4>' +
                                                    '</header>' +
                                                    '<div style="border: none;height: 20px"></div>' +
                                                    '<pre  style="height: auto;width: auto;border: none;font-size: 14px;background-color:#FFFFFF;white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word">' + dataText[i].content + '</pre>' +
                                                    '</article>'
                                            );
                                            $("#show_1").html(
                                                    '<div style="border: none;height: 20px"></div>' +
                                                    '<div style="border: none">' +
                                                    '<h4>' + dataText[i].status2 + '</h4>' +
                                                    '<div style="border: none;height: 10px"></div>' +
                                                    '<h>提交日期：' +rptDate + '</h>' +
                                                    '</div>'
                                            );
                                        }
                                    }else{
                                        $("#tab_close").html(
                                                '<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> <div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false"data-widget-fullscreenbutton="false" data-widget-colorbutton="false"data-widget-deletebutton="false">'+
                                                '<header style="border: none"> <h2 style="font-size: large"> 填写日报 </h2> ' +
                                                '<span> <input style="float: right;clear:right;background-color: yellowgreen"  class="btn" type="button" value="生成日报" onclick="createReport()"> </span>'+
                                                '</header>'+
                                                '<div style="border: none;height: 5px"></div>'+
                                                '<div style="border: dotted 1px;border-radius: 4px" id="reportForm">'+
                                                '<form id="login-form" class="smart-form">'+
                                                '<fieldset>' +
                                                '<input type="hidden" style="border: none;font-size: large"id="belongsDate" name="belongsDate" value='+belongsDate+'>'+
                                                '<section id="autoShow">'+
                                                '<label class="textarea">'+
                                                '<textarea rows="3" id="contents" placeholder="填写内容" name="content"/>'+
                                                '</label>'+
                                                '</section>'+
                                                '</fieldset>'+
                                                '<footer>'+
                                                '<span><i class="fa fa-fw fa-unlock-alt"></i>'+
                                                '<select id="visiblity" name="visiblity" style="border: none">'+
                                                '<option value="0">公开</option>'+
                                                '<option value="1">私密</option>'+
                                                '</select>'+
                                                '</span>'+
                                                '<button type="button" style="background-color: yellowgreen" onclick="saveReport()" class="btn">发布</button>'+
                                                '</footer>'+
                                                '</form>'+
                                                '</div>'+
                                                '</article>'
                                        );
                                        $("#show_1").html(
                                                '<div style="border: none;height: 20px"></div>'+
                                                '<div style="border: none">'+
                                                '<h4><font color="#DB4B68" size="3px"><i class="fa fa-fw fa-times  "></i></font>未提交</h4>'+
                                                '</div>'+
                                                '<div style="border: none;height: 10px"></div>'+
                                                '<h7>日期：'+belongsDates1+'</h7>'
                                        );
                                    }
                                }
                            })
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

    function findOtherMonth(sign){
        var date=$("#belongsDate").val();
        if(date==''){
            var date = CurentTime();
        }
        $.ajax({
            type: "GET",
            url: '/report/queryForMonth.do?belongsDate='+date+'&sign='+sign+'',
            data: {
                type: 0
            },
            success:function(data) {
                if(data.list.length>0){
                    for(var i = 0; i < data.list.length; i++ ){
                        if (data.list[i].visiblity == 0) {
                            data.list[i].visiblity1 = '公开';
                        } else if (data.list[i].visiblity == 1) {
                            data.list[i].visiblity1 = '私密';
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
                        if(data.list[i].belongsDate!=null){
                            var belongsDates=moment(data.list[i].belongsDate).format('YYYY年MM月DD日')
                        }
                        if(data.list[i].rptDate!=null){
                            var rptDate=moment(data.list[i].rptDate).format('YYYY年MM月DD日')
                        }
                        if(data.list[i].week==0){
                            var week='星期日'
                        }
                        if(data.list[i].week==1){
                            var week='星期一'
                        }
                        if(data.list[i].week==2){
                            var week='星期二'
                        }
                        if(data.list[i].week==3){
                            var week='星期三'
                        }
                        if(data.list[i].week==4){
                            var week='星期四'
                        }
                        if(data.list[i].week==5){
                            var week='星期五'
                        }
                        if(data.list[i].week==6){
                            var week='星期六'
                        }
                        $("#tab_close").html(
                                '<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">' +
                                '<div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false"data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false" style="border: none">' +
                                '<header style="border: none">' +
                                '<h2 style="font-size: large"> 已写日报 </h2>' +
                                '<input type="hidden" style="border: none;font-size: large" id="belongsDate" name="belongsDate" value='+data.belongsDate+'>' +
                                "<span><input style='float: right;background-color: yellowgreen' class='btn' type='button' value='修改' onclick='findReport(\"" + data.list[i].id + "\")' ></span>" +
                                '</header>' +
                                '</div>' + '<div style="border: none;height: 150px;float: left;width: 30px">' + '</div>' +
                                '<header style="border: none">' +
                                '<h3 style="font-size: large">' +data.list[i].rptPersonName + '&nbsp;&nbsp;的日报 <span class="btn btn-default disabled" style="float: right">可见性: ' + data.list[i].visiblity1 + '</span></h3>' +
                                '</header>' +
                                '<div style="height:100px;border: none">' +
                                '<div style="border: none">' +
                                '<header style="border: none">' +
                                '<h4><font color="blue" >日报</font><span>&nbsp;&nbsp; ' +belongsDates + '&nbsp;' + week + '日报&nbsp;&nbsp;&nbsp;</span>' + data.list[i].status1 + '</h4>' +
                                '</header>' +
                                '<div style="border: none;height: 20px"></div>' +
                                '<pre  style="height: auto;width: auto;border: none;font-size: 14px;background-color:#FFFFFF;white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word">' + data.list[i].content + '</pre>' +
                                '</article>'
                        );
                        $("#show_1").html(
                                '<div style="border: none;height: 20px"></div>' +
                                '<div style="border: none">' +
                                '<h4>' + data.list[i].status2 + '</h4>' +
                                '<div style="border: none;height: 10px"></div>' +
                                '<h>提交日期：' +rptDate + '</h>' +
                                '</div>'
                        );
                    }
                }else{
                    $("#tab_close").html(
                            '<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> <div class="jarviswidget" data-widget-togglebutton="false" data-widget-editbutton="false"data-widget-fullscreenbutton="false" data-widget-colorbutton="false"data-widget-deletebutton="false">' +
                            '<header style="border: none"> <h2 style="font-size: large"> 填写日报 </h2> ' +
                            '<span> <input style="float: right;clear:right;background-color: yellowgreen"  class="btn" type="button" value="生成日报" onclick="createReport()"> </span>'+
                            '</header>' +
                            '<div style="border: none;height: 5px"></div>' +
                            '<div style="border: dotted 1px;border-radius: 4px">' +
                            '<form id="login-form" class="smart-form">' +
                            '<fieldset>' +
                            '<input type="hidden" style="border: none;font-size: large" id="belongsDate" name="belongsDate" value='+data.belongsDate+'>' +
                            '<section id="autoShow">'+
                            '<label class="textarea">' +
                            '<textarea rows="3" id="contents" placeholder="填写内容" name="content"/>' +
                            '</section>'+
                            '</fieldset>' +
                            '<footer>' +
                            '<span><i class="fa fa-fw fa-unlock-alt"></i>' +
                            '<select id="visiblity" name="visiblity" style="border: none">' +
                            '<option value="0">公开</option>' +
                            '<option value="1">私密</option>' +
                            '</select>' +
                            '</span>' +
                            '<button type="button" style="background-color: yellowgreen" onclick="saveReport()" class="btn">发布</button>' +
                            '</footer>' +
                            '</form>' +
                            '</div>' +
                            '</article>'

                    );
                    $("#show_1").html(
                            '<div style="border: none;height: 20px"></div>'+
                            '<div style="border: none">'+
                            '<h4><font color="#DB4B68" size="3px"><i class="fa fa-fw fa-times  "></i></font>未提交</h4>'+
                            '</div>'+
                            '<div style="border: none;height: 10px"></div>'+
                            '<h7>日期：'+data.sDate1 +'</h7>'
                    );
                }
            }
        })
    }

   // 点击tab加载日报,周报,月报
    function showDay() {//日报
        $('#s2').empty();
        $('#flag').val("0");
        loadURL('/report/queryFor.do?type=' + 0 + '',$('#inbox-content > .table-wrap'));
    }
    function showWeek() {//周报
        $('#s1').empty();
        $('#flag').val("1");
        //加载周报会使遮罩层冲突,可以试着指控#s1
        loadURL('/report/showWeek.do?type=' + 1 + '', $('#s2'));
    }
</script>