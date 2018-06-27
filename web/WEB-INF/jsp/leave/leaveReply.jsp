<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">

<!-- widget grid -->
<section id="widget-grid" class="">




    <!-- row -->
    <div class="row">



        <!-- Widget ID (each widget will need unique ID)-->

        <article class="col-sm-12 col-md-12 col-lg-12">
            <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false"
                 data-widget-togglebutton="false"
                 data-widget-deletebutton="false" data-widget-fullscreenbutton="false"
                 data-widget-colorbutton="false"  data-widget-custombutton="true" data-widget-sortable="false">
                <!-- widget options:
                        usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

                        data-widget-colorbutton="false"
                        data-widget-editbutton="false"
                        data-widget-togglebutton="false"
                        data-widget-deletebutton="false"
                        data-widget-fullscreenbutton="false"
                        data-widget-custombutton="false"
                        data-widget-collapsed="true"
                        data-widget-sortable="false"

                        -->
                <header>
                    <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                    <h2 id="leaveReply">请假审批</h2>
                    <div class="widget-toolbar">
                        <input id="permitButton" class="btn  btn-primary btn-xs " type="button" value="同意">
                        <input type="button" id="refuseButton" class="btn  btn-danger btn-xs " value="拒绝" >
                    </div>
                </header>
                <!-- widget div-->
                <div>

                    <!-- widget edit box -->
                    <div class="jarviswidget-editbox">
                        <!-- This area used as dropdown edit box -->

                    </div>
                    <!-- end widget edit box -->

                    <!-- widget content -->
                    <div class="widget-body no-padding">

                        <table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
                            <thead>
                            <tr>
                                <th class="hasinput" style="border: 0px;">
                                </th>
                                <th class="hasinput" style="border: 0px;">
                                </th>
                                <th class="hasinput" style="border: 0px;">
                                </th>
                                <th class="hasinput" style="border: 0px;">
                                </th>
                                <th class="hasinput" style="border: 0px;">
                                </th>
                                <th class="hasinput" style="border: 0px;">
                                </th>
                                <th class="hasinput" style="border: 0px;width:9%">
                                    <select class="select"  id="approve" name="approve" style="width:100%">
                                        <option value='0'>已拒绝</option>
                                        <option value='1'>已批准</option>
                                        <option value='3' selected = "selected">待处理</option>
                                    </select>
                                </th>
                                <th class="hasinput" style="border: 0px;">
                                </th>
                            </tr>
                            <tr>
                                <th data-class="phone">开始时间</th>
                                <th data-hide="phone"> 结束时间</th>
                                <th data-hide="phone">请假人</th>
                                <th data-hide="phone">请假类型</th>
                                <th data-hide="phone">请假事由</th>
                                <th data-hide="phone">审批人</th>
                                <th data-hide="phone">审批状态</th>
                                <th data-hide="phone">备注</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>

                    </div>
                    <!-- end widget content -->

                </div>
                <!-- end widget div -->

            </div>
            <!-- end widget -->




        </article>
        <!-- WIDGET END -->

    </div>

    <!-- end row -->

    <!-- end row -->

</section>
<!-- end widget grid -->

<script type="text/javascript">

    pageSetUp();

    var pagefunction = function() {


        var table = $('#dt_basic').DataTable({
            // "oLanguage": oLanguages,

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
            "sAjaxSource": "/leave/queryReply.do",
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
                    "data": "startTime",
                    "sWidth": "12%"
                },
                {
                    "targets": 1,
                    "data": "endTime",
                    "sWidth": "12%"
                },
                {
                    "targets": 2,
                    "data": "belongsProject",
                    "sWidth": "10%"
                },
                {
                    "targets": 3,
                    "data": "leaveType",
                    "sWidth": "12%",
                    "render": function (odata) {
                        if (odata == 0) {
                            return "事假";
                        } else if (odata == 1) {
                            return "病假";
                        }
                        else if(odata==2){
                            return "婚假";
                        }  else if(odata==3){
                            return "产假";
                        }

                    }
                },
                {
                    "targets":4,
                    "data": "leaveReason",
                    "sWidth": "10%"
                },
                {
                    "targets": 5,
                    "data": "approvePersonName"
                },
                {
                    "targets": 6,
                    "data": "leaveStatus",
                    "sWidth": "10%",
                    "render": function (odata) {
                        if (odata == 0) {
                            return "已拒绝";
                        } else if (odata == 1) {
                            return "已批准";
                        }
                        else {
                            return "待处理";
                        }

                    }
                },
                {
                    "targets": 7,
                    "data": "remark",
                    "sWidth": "10%"
                }


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
            //console.log($("#dt_basic").find(".selected"));
            //alert(table.rows('.selected').data())
            //alert(table.row(0).data());
        } );

        //同意
        $('#permitButton').click( function () {
            // console.log( table.rows('.selected').row(0).data().id );
            // table.row('.selected').remove().draw( false );
            if(table.rows('.selected').data().length == 0){
                swal("","至少选择一条数据","warning");
                return;
            }
            var ids='';
            var taskIds='';
            var leaveStatus='1';
            var leaveStatusFlag = false;
            for(var i=0;i<table.rows('.selected').data().length;i++){
                //console.log(table.rows('.selected').row(i).data().id);
                ids+=table.rows('.selected').data()[i].id+",";
                taskIds+=table.rows('.selected').data()[i].taskId+",";
                if("0" == table.rows('.selected').data()[i].leaveStatus || "1" == table.rows('.selected').data()[i].leaveStatus){
                    leaveStatusFlag = true;
                }
            }
            if(!leaveStatusFlag){
                $.ajax({
                    type:"GET",
                    url:'/leave/update.do',
                    data : {
                        ids : ids,
                        taskIds:taskIds,
                        leaveStatus:leaveStatus

                    },
                    success:function(data){
                        if(data == "success"){
                            $('#dt_basic').DataTable().ajax.reload();

                        }
                    },
                    // 调用出错执行的函数
                    error: function(){
                    }
                });
            }else{
                swal("","已处理数据不能再次处理","warning");
            }
        } );
        //拒绝
        $('#refuseButton').click( function () {
            // console.log( table.rows('.selected').row(0).data().id );
            // table.row('.selected').remove().draw( false );
            if(table.rows('.selected').data().length == 0){
                swal("","至少选择一条数据","warning");
                return;
            }
            var ids='';
            var  leaveStatus='0';
            var taskIds='';
            var  leaveStatusFlag=false;
            for(var i=0;i<table.rows('.selected').data().length;i++){
                //console.log(table.rows('.selected').row(i).data().id);
                ids+=table.rows('.selected').data()[i].id+",";
                taskIds+=table.rows('.selected').data()[i].taskId+",";
                if("0" == table.rows('.selected').data()[i].leaveStatus || "1" == table.rows('.selected').data()[i].leaveStatus){
                    leaveStatusFlag = true;
                }
            }
            if(!leaveStatusFlag){
                $.ajax({
                    type:"GET",
                    url:'/leave/update.do',
                    data : {
                        ids : ids,
                        taskIds:taskIds,
                        leaveStatus:leaveStatus
                    },
                    success:function(data){
                        if(data == "success"){
                            $('#dt_basic').DataTable().ajax.reload();

                        }
                    },
                    // 调用出错执行的函数
                    error: function(){
                    }
                });
            }else{
                swal("","已处理数据不能再次处理","warning");
            }

        } );
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



    /*
     * TIMEPICKER
     */

    //Load time picker script

    loadScript("/smartAdmin/js/plugin/bootstrap-timepicker/bootstrap-timepicker.min.js", runTimePicker);

    function runTimePicker() {
        $('#startTimepicker').timepicker({
            showMeridian:false
        });
        $('#endTimepicker').timepicker({
            showMeridian:false
        });
    }


</script>

<style>
    table.dataTable tbody tr.selected {
        background-color: #b0bed9;
    }

</style>


