<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">


<!--  附件上传 -->
<script type="text/javascript" src="/EasyUiCompoment/messageAttachment.js"></script>


<!-- MODAL PLACE HOLDER -->
<div class="modal fade" id="receieverModal" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">
    <div class="modal-dialog"  style="width:300px ;height:600px">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><p>选择收件人</p></h4>
            </div>


            <div class="modal-body no-padding">
                                <div class="custom-scroll table-responsive" style="height:250px; overflow-y: scroll;">
                                    <div id="organTree">


                                    </div>

                            </div>

            </div>



            <script>


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
                                    console.log(event);
                                    console.log(data.icon);
                                    if(data.icon=="glyphicon glyphicon-tower"){
                                        return ;
                                    }else{

                                        $('#receiverLabel')[0].innerHTML=data.text;
                                        var personId=data.href;
                                        $('#receivePersonId').val(personId);
                                        $("#receieverModal").modal("hide");

                                    }



                                }
                            });
                        },
                        // 调用出错执行的函数
                        error: function () {
                        }
                    });
                }

            </script>
        </div>
    </div>
</div>
<!-- END MODAL -->




<form enctype="multipart/form-data" action="dummy.php" method="POST" class="form-horizontal" id="email-compose-form">
    <div>
        <h2 class="email-open-header" style="width: 90%">编写新的站内信 <span class="label txt-color-white">草稿</span></h2>
    </div>
    <div class="inbox-info-bar no-padding" style="width: 90%">
        <div class="row" style="width: 100%">
            <div class="form-group">
                <input type="hidden" id="receivePersonId">

                <label class="control-label col-md-1" ><strong>发送至</strong></label> &nbsp;
                <div class="col-md-10">
                    <span  id="receiverLabel"  class="label label-primary" ></span>
                <button onclick="showModal()"  class="btn-sm btn-link  pull-right" type="button" id="chooseReceiver">
                    <i class="fa fa-plus  fa-lg"></i>  收件人
                </button>
                </div>
            </div>
        </div>
    </div>



    <div class="inbox-info-bar no-padding" style="width: 90%;">
        <div class="row" style="width: 100%">
            <div class="form-group">
                <label class="control-label col-md-1" ><strong>主 题</strong></label>
                <div class="col-md-11">
                    <input class="form-control" id="messageTitle" placeholder="站内信主题" type="text">
                    <em><a href="javascript:void(0);" class="show-next" rel="tooltip" data-placement="bottom" data-original-title="Attachments"><i class="fa fa-paperclip fa-lg"></i></a></em>
                </div>
            </div>
        </div>
    </div>

    <div class="inbox-info-bar no-padding hidden" style="width: 90%">
        <input type="hidden" id="coid"/>
        <div class="row">
            <div class="form-group">
                <label class="control-label col-md-1"><strong>附件</strong></label>
                <div class="col-md-8">
                    <input class="form-control fileinput" name="fileToUpload" id="fileToUpload" type="file" multiple="multiple"  onchange="fileSelected();">
                </div>
                <div id="fileName" style="padding: 10px"></div>
                <div id="fileSize" style="padding: 10px"></div>
                <div id="fileType" style="padding: 10px"></div>
                <div id="progressNumber" class="easyui-progressbar" style="width: 380px;"></div>
                <div class="col-md-2">
                    <a id="uploadFile"   class="easyui-linkbutton"
                       data-options="iconCls:'icon-save'" onclick="uploadFile()">开始上传</a>
                </div>
                <input type="hidden" name="fileTypeId" id="fileTypeId">
            </div>
        </div>
    </div>

    <div class="inbox-message no-padding" style="width: 90%;height:200px">
        <input type="hidden" id="messageId" />
        <div id="emailbody">


        </div>
    </div>

    <div style="width: 90%;margin-top: 5px;">
        <button data-loading-text="&lt;i class='fa fa-refresh fa-spin'&gt;&lt;/i&gt; &nbsp; 发送中..." class="btn btn-primary pull-right" type="button" id="send">
            发送 <i class="fa fa-arrow-circle-right fa-lg"></i>
        </button>


    </div>

</form>

<%--<div class="email-infobox">--%>

    <%--<div class="well well-sm well-light">--%>
        <%--<h5>Related Invoice</h5>--%>
        <%--<ul class="list-unstyled">--%>
            <%--<li>--%>
                <%--<i class="fa fa-file fa-fw text-success"></i><a href="javascript:void(0);"> #4831 - Paid</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<i class="fa fa-file fa-fw text-danger"></i><a href="javascript:void(0);"><strong> #4835 - Unpaid</strong></a>--%>
            <%--</li>--%>
        <%--</ul>--%>


    <%--</div>--%>

    <%--<div class="well well-sm well-light">--%>
        <%--<h5>Upcoming Meetings</h5>--%>

        <%--<p>--%>
            <%--<span class="label label-success"><i class="fa fa-check"></i> <strike>Agenda Review @ 10 AM</strike> </span>--%>
        <%--</p>--%>

        <%--<p>--%>
            <%--<span class="label label-primary"><i class="fa fa-clock-o"></i> Client Meeting @ 2:30 PM</span>--%>
        <%--</p>--%>

        <%--<p>--%>
            <%--<span class="label label-warning"><i class="fa fa-clock-o"></i> Salary Review @ 4:00 PM</span>--%>
        <%--</p>--%>
    <%--</div>--%>

    <%--<ul class="list-inline">--%>
        <%--<li><img src="/smartAdmin/img/avatars/5.png" alt="me" width="30px"></li>--%>
        <%--<li><img src="/smartAdmin/img/avatars/3.png" alt="me" width="30px"></li>--%>
        <%--<li><img src="/smartAdmin/img/avatars/sunny.png" alt="me" width="30px"></li>--%>
        <%--<li><a href="javascript:void(0);">1 more</a></li>--%>
    <%--</ul>--%>

<%--</div>--%>

<script type="text/javascript">

    pageSetUp();

    var pagefunction = function() {







    }




    //here we only run
    runAllForms();

    // PAGE RELATED SCRIPTS

    $(".table-wrap [rel=tooltip]").tooltip();

    /*
     * SUMMERNOTE EDITOR
     */
    loadScript("/smartAdmin/js/plugin/summernote/summernote.min.js", iniEmailBody);

    function iniEmailBody() {
        $('#emailbody').summernote({
            height: '120',
            focus: false,
            tabsize: 2
        });


        //获取messageId
        $.ajax({
            type: "GET",
            url: '/message/getUUID.do',
            success: function (data) {
                $('#messageId').val(data);
            },
            error: function () {

            }
        });
    }



    $(".show-next").click(function () {
        $this = $(this);
        $this.hide();
        $this.parent().parent().parent().parent().parent().next().removeClass("hidden");
    })

    $("#send").click(function () {
        var shtml=$('#emailbody').code();
        var title=$('#messageTitle').val();
        var messageTo=$('#receivePersonId').val();
        var messageToName= $('#receiverLabel')[0].innerHTML;
        var id=$('#messageId').val();
        if(messageToName.length<=0){
            swal("","收件人不能为空!","warning");
            return;
        }
        if(title.trim().length<=0){
            swal("","站内信标题不能为空!","warning");
            return;
        }
        $.ajax({
            type: "POST",
            url: '/message/insert.do',
            data:{messageTo:messageTo,messageToName:messageToName,messageContent:shtml,messageTitle:title,id:id},
            success: function (data) {
                var flag=$("#showFlag").val();
                if(flag=="1"){
                loadURL("/message/queryInbox.do", $('#inbox-content > .table-wrap'))
                }
                if(flag=="2"){
                    loadURL("/message/queryOutbox.do", $('#inbox-content > .table-wrap'));
                }
                if(flag=="3"){
                    loadURL("/message/queryGarbage.do", $('#inbox-content > .table-wrap'));
                }
            },
            error: function () {

            }
        });
    });








//    function showModal(){
//        console.log("modalShow");
//        $("#receieverModal").modal("show");
//    }
    function showModal(){
        swal({
                    title: '<h4 class="modal-title"><p>选择收件人</p></h4>',
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
                                return;
                            }else{
                                $('#receiverLabel')[0].innerHTML=data.text;
                                var personId=data.href;
                                $('#receivePersonId').val(personId);
//                                $("#receieverModal").modal("hide");
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