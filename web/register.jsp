<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" %>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
<html lang="en-us" id="extr-page">
<head>
	<meta charset="utf-8">
	<title> 企业注册</title>
	<meta name="description" content="">
	<meta name="author" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

	<!-- #CSS Links -->
	<!-- Basic Styles -->
	<link rel="stylesheet" type="text/css" media="screen" href="smartAdmin/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" media="screen" href="smartAdmin/css/font-awesome.min.css">

	<!-- SmartAdmin Styles : Caution! DO NOT change the order -->
	<link rel="stylesheet" type="text/css" media="screen" href="smartAdmin/css/smartadmin-production-plugins.min.css">
	<link rel="stylesheet" type="text/css" media="screen" href="smartAdmin/css/smartadmin-production.min.css">
	<link rel="stylesheet" type="text/css" media="screen" href="smartAdmin/css/smartadmin-skins.min.css">

	<!-- SmartAdmin RTL Support -->
	<link rel="stylesheet" type="text/css" media="screen" href="smartAdmin/css/smartadmin-rtl.min.css">

	<!-- We recommend you use "your_style.css" to override SmartAdmin
         specific styles this will also ensure you retrain your customization with each SmartAdmin update.
    <link rel="stylesheet" type="text/css" media="screen" href="css/your_style.css"> -->

	<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
	<link rel="stylesheet" type="text/css" media="screen" href="smartAdmin/css/demo.min.css">

	<!-- #FAVICONS -->
	<link rel="shortcut icon" href="smartAdmin/img/favicon/favicon.ico" type="image/x-icon">
	<link rel="icon" href="smartAdmin/img/favicon/favicon.ico" type="image/x-icon">

	<!-- #GOOGLE FONT -->
	<%--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">--%>

	<!-- #APP SCREEN / ICONS -->
	<!-- Specifying a Webpage Icon for Web Clip
         Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html -->
	<link rel="apple-touch-icon" href="smartAdmin/img/splash/sptouch-icon-iphone.png">
	<link rel="apple-touch-icon" sizes="76x76" href="smartAdmin/img/splash/touch-icon-ipad.png">
	<link rel="apple-touch-icon" sizes="120x120" href="smartAdmin/img/splash/touch-icon-iphone-retina.png">
	<link rel="apple-touch-icon" sizes="152x152" href="smartAdmin/img/splash/touch-icon-ipad-retina.png">

	<!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">

	<!-- Startup image for web apps -->
	<link rel="apple-touch-startup-image" href="smartAdmin/img/splash/ipad-landscape.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
	<link rel="apple-touch-startup-image" href="smartAdmin/img/splash/ipad-portrait.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
	<link rel="apple-touch-startup-image" href="smartAdmin/img/splash/iphone.png" media="screen and (max-device-width: 320px)">

</head>
<body id="login">
<!-- possible classes: minified, no-right-panel, fixed-ribbon, fixed-header, fixed-width-->
<header id="header">
	<!--<span id="logo"></span>-->

	<div id="logo-group">
		<span id="logo"> <img alt="中平信通" src="/smartAdmin/img/logoPMCC.png"> </span>

		<!-- END AJAX-DROPDOWN -->
	</div>

	<span id="extr-page-header-space"> <button type="button" class="btn btn-primary btn-lg " style="margin-right: 50px;margin-top: 10px" onclick="callBack()">
	返回登录
</button></span>

</header>
<div id="main" role="main">

	<!-- MAIN CONTENT -->
	<div id="content" class="container">

		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-7 col-lg-7 hidden-xs hidden-sm">
				<h1 class="txt-color-red login-header-big">企业办公综合应用平台</h1>
				<div class="hero">

					<div class="pull-left login-desc-box-l">
						<h4 class="paragraph-header">办公桌为企业量身打造统一办公通讯平台
							在云和移动时代，办公桌成为一个工作方式。</h4>
						<div class="login-app-icons">
						</div>
					</div>

					<img src="smartAdmin/img/demo/iphoneview.png" alt="" class="pull-right display-image" style="width:210px">

				</div>

				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<h5 class="about-heading">About SmartAdmin - Are you up to date?</h5>
						<p>
							Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa.
						</p>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<h5 class="about-heading">Not just your average template!</h5>
						<p>
							Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi voluptatem accusantium!
						</p>
					</div>
				</div>

			</div>
			<div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
				<div class="well no-padding">

					<form id="smart-form-register" class="smart-form client-form">
						<header>
							企业注册

						</header>

						<fieldset>
							<section>
								<label class="input"> <i class="icon-append fa fa-university"></i>
									<input type="text" name="company" placeholder="企业名称" id="company">
									<b class="tooltip tooltip-bottom-right">企业名称</b> </label>

								<span type="text"  placeholder="企业名称" id="companyInfo"></span>
							</section>

						</fieldset>

						<fieldset>
							<section>
								<label class="input"> <i class="icon-append fa fa-user"></i>
									<input type="text" name="usercname" placeholder="管理员中文名称" id="usercname">
									<b class="tooltip tooltip-bottom-right">管理员中文名称</b> </label>
								<span type="text"   id="usercnameInfo"></span>
							</section>
							<section>
								<label class="input"> <i class="icon-append fa fa-user"></i>
									<input type="text" name="userename" placeholder="管理员英文名称" id="userename" onblur="checkPersonName()">
									<b class="tooltip tooltip-bottom-right">管理员英文名称</b> </label>
								<span type="text"   id="userenameInfo"></span>
							</section>

							<section>
								<label class="input"> <i class="icon-append fa fa-lock"></i>
									<input type="password" name="password" placeholder="密码" id="password" onblur="checkpassword()">
									<b class="tooltip tooltip-bottom-right">密码</b> </label>
								<span type="text"   id="passwordInfo"></span>
							</section>
							<section >
								<label class="input"> <i class="icon-append fa fa-lock"></i>
									<input type="password" name="passwordConfirm" placeholder="重复密码" id="passwordConfirm" onblur="checkpasswordConfirm()">
									<b class="tooltip tooltip-bottom-right">重复密码</b> </label>
								<span type="text"   id="passwordConfirmInfo"></span>
							</section>
						</fieldset>

						<footer>
							<input type="button"  class="btn btn-primary" onclick="register()" value="注册"/>

						</footer>

						<div class="message">
							<i class="fa fa-check"></i>
							<p>
								Thank you for your registration!
							</p>
						</div>
					</form>

				</div>

			</div>
		</div>
	</div>

</div>


<!--================================================== -->

<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
<script src="smartAdmin/js/plugin/pace/pace.min.js"></script>

<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
<%--<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>--%>
<script> if (!window.jQuery) { document.write('<script src="smartAdmin/js/libs/jquery-2.1.1.min.js"><\/script>');} </script>

<%--<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>--%>
<script> if (!window.jQuery.ui) { document.write('<script src="smartAdmin/js/libs/jquery-ui-1.10.3.min.js"><\/script>');} </script>

<!-- IMPORTANT: APP CONFIG -->
<script src="smartAdmin/js/app.config.js"></script>

<!-- JS TOUCH : include this plugin for mobile drag / drop touch events
<script src="js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> -->

<!-- BOOTSTRAP JS -->
<script src="smartAdmin/js/bootstrap/bootstrap.min.js"></script>

<!-- JQUERY VALIDATE -->
<script src="smartAdmin/js/plugin/jquery-validate/jquery.validate.min.js"></script>

<!-- JQUERY MASKED INPUT -->
<script src="smartAdmin/js/plugin/masked-input/jquery.maskedinput.min.js"></script>

<!--[if IE 8]>

<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>

<![endif]-->

<!-- MAIN APP JS FILE -->
<script src="smartAdmin/js/app.min.js"></script>

<script type="text/javascript">
	runAllForms();
	function register(){
//		window.location.href="/../login.jsp";
	}
	// Model i agree button
	$("#i-agree").click(function(){
		$this=$("#terms");
		if($this.checked) {
			$('#myModal').modal('toggle');
		} else {
			$this.prop('checked', true);
			$('#myModal').modal('toggle');
		}
	});

	function register(){
//		window.location.href="/../login.jsp";
		var company=$("#company").val();
		var usercname=$("#usercname").val();
		var userename=$("#userename").val();
		var password=$("#password").val();
		var passwordConfirm=$("#passwordConfirm").val();
		if(company==null||company.length<0||company==""){
			$("#companyInfo").html("企业名称不能为空");
			return;
		}else{
			$("#companyInfo").html("");
		}
		if(usercname==null||usercname.length<0||usercname==""){
			$("#usercnameInfo").html("管理员中文名称不能为空");
			return;
		}else{
			$("#usercnameInfo").html("");
		}
		if(userename==null||userename.length<0||userename==""){
			$("#userenameInfo").html("管理员英文名称不能为空")

			return;
		}else{
			$("#userenameInfo").html("")
		}
		if(password==null||password.length<0||password==""){
			$("#passwordInfo").html("密码不能为空")
			return;
				} else if(password.length<6||password.length>20){
				$("#passwordInfo").html("密码长度需要6-20位")
				return;
				}else{
					$("#passwordInfo").html("");
			}
		if(passwordConfirm==null||passwordConfirm.length<0||passwordConfirm==""){
			$("#passwordConfirmInfo").html("密码不能为空")
			return;
				}else if(passwordConfirm.length<6||passwordConfirm.length>20){
					$("#passwordConfirmInfo").html("密码长度需要6-20位");
					return;
				}else{
					$("#passwordConfirmInfo").html("")
		}
		if(password!=passwordConfirm){
			$("#passwordConfirmInfo").html("2次输入密码不一致")
			return;
		}else{
			$("#passwordConfirmInfo").html("")
		}
		if(userename.match(/^[0-9a-zA-Z]*$/)){
			$.ajax({
				type: "GET",
				url: '/organization/checkPersonName.do',
				data: {
					personName:userename
				},
				success: function (data) {
					if(data=="success"){

						$("#userenameInfo").html("");
						$.ajax({
							type: 'POST',
							url: '/organizations/insertCompany.do',
							dataType: 'html',
							data:{
								company:company,
								usercname:usercname,
								userename:userename,
								password:password
							},
							success: function(data) {
								alert("已提交资料,请等待管理员审批");
								window.location.href="/";
							}
						});
					}else{
						$("#userenameInfo").html(userename+"已存在");
					}
				},
				// 调用出错执行的函数
				error: function () {
				}
			});
		}else{
			$("#userenameInfo").html("不能输入汉字和特殊字符");
			return;
		}

	}
	function checkpassword(){
		var password=$("#password").val();
		if(password.length<6||password.length>20){
			$("#passwordInfo").html("密码长度需要6-20位")
		}else{
			$("#passwordInfo").html("")
		}
	}
	function checkpasswordConfirm(){
		var passwordConfirm=$("#passwordConfirm").val();
		var password=$("#password").val();
		if(passwordConfirm.length<6||passwordConfirm.length>20){
			$("#passwordConfirmInfo").html("密码长度需要6-20位");

		}else if(passwordConfirm!=password){
			$("#passwordConfirmInfo").html("2次输入密码不一致");
		}else{
			$("#passwordConfirmInfo").html("")
		}
	}
</script>

<!-- Your GOOGLE ANALYTICS CODE Below -->
<script type="text/javascript">

	var _gaq = _gaq || [];
	_gaq.push(['_setAccount', 'UA-43548732-3']);
	_gaq.push(['_trackPageview']);

//	(function() {
//		var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
//		ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
//		var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
//	})();
function callBack(){
	window.location.href="/";
}
function checkPersonName(){
	var personName=$("#userename").val();
	if(personName.match(/^[0-9a-zA-Z]*$/)){
		$.ajax({
			type: "GET",
			url: '/organization/checkPersonName.do',
			data: {
				personName:personName
			},
			success: function (data) {
				if(data=="success"){

					$("#userenameInfo").html("");
				}else{
					$("#userenameInfo").html(personName+"已存在");
				}
			},
			// 调用出错执行的函数
			error: function () {
			}
		});
	}else{
		$("#userenameInfo").html("不能输入汉字和特殊字符");
		return;
	}

}
</script>
<style type="text/css">
	#companyInfo,#usercnameInfo,#userenameInfo,#passwordInfo,#passwordConfirmInfo{
		color: #FF413E;
	}
</style>
</body>
</html>