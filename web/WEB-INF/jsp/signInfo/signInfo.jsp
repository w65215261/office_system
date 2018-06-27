<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">

<div class="row" style="margin-top: 30px;">
        <div class="row"  style="margin-left: 20px;margin-right: 20px">


                        <div style="">
                            <table>
                                <tr>
                                    <td width="150px"></td>
                                    <td width="80px" style="text-align: right">选择时间段:&nbsp;&nbsp;</td>
                                    <td>
                                        <input type="text" name="startTime" id="startTime" placeholder="选择开始日期" readonly="true"  onchange="checkDate()" >&nbsp;&nbsp;-&nbsp;&nbsp; <input type="text" name="endTime" id="endTime" placeholder="选择结束日期" readonly="true" onchange="checkDate()">
                                    </td>
                                    <td width="80px" style="text-align: right">部门/人员:&nbsp;&nbsp;</td><td>
                                    <input type="text"  id="orgName"  readonly="true" onfocus="checkPerson()">
                                    <input type="hidden" id="personId">
                                    <input type="hidden" id="orgId">
                                    <input type="hidden" id="type">
                                    <input type="hidden" id="types">
                                </td>
                                    <td width="50px"></td>
                                    <td><input type="button"  onclick="checkAdrress()" value="查询"></td>
                                    <td><input type="button"  onclick="report()" value="生成报表">
                                </tr>
                            </table>
                        </div>
        </div>
    <div>
        <div style=" height: auto;"id="myTabContent1" class="tab-content padding-10">
            <div class="tab-pane fade in active" id="s1">
                </div>
            </div>
    </div>
</div>
<script type="text/javascript">


    pageSetUp();



    var pagefunction = function () {
        $.datepicker.regional["zh-CN"] = { closeText: "关闭", prevText: "&#x3c;上月", nextText: "下月&#x3e;", currentText: "今天", monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"], monthNamesShort: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"], dayNames: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"], dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"], dayNamesMin: ["日", "一", "二", "三", "四", "五", "六"], weekHeader: "周", dateFormat: "yy-mm-dd", firstDay: 1, isRTL: !1, showMonthAfterYear: !0, yearSuffix: "年" }
        $.datepicker.setDefaults($.datepicker.regional["zh-CN"]);
        var startTime=$("#startTime").val();
        var endTime=$("#endTIme").val();
        var PersonId=$("#PersonId").val();
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


    $('#startTime').datepicker({
        dateFormat : 'yy-mm-dd',
        showOtherMonths:true,
        prevText : '<i class="fa fa-chevron-left"></i>',
        nextText : '<i class="fa fa-chevron-right"></i>',
        onClose : function(selectedDate) {
            $('#endTime').datepicker('option', 'minDate', selectedDate);
        }
    });
    $('#endTime').datepicker({
        dateFormat : 'yy-mm-dd',
        showOtherMonths:true,
        prevText : '<i class="fa fa-chevron-left"></i>',
        nextText : '<i class="fa fa-chevron-right"></i>',
        onClose : function(selectedDate) {
            $('#startTime').datepicker('option', 'maxDate', selectedDate);
        }
    });
    function checkPerson(){
        swal({
                    title: '<h4 class="modal-title"><p>选择部门或人员</p></h4>',
                    text: '<div class="modal-body no-padding"><div class="custom-scroll table-responsive" style="height:250px; overflow-y: auto;"><div id="organTree2" style="text-align: left;"></div></div></div>',
                    html: true,
                    allowOutsideClick:true
                },
                function(){
                    //不加这段代码，在点击其他弹出框的时候会导致organTree里的内容显示，
                    // 加了之后消除organTree里的内容时弹出框正常显示
                    swal("","","success");
                });
        loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
        loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTreeswal2);
        function renderTreeswal2() {
            $.ajax({
                type: "GET",
                url: '/organization/queryAlltree.do',
                data: {},
                success: function (data) {
                    $('#organTree2').treeview({
                        data: data,
                        onNodeSelected: function (event, data) {
                            if(data.icon=="glyphicon glyphicon-tower"){
                                var orgId=data.href;
                                $('#orgId').val(orgId);
                                $('#personId').val("");
                                $('#orgName').val(data.text)
                                $('#type').val("2");
                            }else{
                                var personId=data.href;
                                $('#personId').val(personId);
                                $('#orgId').val("");
                                $('#orgName').val(data.text)
                                $('#type').val("1");
                                var startTime=$("#startTime").val();
                                var endTime=$("#endTime").val();
                                if(getDays(startTime,endTime)==0){
                                    $("#types").val(0);
                                }else{
                                    $("#types").val(1);
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
    function checkAdrress() {
        var startTime=$("#startTime").val();
        var endTime=$("#endTime").val();
        var personId=$("#personId").val();
        var orgName=$("#orgName").val();
        var orgId=$("#orgId").val();
        console.log(getDays(startTime,endTime));
        if(startTime==""){
            swal("","请选择开始日期!","warning");
            return;
        }
        if(endTime==""){
            swal("","请选择结束日期!","warning");
            return;
        }
        if(orgName==""){
            swal("","请选择部门或者人员!","warning");
            return;
        }
        if(getDays(startTime,endTime)>=31){
            swal("","开始日期和截止日期不能大于31天!","warning");
            return;
        }
        var type=$("#type").val();
        var types=$("#types").val();
                if(type=="1"){
                    if (types=="0"){
                        loadURL("/signInfo/queryOneDayByPerson.do?startTime="+startTime+"&endTime="+endTime+"&personId="+personId+"&personName="+orgName, $('#s1'));
                    }else{
                    loadURL("/signInfo/queryByPerson.do?startTime="+startTime+"&endTime="+endTime+"&personId="+personId+"&personName="+orgName, $('#s1'));
                    }
                }else if(type=="2"){
                    loadURL("/signInfo/queryByOrg.do?startTime="+startTime+"&endTime="+endTime+"&orgId="+orgId+"&orgName="+orgName, $('#s1'));
                }
    }
    function getDays(strDateStart,strDateEnd){
        var strSeparator = "-"; //日期分隔符
        var oDate1;
        var oDate2;
        var iDays;
        oDate1= strDateStart.split(strSeparator);
        oDate2= strDateEnd.split(strSeparator);
        var strDateS = new Date(oDate1[0], oDate1[1]-1, oDate1[2]);
        var strDateE = new Date(oDate2[0], oDate2[1]-1, oDate2[2]);
        iDays = parseInt(Math.abs(strDateS - strDateE ) / 1000 / 60 / 60 /24)//把相差的毫秒数转换为天数
        return iDays ;
    }
     function checkDate(){
         var startTime=$("#startTime").val();
         var endTime=$("#endTime").val();
         if(getDays(startTime,endTime)==0){
             $("#types").val(0);
         }else{
             $("#types").val(1);
         }
     }
    function report(){
        var startTime=$("#startTime").val();
        var endTime=$("#endTime").val();
        var personId=$("#personId").val();
        var orgName=$("#orgName").val();
        var orgId=$("#orgId").val();
        var type=$("#type").val();
        var types=$("#types").val();
        if(startTime==""){
            swal("","请选择开始日期!","warning");
            return;
        }
        if(endTime==""){
            swal("","请选择结束日期!","warning");
            return;
        }
        if(orgName==""){
            swal("","请选择部门或者人员!","warning");
            return;
        }
        if(getDays(startTime,endTime)>=31){
            swal("","开始日期和截止日期不能大于31天!","warning");
            return;
        }
        if(type=="1"){
            if (types=="0"){
                var url='/jasperjsp/toSignInfoReport.jsp?fid=3&type=excel&startTime='+startTime+'&signPersonId='+personId+'&endTime='+endTime;
                window.location.href=url;
            }else{
                var url='/jasperjsp/toSignInfoReport.jsp?fid=1&type=excel&startTime='+startTime+'&signPersonId='+personId+'&endTime='+endTime;
                window.location.href=url;
            }


        }else if(type=="2"){
            var url='/jasperjsp/toSignInfoReport.jsp?fid=2&type=excel&startTime='+startTime+'&endTime='+endTime+'&orgId='+orgId;
            window.location.href=url;
        }

       // loadURL(url, $('#s1'));

    }
</script>
<style>


</style>