<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
  <thead>
  <tr>
    <th data-class="phone">申请人</th>
    <th data-hide="phone">申请时间</th>
    <th data-hide="phone">所在部门</th>
    <th data-hide="phone">会议名称</th>
    <th data-hide="phone">会议地点</th>
    <th data-hide="phone">主办方</th>
    <th data-hide="phone">开始时间</th>
    <th data-hide="phone">结束时间</th>
    <th data-hide="phone">审批状态</th>
  </tr>
  </thead>
  <tbody>

  </tbody>
</table>
<script>
  var pagefunction = function() {
    var approvalType = $("#approvalType").val();
    var rptPersonId = $("#applicantId").val();
    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val();
    var queryFlag = $("#queryFlag").val();
    var auditStatus = $("#approvalStatus").val();

    var table = $('#dt_basic').DataTable({
      "dom": "<'dt-toolbar'<'col-xs-12 col-sm-6'>>" +
      "t" +
      "<'dt-toolbar-footer'<'col-sm-4	  hidden-xs'i><'col-xs-4 col-sm-3'l><'col-xs-6 col-sm-5'p>>",
      "bFilter": true,
      "aLengthMenu": [[10, 20, 50, 100], [10, 20, 50, 100]],
      "aaSorting": [[0, "asc"]],
      "bAutoWidth": true,
      "bLengthChange": true,
      "iDisplayLength": 10,
      "bServerSide": true,
      "bStateSave": false,
      "sAjaxSource": "/approval/query.do?queryFlag="+queryFlag+"&type="+approvalType+"&auditStatus="+auditStatus+"&rptPersonId="+rptPersonId+"&startDate="+startDate+"&endDate="+endDate,
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
          "data": "detailsIndexOne",
          "sWidth": "10%"
        },
        {
          "targets": 1,
          "data": "detailsIndexTwo",
          "sWidth": "10%"
        },
        {
          "targets": 2,
          "data": "detailsIndexThree",
          "sWidth": "10%"
        },
        {
          "targets": 3,
          "data": "detailsIndexFour",
          "sWidth": "10%"
        },
        {
          "targets": 4,
          "data": "detailsIndexFive",
          "sWidth": "10%"
        },
        {
          "targets": 5,
          "data": "detailsIndexSix",
          "sWidth": "10%"
        },
        {
          "targets": 6,
          "data": "detailsIndexSeven",
          "sWidth": "10%"
        },
        {
          "targets": 7,
          "data": "detailsIndexEight",
          "sWidth": "10%"
        },{
          "targets": 8,
          "data": "detailsIndexEleven",
          "sWidth": "10%",
          "render": function (odata) {
            if (odata == 2) {
              return "已拒绝";
            } else if (odata == 1) {
              return "已同意";
            }
            else {
              return "进行中";
            }

          }

        },
      ]
    });
    $("#dt_basic thead th select").on( 'change', function () {
      table
              .column(($(this).parent().index()-1)+':visible')
              .search(this.value)
              .draw();

    } );

    $('#dt_basic tbody').on( 'click', 'tr', function () {
      $(this).toggleClass('selected');
    } );

    $('#detailsButton').click( function () {
      var queryFlag = $("#queryFlag").val();
      var approvalType = $("#approvalType").val();
      var approvalHeadId='';
      var taskId='';
      if(table.rows('.selected').data().length!=1){
        swal("","只能选择一条数据","warning");
        return;
      }else{
        $("#myApproval").text("详情");
        $('#detailsButtonShow').empty();
        approvalHeadId =table.rows('.selected').data()[0].approvalHeadId;
        taskId =  table.rows('.selected').data()[0].taskId;
        $('#home').empty();
        loadURL("/approval/get.do?approvalHeadId="+approvalHeadId+"&queryFlag="+queryFlag+"&approvalType="+approvalType+"&taskId="+taskId,$('#home'));
      }
    });

  }
  loadScript("/smartAdmin/js/plugin/datatables/jquery.dataTables.min.js", function(){
    loadScript("/smartAdmin/js/plugin/datatables/dataTables.colVis.min.js", function(){
      loadScript("/smartAdmin/js/plugin/datatables/dataTables.tableTools.min.js", function(){
        loadScript("/smartAdmin/js/plugin/datatables/dataTables.bootstrap.min.js", function(){
          loadScript("/smartAdmin/js/plugin/datatable-responsive/datatables.responsive.min.js", pagefunction)
        });
      });
    });
  });
</script>