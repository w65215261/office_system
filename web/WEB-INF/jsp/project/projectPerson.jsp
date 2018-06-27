<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
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

<div class="row">
  <div>
    <div class="widget-body no-padding" style="width:97%;margin: 0 auto">
      <form action="" class="smart-form">
        <input type="hidden" name="hrefData" id="personId"/>
        <input type="hidden" name="textData" id="personCname"/>
        <header>
          成员   （${perSonNumber}个人）
        </header>
        <fieldset>
          <section>
            <label class="input">
              <img src="/smartAdmin/img/avatars/sunny.png" width="50"  class="online">
              ${projectManagerPerson}（负责人）
            </label>
          </section>
          <div class="row" id="personCount">
            <c:forEach items="${projectPersonList}" var="ppr">
              <section class="col col-3">
                <label class="input">
                  <img src="/smartAdmin/img/avatars/sunny.png" width="50"  class="online">
                  <span>${ppr.personName}&nbsp;&nbsp;<a href='javascript:void(0);' onclick='deletePerson("${ppr.oid}")'>删除</a></span>
                </label>
              </section>
            </c:forEach>
          </div>
          <section  class="col col-12 no-padding">
            <label class="input">
              <button class="btn btn-sm" type="button" onclick="showPersonModal1()" style="background-color: white;box-shadow: none">
                <i class="fa fa-plus" style="font-size: 40px;color: green"></i>
              </button>
            </label>
          </section>
        </fieldset>
      </form>
    </div>
  </div>
</div>
<script type="text/javascript">
  $('#personNumber').text('人员（'+'${perSonNumber}'+'）');

  function deletePerson(personOid){
    $.ajax({
      type:"POST",
      url:"/projectPerson/delete.do",
      data:{
        oid:personOid
      },
      success:function(data){
        if(data == "success"){
          $('#personNumber').text('人员（'+'${perSonNumber}'+'）');
          loadURL("/projectPerson/show.do?projectOid="+'${projectOid}',$('#s7'));
        }else{
          swal("","删除失败","warning");
        }

      },
      error: function () {
      }
    });
  }

  function showPersonModal1(){
    $("#personId").val("");
    $("#personCname").val("");
    swal({
              title: '<h4 class="modal-title"><p>选择成员</p></h4>',
              text: '<div class="modal-body no-padding"><div class="custom-scroll table-responsive" style="height:250px; overflow-y: auto;"><div id="personTree" style="text-align: left;"></div></div></div>',
              html: true,
              allowOutsideClick:true,
              closeOnConfirm: false
            },
            function(){
              var personOid = $("#personId").val();
              var personName = $("#personCname").val();
              if(personName != "" && personName != null){
                $.ajax({
                  type:"POST",
                  url:"/projectPerson/save.do",
                  data:{
                    projectOid:'${projectOid}',
                    personOid:personOid,
                    personName:personName
                  },
                  success:function(data){

                    if(data == "success"){
                      $('#personNumber').text('人员（'+'${perSonNumber}'+'）');
                      loadURL("/projectPerson/show.do?projectOid="+'${projectOid}',$('#s7'));
                    }else if("already"){
                      //不加这段代码，在点击其他弹出框的时候会导致organTree里的内容显示，
                      // 加了之后消除organTree里的内容时弹出框正常显示
                      swal("","","warning");
                      swal("","成员已存在","warning");
                      return;
                    }else{
                      swal("","","warning");
                      swal("","删除失败","warning");
                      return;
                    }
                    swal("","","success")
                    swal("","人员添加成功","success");
                    return;
                  },
                  error: function () {
                  }
                });
              }else{
                swal("","","warning")
                swal("","请选择添加人员","warning")
              }
            });
    loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
    loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTree);
    function renderTree() {
      $.ajax({
        type: "GET",
        url: '/organization/queryAlltree.do',
        data: {},
        success: function (data) {
          $('#personTree').treeview({
            data: data,
            onNodeSelected: function (event, data) {
              if(data.icon=="glyphicon glyphicon-tower"){
                return;
              }else{
                $("#personId").val(data.href);
                $("#personCname").val(data.text);
              }
            }
          });
        },
        // 调用出错执行的函数
        error: function () {
        }
      });
    }
  }
</script>