<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>




<!--  附件上传 -->
<script type="text/javascript" src="/EasyUiCompoment/messageAttachment.js"></script>





<form enctype="multipart/form-data" action="dummy.php" method="POST" class="form-horizontal" id="email-compose-form">
    <div>
        <h2 class="email-open-header" style="width: 90%">编写新的站内信 <span class="label txt-color-white">草稿</span></h2>
    </div>
    <div class="inbox-info-bar no-padding" style="width: 90%">
        <div class="row">
            <div class="form-group">
                <input type="hidden" id="receivePersonId" value="${messageTo}">
                <label class="control-label col-md-1"><strong>发送至</strong></label> &nbsp;<span  id="receiverLabel"  class="label label-primary">${messageToName}</span>


            </div>
        </div>
    </div>



    <div class="inbox-info-bar no-padding" style="width: 90%">
        <div class="row">
            <div class="form-group">
                <label class="control-label col-md-1"><strong>主题</strong></label>
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

    <div class="inbox-message no-padding" style="width: 90%;height:230px">
        <input type="hidden" id="messageId" />
        <div id="emailbody"><br><br><br><br><br><br><br><br><br>
        </div>
    </div>

    <div style="width: 90%;margin-top: 5px;">



        <button data-loading-text="&lt;i class='fa fa-refresh fa-spin'&gt;&lt;/i&gt; &nbsp; 发送中..." class="btn btn-primary pull-right" type="button" id="send">
            发送 <i class="fa fa-arrow-circle-right fa-lg"></i>
        </button>


    </div>

</form>

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
        console.log("initsummernote");
        $('#emailbody').summernote({
            height: '100%',
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
        $.ajax({
            type: "POST",
            url: '/message/insert.do',
            data:{messageTo:messageTo,messageToName:messageToName,messageContent:shtml,messageTitle:title,id:id},
            success: function (data) {

                loadURL("/message/queryInbox.do", $('#inbox-content > .table-wrap'))

            },
            error: function () {

            }
        });
    });








    function showModal(){
        console.log("modalShow");
        $("#receieverModal").modal("show");
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