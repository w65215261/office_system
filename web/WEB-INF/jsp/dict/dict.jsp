<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>码表配置</title>
<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
</head>
<style type="text/css">
body {
	background-image: url(/publicresource/image/login_BG_Z.jpg);
	background-repeat: repeat-x;
}
</style>

<script type="text/javascript">
	function clickTree(node) {
		//	alert(node.id);
		$('#allDict').datagrid('load', {
			'parentId' : node.id
		});
	}

	function add() {
		self.location.href = "toAddDict.do";
	}
	
	function update() {
		var oneTest = $("#allDict").datagrid("getSelections");
		if (oneTest == null || oneTest.length != 1) {
			$.messager.alert('温馨提示',"请选择一条数据");
		} else {
			var one = oneTest[0];
			self.location.href = "toUpdateDict.do?id=" + one.id;
		}
	}
	
	function del() {
		var oneTest = $("#allDict").datagrid("getSelections");
		if (oneTest == null || oneTest.length == 0) {
			$.messager.alert('温馨提示',"请选择至少一条数据");
		} else {
			var ids = "";
        	for(var i = 0; i < oneTest.length; i++){
        		ids += ',' + oneTest[i].id;
        	}
    		$.messager.confirm('温馨提示','是否确认删除？',function(r){
        		if(r){
        			// 重置
        			$.ajax({
    					type:"GET",
    					url:'delete.do',
    					data : {
    						param : ids
    					},
    					success:function(data){
    						if(data == "success"){
    							$.messager.alert('温馨提示','删除成功！');
    							/* TreeView v;
    							var a = v.SelectedNode.Text; */
    							$("#allDict").datagrid("reload");
    							$("#dicTree").tree("reload");
    							/* $("#dicTree").datagrid("reload",self.location.href = "getParentAll.do"); */
    	       	        	}else{
    	       	        		$.messager.alert('温馨提示','操作失败！');
    	       	        	}
    	       	        },
    	       	        // 调用出错执行的函数
    	       	        error: function(){
    	       	        }         
    	       	    });
        		}
    		});
		}
	}
</script>
<body class="easyui-layout">
	<div data-options="region:'west',split:true" title="字典类别"
		style="width: 200px;">
		<ul class="easyui-tree" id="dicTree"
			data-options="cascadeCheck:false,url:'getParentAll.do',
                onClick:function(node,checked){
                clickTree(node,checked)}">
		</ul>
	</div>

	<div region="center" style="overflow: hidden;">
		<table class="easyui-datagrid" id="allDict" title="字典列表"
			style="height: 450px;"
			data-options="checkbox:true,rownumbers:true,fit:true,singleSelect:false,pagination:true,url:'query.do',method:'get',toolbar:'#tb'">
			<thead>
				<tr>
					<th data-options="field:'id',checkbox:true"></th>
					<th data-options="field:'dictName',width:150">字典名称</th>
					<th data-options="field:'dictId',width:150">编码</th>
					<th data-options="field:'dictType',width:150">编码类别</th>
					<th data-options="field:'rank',width:50">等级</th>
					<th data-options="field:'sortNo',width:50">排序</th>
				</tr>
			</thead>

		</table>

		<div id="tb" style="padding: 5px; height: auto">
			<div style="margin-bottom: 5px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"
					plain="true" onclick="add()">新增</a> <a href="#"
					class="easyui-linkbutton" iconCls="icon-edit" plain="true"
					onclick="update()">修改</a> <a href="#" class="easyui-linkbutton"
					iconCls="icon-remove" plain="true" onclick="del()">删除</a>
			</div>
		</div>
	</div>


</body>
</html>