/* 扫描上传 */
var d = [".jpeg",".png",".bmp",".jpg",".tiff",".gif",".pcx",".tga",".exif",".fpx",".svg",".psd",".cdr",".pcd",".dxf",".ufo",".eps",".ai",".raw",".JPEG",".PNG",".BMP",".JPG",".TIFF",".GIF",".PCX",".TGA",".EXIF",".FPX",".SVG",".PSD",".CDR",".PCD",".DXF",".UFO",".EPS",".AI",".RAW"];
var xhr;
var uuid = $("#uuid").val();
var flag =0;
var files = document.getElementById('fileToUpload').files;
function fileSelected() {

    var file = document.getElementById('fileToUpload').files;

    if(file.length>9){
        swal("","只能上传9张图片","warning");
        $("#fileToUpload").val("");
        return;
    }
    for(var i=0;i<file.length;i++){
        //alert(document.getElementById('fileToUpload').value);
        //$("#systemAttachmentId").append(
        //    '<li class="col-md-1 no-padding" style="width: 65px;">'+
        //    '<span>'+
        //    '<img src="'+file[i].url+'" height="60px" width="60px"/>'+
        //    '</span>'+
        //    '</li>'
        //);


        var fileName = file[i].name;
        console.log(fileName);
        var file_typename = fileName.substring(fileName.lastIndexOf('.'), fileName.length);

        if (d.toString().indexOf(file_typename) != -1) {//这里限定上传文件文件类型
            if (file[i]) {
                $("#uploadFile").show();
            }
        }else {
            $("#uploadFile").hide();
            swal("",file[i].name+"不是图片格式","warning");
            $("#fileToUpload").val("");
            $("#fileTypeId").val("");
            return;
        }
    }
}

// 文件上传
function uploadFile() {
    $.ajax({
        type: "GET",
        url: '/attachment/queryByBusinessData.do',
        data:{
            businessData:uuid
        },
        success: function (data) {
            if(data+files.length > 9){
                swal("","一条审批总共只能上传9张图片","warning");
                return;
            }else{
                flag = 0;
                var businessType = $("#templateCode").val();
                var businessModel = 1;
                for(var i=0;i<files.length;i++){
                    var fd = new FormData();
                    fd.append("fileToUpload", document.getElementById('fileToUpload').files[i]);
                    fd.append("fileTypeId", document.getElementById('fileTypeId').value);
                    xhr = new XMLHttpRequest();
                    xhr.upload.addEventListener("progress", uploadProgress, false);
                    xhr.addEventListener("load", uploadComplete, true);
                    xhr.addEventListener("error", uploadFailed, false);
                    xhr.addEventListener("abort", uploadCanceled, false);
                    xhr.open("POST", "/attachment/upload.do?uuid=" + uuid+"&businessType="+businessType+"&businessModel="+businessModel);
                    xhr.send(fd);
                    flag++;
                }
                xhr.onreadystatechange=state_Change;
            }
        },
        error: function () {

        }
    });
}

function state_Change()
{
    if (xhr.readyState==4)
    {
        if (xhr.status==200)
        {
            if(flag == files.length){
                $("#fileToUpload").val("");
                $("#fileTypeId").val("");
                $.ajax({
                    type: "GET",
                    url: '/attachment/query.do',
                    data:{
                        businessData:uuid
                    },
                    success: function (data) {
                        $("#systemAttachmentId").empty();
                        for(var i =0;i<data.length;i++){
                            $("#systemAttachmentId").append(
                                '<li class="col-md-1 no-padding" style="width: 65px;">'+
                                '<span>'+
                                '<img src="'+data[i].fileUrl+'" height="60px" width="60px"/>'+
                                '<span class="no-padding" style="position: absolute; top: 0; right: 5px;color: #000000" onclick="deletePhoto(\''+data[i].id+'\')">X</span>'+
                                '</span>'+
                                '</li>'
                            );
                        }


                    },
                    error: function () {

                    }
                });
            }
        }
        else
        {
            alert("Problem retrieving XML data");
        }
    }
}

function deletePhoto(id){
    $.ajax({
        type: "GET",
        url: '/attachment/deleteById.do',
        data:{
            id:id,
            businessData:uuid
        },
        success: function (data) {
            $("#systemAttachmentId").empty();
            for(var i =0;i<data.length;i++){
                $("#systemAttachmentId").append(
                    '<li class="col-md-1 no-padding" style="width: 65px;">'+
                    '<span>'+
                    '<img src="'+data[i].fileUrl+'" height="60px" width="60px"/>'+
                    '<span class="no-padding" style="position: absolute; top: 0; right: 5px;color: #000000" onclick="deletePhoto(\''+data[i].id+'\')">X</span>'+
                    '</span>'+
                    '</li>'
                );
            }


        },
        error: function () {

        }
    });

}

function uploadProgress(evt) {
    if (evt.lengthComputable) {
        var percentComplete = Math.round(evt.loaded * 100 / evt.total);
        // $('#progressNumber').progressbar('setValue', percentComplete);
    }else {
        //document.getElementById('progressNumber').innerHTML = '无法计算';
    }
}

function uploadComplete(evt) {
    // 后台返回信息
    var res = evt.target.responseText;
    console.log(res);

    // 清空文件名、文件大小
    //document.getElementById('fileName').innerHTML = "";
    //document.getElementById('fileSize').innerHTML = "";
    // 隐藏上传按钮
    $("#uploadFile").hide();
}

function uploadFailed(evt) {
    $.messager.alert('温馨提示','上传出错！');
}

function uploadCanceled(evt) {
    $.messager.alert('温馨提示','上传已由用户或浏览器取消删除连！');
}

