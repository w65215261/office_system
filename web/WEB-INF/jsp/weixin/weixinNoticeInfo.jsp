<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" >
	<title>通知详情</title>
	<!--<link rel="shortcut icon" href="favicon.ico">--> 
	<link rel="stylesheet" href="../EasyUiCompoment/mobile/css/themes/default/jquery.mobile-1.4.5.min.css">
	<!--<link rel="stylesheet" href="_assets/css/jqm-demos.css">--> 
    <link rel="stylesheet" href="../EasyUiCompoment/mobile/css/themes/default/googleapis_fonts.css">
  
	<script src="../EasyUiCompoment/mobile/js/jquery.js"></script>
	 <!--<script src="_assets/js/index.js"></script>--> 
	<script src="../EasyUiCompoment/mobile/js/jquery.mobile-1.4.5.min.js"></script>
	<script src="../EasyUiCompoment/mobile/js/jquery.mobile.toast.js"></script>
	
   	<style type="text/css">
  	img{
  		max-width:20%;
  	}
  	span{
  		
  	}
  </style> 
  
</head>
<body>
		<div data-role="page" id="page" > 
	 <script>
	    
	 $(function(){
		var noticeId = $("#noticeid").val();
		 $.ajax({
			 url : '../mobileManager/noticeFile.do?noticeId='+noticeId,
			 success  : function(data) {
				 if(data.state == "0") {
					 $("#aAttach").text(data.result.filename);
					 $("#aAttach").attr('href', data.result.filepath);
				 } else if(data.state == "1") {
					 $("#aAttach").text("暂无！");
					 $("#aAttach").attr('href', "#");
				 }
			 },
			 error : function() {
				 showMessage("服务器异常！");
			 }
		 });
		 
		var pageCount=1;  //页面计数器
	    var total;   //最大页面数据
	     $("#moreComment").click(function(){
// 	    	 if(pageCount != 1) {
// 		    	 if(total < pageCount) {
// 		    		 showMessage("没有更多了");
// 		    	 }
// 	    	 }
	    	 
			 $.ajax({
				 url : '../mobileManager/showCommontList.do?noticeid=' + noticeId + '&page=' + pageCount++,
				dataType : 'json',
				success : function(data){
					total = data.total;
					if(total == 0) {
						$("#ulComment").append("暂无评论！");
					}
					for(i = 0; i < data.list.length; i++) {
						var result = data.list[i];
						if(result.user_sex == 0) {
							$("#ulComment").append(
									"<li><p style='color:#999'><img src='/assets/img/man.png'>" + result.user_cname + (result.toObserver===undefined?"":("回复"+result.toObserver)) + "：" + (result.org_cname===undefined?"":result.org_cname) + " " + result.createtime + "</p>" + 
									"<span style='white-space:pre-wrap;'>" + result.content + "</span></li>");
						} else if (result.user_sex == 1) {
							$("#ulComment").append(
									"<li><p style='color:#999'><img src='/assets/img/woman.png'>" + result.user_cname + (result.toObserver===undefined?"":("回复"+result.toObserver)) + "：" + (result.org_cname===undefined?"":result.org_cname) + " " + result.createtime + "</p>" + 
									"<span style='white-space:pre-wrap;'>" + result.content + "</span></li>");
						}
					}
					$('#ulComment').listview('refresh');
				}
			 });
	     });
	     $("#moreComment").click();
// 	     $('#moreComment').trigger('click');
	 });
	
  	function sendCommont(){
  		var content = $("#inputComment").val().trim();
  		if(content==""){
  			showMessage("评论不能为空");
  			return;
  		}
  		var createId = $("#createid").val();
  		var noticeId = $("#noticeid").val();
  		var wechatId = $("#wechatid").val();
  		$.ajax({
  			type : 'get',
  			url : '../mobileManager/insertCommont.do?content='+content+'&noticeid='+noticeId+'&createid='+createId+'&wechatId='+wechatId,
  			success : function(data){
				if(data.user_sex == 0) {
					$("#ulComment").prepend(
							"<li><p style='color:#999'><img src='/assets/img/man.png'>" + data.user_cname + (data.toObserver===undefined?"":("回复"+data.toObserver)) + "：" + (data.org_cname===undefined?"":data.org_cname) + " " + data.createtime + "</p>" + 
							"<span style='white-space:pre-wrap;'>" + data.content + "</span></li>");
				} else if (data.user_sex == 1) {
					$("#ulComment").prepend(
							"<li><p style='color:#999'><img src='/assets/img/woman.png'>" + data.user_cname + (data.toObserver===undefined?"":("回复"+data.toObserver)) + "：" + (data.org_cname===undefined?"":data.org_cname) + " " + data.createtime + "</p>" + 
							"<span style='white-space:pre-wrap;'>" + data.content + "</span></li>");
				}
			 $("#inputComment").val("");
			 $('#ulComment').listview('refresh');
  			},
  			error : function() {
  				showMessage("服务器异常，请重试");
  				return;
  			}
  		});
  		
  	}
  	 function showMessage(message1){         //toast信息显示窗口
  	    $.mobile.toast({
                 message: message1,
                 classOnOpen: 'pomegranate'
        });
  	 }
  	 
  </script>
		    <div data-role="header">
		    	<c:if test="${flag==0 }">
			    	<a href=""  data-rel="back" data-icon="carat-l" data-iconpos="notext" ></a>
			    	<h1>通知详情</h1>
				</c:if>
		    </div>
		    
		    <input type="hidden" id="createid" value="${result.createid }"/>
		    <input type="hidden" id="noticeid" value="${result.id }"/>
		    <input type="hidden" id="wechatid" value="${result.wechatid }"/>
		    <div align="center">
              		<h3>${result.title }</h3>
                    <span style='color:#999;font-size:11px;'>
                       <font>
                            发布人:<font>${result.USER_CNAME }</font>(<font>${result.ORG_CNAME }</font>)
                        </font>|
                        <font>
                               发布时间:<font>${result.createtime }</font></font>
                    </span>
            </div>
		    <div data-role="content" id=content1 >
		        ${result.content }
		    </div>  
		    
		     <!-- 以下为附件列表 -->
         <div id="divAttach">
     	       <span>附件：</span>
		         <ul id="ulAttach"  data-role="listview" >
	            	 <li data-icon="false"><a id="aAttach"  data-ajax="false"></a></li> 
			     </ul>   
        </div>
        <br/>
        <br/>
         <!-- 以下为评论列表 -->
        <div id="divComment">
	         <span>评论：</span>
	         <ul id="ulComment" data-role="listview" ></ul>    
        </div>
		<div >
        	<ul data-role="listview">
           		<li data-icon="false" style="webkit-padding-start: 111px; text-align:center"><a id="moreComment"  data-ajax="false" style="text-align:center">更多...</a></li> 
	      	</ul>   
  		</div>
         <!-- 以下为评论输入发送 -->
        <div data-role="footer"    data-position="fixed"  data-tap-toggle = "false">  
		      	<table style="width:100%;">
			        <tr>
			          <td style=" padding:0 5px;">
			            <input type="text" value="" id="inputComment" name="inputComment" placeholder="评论..">
			          </td>
			          <td style="width:55px; padding-right:5px;">
			            <button id="btnSend" onclick="sendCommont()" >发送</button>
			          </td>
			        </tr>
            </table>
		     </div>  
    </div>  
</body>
</html>
