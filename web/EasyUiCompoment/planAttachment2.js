/* 扫描上传 */
var d = [".jpg", ".rar", ".doc",".txt",".docx",".xlsx",".xls"];
function fileSelected2() {

    var file = document.getElementById('fileToUpload2').files[0];

    var fileName = file.name;
    console.log(fileName);
    var file_typename = fileName.substring(fileName.lastIndexOf('.'), fileName.length);
    console.log(file_typename);
    if (d.toString().indexOf(file_typename) > -1) {//这里限定上传文件文件类型
        if (file) {

            $("#uploadFile2").show();
            var fileSize = 0;
            if (file.size > 1024 * 1024)
                fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
            else
                fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';

            document.getElementById('fileName2').innerHTML = '文件名: ' + file.name;
            document.getElementById('fileSize2').innerHTML = '大小: ' + fileSize;
        }
    }else {
        $("#uploadFile2").hide();
        document.getElementById('fileName2').innerHTML = "<span style='color:Red'>错误提示:该种" + file_typename + "文件类型不允许上传,请重新选择文件</span>";
        document.getElementById('fileSize2').innerHTML ="";
    }
}

// 文件上传
function uploadFile2() {

    var fd = new FormData();
    var coid = $('#coid1').val();
   console.log(document.getElementById('fileToUpload2').files[0]);
    fd.append("fileToUpload", document.getElementById('fileToUpload2').files[0]);
    fd.append("fileTypeId", document.getElementById('fileTypeId2').value);
    var xhr = new XMLHttpRequest();
    xhr.upload.addEventListener("progress", uploadProgress, false);
    xhr.addEventListener("load", uploadComplete2, false);
    xhr.addEventListener("error", uploadFailed, false);
    xhr.addEventListener("abort", uploadCanceled, false);
    xhr.open("POST", "/plan/upload.do?uuid=" + coid);
    xhr.send(fd);

}

function uploadProgress(evt) {
    if (evt.lengthComputable) {
        var percentComplete = Math.round(evt.loaded * 100 / evt.total);
       // $('#progressNumber').progressbar('setValue', percentComplete);
    }else {
        document.getElementById('progressNumber2').innerHTML = '无法计算';
    }
}

function uploadComplete2(evt) {
    // 后台返回信息
    console.log("后台方法2");
    var res = evt.target.responseText;
    var file = document.getElementById('fileToUpload2').files[0].name;
    console.log(res);

    // 清空文件名、文件大小
    document.getElementById('fileName2').innerHTML = "";
    document.getElementById('fileSize2').innerHTML = "";
    // 隐藏上传按钮
    $("#uploadFile2").hide();
    $("#fileToUpload2").val("");
    // 进度条清0
    if(res != null && res != ""){
        //$('#coid').val(res);
        $("#files2").show();
        $.ajax({
            type: "POST",
            url: '/plan/findAttachmentByOid.do',
            data: {
                res: res
            },
            success: function (data) {
                console.log("data"+data.length);
                $('#files2').empty();
                for(var k=0;k<data.length;k++){
                    $('#files2').append(
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
                        '<a href="JavaScript:deleteAttachment1(\''+data[k].id+'\')"> 删除</a>'+
                        '</span>'+
                        '</li>'+
                        '</ul>'
                    );
                }
                document.getElementById("files2").style.height=data.length*45+"px";
            }
        })
    }
}

function uploadFailed1(evt) {
    $.messager.alert('温馨提示','上传出错！');
}

function uploadCanceled1(evt) {
    $.messager.alert('温馨提示','上传已由用户或浏览器取消删除连！');
}
function download1(row){
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
function deleteAttachment1(oid){
    var findOid = $("#oid2").val();
    $.ajax({
        type: "POST",
        url: '/plan/deleteAttachment.do',
        data:{
            id:oid
        },
        success: function (data) {
            if (data == "success") {
                var res = $('#coid1').val();
                console.log("taskOid是不是原来的数据呢"+res);
                $("#files2").empty();
                $("#files2").html("");
                $.ajax({
                    type: "POST",
                    url: '/plan/findAttachmentByTaskOid.do',
                    data:{
                        taskOid:res
                    },
                    success: function (data) {
                        console.log("data2的条数"+data.length);
                        for(var k=0;k<data.length;k++){
                            $('#files2').append(
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
                                '<a href="JavaScript:deleteAttachment1(\''+data[k].id+'\')"> 删除</a>'+
                                '</span>'+
                                '</li>'+
                                '</ul>'
                            );
                        }
                        document.getElementById("files2").style.height=data.length*45+"px";
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

