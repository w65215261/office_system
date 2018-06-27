<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<article class="">
  <div class="row">

    <div class="col-sm-12">

      <div class="well">



        <!-- Post
        <tr>
          <td class="text-center">
            <a href="#">
              <strong>我</strong></a>
          </td>
          <td><em>今天</em></td>
        </tr>-->
          <tr>

            <td colspan="2">

              <div id="forumPost"  ></div>
              <p class="text-right"><button onclick='postTopic0("${projectOid}")' class="btn btn-primary margin-top-10">发送</button></p>

            </td>
          </tr>

        <!-- end Post -->
        <table class="table table-striped table-forum">
          <%--<c:if test="${currentPage eq 1}">--%>
          <thead>
          <th colspan="3"></th>
          </thead>


          <c:forEach items="${dynamicsList}" var="d">

          <tbody>

          <!-- Post     -->
          <tr>
            <td class="text-center">
              <a href="#"><strong>${d.rptPerson}</strong></a>
            </td>
            <td>
              <em><fmt:formatDate value="${d.rptTime}" type="both" /></em>
            </td>

            <td align="right">

              <a href="javascript:void(0);" rel="tooltip" title="" id="${d.id}" class="deleteButton" onclick="deleteContent('${d.projectOid}')">删除</a>
            </td>
          </tr>
          <tr>
            <td class="text-center" style="width: 12%;">
              <div class="push-bit">
                <a href="#">
                  <img src="/smartAdmin/img/avatars/sunny.png" width="50"  class="online">
                </a>
              </div>
            </td>
            <td colspan="2">
              <p>${d.content}
              </p>
            </td>
          </tr>
          </c:forEach>
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
                <li ><a href="JavaScript:pageTurning('${projectOid}','${currentPage-1}')">Prev</a></li>
              </c:otherwise>
            </c:choose>


            <c:forEach begin="1" end="${totalPage}" var="i">
              <c:choose>
                <c:when test="${currentPage eq i}">
                  <li class="active"><a href="javascript:void(0);">${i}</a></li>
                </c:when>
                <c:otherwise>
                  <li><a href="JavaScript:pageTurning('${projectOid}','${i}')">${i}</a></li>
                </c:otherwise>
              </c:choose>
            </c:forEach>


            <c:choose>
              <c:when test="${currentPage eq totalPage}">
                <li class="disabled"><a>Next</a></li>
              </c:when>
              <c:otherwise>
                <li><a href="JavaScript:pageTurning('${projectOid}','${currentPage+1}')">Next</a></li>
              </c:otherwise>
            </c:choose>
          </ul>
        </div>
      </div>
    </div>
  </div>
</article>
<script type="text/javascript">


  pageSetUp();

  function pageTurning(id,page){
    loadURL("/projectDynamics/show.do?projectOid="+id+"&page="+page,$('#s1'));
  }

  var pagefunction = function() {
    $('#personNumber').text('人员（'+'${personNumber}'+'）');
    $('#fileNumber').text('文件（'+'${fileNumber}'+'）');
    $('#forumPost').summernote({
      height : 180,
      focus : false,
      tabsize : 2
    });

  };


  function deleteContent(projectOid){
    var id = $(".deleteButton").attr("id");
    $.ajax({
      type: "POST",
      url: '/projectDynamics/delete.do',
      data:{id:id},
      success: function (data) {
        if (data == "success") {
          loadURL("/projectDynamics/show.do?projectOid="+projectOid,$('#s1'))
        }
      },
      error: function () {

      }
    });
  }


  function postTopic0(projectOid){
    var content=$('#forumPost').code();
    $.ajax({
      type: "POST",
      url: '/projectDynamics/save.do',
      data:{content:content,projectOid:projectOid},
      success: function (data) {
        if (data == "success") {
          loadURL("/projectDynamics/show.do?projectOid="+projectOid,$('#s1'))
        } else {
          alert('保存失败!');
        }
      },
      error: function () {

      }
    });
  }

  // end pagefunction

  // load summernote editor
  loadScript("/smartAdmin/js/plugin/summernote/summernote.min.js", pagefunction);

</script>
