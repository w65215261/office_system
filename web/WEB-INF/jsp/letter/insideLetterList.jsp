<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div style="min-height: 300px">

<%--<table id="inbox-table" class="table table-striped table-hover">--%>

    <%--<tbody>--%>
    <%--<div class="inbox-checkbox-triggered">--%>

        <%--<div class="btn-group">--%>
            <%--<a href="javascript:void(0);" rel="tooltip" title="" data-placement="bottom" data-original-title="删除" class="deleteStatusButton btn btn-default">删除</a>--%>
            <%--<a href="javascript:void(0);" rel="tooltip" title="" data-placement="bottom" data-original-title="彻底删除" class="deleteButton btn btn-default">彻底删除</a>--%>
        <%--</div>--%>

    <%--</div>--%>


    <%--<tr  class="unread">--%>
        <%--<td class="inbox-table-icon">--%>
            <%--<div class="checkbox">--%>
                <%--<label>--%>
                    <%--&lt;%&ndash;<input type="checkbox" class="checkbox style-2">&ndash;%&gt;--%>
                    <%--<span></span> </label>--%>
            <%--</div>--%>
        <%--</td>--%>
        <%--<td class="inbox-data-from hidden-xs hidden-sm">--%>
            <%--<div>--%>
                <%--发件人--%>
            <%--</div>--%>
        <%--</td>--%>
        <%--<td class="inbox-data-data  hidden-xs">--%>
            <%--<div>--%>
              <%--<b>标题</b>--%>
            <%--</div>--%>
        <%--</td>--%>
        <%--<td class="inbox-data-date hidden-xs">--%>
            <%--<div>--%>
               <%--发送时间--%>
            <%--</div>--%>
        <%--</td>--%>
    <%--</tr>--%>
    <%--<c:forEach items="${letterList}" var="d">--%>
        <%--<c:if test="${d.showFlag=='1'}">--%>
            <%--<tr  class="unread"  id="${d.id}" >--%>

                <%--<td class="inbox-table-icon" style="border: solid red 1px">--%>
                    <%--<div class="checkbox">--%>
                        <%--<label>--%>
                            <%--<input type="checkbox" class="checkbox style-2">--%>
                            <%--<span></span> </label>--%>
                    <%--</div>--%>
                <%--</td>--%>

                <%--<td  style="border: solid red 1px">--%>
                    <%--<div>--%>
                          <%--<b>  ${d.messageFromName}</b>--%>
                    <%--</div>--%>
                <%--</td>--%>
                <%--<td class="inbox-table"style="border: solid red 1px">--%>
                    <%--<div>--%>
                            <%--<b>${d.messageTitle}</b>--%>
                    <%--</div>--%>
                <%--</td>--%>
                <%--<td class="inbox-table" style="border: solid red 1px">--%>
                    <%--<div>--%>
                       <%--<b> <fmt:formatDate value="${d.createDate}"  type="date" dateStyle="short" /></b>--%>
                    <%--</div>--%>
                <%--</td>--%>
            <%--</tr>--%>
        <%--</c:if>--%>
        <%--<c:if test="${d.showFlag=='0'}">--%>
            <%--<tr  class="unread"  id="${d.id}">--%>

                <%--<td class="inbox-table-icon">--%>
                    <%--<div class="checkbox">--%>
                        <%--<label>--%>
                            <%--<input type="checkbox" class="checkbox style-2">--%>
                            <%--<span></span> </label>--%>
                    <%--</div>--%>
                <%--</td>--%>

                <%--<td class="inbox-table">--%>
                    <%--<div>--%>
                            <%--${d.messageFromName}--%>
                    <%--</div>--%>
                <%--</td>--%>
                <%--<td class="inbox-table">--%>
                    <%--<div>--%>
                            <%--${d.messageTitle}--%>
                    <%--</div>--%>
                <%--</td>--%>
                <%--<td class="inbox-table">--%>
                    <%--<div>--%>
                        <%--<fmt:formatDate value="${d.createDate}"  type="date" dateStyle="short" />--%>
                    <%--</div>--%>
                <%--</td>--%>
            <%--</tr>--%>
        <%--</c:if>--%>



    <%--</c:forEach>--%>

    <%--</tbody>--%>
<%--</table>--%>
    <table id="inbox-table" class="table table-striped table-hover">
        <tbody>
        <div class="inbox-checkbox-triggered">

            <div class="btn-group">
                <a href="javascript:void(0);" rel="tooltip" title="" data-placement="bottom" data-original-title="删除" class="deleteStatusButton btn btn-default">删除</a>
                <a href="javascript:void(0);" rel="tooltip" title="" data-placement="bottom" data-original-title="彻底删除" class="deleteButton btn btn-default">彻底删除</a>
            </div>

        </div>
        <tr  class="unread">
            <td class="inbox-table-icon">
                <div class="checkbox">
                    <label>
                        <%--<input type="checkbox" class="checkbox style-2">--%>
                        <span></span> </label>
                </div>
            </td>
            <td class="inbox-data-from hidden-xs hidden-sm">
                <div>
                    发件人
                </div>
            </td>
            <td class="inbox-data-data  hidden-xs">
                <div>
                    <b>标题</b>
                </div>
            </td>
            <td class="inbox-data-date hidden-xs">
                <div>
                    发送时间
                </div>
            </td>
        </tr>
    <c:forEach items="${letterList}" var="d">
        <c:if test="${d.showFlag=='1'}">
            <tr  id="${d.id}" >
                <td class="inbox-table-icon">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" class="checkbox style-2">
                            <span></span> </label>
                    </div>
                </td>
                <td class="inbox-table">
                    <div>
                        <b>  ${d.messageFromName}</b>
                    </div>
                </td>
                <td class="inbox-table">
                    <div>
                        <b>${d.messageTitle}</b>
                    </div>
                </td>
                <td class="inbox-table">
                    <div>
                        <b> <fmt:formatDate value="${d.createDate}"  type="date" dateStyle="short" /></b>
                    </div>
                </td>
            </tr>
            </c:if>
        <c:if test="${d.showFlag=='0'}">
            <tr  id="${d.id}">
                <td class="inbox-table-icon">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" class="checkbox style-2">
                            <span></span> </label>
                    </div>
                </td>
                <td class="inbox-table" >
                    <div>
                          ${d.messageFromName}
                    </div>
                </td>
                <td class="inbox-table">
                    <div>
                        ${d.messageTitle}
                    </div>
                </td>
                <td class="inbox-table">
                    <div>
                         <fmt:formatDate value="${d.createDate}"  type="date" dateStyle="short" />
                    </div>
                </td>
            </tr>
        </c:if>
        </c:forEach>


        </tbody>
    </table>
</div>
<div class="text-center">
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
<script>

    $(".inbox-page-a").click(function() {
        loadURL('/message/queryInbox.do?flag=1&page=${currentPage-1}', $('#inbox-content > .table-wrap'))
    });
    $(".inbox-page-b").click(function() {
       var  num  = $(this).attr("id");
        loadURL('/message/queryInbox.do?flag=1&page='+num, $('#inbox-content > .table-wrap'))
    });
    $(".inbox-page-c").click(function() {
        loadURL('/message/queryInbox.do?flag=1&page=${currentPage+1}', $('#inbox-content > .table-wrap'))
    });
    //Gets tooltips activated
    $("#inbox-table [rel=tooltip]").tooltip();

    $("#inbox-table input[type='checkbox']").change(function() {
        $(this).closest('tr').toggleClass("highlight", this.checked);
    });

    $(".inbox-table").click(function() {
        var  id  = $(this).parent().attr("id");
        loadURL('/message/read.do?flag=1&id='+id, $('#inbox-content > .table-wrap'));
    })




    $('.inbox-table-icon input:checkbox').click(function() {
        enableDeleteButton();
    })

    $(".deleteStatusButton").click(function() {

        var stri='no';
        var arr = $('.highlight');

        for (var i = 0; i < arr.length; i++) {
            stri = stri + '_' + arr[i].id;

        }
        if (stri.length >2) {
            $.ajax({
                url: "/message/deleteStatus.do?flag=1",
                type: "get",
                data: {"stri": stri}

            });
            setTimeout(function(){
                $("#box-receive").click();
            },500);

        }
    });

        $(".deleteButton").click(function() {

            var stri='no' ;
            var   arr=$('.highlight');

            for (var i = 0; i < arr.length; i++) {
                stri= stri+'_'+arr[i].id;

            }
            if(stri.length>2){
                $.ajax({
                    url: "/message/delete.do?flag=1",
                    type: "get",
                    data: {"stri": stri}
                   });

                setTimeout(function(){
                    $("#box-receive").click();
                },500);

            }


            //   $('#inbox-table td input:checkbox:checked').parents("tr").rowslide();
        //$(".inbox-checkbox-triggered").removeClass('visible');
        //$("#compose-mail").show();
    });

    function enableDeleteButton() {
        var isChecked = $('.inbox-table-icon input:checkbox').is(':checked');

        if (isChecked) {
            $(".inbox-checkbox-triggered").addClass('visible');
            //$("#compose-mail").hide();
        } else {
            $(".inbox-checkbox-triggered").removeClass('visible');
            //$("#compose-mail").show();
        }
    }

</script>
