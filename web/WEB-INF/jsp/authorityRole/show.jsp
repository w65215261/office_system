<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="/publicresource/btn.css">

<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>


<script type="text/javascript">

function showWindow(edit){
	var toShowUrl="/authorityRole/toEdit.do"; 
	if(edit == 1){
		var selectedData = $("#authorityRoleShowTable").datagrid("getSelected");
		if(selectedData== null || selectedData.id==''){
			$.messager.alert('提示','请选择一行数据！');
			return;
		}
		$("#id").val($("#authorityRoleShowTable").datagrid("getSelected").id);
		toShowUrl= toShowUrl+"?id="+selectedData.id;
	}
	//window.location.href=toShowUrl;
	top.openDialogWithCallback(toShowUrl, top.NewGuid(), '窗体列表 - 编辑',750,350,function(data){
		if(data == true){
			$("#authorityRoleShowTable").datagrid("reload");
		}else{
		return;
		}
	}, true);
}





function removeData(){
	var selectedData = $("#authorityRoleShowTable").datagrid("getSelected");
	if(selectedData.id==''){
		$.messager.alert('提示','请选择一行数据！');
	}
	if("000001"==selectedData.id || "000002"==selectedData.id || "000003"==selectedData.id || "000004"==selectedData.id) {
		$.messager.alert('提示', '你不能这样做！');
		return false;	
	}
	$.messager.confirm('提示', '确定删除?', function(r){
		if (r){
			$.ajax({
			    type:"POST",
			    url:"remove.do",
			    data:{id:selectedData.id},
			    success:function(data){
			       if(data.success){
			     		$('#authorityRoleShowTable').datagrid('reload');
			       }        
			    },
			    complete: function(XMLHttpRequest, textStatus){
			      
			    },
			    error: function(){
			     
			    }         
			 });
		}
	});
	
	
}

$(function(){
	$('#authorityRoleShowTable').datagrid({
		onDblClickRow:function(){
			 $("#id").val($("#authorityRoleShowTable").datagrid("getSelected").id);
			 $('#cc').layout('expand','east');
			 $('#tt').tree({
			        url:'/MenuItemInfo/getReadMenu.do?role='+$("#id").val()
			    });
		}
	});
	 $('#tt').tree({
		onLoadSuccess : function(node, data) {
			/* 展开所有节点 */  
	        $("#tt").tree('expandAll');  
		}
	 });
});



</script>

</head>
<body id="cc" class="easyui-layout">
	
	<div data-options="region:'center',title:'角色列表',fit:true">
	<input type="hidden" id="id" name="id" >
		<table id="authorityRoleShowTable" class="easyui-datagrid" data-options="rownumbers:true,singleSelect:true,url:'query.do',method:'get',toolbar:'#tb_authorityRoleInfoShow'">
        <thead>
            <tr>
              	<th data-options="field:'id',checkbox:true"></th>
                <th data-options="field:'roleCode',width:160,align:'center'">角色编码</th>
                <th data-options="field:'roleName',width:160,align:'center'">角色名称</th>
                <!-- <th data-options="field:'appSystem',width:160,align:'center'">所属应用</th> -->
                <th data-options="field:'remark',width:240" align="center">备注</th>
            </tr>
        </thead>
    </table>
	
	  <div id="tb_authorityRoleInfoShow" style="padding:5px;height:auto">
        <div style="margin-bottom:5px">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showWindow(0);">新增</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showWindow(1);">修改</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeData();">删除</a>
        </div>
    </div>
	

	</div>
	 <div data-options="region:'east',split:true,collapsed:true,title:'菜单权限'"  style="width:300px;padding:5px">
	     	<ul class="easyui-tree" data-options="lines:true" id="tt"></ul>
	</div>


</body>
</html>