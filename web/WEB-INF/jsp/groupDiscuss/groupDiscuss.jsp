<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!-- widget grid -->
<section id="widget-grid" class="">

    <!-- row -->
    <div class="row">

        <!-- NEW WIDGET START -->
        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

            <!-- Widget ID (each widget will need unique ID)-->
            <div class="jarviswidget" id="wid-id-10" data-widget-editbutton="false">
                <!-- widget options:
                usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

                data-widget-colorbutton="false"
                data-widget-editbutton="false"
                data-widget-togglebutton="false"
                data-widget-deletebutton="false"
                data-widget-fullscreenbutton="false"
                data-widget-custombutton="false"
                data-widget-collapsed="true"
                data-widget-sortable="false"

                -->
                <header>
                    <h2><a href="#/groupDiscuss/showNew.do"> 我的小组话题</a>> ${ groupInfo.groupName} </h2>
                    <div class="widget-toolbar">
                        <a href="#/groupDiscuss/toAddDiscuss.do?groupId=${groupInfo.id}"><span class="glyphicon glyphicon-plus"></span>发言</a>

                    </div>
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


                        <!-- row -->
                        <div class="row">

                            <div class="col-sm-12">

                                <div class="well">

                                    <table class="table table-striped table-forum">
                                        <thead>
                                        <tr>
                                            <th colspan="2"> 主题</th>
                                            <th class="hidden-xs hidden-sm" style="width: 200px;">发帖人</th>
                                            <th class="text-center hidden-xs hidden-sm" style="width: 100px;">回帖数</th>
                                            <th class="text-center hidden-xs hidden-sm" style="width: 100px;">浏览次数</th>
                                            <th class="hidden-xs hidden-sm" style="width: 200px;">最后回应</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${groupDiscuss}" var="d">


                                            <tr>
                                                <td colspan="2">
                                                    <a href="#/groupDiscuss/show.do?id=${d.id}">
                                                            ${d.discussTitle}
                                                    </a>
                                                </td>
                                                <td class="hidden-xs hidden-sm">
                                                    <a href="javascript:void(0);">${d.rptPersonName}</a>
                                                </td>
                                                <td class="text-center hidden-xs hidden-sm">
                                                    <a href="javascript:void(0);">${d.replyPersonNumber}</a>
                                                </td>
                                                <td class="text-center hidden-xs hidden-sm">
                                                    <a href="javascript:void(0);">${d.browseNumber}</a>
                                                </td>

                                                <td class="hidden-xs hidden-sm">by
                                                    <small><i><fmt:formatDate value="${d.rptDate}" type="both" /></i>    </small>
                                                </td>
                                            </tr>

                                        </c:forEach>
                                        </tbody>
                                    </table>

                                    <div class="text-center">
                                        <ul class="pagination pagination-sm">

                                            <c:choose>
                                                <c:when test="${currentPage eq 1}">
                                                    <li class="disabled"><a>上一页</a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li ><a href="#/groupDiscuss/toList.do?groupInfoOid=${groupInfo.id}&page=${currentPage-1}">上一页</a></li>
                                                </c:otherwise>
                                            </c:choose>


                                            <c:forEach begin="1" end="${totalPage}" var="i">
                                                <c:choose>
                                                    <c:when test="${currentPage eq i}">
                                                        <li class="active"><a href="javascript:void(0);">${i}</a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li><a href="#/groupDiscuss/toList.do?groupInfoOid=${groupInfo.id}&page=${i}">${i}</a></li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>


                                            <c:choose>
                                                <c:when test="${currentPage eq totalPage}">
                                                    <li class="disabled"><a>下一页</a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li><a href="#/groupDiscuss/toList.do?groupInfoOid=${groupInfo.id}&page=${currentPage+1}">下一页</a></li>
                                                </c:otherwise>
                                            </c:choose>

                                        </ul>
                                    </div>

                                </div>
                            </div>

                        </div>






                    </div>
                    <!-- end widget content -->

                </div>
                <!-- end widget div -->

            </div>
            <!-- end widget -->


            <!-- end widget -->


            <!-- end widget -->



        </article>
        <!-- WIDGET END -->

    </div>

    <!-- end row -->

    <!-- end row -->

</section>






<!-- end row -->

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
