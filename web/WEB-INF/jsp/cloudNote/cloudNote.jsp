<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/mycloudNote/styles/main.css"/>
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
<!--  附件上传 -->
<script type="text/javascript" src="/EasyUiCompoment/noteAttachment.js"></script>
<style>
  .summernote{
    border:1px solid #a9a9a9; border-width:0px 1px 1px 1px;
    min-height: 280px;
  }
  .summernote2{
    border:1px solid #a9a9a9; border-width:1px 1px 1px 1px;
    min-height: 200px;
  }
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



<div class="row">
  <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
    <!-- MODAL PLACE HOLDER -->
    <!-- 笔记添加控件 -->
    <div class="modal fade" id="addNote" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel"
         aria-hidden="true">
      <div class="modal-dialog" style="width:600px ;height:800px">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
              &times;
            </button>
            <h4 class="modal-title"><p>添加笔记</p></h4>
          </div>
          <div class="modal-body no-padding">
            <form method="post" class="smart-form">
              <fieldset>
                <section>
                  <div class="row">
                    <div class="col col-xs-12">
                      <label class="input col col-xs-6">选择笔记本：
                        <input type="hidden" id="chooseNoteBookId" name="chooseNoteBookId">
                        <span class="col-xs-10"><input type="text" id="chooseNoteBook" name="chooseNoteBook" placeholder="选择笔记本" readonly="true"></span>
                        <span class="col-xs-1"><button type="button" onclick="chooseNoteBooks()" class="btn btn-primary" style="background-color: #0091D9;height: 30px;">
                          选择
                        </button></span>
                      </label>
                      <label class="input  col col-xs-6">填写笔记名称：
                        <input type="text" id="addNoteName" name="addNoteName" placeholder="笔记名称" maxlength="25">
                      </label>
                      <label class="input  col col-xs-12" style="margin-top: 5px;">
                        填写笔记详情：
                        <div class="summernote2" id="summernote2">
                        </div>
                      </label>
                    </div>
                  </div>
                </section>
              </fieldset>
              <footer>
                <button type="button" class="btn btn-default" data-dismiss="modal">
                  取消
                </button>
                <button type="button" onclick="addNote()" class="btn btn-primary">
                  提交
                </button>
              </footer>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- 笔记添加控件_END -->
</div>
<!-- END MODAL -->

<section id="widget-grid" class="">
  <div class="row" style='padding:0;'>
    <input type="hidden" id="noteBookOid" name="noteBookOid">
    <!-- 全部笔记 -->
    <div class="col-xs-4"  id='action_part_1' style='padding:0 0 0 0px;'>
      <div class="pc_top_first" id="noteShow" style="background-color: #5994e7">
        <div class="row">
          <section class="col-xs-12 no-padding">
            <label class="input no-padding col-xs-4" style="text-align: left">
              <button type="button" onclick="chooseBookShow('');" class="btn btn-primary col-xs-4" style="background-color: white;color: #428BCA;">
                <i class="fa fa-book"></i>
              </button>
              <button type="button" onclick="changeNoteOrRecycleBin()" class="btn btn-primary col-xs-7" id="recycleBinId" style="background-color: white;color: #428BCA;">
                回收站
              </button>
            </label>
            <label class="input col-xs-7 no-padding" style="text-align: right;">
              <input type="text" name="searchNotes" id="searchNotes" placeholder="搜索笔记"  class="col-xs-10" style="height: 32px;">
              <button type="button" onclick="searchNote()" class="btn btn-primary col-xs-2" style="background-color: white;color: #428BCA;" >
                <i class="fa fa-search"></i>
              </button>
            </label>
            <label class="input col-xs-1 no-padding" style="text-align: right;">
              <button type="button" onclick="insertNoteModal()" class="btn btn-primary" style="background-color: white;color: #428BCA;">
                <i class="fa fa-plus"></i>
              </button>
            </label>
          </section>
        </div>
      </div>
      <aside class="side-right" id='second_action' style="background-color: #F0F0F0">
        <div class="module" data-toggle="niceScroll">
          <div class="chat-contact">
            <div class="contact-body">
              <ul class="contacts-list" id="notesList">
                <c:if test="${bookSize==0}">
                  <div style="text-align: center;height: 60px;margin-top: 30px;">
                      <span style="font-size: 20px;">没有笔记本,请<a href="javascript:void(0);" onclick="chooseBookShow('-1')" style="font-size: 25px;color: #428BCA ">添加</a>新的笔记本
                      </span>
                  </div>
                </c:if>
                <c:if test="${bookSize!=0}">
                  <c:if test="${noteList.size()==0}">
                    <div style="text-align: center;height: 60px;margin-top: 30px;">
                        <span style="font-size: 20px;">没有笔记,请<a href="javascript:void(0);" onclick="insertNoteModal()" style="font-size: 25px;color: #428BCA ">添加</a>新的笔记
                        </span>
                    </div>
                  </c:if>
                </c:if>
                <c:forEach items="${noteList}" var="note" varStatus="status">
                  <li class="online" id="${note.oid}" name="${note.notebookOid}">
                    <a >
                      <div style="text-align: right;margin: 0 auto;padding-top: 10px;"class="row">
                        <div class="col-md-12 pull-left no-padding" onclick="findNoteDetails('${note.oid}','${status.index}')" >
                          <span class="col-md-1  no-padding" style="text-align: left;padding-top: 10px;"><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom" style="font-size: 50px;color: #5892e5"></i></span>
                          <span  class="col-md-1  pull-right" style="padding-top: 10px;">
                            <button type="button" class="btn btn-default btn-xs btn_delete2" onclick="deleteNote('${note.oid}','${note.notebookOid}')" style="font-size: 20px;">
                              <i class="fa fa-trash-o" style="color: #428BCA"></i>
                            </button>
                          </span>
                          <span id="${status.index}" class="col-md-6  pull-left" style="text-align: left;font-size: 15px;">${note.noteTitle}</span>
                          <span  class="col-md-6  pull-left" style="text-align: left;font-size: 15px;">修改时间：<span id="modifyTime${status.index}"><fmt:formatDate pattern="yyyy-MM-dd" value="${note.noteLastModifyTime}"/></span></span>
                        </div>

                      </div>
                    </a>
                  </li>
                </c:forEach>
              </ul>
            </div>
          </div>
        </div>
      </aside>
    </div>
    <!-- 全部笔记 -->
    <!-- 预览笔记 -->
    <div class="col-sm-6  no-padding" id='pc_part_5' >
      <div class="pc_top_third" style="border-bottom:1px solid rgba(89, 148, 231, 0.80);background-color: rgba(89, 148, 231, 0.80)">
        <div class="row">
          <div class="col-xs-9">
            <h3 style="color: white;">预览笔记</h3>
          </div>
        </div>
      </div>
      <aside class="side-right" id='fifth_side_right'>
        <div class="module" data-toggle="niceScroll"  style="background-color: white">
          <div class="chat-contact">
            <div class="contact-body clear_margin" id="content_body">
              <form class="smart-form" id="createProject">
                <input type="hidden" id="noteOid" name="noteOid">
                <fieldset>
                  <section>
                    <div class="row" >
                      <label class="col col-6  no-padding">
                        <span class="col col-3  no-padding">笔记本名称:</span>
                        <span class="col col-9  no-padding"><input type="text"  name="NoteBookNameDetails" id="NoteBookNameDetails" class="no-padding" readonly="true" style="height: 25px;width: 98%"></span>
                      </label>
                      <label class="col col-6  no-padding">
                        <span class="col col-3  no-padding">笔记名称:</span>
                        <span class="col col-9  no-padding"><input type="text"  name="noteName" id="noteName" onblur="updateNote()" class="no-padding" maxlength="25" style="height: 25px;width: 98%"></span>
                      </label>
                    </div>
                  </section>
                  <section>
                    <div class="row" >
                      <label class="col col-6  no-padding">
                        <span class="col col-3  no-padding">创建日期:</span>
                        <span class="col col-9  no-padding"><input type="text"  name="createTime" id="createTime" readonly="true" style="height: 25px;width: 98%"></span>
                      </label>
                      <label class="col col-6  no-padding">
                        <span class="col col-3  no-padding">修改日期:</span>
                        <span class="col col-9  no-padding"><input type="text"  name="modifyTime" id="modifyTime" readonly="true" style="height: 25px;width: 98%"></span>
                      </label>
                    </div>
                  </section>
                  <section>
                    <div class="row">
                      <label class="col col-6  no-padding">
                        <span class="col col-3  no-padding">附件:</span>
                        <span class="col col-9  no-padding">
                          <input  class="form-control" name="fileToUpload" id="fileToUpload" type="file" multiple="multiple"  onchange="fileSelected();" style="height: 25px;border: 1px solid #000000;width: 98%">
                          <input type="hidden" name="fileTypeId" id="fileTypeId">
                        </span>
                      </label>
                      <label class="col col-1  no-padding" style="width: 3%" id="uploadFile">
                        <span class="no-padding">
                          <button class="btn btn-sm btn-success" type="button" onclick="uploadFile()" style="background-color: #5994E7;height: 28px;">
                            <i class="glyphicon glyphicon-arrow-up" ></i>
                          </button>
                        </span>
                      </label>
                      <label class="col col-5  no-padding" style="width: 45%">
                        <span class="no-padding">
                          <div style="margin-left: 15px;" class="no-padding" id="fileName"></div>
                          <div style="margin-left: 15px;" class="no-padding" id="fileSize"></div>
                        </span>
                      </label>
                    </div>
                  </section>
                  <section>
                    <div class="row">
                      <input type="hidden"  name="noteTitleIndex" id="noteTitleIndex" class="no-padding">
                      <input type="hidden"  name="modifyTimeId" id="modifyTimeId" class="no-padding">
                      <div class="summernote" id="summernote" onblur="updateNote()">
                      </div>
                    </div>
                  </section>
                </fieldset>
              </form>
            </div>
          </div>
        </div>
      </aside>
    </div>
    <!-- 预览笔记 -->

    <div class="col-sm-2 no-padding" id='fifth_side_pull_right' >
      <div class="pc_top_third  no-padding" style="border-bottom:inherit;height: 52px;background-color: rgba(89, 148, 231, 0.59)">
        <div class="row" style="padding-top: 20px;">
          <div class="col-xs-12">
            <h4 style="color: white;">笔记附件</h4>
          </div>
        </div>
      </div>
      <aside class="side-right" id='' style="border-left: 1px solid rgba(89, 148, 231, 0.59);">
        <div class="module" data-toggle="niceScroll"  style="background-color: white">
          <div class="chat-contact">
              <div class="inbox-download col-md-12  no-padding" style="border-width: 0px 0px;" id="attachmentRead">


              </div>
          </div>
        </div>
      </aside>
    </div>

  </div>
</section>
<script type="text/javascript">
  pageSetUp();
  $("#uploadFile").hide();
  //加载DOM之后处理页面高度
  function get_dom(e){
    return document.getElementById(e);
  }
  function set_height(){
    var pc_height=window.innerHeight;
    pc_height=pc_height;
//    get_dom('first_action').style.height=pc_height+'px';
    get_dom('second_action').style.height=pc_height+'px';
    get_dom('fifth_side_right').style.height=(pc_height)+'px';
    get_dom('fifth_side_pull_right').style.height=(pc_height)+'px';
  }
  set_height();
  window.onresize=function(){
    set_height();
  }

  //笔记预览时，只有笔记是选中状态，展示详情时，才能触发事件去修改相对应的笔记详情。
  var noteFlag = 0;
  var changeNoteOrRecycleBinFlag = false;
  var onblurFlag = false;
  function editNameModal(oid,index){
    checkedEditFlag = false;
    var nbn = "#treeNoteBookName"+index;
    $(nbn).removeAttr("style");
    $(nbn).removeAttr("readonly");
    $(nbn).css({ height: "20px",width: "80%",margin: "0 auto",display:"inline-block"});
    onblurFlag = true;
  }

  function showTaskAttachment(oid){
    $('#attachmentRead').empty();
    $.ajax({
      type: "GET",
      url: '/noteAttachment/read.do',
      data:{
        noteOid:oid
      },
      success: function (data) {
        var d = [".jpg",".jpeg",".gif",".png",".bmp"];
        for(var i=0;i<data.length;i++){
          var showFlag = '<i class="fa fa-file-text-o" style="font-size: 40px;"></i>';
          var file_typename = data[i].fileMathName;
          file_typename = file_typename.substring(file_typename.lastIndexOf('.'), file_typename.length);
          if(d.toString().indexOf(file_typename) != -1){
            showFlag = '<img src="/'+data[i].fileUrl+'" height="40px" width="40px" onclick="showPicture(\''+data[i].fileUrl+'\')"/>';
          }
          $('#attachmentRead').append(
                  '<ul class="inbox-download-list col-md-12 no-padding" id="'+data[i].id+'">'+
                  '<li class="col-md-12 no-padding">'+
                  '<span class="col-md-6" style="width: 50px;">'+
                  showFlag+
                  '</span>'+
                  '<span class="col-md-9 no-padding">'+
                  '<nobr>'+data[i].fileName+'</nobr>'+
                  '</span>'+
                  '<span class="col-md-6  no-padding">'+
                  '<span style="color: #508ABC"><a href="/noteAttachment/downAttachment.do?fileUrl=' + data[i].fileUrl+'&fileName='+data[i].fileName+'" target= "_self "> 下载</a></span>'+
                  '<span style="color: #508ABC"><a href="JavaScript:deleteAttachment(\''+data[i].id+'\')">&nbsp;删除</a></span>'+
                  '</span>'+
                  '</li>'+
                  '</ul>'
          );
        }
      },
      // 调用出错执行的函数
      error: function () {

      }
    });
  }

  function showPicture(url){
      window.open("/"+url);
  }

function downAttachment(url,path){
  $.ajax({
    type: "POST",
    url: '/noteAttachment/downAttachment.do',
    data:{
      fileUrl:url,
      fileMathName:path
    },
    success: function (data) {

    },
    // 调用出错执行的函数
    error: function () {

    }
  });
}

  function deleteAttachment(oid){
    var noteAttachment = "#" + oid;
    $.ajax({
      type: "POST",
      url: '/noteAttachment/deleteAttachment.do',
      data:{
        id:oid
      },
      success: function (data) {
        if(data == "success"){
          $(noteAttachment).remove();
        }
      },
      // 调用出错执行的函数
      error: function () {

      }
    });
  }

  function findNotes(notebookOid){

    noteFlag = 0;
//    $('#noteBooks').children('li').removeAttr("style");
    $('#noteBookOid').val(notebookOid);
    $('#notesList').empty();
    $("#createTime").val("");
    $("#NoteBookNameDetails").val("");
    $("#modifyTime").val("");
    $("#noteName").val("");
    $('.summernote').code("");
    $.ajax({
      type: "POST",
      url: '/cloudNote/findNotesByNotebookOid.do',
      data:{
        notebookOid:notebookOid
      },
      success: function (data) {
        $.ajax({
          type: "GET",
          url: '/cloudNote/findBooksLength.do',
          data:{},
          success: function (length) {
            if(length == 0){
              $('#notesList').append('<div style="text-align: center;height: 60px;margin-top: 30px;">'+'<span style="font-size: 20px;">没有笔记本,请<a href="javascript:void(0);" onclick="chooseBookShow(-1)" style="font-size: 25px;color: #428BCA ">添加</a>新的笔记本</span></div>');
            }else{
              if(data.length == 0){
                $('#notesList').append('<div style="text-align: center;height: 60px;margin-top: 30px;">'+'<span style="font-size: 20px;">没有笔记,请<a href="javascript:void(0);" onclick="insertNoteModal()" style="font-size: 25px;color: #428BCA ">添加</a>新的笔记</span></div>');
              }
            }
          },
          // 调用出错执行的函数
          error: function () {
          }
        });
        for(var i=0;i<data.length;i++){
          $('#notesList').append(
                  '<li class="online" id="'+data[i].oid+'" name = "'+data[i].notebookOid+'">'+
                  '<a >'+
                  '<div style="text-align: right;margin: 0 auto;padding-top: 10px;" class="row">'+
                  '<div class="col-md-12 pull-left no-padding" onclick="findNoteDetails(\''+data[i].oid+'\','+i+')">'+
                  '<span class="col-md-1  no-padding" style="text-align: left;padding-top: 10px;"><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom" style="font-size: 50px;color: #5892e5"></i></span>'+
                  '<span  class="col-md-1  pull-right" style="padding-top: 10px;">'+
                  '<button type="button" class="btn btn-default btn-xs btn_delete2" onclick="deleteNote(\''+data[i].oid+'\',\''+data[i].notebookOid+'\')" style="font-size: 20px;">'+
                  '<i class="fa fa-trash-o" style="color: #428BCA;"></i>'+
                  '</button>'+
                  '</span>'+
                  '<span id="'+i+'" class="col-md-6  pull-left" style="text-align: left;font-size: 15px;">'+data[i].noteTitle+'</span>'+
                  '<span  class="col-md-6  pull-left" style="text-align: left;font-size: 15px;" >修改时间：<span id="modifyTime'+i+'">'+data[i].noteLastModifyTime+'</span></span>'+
                  '</div>'+
                  '</div>'+
                  '</a>'+
                  '</li>'
          );
        }
      },
      // 调用出错执行的函数
      error: function () {
      }
    });
  }

  function findNoteDetails(oid,index){
    showTaskAttachment(oid);
    $("#noteOid").val(oid);
    $("#modifyTimeId").val("modifyTime"+index);
    $("#noteTitleIndex").val(index);
    $('#notesList').children('li').removeAttr("style");
    $("#"+oid).attr("style","background-color: #428BCA");
    noteFlag = 1;
    $.ajax({
      type: "POST",
      url: '/cloudNote/findNoteDetailsByNoteOid.do',
      data:{
        oid:oid
      },
      success: function (data) {
        $("#NoteBookNameDetails").val(data.noteBookName);
        $("#createTime").val(data.noteCreateTime);
        $("#modifyTime").val(data.noteLastModifyTime);
        $("#noteName").val(data.noteTitle);
        $('.summernote').code(data.noteBody);
      },
      // 调用出错执行的函数
      error: function () {
      }
    });

  }

  function addNote(){
    var noteBookOid = $("#chooseNoteBookId").val();
    var noteName = $("#addNoteName").val();
    var noteBody = $('.summernote2').code();
    if(noteName ==""||noteName ==null){
      swal("","请输入笔记名称","warning");
      return;
    }
    if(noteBookOid !=""&&noteBookOid !=null){
      $.ajax({
        type: "POST",
        url: '/cloudNote/addNote.do',
        data:{
          notebookOid:noteBookOid,
          noteTitle:noteName,
          noteBody:noteBody
        },
        success: function (data) {
          if(data == "success"){
            findNotes(noteBookOid);
          }
          $("#addNote").modal("hide");
        },
        // 调用出错执行的函数
        error: function () {
        }
      });
    }else{
      swal("","请选择一个笔记本","warning");
    }
  }

  function deleteNote(oid,noteBookOids){
    var noteBookOid = '';
    if($("#noteBookOid").val() != ""){
      noteBookOid = noteBookOids;
    }
    $.ajax({
      type: "POST",
      url: '/cloudNote/deleteNote.do',
      data:{
        oid:oid
      },
      success: function (data) {
        if(data == "success"){
          findNotes(noteBookOid);
          $("#createTime").val("");
          $("#NoteBookNameDetails").val("");
          $("#modifyTime").val("");
          $("#noteName").val("");
          $('.summernote').code("");
        }
      },
      // 调用出错执行的函数
      error: function () {
      }
    });
  }


  function addNoteBook(){
    showHideFlag = true;
      $.ajax({
        type: "POST",
        url: '/cloudNote/addNoteBook.do',
        data:{
          notebookName:"新笔记本"
        },
        success: function () {
          $("#nestableG").empty();
          loadScript("/smartAdmin/js/plugin/jquery-nestable/jquery.nestable.min.js", renderTreeSweet);
        },
        // 调用出错执行的函数
        error: function () {
        }
      });
  }

  function modifyNoteBookName(oid,index){
    checkedEditFlag = true;
    if(!onblurFlag){
      return;
    }
    onblurFlag = false;
    var nbn = "#treeNoteBookName"+index;
    var editNoteBookName =$(nbn).val();
    $(nbn).removeAttr("style");
    $(nbn).attr("readonly","readonly");
    $(nbn).css({ height: "20px",width: "80%",margin: "0 auto",display:"inline-block",border:"none"});
    var editNoteBookNameOid = oid;
    if(editNoteBookName.length==0){
      swal("","笔记本名称长度不能为空","warning");
    }else{
      $.ajax({
        type: "POST",
        url: '/cloudNote/modifyNoteBook.do',
        data:{
          notebookName:editNoteBookName,
          oid:editNoteBookNameOid
        },
        success: function (data) {
          if(data == "success"){
            $(index).text(editNoteBookName);
          }
          $("#editNoteBook").modal("hide");
        },
        // 调用出错执行的函数
        error: function () {
        }
      });
    }
  }

  var oDate = new Date(); //实例一个时间对象；
  var year = oDate.getFullYear();   //获取系统的年；
  var month = oDate.getMonth()+1;   //获取系统月份，由于月份是从0开始计算，所以要加1
  var day = oDate.getDate(); // 获取系统日，
  month = month+"";
  if(month.length == 1){
    month = "0"+month;
  }
  var date = year+"-"+month+"-"+day

  function updateNote(){
    if(noteFlag == 1){
      var noteBookOid = $("#noteBookOid").val();
      var noteCreateTime= $("#createTime").val();
      var noteTitle = $("#noteName").val();
      var noteBody = $('.summernote').code();
      var oid = $("#noteOid").val();
      var noteTitleIndex = $("#noteTitleIndex").val();
      var modifyTimeId = $("#modifyTimeId").val();
      if(noteTitle == ""||noteTitle ==null){
        swal("","笔记名称不能为空","warning");
      }else{
        $("#"+noteTitleIndex).text(noteTitle);
        $("#"+modifyTimeId).text(date);
        $.ajax({
          type: "POST",
          url: '/cloudNote/modifyNoteDetails.do',
          data:{
            oid:oid,
            noteTitle:noteTitle,
            noteBody:noteBody,
            noteCreateTime:noteCreateTime
          },
          success: function (data) {
            $("#modifyTime").val(date);
          },
          // 调用出错执行的函数
          error: function () {
          }
        });

      }
    }
  }

  function insertNoteModal(){
    changeNoteOrRecycleBinFlag = false;
    $("#recycleBinId").removeAttr("style");
    $("#recycleBinId").css({ "background-color": "white",color: "#428BCA"});
    $("#addNoteName").val("");
    $('.summernote2').code("");
    $("#addNote").modal("show");
  }


  function changeNoteOrRecycleBin(){
      if(changeNoteOrRecycleBinFlag){
        changeNoteOrRecycleBinFlag = false;
        $("#recycleBinId").removeAttr("style");
        $("#recycleBinId").css({ "background-color": "white",color: "#428BCA"});
        noteShow();
      }else{
        changeNoteOrRecycleBinFlag = true;
        $("#recycleBinId").removeAttr("style");
        $("#recycleBinId").css({ "background-color": "#428BCA",color: "white"});
        recycleBinShow();
    }
  }

  function noteShow(){
    findNotes("");
  }

  function recycleBinShow(){
    recycleBinNotes();
  }

  function recycleBinNotes(){
    $('#notesList').empty();
    $("#createTime").val("");
    $("#NoteBookNameDetails").val("");
    $("#modifyTime").val("");
    $("#noteName").val("");
    $('.summernote').code("");
    $.ajax({
      type: "POST",
      url: '/cloudNote/findRecycleBin.do',
      data:{
      },
      success: function (data) {
        for(var i=0;i<data.length;i++){
          $('#notesList').append(
                  '<li class="online" id="'+data[i].oid+'" name = "'+data[i].notebookOid+'">'+
                  '<a >'+
                  '<div style="text-align: right;margin: 0 auto;padding-top: 10px;" class="row">'+
                  '<div class="col-md-12 pull-left no-padding" onclick="findNoteDetails(\''+data[i].oid+'\','+i+')">'+
                  '<span class="col-md-1  no-padding" style="text-align: left;padding-top: 10px;"><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom" style="font-size: 50px;color: #5892e5"></i></span>'+
                  '<span  class="col-md-1  pull-right" style="padding-top: 10px;">'+
                  '<button type="button" class="btn btn-default btn-xs btn_delete2" onclick="deleteNoteTrue(\''+data[i].oid+'\',\''+data[i].notebookOid+'\')" style="font-size: 20px;">'+
                  '<i class="fa fa-trash-o" style="color: #428BCA;"></i>'+
                  '</button>'+
                  '</span>'+
                  '<span  class="col-md-1  pull-right" style="padding-top: 10px;">'+
                  '<button type="button" class="btn btn-default btn-xs btn_edit" onclick="restoreNote(\''+data[i].oid+'\',\''+data[i].notebookOid+'\')" style="font-size: 20px;">'+
                  '<i class="fa fa-mail-reply" style="color: #428BCA;"></i>'+
                  '</button>'+
                  '</span>'+
                  '<span id="'+i+'" class="col-md-6  pull-left" style="text-align: left;font-size: 15px;">'+data[i].noteTitle+'</span>'+
                  '<span  class="col-md-6  pull-left" style="text-align: left;font-size: 15px;" >修改时间：<span id="modifyTime'+i+'">'+data[i].noteLastModifyTime+'</span></span>'+
                  '</div>'+
                  '</div>'+
                  '</a>'+
                  '</li>'
          );
        }
      },
      // 调用出错执行的函数
      error: function () {
      }
    });
  }


  var noteBookName = "";
  var noteBookNameId = "";

  function chooseNoteBooks(){
    noteBookName = "";
    noteBookNameId = "";
    swal({
              title: '<h4 class="modal-title"><p>选择笔记本</p></h4>',
              text: '<div class="modal-body no-padding"><div class="custom-scroll table-responsive" style="height:250px; overflow-y: auto;"><div id="organBookTree" style="text-align: left;"></div></div></div>',
              html: true,
              allowOutsideClick:true
            },
            function(){
              $("#chooseNoteBookId").val(noteBookNameId);
              $("#chooseNoteBook").val(noteBookName);
              //不加这段代码，在点击其他弹出框的时候会导致organTree里的内容显示，
              // 加了之后消除organTree里的内容时弹出框正常显示
              swal("","","success");
            });
    loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
    loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderBookTreeSweet);
    function renderBookTreeSweet() {
      $.ajax({
        type: "GET",
        url: '/cloudNote/queryAlltree.do',
        data: {},
        success: function (data) {
          $('#organBookTree').treeview({
            data: data,
            onNodeSelected: function (event, data) {
              if(data.icon=="glyphicon glyphicon-briefcase"){
                return;
              }else{
                noteBookName = data.text;
                noteBookNameId=data.href;
              }
            },
            onNodeUnselected:function(event, data){
              noteBookName = "";
              noteBookNameId = "";
            }
          });
        },
        // 调用出错执行的函数
        error: function () {
        }
      });
    }
  }



  function chooseBookShow(oid){
    changeNoteOrRecycleBinFlag = false;
    $("#recycleBinId").removeAttr("style");
    $("#recycleBinId").css({ "background-color": "white",color: "#428BCA"});
    noteBookName = "";
    noteBookNameId = oid;
    swal({
              type:"input",
              title: '<h4 class="modal-title"><p>选择笔记本</p></h4>',
              text: '<div style="margin: 0 auto;text-align: right"><p onclick="addNoteBook()" class="btn btn-primary" style="background-color: white;color: #0091D9;margin: 0 auto;padding: inherit;border: inherit">'+
              '<i class="fa fa-plus">添加</i>'+
              '</p></div>'+
              '<div class="" style="height: 300px;overflow:auto;">' +
              '<div class="no-padding" id="nestableG">'+
              '</div>'+
              '</div>',
              html: true,
              allowOutsideClick:true
            },
            function(){
              if(noteBookNameId != "" && noteBookNameId!="-1"){
                $("#chooseNoteBookId").val(noteBookNameId);
                $("#chooseNoteBook").val(noteBookName);
                findNotes(noteBookNameId)
              }else if(noteBookNameId == "-1"){
                $("#chooseNoteBookId").val("");
                $("#chooseNoteBook").val("");
                findNotes("")
              }
              //不加这段代码，在点击其他弹出框的时候会导致organTree里的内容显示，
              // 加了之后消除organTree里的内容时弹出框正常显示
              swal("","","success");
            });
    loadScript("/smartAdmin/js/plugin/jquery-nestable/jquery.nestable.min.js", renderTreeSweet);
  }
  var showHideFlag = false;
  function renderTreeSweet() {
    $.ajax({
      type: "GET",
      url: '/cloudNote/queryAlltree.do',
      data: {},
      success: function (data) {
        $("#nestableG").empty();
        for(var i=0;i<data.length;i++){
          var noteBook = data[i].nodes;
          $("#nestableG").append(
                  '<ol class="">   '+
                  '<div class="" style="text-align: left;float: left;margin: 0 auto;height: 35px;padding-top: 6px;" id="plusAndMinus" ><button class="glyphicon glyphicon-plus no-padding" type="button" style="color: #0091D9;margin: 0 auto;background-color: inherit"></button></div>'+
                  '<li class="" style="list-style:none" >'+
                  '<div class="dd3-content" style="border: 1px dotted #E0E4E8;background-color: white;margin: 0 auto;padding-left: 0px;" >'+
                  '<div style="text-align: left;padding-left: 20px;" id="allNoteBook" onclick="checkedBook(\''+data[i].text+'\',\'-1\',\'-2\')">'+
                  '<i class="fa fa-book" style="color: #0091D9;" ></i>'+
                  '<span id="books">'+data[i].text+
                  '</span>'+
                  '</div>'+
                  '</div>'+
                  '<ol class="dd-list no-padding" id="noteBooks" style="padding-left: 20px;">'+

                  '</ol>'+
                  '</li>'+
                  '</ol>'
          );
          for(var k=0;k<noteBook.length;k++){
            $("#noteBooks").append(
                    '<li class="dd-item dd3-item" id="'+noteBook[k].href+'">'+
                    '<div class="dd3-content"  style="border: 1px dotted #E0E4E8;background-color:white;margin: 0 auto;padding-left: 0px;margin-left: 20px;" >'+
                    '<div class="seleteColor" style="text-align: left;padding-top: 3px;" id="seleteColor'+k+'" onclick="checkedBook(\''+noteBook[k].text+'\',\''+noteBook[k].href+'\','+k+')">'+
                    '<span style="padding-left: 5px;"></span>'+
                    '<i class="fa fa-book" style="color: #0091D9;" ></i>' +
                    '<input class="seleteColorInput" id="treeNoteBookName'+k+'" type="text" size="6" value="'+noteBook[k].text+'" style="height: 20px;width: 80%;margin: 0 auto;display:inline-block;border:none" readonly="true" onblur="modifyNoteBookName(\''+noteBook[k].href+'\','+k+')" maxlength="25"/>'+
                    '<span class="pull-right" onclick="deleteNoteBook(\''+noteBook[k].href+'\')" style="margin-left: 5px;">'+
                    '<button type="button" style="background-color: inherit;margin: 0 auto;padding: inherit;">'+
                    '<i class="fa fa-trash-o" style="color: #0091D9;"></i>'+
                    '</button>'+
                    '</span>'+
                    '<span class="pull-right" onclick="editNameModal(\''+noteBook[k].href+'\','+k+')">'+
                    '<button type="button" style="background-color: inherit;margin: 0 auto;padding: inherit;">'+
                    '<i class="fa fa-edit" style="color: #0091D9;"></i>'+
                    '</button>'+
                    '</span>'+

                    '</div>'+
                    '</div>'+
                    '</li>'
            );
          }
        }

        var toggleFlag = false;
        if(showHideFlag){
          $("#noteBooks").show();
          $("#plusAndMinus").children("button").removeClass();
          $("#plusAndMinus").children("button").addClass("glyphicon glyphicon-minus no-padding");
          toggleFlag = false;
          showHideFlag = false;
        }else{

          if(noteBook.length == 0){
            $("#plusAndMinus").children("button").removeClass();
            $("#plusAndMinus").children("button").addClass("no-padding");
          }else{
            $("#plusAndMinus").children("button").removeClass();
            $("#plusAndMinus").children("button").addClass("glyphicon glyphicon-minus no-padding");
          }
          $("#noteBooks").show();
        }

        $("#plusAndMinus").click(function(){
          if(!toggleFlag){
            $("#plusAndMinus").children("button").removeClass();
            $("#plusAndMinus").children("button").addClass("glyphicon glyphicon-plus no-padding");
            toggleFlag = true;
          }else{
            $("#plusAndMinus").children("button").removeClass();
            $("#plusAndMinus").children("button").addClass("glyphicon glyphicon-minus no-padding");
            toggleFlag = false;
          }

          $("#noteBooks").toggle();
        });
      },
      // 调用出错执行的函数
      error: function () {
      }
    });
  }

  function searchNote(){
    changeNoteOrRecycleBinFlag = false;
    $("#recycleBinId").removeAttr("style");
    $("#recycleBinId").css({ "background-color": "white",color: "#428BCA"});
    var searchNotes = $("#searchNotes").val();
    $.ajax({
      type: "POST",
      url: '/cloudNote/searchNote.do',
      data:{
        noteTitle:searchNotes
      },
      success: function (data) {
        $('#notesList').empty();
        for(var i=0;i<data.length;i++){
          $('#notesList').append(
                  '<li class="online" id="'+data[i].oid+'" name = "'+data[i].notebookOid+'">'+
                  '<a >'+
                  '<div style="text-align: right;margin: 0 auto;padding-top: 10px;" class="row">'+
                  '<div class="col-md-12 pull-left no-padding" onclick="findNoteDetails(\''+data[i].oid+'\','+i+')">'+
                  '<span class="col-md-1  no-padding" style="text-align: left;padding-top: 10px;"><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom" style="font-size: 50px;color: #5892e5"></i></span>'+
                  '<span  class="col-md-1  pull-right" style="padding-top: 10px;">'+
                  '<button type="button" class="btn btn-default btn-xs btn_delete2" onclick="deleteNote(\''+data[i].oid+'\','+data[i].notebookOid+')" style="font-size: 20px;">'+
                  '<i class="fa fa-trash-o" style="color: #428BCA"></i>'+
                  '</button>'+
                  '</span>'+
                  '<span id="'+i+'" class="col-md-6  pull-left" style="text-align: left;font-size: 15px;">'+data[i].noteTitle+'</span>'+
                  '<span  class="col-md-6  pull-left" style="text-align: left;font-size: 15px;" >修改时间：<span id="modifyTime'+i+'">'+data[i].noteLastModifyTime+'</span></span>'+
                  '</div>'+
                  '</div>'+
                  '</a>'+
                  '</li>'
          );
        }
      },
      // 调用出错执行的函数
      error: function () {
      }
    });
  }
  function restoreNote(oid,notebookOid){
    var restoreOid = "#"+oid;
    $.ajax({
      type: "POST",
      url: '/cloudNote/restoreNote.do',
      data:{
        oid:oid,
        notebookOid:notebookOid
      },
      success: function (data) {
        $(restoreOid).empty();
      },
      // 调用出错执行的函数
      error: function () {
      }
    });
  }
  //真删除
  function deleteNoteTrue(oid,notebookOid){
    $.ajax({
      type: "POST",
      url: '/cloudNote/deleteNoteTrue.do',
      data:{
        oid:oid,
        notebookOid:notebookOid
      },
      success: function (data) {
        if(data == "success"){
          recycleBinNotes();
        }
      },
      // 调用出错执行的函数
      error: function () {
      }
    });
  }
  function deleteNoteBook(oid){
    var noteBookOidFlag = "#" + oid;
    var nameId = "li[name="+oid+"]";
    $(nameId).remove();
    $.ajax({
      type: "POST",
      url: '/cloudNote/deleteNoteBook.do',
      data:{
        oid:oid
      },
      success: function (data) {
        if(data == "success"){
          $(noteBookOidFlag).remove();
          $("#chooseNoteBookId").val("");
          $("#chooseNoteBook").val("");
          noteBookNameId = "-1"
        }
      },
      // 调用出错执行的函数
      error: function () {
      }
    });
  }
  var checkedEditFlag = true;
  var checkedBookFlag = false;
  function checkedBook(bookName,bookNameId,index){
    if(!checkedEditFlag){
        return;
    }
    var nbn = "#treeNoteBookName"+index;
    var seleteColor = "#seleteColor" + index;

    $(nbn).removeAttr("style");
    $(".seleteColorInput").attr("readonly","readonly");
    $("#allNoteBook").css({color:"black"});
    $("#allNoteBook").css({ "background-color": "white"});
    $(".seleteColorInput").css({ height: "20px",width: "80%",margin: "0 auto",display:"inline-block",border:"none","background-color": "white",color:"black"});
    $(".seleteColor").css({ "background-color": "white"});
    if(bookNameId == "-1" && index == "-2"){
        $("#allNoteBook").css({color:"white"});
        $("#allNoteBook").css({ "background-color": "#428BCA"});
    }else{
      $(nbn).css({ height: "20px",width: "80%",margin: "0 auto",display:"inline-block",border:"none","background-color": "#428BCA",color:"white"});
      $(seleteColor).css({ "background-color": "#428BCA"});
    }


    noteBookName = bookName;
    noteBookNameId = bookNameId;
  }

  var pagefunction = function() {
    $('.summernote').summernote({
      height : 90,
      focus : false,
      airMode:false
    });
    $('.summernote2').summernote({
      height : 90,
      focus : false,
      airMode:false
    });
    $('#summernote').summernote({
      onblur: function(e) {
        updateNote();
      }
    });
  };
    loadScript("/smartAdmin/js/plugin/summernote/summernote.min.js", pagefunction);

</script>