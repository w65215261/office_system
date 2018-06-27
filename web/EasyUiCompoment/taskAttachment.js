/* 扫描上传 */
var d = [".jpg", ".rar", ".doc",".txt",".docx",".xlsx",".xls",".png",".PDM",".exe"];
var i = "0";
var dflag = "";
var taskOidFlag="";
function fileSelected(k,j) {
    i = k;
    var file;
    if(j != "undefined"){


        file = document.getElementById('fileToUpload'+i+j).files[0];
    }else{
        file = document.getElementById('fileToUpload'+i).files[0];

    }

    var fileName = file.name;
    console.log(fileName);
    var file_typename = fileName.substring(fileName.lastIndexOf('.'), fileName.length);

    if (d.toString().indexOf(file_typename) > -1) {//这里限定上传文件文件类型
        if (file) {
            if(j != "undefined"){
                $("#uploadFile"+i+j).show();
                document.getElementById('fileName'+i+j).innerHTML = '';
                var fileSize = 0;
                if (file.size > 1024 * 1024)
                    fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
                else
                    fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
            }else{
                $("#uploadFile"+i).show();
                var fileSize = 0;
                if (file.size > 1024 * 1024)
                    fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
                else
                    fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';

                document.getElementById('fileName'+i).innerHTML = file.name;
                document.getElementById('fileSize'+i).innerHTML = '' + fileSize;

            }

        }
    }else {
        if(j != "undefined"){
            $("#uploadFile"+i+j).hide();
            document.getElementById('fileName'+i+j).innerHTML = "<span style='color:Red;font-size: 10px;'>错误提示:该种" + file_typename + "文件类型不允许上传,请重新选择文件</span>";
        }else{
            $("#uploadFile"+i).hide();
            document.getElementById('fileName'+i).innerHTML = "<span style='color:Red;'>错误提示:该种" + file_typename + "文件类型不允许上传,请重新选择文件</span>";
            document.getElementById('fileSize'+i).innerHTML ="";
        }
    }
}

// 文件上传
function uploadFile(foid) {

    var projectOid = $("#projectOid").val();
    if(foid != ""){
        var flag = foid.split(",");
        var workHourOid = flag[0];
        dflag = flag[1];
    }

    var fd = new FormData();
    var coid = "0";
    var taskOid = document.getElementById('oid2').value;
    taskOidFlag =taskOid;
    if(i == "1"){
        coid = document.getElementById('taskId'+i).value;
    }else if(i == "2"){
        coid = document.getElementById('oid2').value;
    }else if(i == "3"){
        coid = document.getElementById('taskId'+i).value;
    }else if(i == "4"){
        coid = document.getElementById('workHourOid').value+",4"+","+taskOid;
    }else if(i == "5"){
        coid = document.getElementById('experienceOid').value+",5"+","+taskOid;
    }else{
        coid = workHourOid+",4"+","+taskOid;
    }
    if(i !=6){
        fd.append("fileToUpload", document.getElementById('fileToUpload'+i).files[0]);
        fd.append("fileTypeId", document.getElementById('fileTypeId'+i).value);
        var xhr = new XMLHttpRequest();
        xhr.upload.addEventListener("progress", uploadProgress, false);
        xhr.addEventListener("load", uploadComplete, false);
        xhr.addEventListener("error", uploadFailed, false);
        xhr.addEventListener("abort", uploadCanceled, false);
        xhr.open("POST", "/taskAttachment/upload.do?uuid=" + coid+"&projectOid="+projectOid);
        xhr.send(fd);
        $('#'+'fileToUpload'+i).val("");
    }else{
        fd.append("fileToUpload", document.getElementById('fileToUpload'+i+dflag).files[0]);
        var xhr = new XMLHttpRequest();
        xhr.upload.addEventListener("progress", uploadProgress, false);
        xhr.addEventListener("load", uploadComplete, false);
        xhr.open("POST", "/taskAttachment/upload.do?uuid=" + coid+"&projectOid="+projectOid);
        xhr.send(fd);
        $('#'+'fileToUpload'+i+dflag).val("");
    }

}

function uploadProgress(evt) {
    if (evt.lengthComputable) {
        var percentComplete = Math.round(evt.loaded * 100 / evt.total);
       // $('#progressNumber').progressbar('setValue', percentComplete);
    }else {
        if(i!=6){
            document.getElementById('progressNumber'+i).innerHTML = '无法计算';
        }

    }
}

function uploadComplete(evt) {
    if(i=="2"){
        showTaskAttachment(taskOidFlag);
    }

    if(i =="6"){
        showWorkHour(taskOidFlag);
    }
    // 隐藏上传按钮
    if(i==6){
        $("#uploadFile"+i+dflag).hide();
    }else{
        // 后台返回信息
        var res = evt.target.responseText;
        console.log(res);

        // 清空文件名、文件大小
        document.getElementById('fileName'+i).innerHTML = "";
        document.getElementById('fileSize'+i).innerHTML = "";
        $("#uploadFile"+i).hide();
    }

    // 进度条清0
    if(res != null && res != ""){
        $('#coid'+i).val(res);
    }
}

function uploadFailed(evt) {
    $.messager.alert('温馨提示','上传出错！');
}

function uploadCanceled(evt) {
    $.messager.alert('温馨提示','上传已由用户或浏览器取消删除连！');
}

// 删除附件
function dele(i){

    var oneTest = $("#mc"+i).datagrid("getSelected");
    var coid = document.getElementById('coid'+i).value;
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

