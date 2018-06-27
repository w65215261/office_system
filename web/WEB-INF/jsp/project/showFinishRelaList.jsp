<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="widget-body" style="width: 910px; height: auto ">
        <div class="tab-pane fade in active" id="s2">
            <div style="width: 910px;text-align: center;height: 260px">
                <c:forEach items="${projectRelaFinishList}" var="dd">

                    <a href="javascript:void(0); " onclick="projectTask('${dd.project.id}')" class="jarvismetro-tile bigger-cubes bg-color-orangeDark">
                        <span class="iconbox">
                         <span>项目名称:${dd.project.projectName}</span>
                         <span>负责人:${dd.project.projectManager}</span>
                         <span>审批人:${dd.approvalPerson}</span>
                             <c:choose>
                                 <c:when test="${dd.project.visibility=='0'}">
                                     <span>可见范围:私密</span>
                                 </c:when>
                                 <c:otherwise>
                                     <span>可见范围:公开</span>
                                 </c:otherwise>
                             </c:choose>
                         </span>
                    </a>
                </c:forEach>
            </div>
            <div  id="page" style="width: 890px;text-align: center">
                <ul class="pagination pagination-sm">

                    <c:choose>
                        <c:when test="${currentPage eq 1}">
                            <li class="disabled"><a>上一页</a></li>
                        </c:when>
                        <c:otherwise>
                            <li ><a href="javascript:void(0);" class="inbox-page-a" >上一页</a></li>
                        </c:otherwise>
                    </c:choose>


                    <c:forEach begin="1" end="${totalPage}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <li class="active  inbox-page-b"  id="${i}"><a href="javascript:void(0);">${i}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li  id="${i}"   class="inbox-page-b"  ><a href="javascript:void(0);" >${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>


                    <c:choose>
                        <c:when test="${currentPage eq totalPage}">
                            <li class="disabled"><a>下一页</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="javascript:void(0);" class="inbox-page-c">下一页</a></li>
                        </c:otherwise>
                    </c:choose>

                </ul>
            </div>
        </div>
</div>
<script>



    $("#addProject").click(function() {
        loadURL("/project/addProject.do", $('#inbox-content > .table-wrap'));
    });
    $(".inbox-page-a").click(function() {
        loadURL('/project/showList.do?page=${currentPage-1}', $('#inbox-content > .table-wrap'))
    });
    $(".inbox-page-b").click(function() {
        var  num  = $(this).attr("id");
        loadURL('/project/showList.do?page='+num, $('#inbox-content > .table-wrap'))
    });
    $(".inbox-page-c").click(function() {
        loadURL('/project/showList.do?page=${currentPage+1}', $('#inbox-content > .table-wrap'))
    });
    //Gets tooltips activated
    $("#inbox-table [rel=tooltip]").tooltip();

    $("#inbox-table input[type='checkbox']").change(function() {
        $(this).closest('tr').toggleClass("highlight", this.checked);
    });

    $(".inbox-table").click(function() {
        var  id  = $(this).parent().attr("id");
        loadURL('/project/queryProject.do?id='+id, $('#inbox-content > .table-wrap'));
    })
    function showProductRelaStatus(){
        loadURL('/project/showFinishRelaList.do', $('#s2'));
    }

    function projectTask(projectOid){
        loadURL("/project/findProjectByOid.do?projectId="+projectOid, $('#inbox-content > .table-wrap'));
    }

</script>
<style type="text/css">
    .jarvismetro-tile.bigger-cubes {
        width: 170px;
        height: 120px
    }
    #page{
        clear:both
    }
</style>