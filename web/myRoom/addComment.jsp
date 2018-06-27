<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
var numb=1;
var infoId="${dlroompostWithBLOBs.id}";
var cur_ryxxid = "${cur_ryxxid}";
function getContent(){
var content= $('#content').val();
var noticeid=$('#noticeid').val();
window.location.href="/myRoomManager/save.do?content="+encodeURI(encodeURI(content))+"&&noticeid="+noticeid;
}

function reset(){
	$('#content').val('');
}
</script>
<link href='/EasyUiCompoment/noticeModel/css/gginfo.css' rel='stylesheet' type='text/css' />
<link href='/EasyUiCompoment/noticeModel/css/easyui.css' rel='stylesheet' type='text/css' />
<script type="text/javascript" src="/EasyUiCompoment/noticeModel/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="/EasyUiCompoment/noticeModel/js/jquery.paginate.js"></script>
<script type="text/javascript" src="/EasyUiCompoment/noticeModel/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/EasyUiCompoment/noticeModel/js/Notice.js"></script>
<script type="text/javascript">
$(document).ready(function(){
$.ajax({
    type: "GET",
    url: "../myRoomManager/findPersonId.do",
    data : {id : cur_ryxxid},
    contentType: "application/json",
    dataType: "json",
    success: function (data) {
    	  $(".name_ss").text(data.usercname);
          $(".name_sl").text(data.department);
    },
    error: function (err) {
        alert("操作失败！原因：" + err);
    }
});
});

</script>
<style type="text/css">
   table {
        border-collapse: collapse;
      }
</style>
</head>
<body>
    <!-- 鼠标悬停个人信息窗口开始 -->
    <div id="div_mp" class="easyui-window" data-options="closed:true,noheader: true,border:false,collapsible:false,minimizable:false,maximizable:false,closable:false,draggable:false,resizable:false,modal:true,title:'名片'"
         style="width: 326px; height: 235px;">
    </div>
    <input type="hidden" id="hf_mp" />
    <!-- 鼠标悬停个人信息窗口结束 -->
<!--     <div id="Header" style="width: 900px; margin: 0 auto;"> -->
<!--         <div class="logo"> -->
<!--             <a id="avc" href="#"></a> -->
<!--         </div> -->
<!--         logo -->
<!--         headerright -->
<!--         <div class="clear"> -->
<!--         </div> -->
<!--     </div> -->
    <div class="gg_page">
<!--         <h2> -->
<!--             <span><a href="#" title="关闭公告" onclick="window.close();">关闭</a></span>通知公告 -->
<!--         </h2> -->
        <div class="gg_div">
            <div class="gg_title">
                <h1>
                   ${dlroompostWithBLOBs.title }<br />
                    <br />
                    <span>
                        <font style="font-weight: bold;">
                            类型：帖子
                        </font>&nbsp;&nbsp;|&nbsp;&nbsp;<font style="font-weight: bold;">
                            发布人：<font class="name_ss"></font>(<font class="name_sl"></font>)
                        </font><br />
                        ${dlroompostWithBLOBs.createtime}&nbsp;&nbsp;阅读(<font class="nums_yd"></font>)&nbsp;&nbsp;评论(<font class="nums_pl"></font>)
                    </span>
                </h1>
             
            </div>
            <div class="gg_content">
                ${dlroompostWithBLOBs.content }
            </div>
            <div class="gg_files">
              <!--   <font style="font-weight: bold;">
                    附件：
                </font> -->
             <!--    <ul>
                    <li>
                        <a class="fileDownload" href='#'>附件名称附件名称附件名称附件名称附件名称.doc</a>
                        &nbsp;&nbsp;(下载
                        <span class="count">
                            14
                        </span> 次)
                    </li>
                </ul> -->
            </div>
      
            <div class="gg_fangke">
                <h3>最近访客</h3>
                <ul>
                 <c:forEach items="${personReadInfoList}" var="personRead">  
                    <li>               
                    <c:if test="${personRead.usersex == 0}">
						<img class="imgTx" yhbh='${personRead.personid }' src='/EasyUiCompoment/noticeModel/img/pic000.jpg' style="cursor: pointer" /><br />
						</c:if>
                         <c:if test="${personRead.usersex == 1}">
                         <img class="imgTx" yhbh='${personRead.personid }' src='/EasyUiCompoment/noticeModel/img/pic0001.jpg' style="cursor: pointer" /><br />
                         </c:if>
                        <span style="color: #915833;">
                            	${personRead.name }
                        </span>
                        <br />
                        <span>${personRead.checktime }</span>
                    </li>
                 </c:forEach>
                   
                </ul>
            </div>
            <div class="gg_pinglun">
                <h3><span>评论(<font class="nums_pl"></font>)</span>&nbsp;</h3>
                <div class="div_pinglun_ul">
                </div>
                <div class="pages">
                    <div class="demo" id="demo1" style="width: 200px; height: 21px; overflow: hidden; float: right;">
                    </div>
                </div>
            </div>
            <div class="gg_pinglun_fabu">
                <h3>发布评论</h3>
                <div class="gg_pinglun_fabu_content">
                    <div class="gg_pinglun_fabu_content_left">
                       <c:if test="${personUsersex == 0}">
                     <img id="Img_current_Zp" src='/EasyUiCompoment/noticeModel/img/pic000.jpg' />
                    </c:if>
                     <c:if test="${personUsersex == 1}">
                      <img id="Img_current_Zp" src='/EasyUiCompoment/noticeModel/img/pic0001.jpg' />
                      </c:if>
                    </div>
                    <div class="gg_pinglun_fabu_content_right">
                        <textarea class="gg_pinglun_fabu_text" id="Textarea4" rows="3" cols=""></textarea>
                        <div>
                            <input id="Button5" class="btn_submit" type="button" value="发表"  style="cursor: pointer;" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>