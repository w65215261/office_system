<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/smartAdmin/css/BootSideMenu.css">
<link rel="stylesheet" href="/smartAdmin/css/normalize.css">
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
<style type="text/css">
    .clear{
        clear: both;
    }
    #point1{
        cursor: pointer;
    }
</style>
<div id="taskSideBar2" style="height:90%;width:650px;margin-top: 80px">
    <p class="alert alert-info" style="background-color: lightgray;display: block;height: 40px">
        <span style="display: block;width: 90%;text-align: center;float: left">日报</span>
    <span class="pull-right" style="float: right;display: block;width: 10%;text-align: right">
      <a href="javascript:void(0);" class="btn btn-xs" onclick="closeTrigger()" style="font-size: 15px;">X</a>
    </span>
    </p>
    <div style="margin-left: 20px">
        <div><span id="personName3" style="font-size: 30px"></span></div>
        <div><span id="date3"></span></div>
    </div>
    <hr/>
    <div  style="margin-left: 20px">
        <input type="hidden" id="reportId3">
        <div><span style="display: inline-block;width: 85px;"><strong>今日完成工作</strong></span><span type="text" id="doneWork3"></span></div>
        <div style="margin-top: 10px"><span style="display: inline-block;width: 85px;"><strong>未完成工作</strong></span><span type="text" id="undoneWork3"></span></div>
        <div style="margin-top: 10px"><span style="display: inline-block;width: 85px;"><strong>需协调工作</strong></span><span type="text" id="teamWork3"></span></div>
        <div style="margin-top: 10px"   ><span style="display: inline-block;width: 85px;"><strong>备注</strong></span><span type="text" id="bz3"></span></div>
    </div>
    <hr/>
    <div >
        <div id="files1"style="border-bottom: solid 1px #dcdcdc;"></div>
    </div>

    <div style="margin-left: 20px">
        <div style="padding-top: 10px">
            <div style="display: block;width: 80%;float: left" id="num1" >已读</div>
            <div style="display: block;width: 20%;float: right" id="flag1"><i  class="fa  fa-arrow-circle-down" id="point1" onclick="showPersonDetails()"></i></div>
            <div  id="showPersonName1" class="clear"></div>
        </div>
    </div>
    <hr/>
    <hr/>
    <div  style="margin-left: 20px;min-height: 140px">
        <div id="replayCount3">暂无评论</div>
    </div>
    <p class="alert alert-info" style="background-color: lightgray;display: block;height: 50px;" >
    <span class="" style="display: block;width: 100%;text-align: left;">
        <input type="text" size="30"  placeholder="请填写评论内容" style="height: 30px;width: 60%;" id="replay3">

        <button type="button"  id="saveReportReceived3" class="btn btn-primary" style="height: 30px;width: 50px;margin-left: 5px;">
            发送
        </button>
         <span id="receivedInfo" style="color: red"></span>
    </span>
    </p>
</div>
<div class="widget-body no-padding" style="margin: 0 auto;min-height: 500px">
    <form class="smart-form" id="reportShow">
        <fieldset>
            <section >
                <div class="row  no-padding">
                    <div class="col-sm-12">
                        <label class="input col-sm-4">
                            <span class=" col-sm-2 no-padding" style="line-height:32px;height: 32px;color: #000000">发送人：</span>
                            <span  class=" col-sm-10  no-padding">
                                 <input type="text" class="text"  id="rptPersonNames" name="rptPersonNames" readonly="true"  placeholder="点击选择发送人" onclick="checkPerson()" value="${rptPersonName}">
                                                <input type="hidden" class="text"  id="rptPersonIds" name="rptPersonIds" readonly="true" value="${rptPersonId}">
                            </span>
                        </label>
                        <label class="col-sm-1" style="padding-left: 5px;width: 4%">
                            <button type="button"  id="seachReceivedWork" class="btn btn-primary" style="height: 32px;width: 50px;">
                                搜索
                            </button>
                        </label>
                    </div>
                    <div class="col-sm-12" style="padding-top: 30px;">
                        <label class="input col-sm-5">
                            <span class=" col-sm-2 no-padding" style="line-height:32px;height: 32px;width: 13.5%;color: #000000">时间：</span>
                            <span  class=" col-sm-3" style="padding-right: 6%;">
                                <input type="text" class="text"  id="startTime" name="startTime" readonly="true"   placeholder="选择开始日期" value="<fmt:formatDate value="${sdate}"  type="date" />">
                            </span>
                            <span  class=" col-sm-3" style="margin-top: 15px;width: 80px;padding-right: 6%;">
                                <hr style="border: 1px solid rgba(169, 169, 169, 0.44);width: 80px;" />
                            </span>
                            <span  class=" col-sm-3 no-padding">
                                <input type="text" class="text"  id="endTime" name="endTime" readonly="true"  placeholder="选择结束日期" value="<fmt:formatDate value="${edate}"  type="date" />">
                            </span>
                        </label>
                    </div>
                    <div class="col-sm-12" style="padding-top: 10px;">
                        <hr style="border: 1px solid rgba(169, 169, 169, 0.44);" />
                    </div>
                </div>
            </section>
        </fieldset>
    </form>

    <c:forEach items="${reports}" var="r">
        <a href="javascript:void(0);" onclick="projectTask('${r.id}')" class="jarvismetro-tile bigger-cubes " style="display: block;width: 24%;min-height: 150px;background-color: #76A6E9">
            <div class="iconbox">
                <div>
                    <div style="float: left;width: 69%;text-align: left"><h6>${r.rptPersonName}</h6></div>
                    <div style="float: left;width: 30%;text-align: right"><h6>日报</h6></div>
                </div>
                <div style="text-align: left;border-top: solid 1px #ffffff" class="clear" >今日完成工作:${r.doneWork}</div>
                <div style="text-align: left">未完成工作:${r.undoneWork}</div>
                <div style="text-align: left">需协调工作:${r.teamWork}</div>
                <div style="text-align: left;height: 20px"></div>
                <div style="text-align: left;height: 20px;">
                    <div style="float: left;width: 60%;text-align: left"><strong><fmt:formatDate value="${r.rptDate}"  type="both" /></strong></div>
                    <div style="float: left;width: 40%;text-align: right"><strong>查看详情</strong></div>
                </div>
            </div>
        </a>
    </c:forEach>
  </div>
<script type="text/javascript">


    pageSetUp();



    var pagefunction = function () {
        $("#showPersonName1").hide();
        $.datepicker.regional["zh-CN"] = { closeText: "关闭", prevText: "&#x3c;上月", nextText: "下月&#x3e;", currentText: "今天", monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"], monthNamesShort: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"], dayNames: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"], dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"], dayNamesMin: ["日", "一", "二", "三", "四", "五", "六"], weekHeader: "周", dateFormat: "yy-mm-dd", firstDay: 1, isRTL: !1, showMonthAfterYear: !0, yearSuffix: "年" }
        $.datepicker.setDefaults($.datepicker.regional["zh-CN"]);
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
            dateFormat: 'yy-mm-dd',
            showOtherMonths: true,
            prevText: '<i class="fa fa-chevron-left"></i>',
            nextText: '<i class="fa fa-chevron-right"></i>',
            onSelect: function (selectedDate) {
                $('#startTime').datepicker('option', 'maxDate', selectedDate);
            }
        });
    };

    loadScript("/smartAdmin/js/plugin/delete-table-row/delete-table-row.min.js", pagefunction);
    loadScript("/smartAdmin/js/plugin/delete-table-row/delete-table-row.min.js", pagefunction);
    //引入遮罩层的jar包
    loadScript("/smartAdmin/js/BootSideMenu.js",triggerInit);
    //遮罩层初始化
    function triggerInit(){
        $('#taskSideBar2').BootSideMenu({side:"right",autoClose:"true"});
    }
    //遮罩层的弹出方法
    function trigger() {
        $(".toggler").trigger("click");
    }
    //关闭遮罩层
    function closeTrigger() {
        $(".toggler").trigger("click");
    }
    $('.summernote').summernote({
        height:90,
        airMode: true
    });
//    function showRptTree1(){
//        var leavePersonId1 = "";
//        var leavePersonName1 = "";
//        swal({
//                    title: '<h4 class="modal-title"><p>选择发送人</p></h4>',
//                    text: '<div class="modal-body no-padding"><div class="custom-scroll table-responsive" style="height:250px; overflow-y: auto;"><div id="organTree1" style="text-align: left;"></div></div></div>',
//                    html: true,
//                    allowOutsideClick:true
//                },
//                function(){
//                    $('#leavePersonId1').val(leavePersonId1);
//                    $('#leavePersonName1').val(leavePersonName1);
//                    //不加这段代码，在点击其他弹出框的时候会导致organTree里的内容显示，
//                    // 加了之后消除organTree里的内容时弹出框正常显示
//                    swal("","","success");
//
//                });
//        loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
//        loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTreeSweet);
//        function renderTreeSweet() {
//            $.ajax({
//                type: "GET",
//                url: '/organization/queryAlltree.do',
//                data: {},
//                success: function (data) {
//                    $('#organTree1').treeview({
//                        data: data,
//                        multiSelect:true,
//                        onNodeSelected: function (event, data) {
//                            if(data.icon=="glyphicon glyphicon-tower"){
//                                return;
//                            }else{
//                                var treeFlag = true;
//                                for(var i = 0;i<leavePersonId1.split(",").length;i++){
//                                    if(data.href == leavePersonId1.split(",")[i]){
//                                        treeFlag = false;
//                                    }
//                                }
//                                if(treeFlag) {
//                                    if (leavePersonName1 == "") {
//                                        leavePersonName1 = data.text;
//                                    } else {
//                                        leavePersonName1 += "," + data.text;
//                                    }
//
//                                    if (leavePersonId1 == "") {
//                                        leavePersonId1 = data.href;
//                                    } else {
//                                        leavePersonId1 += "," + data.href;
//                                    }
//                                }
//                            }
//
//                        },
//                        onNodeUnselected:function(event, data){
//
//                            var treeFlag = true;
//                            var leavePersonIds1 = leavePersonId1.split(",");
//                            var leavePersonNames1 = leavePersonName1.split(",");
//                            leavePersonName1 ="";
//                            leavePersonId1 = "";
//                            for(var i = 0;i<leavePersonIds1.length;i++){
//                                treeFlag = true;
//                                if(data.href == leavePersonIds1[i]){
//                                    treeFlag = false;
//                                }
//                                if(treeFlag){
//                                    if(leavePersonId1 == ""){
//                                        leavePersonId1 = leavePersonIds1[i];
//                                        leavePersonName1 = leavePersonNames1[i];
//                                    }else{
//                                        leavePersonId1 += "," + leavePersonIds1[i];
//                                        leavePersonName1 += "," + leavePersonNames1[i];
//                                    }
//                                }
//                            }
//                        }
//                    });
//                },
//                // 调用出错执行的函数
//                error: function () {
//                }
//            });
//        }
//    }
    function checkPerson(){
        $('#rptPersonIds').val("");
        $('#rptPersonNames').val("");
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
//                                $('#orgId').val(orgId);
//                                $('#personId').val("");
//                                $('#orgName').val(data.text)
//                                $('#type').val("2");
                            }else{
                                var personId=data.href;
                                $('#rptPersonIds').val(personId);
                                $('#rptPersonNames').val(data.text)
                            }
                        },
                        onNodeUnselected:function(event, data){
                            $('#rptPersonIds').val("");
                            $('#rptPersonNames').val("");
                        }
                    });
                },
                // 调用出错执行的函数
                error: function () {
                }
            });
        }
    }
    function projectTask(oid){
        window.scrollTo( 0, 0 );
        trigger();
        $.ajax({
            type: "GET",
            url: '/summarizeReport/queryDetails.do',
            data: {
                reportId:oid,
                 flag:"receive"
            },
            success: function (data) {
                $("#undoneWork3").html(data.report.undoneWork);
                $("#doneWork3").html(data.report.doneWork);
                $("#teamWork3").html(data.report.teamWork);
                $("#bz3").html(data.report.content);
                $("#personName3").html(data.report.rptPersonName);
                $("#date3").html(data.date);
                $("#reportId3").val(oid);
                if(data.srrys.length>0) {
                    $("#replayCount3").html('<div><span style="font-size: 20px">已有' +data.srrys.length + '人评论</span></div> ');
                    for (var k = 0; k < data.srrys.length; k++) {
                        $("#replayCount3").append(
                                '<div><span><Strong>' + data.srrys[k].replyPersonName + '</Strong></span><span>&nbsp;&nbsp;&nbsp;' + data.srrys[k].replyDate + '</span></div>' +
                                '<div><span>' + data.srrys[k].replyContent + '</span></div>'
                        )
                    }
                }
                if(data.srres.length>0){
                    console.log(data.srres.length);
                    $("#num1").html("已读"+data.srres.length+"人");
                    $("#showPersonName1").html("");
                    for (var k = 0; k < data.srres.length; k++) {
                        if(k==data.srres.length-1){
                            $("#showPersonName1").append(
                                    '<span style="font-size: 20px"><Strong>' + data.srres[k].receivePersonName + '</Strong></span>'
                            )
                        }else{
                            $("#showPersonName1").append(
                                    '<span style="font-size: 20px"><Strong>' + data.srres[k].receivePersonName + ',</Strong></span>'
                            )
                        }
                    }
                }else{
                    console.log(data.srres.length);
                    $("#num").html("已读0人");
                }
                $('#files1').empty();
                console.log("datassssss"+data.sas.length);
                for(var k=0;k<data.sas.length;k++){
                    if(data.sas.length>0){
                        $('#files1').show();
                        $('#files1').append(
                                '<ul class="inbox-download-list col-md-12">'+
                                '<li class="col-md-12">'+
                                '<span class="col-md-6" style="width: 50px;">'+
                                '<i class="fa fa-file-text-o" style="font-size: 40px;"></i>'+
                                '</span>'+
                                '<span class="col-md-6">'+
                                '<strong>'+data.sas[k].fileName+'</strong>'+
                                '</span>'+
                                '<span class="col-md-6">'+
                                '<a href="/'+data.sas[k].fileUrl+'"> 下载</a>'+
                                '</span>'+
                                '</li>'+
                                '</ul>'
                        );
                        document.getElementById("files1").style.height=data.sas.length*50+"px";
                    }else{
                        $('#files1').hide();
                        document.getElementById("files1").style.height=0*50+"px";
                    }

                }
            },
            // 调用出错执行的函数
            error: function () {
            }
        });
    }
    $("#saveReportReceived3").click(function (){
        var replay=$("#replay3").val();
        var uuid=$("#reportId3").val();
        var personName=$("#personName3").html();
        if(replay.length<=0){
            $("#receivedInfo").html("评论内容不能为空");
            return;
        }
        $("#receivedInfo").html("");
        $.ajax({
            type: "POST",
            url: '/summarizeReport/replay.do',
            data:{
                reportId:uuid,
                replyContent:replay
            },
            success: function (data) {

                $.ajax({
                    type: "POST",
                    url: '/summarizeReport/queryDetails.do',
                    data:{
                        reportId:uuid,
                        flag:"receive"
                    },
                    success: function (data) {
                       $("#replay3").val("");
                        if(data.srrys.length>0){
//                            $("#replayCount3").append(
//                                    '<div><span style="font-size: 30px"><Strong>' + personName + '</Strong></span><span>&nbsp;&nbsp;&nbsp;'+data+'</span></div>' +
//                                    '<div><span>' + replay + '</span></div>'
//                            )
//                        }else{
                            $("#replayCount3").html('<div><span style="font-size: 20px">已有' +data.srrys.length + '人评论</span></div> ');
                            for (var k = 0; k < data.srrys.length; k++) {
                                $("#replayCount3").append(
                                        '<div><span><Strong>' + data.srrys[k].replyPersonName + '</Strong></span><span>&nbsp;&nbsp;&nbsp;' + data.srrys[k].replyDate + '</span></div>' +
                                        '<div><span>' + data.srrys[k].replyContent + '</span></div>'
                                )
                            }
                        }
                    },
                    // 调用出错执行的函数
                    error: function () {
                    }
                });

            },
            // 调用出错执行的函数
            error: function () {
            }
        });
    });
    function showPersonDetails(){
        $("#showPersonName1").show();
        $("#flag1").html(' <i  class="fa  fa-arrow-circle-up" id="point" onclick="showPersonDetails1()">');
    }
    function showPersonDetails1(){
        $("#showPersonName1").hide();
        $("#flag1").html(' <i  class="fa  fa-arrow-circle-down" id="point" onclick="showPersonDetails()">');
    }
    $("#seachReceivedWork").click(function () {
        var startTime=$("#startTime").val();
        var endTime=$("#endTime").val();
        var personId=$("#rptPersonIds").val();
        console.log(personId);
        loadURL("/summarizeReport/seachReceivedWork.do?startTime="+startTime+"&endTime="+endTime+"&rptPersonId="+personId,$('#s3'));
    });
</script>
<style>
    #myTabContent1{
        clear: both;
    }

</style>