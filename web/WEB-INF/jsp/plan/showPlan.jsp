<%@ page import="com.pmcc.soft.week.domain.TreeTask" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="/smartAdmin/css/BootSideMenu.css">
<link rel="stylesheet" href="/smartAdmin/css/normalize.css">
<style>
    .summernote {
        border: 1px solid #a9a9a9;
        border-width: 1px 1px;
    }
    .summernote2{
        border:1px solid #a9a9a9; border-width:0px 1px 1px 1px;
    }
    .summernote3 {
        border: 1px solid #a9a9a9;
        border-width: 1px 1px;
    }
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



<!--查找plan结束-->
<div class="col-sm-13 col-lg-13">
    <div class="col-sm-13 col-lg-13" id="nestable">
        <ol class="dd-list">
            <c:forEach items="${treeTasks}" var="task" varStatus="t">
                <li class="dd-item dd3-item" data-id="1">
                    <div class="dd3-content" style="background-color: white;border: 0px;">
                        <c:if test="${task.tasks.size()==0}">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:if>
                <span class="semi-bold">
                 <a href="javascript:void(0);" id="${task.oid}" class="btn btn-primary btn-xs" rel="popover" data-placement="bottom"  data-html="true" onclick="loadPopoverStatus('${task.oid}')">
                     <c:if test="${task.taskStatus==0}">
                         <i class="fa fa-square-o"></i>
                     </c:if>
                     <c:if test="${task.taskStatus==1}">
                         <i class="fa fa-check-square-o"></i>
                     </c:if>
                     <c:if test="${task.taskStatus==2}">
                         <i class="fa fa-spinner"></i>
                     </c:if>
                     <c:if test="${task.taskStatus==3}">
                         <i class="fa fa-times-circle-o"></i>
                     </c:if>
                     <c:if test="${task.taskStatus==4}">
                         <i class="fa fa-clock-o"></i>
                     </c:if>
                     <c:if test="${task.taskStatus==5}">
                         <i class="fa fa-pause"></i>
                     </c:if>
                 </a>
                </span>
                        <span><a href="javascript:window.scrollTo( 0, 0 );" onclick="decompositionTask('${task.oid}')">计划名称：${task.taskName}</a></span>
                        <input type="hidden" id="h${t.count}" value="${task.oid}"><input type="hidden" id="hasStard${t.count}" value="${task.hasStar}">
                                            <span class="pull-right" id="d${t.count}"  onclick="changeStar(${t.count})">
                                                  <span class='has1'>
                                                    <c:if test="${task.hasStar==0}">
                                                        <i class='fa fa-star-o'></i>
                                                    </c:if>
                                                    <c:if test="${task.hasStar==1}">
                                                        <i class='fa fa-star'></i>
                                                    </c:if>
                                                      </span>
                                            </span>
                        <span class="pull-right">${task.responsiblePersonName}</span>
                        <span class="pull-right"><fmt:formatDate pattern="yyyy-MM-dd" value="${task.endTime}"/>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    </div>

                    <c:choose>

                        <c:when test="${task.tasks.size()!=0}">
                            <ol class="dd-list">
                                <c:forEach items="${task.tasks}" var="task2" varStatus="t1">
                                    <li class="dd-item dd3-item" data-id="2">
                                        <div class="dd3-content" style="background-color: white;border: 0px;">

                                            <c:if test="${task2.tasks.size()==0}">
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            </c:if>
                                                        <span class="semi-bold">
                                                          <a href="javascript:void(0);" id="${task2.oid}" class="btn btn-primary btn-xs" rel="popover" data-placement="bottom"  data-html="true" onclick="loadPopoverStatus('${task2.oid}')">
                                                              <c:if test="${task2.taskStatus==0}">
                                                                  <i class="fa fa-square-o"></i>
                                                              </c:if>
                                                              <c:if test="${task2.taskStatus==1}">
                                                                  <i class="fa fa-check-square-o"></i>
                                                              </c:if>
                                                              <c:if test="${task2.taskStatus==2}">
                                                                  <i class="fa fa-spinner"></i>
                                                              </c:if>
                                                              <c:if test="${task2.taskStatus==3}">
                                                                  <i class="fa fa-times-circle-o"></i>
                                                              </c:if>
                                                              <c:if test="${task2.taskStatus==4}">
                                                                  <i class="fa fa-clock-o"></i>
                                                              </c:if>
                                                              <c:if test="${task2.taskStatus==5}">
                                                                  <i class="fa fa-pause"></i>
                                                              </c:if>
                                                          </a>
                                                        </span>
                                            <span><a href="javascript:window.scrollTo( 0, 0 );" onclick="decompositionTask('${task2.oid}')">计划名称：${task2.taskName}</a></span>
                                            <input type="hidden" id="hh${t.count}${t1.count}" value="${task2.oid}"> <input type="hidden" id="hasStardd${t.count}${t1.count}" value="${task2.hasStar}">
                                                        <span class="pull-right" id="dd${t.count}${t1.count}"  onclick="changeStar1(${t.count}${t1.count})">
                                                              <span class='has11'>
                                                                <c:if test="${task2.hasStar==0}">
                                                                    <i class='fa fa-star-o'></i>
                                                                </c:if>
                                                                <c:if test="${task2.hasStar==1}">
                                                                    <i class='fa fa-star'></i>
                                                                </c:if>
                                                                  </span>
                                                        </span>
                                            <span class="pull-right">${task2.responsiblePersonName}</span>
                                            <span class="pull-right"><fmt:formatDate pattern="yyyy-MM-dd" value="${task2.endTime}"/>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                        </div>
                                        <c:choose>

                                            <c:when test="${task2.tasks.size()!=0}">
                                                <ol class="dd-list">
                                                    <c:forEach items="${task2.tasks}" var="task3" varStatus="t2">
                                                        <li class="dd-item dd3-item" data-id="3">
                                                            <div class="dd3-content" style="background-color: white;border: 0px;">

                                                                <c:if test="${task3.tasks.size()==0}">
                                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                </c:if>
                                                                              <span class="semi-bold">
                                                                                 <a href="javascript:void(0);" id="${task3.oid}" class="btn btn-primary btn-xs" rel="popover" data-placement="bottom"  data-html="true" onclick="loadPopoverStatus('${task3.oid}')">
                                                                                     <c:if test="${task3.taskStatus==0}">
                                                                                         <i class="fa fa-square-o"></i>
                                                                                     </c:if>
                                                                                     <c:if test="${task3.taskStatus==1}">
                                                                                         <i class="fa fa-check-square-o"></i>
                                                                                     </c:if>
                                                                                     <c:if test="${task3.taskStatus==2}">
                                                                                         <i class="fa fa-spinner"></i>
                                                                                     </c:if>
                                                                                     <c:if test="${task3.taskStatus==3}">
                                                                                         <i class="fa fa-times-circle-o"></i>
                                                                                     </c:if>
                                                                                     <c:if test="${task3.taskStatus==4}">
                                                                                         <i class="fa fa-clock-o"></i>
                                                                                     </c:if>
                                                                                     <c:if test="${task3.taskStatus==5}">
                                                                                         <i class="fa fa-pause"></i>
                                                                                     </c:if>
                                                                                 </a>

                                                                              </span>
                                                                <span><a href="javascript:window.scrollTo( 0, 0 );" onclick="decompositionTask('${task3.oid}')">计划名称：${task3.taskName}</a></span>
                                                                <input type="hidden" id="hhh${t.count}${t1.count}${t2.count}" value="${task3.oid}"><input type="hidden" id="hasStarddd${t.count}${t1.count}${t2.count}" value="${task3.hasStar}">
                                                                            <span class="pull-right" id="ddd${t.count}${t1.count}${t2.count}"  onclick="changeStar2(${t.count}${t1.count}${t2.count})">
                                                                                  <span class='has111'>
                                                                                    <c:if test="${task3.hasStar==0}">
                                                                                        <i class='fa fa-star-o'></i>
                                                                                    </c:if>
                                                                                    <c:if test="${task3.hasStar==1}">
                                                                                        <i class='fa fa-star'></i>
                                                                                    </c:if>
                                                                                      </span>
                                                                            </span>
                                                                <span class="pull-right">${task3.responsiblePersonName}</span>
                                                                <span class="pull-right"><fmt:formatDate pattern="yyyy-MM-dd" value="${task3.endTime}"/>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                                            </div>
                                                            <c:choose>
                                                                <c:when test="${task3.tasks.size()!=0}">

                                                                    <ol class="dd-list">

                                                                        <c:forEach items="${task3.tasks}" var="task4" varStatus="t3">

                                                                            <li class="dd-item dd3-item" data-id="4">
                                                                                <div class="dd3-content" style="background-color: white;border: 0px;">

                                                                                    <c:if test="${task4.tasks.size()==0}">
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                    </c:if>
                                                                                            <span class="semi-bold">
                                                                                             <a href="javascript:window.scrollTo( 0, 0 );" id="${task4.oid}" class="btn btn-primary btn-xs" rel="popover" data-placement="bottom"  data-html="true" onclick="loadPopoverStatus('${task4.oid}')">
                                                                                                 <c:if test="${task4.taskStatus==0}">
                                                                                                     <i class="fa fa-square-o"></i>
                                                                                                 </c:if>
                                                                                                 <c:if test="${task4.taskStatus==1}">
                                                                                                     <i class="fa fa-check-square-o"></i>
                                                                                                 </c:if>
                                                                                                 <c:if test="${task4.taskStatus==2}">
                                                                                                     <i class="fa fa-spinner"></i>
                                                                                                 </c:if>
                                                                                                 <c:if test="${task4.taskStatus==3}">
                                                                                                     <i class="fa fa-times-circle-o"></i>
                                                                                                 </c:if>
                                                                                                 <c:if test="${task4.taskStatus==4}">
                                                                                                     <i class="fa fa-clock-o"></i>
                                                                                                 </c:if>
                                                                                                 <c:if test="${task4.taskStatus==5}">
                                                                                                     <i class="fa fa-pause"></i>
                                                                                                 </c:if>
                                                                                             </a>

                                                                                            </span>
                                                                                    <span><a href="javascript:window.scrollTo( 0, 0 );" onclick="decompositionTask('${task4.oid}')">计划名称：${task4.taskName}</a></span>
                                                                                    <input type="hidden" id="hhhh${t.count}${t1.count}${t2.count}${t3.count}" value="${task4.oid}"><input type="hidden" id="hasStardddd${t.count}${t1.count}${t2.count}${t3.count}" value="${task4.hasStar}">
                                                                                                <span class="pull-right" id="dddd${t.count}${t1.count}${t2.count}${t3.count}"  onclick="changeStar3(${t.count}${t1.count}${t2.count}${t3.count})">
                                                                                                      <span class='has1111'>
                                                                                                        <c:if test="${task4.hasStar==0}">
                                                                                                            <i class='fa fa-star-o'></i>
                                                                                                        </c:if>
                                                                                                        <c:if test="${task4.hasStar==1}">
                                                                                                            <i class='fa fa-star'></i>
                                                                                                        </c:if>
                                                                                                          </span>
                                                                                                </span>
                                                                                    <span class="pull-right">${task4.responsiblePersonName}</span>
                                                                                    <span class="pull-right"><fmt:formatDate pattern="yyyy-MM-dd" value="${task4.endTime}"/>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                                                                </div>
                                                                            </li>
                                                                        </c:forEach>
                                                                    </ol>
                                                                </c:when>
                                                                <c:otherwise>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </li>
                                                    </c:forEach>
                                                </ol>
                                            </c:when>
                                            <c:otherwise>
                                            </c:otherwise>
                                        </c:choose>
                                    </li>
                                </c:forEach>
                            </ol>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                </li>
            </c:forEach>
        </ol>
    </div>
</div>

<script type="text/javascript">

    pageSetUp();

    var pagefunction = function() {


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
            $('#responsiblePersonOid').editable({
                showbuttons: false
            });
        }

        $('.summernote').summernote({
            height: 180,
            focus: false,
            tabsize: 2,
            airMode: true
        });

        $('#nestable').nestable();
        $('#nestable').nestable("collapseAll");
    }
    loadScript("/smartAdmin/js/plugin/summernote/summernote.min.js", function(){
        loadScript("/smartAdmin/js/plugin/jquery-nestable/jquery.nestable.min.js", pagefunction)
    });
    var hasStarP=0;
    function changeStar(count){
        console.log("hasStar"+11111);
        var oid=$('#h'+count).val();
        var hasStard=$('#hasStard'+count).val();
        if(hasStard==0){
            console.log("hasStar"+hasStard);
            document.getElementById('d'+count).innerHTML = "<span class='has1'> <i class='fa fa-star'></i></span>";
            hasStarP=1;
            document.getElementById('hasStard'+count).value="1";
        } else {
            document.getElementById('d'+count).innerHTML = "<span class='has1'><i class='fa fa-star-o'></i></span>";
            hasStarP=0;
            document.getElementById('hasStard'+count).value="0";
        }
        $.ajax({
            type: "POST",
            url: '/plan/updateHasStar.do',
            data:{
                hasStar:hasStarP,
                oid:oid
            },
            success: function () {
            },
            error: function () {
            }
        });
    }
    var hasStarP1=0;
    function changeStar1(count){
        console.log("111111111111count"+count);
        console.log("dd"+count);
        var oid=$('#hh'+count).val();
        console.log("要修改的计划的OID"+oid);
        var hasStardd=$('#hasStardd'+count).val();
        if(hasStardd==0){
            document.getElementById("dd"+count).innerHTML = "<span class='has11'> <i class='fa fa-star'></i></span>";
            hasStarP1=1;
            document.getElementById('hasStardd'+count).value="1";
        } else {
            document.getElementById('dd'+count).innerHTML = "<span class='has11'><i class='fa fa-star-o'></i></span>";
            hasStarP1=0;
            document.getElementById('hasStardd'+count).value="0";
        }
        $.ajax({
            type: "POST",
            url: '/plan/updateHasStar.do',
            data:{
                hasStar:hasStarP1,
                oid:oid
            },
            success: function (data) {
            },
            error: function () {
            }
        });
    }
    var hasStarP2=0;
    function changeStar2(count){
        var oid=$('#hhh'+count).val();
        var hasStarddd=$('#hasStarddd'+count).val();
        if(hasStarddd==0){
            document.getElementById('ddd'+count).innerHTML = "<span class='has111'> <i class='fa fa-star'></i></span>";
            hasStarP2=1;
            document.getElementById('hasStarddd'+count).value="1";
        } else {
            document.getElementById('ddd'+count).innerHTML = "<span class='has11'><i class='fa fa-star-o'></i></span>";
            hasStarP2=0;
            document.getElementById('hasStarddd'+count).value="0";
        }
        $.ajax({
            type: "POST",
            url: '/plan/updateHasStar.do',
            data:{
                hasStar:hasStarP2,
                oid:oid
            },
            success: function () {
            },
            error: function () {
            }
        });
    }
    var hasStarP3=0;
    function changeStar3(count){
        var oid=$('#hhhh'+count).val();
        var hasStardddd=$('#hasStardddd'+count).val();
        if(hasStardddd==0){
            document.getElementById('dddd'+count).innerHTML = "<span class='has1111'> <i class='fa fa-star'></i></span>";
            hasStarP3=1;
            document.getElementById('hasStardddd'+count).value="1";
        } else {
            document.getElementById('dddd'+count).innerHTML = "<span class='has1111'><i class='fa fa-star-o'></i></span>";
            hasStarP3=0;
            document.getElementById('hasStardddd'+count).value="0";
        }
        $.ajax({
            type: "POST",
            url: '/plan/updateHasStar.do',
            data:{
                hasStar:hasStarP3,
                oid:oid
            },
            success: function () {
            },
            error: function () {
            }
        });
    }
    function loadPopoverStatus(taskOid){
        var id = "#"+taskOid;
        $.ajax({
            type: 'POST',
            url: '/projectTask/loadPopoverStatus.do',
            dataType: 'html',
            data:{
                taskOid:taskOid
            },
            success: function(data) {
                $(id).attr('data-content', data);
                $(id).popover('show');
            }
        });
    }

</script>
<style type="text/css">
    .popover {
        min-width: 100px;
        z-index   : 1060;
        height: auto;
    }
    .week{
        cursor:pointer
    }
    .has1{
        margin-left: 10px;
        cursor:pointer;
    }
    .has11{
        margin-left: 10px;
        cursor:pointer;
    }
    .has111{
        margin-left: 10px;
        cursor:pointer;
    }
    .has1111{
        margin-left: 10px;
        cursor:pointer;
    }
</style>