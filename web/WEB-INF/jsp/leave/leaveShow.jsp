<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.pmcc.soft.core.utils.DateUtil" %>
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
                    <h2 id="leaveShow">请假查询</h2>
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
                        <input type="hidden" id="belongsDate" name="belongsDate">
                        <form action="/workHour/save.do" method="post" id="login-form" class="smart-form">
                            <input type="hidden" id="workHourId" name="workHourId">
                            <fieldset>
                                <div class="row">
                                    <section>
                                        <label class="label col col-1"  style="width: 12%">开始日期：</label>
                                        <section class="col col-4  no-padding">
                                            <label class="input"> <i class="icon-append fa fa-calendar"></i>
                                                <input type="text" name="startDate" id="startDate" placeholder="选择开始日期" readonly="true" value="${sDate}">
                                            </label>
                                        </section>
                                        <label class="label col col-1"  style="width: 12%">结束日期：</label>
                                        <section class="col col-4  no-padding">
                                            <label class="input"> <i class="icon-append fa fa-calendar"></i>
                                                <input type="text" name="endDate" id="endDate" placeholder="选择结束日期" readonly="true" value="${eDate}">
                                            </label>
                                        </section>
                                    </section>
                                    <section>
                                        <label class="label col col-1" style="width: 12%">请假类别：</label>
                                        <section class="col col-4  no-padding">
                                            <label class="select">
                                                <select class="input-sm" id="leaveType"  name="leaveType">
                                                    <option value=''></option>
                                                    <option value='0'>事假</option>
                                                    <option value='1'>病假</option>
                                                    <option value='2'>婚假</option>
                                                    <option value='3'>产假</option>
                                                </select><i class="icon-append fa fa-lock"></i>
                                            </label>
                                        </section>
                                    </section>
                                    <section>
                                        <label class="label  col col-1" style="width: 12%">审批状态：</label>
                                        <section class="col col-4  no-padding">
                                            <label class="select">
                                                <select class="input-sm" id="approve"  name="approve">
                                                    <option value=''></option>
                                                    <option value='0'>已拒绝</option>
                                                    <option value='1'>已批准</option>
                                                    <option value='3'>待处理</option>
                                                </select> <i class="icon-append fa fa-lock"></i>
                                            </label>
                                        </section>
                                    </section>

                                    <section>

                                        <label class="label col col-1" style="width: 12%">请假人：</label>
                                        <section class="col col-4  no-padding">
                                            <label class="input">
                                                <input type="text" class="text"  id="leavePersonName" name="leavePersonName" readonly="true">
                                                <input type="hidden" class="text"  id="leavePersonId" name="leavePersonId" readonly="true">
                                            </label>
                                        </section>
                                    </section>
                                </div>
                                <footer style="background-color: white">
                                    <button type="button" onclick="report();" class="btn btn-primary">
                                        生成报表
                                    </button>
                                    <button type="button" onclick="queryLeaveData();" class="btn btn-primary">
                                        查询
                                    </button>

                                </footer>
                            </fieldset>
                        </form>

                        <table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
                            <thead>
                            <tr>
                                <th data-class="phone">开始时间</th>
                                <th data-hide="phone"> 结束时间</th>
                                <th data-hide="phone"> 申请日期</th>
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
        </article>
        <!-- WIDGET END -->
    </div>
    <!-- end row -->
</section>
<!-- end widget grid -->

<script type="text/javascript">

    pageSetUp();

    $('#startDate').datepicker({
        dateFormat : 'yy-mm-dd',
        showOtherMonths:true,
        prevText : '<i class="fa fa-chevron-left"></i>',
        nextText : '<i class="fa fa-chevron-right"></i>',
        onSelect : function(selectedDate) {
            $('#endDate').datepicker('option', 'minDate', selectedDate);
        }
    });

    $('#endDate').datepicker({
        dateFormat : 'yy-mm-dd',
        showOtherMonths:true,
        prevText : '<i class="fa fa-chevron-left"></i>',
        nextText : '<i class="fa fa-chevron-right"></i>',
        onSelect : function(selectedDate) {
            $('#startDate').datepicker('option', 'maxDate', selectedDate);
        }
    });

    var pagefunction = function() {
        $.datepicker.regional["zh-CN"] = { closeText: "关闭", prevText: "&#x3c;上月", nextText: "下月&#x3e;", currentText: "今天", monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"], monthNamesShort: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"], dayNames: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"], dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"], dayNamesMin: ["日", "一", "二", "三", "四", "五", "六"], weekHeader: "周", dateFormat: "yy-mm-dd", firstDay: 1, isRTL: !1, showMonthAfterYear: !0, yearSuffix: "年" }
        $.datepicker.setDefaults($.datepicker.regional["zh-CN"]);
        var responsiveHelper_dt_basic = undefined;
        var breakpointDefinition = {
            tablet : 1024,
            phone : 480
        };
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
            "sAjaxSource": "/leave/queryShow.do",
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
                    "sWidth": "10%"
                },
                {
                    "targets": 1,
                    "data": "endTime",
                    "sWidth": "10%"
                },
                {
                    "targets": 2,
                    "data": "createTime",
                    "sWidth": "10%"
                },
                {
                    "targets": 3,
                    "data": "belongsProject",
                    "sWidth": "8%"
                },
                {
                    "targets": 4,
                    "data": "leaveType",
                    "sWidth": "10%",
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
                    "targets":5,
                    "data": "leaveReason"
                },
                {
                    "targets": 6,
                    "data": "approvePersonName"
                },
                {
                    "targets": 7,
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
                    "targets": 8,
                    "data": "remark",
                    "sWidth": "10%"
                }

            ]
        });

        $("#leavePersonName").on( 'click', function () {
            showModal();
        } );

        var personId = '';
        var personName = '';
        function showModal(){
            swal({
                        title: '<h4 class="modal-title"><p>选择请假人</p></h4>',
                        text: '<div class="modal-body no-padding"><div class="custom-scroll table-responsive" style="height:250px; overflow-y: auto;"><div id="organTree" style="text-align: left;"></div></div></div>',
                        html: true,
                        allowOutsideClick:true
                    },
                    function(){
                        $('#leavePersonName').val(personName);
                        $('#leavePersonId').val(personId);
                        //不加这段代码，在点击其他弹出框的时候会导致organTree里的内容显示，
                        // 加了之后消除organTree里的内容时弹出框正常显示
                        swal("","","success");
                    });
            loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
            loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTreeSweet);
            function renderTreeSweet() {
                $.ajax({
                    type: "GET",
                    url: '/organization/queryAlltree.do',
                    data: {},
                    success: function (data) {
                        $('#organTree').treeview({
                            data: data,
                            onNodeSelected: function (event, data) {
                                if(data.icon=="glyphicon glyphicon-tower"){
                                    return;
                                }else{
                                    personName = data.text;
                                    personId=data.href;
                                }
                            },
                            onNodeUnselected:function(event, data){
                                personName = "";
                                personId = "";
                            }
                        });
                    },
                    // 调用出错执行的函数
                    error: function () {
                    }
                });
            }
        }


    }
    function queryLeaveData(){

        var personId = $('#leavePersonId').val();
        var startTime = $('#startDate').val();
        var endTime = $('#endDate').val();
        var leaveType = $('#leaveType').val();
        var leaveStatus = $('#approve').val();
        $('#dt_basic').DataTable().ajax.url('/leave/queryByParameter.do?personId='+personId+'&startTime='+startTime+'&endTime='
        +endTime+'&leaveType='+leaveType+'&leaveStatus='+leaveStatus).load();
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
function report(){
    var startTime = $('#startDate').val();
    var endTime = $('#endDate').val();
    var leaveType = $('#leaveType').val();
    var leaveStatus = $('#approve').val();
    var personId = $('#leavePersonId').val();

    var myWhere="";
    if(leaveType.length>0){
        myWhere=myWhere+" where LEAVE_TYPE="+leaveType;
    }
    if(leaveStatus.length>0){
        if(myWhere.length>0){
        myWhere=myWhere+"and LEAVE_STATUS="+leaveStatus
        }else{
            myWhere=myWhere+" where LEAVE_STATUS="+leaveStatus
        }
    }
    if(personId.length>0){
        if(myWhere.length>0){
            myWhere=myWhere+"and APPROVE_PERSON_ID="+leaveStatus
        }else{
            myWhere=myWhere+"where APPROVE_PERSON_ID="+leaveStatus
        }
    }
    console.log("leaveType"+leaveType);
    console.log("myWhere"+myWhere);
    if(myWhere.length>0){
        myWhere=myWhere+"CREATE_TIME between  "+ startTime+" 00:00:00 and "+ endTime+" 23:59:59 and DEL_FLAG=0"
        var url='/jasperjsp/toLeaveReport.jsp?fid=1&type=excel&startTime='+startTime+'&endTime='+endTime+'&myWhere='+ myWhere;
        window.location.href=url;
    }else{
        myWhere=" where DEL_FLAG=0 and CREATE_TIME between ' "+startTime+" 00:00:00' and'"+" "+endTime+" 23:59:59' "
    var url='/jasperjsp/toLeaveReport.jsp?fid=1&type=excel&startTime='+startTime+'&endTime='+endTime+'&myWhere='+myWhere;
    window.location.href=url;
    }
}
</script>

<style>
    table.dataTable tbody tr.selected {
        background-color: #b0bed9;
    }

</style>


