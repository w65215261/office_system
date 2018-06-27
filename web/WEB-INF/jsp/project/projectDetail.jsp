<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
<style>
    #popoverSpan1{
        cursor: pointer;
    }
    #popoverSpan2{
        cursor: pointer;
    }
    #projectManager,#startTimes,#endTimes{
        cursor: pointer;
    }
    #trShows{
          cursor: pointer;
      }
</style>
<div  >
    <table id="user" class="table table-bordered table-striped" style="clear: both">
        <tbody>
        <tr>
            <td style="width:15%;padding-top: 15px">项目名称</td>
            <td style="width:65%;padding-top: 15px">
               <span> <a href="form-x-editable.html#" id="projectname" data-type="text" data-pk="1" data-original-title="项目名称" data-placement="bottom">${project.projectName}</a></span>
            </td>
            <td style="width:20%;text-align: right">

                <div class="dropdown"><span  id="trShows"onclick="trShow()">展开详情&nbsp;&nbsp;<i  class="fa  fa-arrow-circle-down"></i>&nbsp;&nbsp;&nbsp;</span>
                    <button type="button" class="btn dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown"> 操作<b class="caret"></b></button>
                    <ul class="dropdown-menu pull-right" role="menu"  aria-labelledby="dropdownMenu1">
                        <c:if test="${project.projectStatus==1}">
                            <li>
                                <a href="#"     data-toggle="tab" onclick=changeProjectStatus1("${project.id}")>重启项目</a>
                            </li>
                        </c:if>
                        <c:if test="${project.projectStatus==0}">
                            <li>
                                <a href="#"   onclick=changeProjectStatus("${project.id}")  data-toggle="tab">完成项目</a>
                            </li>
                        </c:if>
                        <c:if test="${project.delFlag!=0}">
                            <li>
                                <a href="#"  data-toggle="tab"><span style="color: red" >删除成功</span></a>
                            </li>
                        </c:if>
                        <c:if test="${project.delFlag==0}">
                            <li>
                                <a href="#"   onclick=delProject("${project.id}") data-toggle="tab">删除项目</a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </td>
        </tr>
        <div  >
        <tr class="trHide">
            <td style="width:15%;">项目目标</td>
            <td style="width:85%" colspan="2">
                <a href="form-x-editable.html#" id="projectGoal" data-type="textarea" data-pk="1" data-placeholder="项目目标" data-placement="bottom" data-original-title="项目目标">${project.projectGoal}</a>
            </td>
        </tr>
        <tr class="trHide">
            <td style="width:15%;">开始时间</td>
            <td style="width:85%" colspan="2">
                <span href="#" id="startTimes" data-type="date" data-viewformat="yyyy-mm-dd" data-pk="1" data-placement="bottom" data-original-title="开始时间" style="color: #3377B1;">${startTime}</span>
            </td>
        </tr>
        <tr class="trHide">
            <td style="width:15%;">结束时间</td>
            <td style="width:85%" colspan="2">
                <span href="#" id="endTimes" data-type="date" data-viewformat="yyyy-mm-dd" data-pk="1" data-placement="bottom" data-original-title="结束时间" style="color: #3377B1;">${endTime}</span>
            </td>
        </tr>
        <tr class="trHide">
                <td style="width:15%;">可见范围</td>
                <td style="width:85%" colspan="2">
                    <a href="form-x-editable.html#" id="visibility" data-type="select" data-pk="1" data-value="${project.visibility}" data-original-title="可见范围"></a>
                </td>
         </tr>
        <tr class="trHide">
            <td style="width:15%;padding-top: 15px">负责人</td>
            <td style="width:85%" colspan="2">
                <div style="text-align: left; border:hidden;width:10px;background-color: inherit;margin-left: -10px" onclick="projectManager()" class="input-group-addon"  data-placement="right" data-original-title="负责人" data-html="false">
                    <div id="projectManager" style="text-align: left;border-bottom: dashed 1px #3399D2;padding-bottom: 5px;color: #3377B1;margin-left: -10px">${project.projectManager}</div> </div>
            </td>
        </tr>
        <tr class="trHide">
            <td style="width:15%;padding-top: 15px">审批人</td>
            <td style="width:85%" colspan="2">
                <div style="text-align: left; border:hidden;width:10px;background-color: inherit;margin-left: -10px" onclick="loadPopover6()" class="input-group-addon" id="inputTest6"  data-placement="bottom" data-original-title="审批人" data-html="false">
                    <div id="popoverSpan6" style="text-align: left;border-bottom: dashed 1px #3399D2;padding-bottom: 5px;color: #3377B1;margin-left: -10px">${ApprovePerson}</div> </div>
            </td>
        </tr>

        <tr class="trHide">
                <td style="width:15%;padding-top: 15px">项目进度</td>
                <td style="width:65%;border-right: hidden">
                    <div class="progress" style="width: 100%">
                            <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="50" style="width: 500px" data-original-title="项目进度">
                            </div>
                    </div>
            </td>
            <td style="width:20%">
                <a href="form-x-editable.html#" id="projectSchedule" data-type="text" data-pk="1" data-original-title="项目进度">${project.projectSchedule}</a><span style="color: #3377B1;border-bottom: dashed 1px #3399D2;">%</span>
            </td>
        </tr>
        </div>
        </tbody>
    </table>
</div>
<!-- widget content -->
<div class="widget-body">


    <hr class="simple">

    <ul id="myTab1" class="nav nav-tabs bordered">
        <li class="active">
            <a href="#s1" data-toggle="tab">动态</a>
        </li>
        <li>
            <a href="#s2" data-toggle="tab" onclick="projectTask('${project.id}')"> 任务</a>
        </li>
        <li>
            <a href="#s5" data-toggle="tab" onclick="projectCount('${project.id}')"> 统计</a>
        </li>
        <li>
            <a href="#s6" data-toggle="tab" onclick="projectAttachment('${project.id}')" id="fileNumber"> </a>
        </li>
        <li>
            <a href="#s7" data-toggle="tab" onclick="projectPerson('${project.id}')" id="personNumber"> </a>
        </li>
        <li>
            <a href="#s8" data-toggle="tab" onclick="operationRecord('${project.id}')"> 操作记录</a>
        </li>
        <%--<li class="dropdown">--%>
            <%--<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>--%>
            <%--<ul class="dropdown-menu">--%>
                <%--<li>--%>
                    <%--<a href="#s3" data-toggle="tab">@fat</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="#s4" data-toggle="tab">@mdo</a>--%>
                <%--</li>--%>
            <%--</ul>--%>
        <%--</li>--%>
        <li class="pull-right">

        </li>
    </ul>

    <div id="myTabContent1" class="tab-content" style="overflow: visible;">
        <div class="tab-pane fade in active" id="s1">
        </div>
        <div class="tab-pane fade" id="s2" style="overflow: visible;">
            <p>
                Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee.
            </p>
        </div>
        <div class="tab-pane fade" id="s5" style="overflow: visible;">
            <p>
                Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee.
            </p>
        </div>
        <div class="tab-pane fade" id="s6" style="overflow: visible;">
            <p>
                Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee.
            </p>
        </div>
        <div class="tab-pane fade" id="s7" style="overflow: visible;">
            <p>
                Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee.
            </p>
        </div>
        <div class="tab-pane fade" id="s8" style="overflow: visible;">
            <p>
                Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee.
            </p>
        </div>
        <div class="tab-pane fade" id="s3">
            <p>
                Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade. Messenger bag gentrify pitchfork tattooed craft beer, iphone skateboard locavore carles etsy salvia banksy hoodie helvetica. DIY synth PBR banksy irony.
            </p>
        </div>
        <div class="tab-pane fade" id="s4">
            <p>
                Trust fund seitan letterpress, keytar raw denim keffiyeh etsy art party before they sold out master cleanse gluten-free squid scenester freegan cosby sweater. Fanny pack portland seitan DIY, art party locavore wolf cliche high life echo park Austin. Cred vinyl keffiyeh DIY salvia PBR, banh mi before they sold out farm-to-table.
            </p>
        </div>
    </div>




</div>
<script type="text/javascript">
    function update() {
        $("#remoteModal").modal("show");
        //$("#workType").val("");
    }

    $('#Schedule').on('click',function(){
        var schedule=$('#projectSchedule').val();
        var projectId=$('#projectId').val();
        if(schedule>100){
            alert("进度不能超过100或者小于原进度");
        }else{
            console.log(schedule);
            console.log(projectId);
            $.ajax({
                type: "POST",
                url: '/project/updateSchedule.do',
                data:{projectSchedule:schedule,
                    projectId:projectId
                },
                success: function (data) {
                    if (data == "success") {
                        loadURL("/project/findProjectByOid.do?projectId="+projectId, $('#inbox-content > .table-wrap'));
                    } else {
                        alert('保存失败!');
                    }
                },
                error: function () {

                }
            });
        }
    });
    $('.progress-bar').css({'width':'${project.projectSchedule}%'}).find('span').html('');
    pageSetUp();
    function projectfunction(projectOid) {
        loadURL("/projectDynamics/show.do?projectOid="+projectOid,$('#s1'));
    }
    projectfunction('${project.id}');
    function changeProjectStatus(projectId){
        $.ajax({
            type: "GET",
            url: '/project/update.do?flag=1',
            data:{projectId:projectId},
            success: function (data) {
                if (data == "success") {
                    loadURL("/project/showList.do", $('#inbox-content > .table-wrap'));
                } else {
                    alert('保存失败!');
                }
            },
            error: function () {

            }
        });

    }
    function changeProjectStatus1(projectId){
        $.ajax({
            type: "GET",
            url: '/project/update.do?flag=2',
            data:{projectId:projectId},
            success: function (data) {
                if (data == "success") {
                    loadURL("/project/showList.do", $('#inbox-content > .table-wrap'));
                } else {
                    alert('保存失败!');
                }
            },
            error: function () {

            }
        });

    }
    function delProject(delId){
        $.ajax({
            type: "GET",
            url: '/project/update.do',
            data:{delId:delId},
            success: function (data) {
                if (data == "success") {
                    loadURL("/project/showList.do", $('#inbox-content > .table-wrap'));
                } else {
                    alert('保存失败!');
                }
            },
            error: function () {

            }
        });

    }

    function projectCount(projectOid){
        loadURL("/projectCount/show.do?projectOid="+projectOid,$('#s5'));
    }


    function projectTask(projectOid){
        loadURL("/projectTask/show.do?projectOid="+projectOid,$('#s2'));
    }
    function projectAttachment(projectOid){
        loadURL("/projectAttachment/show.do?projectOid="+projectOid,$('#s6'));
    }
    function projectPerson(projectOid){
        loadURL("/projectPerson/show.do?projectOid="+projectOid,$('#s7'));
    }
    function operationRecord(projectOid){
        loadURL("/operationRecord/show.do?projectOid="+projectOid,$('#s8'));
    }
    function updateProjectDetail(projectId){
        console.log(projectId);
        loadURL("/project/findProjectDetail.do?projectOid="+projectId,$('#inbox-content > .table-wrap'));
    }
    loadScript("/smartAdmin/js/plugin/x-editable/moment.min.js", loadMockJax);

    function loadMockJax() {
        loadScript("/smartAdmin/js/plugin/x-editable/jquery.mockjax.min.js", loadXeditable);
    }

    function loadXeditable() {
        loadScript("/smartAdmin/js/plugin/x-editable/x-editable.min.js", loadTypeHead);
    }

    function loadTypeHead() {
        loadScript("/smartAdmin/js/plugin/typeahead/typeahead.min.js", loadTypeaheadjs);
    }

    function loadTypeaheadjs() {

        loadScript("/smartAdmin/js/plugin/typeahead/typeaheadjs.min.js", runXEditDemo);
    }

    function runXEditDemo() {

        $('#vacation').editable({
            datepicker: {
                todayBtn: 'linked'
            }
        });
//        $.fn.editable.defaults.mode = 'inline';
        // $.fn.editable.defaults.mode = 'popup';

        $('#visibility').editable({
            url: '/project/updateProjectVisibility.do?projectId='+'${project.id}',
            name:'visibility',
            pk:'1',
            source: [{
                value: 0,
                text: '私密'
            }, {
                value: 1,
                text: '公开'
            }],
            display: function (value, sourceData) {
                var colors = {
                    "": "gray",
                    1: "green",
                    2: "blue"
                }, elem = $.grep(sourceData, function (o) {
                    return o.value == value;
                });

                if (elem.length) {
                    $(this).text(elem[0].text).css("color", colors[value]);
                } else {
                    $(this).empty();
                }
            }
        });

        $('#projectname').editable({
            url: '/project/updateProjectName.do?projectId='+'${project.id}',
            type: 'text',
            pk: 1,
            name: 'username',
            title: 'Enter username',
            validate: function(value) {
                if($.trim(value).length > 100) {
                    return '不能超过100个字符';
                }
            }
        });
        $('#projectGoal').editable({
            showbuttons: 'bottom',
            url: '/project/updateProjectGoal.do?projectId='+'${project.id}',
            pk: 1,
            name: 'projectGoal',
            title: 'Enter projectGoal',
            validate: function(value) {
                if($.trim(value).length > 500) {
                    return '不能超过500个字符';
                }
            }
        });
        $('#startTimes').editable({
            url:'/project/updateStartTime.do?projectId='+'${project.id}',
            format: 'yyyy-mm-dd',
            viewformat: 'yyyy/mm/dd',
            datepicker: {
                firstDay: 1
            },
            validate:function(value) {
                var endTime = $("#endTimes").html();
                var val = Date.parse(endTime);
                var endDate = new Date(val);
                if (value.getFullYear()>endDate.getFullYear() ) {
                    return "开始时间不能大于结束时间";
                }
                if ( value.getFullYear() == endDate.getFullYear()&&value.getMonth()> endDate.getMonth()) {
                    return "开始时间不能大于结束时间";
                }
                if (endDate.getFullYear()== value.getFullYear()&&endDate.getMonth()==value.getMonth()&&endDate.getDate()<value.getDate()) {
                    return "开始时间不能大于结束时间";
                }
            }
        });
        $('#endTimes').editable({
            url:'/project/updateEndTime.do?projectId='+'${project.id}',
            format: 'yyyy-mm-dd',
            viewformat: 'yyyy/mm/dd',
            datepicker: {
                firstDay: 1
            },
            validate:function(value) {
                var startTime = $("#startTimes").html();
                var val = Date.parse(startTime);
                var newDate = new Date(val);
                if (newDate.getFullYear() > value.getFullYear()) {
                    return "结束时间不能小于开始时间";
                }
                if (newDate.getFullYear() == value.getFullYear()&&newDate.getMonth()>value.getMonth()) {
                    return "结束时间不能小于开始时间";
                }
                if (newDate.getFullYear() == value.getFullYear()&&newDate.getMonth()==value.getMonth()&&newDate.getDate()>value.getDate()) {
                    return "结束时间不能小于开始时间";
                }
            }
        });

        $('#projectSchedule').editable({
            url: '/project/updateProjectSchedule.do?projectId='+'${project.id}',
            type: 'text',
            pk: 1,
            name: 'username',
            title: 'Enter username',
            success: function (data) {
                if (data.success == "success") {
                    $('.progress-bar').css({'width':data.ProjectSchedule+'%'}).find('span').html('');
                }
            },
            validate:function(value){
            var ss=/^[0-9]*$/i;
                if(ss.test(value)){
                    if(value>100||value<0){
                        return "项目进度不能大于100";
                    }
                }else{
                    return '项目进度必须为纯数字';
                }

            }
        });

    }
    //加载下拉页面
    var flags=0;

    function projectManager(){
        swal({
                    title: '<h4 class="modal-title"><p>选择负责人</p></h4>',
                    text: '<div class="modal-body no-padding"><div class="custom-scroll table-responsive" style="height:250px; overflow-y: auto;"><div id="organTree" style="text-align: left;"></div></div></div>',
                    html: true,
                    allowOutsideClick:true
                },
                function(){
                    //不加这段代码，在点击其他弹出框的时候会导致organTree里的内容显示，
                    // 加了之后消除organTree里的内容时弹出框正常显示
                    swal("","","success");
                });
        loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
        loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTreeswal1);
        function renderTreeswal1() {
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
                                $('#projectManager').html(data.text);
                                var personId=data.href;
                                $.ajax({
                                    type: "POST",
                                    url:'/project/updateProjectManager.do?projectId='+'${project.id}',
                                    data:
                                    {
                                        projectManager:data.text,
                                        personId:personId
                                    },
                                    success: function (data1) {
                                        if(data1=="success"){}
                                    },
                                    error: function () {

                                    }
                                });
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
    $(".trHide").hide();
    var trFlag=0;
    function trShow(){
        if(trFlag==0){
        $(".trHide").show();

            trFlag=1;
            $("#trShows").html("关闭详情&nbsp;&nbsp;<i  class='fa  fa-arrow-circle-up'></i>&nbsp;&nbsp;&nbsp;");
        }else{
            $(".trHide").hide();
            trFlag=0;
            $("#trShows").html("展开详情&nbsp;&nbsp;<i  class='fa  fa-arrow-circle-down'></i>&nbsp;&nbsp;&nbsp;");
        }
    }
</script>
<style>
</style>