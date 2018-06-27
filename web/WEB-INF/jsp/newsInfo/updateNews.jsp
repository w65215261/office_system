<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@page import="com.pmcc.soft.core.utils.AppUtils" %>
<%@page import="org.springframework.web.util.WebUtils" %>
<%@page import="com.pmcc.soft.core.common.OnlineUser" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>中平信通合同管理系统</title>
    <script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
    <!--  附件上传 -->
    <script type="text/javascript" src="/EasyUiCompoment/upload.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" charset="utf-8" src="/EasyUiCompoment/commons.js"></script>

    <script type="text/javascript">
        //实例化编辑器
        $(document).ready(function () {
            var ue = UE.getEditor('editor');
            ue.ready(function () {
                $.ajax({
                    type: "get",
                    url: '/newsInfo/find.do',

                    data: {
                        id: sid
                    },
                    success: function (data) {
                        ue.setContent(data.content);
                        $('#title').val(data.title);
                        /*  $('#orderNo').val(data.orderNo); */
                    },
                    //调用出错执行的函数
                    error: function () {
                    }
                });

            });
        });

        //初始化编辑器，加载内容


        //获得请求参数的方法，用来获取主页面传的新闻id
        var request =
        {
            QueryString: function (val) {
                var uri = window.location.search;
                var re = new RegExp("" + val + "=([^&?]*)", "ig");
                return ((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 1)) : null);
            }
        }
        var sid = request.QueryString("id");
        //返回
        function goBack() {
            window.returnVal = false;
            OpenClose();
        }
        //获得编辑器内容并保存
        function getContent() {
            var content = UE.getEditor('editor').getContent();
            var contentTxt = UE.getEditor('editor').getContentTxt();
            var title = $('#title').val();
            /*  var orderNo=$('#orderNo').val(); */
            $.ajax({
                type: "POST",
                url: '/newsInfo/update.do',
                data: {
                    title: title,
                    content: content,
                    contentTxt: contentTxt,
                    /* orderNo:orderNo, */

                    id: sid
                },
                success: function (data) {
                    if (data == "success") {
                        window.returnVal = true;
                        OpenClose();
                    }
                },
                //调用出错执行的函数
                error: function () {
                }

            });
        }
    </script>
</head>

<body class="easyui-layout">
<div data-options="fit:true">
    <table width="100%" border="0" cellspacing="1" cellpadding="1">
        <tr>
            <td>
                <div data-options="region:'north',height:30px" title="新闻标题：">
                    新闻标题：<input class="easyui-validatebox" name="title" style="width:60%" validtype="length[0,50]"
                                id="title">
                </div>

            </td>
        </tr>
        <!-- <tr>
        <td>
        排序：<input class="easyui-numberbox" type="text"  style="width:80%" name="orderNo" id="orderNo" missingMessage="该项为必须输入项" data-options="required:true"></input>
        </td>
        </tr> -->

        <tr>
            <td>
                <div style="height:400px;overflow:auto;text-align: center;">
                    <script id="editor" type="text/plain" style="width:970px;height:360px;"></script>
                </div>
            </td>
        </tr>


        <!--  <tr>
             <div id="tbar" >
           <table width="100%">
             <tr>
               <td width="100%">
                 &nbsp;&nbsp;
                 <input type="button" value="保存" onclick="getContent()" />
                 &nbsp;&nbsp;
                 <input type="button" value="返回" onclick="goBack()" />
               </td>
             </tr>
           </table>
         </div>
         </tr> -->
    </table>

    <div style="text-align:center;">
        <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-ok" onclick="getContent()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-cancel" onclick="goBack()">返回</a>
    </div>

</div>


<input type="hidden" name="content" id="content"/>
</body>
</html>