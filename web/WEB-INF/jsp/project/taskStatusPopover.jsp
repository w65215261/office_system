<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<!-- widget content -->
<div class="widget-body">
  <div class="row">
      <span class="semi-bold" onclick="selectStatus(1);" style="display:block;margin-top: 5px;">
        <a href="javascript:void(0)" class="btn btn-primary btn-xs">
          <i class="fa fa-check-square-o"></i>
        </a>
        已完成
      </span>
      <span class="semi-bold" onclick="selectStatus(2);" style="display:block;margin-top: 5px;">
        <a href="javascript:void(0)" class="btn btn-primary btn-xs">
          <i class="fa fa-spinner"></i>
        </a>
        进行中
      </span>
      <span class="semi-bold" onclick="selectStatus(3);" style="display:block;margin-top: 5px;">
        <a href="javascript:void(0)" class="btn btn-primary btn-xs">
          <i class="fa fa-times-circle-o"></i>
        </a>
        已取消
      </span>
      <span class="semi-bold" onclick="selectStatus(4);" style="display:block;margin-top: 5px;">
        <a href="javascript:void(0)" class="btn btn-primary btn-xs">
          <i class="fa fa-clock-o"></i>
        </a>
        已延迟
      </span>
      <span class="semi-bold" onclick="selectStatus(5);" style="display:block;margin-top: 5px;">
        <a href="javascript:void(0)" class="btn btn-primary btn-xs">
          <i class="fa fa-pause"></i>
        </a>
        暂停中
      </span>
      <span class="semi-bold" onclick="selectStatus(0);" style="display:block;margin-top: 5px;">
        <a href="javascript:void(0)" class="btn btn-primary btn-xs">
          <i class="fa fa-square-o"></i>
        </a>
        未开始
      </span>
    </div>
</div>

<script type="text/javascript">

  function selectStatus(i){
    var taskId = $('#oid2').val();
    var taskOid = "${taskOid}";
    var flag = "${taskOidI}";
    if(taskOid == "1"){
      $('#statusid').removeClass();
      if(i == 0){
        $('#statusid').addClass("fa fa-square-o");
        $('#statusid').text("未开始");
      }
      if(i == 1){
        $('#statusid').addClass("fa fa-check-square-o");
        $('#statusid').text("已完成");
      }
      if(i == 2){
        $('#statusid').addClass("fa fa-spinner");
        $('#statusid').text("进行中");
      }
      if(i == 3){
        $('#statusid').addClass("fa fa-times-circle-o");
        $('#statusid').text("已取消");
      }
      if(i == 4){
        $('#statusid').addClass("fa fa-clock-o");
        $('#statusid').text("已延迟");
      }
      if(i == 5){
        $('#statusid').addClass("fa fa-pause");
        $('#statusid').text("暂停中");
      }
      $.ajax({
        type: 'POST',
        url: '/projectTask/modifyStatus.do',
        dataType: 'html',
        data:{
          oid:taskId,
          taskStatus:i
        },
        success: function(data) {

        }
      });

      $('#projectTaskStatus').popover('hide');
    }else{
      var id;
      if(flag !=""){
        id="#"+taskOid+flag;
      }else{
        id = "#"+taskOid;
      }
      $(id).children().removeClass();
      if(i == 0){
        $(id).children().addClass("fa fa-square-o");
      }
      if(i == 1){
        $(id).children().addClass("fa fa-check-square-o");
      }
      if(i == 2){
        $(id).children().addClass("fa fa-spinner");
      }
      if(i == 3){
        $(id).children().addClass("fa fa-times-circle-o");
      }
      if(i == 4){
        $(id).children().addClass("fa fa-clock-o");
      }
      if(i == 5){
        $(id).children().addClass("fa fa-pause");
      }

      $.ajax({
        type: 'POST',
        url: '/projectTask/modifyStatus.do',
        dataType: 'html',
        data:{
          oid:"${taskOid}",
          taskStatus:i
        },
        success: function(data) {

        }
      });

      $(id).popover('hide');
    }
  }
</script>