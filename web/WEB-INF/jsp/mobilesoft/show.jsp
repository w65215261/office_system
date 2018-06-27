<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
<link rel="stylesheet" type="text/css" href="/publicresource/btn.css">

<script type="text/javascript">
$.extend($.fn.validatebox.defaults.rules, {
    //验证汉子
    CHS: {
        validator: function (value) {
            return /^([0-9.]+)$/.test(value);
        },
        message: '只能输入数字'
    }
}) 

function show(){
	top.openDialogWithCallback("/mobilesoftadd/mobilesoftadd.jsp", top.NewGuid(), '窗体列表 - 查看详情',390,170, function (data){
		if(data ==true){
			$('#authorityRoleShowTable').datagrid({
				url : '/mobilesoft/query.do'
			});
		}else{
			return;
		}
		
	}, true);
}



function showWindow(){
		var selectedData = $("#authorityRoleShowTable").datagrid("getSelections");
		if(selectedData== null || selectedData.length==0){
			$.messager.alert('提示','请选择一行数据！');
			return;
		}
		if(selectedData.length>=2){
			$.messager.alert('提示','请选择一行数据！');
			return;
		}
		var value = $("#authorityRoleShowTable").datagrid("getSelected").versionid;
		var time = $("#authorityRoleShowTable").datagrid("getSelected").createtime;
					top.openDialogWithCallback('/mobilesoft/queryByid.do?id='+value, top.NewGuid(), '窗体列表 - 查看详情',390,170, function (data){
						if(data == true){
							$('#authorityRoleShowTable').datagrid({
								url : '/mobilesoft/query.do'
							});
						}else{
							return;
						}	
					}, true);
	}

	function removeData() {
		var selectedData = $("#authorityRoleShowTable").datagrid(
				"getSelections");
		if (selectedData == null || selectedData.length == 0) {
			$.messager.alert('提示', '请选择一行数据！');
		} else {
			var ids = "";
			for (var i = 0; i < selectedData.length; i++) {
				ids += selectedData[i].versionid + ",";
			}
			$.messager.confirm('温馨提示', '是否确认删除？', function(r) {
				if (r) {
					$.ajax({
								type : "POST",
								url : '/mobilesoft/delete.do',
								data : {
									ids : ids
								},
								success : function(data) {
									if (data == "success") {
										$.messager.alert('温馨提示', '操作成功！');
										$('#authorityRoleShowTable').datagrid(
												'reload');
									} else {
										$.messager.alert('温馨提示', '操作失败！');
									}
								},
								error : function() {
								}
							});
				}
			});
		}
	}

	function sousuo() {
		var value = $('#ss').val();
		$('#authorityRoleShowTable').datagrid({
			url : '/mobilesoft/find.do?value=' + value
		});

	}
	function  go(val,row){
		return '<a href="#" onclick="constructionManager(\'' + row.versionid+ '\')">下载</a> '
		}
	
	function constructionManager(row){
		window.location.href="/mobilesoft/download.do?id="+row;
		
	}
	
	

	$(function() {
		$('#authorityRoleShowTable').datagrid({
			url : '/mobilesoft/query.do'
		});
	});

	function formatterdate(val, row) {
	 	if (val != null) {
			var date = new Date(val);
			return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
					+ date.getDate() + ' ' + date.getHours() + ':'
					+ date.getMinutes() + ':' + date.getSeconds();
		} 
		/* val=parseInt(val,10);//转为整形
		var date =new Date(val);//正确
		return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
		+ date.getDate() + ' ' + date.getHours() + ':'
		+ date.getMinutes() + ':' + date.getSeconds(); */
	}
</script>

</head>
<body id="cc" class="easyui-layout">
	<div data-options="region:'center',title:'手机应用管理',fit:true">
		<table id="authorityRoleShowTable" class="easyui-datagrid" data-options="checkbox:true,fit:true,pagination:true,rownumbers:true,singleSelect:false,url:'',method:'get',toolbar:'#tb_authorityRoleInfoShow'">
        <thead>
            <tr>
              	<th data-options="field:'versionid',checkbox:true"></th> 
                <th data-options="field:'versioncode',width:100,align:'center'">编码</th>
                <th data-options="field:'versionname',width:100,align:'center'">名称</th>
                <th data-options="field:'versiondescription',width:100,align:'center'">备注</th>
              <!--   <th data-options="field:'downloadurl',width:200" align="center">URL</th> -->
                <th data-options="field:'createtime',width:150,formatter : formatterdate" align="center">时间</th>
                <th data-options="field:'aa',width:200,formatter:go" align="center">下载</th>
            </tr>
        </thead>
    </table>
	
	  <div id="tb_authorityRoleInfoShow" style="padding:5px;height:auto">
        <div style="margin-bottom:5px">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="show();">新增</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showWindow();">修改</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeData();">删除</a>
         	   &nbsp;&nbsp;   &nbsp;&nbsp;编码:<input   type="text"  plain="true" id="ss" align="right" > 
       <!--   	 <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="sousuo();">搜索</a> -->
         	      &nbsp;&nbsp;
            <input type="button" value="查询" onclick="sousuo()" />
        </div>
    </div>
	</div>
</body>
</html>