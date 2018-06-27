<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<input type="hidden"  id="projectUpdId" value="${project.id}">
<c:if test="${flag==1}">
<!-- widget content -->
            <div class="widget-body " id="pwidget-body" style="width: 250px">

                <div class="row" >
                     <input class="" style="height: 30px;border:  solid 1px#357ebd" type="text"  id="projectUpdName" size="19" value="${project.projectName}">
                    &nbsp;&nbsp;<button  class="update" style="height: 30px;width: 30px;"> <i class="fa  fa-check "></i></button> &nbsp;<button  class="delete" style="height: 30px;width: 30px;line-height: 2px"> <i class="fa  fa-times "></i></button>
                </div>
            </div>
</c:if>
<c:if test="${flag==2}">
    <!-- widget content -->
    <div class="widget-body " id="pwidget-body" style="width: 250px">
        <div class="row" >
            <textarea class="" style="border:  solid 1px#357ebd;width: 178px;height: 200px" type="text"  id="projectGoalUpd" >${project.projectGoal}</textarea>
            &nbsp;&nbsp;<button  id="update" style="height: 30px;width: 30px;"> <i class="fa  fa-check "></i></button> &nbsp;<button  class="delete" style="height: 30px;width: 30px;line-height: 2px"> <i class="fa  fa-times "></i></button>
        </div>
    </div>
</c:if>
<c:if test="${flag==3}">
            <div class="widget-body " id="pwidget-body" style="width: 250px">
                <div class="row" >
                    <input  style="height: 30px;border:  solid 1px#357ebd"  type="text" name="startTime" id="startTimeUpd" placeholder="选择开始日期" value="${startTime}">
                    &nbsp;&nbsp;<button  id="update3" style="height: 30px;width: 30px;"> <i class="fa  fa-check "></i></button> &nbsp;<button  class="delete" style="height: 30px;width: 30px;line-height: 2px"> <i class="fa  fa-times "></i></button>
                </div>
            </div>
</c:if>
<c:if test="${flag==4}">
    <!-- widget content -->
    <div class="widget-body " id="pwidget-body" style="width: 250px">
        <div class="row" >
            <input type="text"   style="height: 30px;border:  solid 1px#357ebd"  name="startTime" id="endTimeUpd" placeholder="选择结束日期" value="${endTime}">
            &nbsp;&nbsp;<button  id="update4" style="height: 30px;width: 30px;"> <i class="fa  fa-check "></i></button> &nbsp;<button  class="delete" style="height: 30px;width: 30px;line-height: 2px"> <i class="fa  fa-times "></i></button>
        </div>
    </div>
</c:if>
<c:if test="${flag==5}">
    <!-- widget content -->
    <div class="widget-body">

        <div class="tree smart-form">
            <ul>
                <li>
                    <span><i class="fa fa-lg fa-folder-open"></i> Parent</span>
                    <ul>
                        <li>
                            <span><i class="fa fa-lg fa-plus-circle"></i> Administrators</span>
                            <ul>
                                <li style="display:none">
													<span> <label class="checkbox inline-block">
                                                        <input type="checkbox" name="checkbox-inline">
                                                        <i></i>Michael.Jackson</label> </span>
                                </li>
                                <li style="display:none">
													<span> <label class="checkbox inline-block">
                                                        <input type="checkbox" checked="checked" name="checkbox-inline">
                                                        <i></i>Sunny.Ahmed</label> </span>
                                </li>
                                <li style="display:none">
													<span> <label class="checkbox inline-block">
                                                        <input type="checkbox" checked="checked" name="checkbox-inline">
                                                        <i></i>Jackie.Chan</label> </span>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>

    </div>
    <!-- end widget content -->
</c:if>
<c:if test="${flag==6}">
    <!-- widget content -->
    <div class="widget-body " id="pwidget-body" style="width: 250px">
        <div class="row" >
            <input class="" style="height: 30px;border:  solid 1px#357ebd" type="text"  id="projectUpdName6" size="19" value="${project.projectName}">
            &nbsp;&nbsp;<button  class="update" style="height: 30px;width: 30px;"> <i class="fa  fa-check "></i></button> &nbsp;<button  class="delete" style="height: 30px;width: 30px;line-height: 2px"> <i class="fa  fa-times "></i></button>
        </div>
    </div>
</c:if>
<c:if test="${flag==7}">
    <!-- widget content -->
    <div class="widget-body " id="pwidget-body" style="width: 250px">
        <div class="row" >
            <input class="" style="height: 30px;border:  solid 1px#357ebd" type="text"  id="projectUpdName7" size="19" value="${project.projectName}">
            &nbsp;&nbsp;<button  class="update" style="height: 30px;width: 30px;"> <i class="fa  fa-check "></i></button> &nbsp;<button  class="delete" style="height: 30px;width: 30px;line-height: 2px"> <i class="fa  fa-times "></i></button>
        </div>
    </div>
</c:if>

<script type="text/javascript">
    $('#startTimeUpd').datepicker({
        dateFormat : 'yy-mm-dd',
        showOtherMonths:true,
        changeMonth:true,
        changeYear:true,
        prevText : '<i class="fa fa-chevron-left"></i>',
        nextText : '<i class="fa fa-chevron-right"></i>',
        onSelect : function(selectedDate) {
            $('#endTime').datepicker('option', 'minDate', selectedDate);
        }
    });
    $('#endTimeUpd').datepicker({
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
        $(".update").click(function (){
            console.log(2);
            var projectId=$("#projectUpdId").val();
            var projectNames=$("#projectUpdName").val();
            var projectGoalUpd=$("#projectGoalUpd").html();
            console.log("projectGoalUpdnimabi"+projectGoalUpd)
            $("#popoverSpan1").html(projectNames);
            $.ajax({
                type: 'POST',
                url: '/project/updateProjectDetail.do',
                data:{
                    projectId:projectId,
                    projectName:projectNames,
                    projectGoal:projectGoalUpd
                },
                success: function(data) {
                    console.log(data.projectName);

                }
            });
            $('#inputTest1').popover('hide');
        })
        $(".delete").click(function (){
            $('#inputTest1').popover('hide');
            $('#inputTest2').popover('hide');
            $('#inputTest3').popover('hide');
            $('#inputTest4').popover('hide');
            $('#inputTest5').popover('hide');
            $('#inputTest6').popover('hide');
            $('#inputTest7').popover('hide');
        })
    $("#update").click(function (){
        console.log(33333);
        var projectId=$("#projectUpdId").val();
        var projectGoalUpd=$("#projectGoalUpd").val();
        console.log("projectGoalUpdnimabi"+projectGoalUpd)
        $("#popoverSpan2").html(projectGoalUpd);
        $.ajax({
            type: 'POST',
            url: '/project/updateProjectDetail.do',
            data:{
                projectId:projectId,
                projectGoal:projectGoalUpd
            },
            success: function(data) {
                console.log(data.projectName);

            }
        });
        $('#inputTest2').popover('hide');
    })
    $("#update3").click(function (){
        console.log(4444);
        var projectId=$("#projectUpdId").val();
        var startTime=$("#startTimeUpd").val();
        console.log("startTimeUpd"+startTime)
        $("#popoverSpan3").html(startTime);
        $.ajax({
            type: 'POST',
            url: '/project/updateProjectDetail.do',
            data:{
                projectId:projectId,
                startTime:startTime
            },
            success: function(data) {
            }
        });
        $('#inputTest3').popover('hide');
    })
    $("#update4").click(function (){
        console.log(555);
        var projectId=$("#projectUpdId").val();
        var endTime=$("#endTimeUpd").val();
        console.log("endTimeUpd"+endTime)
        $("#popoverSpan4").html(endTime);
        $.ajax({
            type: 'POST',
            url: '/project/updateProjectDetail.do',
            data:{
                projectId:projectId,
                endTime:endTime
            },
            success: function(data) {
            }
        });
        $('#inputTest4').popover('hide');
    })
   loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
//    loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTree);
//    function renderTree() {
//        $.ajax({
//            type: "GET",
//            url: '/organization/queryAlltree.do',
//            data: {},
//            success: function (data) {
//                $('#organTree').treeview({
//                    data: data,
//                    onNodeSelected: function (event, data) {
//                        if(data.icon=="glyphicon glyphicon-tower"){
//                            return ;
//                        }else{
//
//                            $('#receiverLabel2').val(data.text);
//                            var personId=data.href;
//                            $('#approvePersonName2').val(personId);
//                            $("#receieverModal2").modal("hide");
//
//                        }
//                    }
//                });
//            },
//            // 调用出错执行的函数
//            error: function () {
//            }
//        });
//    }
//    var pagefunction = function() {
//
//        loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
//
//    };
//    pagefunction();
</script>
<style type="text/css">
 #pwidget-body{
     padding-left: 10px;
     padding-right: 10px;
}
    #projectName{
        cursor:pointer
    }
 .dict{
     cursor:pointer;
     display:block;
     width:330px
 }
</style>