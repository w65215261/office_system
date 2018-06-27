<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
<div class="custom-scroll table-responsive" style="max-width:100%; overflow-y: scroll;">
    <div>
        <table id="user" class="table table-bordered table-striped">
            <tbody>
            <tr></tr>
            <tr>
                <td style="min-width:20px"><span style="display: block;width: 40px">姓名</span></td>
                <td style="min-width:80px"><span style="display: block;width: 40px">部门</span> </td>
                <td style="min-width:80px;"><span style="display: block;width: 40px">职位</span> </td>

                <c:forEach items="${lists}" var="p">
                        <td style="min-width: 100px">${p}</td>
            </c:forEach>
            </tr>
            </tbody>
            <c:forEach items="${personList}" var="person">
                    <tr>
                        <td style="min-width: 100px">${person.userCname}</td>
                        <td>${orgName}</td>
                        <td>${person.duty}</td>
                        <c:forEach items="${person.list}" var="s">
                            <td>${s}</td>
                        </c:forEach>
                    </tr>
            </c:forEach>
        </table>
    </div>

</div>
<div class="widget-body" style="height: auto ">
    <div class="tab-pane fade in active" id="s2">
        <div  id="page" style="text-align: center;float: left;width: 85%">

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
            <%--<div style="float: right; padding: 25px;width: 15%">共<strong>${totalRecord}</strong>条</div>--%>
        </div>
    </div>
</div>
<script>
    var startTime=$("#startTime").val();
    var endTime=$("#endTime").val();
    var personId=$("#personId").val();
    var personName=$("#personName").val();
    var orgId=$("#orgId").val();
    var orgName=$("#orgName").val();
    console.log(personName);

    $(".inbox-page-a").click(function() {
        if(startTime==""){
            swal("","请选择开始日期!","warning");
            return;
        }
        loadURL("/signInfo/queryByOrg.do?startTime="+startTime+"&endTime="+endTime+"&orgId="+orgId+"&orgName="+orgName+"&page="+${currentPage-1}, $('#s1'));
    });
    $(".inbox-page-b").click(function() {
        if(startTime==""){
            swal("","请选择开始日期!","warning");
            return;
        }
        var  num  = $(this).attr("id");
        loadURL("/signInfo/queryByOrg.do?startTime="+startTime+"&endTime="+endTime+"&orgId="+orgId+"&orgName="+orgName+"&page="+num, $('#s1'));
    });
    $(".inbox-page-c").click(function() {
        if(startTime==""){
            swal("","请选择开始日期!","warning");
            return;
        }
        loadURL("/signInfo/queryByOrg.do?startTime="+startTime+"&endTime="+endTime+"&orgId="+orgId+"&orgName="+orgName+"&page="+${currentPage+1}, $('#s1'));
    });
    //Gets tooltips activated
    $("#inbox-table [rel=tooltip]").tooltip();

    $("#inbox-table input[type='checkbox']").change(function() {
        $(this).closest('tr').toggleClass("highlight", this.checked);
    });
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