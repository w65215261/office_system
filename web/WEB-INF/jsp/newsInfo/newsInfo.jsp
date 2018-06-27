<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>

    <script type="text/javascript">

        //  $(document).ready(function() {
        //  	 $('#mc').datagrid({
        //			onDblClickRow: function(rowIndex, rowData){
        // 				top.openDialogWithCallback("/newsInfo/detail.do?id="+rowData.id,top.NewGuid(), '窗体列表 - 查看详情',1000,500,function (){
        //		    		$('#mc').datagrid("reload");
        //		    	}, true);
        //    	 			addTab("公告详情","/newsInfo/see.do?id="+rowData.id);
        //			}
        //		});
        //});
        $(document).ready(function () {
            $('#mc').datagrid({
                checkbox: true,
                rownumbers: true,
                fit: true,
                singleSelect: false,
                pagination: true,
                url: '/newsInfo/query.do',
                method: 'get',
                toolbar: '#tb1',
                rowStyler: function (index, row) {
                    if (row.topmark == 1) {/* #4392F1 */
                        return 'background-color:#5294E3;';
                        /* color:red;font-weight:bold; */
                    }
                }
            });
        });


        // 查询按钮
        function search() {
            var stype = $("#searchType").val();
            var sval = $("#searchTextValue").val();
            var startTime = $("#startTime").datebox("getValue");
            var endTime = $("#endTime").datebox("getValue");
            if ((startTime != "" && endTime == "") || (startTime == "" && endTime != "")) {
                $.messager.alert('温馨提示', "请同时选择开始时间和结束时间进行查询！");
                return;
            } else {
                if (stype == "title") {

                    if (startTime != null && startTime != "" && endTime != null && endTime != "") {
                        $('#mc').datagrid('reload', {title: sval, stime: startTime, etime: endTime});
                    } else {
                        $('#mc').datagrid('reload', {title: sval});
                    }

                } else if (stype == "content") {
                    if (startTime != null && startTime != "" && endTime != null && endTime != "") {
                        $('#mc').datagrid('reload', {content: sval, stime: startTime, etime: endTime});
                    } else {
                        $('#mc').datagrid('reload', {content: sval});
                    }
                }

            }
        }
        // 重置
        function reset() {
            $("#searchTextValue").val("");
            $("#searchComboValue").combobox("clear");
            $("#startTime").datebox("setValue", "");
            $("#endTime").datebox("setValue", "");
        }


        // 发布新闻
        function releaseNews() {
            top.openDialogWithCallback("/newsInfo/toSaveUI.do", top.NewGuid(), '窗体列表 - 查看详情', 1000, 500, function (data) {
                if (data == true) {
                    $('#mc').datagrid({
                        checkbox: true,
                        rownumbers: true,
                        fit: true,
                        singleSelect: false,
                        pagination: true,
                        url: '/newsInfo/query.do',
                        method: 'get',
                        toolbar: '#tb1',
                        rowStyler: function (index, row) {
                            if (row.topmark == 1) {/* #4392F1 */
                                return 'background-color:#5294E3;';
                                /* color:red;font-weight:bold; */
                            }
                        }
                    });
                } else {
                    return;
                }
            }, true);
        }

        function updateNews() {
            var oneTest = $("#mc").datagrid("getSelections");
            if (oneTest == null || oneTest.length == 0) {
                $.messager.alert('温馨提示', '请选择一条数据！');
            } else {
                if (oneTest.length > 1) {
                    $.messager.alert('温馨提示', '只能修改一条数据！');
                } else {
                    top.openDialogWithCallback("/newsInfo/toUpdate.do?id=" + oneTest[0].id, top.NewGuid(), '窗体列表 - 查看详情', 1000, 500, function (data) {
                        if (data == true) {
                            $('#mc').datagrid("reload");
                        } else {
                            return;
                        }
                    }, true);
                    // self.location.href="toUpdate.do?type=${type}&id="+oneTest[0].id;
                }
            }
        }


        function deleteNews() {
            var oneTest = $("#mc").datagrid("getSelections");
            if (oneTest == null || oneTest.length == 0) {
                $.messager.alert('提示', '请先选择要删除的新闻');
            } else {
                var ids = "";
                for (var i = 0; i < oneTest.length; i++) {
                    ids += oneTest[i].id + ",";
                }

                $.messager.confirm('温馨提示', '是否确认删除？', function (r) {
                    if (r) {

                        $.ajax({
                            type: "GET",
                            url: '/newsInfo/delete.do',
                            data: {
                                ids: ids
                            },
                            success: function (data) {
                                if (data == "success") {
                                    $.messager.alert('温馨提示', '操作成功！');
                                    window.location.href = "/newsInfo/show.do";
                                } else {
                                    $.messager.alert('温馨提示', '操作失败！');
                                }
                            },
                            error: function () {
                            }
                        });
                    }
                });
            }
        }
        function rpt(val, row) {
            return '<a href="#\'' + row.id + '\'" onclick="constructionManager3(\'' + row.id + '\')">' + '置顶' + '</a> '
        }
        function constructionManager3(id) {

            $.ajax({
                type: "GET",
                url: '/newsInfo/stick.do',
                data: {
                    id: id
                },
                success: function (data) {
                    if (data == "success") {
                        $.messager.alert('温馨提示', '置顶成功！');
                        window.location.href = "/newsInfo/show.do";
                    } else {
                        $.messager.alert('温馨提示', '置顶失败！');
                    }
                },
                error: function () {
                }
            });

        }


        function go1(val, row) {
            return '<a href="#\'' + row.id + '\'" onclick="constructionManager7(\'' + row.id + '\')">' + val + '</a> '
        }
        function constructionManager7(id) {
            top.openDialogWithCallback("/newsInfo/detail.do?id=" + id, top.NewGuid(), '窗体列表 - 查看详情', 1000, 500, function () {
                $('#mc').datagrid("reload");
            }, true);
        }
    </script>
</head>
<body class="easyui-layout">

<div region="center">
    <table class="easyui-datagrid" id="mc" title="文章列表" style="height:450px;"
           data-options="checkbox:true,rownumbers:true,fit:true,singleSelect:false,pagination:true,url:'/newsInfo/query.do',method:'get',toolbar:'#tb1'">
        <thead>
        <tr>
            <th data-options="field:'id',checkbox:true"></th>
            <th data-options="field:'title',width:300,formatter:go1">标题</th>
            <!--   <th data-options="field:'orderNo',width:300">排序</th> -->
            <th data-options="field:'rptPerson',width:70">发布人</th>
            <th data-options="field:'department',width:70">发布人部门</th>
            <th data-options="field:'rptDate',width:150">发布时间</th>
            <th data-options="field:'clicknum',width:150">点击数</th>
            <th data-options="field:'rpt',width:150,formatter:rpt"></th>
        </tr>
        </thead>
    </table>
    <div id="tb1" style="padding:5px;height:auto">
        <div id="tb2" style="padding:5px;height:auto">
            <div style="margin-bottom:5px">
                <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="releaseNews()">新增</a>
                &nbsp;&nbsp;
                <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateNews()">修改</a>
                &nbsp;&nbsp;
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteNews()">删除</a>
            </div>
        </div>
        <table width="100%">
            <tr>
                <td width="100%">
                    <select id="searchType" style="width:10%" name="">
                        <option value="title">标题</option>
                        <option value="content">内容</option>
                    </select>

                    <div id="div_searchTextValue" style="width:200px;display:inline;">
                        <input id="searchTextValue" type="text" style="width:200px"/>
                    </div>
                    &nbsp;&nbsp;
                    开始时间:
                    <input id="startTime" name="startTime" class="easyui-datebox" type="text"/>
                    &nbsp;&nbsp;
                    结束时间:
                    <input id="endTime" name="endTime" class="easyui-datebox" type="text"/>
                    &nbsp;&nbsp;
                    <input type="button" value="查询" onclick="search()"/>
                    &nbsp;&nbsp;
                    <input type="button" value="重置" onclick="reset()"/>
                    &nbsp;&nbsp;

                </td>
            </tr>
        </table>
    </div>
</div>

<!-- 新闻列表结束 -->


</body>
</html>