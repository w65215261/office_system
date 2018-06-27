<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1" >
<title>人员管理</title>
<link rel="stylesheet" href="../EasyUiCompoment/mobile/css/themes/default/jquery.mobile-1.4.5.min.css">
<link rel="stylesheet" href="../EasyUiCompoment/mobile/css/themes/default/googleapis_fonts.css">
<script src="../EasyUiCompoment/mobile/js/jquery.js"></script>
<script src="../EasyUiCompoment/mobile/js/jquery.mobile-1.4.5.min.js"></script>
<script src="../EasyUiCompoment/mobile/js/jquery.mobile.toast.js"></script>

</head>
<body class="easyui-layout">
	<div data-role="page" id="page" >
	<script type="text/javascript">
    function formSubmit1(obj){
    	var username = $('#username').val().trim();
    	var email = $('#email').val().trim();
    	if(username == '' || email == '') {
    		showMessage1("请将内容填写完整！");
    		return false;
    	}
    	var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/g;
    	if(!reg.test(email)) {
    		showMessage1("邮箱格式错误！");
    		return false;
    	}
    	if(username!='' && email!=''){
    			$.ajax({
					type:"POST",
					url:'/mobileManager/sendMail.do',
					data:{'username':username, 'email':email},
					success:function(data){
						//showMessage1(data.content);
						if(data.state == "0"){
							showMessage1(data.content);
// 							window.location.href = "";
							$("#backA").click();
						} else if (data.state == "1") {
							showMessage1(data.content);
	       	        	}else{
	       	        		showMessage1('操作失败！');
	       	        	}
	       	        },
	       	           //调用出错执行的函数
	       	        error: function(){
	       	        }         
	       	    });
		}else{
			showMessage1("请将内容填写完整！");
		}
    }    
    function showMessage1(message1){         //toast信息显示窗口
 	    $.mobile.toast({
                message: message1,
                classOnOpen: 'pomegranate'
       });
 	}
    </script>
        <div data-role="header" >
            <!--data-add-back-btn="true" data-back-btn-text=""-->
            <a href="" data-rel="back" data-icon="carat-l" data-iconpos="notext" id="backA"></a>
            <h1>找回密码</h1>
        </div>
        <div data-role="content" id="content1">
	         <form >
	           <div class="ui-field-contain">
			      <label for="account">账号：</label>
			      <input type="text" name="username" id="username" value="${username }">
			      <br/>
			      <label for="email">邮箱：</label>
			      <input type="text" name="email" id="email" >
			      <br/>
			      <input type="button" id="reset"  value="发     送" onclick="formSubmit1(this.form);">
			    
			   </div>
		    </form>
          </div>
	</div>
</body>
</html>