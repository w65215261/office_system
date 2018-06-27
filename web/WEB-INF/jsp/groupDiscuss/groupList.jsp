<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Bread crumb is created dynamically -->
<!-- row -->

<!-- end row -->

<!--
The ID "widget-grid" will start to initialize all widgets below
You do not need to use widgets if you dont want to. Simply remove
the <section></section> and you can use wells or panels instead
-->
<section id="widget-grid" class="">
    <article class="col-sm-12 col-md-12 col-lg-12">
        <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
            <header>
                <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                <h2 id="group" ><a href="#/groupDiscuss/showNew.do"> 我的小组话题</a> > <a href="#/groupDiscuss/toList.do?groupInfoOid=${ groupInfo.id} ">${ groupInfo.groupName} </a> >${gd.discussTitle}</h2>
                <div class="widget-toolbar">
                </div>
            </header>
            <div>

                <!-- widget edit box -->
                <div class="jarviswidget-editbox">
                    <!-- This area used as dropdown edit box -->
                </div>
                <div class="widget-body no-padding">
                    <div class="row">

                        <div class="col-sm-12">

                            <div class="well">

                         <table class="table table-striped table-forum">


                             <c:if test="${currentPage eq 1}">
                                 <thead>
                                 <tr>
                                     <th colspan="2">
                                     </th>
                                 </tr>
                                 </thead>
                                 <tbody>
                                 <!-- Post     -->
                                 <tr>
                                     <td class="text-center">
                                         <a href="#"><strong>${gd.rptPerson}</strong></a>
                                     </td>
                                     <td>on <em><fmt:formatDate value="${gd.rptDate}" type="both" /></em></td>
                                 </tr>
                                 <tr>
                                     <td class="text-center" style="width: 12%;">
                                         <div class="push-bit">
                                             <a href="#">
                                                 <img src="/smartAdmin/img/avatars/sunny.png" width="50"  class="online">
                                             </a>
                                         </div>
                                         <c:if test="${gd.replyPersonNumber !=null }">
                                             <small>${gd.replyPersonNumber}回帖数</small>
                                         </c:if>
                                     </td>
                                     <td>
                                         <p>${gd.discussion}
                                         </p>
                                     </td>
                                 </tr>
                             </c:if>


                        <c:forEach items="${discussList}" var="d">
                        <thead>
                        <tr>
                            <th colspan="2">

                            </th>
                        </tr>
                        </thead>
                        <tbody>

                        <!-- Post     -->
                        <tr>
                            <td class="text-center">
                                <a href="#"><strong>${d.peplyName}</strong></a>
                            </td>
                            <td>on <em><fmt:formatDate value="${d.replyDate}" type="both" /></em></td>
                        </tr>
                        <tr>
                            <td class="text-center" style="width: 12%;">
                                <div class="push-bit">
                                    <a href="#">
                                        <img src="/smartAdmin/img/avatars/sunny.png" width="50"  class="online">
                                    </a>
                                </div>
                            </td>
                            <td>
                                <p>${d.reply}
                                </p>
                            </td>
                        </tr>
                        </c:forEach>
                <!-- end Post -->


                <!-- Post -->
                <tr>
                    <td class="text-center">
                        <a href="#">
                            <strong>我</strong></a>
                    </td>
                    <td><em>今天</em></td>
                </tr>
                <tr>
                    <td class="text-center" style="width: 12%;">
                        <div class="push-bit">
                            <a href="#">
                                <img src="/smartAdmin/img/avatars/sunny.png" width="50"  class="online">
                            </a>
                        </div>
                        <small></small>
                    </td>
                    <td>

                        <div id="forumPost"></div>
                        <button onclick='postTopic("${gd.id}")' class="btn btn-primary margin-top-10">发送</button>

                    </td>
                </tr>
                <!-- end Post -->

                </tbody>
            </table>
                    <div class="text-center">
                        <ul class="pagination pagination-sm">
                        <c:choose>
                            <c:when test="${currentPage eq 1}">
                                <li class="disabled"><a>Prev</a></li>
                            </c:when>
                            <c:otherwise>
                                <li ><a href="#/groupDiscuss/show.do?id=${gd.id}&page=${currentPage-1}">Prev</a></li>
                            </c:otherwise>
                        </c:choose>


                        <c:forEach begin="1" end="${totalPage}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <li class="active"><a href="javascript:void(0);">${i}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="#/groupDiscuss/show.do?id=${gd.id}&page=${i}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>


                        <c:choose>
                            <c:when test="${currentPage eq totalPage}">
                                <li class="disabled"><a>Next</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="#/groupDiscuss/show.do?id=${gd.id}&page=${currentPage+1}">Next</a></li>
                            </c:otherwise>
                        </c:choose>
                </ul>
            </div>
                </div>
              </div>
                    </div>
                    </div>
             </div>
        </div>
        </article>
    </section>

<script type="text/javascript">


    pageSetUp();


    var pagefunction = function() {

        $('#forumPost').summernote({
            height : 180,
            focus : false,
            tabsize : 2
        });

    };




    function postTopic(discussId){
        var shtml=$('#forumPost').code();
        $.ajax({
            type: "POST",
            url: '/groupDiscuss/postReply.do',
            data:{reply:shtml,groupDiscussOid:discussId},
            success: function (data) {
               loadURL("/groupDiscuss/show.do?id="+discussId,$('#content'))

            },
            error: function () {

            }
        });
    }

    // end pagefunction

    // load summernote editor
    loadScript("/smartAdmin/js/plugin/summernote/summernote.min.js", pagefunction);

</script>
