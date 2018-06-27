<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.pmcc.soft.core.utils.AppUtils"%>
<%@page import="org.springframework.web.util.WebUtils"%>
<%@page import="com.pmcc.soft.core.common.OnlineUser"%>


<!-- MODAL PLACE HOLDER -->
<div class="modal fade" id="receieverModal" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">
    <div class="modal-dialog"  style="width:300px ;height:600px">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><p>选择审批人</p></h4>
            </div>


            <div class="modal-body no-padding">
                <div class="custom-scroll table-responsive" style="height:250px; overflow-y: scroll;">
                    <div id="organTree">


                    </div>

                </div>

            </div>



            <script>


                loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
                loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTree);


                function renderTree() {
                    console.log("modalShow111");
                    $.ajax({
                        type: "GET",
                        url: '/organization/queryAlltree.do',
                        data: {},
                        success: function (data) {
                            $('#organTree').treeview({
                                data: data,
                                onNodeSelected: function (event, data) {
                                    console.log(event);
                                    console.log(data.icon);
                                    if(data.icon=="glyphicon glyphicon-tower"){
                                        return ;
                                    }else{

                                        $('#receiverLabel')[0].innerHTML=data.text;
                                        var personId=data.href;
                                        $('#approvePersonName').val(personId);
                                        $("#receieverModal").modal("hide");

                                    }



                                }
                            });
                        },
                        // 调用出错执行的函数
                        error: function () {
                        }
                    });
                }

            </script>
        </div>
    </div>
</div>
<!-- END MODAL -->

<div class="jarviswidget" id="wid-id-1" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="false">

    <header>
        <span class="widget-icon"> <i class="fa fa-edit"></i> </span>
        <h2>修改项目<input type="text" value="${project.id}" id="projectId"></h2>

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

            <form class="smart-form" id="createProject">
                <fieldset>
                    <section>
                        <label class="label">项目名称</label>
                        <label class="input">
                            <input type="text" name="projectName" id="projectName" value="${project.projectName}">
                        </label>
                    </section>
                    <section>
                        <label class="label">起止时间</label>
                        <div class="row">
                            <section class="col col-4">
                                <label class="input"> <i class="icon-append fa fa-calendar"></i>
                                    <input type="text" name="startTime" id="startTime" value="${startTime}">
                                </label>
                            </section>
                            <section class="col col-4">
                                <label class="input"> <i class="icon-append fa fa-calendar"></i>
                                    <input type="text" name="endTime" id="endTime" value="${endTime}">
                                </label>
                            </section>
                        </div>
                    </section>
                    <section>
                        <label class="label">谁来负责</label>
                        <label class="input">
                            <input type="text"   id="projectManager" name="projectManager" readonly="true"  value="${project.projectManager}" >
                        </label>
                    </section>
                    <section>

                        <label class="label">审批人

                            <button type="button" class="btn btn-warning" onclick="showModal()"  id="approvePersonId1">选择</button>
                        </label>
                        <label class="input">

                            <div class="box">
                                <input type="text" class="text"  id="approvePersonId" name="approvePersonId" readonly="true" value="">
                                <span class="float"  id="receiverLabel" >${ApprovePerson}</span>
                            </div>
                        </label>


                    </section>

                    <section>
                        <label class="label">项目目标</label>
                        <label class="textarea">
                            <textarea rows="3" class="custom-scroll"  id="projectGoal" name="projectGoal">${project.projectGoal}</textarea>
                        </label>
                    </section>
                    <section>
                        <label class="label">可见范围</label>
                        <div class="inline-group">
                            <label class="radio">
                                <input type="radio" name="visibility" checked="checked"value="0">
                                <i></i>私密</label>
                            <label class="radio">
                                <input type="radio" name="visibility" value="1">
                                <i></i>公开</label>
                        </div>
                    </section>
                    <footer>
                        <button type="button"  id="saveProject" class="btn btn-primary">
                            Submit
                        </button>
                    </footer>
                </fieldset>
            </form>

        </div>
        <!-- end widget content -->

    </div>
    <!-- end widget div -->

</div>
<!-- end widget -->


<script type="text/javascript">

    pageSetUp();

    var pagefunction = function() {
    }


    $('#startTime').datepicker({
        dateFormat : 'yy-mm-dd',
        showOtherMonths:true,
        prevText : '<i class="fa fa-chevron-left"></i>',
        nextText : '<i class="fa fa-chevron-right"></i>',
        onSelect : function(selectedDate) {
            $('#endTime').datepicker('option', 'minDate', selectedDate);
        }
    });

    $('#endTime').datepicker({
        dateFormat : 'yy-mm-dd',
        showOtherMonths:true,
        changeMonth:true,
        changeYear:true,
        yearRange :'-0:+5',
        prevText : '<i class="fa fa-chevron-left"></i>',
        nextText : '<i class="fa fa-chevron-right"></i>',
        onSelect : function(selectedDate) {
            $('#startTime').datepicker('option', 'maxDate', selectedDate);
        }
    });

    $("#saveProject").click(function (){
        var projectId=$('#projectId').val();
        var projectName=$('#projectName').val();
        var startTime=$('#startTime').val();
        var endTime=$('#endTime').val();
        var projectManager=$('#projectManager').val();
        var receiverLabel=$('#receiverLabel').text();
        var projectGoal=$('#projectGoal').val();
        var visibility=$('input:radio[name="visibility"]:checked').val();
            $.ajax({
                type: "POST",
                url: '/project/updateProjectDetail.do',
                data:{
                    projectId:projectId,
                    projectName:projectName,
                    startTime:startTime,
                    endTime:endTime,
                    projectManager:projectManager,
                    personName:receiverLabel,
                    projectGoal:projectGoal,
                    visibility:visibility
                },
                success: function (data) {
                    console.log("data"+data);
                    console.log("datasuccess"+data.success);
                    console.log("data.projectId"+data.projectId);
                    if(data.success=="success"){
                        loadURL("/project/findProjectByOid.do?projectId="+data.projectId, $('#inbox-content > .table-wrap'))
                        console.log("改跳转了");
                    }
                },
                error: function () {

                }
            });

    });

    function showModal(){
        console.log("modalShow");
        $("#receieverModal").modal("show");
    }
</script>
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
    .box{height:30px; position:relative;}
    .box .text{float:left;border:none;background:none;height:100%;line-height:30px;border:solid 1px #ccc;}
    .box .float{height:100%;line-height:30px; text-align:center;float:left;position:absolute; top:2px;left:15px;}
</style>