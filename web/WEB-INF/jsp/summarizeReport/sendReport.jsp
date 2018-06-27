<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/smartAdmin/css/BootSideMenu.css">
<link rel="stylesheet" href="/smartAdmin/css/normalize.css">
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
<script type="text/javascript" src="/EasyUiCompoment/reportAttachment.js"></script>


<style type="text/css">
    #sendLog,#mySend,#myReceived,#logReport{
        cursor: pointer;
    }
</style>

<div class="widget-body no-padding" style="width: 99%;margin: 0 auto;">
    <div id="taskSideBar" style="height:90%;width:650px;margin-top: 80px">
        <p class="alert alert-info" style="background-color: lightgray;display: block;height: 40px">
            <span style="display: block;width: 90%;text-align: center;float: left">日报</span>
            <input type="hidden" id="uuid1">
    <span class="pull-right" style="float: right;display: block;width: 10%;text-align: right">
      <a href="javascript:void(0);" class="btn btn-xs" onclick="closeTrigger()" style="font-size: 15px;">X</a>
    </span>
        </p>
        <div style="margin-left: 20px">
            <div><span id="personName" style="font-size: 30px"></span></div>
            <div><span id="date"></span></div>
        </div>
        <hr/>
        <div  style="margin-left: 20px">
            <div><span style="display: inline-block;width: 85px;"><strong>今日完成工作</strong></span><span type="text" id="doneWork1"></span></div>
            <div style="margin-top: 10px"><span style="display: inline-block;width: 85px;"><strong>未完成工作</strong></span><span type="text" id="undoneWork1"></span></div>
            <div style="margin-top: 10px"><span style="display: inline-block;width: 85px;"><strong>需协调工作</strong></span><span type="text" id="teamWork1"></span></div>
            <div style="margin-top: 10px"   ><span style="display: inline-block;width: 85px;"><strong>备注</strong></span><span type="text" id="bz1"></span></div>
        </div>
        <hr/>
        <div >
            <div id="files5"style="border-bottom: solid 1px #dcdcdc;"></div>
        </div>
        <hr/>
        <div  style="margin-left: 20px">
            <div>已读：0人</div>
        </div>
        <hr/>
        <div  style="margin-left: 20px;min-height: 140px">
            <div id="replayCount">暂无评论</div>
        </div>
        <p class="alert alert-info" style="background-color: lightgray;display: block;height: 50px;" >
    <span class="" style="display: block;width: 100%;text-align: left;">
        <input type="text" size="30"  placeholder="请填写评论内容" style="height: 30px;width: 60%;" id="replay">
        <button type="button"  id="saveReportReceived" class="btn btn-primary" style="height: 30px;width: 50px;margin-left: 5px;">
            发送
        </button>
        <span id="sendWorkInfo" style="color: red"></span>
    </span>
        </p>
    </div>
    <form class="smart-form" id="createProject">
        <input type="hidden" value="${reportId}" id="uuid">
        <fieldset>
                    <section>
                        <label class="label">今日完成工作</label>
                        <label class="textarea">
                            <textarea rows="3" class="custom-scroll"  id="doneWork" name="doneWork"></textarea>
                        </label>
                    </section>
                     <section>
                           <label class="label">未完成工作</label>
                           <label class="textarea">
                                    <textarea rows="3" class="custom-scroll"  id="undoneWork" name="undoneWork"></textarea>
                           </label>
                      </section>
                    <section>
                        <label class="label">需协调工作</label>
                        <label class="textarea">
                            <textarea rows="3" class="custom-scroll"  id="teamWork" name="teamWork"></textarea>
                        </label>
                    </section>
                    <section>
                        <label class="label">备注</label>
                        <label class="textarea">
                            <textarea rows="3" class="custom-scroll"  id="bz" name="content"></textarea>
                        </label>
                    </section>
            <section>
                <label class="label"><i class="fa fa-paperclip fa-lg"></i>图片</label>
                <input type="hidden" id="coid14" value="${taskOid}" />
                <div class="row" style="margin-left: 10px">
                    <div class="form-group">
                        <div class="col-md-6">
                            <input style="width: 300px;" class="" name="fileToUpload" id="fileToUpload14" type="file" multiple="multiple"  onchange="fileSelected14();">
                        </div>
                        <div class="col-md-6">
                            <a id="uploadFile14"  class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="uploadFile14()">开始上传</a>
                        </div>
                        <div class="col-md-4" id="fileName14"></div>
                        <div class="col-md-2" id="fileSize14"></div>
                        <div class="col-md-2" id="fileType14"></div>
                        <div class="easyui-progressbar col-md-2" id="progressNumber14"></div>
                        <input type="hidden" name="fileTypeId" id="fileTypeId14">
                    </div>
                </div>
                <div id="files14" style="width:100%;border-top:solid 1px lightgray;border-bottom: solid 1px lightgray ">
                </div>
            </section>
                    <section >
                        <label class="input"><button type="button" class="btn btn-warning" onclick="showModal()"  id="approvePersonId1">日志接收人</button>：
                        </label>
                        <div class="">
                                <label class="select">
                                    <label class="input">
                                        <input type="text" class="text"  id="leavePersonName" name="leavePersonName" readonly="true">
                                    </label>
                                    <input type="hidden" class="text"  id="leavePersonId" name="leavePersonId">
                                </label>
                            </div>
                    </section>

            <footer>
                <button type="button"  class="btn btn-primary" onclick="sendReport()">
                    提交
                </button>
            </footer>
        </fieldset>
    </form>
</div>
<script type="text/javascript">


    pageSetUp();



    var pagefunction = function () {
        $("#uploadFile14").hide();
    };

    loadScript("/smartAdmin/js/plugin/delete-table-row/delete-table-row.min.js", pagefunction);
    //引入遮罩层的jar包
    loadScript("/smartAdmin/js/BootSideMenu.js",triggerInit);
    //遮罩层初始化
    function triggerInit(){
        $('#taskSideBar').BootSideMenu({side:"right",autoClose:"true"});
    }
    //遮罩层的弹出方法
    function trigger(oid) {
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
    function showModal(){
        var leavePersonId = "";
        var leavePersonName = "";
        swal({
                    title: '<h4 class="modal-title"><p>日志接收人</p></h4>',
                    text: '<div class="modal-body no-padding"><div class="custom-scroll table-responsive" style="height:250px; overflow-y: auto;"><div id="organTree" style="text-align: left;"></div></div></div>',
                    html: true,
                    allowOutsideClick:true
                },
                function(){
                    $('#leavePersonId').val(leavePersonId);
                    $('#leavePersonName').val(leavePersonName);
                    //不加这段代码，在点击其他弹出框的时候会导致organTree里的内容显示，
                    // 加了之后消除organTree里的内容时弹出框正常显示
                    swal("","","success");

                });
        loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
        loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTreeSweet);
        function renderTreeSweet() {
            $.ajax({
                type: "GET",
                url: '/organization/queryAlltree.do',
                data: {},
                success: function (data) {
                    $('#organTree').treeview({
                        data: data,
                        multiSelect:true,
                        onNodeSelected: function (event, data) {
                            if(data.icon=="glyphicon glyphicon-tower"){
                                return;
                            }else{
                                var treeFlag = true;
                                for(var i = 0;i<leavePersonId.split(",").length;i++){
                                    if(data.href == leavePersonId.split(",")[i]){
                                        treeFlag = false;
                                    }
                                }
                                if(treeFlag) {
                                    if (leavePersonName == "") {
                                        leavePersonName = data.text;
                                    } else {
                                        leavePersonName += "," + data.text;
                                    }

                                    if (leavePersonId == "") {
                                        leavePersonId = data.href;
                                    } else {
                                        leavePersonId += "," + data.href;
                                    }
                                }
                            }

                        },
                        onNodeUnselected:function(event, data){

                            var treeFlag = true;
                            var leavePersonIds = leavePersonId.split(",");
                            var leavePersonNames = leavePersonName.split(",");
                            leavePersonName ="";
                            leavePersonId = "";
                            for(var i = 0;i<leavePersonIds.length;i++){
                                treeFlag = true;
                                if(data.href == leavePersonIds[i]){
                                    treeFlag = false;
                                }
                                if(treeFlag){
                                    if(leavePersonId == ""){
                                        leavePersonId = leavePersonIds[i];
                                        leavePersonName = leavePersonNames[i];
                                    }else{
                                        leavePersonId += "," + leavePersonIds[i];
                                        leavePersonName += "," + leavePersonNames[i];
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
    //发送日报
    function sendReport(){

        var  doneWork=$("#doneWork").val();
        var  undoneWork=$("#undoneWork").val();
        var  teamWork=$("#teamWork").val();
        var content=$("#bz").val();
        var personIds=$("#leavePersonId").val();
        var uuid=$("#uuid").val();
        var fileToUpload14 = $("#fileToUpload14").val();
        console.log("uuid"+uuid);
        if(doneWork.length==0&&doneWork.length==0&&teamWork.length==0){
            swal("","需要至少填写一项!!","warning");
            return;
        }
        if(personIds.length==0){
            swal("","请选择接收人!!","warning");
            return;
        }
        if(fileToUpload14 !=""){
            swal("","请先上传附件!!","warning");
            return;
        }
        $("#leavePersonName").val("");
        $("#leavePersonId").val("");
        $("#files14").empty();
        $.ajax({
            type: "POST",
            url: '/summarizeReport/insert.do',
            data:{
                receivePersonId:personIds,
                doneWork:doneWork,
                undoneWork:undoneWork,
                content:content,
                teamWork:teamWork,
                uuid:uuid
            },
            //查询详情
            success: function (data) {
                $("#date").html(data.date);
                $("#uuid1").val(data.uuid);
                if(data!="success"){
                $.ajax({
                    type: "GET",
                    url: '/summarizeReport/queryDetails.do',
                    data:{
                        reportId:data.uuid
                    },
                    success: function (data) {
                        console.log(data.report.rptPersonName)

                        $("#undoneWork1").html(undoneWork);
                        $("#doneWork1").html(doneWork);
                        $("#teamWork1").html(teamWork);
                        $("#bz1").html(content);
                        $("#undoneWork").val("");
                        $("#doneWork").val("");
                        $("#teamWork").val("");
                        $("#bz").val("");
                        $("#uuid").val(data.reportId);
                        $("#personName").html(data.report.rptPersonName);
                        $('#files5').empty();
                        console.log("datassssss"+data.sas.length);
                        for(var k=0;k<data.sas.length;k++){
                            if(data.sas.length>0){
                                $('#files5').show();
                                $('#files5').append(
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
                                document.getElementById("files5").style.height=data.sas.length*50+"px";
                            }else{
                                $('#files5').hide();
                                document.getElementById("files5").style.height=0*50+"px";
                            }

                        }
                    },
                    // 调用出错执行的函数
                    error: function () {
                    }
                });
                }
            },
            // 调用出错执行的函数
            error: function () {
            }

        });

        trigger();
        window.scrollTo( 0, 0 );
    }
    $("#saveReportReceived").click(function (){
        var replay=$("#replay").val();
        var uuid=$("#uuid1").val();
        var personName=$("#personName").html();
        var personName1=$("#personName").val();
        var personName1=$("#personName").text();
        if(replay.length<=0){
            $("#sendWorkInfo").html("评论内容不能为空");
            return;
        }
        $("#sendWorkInfo").html("");
        $.ajax({
            type: "POST",
            url: '/summarizeReport/replay.do',
            data:{
                reportId:uuid,
                replyContent:replay
            },
            success: function (data) {
                $("#replay").val("");
                $.ajax({
                    type: "POST",
                    url: '/summarizeReport/queryDetails.do',
                    data:{
                        reportId:uuid,
                        flag:"rpt"
                    },
                    success: function (data) {
                        var length = data.srrys.length;
                        if(data.srrys.length>0){
                            $("#replayCount").html('<div><span style="font-size: 20px">已有' +length + '人评论</span></div> ');
                            for (var k = 0; k < data.srrys.length; k++) {
                                $("#replayCount").append(
                                        '<div><Strong>' + data.srrys[k].replyPersonName + '</Strong><span>&nbsp;&nbsp;&nbsp;' + data.srrys[k].replyDate  + '</span></div>' +
                                        '<div><span>' + data.srrys[k].replyContent + '</span></div>'
                                )
                            }
                        }
                    },
                    // 调用出错执行的函数
                    error: function () {
                    }
                });
//                $("#replayCount").append(
//                        '<div><span style="font-size: 30px"><Strong>' + personName + '</Strong></span><span>&nbsp;&nbsp;&nbsp;'+data+'</span></div>' +
//                        '<div><span>' + replay + '</span></div>'
//                )
            },
            // 调用出错执行的函数
            error: function () {
            }
        });
    });

</script>
<style>
    #myTabContent1{
        clear: both;
    }

</style>