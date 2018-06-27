<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="/publicresource/btn.css">
<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>

<script type="text/javascript">


   function clearWindow(){
	   $('#user').val('');
	   $('#roleItems').val('');
	   $('#dg_personshow').datagrid('clearSelections');
   }
   

	function clickTree(node) {
		$("#organId").val(node.id);
		 showItems();
	}
	
	function showWindow(id,userCname){
		$("#user").val(id);
		$('#show_user_window').window('open');
		$('#show_user_window').window('setTitle','用户授权>'+userCname);
		$('#systemEditTree').tree({
			onBeforeLoad:function(node, param){
				if($('#user').val()!=''){
					param.person=$('#user').val();
					
				}
				
			}
		});
	}
	function showItems(){
		var vorg=$("#organId").val();
		if(vorg!=''){
			$('#dg_personshow').datagrid('load', {
				organId : vorg
			});
		}

	}
	
	
	function doSave(){
		$.messager.confirm('提示', '确定授权?', function(r){
			if (r){
				var roleIds= [];
				var aroleItems =$("#systemEditTree").tree("getChecked");
				$.each(aroleItems,function(i,e){
					roleIds.push(aroleItems[i].id);
				});
				
				$('#roleItems').val(roleIds);
					   $.ajax({
				           type:"POST",
				           url:"save.do",
				           data:{
				        	   user:$('#user').val(),
				        	   roleItems:$('#roleItems').val(),
				        	   },
				           success:function(data){
				              if(data.success){
				            		$.messager.alert('提示','提交成功');
				            		$('#show_user_window').window('close');
				            		clearWindow();
				            		$('#dg_personshow').datagrid('reload');
				              }        
				           },
				           complete: function(XMLHttpRequest, textStatus){
				           },
				           error: function(){
				           }         
				        });
			}
		})
		
	}
	
    function showOP(row){
    	 return  "<span><a href='#' onclick='showWindow(\""+row.id+"\",\""+row.userCname+"\");'>授权</span>";
    }
	
	$(function() {		

		$('#show_user_window').window({
			onClose: function(){
			          clearWindow();
					}
			});
	});
</script>


</head>
<body id="div_show_01" class="easyui-layout">
	<input  id="user" >12
	<input type="hidden" id="roleItems" >
	<input type="hidden" id="organId" >
	<input type="hidden" id="type" name="type" value="${type}" >
			<div data-options="region:'west',split:true,title:'组织机构'"style="width: 200px; padding: 10px;">
				<ul id="tree_orgization" class="easyui-tree" data-options=" url: '/organization/queryOrgCombtree.do',
	                method: 'post',
	                onClick:function(node){clickTree(node)}"></ul>
			</div>

			<div data-options="region:'center'" style="width:auto;">
				<table id="dg_personshow" class="easyui-datagrid" data-options="title:'人员列表',rownumbers:true,pagination:true,fit:true,singleSelect:true,url:'/personManage/findByOrg.do',method:'get'">
			        <thead>
			            <tr>
			              	<th data-options="field:'id',checkbox:true"></th>
			                <th data-options="field:'userCname',width:120,align:'center'">用户名称</th>
			                <th data-options="field:'userEname',width:120,align:'center'">用户英文</th>
			                <th data-options="field:'department',width:120">所属部门</th>
                			<th data-options="field:'telephone',width:100">电话</th>
			                <th data-options="field:'power',width:200,align:'center'">权限</th>
			                <th data-options="field:'duty',width:100,align:'center'">部门岗位</th>
			                <th data-options="field:'op',width:120,align:'center',formatter:function(value, row, index){return showOP(row);}"></th>
			            </tr>
			        </thead>
				</table>
			</div>
		</div>





	 <div id="show_user_window" class="easyui-window" title="用户权限" data-options="modal:true,closed:true" style="width:500px;height:400px;padding: 10px;">
	 	
	 	
	 	<div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'center'" style="padding:10px;">
              <ul id="systemEditTree" class="easyui-tree" data-options="url:'/authorityUser/systemEdit.do',method: 'post',checkbox:true,onlyLeafCheck:true"></ul>
            </div>
            <div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;">
                <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="doSave();" style="width:80px">保存</a>
                <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="$('#show_user_window').window('close');" style="width:80px">关闭</a>
            </div>
        </div>
	 	
	 	
	</div>
  	       
</body>
</html>