<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
<link rel="stylesheet" href="/mycloudNote/styles/personMain.css"/>

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
  <section id="widget-grid" class="">
    <div class="row no-padding" style=" margin: auto;">
      <div class="col-xs-3  no-padding"  id='action_part_1'>
        <div class="pc_top_first no-padding" style="background-color: #0055AA;border-right: 1px solid green;height: 40px;">
          <span style="color: white;display: block;padding-top: 10px;padding-left: 5px;" >组织架构</span>
        </div>
        <aside class="side-right" id='first_action'>
          <div class="module" data-toggle="niceScroll" style="background-color: white">
            <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0"  data-widget-togglebutton="false"
                 data-widget-deletebutton="false" data-widget-fullscreenbutton="false"
                 data-widget-colorbutton="false" data-widget-editbutton="false"  data-widget-custombutton="true">
              <div>
                <div class="jarviswidget-editbox">
                </div>
                <div class="widget-body no-padding">
                  <div class="custom-scroll table-responsive" style="overflow-y: scroll;">
                    <div id="organTree">
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </aside>
      </div>

      <div class="col-sm-9 no-padding" id='pc_part_5'>
            <div class="pc_top_first no-padding" style="background-color: #0055AA;border-left: 1px solid green;height: 40px;">
                <span style="color: white;display: block;padding-top: 10px;padding-left: 5px;">组织人员</span>
            </div>
        <aside class="side-right" id='fifth_side_right'>
          <div class="module no-padding" data-toggle="niceScroll" style="background-color: white">
            <div class="row no-padding" style="margin: auto;">


              <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">


                <!-- MODAL PLACE HOLDER -->
                <div class="modal fade" id="remoteModal" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel"
                     aria-hidden="true">
                  <div class="modal-dialog" style="width:800px ;height:600px;padding-top: 50px;">
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                          &times;
                        </button>
                        <h4 class="modal-title no-padding" style="border: 0"><p id="saveOrUpdate"></p></h4>
                      </div>
                      <div class="modal-body no-padding">
                        <form action="/personInfo/save.do" method="post" id="login-form" class="smart-form">
                          <input type="hidden" id="personId" name="personId">
                          <input type="hidden" id="sorting" name="sorting">
                          <fieldset>
                            <section class="no-padding">
                              <div class="row">
                                <label class="label col col-2">用户名：</label>

                                <div class="col col-4">
                                  <label class="input" id="appendMessage">
                                    <input class="form-control " id="userEname" name="userEname"
                                           type="text" placeholder="填写用户名" onchange="userEnameCheck()">
                                  </label>

                                </div>

                                <label class="label col col-2">用户昵称：</label>

                                <div class="col col-4">
                                  <label class="input">
                                    <input class="form-control " id="userCname" name="userCname"
                                           type="text" placeholder="填写用户昵称">
                                  </label>

                                </div>
                              </div>
                            </section>

                            <section class="no-padding">
                              <div class="row">
                                <label class="label col col-2">手机号：</label>
                                <div class="col col-4">
                                  <label class="input">
                                    <input type="text" id="telephone" name="telephone" placeholder="填写手机号">
                                  </label>
                                </div>
                                <label class="label col col-2">邮件：</label>
                                <div class="col col-4">
                                  <label class="input">
                                    <input type="text" id="userMail" name="userMail" placeholder="填写邮件">
                                  </label>
                                </div>
                              </div>
                            </section>
                            <section class="no-padding">
                              <div class="row">
                                <label class="label col col-2">部门：</label>
                                <div class="col col-4">
                                  <label class="input">
                                    <input type="hidden" id="departmentId" name="departmentId">
                                    <input type="text" id="department" name="department" placeholder="选择部门" onclick="showModal();">
                                  </label>
                                </div>
                              </div>
                            </section>
                          </fieldset>

                          <footer>

                            <button type="button" class="btn btn-default" data-dismiss="modal">
                              取消
                            </button>
                            <button type="button" onclick="savePerson()" class="btn btn-primary">
                              提交
                            </button>
                          </footer>

                        </form>



                      </div>


                    </div>
                  </div>
                </div>
                <!-- END MODAL -->


              </div>

              <section class="">


                <!-- row -->
                <div>
                  <article class="col-sm-12 col-md-12 col-lg-12 no-padding" style="margin: auto;">
                    <div class="jarviswidget jarviswidget-color-darken"  data-widget-editbutton="false"
                         data-widget-togglebutton="false"
                         data-widget-deletebutton="false" data-widget-fullscreenbutton="false"
                         data-widget-colorbutton="false"  data-widget-custombutton="true" data-widget-sortable="false">
                      <header>
                        <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                        <div class="widget-toolbar">
                          <input onclick="addPerson()" class="btn  btn-success btn-xs " type="button" value="新增">
                          <input id="updateButton" class="btn  btn-primary btn-xs " type="button" value="修改">
                          <input type="button" id="button" class="btn  btn-danger btn-xs " value="删除">
                        </div>
                      </header>
                      <div>
                        <div class="jarviswidget-editbox">
                        </div>
                        <div class="widget-body no-padding">

                          <table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
                            <thead>
                            <tr>
                              <th data-class="expand"> 姓名</th>
                              <th data-hide="phone"> 用户名</th>
                              <th data-class="expand">职位</th>
                              <th data-hide="phone">电话</th>
                              <th data-hide="phone">权限</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                          </table>

                        </div>
                      </div>
                    </div>
                  </article>
                </div>
              </section>
            </div>
          </div>
        </aside>
      </div>
    </div>
  </section>
<script type="text/javascript">
  pageSetUp();
  var table = "";
  var messageFlag = true;
  // Load form valisation dependency
  loadScript("/smartAdmin/js/plugin/jquery-form/jquery-form.min.js", function () {
  });
  // Registration validation script    var flag = $('#netForm').form('validate');
  var $loginForm = $("#login-form").validate({

    // Rules for form validation
    rules: {
      userEname: {
        required: true,
        maxlength:20
      },
      userCname: {
        required: true,
        maxlength:20
      }, telephone: {
        required: true,
        number:true,
        minlength:11,
        maxlength:11

      }, userMail: {
        required: true,
        email:true
      }, department: {
        required: true
      }
    },

    messages: {
      userEname: {
        required: "请将信息填写完整",
        maxlength:"用户名最多20位"
      },
      userCname: {
        required: "请将信息填写完整",
        maxlength:"昵称最多20位"
      }, telephone: {
        required: "请将信息填写完整",
        minlength:"手机号长度为11位",
        maxlength:"手机号长度为11位",
        number:"请按照正确的电话格式填写"
      }, userMail: {
        required: "请将信息填写完整",
        email:"请按照正确的邮件格式填写"
      }, department: {
        required: "请将信息填写完整"
      }
    },

    errorPlacement: function (error, element) {
      error.insertAfter(element.parent());
    }
  });


  function savePerson(){
    $('#login-form').submit(function(event) {
      event.preventDefault();
    });

    $('#login-form').submit(); //触发绑定事件；
    var validator =  $('#login-form').validate();
    if (validator.numberOfInvalids() <= 0) { //判断加入所有校验都通过后再做ajax提交；
      var massageId = $("#massageId").text();
      var personId = $("#personId").val();
      var userCname = $("#userCname").val();
      var userEname = $("#userEname").val();
      var telephone = $("#telephone").val();
      var userMail = $("#userMail").val();
      var departmentId = $("#departmentId").val();
      var sorting = $("#sorting").val();

      if(massageId != ""){
          return;
      }
      $.ajax({
        type: "POST",
        url: '/personInfo/save.do',
        data: {
          id: personId,
          userEname:userEname,
          userCname:userCname,
          telephone:telephone,
          userMail:userMail,
          departmentId:departmentId,
          sorting:sorting
        },
        success: function (data) {
          if(data == "success"){
            $('#dt_basic').DataTable().ajax.reload();
            $("#remoteModal").modal("hide");
          }else if(data == "repeat"){
            swal("","用户名已存在","warning");
          }

        },
        // 调用出错执行的函数
        error: function () {
        }
      });
    }
  }


  //加载DOM之后处理页面高度
  function get_dom(e){
    return document.getElementById(e);
  }

  function set_height(){
    var pc_height=window.innerHeight;
    pc_height=pc_height-105;
    get_dom('first_action').style.height=pc_height+'px';
    get_dom('fifth_side_right').style.height=(pc_height)+'px';
  }

  set_height();

  window.onresize=function(){
    set_height();
  }

  loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
  loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTree);


  function renderTree() {
    $.ajax({
      type: "GET",
      url: '/organization/queryAllOrganTree.do',
      data: {},
      success: function (data) {
        $('#organTree').treeview({
          data: data,
          onNodeSelected: function (event, data) {
            if(data.icon=="glyphicon glyphicon-tower"){
              var departmentId=data.href;
              $('#dt_basic').DataTable().ajax.url('/personInfo/queryByDepartmentId.do?departmentId='+departmentId).load();
            }else{
              return;
            }



          }
        });
      },
      // 调用出错执行的函数
      error: function () {
      }
    });
  }


  var pagefunction = function () {
    table = $('#dt_basic').DataTable({

      "dom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f>>" +
      "t" +
      "<'dt-toolbar-footer'<'col-sm-4	  hidden-xs'i><'col-xs-4 col-sm-3'l><'col-xs-6 col-sm-5'p>>",


      "bFilter": false,
      "aLengthMenu": [[10, 20, 50, 100], [10, 20, 50, 100]],
      "aaSorting": [[0, "asc"]],
      "bAutoWidth": true,
      "bLengthChange": true,
      "iDisplayLength": 10,
      "bServerSide": true,
      "bStateSave": false,
      "sAjaxSource": "/personInfo/query.do",
      "oLanguage": {                          //汉化
        "sLengthMenu": "每页 _MENU_ 条记录",
        "sZeroRecords": "没有检索到数据",
        "sInfo": "第 _START_ 到第 _END_ 条数据,共有 _TOTAL_ 条",
        "sInfoEmpty": "<span class='text-danger'>没有数据</span>",
        "sProcessing": "正在加载数据...",
        "oPaginate": {
          "sFirst": "首页",
          "sPrevious": "前页",
          "sNext": "后页",
          "sLast": "尾页"
        }
      },
      "columnDefs": [

        {
          "targets": 0,
          "data": "userCname",
          "sWidth": "12%"
        },
        {
          "targets": 1,
          "data": "userEname",
          "sWidth": "12%"
        },
        {
          "targets": 2,
          "data": "duty",
          "sWidth": "12%"
        },
        {
          "targets": 3,
          "data": "telephone",
          "sWidth": "12%"
        },
        {
          "targets": 4,
          "data": "power",
          "sWidth": "12%"
        }
      ]

    });


    $('#dt_basic tbody').on('click', 'tr', function () {
      $(this).toggleClass('selected');
    });
    //删除
    $('#button').click(function () {




      // console.log( table.rows('.selected').row(0).data().id );
      // table.row('.selected').remove().draw( false );
      var ids = '';
      for (var i = 0; i < table.rows('.selected').data().length; i++) {
        //console.log(table.rows('.selected').row(i).data().id);
        ids += table.rows('.selected').data()[i].id + ",";
      }
      $.ajax({
        type: "POST",
        url: '/personInfo/delete.do',
        data: {
          oids: ids
        },
        success: function (data) {
          if (data == "success") {
            $('#dt_basic').DataTable().ajax.reload();

          } else {
          }
        },
        // 调用出错执行的函数
        error: function () {
        }
      });


    });


    $('#updateButton').click(function () {
      if (table.rows('.selected').data().length != 1) {
        swal("","请选择一条人员数据","warning");
      } else {
        $("#personId").val("");
        $("#userCname").val("");
        $("#userEname").val("");
        $("#telephone").val("");
        $("#userMail").val("");
        $("#departmentId").val("");
        $("#department").val("");
        $("#sorting").val(table.rows('.selected').data()[0].sorting);
        $("#personId").val(table.rows('.selected').data()[0].id);
        $("#userCname").val(table.rows('.selected').data()[0].userCname);
        $("#userEname").val(table.rows('.selected').data()[0].userEname);
        $("#telephone").val(table.rows('.selected').data()[0].telephone);
        $("#userMail").val(table.rows('.selected').data()[0].userMail);
        $("#departmentId").val(table.rows('.selected').data()[0].departmentId);
        $("#department").val(table.rows('.selected').data()[0].department);
        $("#saveOrUpdate").text("修改人员信息");
        $("#remoteModal").modal("show");
      }
    });


  };

  loadScript("/smartAdmin/js/plugin/datatables/jquery.dataTables.min.js", function () {
    loadScript("/smartAdmin/js/plugin/datatables/dataTables.colVis.min.js", function () {
      loadScript("/smartAdmin/js/plugin/datatables/dataTables.tableTools.min.js", function () {
        loadScript("/smartAdmin/js/plugin/datatables/dataTables.bootstrap.min.js", function () {
          loadScript("/smartAdmin/js/plugin/datatable-responsive/datatables.responsive.min.js", pagefunction)
        });
      });
    });
  });

  function addPerson(){
    $("#saveOrUpdate").text("添加人员");
    $("#personId").val("");
    $("#userCname").val("");
    $("#userEname").val("");
    $("#telephone").val("");
    $("#userMail").val("");
    $("#departmentId").val("");
    $("#department").val("");
    $("#sorting").val("");
    $("#remoteModal").modal("show");
  }

  var departmentId = '';
  var department = '';
  function showModal(){
    swal({
              title: '<h4 class="modal-title"><p>选择部门</p></h4>',
              text: '<div class="modal-body no-padding"><div class="custom-scroll table-responsive" style="height:250px; overflow-y: auto;"><div id="organPersonTree" style="text-align: left;"></div></div></div>',
              html: true,
              allowOutsideClick:true
            },
            function(){
              $('#department').val(department);
              $('#departmentId').val(departmentId);
              $.ajax({
                type: "GET",
                url: '/personManage/selectSorting.do',
                data: {
                  personUintId:departmentId
                },
                success: function (data) {
                  if(data == ""){
                    $('#sorting').val("0");
                  }else{
                    $('#sorting').val(data.sorting);
                  }

                },
                // 调用出错执行的函数
                error: function () {
                }
              });
              //不加这段代码，在点击其他弹出框的时候会导致organTree里的内容显示，
              // 加了之后消除organTree里的内容时弹出框正常显示
              swal("","","success");
            });
    loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
    loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTreeSweet);
    function renderTreeSweet() {
      $.ajax({
        type: "GET",
        url: '/organization/queryAllOrganTree.do',
        data: {},
        success: function (data) {
          $('#organPersonTree').treeview({
            data: data,
            onNodeSelected: function (event, data) {
              if(data.icon=="glyphicon glyphicon-tower"){
                department = data.text;
                departmentId=data.href;
              }else{
                return;
              }
            },
            onNodeUnselected:function(event, data){
              department = "";
              departmentId = "";
            }
          });
        },
        // 调用出错执行的函数
        error: function () {
        }
      });
    }
  }

  /**
   * 用户名英文验证
   */
  function userEnameCheck(){

    var userEname = $("#userEname").val();
    var re=/^[a-zA-Z]+$/;
    if(!re.test(userEname)){
      if(!messageFlag){
        $("#massageId").show();
        $("#massageId").text("用户名必须是英文");
      }else{
        messageFlag = false;
        $("#appendMessage").append("<span id='massageId' style='color: #D56161;font-size: 10px;'>用户名必须是英文</span>");
      }
    }else{
      if(!messageFlag){
        $("#massageId").text("");
        $("#massageId").hide();
      }
    }


  }

</script>
  <style type="text/css">
    table.dataTable tbody tr.selected {
      background-color: #b0bed9;
    }
  </style>