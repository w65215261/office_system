/* 扫描上传 */
var d = [".jpg", ".rar", ".doc",".txt",".docx",".xlsx",".xls"];
function fileSelected4() {
    var file = document.getElementById('fileToUpload4').files[0];

    var fileName = file.name;
    console.log(fileName);
    var file_typename = fileName.substring(fileName.lastIndexOf('.'), fileName.length);
    console.log(file_typename);
    if (d.toString().indexOf(file_typename) > -1) {//这里限定上传文件文件类型
        if (file) {

            $("#uploadFile4").show();
            var fileSize = 0;
            if (file.size > 1024 * 1024)
                fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
            else
                fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';

            document.getElementById('fileName4').innerHTML = '文件名: ' + file.name;
            document.getElementById('fileSize4').innerHTML = '大小: ' + fileSize;
        }
    }else {
        $("#uploadFile4").hide();
        document.getElementById('fileName4').innerHTML = "<span style='color:Red'>错误提示:该种" + file_typename + "文件类型不允许上传,请重新选择文件</span>";
        document.getElementById('fileSize4').innerHTML ="";
    }
}

// 文件上传
function uploadFile4() {

    var fd = new FormData();
    var coid = $('#oid2').val();
    var experienceId=$('#coid4').val();
   console.log(document.getElementById('fileToUpload4').files[0]);
    fd.append("fileToUpload", document.getElementById('fileToUpload4').files[0]);
    fd.append("fileTypeId", document.getElementById('fileTypeId4').value);
    var xhr = new XMLHttpRequest();
    xhr.upload.addEventListener("progress", uploadProgress, false);
    xhr.addEventListener("load", uploadComplete4, false);
    xhr.addEventListener("error", uploadFailed, false);
    xhr.addEventListener("abort", uploadCanceled, false);
    xhr.open("POST", "/plan/upload.do?uuid=" + coid+"&experienceId="+experienceId);
    xhr.send(fd);

}

function uploadProgress(evt) {
    if (evt.lengthComputable) {
        var percentComplete = Math.round(evt.loaded * 100 / evt.total);
       // $('#progressNumber').progressbar('setValue', percentComplete);
    }else {
        document.getElementById('progressNumber3').innerHTML = '无法计算';
    }
}

function uploadComplete4(evt) {
    // 后台返回信息
    console.log("后台方法4");
    var res = evt.target.responseText;
    var experienceId=$('#coid4').val();
    var file = document.getElementById('fileToUpload4').files[0].name;
    console.log(res);

    // 清空文件名、文件大小
    document.getElementById('fileName4').innerHTML = "";
    document.getElementById('fileSize4').innerHTML = "";
    // 隐藏上传按钮
    $("#uploadFile4").hide();
    $("#fileToUpload4").val("");
    // 进度条清0
    if(res != null && res != ""){
        //$('#coid').val(res);
        $("#files4").show();
        $.ajax({
            type: "POST",
            url: '/plan/findAttachmentByOid.do',
            data: {
                res: res,
                experienceId:experienceId
            },
            success: function (data) {
                console.log("data" + data.length);
                $("#files4").append(
                    '<ul class="inbox-download-list col-md-12">' +
                    '<li class="col-md-12">' +
                    '<span class="col-md-6" style="width: 50px;">' +
                    '<i class="fa fa-file-text-o" style="font-size: 40px;"></i>' +
                    '</span>' +
                    '<span class="col-md-6">' +
                    '<strong>' + data[0].fileName + '</strong>' +
                    '</span>' +
                    '<span class="col-md-6">' +
                    '<a href="/' + data[0].fileUrl + '"> 下载</a>' +
                    '<a href="JavaScript:deleteAttachment4(\'' + data[0].id + '\')"> 删除</a>' +
                    '</span>' +
                    '</li>' +
                    '</ul>'
                );
                document.getElementById("files4").style.height = data.length * 45 + "px";
            }
            });
        }
}

function deleteAttachment4(oid){
    $.ajax({
        type: "POST",
        url: '/plan/deleteAttachment.do',
        data:{
            id:oid
        },
        success: function (data) {
            if (data == "success") {
                var taskOid = $('#oid2').val();
                var experienceId=$('#coid4').val();
                console.log("taskOid是不是原来的数据呢"+taskOid+"experienceId事不是原来的了呢"+experienceId);
                $("#files4").empty();
                $("#files4").html("");
                $.ajax({
                    type: "POST",
                    url: '/plan/findAttachmentByOid.do',
                    data:{
                        res:taskOid,
                        experienceId:experienceId
                    },
                    success: function (data) {
                        console.log("data4的条数"+data.length);
                        for(var k=0;k<data.length;k++){
                            $('#files4').append(
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
                                '<a href="JavaScript:deleteAttachment4(\''+data[k].id+'\')"> 删除</a>'+
                                '</span>'+
                                '</li>'+
                                '</ul>'
                            );
                        }
                        document.getElementById("files4").style.height=data.length*45+"px";
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

