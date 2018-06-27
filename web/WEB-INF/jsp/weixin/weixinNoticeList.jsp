<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>微信公告列表</title>
	<!--<link rel="shortcut icon" href="favicon.ico">--> 
	<link rel="stylesheet" href="../EasyUiCompoment/mobile/css/themes/default/jquery.mobile-1.4.5.min.css">
	<!--<link rel="stylesheet" href="_assets/css/jqm-demos.css">--> 
	
    <link rel="stylesheet" href="../EasyUiCompoment/mobile/css/themes/default/googleapis_fonts.css">
	<script src="../EasyUiCompoment/mobile/js/jquery.js"></script>
	 <!--<script src="_assets/js/index.js"></script>--> 
	<script src="../EasyUiCompoment/mobile/js/jquery.mobile-1.4.5.min.js"></script>
	<script src="../EasyUiCompoment/mobile/js/jquery.mobile.toast.js"></script>
	<script>  
	 	    var pageCount=1;  //页面计数器
	 	    var maxPageNum;   //最大页面数据
	 	        
	 	        
	 	    $(document).on("pageinit","#pageone",function(){   //id=pageone 的页面初始化完成后执行函数
	             
	 	    	pageCount = $("#page").val();
	 	    	maxPageNum = $("#total").val();
         //   $(function(){  
            	  	
            	
                $('#id1').click(function(){ 
                    if(pageCount == 1){
                   	 showMessage("已经是第一页啦");
                   	 return ;
                   	} 
                
                	 $("#content1").empty();
                	var wechatId = $("#wechatId").val();
                	var num = parseInt(pageCount)-1;
                	$.ajax({
    					type:"GET",
    					url:'queryNoticeForajax.do?wechatId='+wechatId+'&page='+num,
    					success:function(data){
    						$(data).each(function(i,val) {
    							if(val.checkflag==0){
    								$("#content1").append("<li data-icon='coding'><a href='noticeInfo.do?wechatId="+wechatId+"&noticeid="+val.id+"'>"+val.title+"</a></li>");
    							}else{
    								$("#content1").append("<li data-icon='eye'><a href='noticeInfo.do?wechatId="+wechatId+"&noticeid="+val.id+"'>"+val.title+"</a></li>");
    							}
    							
    						});
    						pageCount=num;
    						 $('#content1').listview('refresh');  
    	       	        },
    	       	           //调用出错执行的函数
    	       	        error: function(){
    	       	        	
    	       	        }         
    	       	    });
                    
             
                    
                      
                    /*  pageCount = pageCount - 1;
                      $.ajax({ 
					            type : "POST", 
					            url  : "form.php",  
					            cache : false, 
					            dataType:"json",
					            data : {"pagenum":pagaCount},
					            success : onSuccess, 
					            error : onError 
					            });  */
                     
                });  
                 $('#id2').click(function(){  
                	 if(parseInt(pageCount) == parseInt(maxPageNum)){
                       	 showMessage("已经是最后一页啦");
                       	 return ;
                       	} 
                    
                    	 $("#content1").empty();
                    	var wechatId = $("#wechatId").val();
                    	var num = parseInt(pageCount)+1;
                    	$.ajax({
        					type:"GET",
        					url:'queryNoticeForajax.do?wechatId='+wechatId+'&page='+num,
        					success:function(data){
        						$(data).each(function(i,val) { 
        							if(val.checkflag==0){
        								$("#content1").append("<li data-icon='coding'><a href='noticeInfo.do?wechatId="+wechatId+"&noticeid="+val.id+"'>"+val.title+"</a></li>");
        							}else{
        								$("#content1").append("<li data-icon='eye'><a href='noticeInfo.do?wechatId="+wechatId+"&noticeid="+val.id+"'>"+val.title+"</a></li>");
        							}
        							
        						});
        						pageCount=num;
        						 $('#content1').listview('refresh');  
        	       	        },
        	       	           //调用出错执行的函数
        	       	        error: function(){
        	       	        	
        	       	        }         
        	       	    });
                }); 
                 $('#more').click(function(){  
                        $.mobile.toast({
                               message: 'toast测试信息',
                               classOnOpen: 'pomegranate'
               
                              });
                  
                }); 
                function showMessage(message1){         //toast信息显示窗口
                	    $.mobile.toast({
                               message: message1,
                               classOnOpen: 'pomegranate'
                      });
                	}
                function onSuccess(data,status){   //调用成功，刷新通知列表
				   
				 } 
				  
                function onError(XMLHttpRequest, textStatus, errorThrown){  //进行错误处理 
								    
			    } 	
                	
                //$('#id1').click();
                
                
            });  
            
        /*function ReflashEMR(pagenum) {
  						//数据。其中ResponseShortAdvise为Control里面的函数；time为传入的参数；data为返回的JSON数据
  						$.post("../YdylAjax/ResponseShortAdvise", { "pagenum": pagenum }, function (data, status) {
  						  var  listUrl = $.parseJSON(data);
  						 if (status == "success") {
  						  var objListView = document.getElementById("emrListView");
  						  objListView.innerHTML = "";    //清空ListView原本的内容
  						  //如果服务器返回记录为空
  						  if (listUrl.length == 0) {
  						      $("#EmrGallery").append('<h4>提示：当前日期没有数据</h4>');
  						      return;
  						  }
  						  var tempnum = 1;
  						  for (var i = listUrl.length - 1; i >= 0; i--) {
  						      $("#emrListView").append(' <li><a href="#"  data-transition="fade"><img src="http://www.cnblogs.com/Content/imges/DutyRoster.png" class="ui-li-icon"> <h3 >病历 ' + tempnum + ' </h3><p >  医生：' + listUrl[i].Doc + ' </p><p> ' + listUrl[i].Dep  + listUrl[i].Time + ' </p></a></li>');        //将ListView中新的li
  						      tempnum++;
  						  }
  						
  						"#emrListView").listview("refresh");  //这里是重新渲染JqueryMobile中ListView的样式
  				}
  			}
  		)};	 */
 	
     </script>  
   	<style type="text/css">
  	.ui-icon-coding:after {
    background-image: url("../EasyUiCompoment/mobile/eye-red.png");
/*     background-color: rgba(0, 0, 0, 0); */
/*     background-size: 18px 18px;  */
}
  </style> 
</head>
<body>
		<div data-role="page" id="pageone" >  
		    <div data-role="header"   data-position="fixed"  data-tap-toggle = "false">  
		        <h1>通知</h1>  
		    </div>
		   <input type="hidden"  name="wechatId" id="wechatId" value="${wechatId}"/>
		   <input type="hidden"  name="page" id="page" value="${page}"/>
		   <input type="hidden"  name="total" id="total" value="${total}"/>
		    <div data-role="content">   
		        <ul id="content1" data-role="listview" >  
		        <c:forEach var="notice" items="${noticeList}">
		        	<c:if test="${notice.checkflag==0 }">
		        		<li data-icon="coding"><a href="noticeInfo.do?openid=${wechatId}&noticeid=${notice.id }">${notice.title }</a></li> 
		        	</c:if>
        		 	<c:if test="${notice.checkflag==1 }">
		        		<li data-icon="eye"><a href="noticeInfo.do?openid=${wechatId}&noticeid=${notice.id }">${notice.title }</a></li> 
		        	</c:if>
        		</c:forEach>
		        </ul>       
		    </div>  
		    <div data-role="footer"    data-position="fixed"  data-tap-toggle = "false">  
		    	   <!--    <button id="id1" class="ui_btn">上一页</button>  
		    	      
                <button id="id2" class="ui_btn">下一页</button>  
                 <a href="#" data-role="button" id="more" >更多...</a>  -->
                
               <div data-role="navbar" >
						      <ul>
						        <!-- <li><a href="#" class="ui-btn-active" id="id1">上一页</a></li> -->
						        <li><a href="#"  data-role="button" class="ui-btn-active" id="id1">上一页</a></li>
						        <li><a href="#" data-role="button" id="id2"  >下一页</a></li>
				
						      </ul>
						   </div> 
						   
            <!--   <div data-role="navbar">
						      <ul>
						         <li><button id="id1" class="ui_btn">上一页</button></li>
						         <li><button id="id2" class="ui_btn">下一页</button></li>
						      </ul>
						   </div>
						   -->
		       
		    </div>  
		</div>  

</body>
</html>

