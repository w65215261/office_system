<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Bread crumb is created dynamically -->
<!-- row -->
<section id="widget-grid" class="">
    <article class="col-sm-12 col-md-12 col-lg-12">
        <div class="jarviswidget " id="wid-id-0" data-widget-editbutton="false">
                <header>
                     <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                     <h2 id="group">我的小组话题</h2>
                    <div class="widget-toolbar">
                    </div>
                </header>
            <div>

                <!-- widget edit box -->
                <div class="jarviswidget-editbox">
                    <!-- This area used as dropdown edit box -->
                </div>
                <!-- end widget edit box -->

                <!-- widget content -->
                <div class="widget-body no-padding">
                    <table class="table table-striped table-forum">
                        <thead>
                        <tr>
                            <th colspan="2"> 主题</th>
                            <th class="text-center hidden-xs hidden-sm" style="width: 100px;">回帖数</th>
                            <th class="text-center hidden-xs hidden-sm" style="width: 100px;">讨论组</th>
                            <th class="hidden-xs hidden-sm" style="width: 200px;">发帖人</th>
                            <th class="hidden-xs hidden-sm" style="width: 200px;">发帖时间</th>
                        </tr>
                        </thead>

                            <tbody>
                                <%--<td>${d.discussTitle}</td>--%>
                                <%--<td>${d.rptPerson}</td>--%>
                                <%--<td>${d.rptDate}</td>--%>
                                <%--<td>${d.discussTitle}</td>--%>
                                <c:forEach items="${discussList}" var="d">
                            <tr>
                                <td colspan="2">
                                    <a href="#/groupDiscuss/show.do?id=${d.id}">
                                            ${d.discussTitle}
                                    </a>


                                </td>
                                <td class="text-center hidden-xs hidden-sm">
                                    <a href="javascript:void(0);">${d.replyPersonNumber}</a>
                                </td>
                                <td class="text-center hidden-xs hidden-sm">
                                    <a href="#/groupDiscuss/toList.do?groupInfoOid=${d.groupInfoOid}">${d.groupName}</a>
                                </td>
                                <td class="hidden-xs hidden-sm">
                                    <a href="javascript:void(0);">${d.rptPerson}</a>

                                </td>

                                <td class="hidden-xs hidden-sm">
                                      <a href="javascript:void(0);">  <fmt:formatDate value="${d.rptDate}" type="both" /></a>                              </td>

                            </tr>
                                </c:forEach>
                            </tbody>


                    </table>

                   </div>
            </div>
            <div class="row">
                        <div class="text-center">

                            <ul class="pagination pagination-sm">

                                <c:choose>
                                    <c:when test="${currentPage eq 1}">
                                        <li class="disabled"><a>Prev</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li ><a href="#/groupDiscuss/showNew.do?page=${currentPage-1}">Prev</a></li>
                                    </c:otherwise>
                                </c:choose>


                                <c:forEach begin="1" end="${totalPage}" var="i">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <li class="active"><a href="javascript:void(0);">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a href="#/groupDiscuss/showNew.do?page=${i}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>


                                <c:choose>
                                    <c:when test="${currentPage eq totalPage}">
                                        <li class="disabled"><a>Next</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="#/groupDiscuss/showNew.do?page=${currentPage+1}">Next</a></li>
                                    </c:otherwise>
                                </c:choose>

                            </ul>


                        </div>


            </div>
     </div>
     </article>
 </section>

<!-- end row -->

<!-- row -->



<script type="text/javascript">
    /* DO NOT REMOVE : GLOBAL FUNCTIONS!
     *
     * pageSetUp(); WILL CALL THE FOLLOWING FUNCTIONS
     *
     * // activate tooltips
     * $("[rel=tooltip]").tooltip();
     *
     * // activate popovers
     * $("[rel=popover]").popover();
     *
     * // activate popovers with hover states
     * $("[rel=popover-hover]").popover({ trigger: "hover" });
     *
     * // activate inline charts
     * runAllCharts();
     *
     * // setup widgets
     * setup_widgets_desktop();
     *
     * // run form elements
     * runAllForms();
     *
     ********************************
     *
     * pageSetUp() is needed whenever you load a page.
     * It initializes and checks for all basic elements of the page
     * and makes rendering easier.
     *
     */

    pageSetUp();

    /*
     * ALL PAGE RELATED SCRIPTS CAN GO BELOW HERE
     * eg alert("my home function");
     */

    // pagefunction

    var pagefunction = function() {

    };

    // end pagefunction

    // run pagefunction on load

    pagefunction();

</script>
