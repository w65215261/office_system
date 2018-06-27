<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<!-- widget content -->
<div class="widget-body" id="pwidget-body">


    <hr class="simple">

    <ul id="myTab1" class="nav nav-tabs bordered">
        <li class="active">
            <a href="#s1" data-toggle="tab">选择分类</a>
        </li>
        <li>
            <a href="#s2" data-toggle="tab" ><i class="fa fa-fw fa-lg fa-gear"></i>选择项目</a>
        </li>
    </ul>

    <div id="myTabContent1" class="tab-content">
        <div class="tab-pane fade in active" id="s1">

            <div class="custom-scroll table-responsive" style="max-height:250px; overflow-x: scroll;">
            <div class="dd">
                    <ol class="dd-list">
                            <c:forEach items="${dictList}" var="d">
                                <c:if test="${d.parentId=='-1'}">
                                    <li class="dd-item" data-id="1">
                                        <div class="dd3-content">${d.dictName}</div>
                                        <ol>
                                            <c:forEach items="${dictList}" var="d1">
                                                <c:if test="${d1.parentId==d.id}">
                                                    <li class="dd-item" data-id="2">
                                                         <div class="dd3-content"> <span   class="dict" onclick="chooseDict('${d1.dictName}')">${d1.dictName}</span></div>
                                                        <c:forEach items="${dictList}" var="d2">
                                                            <c:if test="${d2.parentId==d1.id}">
                                                                <ol class="dd-list">
                                                                    <li class="dd-item" data-id="3">
                                                                        <div class="dd3-content"><span   class="dict" onclick="chooseDict('${d2.dictName}')">${d2.dictName}</span></div>
                                                                        <c:forEach items="${dictList}" var="d3">
                                                                            <c:if test="${d3.parentId==d2.id}">
                                                                                <ol class="dd-list">
                                                                                    <li class="dd-item" data-id="4">
                                                                                        <div class="dd3-content"><span   class="dict" onclick="chooseDict('${d3.dictName}')">${d3.dictName}</span></div>
                                                                                        <c:forEach items="${dictList}" var="d4">
                                                                                            <c:if test="${d4.parentId==d3.id}">
                                                                                                <ol class="dd-list">
                                                                                                    <li class="dd-item" data-id="5">
                                                                                                        <div class="dd3-content"><span   class="dict"  onclick="chooseDict('${d4.dictName}')">${d4.dictName}</span></div>
                                                                                                    </li>
                                                                                                </ol>
                                                                                            </c:if>
                                                                                        </c:forEach>
                                                                                    </li>
                                                                                </ol>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </li>
                                                                </ol>
                                                            </c:if>
                                                        </c:forEach>
                                                    </li>
                                                </c:if>
                                            </c:forEach>
                                        </ol>
                                    </li>
                                </c:if>
                            </c:forEach>
                    </ol>
                </div>
                </div>
        </div>
        <div class="tab-pane fade" id="s2">
            <div class="custom-scroll table-responsive" style="max-height:240px; overflow-y: scroll;">
                    <c:forEach items="${projectList1}" var="p">

                            <ol class="dd-list">
                                <li class="dd-item" data-id="1">
                                    <div class="dd3-content"><span  id="projectName" onclick="chooseProject('${p.projectName}')" style="display:block;">${p.projectName}</span></div>
                                </li>
                            </ol>
                    </c:forEach>
            </div>
        </div>
    </div>




</div>
<script type="text/javascript">

    jQuery(function() {
        $('.dd').nestable();
    });
    $('.dd').nestable('collapseAll');//折叠所有节点
    $('.dd').nestable('serialize');//树结构序列化
    function chooseDict(dictId){
        document.getElementById("popoverSpan1").innerHTML = dictId;
        $('#inputTest1').popover('hide');
    }
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
            height : 180,
            focus : false,
            tabsize : 2,
            airMode:true
        });

        $('#nestable').nestable();
        $('#nestable').nestable("collapseAll");
        $('#nestable3').nestable();
        $('#nestable3').nestable("collapseAll");

    };
    loadScript("/smartAdmin/js/plugin/summernote/summernote.min.js", function(){
        loadScript("/smartAdmin/js/plugin/jquery-nestable/jquery.nestable.min.js", pagefunction)
    });
        function chooseProject(projectName) {
            console.log(projectName);
            document.getElementById("popoverSpan1").innerHTML = projectName;
            $('#inputTest1').popover('hide');


        }

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
    min-width: 150px;
 }
 .popover {
     margin-left: 60px;
     min-width: 100px;
     z-index   : 1060;
     height: auto;
 }
</style>