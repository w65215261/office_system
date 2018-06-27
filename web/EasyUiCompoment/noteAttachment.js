/* 扫描上传 */
var d = [".PDM",".sql"];
function fileSelected() {

    var file = document.getElementById('fileToUpload').files[0];

    var fileName = file.name;
    console.log(fileName);
    var file_typename = fileName.substring(fileName.lastIndexOf('.'), fileName.length);

    if (d.toString().indexOf(file_typename) == -1) {//这里限定上传文件文件类型
        if (file) {

            $("#uploadFile").show();
            var fileSize = 0;
            if (file.size > 1024 * 1024)
                fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
            else
                fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';

            document.getElementById('fileName').innerHTML = '文件名: ' + file.name;
            document.getElementById('fileSize').innerHTML = '大小: ' + fileSize;
        }
    }else {
        $("#uploadFile").hide();
        document.getElementById('fileName').innerHTML = "<span style='color:Red'>该种" + file_typename + "文件类型不允许上传</span>";
        document.getElementById('fileSize').innerHTML ="";
    }
}

// 文件上传
function uploadFile() {
    var fd = new FormData();
    //
    var coid = document.getElementById('noteOid').value;
    if(coid == "" || coid == null){
        swal("","请选择一个笔记","warning");
        return;
    }
    console.log(document.getElementById('fileToUpload').files[0]);
    fd.append("fileToUpload", document.getElementById('fileToUpload').files[0]);
    fd.append("fileTypeId", document.getElementById('fileTypeId').value);
    var xhr = new XMLHttpRequest();
    xhr.upload.addEventListener("progress", uploadProgress, false);
    xhr.addEventListener("load", uploadComplete, true);
    xhr.addEventListener("error", uploadFailed, false);
    xhr.addEventListener("abort", uploadCanceled, false);
    xhr.open("POST", "/noteAttachment/upload.do?uuid=" + coid);
    xhr.send(fd);
    $("#fileToUpload").val("");
    $("#fileTypeId").val("");


}

function uploadProgress(evt) {
    if (evt.lengthComputable) {
        var percentComplete = Math.round(evt.loaded * 100 / evt.total);
        // $('#progressNumber').progressbar('setValue', percentComplete);
    }else {
        document.getElementById('progressNumber').innerHTML = '无法计算';
    }
}

function uploadComplete(evt) {
    // 后台返回信息
    var res = evt.target.responseText;
    console.log(res);

    // 清空文件名、文件大小
    document.getElementById('fileName').innerHTML = "";
    document.getElementById('fileSize').innerHTML = "";
    // 隐藏上传按钮
    $("#uploadFile").hide();
    showTaskAttachment(res);
}

function uploadFailed(evt) {
    $.messager.alert('温馨提示','上传出错！');
}

function uploadCanceled(evt) {
    $.messager.alert('温馨提示','上传已由用户或浏览器取消删除连！');
}

function download(row){
    var mm="/"+row.fileUrl;
    return "<span><a href='"+mm+"' target= '_self '> 下载</span>";
}



