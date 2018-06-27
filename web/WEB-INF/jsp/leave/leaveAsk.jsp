<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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


    <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">


        <!-- MODAL PLACE HOLDER -->
        <div class="modal fade" id="remoteModal" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">
            <div class="modal-dialog"  style="width:800px ;height:600px">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title"><p>请假申请</p></h4>
                    </div>
                    <div class="modal-body no-padding">
                        <input type="hidden" id="belongsDate" name="belongsDate">


                        <form action="/workHour/save.do" method="post" id="login-form" class="smart-form">
                            <input type="hidden" id="workHourId" name="workHourId">
                            <fieldset>
                                <section>
                                    <div class="row">
                                        <label class="label col col-2">开始日期：</label>
                                        <section class="col col-4">
                                            <label class="input"> <i class="icon-append fa fa-calendar"></i>
                                                <input type="text" name="startTimepicker" id="startTimepicker" placeholder="选择开始日期" readonly="true">
                                            </label>
                                        </section>
                                        <label class="label col col-2">结束日期：</label>
                                        <section class="col col-4">
                                            <label class="input"> <i class="icon-append fa fa-calendar"></i>
                                                <input type="text" name="endTimepicker" id="endTimepicker" placeholder="选择结束日期" readonly="true">
                                            </label>
                                        </section>
                                    </div>
                                </section>

                                <section>
                                    <div class="row">
                                        <label class="label col col-2">请假类别：</label>
                                        <div class="col col-4">
                                            <label class="select"> <i class="icon-append fa fa-lock"></i>
                                                <select class="input-sm" id="leaveType"  name="leaveType" >
                                                    <option value='0'>事假</option>
                                                    <option value='1'>病假</option>
                                                    <option value='2'>婚假</option>
                                                    <option value='3'>产假</option>
                                                </select> <i></i>
                                            </label>
                                        </div>
                                        <label class="label col col-2">审批人：</label>
                                        <div class="col col-4">
                                            <label class="select">
                                                <label class="input">
                                                    <input type="text" class="text"  id="leavePersonName" name="leavePersonName" readonly="true">
                                                </label>
                                                <input type="hidden" class="text"  id="leavePersonId" name="leavePersonId">
                                            </label>
                                        </div>
                                    </div>
                                </section>

                                <section>
                                    <div class="row">
                                        <label class="label col col-2">请假事由：</label>
                                        <div class="col col-10">
                                            <label class="textarea textarea-expandable">
                                                <textarea rows="3" id="workContent" name="workContent"  class="custom-scroll"></textarea>
                                            </label>
                                        </div>

                                    </div>
                                </section>
                                <section>
                                    <div class="row">
                                        <label class="label col col-2">备注：</label>
                                        <div class="col col-10">
                                            <label class="textarea textarea-expandable">
                                                <textarea rows="3" id="remark" name="remark" class="custom-scroll "></textarea>
                                            </label>
                                        </div>
                                    </div>
                                </section>
                            </fieldset>

                            <footer>

                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                    取消
                                </button>
                                <button type="button" onclick="saveWorkHour()" class="btn btn-primary">
                                    提交
                                </button>
                            </footer>

                        </form>

                        <script>
                            // Load form valisation dependency
                            loadScript("/smartAdmin/js/plugin/jquery-form/jquery-form.min.js", function(){});
                            // Registration validation script    var flag = $('#netForm').form('validate');
                            var $loginForm = $("#login-form").validate({
                                // Rules for form validation

                                rules : {
                                    startTimepicker : {
                                        required : true
                                    },endTimepicker : {
                                        required : true
                                    },leavePersonName : {
                                        required : true
                                    },workContent : {
                                        required : true
                                    }
                                },

                                // Messages for form validation
                                messages : {
                                    startTimepicker : {
                                        required : "请将信息填写完整"
                                    },
                                    endTimepicker : {
                                        required : "请将信息填写完整"
                                    },
                                    leavePersonName : {
                                        required : "请将信息填写完整"
                                    }, workContent : {
                                        required : "请将信息填写完整"
                                    }
                                },

                                // Do not change code below
                                errorPlacement : function(error, element) {
                                    error.insertAfter(element.parent());
                                }
                            });

                            $('#startTimepicker').datepicker({
                                dateFormat : 'yy-mm-dd',
                                showOtherMonths:true,
                                prevText : '<i class="fa fa-chevron-left"></i>',
                                nextText : '<i class="fa fa-chevron-right"></i>',
                                onSelect : function(selectedDate) {
                                    $('#endTimepicker').datepicker('option', 'minDate', selectedDate);
                                }
                            });

                            $('#endTimepicker').datepicker({
                                dateFormat : 'yy-mm-dd',
                                showOtherMonths:true,
                                prevText : '<i class="fa fa-chevron-left"></i>',
                                nextText : '<i class="fa fa-chevron-right"></i>',
                                onSelect : function(selectedDate) {
                                    $('#startTimepicker').datepicker('option', 'maxDate', selectedDate);
                                }
                            });
                        </script>

                    </div>




                </div>
            </div>
        </div>
        <!-- END MODAL -->



    </div>

</div>

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
                    <h2 id="leaveAsk">请假申请</h2>
                    <div class="widget-toolbar">
                        <input onclick="addWorkHour()" class="btn  btn-success btn-xs " type="button" value="新增">
                        <input id="updateButton" class="btn  btn-primary btn-xs " type="button" value="修改">
                        <input type="button" id="deleteButton" class="btn  btn-danger btn-xs " value="删除" >

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
                                <th class="hasinput" style="border: 0px;width:9%">
                                    <select class="select"  id="approve" name="approve" style="width:100%">
                                        <option value=''></option>
                                        <option value='0'>已拒绝</option>
                                        <option value='1'>已批准</option>
                                        <option value='3'>待处理</option>
                                    </select>
                                </th>
                                <th class="hasinput" style="border: 0px;">
                                </th>
                            </tr>
                            <tr>
                                <th data-class="expand">开始时间</th>
                                <th data-hide="phone">结束时间</th>
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
    var showTreeFlag = '';
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
            "sAjaxSource": "/leave/query.do",
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
                    "targets": 3,
                    "data": "leaveReason",
                    "sWidth": "10%"
                },
                {
                    "targets": 4,
                    "data": "approvePersonName"
                },{
                    "targets": 5,
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
                    "targets": 6,
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
        $('#deleteButton').click( function () {


            var id='';
            if(table.rows('.selected').data().length==0){
                swal("","请选择几条未处理数据","warning");
            }else{
                for(var i=0;i<table.rows('.selected').data().length;i++){
                    //console.log(table.rows('.selected').row(i).data().id);
                    id+=table.rows('.selected').data()[i].id+",";
                }

                $.ajax({
                    type:"GET",
                    url:'/leave/delete.do',
                    data : {
                        id : id
                    },
                    success:function(data){
                        if(data == "success"){
                            $('#dt_basic').DataTable().ajax.reload();
                        }else{
                            swal("","已处理数据不能删除","warning");
                            $('#dt_basic').DataTable().ajax.reload();
                        }
                    },
                    // 调用出错执行的函数
                    error: function(){
                    }
                });
            }




        } );


        $('#updateButton').click( function () {
            showTreeFlag = "1";
            $('#approvePerson').attr("disabled","disabled");
            var id='';
            if(table.rows('.selected').data().length!=1){
                swal("","请选择一条未处理数据","warning");
            }else{
                id =table.rows('.selected').data()[0].id;
                $("#approvePerson").empty();
                $.ajax({
                    type : "GET",
                    url : '../leave/approvePerson.do',
                    success : function(data) {

                        for(var i=0;i<data.length;i++){
                            $("#approvePerson").append( "<option value='"+data[i].id+"'>"+data[i].userCname+"</option>" );
                        }
                        $.ajax({
                            type:"GET",
                            url:'/leave/findById.do',
                            data : {
                                id : id
                            },
                            success:function(data){
                                if(data.leaveStatus != "3"){
                                    swal("","已处理数据不能修改","warning");
                                }else{

                                    $("#remoteModal").modal("show");
                                    $('#workHourId').val(data.id);
                                    $('#startTimepicker').val(data.startTime);
                                    $('#endTimepicker').val(data.endTime);
                                    $('#workContent').val(data.leaveReason);
                                    $('#remark').val(data.remark);
                                    $('#leavePersonId').val(data.approvePersonId);
                                    $('#leavePersonName').val(data.approvePersonName);
                                    $('#leaveType').val(data.leaveType);

                                }



                            },
                            // 调用出错执行的函数
                            error: function(){
                            }
                        });


                    }
                });



            }




        } );

        $("#leavePersonName").on( 'click', function () {
            if("1" != showTreeFlag){
                showModal();
            }
        } );
        var personId = '';
        function showModal(){
            var leavePersonId = "";
            var leavePersonName = "";
            swal({
                        title: '<h4 class="modal-title"><p>选择审批人（可多选）</p></h4>',
                        text: '<div class="modal-body no-padding"><div class="custom-scroll table-responsive" style="height:250px; overflow-y: auto;"><div id="organTree" style="text-align: left;"></div></div></div>',
                        html: true,
                        allowOutsideClick:true
                    },
                    function(){
                        $('#leavePersonId').val(leavePersonId);
                        $('#leavePersonName').val(leavePersonName);
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
                            multiSelect:true,
                            onNodeSelected: function (event, data) {
                                if(data.icon=="glyphicon glyphicon-tower"){
                                    return;
                                }else{
                                    var treeFlag = true;
                                    for(var i = 0;i<leavePersonId.split(",").length;i++){
                                        if(data.href == leavePersonId.split(",")[i]){
                                            treeFlag = false;
                                        }
                                    }
                                    if(treeFlag) {
                                        if (leavePersonName == "") {
                                            leavePersonName = data.text;
                                        } else {
                                            leavePersonName += "," + data.text;
                                        }

                                        if (leavePersonId == "") {
                                            leavePersonId = data.href;
                                        } else {
                                            leavePersonId += "," + data.href;
                                        }
                                    }
                                }

                            },
                            onNodeUnselected:function(event, data){

                                var treeFlag = true;
                                var leavePersonIds = leavePersonId.split(",");
                                var leavePersonNames = leavePersonName.split(",");
                                leavePersonName ="";
                                leavePersonId = "";
                                for(var i = 0;i<leavePersonIds.length;i++){
                                    treeFlag = true;
                                    if(data.href == leavePersonIds[i]){
                                        treeFlag = false;
                                    }
                                    if(treeFlag){
                                        if(leavePersonId == ""){
                                            leavePersonId = leavePersonIds[i];
                                            leavePersonName = leavePersonNames[i];
                                        }else{
                                            leavePersonId += "," + leavePersonIds[i];
                                            leavePersonName += "," + leavePersonNames[i];
                                        }
                                    }
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

    }



    function saveWorkHour(){
        var cName = $('#leavePersonName').val();
        var id = $('#leavePersonId').val();
        var leaveType = $('#leaveType').val();

        var belongsDate=$('#belongsDate').val();
        var startTime=$('#startTimepicker').val();
        var endTime=$('#endTimepicker').val();
        var workContent=$('#workContent').val();
        var remark=$('#remark').val();
        var workHourId= $('#workHourId').val();
        // startTime=belongsDate+" "+startTime;
        //endTime=belongsDate+" "+endTime;
        // belongsDate=startTime;
        $('#login-form').submit(function(event) {
            event.preventDefault();
        });

        $('#login-form').submit(); //触发绑定事件；
        var validator =  $('#login-form').validate();
        if (validator.numberOfInvalids() <= 0) { //判断加入所有校验都通过后再做ajax提交；
            $("#remoteModal").modal("hide");
            $.ajax({
                type: "POST",
                url: '/leave/save.do',
                data: {
                    leaveType:leaveType,
                    belongsDate:belongsDate,
                    approvePersonId:id,
                    approvePersonName:cName,
                    startTime: startTime,
                    endTime: endTime,
                    leaveReason: workContent,
                    remark: remark,
                    id:workHourId

                },
                success: function (data) {
                    if (data == "success") {
                        $('#dt_basic').DataTable().ajax.reload();

                    } else {
                        swal("","保存失败!","warning");
                    }
                },
                // 调用出错执行的函数
                error: function () {
                }
            });
        }

    }

    function addWorkHour(){
        showTreeFlag = "0";
        var date = new Date();
        $("#belongsDate").val(date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
        $("#remoteModal").modal("show");

        //$("#workType").val("");
        $("#login-form input").val("");//.removeAttr("checked").remove("selected");//核心
        $("#login-form textarea").val("");
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

<style>
    table.dataTable tbody tr.selected {
        background-color: #b0bed9;
    }

</style>


