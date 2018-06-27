<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" >
	<title>通知详情</title>
	<link rel="stylesheet" href="../EasyUiCompoment/mobile/css/themes/default/jquery.mobile-1.4.5.min.css">
    <link rel="stylesheet" href="../EasyUiCompoment/mobile/css/themes/default/googleapis_fonts.css">
	<script src="../EasyUiCompoment/mobile/js/jquery.js"></script>
	<script src="../EasyUiCompoment/mobile/js/jquery.mobile-1.4.5.min.js"></script>
	<script src="../EasyUiCompoment/mobile/js/jquery.mobile.toast.js"></script>
  
</head>
<body>
		<div data-role="page" id="page" > 
		    <div data-role="header">
		    	<c:if test="${flag==0 }">
			    	<a href=""  data-rel="back" data-icon="carat-l" data-iconpos="notext" ></a>
			    	<h1>通知详情</h1>
				</c:if>
		    </div>
		    <input type="hidden" id="createid" value="${result.createid }"/>
		    <input type="hidden" id="noticeid" value="${result.id }"/>
		    <input type="hidden" id="wechatid" value="${result.wechatid }"/>
		    <div data-role="content" id=content1 >
		        ${result.content }
		    </div>  
        <br/>
        <br/>
    </div>  
</body>
</html>
