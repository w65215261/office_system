<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
<script type="text/javascript">
        function add(){
        	top.openDialogWithCallback("../personManage/addPerson.jsp", top.NewGuid(), '窗体列表 - 查看详情',750,345, function(data){
        		if(data == true){
        			$('#allTest').datagrid({
                		url:'../personManage/query.do?groupOid='+$('#nodeidd').val()
                	});	
        		}else{
        			return ;
        		}
        	}, true);
        	//self.location.href="../personManage/addPerson.jsp";
        }
        //修改人员信息
        function update(){
        	var oneTest = $("#allTest").datagrid("getSelections");
        	if(oneTest==null ||  oneTest.length != 1){
        		$.messager.alert('温馨提示',"请选择一条人员信息");
        	}else{
        		var one = oneTest[0];
        		//self.location.href="updatePerson.jsp?id="+one.id;
        		top.openDialogWithCallback("../personManage/updatePerson.jsp?id="+one.id, top.NewGuid(), '窗体列表 - 查看详情',750,280, function(data){
        			if(data == true){
            			$('#allTest').datagrid({
                    		url:'../personManage/query.do?groupOid='+$('#nodeidd').val()
                    	});	
            		}else{
            			return ;
            		}
            	}, true);
        	}
        }
        //删除人员信息
        function del(){
        	var oneTest = $("#allTest").datagrid("getSelections");
        	if(oneTest=='' || oneTest.length == 0){
        		$.messager.alert('温馨提示',"请选择至少一条人员信息");
        	}else{
        	//self.location.href="../personManage/delete.do?id="+oneTest.id;
        	var ids = "";
        	for(var i = 0; i < oneTest.length; i++){
        		ids += ',' + oneTest[i].id;
        	}
    		$.messager.confirm('温馨提示','是否确认删除？',function(r){
        		if(r){
        			// 重置
        			$.ajax({
    					type:"POST",
    					url:'../personManage/delete.do',
    					data : {
    						param : ids
    					},
    					success:function(data){
    						if(data == "success"){
    							$.messager.alert('温馨提示','删除成功！');
    							/* TreeView v;
    							var a = v.SelectedNode.Text; */
    							$("#allTest").datagrid("reload");
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
        var nodeId;
        //点击树查询人员列表
        function clickTree(node){
        	/* $('#allTest').datagrid('load',{
        		groupOid: node.id,
        	}); */
        	nodeId = node.id;
        	$('#nodeidd').val(nodeId);
        	$('#allTest').datagrid({
        		url:'../personManage/query.do?groupOid='+node.id
        	});

        }
        //密码重置
        function passwordReset(){
        	var oneTest = $("#allTest").datagrid("getSelections");
        	if(oneTest==null ||  oneTest.length == 0){
        		$.messager.alert('温馨提示',"请选择至少一条人员信息");
        	}else{
        		var ids = "";
        		var usernames = "";
            	for(var i = 0; i < oneTest.length; i++){
            		ids += oneTest[i].id + ',';
            		usernames += oneTest[i].userEname + ',';
            	}
        		$.messager.confirm('温馨提示','是否确认重置密为123456？',function(r){
	        		if(r){
	        			// 重置
	        			$.ajax({
	    					type:"GET",
	    					url:'../personManage/passwordReset.do',
	    					data : {
	    						param : ids,
	    						ename : usernames
	    					},
	    					success:function(data){
	    						if(data == "success"){
	    							$.messager.alert('温馨提示','密码重置成功！');
	    							$("#allTest").datagrid("clearSelections");
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
        
        function search(){
       	 var cName=$("#TextValue").val();
       	 if(nodeId == "" || typeof(nodeId) == "undefined"){
       		$.messager.alert('温馨提示',"请选择左侧组织机构或部门");
       	 }else{
       		$('#allTest').datagrid('reload',{groupOid:nodeId,userCname:cName});
		 }
       
	  	  /* $('#allTest').datagrid({
				url : '../personManage/findByName.do?cName=' + cName
			});   
        */}
      </script>
</head>
<body class="easyui-layout">
<input type="hidden" id="nodeidd" name="nodeidd">
<div  data-options="region:'west',split:true" style="width:200px;" title="组织机构" >
  <table id="sss"
          class="easyui-tree" data-options=" url: '../organization/queryOrgCombtree.do',
                method: 'post',
                idField: 'id',
                treeField: 'orgCname',onClick:function(node){clickTree(node)}">
    </table>
    </div>
    <div  data-options="region:'center'">
    <table class="easyui-datagrid" id="allTest"  title="人员管理" style="height:450px;"
            data-options="checkbox:true,rownumbers:true,fit:true,singleSelect:false,pagination:true,url:'',method:'post',toolbar:'#tb'">
        <thead>
        
            <tr>
          	    <th data-options="field:'id',checkbox:true"></th>
                <th data-options="field:'userEname',width:80">用户名</th>
                <th data-options="field:'userCname',width:80">中文名</th>
                <th data-options="field:'userDescription',width:80">用户描述</th>
                <th data-options="field:'sexShow',width:80">性别</th>
                <th data-options="field:'power',width:80">权限</th>
                <th data-options="field:'personUnitname',width:120">所属部门</th>
                <th data-options="field:'telephone',width:120">电话</th>
                <th data-options="field:'duty',width:120">职务</th>
                <!-- <th data-options="field:'createPersonShow',width:80">创建人</th> -->
                <th data-options="field:'sorting',width:80">排序</th>
                <!-- <th data-options="field:'remark',width:200">备注</th> -->
                <th data-options="field:'officephone',width:150">办公电话</th>
                <th data-options="field:'userMail',width:150">邮箱</th>
            </tr>
        </thead>
    </table>

    <div id="tb" style="padding:5px;height:auto">
        <div style="margin-bottom:5px">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">新增</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"onclick="update()">修改</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="passwordReset()">密码重置</a>
                              中文名称:
            &nbsp;&nbsp;
	        <input id="TextValue" id="TextValue" type="text" style="width:200px"/>
	        &nbsp;&nbsp;
	        <input type="button" value="查询" onclick="search()" />
        </div>
    </div>
    </div>
</body>
</html>