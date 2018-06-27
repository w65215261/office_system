<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.pmcc.soft.core.utils.AppUtils"%>
<%@page import="org.springframework.web.util.WebUtils"%>
<%@page import="com.pmcc.soft.core.common.OnlineUser"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8" />
<head>
    <title>企业办公综合应用平台</title>
    <script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
    <script type="text/javascript" src="/EasyUiCompoment/commons.js"></script>
    <script language="JavaScript">

        $(document).ready(




                function () {



                    $('#tt').tree({
                        url:'/MenuItemInfo/getMenu.do',//取管理员菜单
                        onClick: function(node){
                            if(node.url==null || node.url==""){
                                return;
                            }
                            addTab(node.text,node.url);  // alert node text property when clicked
                        }
                    });
                    $('.easyui-accordion li a').click(function () {
                        var tabTitle = $(this).text();
                        var url = $(this).attr("href");
                        addTab(tabTitle, url);
                        $('.easyui-accordion li div').removeClass("selected");
                        $(this).parent().addClass("selected");
                    }).hover(function () {
                        $(this).parent().addClass("hover");
                    }, function () {
                        $(this).parent().removeClass("hover");
                    });

                    function addTab(subtitle, url) {
                        if (!$('#tabs').tabs('exists', subtitle)) {
                            $('#tabs').tabs('add', {
                                title: subtitle,
                                content: createFrame(url),
                                closable: true,
                                width: $('#mainPanle').width() - 10,
                                height: $('#mainPanle').height() - 26
                            });
                        } else {

                            $('#tabs').tabs('select', subtitle);
                            var tab =  $('#tabs').tabs('getSelected');
                            if(subtitle!='首页'){
                                $('#tabs').tabs('update', {
                                    tab: tab,
                                    options: {
                                        content: createFrame(url)
                                    }
                                });
                            }else{
                                $('#tabs').tabs('update', {
                                    tab: tab,
                                    options: {
                                        content: createFrame('/newsInfo/sysNotice.do')
                                    }

                                });
                            }



                        }
                        tabClose();
                    }

                    function createFrame(url) {
                        var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
                        return s;
                    }

                    function tabClose() {
                        /*双击关闭TAB选项卡*/
                        $(".tabs-inner").dblclick(function () {
                            var subtitle = $(this).children("span").text();
                            closeTabs(subtitle);
                        });

                        $(".tabs-inner").bind('contextmenu', function (e) {
                            $('#mm').menu('show', {
                                left: e.pageX,
                                top: e.pageY,
                            });

                            var subtitle = $(this).children("span").text();
                            $('#mm').data("currtab", subtitle);

                            return false;
                        });
                    }


                    tabCloseEven();
                    //双击待办事项，进入评审界面
                    $('#todoList').datagrid({
                        onDblClickRow:
                                function(rowIndex,rowData){
                                    cId=rowData.id;
                                    type=rowData.ctype;
                                    taskId=rowData.taskId;
                                    var aurl="?id="+cId+"&taskId="+taskId;
                                    if(type == "GCFWHT"){
                                        aurl= "/engineeringContract/toApprovalUI.do"+aurl;
                                    }else if(type == "CGHT"){
                                        //
                                        aurl = "/purchaseContract/toApprovalUI.do"+aurl;
                                    }else if(type == "WLJRXY"){
                                        aurl="/networkAccessProtocol/toApprovalUI.do"+aurl;
                                    }else if(type == "LWHT"){
                                        aurl = "/labourServicesContract/toApprovalUI.do"+aurl;
                                    }else if(type == "YWFWHT"){
                                        aurl= "/maintenanceServiceContract/toApprovalUI.do"+aurl;
                                    }else if(type == "GYPMMHT"){
                                        // 工业品买卖合同
                                        aurl = "/industryContract/toApprovalUI.do"+aurl;
                                    }else if(type == "WBHT"){
                                        aurl = "/maintenanceContract/toApprovalUI.do"+aurl;
                                    }
                                    addTab("合同评审",aurl);
                                }
                    });

                    $('#tabs').tabs('add', {
                        title: '首页',
                        content: createFrame('/newsInfo/sysNotice.do'),
                        closable: false,
                        width: $('#mainPanle').width() - 10,
                        height: $('#mainPanle').height() - 26
                    });
                });


        function closeTabs(title){
            if(title!='首页'){
                $('#tabs').tabs('close', title);
            }
        }
        //绑定右键菜单事件
        function tabCloseEven() {
            //关闭当前
            $('#mm-tabclose').click(function () {
                var currtab_title = $('#mm').data("currtab");
                closeTabs(currtab_title);

            });
            //全部关闭
            $('#mm-tabcloseall').click(function () {
                $('.tabs-inner span').each(function (i, n) {
                    var t = $(n).text();
                    closeTabs(t);
                });
            });
            //关闭除当前之外的TAB
            $('#mm-tabcloseother').click(function () {
                var currtab_title = $('#mm').data("currtab");
                $('.tabs-inner span').each(function (i, n) {
                    var t = $(n).text();
                    if (t != currtab_title)
                        closeTabs(t);
                });
            });
            //关闭当前右侧的TAB
            $('#mm-tabcloseright').click(function () {
                var nextall = $('.tabs-selected').nextAll();
                if (nextall.length == 0) {
                    //msgShow('系统提示','后边没有啦~~','error');
                    return false;
                }
                nextall.each(function (i, n) {
                    var t = $('a:eq(0) span', $(n)).text();
                    closeTabs(t);
                });
                return false;
            });
            //关闭当前左侧的TAB
            $('#mm-tabcloseleft').click(function () {
                var prevall = $('.tabs-selected').prevAll();
                if (prevall.length == 0) {
                    return false;
                }
                prevall.each(function (i, n) {
                    var t = $('a:eq(0) span', $(n)).text();
                    closeTabs(t);
                });
                return false;
            });

            //退出
            $("#mm-exit").click(function () {
                $('#mm').menu('hide');
            });
        }

        //修改密码功能
        function openWin(){
            $('#passwordWin').window('open');
            $('#password1').val('');
            $('#password2').val('');
        }
        //关闭修改密码框
        function closeWin(){
            $('#passwordWin').window('close');
        }
        function formSubmit(obj){
            var newPassword = $('#password1').val();
            var twoPassword = $('#password2').val();
            if(newPassword!='' && twoPassword!=''){
                if(newPassword!=twoPassword){
                    $.messager.alert('温馨提示',"两次输入的密码不一致！");
                    return;
                }else{
                    $.ajax({
                        type:"POST",
                        url:'/personManage/updatePassword.do?md5Pwd='+newPassword,
                        success:function(data){
                            if(data == "success"){
                                $.messager.alert('温馨提示','密码修改成功！');
                                $('#passwordWin').window('close');
                            }else{
                                $.messager.alert('温馨提示','操作失败！');
                            }
                        },
                        //调用出错执行的函数
                        error: function(){
                        }
                    });
                }
            }else{
                $.messager.alert('温馨提示',"请将内容填写完整！");
            }
        }




    </script>
    <style>
        .footer {
            width: 100%;
            text-align: center;
            line-height: 35px;
            background:url(/publicresource/image/copy_right.jpg) repeat-x ;
        }

        .top-bg {
            width: 100%;
            background:url(/publicresource/image/banner_back.png) repeat-x ;
            height: 60px;
        }
        a:link,a:visited{ text-decoration:none;}
    </style>
</head>


<body class="easyui-layout" data-options="fit:true">
<div data-options="region:'north'" border="true" split="true" style="overflow: hidden; height: 60px;">
    <div  class="top-bg">
        <div style="height:60px; width:100%;background-image: url(/publicresource/image/backshouye.jpg);background-repeat: no-repeat;">
            <table  align="right" style="margin-top:15px">
                <tr>
                    <td><font color="#ffffff" size="10">用户名:${personManage.userCname }</font></td>
                    <td><a href="#"  onclick="openWin()">修改密码</a></td>
                    <td><a href="#"  onclick="logOut();">退出</a></td>
                </tr>
            </table>
        </div>
    </div>
</div>
<!--     <div data-options="region:'south'" border="true" split="true" style="overflow: hidden; height: 40px;"> -->
<!--         <div class="footer" >版权所有：<a href="#">中国平煤神马集团平顶山信息通信技术开发公司</a></div> -->
<!--     </div> -->
<div data-options="region:'west'"  split="true" title="导航菜单" style="width: 200px;fit:true;">
    <div id="aa" class="easyui-accordion" style="position: absolute; top: 27px; left: 0px; right: 0px; bottom: 0px;" fit="true">
        <ul class="easyui-tree" data-options="lines:true" id="tt"></ul>
    </div>
</div>
<div id="mainPanle" data-options="region:'center'">
    <div id="mm" class="easyui-menu" style="width:150px;">
        <div id="mm-tabclose">关闭</div>
        <div id="mm-tabcloseall">全部关闭</div>
        <div id="mm-tabcloseother">除此之外全部关闭</div>
        <div class="menu-sep"></div>
        <div id="mm-tabcloseright">当前页右侧全部关闭</div>
        <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
        <div class="menu-sep"></div>
        <div id="mm-exit">退出</div>
    </div>
    <div id="tabs" class="easyui-tabs"   data-options="closable:false,fit:true"  border="false">
        <!--             <div title="首页" id="home"> -->
        <!--      <table width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr> -->
        <!-- <td height="240" ><table class="easyui-datagrid" id="todoList"  title="待审核"
                    data-options="rownumbers:true,singleSelect:true,fit:true,pagination:true,url:'/homePage/queryDaiban.do?contractStatus=0',method:'get',toolbar:'#tb'">
                <thead>

                    <tr>
                        <th data-options="field:'contractName',width:150">合同名称</th>
                        <th data-options="field:'contractId',width:100">合同编号</th>
                        <th data-options="field:'initUnitName',width:150">发起部门</th>
                        <th data-options="field:'contractStatusShow',width:80">合同状态</th>
                    </tr>
                </thead>
            </table></td> -->

        <!-- <td height="550"> -->
        <!-- 			<table class="easyui-datagrid" id="mc"  title="公告列表"  -->
        <!--                 data-options="fit:true,checkbox:true,singleSelect:false,pagination:true,url:'/newsInfo/query.do',method:'get'"> -->
        <!-- 	            <thead> -->
        <!-- 	                <tr> -->
        <!-- 	                    <th data-options="field:'title',width:300">标题</th> -->
        <!-- 	                    <th data-options="field:'rptPerson',width:70">发布人</th> -->
        <!-- 	                    <th data-options="field:'rptDate',width:150">发布时间</th> -->

        <!-- 	                </tr> -->
        <!-- 	            </thead> -->
        <!--        	 </table> -->
        <!--  </td>
      </tr> -->

        <!-- <tr>
          <td height="260">  <table class="easyui-datagrid" id="todoList"  title="预警信息" style="height:230px;width:540px;"
                                  data-options="rownumbers:true,singleSelect:true,fit:true,pagination:true,url:'/homePage/queryYujing.do',method:'get'">
                                      <thead>
                                          <tr>
                                              <th data-options="field:'cName',width:150">合同名称</th>
                                              <th data-options="field:'contractId',width:100">合同编号</th>
                                              <th data-options="field:'initUnitName',width:90">发起部门</th>
                                              <th data-options="field:'dictOid',width:150">预警类型</th>
                                          </tr>
                                          </thead>
                          </table></td>
          <td><table class="easyui-datagrid" id="todoList"  title="告警信息" style="height:230px;width:530px;"
                            data-options="rownumbers:true,singleSelect:true,fit:true,pagination:true,url:'/homePage/queryGaojing.do?querytype=HTGJ',method:'get'">
                            <thead>

                                  <tr>
                                      <th data-options="field:'cName',width:150">合同名称</th>
                                      <th data-options="field:'contractId',width:100">合同编号</th>
                                      <th data-options="field:'initUnitName',width:90">发起部门</th>
                                      <th data-options="field:'dictOid',width:140">预警类型</th>
                                  </tr>
                            </thead>
                          </table></td>
        </tr> -->
        <!-- 	</table> -->

        <!--             </div> -->
    </div>
</div>

<!-- 修改密码window syx  开始 -->
<div id="passwordWin" class="easyui-window" data-options="closed:true,modal:true,title:'修改密码'" style="width:300px;height:180px;margin-top:30px">
    <form  method="post"  id="updateForm">
        <input type="hidden" value="" name="id" id="id">
        <table cellpadding="5" width="100%">
            <tr>
                <td width="35%">新&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;码:</td>
                <td><input type="password" id="password1" class="easyui-validatebox" ></td>
            </tr>
            <tr>
                <td width="20%">新密码确认:</td>
                <td><input type="password" id="password2" class="easyui-validatebox"></td>
            </tr>
        </table>

        <div style="text-align:center;padding:5px">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="formSubmit(this.form);" style="width:80px">保存</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="closeWin()" style="width:80px">返回</a>
        </div>

    </form>
</div>
<!-- 修改密码window结束 -->

</body>
</html>
