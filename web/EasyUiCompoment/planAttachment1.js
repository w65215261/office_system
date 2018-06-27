/* 扫描上传 */
var d = [".jpg", ".rar", ".doc",".txt",".docx",".xlsx",".xls"];
function fileSelected1() {
    console.log("第一个方法");
    var file = document.getElementById('fileToUpload1').files[0];

    var fileName = file.name;
    console.log(fileName);
    var file_typename = fileName.substring(fileName.lastIndexOf('.'), fileName.length);
    console.log(file_typename);
    if (d.toString().indexOf(file_typename) > -1) {//这里限定上传文件文件类型
        if (file) {

            $("#uploadFile1").show();
            var fileSize = 0;
            if (file.size > 1024 * 1024)
                fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
            else
                fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';

            document.getElementById('fileName1').innerHTML = '文件名: ' + file.name;
            document.getElementById('fileSize1').innerHTML = '大小: ' + fileSize;
        }
    }else {
        $("#uploadFile1").hide();
        document.getElementById('fileName1').innerHTML = "<span style='color:Red'>错误提示:该种" + file_typename + "文件类型不允许上传,请重新选择文件</span>";
        document.getElementById('fileSize1').innerHTML ="";
    }
}

// 文件上传
function uploadFile1() {
    var fd = new FormData();
    var coid = $('#oid2').val();
    console.log(document.getElementById('fileToUpload1').files[0]);
    fd.append("fileToUpload", document.getElementById('fileToUpload1').files[0]);
    fd.append("fileTypeId", document.getElementById('fileTypeId1').value);
    var xhr = new XMLHttpRequest();
    xhr.upload.addEventListener("progress", uploadProgress, false);
    xhr.addEventListener("load", uploadComplete1, false);
    xhr.addEventListener("error", uploadFailed, false);
    xhr.addEventListener("abort", uploadCanceled, false);
    xhr.open("POST", "/plan/upload.do?uuid=" + coid+"&flag=1");
    xhr.send(fd);

}

function uploadProgress(evt) {
    if (evt.lengthComputable) {
        var percentComplete = Math.round(evt.loaded * 100 / evt.total);
        $('#progressNumber1').progressbar('setValue', percentComplete);
    }else {
        document.getElementById('progressNumber1').innerHTML = '无法计算';
    }
}

function uploadComplete1(evt) {
    // 后台返回信息
    var res = evt.target.responseText;
    var file = document.getElementById('fileToUpload1').files[0].name;
    console.log(res);

    // 清空文件名、文件大小
    document.getElementById('fileName1').innerHTML = "";
    document.getElementById('fileSize1').innerHTML = "";
    // 隐藏上传按钮
    $("#uploadFile1").hide();
    $("#fileToUpload1").val("");
    console.log($("#fileToUpload1").val());
    // 进度条清0
    if(res != null && res != ""){
        console.log(res);
        $("#files1").show();
        $.ajax({
            type: "POST",
            url: '/plan/findAttachmentByTaskOid.do',
            data: {
                taskOid: res
            },
            success: function (data) {
                $('#files1').empty();
                console.log("datassssss"+data.length);
                for(var k=0;k<data.length;k++){
                    $('#files1').append(
                        '<ul class="inbox-download-list col-md-12">'+
                        '<li class="col-md-12">'+
                        '<span class="col-md-6" style="width: 50px;">'+
                        '<i class="fa fa-file-text-o" style="font-size: 40px;"></i>'+
                        '</span>'+
                        '<span class="col-md-6">'+
                        '<strong>'+data[k].fileName+'</strong>'+
                        '</span>'+
                        '<span class="col-md-6">'+
                        '<a href="/'+data[k].fileUrl+'"> 下载</a>'+
                        '<a href="JavaScript:deleteAttachment0(\''+data[k].id+'\')"> 删除</a>'+
                        '</span>'+
                        '</li>'+
                        '</ul>'
                    );
                }
                document.getElementById("files1").style.height=data.length*45+"px";
                //$.ajax({
                //    type: "POST",
                //    url: '/plan/addFileOperation.do',
                //    data: {
                //        taskOid: res,
                //        fileName:data[0].fileName
                //    },
                //    success: function (data) {}
                //})
            }
        })
    }
}
function deleteAttachment0(oid){
    $.ajax({
        type: "POST",
        url: '/plan/deleteAttachment.do?flag=1',
        data:{
            id:oid
        },
        success: function (data) {
            if (data == "success") {
                var taskOid = $('#oid2').val();
                console.log("taskOid是不是原来的数据呢"+taskOid);
                $("#files1").empty();
                $("#files1").html("");
                $.ajax({
                    type: "POST",
                    url: '/plan/findAttachmentByTaskOid.do',
                    data:{
                        taskOid:taskOid
                    },
                    success: function (data) {
                        console.log("data1的条数"+data.length);
                        for(var k=0;k<data.length;k++){
                            $('#files1').append(
                                '<ul class="inbox-download-list col-md-12">'+
                                '<li class="col-md-12">'+
                                '<span class="col-md-6" style="width: 50px;">'+
                                '<i class="fa fa-file-text-o" style="font-size: 40px;"></i>'+
                                '</span>'+
                                '<span class="col-md-6">'+
                                '<strong>'+data[k].fileName+'</strong>'+
                                '</span>'+
                                '<span class="col-md-6">'+
                                '<a href="/'+data[k].fileUrl+'"> 下载</a>'+
                                '<a href="JavaScript:deleteAttachment0(\''+data[k].id+'\')"> 删除</a>'+
                                '</span>'+
                                '</li>'+
                                '</ul>'
                            );
                        }
                        document.getElementById("files1").style.height=data.length*45+"px";
                    },
                    // 调用出错执行的函数
                    error: function () {

                    }
                });

            } else {
                alert('删除失败!');
            }
        },
        // 调用出错执行的函数
        error: function () {

        }
    });
}

