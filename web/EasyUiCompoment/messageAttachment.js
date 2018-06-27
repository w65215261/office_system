/* 扫描上传 */
var d = [".jpg", ".rar", ".doc",".txt",".docx",".xlsx",".xls"];
function fileSelected() {

    var file = document.getElementById('fileToUpload').files[0];

    var fileName = file.name;
    console.log(fileName);
    var file_typename = fileName.substring(fileName.lastIndexOf('.'), fileName.length);

    if (d.toString().indexOf(file_typename) > -1) {//这里限定上传文件文件类型
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
        document.getElementById('fileName').innerHTML = "<span style='color:Red'>错误提示:该种" + file_typename + "文件类型不允许上传,请重新选择文件</span>";
        document.getElementById('fileSize').innerHTML ="";
    }
}

// 文件上传
function uploadFile() {
    var fd = new FormData();
    //
    var coid = document.getElementById('messageId').value;
   console.log(document.getElementById('fileToUpload').files[0]);
    fd.append("fileToUpload", document.getElementById('fileToUpload').files[0]);
    fd.append("fileTypeId", document.getElementById('fileTypeId').value);
    var xhr = new XMLHttpRequest();
    xhr.upload.addEventListener("progress", uploadProgress, false);
    xhr.addEventListener("load", uploadComplete, false);
    xhr.addEventListener("error", uploadFailed, false);
    xhr.addEventListener("abort", uploadCanceled, false);
    xhr.open("POST", "/messageAttachment/upload.do?uuid=" + coid);
    xhr.send(fd);

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
    // 进度条清0
    if(res != null && res != ""){
        $('#coid').val(res);
    }
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
function add(){
    // 清空文件选择框
    document.getElementById('fileToUpload').outerHTML = document.getElementById('fileToUpload').outerHTML;
    $('#openType').window('open');
}
// 删除附件
function dele(){

    var oneTest = $("#mc").datagrid("getSelected");
    var coid = document.getElementById('coid').value;
    if(oneTest != null && oneTest != ""){
        $.messager.confirm("温馨提示", "是否确认删除？", function (r) {
            if (r) {
                $.ajax({
                    type:"GET",
                    url:'/messageAttachment/delete.do',
                    data : {
                        id : oneTest.id,
                        materialId : coid
                    },
                    success:function(data){
                        if(data == '1'){
                            $.messager.alert('温馨提示','操作成功！');
                            $('#mc').datagrid({url:'/messageAttachment/query.do?material='+coid});
                            //$('#mc').datagrid('reload',{contractOid:coid});
                        }else{
                            $.messager.alert('温馨提示','操作失败！');
                        }
                    },
                    //调用出错执行的函数
                    error: function(){
                    }
                });
            }
        });

    }else{


        $.messager.alert('提示信息','请选择一条数据！');
    }
}

