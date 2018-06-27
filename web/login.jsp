<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">

    <title>企业办公综合应用平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/smartAdmin/css/bootstrap.min.css">
    <script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>


    <style>

        /* http://css-tricks.com/perfect-full-page-background-image/ cornsilk */
        html {
            background: url(/smartAdmin/loginnn.jpg) no-repeat center center fixed;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
        }

        body {
            padding-top: 20px;
            font-size: 16px;
            font-family: "微软雅黑", serif;
            background: transparent;
        }

        h1 {
            font-family: "微软雅黑";
            font-weight: 400;
            font-size: 40px;
        }

        /* Override B3 .panel adding a subtly transparent background */
        .panel {
            background-color: rgba(255, 255, 255, 0.7);
        }

        .margin-base-vertical {
            margin: 40px 0;
        }

        #appDownload{
            cursor: pointer;
        }
    </style>
</head>

<script type="text/javascript">

    function showRegister(){
        window.location.href="register.jsp";
    }
    document.onkeydown = function (event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];

        if (e && e.keyCode == 13) { // enter 键
            doLogin();  //调用登录按钮的登录事件
        }
    };
    function doLogin() {
        if ($('#userName').val() == '') {
//			$.messager.alert('提示', '请填写用户名！');
//			$("#aaa").attr("class","alert alert-danger");
            alert("请填写用户名");
            return;
        }
        if ($('#passWord').val() == '') {
            alert('请填写密码！');
            return;
        }
        $.ajax({
            type: "POST",
            url: 'authority/login.do',
            data: {
                'userName': $('#userName').val(),
                'passWord': $('#passWord').val(),
                'type': '1'
            },
            success: function (data) {
                if (!data.success) {
//					$.messager.alert('提示', data.loginMsg);
                    alert(data.loginMsg);
                } else {
                    window.location.href = data.loginUrl;
                }
            }
        });
    }

    function keydown(event) {
        if (event.keyCode == 13) {
            doLogin();  //调用登录按钮的登录事件
        }
    }

    function formClear() {
        $("#loginForm").form("clear");
    }
//    $(function(){
//        $("#appDownload").hover(
//            function () {
//                $(this).attr("src","/smartAdmin/img/appDownload/appDownload_hover.gif");
//            },
//            function () {
//                $(this).attr("src","/smartAdmin/img/appDownload/appDownload_normal.gif");
//            }
//        );
//    });

    //app下载
    function apkDownload(){
       location.href="/PmccDesk.apk"
    }
</script>
<body>

<%--<div id="alert-user" class="alert alert-danger hidden " role="alert">--%>
<%--<strong>提示：</strong>请输入您的用户名！--%>
<%--</div>--%>

<div style="width: 300px;padding-left: 100px" id="aaa" class="alert alert-warning hidden alert-dismissable">
    <button type="button" class="close" data-dismiss="alert"
            aria-hidden="false">
        &times;
    </button>
    请填写用户名！
</div>

<div style="text-align: right;margin-right: 30px;">
    <img src="publicresource/image/apk.png"  />
</div>
<div class="container">
    <div class="row">
        <div style="position: absolute; left: 50%; top: 50%; margin-top: -150px; margin-left: -175px;"
             class="col-md-3 col-md-offset-3 panel panel-default">

            <h1 class="margin-base-vertical"><p class="text-center">企业办公综合应用平台</p>
                <small><p class="text-center"></small>
            </h1>


            <p>

            </p>

            <p>

            </p>

            <form class="margin-base-vertical" role="form" action="" method="POST">
                <p class="input-group">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span><span
                            class="icon-envelope"></span></span>
                    <input type="text" class="form-control input-lg" id="userName" name="userName" placeholder="用户名"/>

                </p>

                <p class="input-group">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span><span
                            class="icon-envelope"></span></span>

                    <input type="password" class="form-control input-lg" id="passWord" name="passWord"
                           placeholder="密码"/>
                </p>

                <p class="text-center">
                    <button style="width: 150px;" type="button" onclick="doLogin();" class="btn btn-success btn-1g">登录
                    </button>
                </p>
                </span>
            </form>


        </div>
        <!-- //main content -->
    </div>
    <!-- //row -->
</div>
<!-- //container -->

</body>
</html>


