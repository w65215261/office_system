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
        $(document).ready(function () {
            var ue = UE.getEditor('editor');
        });

        function getContent() {
            var content = UE.getEditor('editor').getContent();
            var contentTxt = UE.getEditor('editor').getContentTxt();
            var title = $('#title').val();
            /*   var orderNo=$('#orderNo').val(); */
            if (title != "" && title != null) {
                $.ajax({
                    type: "POST",
                    url: '/newsInfo/insert.do',
                    data: {
                        title: title,
                        content: content,
                        contentTxt: contentTxt
                        /* 	orderNo:orderNo, */

                    },
                    success: function (data) {
                        if (data == "success") {
                            window.returnVal = true;
                            OpenClose();
                            // $.messager.alert('温馨提示','操作成功');
                            //window.location.href = "/newsInfo/show.do?type=${type}";
                        }
                    },
                    //调用出错执行的函数
                    error: function () {
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请输入标题");
            }
        }

        function goBack() {
            window.returnVal = false;
            OpenClose();
        }
    </script>
</head>

<body class="easyui-layout">
<div data-options="fit:true">
    <table width="100%" border="0" cellspacing="1" cellpadding="1">
        <tr>
            <td>
                <div data-options="region:'north',height:30px" title="新闻标题：">
                    标题：<input class="easyui-validatebox" name="title" style="width:60%" validtype="length[0,50]"
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
                <div style="text-align:center;">
                    <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-ok" onclick="getContent()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-cancel" onclick="goBack()">返回</a>
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

</div>
</body>
</html>