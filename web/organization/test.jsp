<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>DataGrid Complex Toolbar - jQuery EasyUI Demo</title>
    <script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
    <script type="text/javascript">
        function add(){
        	top.openDialogWithCallback("/organization/organization.jsp", top.NewGuid(), '窗体列表 - 新增部门',680,250, function (data){
        		if(data ==true){
        			$('#treegrid').treegrid("reload");
        		}else{
        			return;
        		}
        		
        	}, true);
        }
        function addOrgan(){
        	$.ajax({
	             type:"GET",
	             url:'../organization/toAddOrgan.do',
	             success : function(data){
	            	if(data == "noAdmin"){
	            		$.messager.alert('温馨提示',"您没有这个权限！");
	            		}else if(data == "isAdmin"){
	            			top.openDialogWithCallback("/organization/addOrgan.jsp", top.NewGuid(), '窗体列表 - 新增机构',680,250, function (data){
	            				if(data ==true){
	                    			$('#treegrid').treegrid("reload");
	                    		}else{
	                    			return;
	                    		}
	 
	                    	}, true);
	            			//self.location.href="addOrgan.jsp";
	            		}
	            	}
	         });
        }
        function update(){
        	var oneTest = $("#treegrid").treegrid("getSelected");
        	if(null == oneTest)
        		{$.messager.alert('温馨提示',"请先选中一行记录！"); return;}
        	 // self.location.href="../organization/organizationUpdate.jsp?id="+oneTest.id;
        	  top.openDialogWithCallback("../organization/organizationUpdate.jsp?id="+oneTest.id, top.NewGuid(), '窗体列表 - 修改信息',680,250, function (data){
        			if(data ==true){
            			$('#treegrid').treegrid("reload");
            		}else{
            			return;
            		}
          	}, true);
        }
        /* function deleteData(){
        	var oneTest = $("#treegrid").treegrid("getSelected");
        	if(null == oneTest)
        		{$.messager.alert('温馨提示',"请先选中一行记录！"); return;}
          			$.messager.confirm('温馨提示','是否确认删除？',function(r){
          				if(r){
          					self.location.href="../organization/delete.do?id="+oneTest.id;
          				}
          			});
        } */
        
        
  /*     //点击树查询人员列表
        function clickTree(node){
          $('#allTest').datagrid('load',{
            groupOid: node.id
          });
        } */
        //设置组织机构管理员
        function setUp(){
        	var id = $('#treegrid').datagrid("getSelected");
    		if (id == null || id.length == 0) {
    			$.messager.alert('提示', '请选择所要设置的组织机构');
    			return;
    		}else{
    			 $.ajax({
			             type:"GET",
			             url:'../organization/queryOrganById.do',
			             data : {id : id.id},
			             success : function(data){
			            	 if(data == "true"){
			            		 $('#personList').datagrid('loadData',{total:0,rows:[]});
			            		 $('#sendGroupWin').window('open');
			         			 $('#personTree').tree({    
			         				url: '../organization/queryOrgan.do?sjid='+id.id,
			     	                method: 'post',
			     	                idField: 'id',
			     	                treeField: 'orgCname',onClick:function(node){clickTree(node)}
			         			});  
			            	 }else if(data == "noAdmin"){
			            		 $.messager.alert('提示', '您没有这个权限');
			            	 }else if(data == "isMe"){
			            		 $.messager.alert('提示', '不能更改自己所在的机构');
			            	 }else{
			            		 $.messager.alert('提示', '请选择所要设置的组织机构');
			            	 }
			             }
			         });
    			//self.location.href="../organization/queryOrgan.do?id="+id.id;
    			

    			
    		}
    	}
        
        //保存组织结构管理员
        function saveMember(){
        	var tree = $('#treegrid').datagrid("getSelected");
        	var personId = $('#personList').datagrid("getSelected");
        	if(personId==null || personId.length==0){
                $.messager.alert('温馨提示','请选择要设置的人员');
              } else {
            	  $.ajax({
            		  type:"GET",
			             url:'../organization/saveOrganPerson.do',
			             data : {id : personId.id,oid : tree.id},
			             success:function(data){
			            	 if(data == "true"){
			            		 $.messager.alert('温馨提示',"操作成功！");
			            		 $('#sendGroupWin').window('close');
			            		 self.location.href="../../organization/test.jsp";
			            	 }else{
			            		 $.messager.alert('温馨提示',"操作失败！");
			            	 }
			             }
            	  });
            	  
              }
        }
        //关闭设置组织机构管理员window
        function closeMemberWin(){
        	$('#sendGroupWin').window('close');
        }
        
      //根据组织结构(树节点)查询人员
        function clickTree(node){
          $('#personList').datagrid({
            url:'/personManage/query.do?groupOid=' + node.id,
            method:'get'
          });
        }
      
      
    	$(document).ready(function(){
    	      $('#personTree').tree({
    	    	  onClick: function(node) {
    	        	alert(node.id);
    	          var roomID = rowData.roomID;
    	          $('#personList').datagrid({url:'/ofMucMember/findByRoomId.do?roomID='+roomID,method:'GET'});
    	        }
    	      });
    	    });
        
    	/*  function deleteData(){
         	var oneTest = $("#treegrid").treegrid("getSelected");
         	if(null == oneTest)
         		{$.messager.alert('温馨提示',"请先选中一行记录！"); return;}
           			$.messager.confirm('温馨提示','是否确认删除？',function(r){
           				if(r){
           					self.location.href="../organization/delete.do?id="+oneTest.id;
           				}
           			});
         } */
    	
    	
         //组织机构不能删除
        function deleteData(){
        	var oneTest = $("#treegrid").treegrid("getSelected");
        	if(null == oneTest)
        		{$.messager.alert('温馨提示',"请先选中一行记录！"); return;}
          			$.messager.confirm('温馨提示','是否确认删除？',function(r){
          				if(r){
          					$.ajax({
          			             type:"GET",
          			             url:'../organization/deleteNoOrgan.do',
          			             data : {id : oneTest.id},
          			             success:function(data){
          			            	
          			                 if(data == "success"){
          			                     $.messager.alert('温馨提示','操作成功！');
          			                   	 $("#treegrid").treegrid("reload");
          			                 }else if(data == "false"){
          			                     $.messager.alert('温馨提示','组织机构不可删除！');
          			                 }else{
          			                	 $.messager.alert('温馨提示','操作失败！');
          			                 }
          			             },
          			                //调用出错执行的函数
          			             error: function(){
          			             }         
          			         });
          					//self.location.href="../organization/delete.do?id="+oneTest.id;
          				}
          			});
        } 
        
        
        function formatValue(value){
        	if(""==value)
        		return '';
        	else
        		return value;
        }
        
        
        function search(){
        	 var cName=$("#TextValue").val();
        	 if(cName.trim() == ""){
        		 self.location.href="test.jsp";
        		 
        		
        	 }else{
        		 $('#treegrid').treegrid({
     				url : '../organization/findByName.do?cName=' + cName.trim()
     			});   
        	 }
	  	 
        }
        function setWeChat(){
        	var id = $('#treegrid').datagrid("getSelected");
        	
    		if (id == null || id.length == 0) {
    			$.messager.alert('提示', '请选择所要设置的组织机构');
    			return;
    		}else{
    			 $.ajax({
			             type:"GET",
			             url:'../organization/queryOrganByIdSetWeChat.do',
			             data : {id : id.id},
			             success : function(data){
			            	 if(data.result == "true"){
			            		 top.openDialogWithCallback("../organization/setWeChat.jsp?orgId=" + data.id  +"&&name=" + data.name + "&&uri=" + data.uri + "&&token=" + data.token +"&&appId=" + data.appId, top.NewGuid(), '窗体列表 - 公众微信号',600,150, function (data){
			             			if(data ==true){
			                 			$('#treegrid').treegrid("reload");
			                 		}else{
			                 			return;
			                 		}
			               	}, true);
			            	 }else{
			            		 $.messager.alert('提示', '请选择所要设置的组织机构');
			            	 }
			             }
			         });
    			//self.location.href="../organization/queryOrgan.do?id="+id.id;
    		}
    	}
      </script>
      
</head>
<body class="easyui-layout">
   
    <div  data-options="region:'center'">
    
    <div id="tb" style="padding:5px;height:auto">
        <div style="margin-bottom:5px">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">新增部门</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update()">修改</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"  onclick="deleteData()">删除</a>
            <%--<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="addOrgan()">新增机构</a>--%>
            <%--<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="setUp()">设置机构管理员</a>--%>
            <%--<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="setWeChat()">设置公众微信号</a>--%>
                                        中文名称:
            &nbsp;&nbsp;
	        	<input id="TextValue" name="TextValue" type="text" style="width:200px"/>
	        &nbsp;&nbsp;
	        	<input type="button" value="查询" onclick="search()" />
	        	
	        	
        </div>
    </div>
    
    
      <table title="组织机构" id="treegrid" class="easyui-treegrid" style="height:450px"
            data-options="
                url: '../organization/queryOrgtree.do',
                method: 'post',
                rownumbers: true,
                fit:true,
                idField: 'id',
                treeField: 'orgCname',
                toolbar:'#tb'
            ">
        <thead>
            <tr>
            	<th data-options="field:'id',checkbox:true"  width="300" ></th>
                <th data-options="field:'orgCname'" width="300" >中文名称</th>
                <th data-options="field:'orgEname'" width="150">英文名称</th>
                <th data-options="field:'description'"  formatter="formatValue"  width="150">描述</th>
                <th data-options="field:'orgType'" width="100">机构性质</th>
                <th data-options="field:'rptUnit'" width="100">机构管理员</th>
                <th data-options="field:'manageUnitId'" width="100">排序</th>
				<!-- <th data-options="field:'orgCode'" width="100">代码</th> -->
                <th data-options="field:'remark'" width="100">备注</th>
            </tr>
        </thead>
    </table>
    </div>
    
    
    
<div id="sendGroupWin" class="easyui-window" data-options="closed:true,modal:true,title:'群组成员名单'" style="width:700px;height:400px;">
  <div class="easyui-layout" fit="true">
    	<!-- 左侧组织机构树 -->
	   	<div region="west"  split="true" style="width:200px;overflow:auto;" title="组织机构">
        <table id="personTree" class="easyui-tree" data-options=""></table>
      </div>
	  <div region="center">
          <table class="easyui-datagrid" id="personList" style="height:330px;overflow:auto;"  title="人员列表"  data-options="singleSelect:true,pagination:true,rownumbers:true">
            <thead>
            <tr>
              <th data-options="field:'id',checkbox:true"></th>
              <th data-options="field:'userCname',width:80">用户名称</th>
              <th data-options="field:'department',width:120">所属部门</th>
              <th data-options="field:'duty',width:120">职务</th>
              <th data-options="field:'personUnitname',width:120">所属部门</th>
              <th data-options="field:'telephone',width:120">电话</th>
            </tr>
            </thead>
          </table>
      </div>
	  <div region="south" border="false" style="text-align:right;height:30px;line-height:25px;">
        <div align="center" >
          <a class="easyui-linkbutton"  icon="icon-ok" onclick="saveMember()">确定</a>
          &nbsp;&nbsp;&nbsp;&nbsp;
          <a class="easyui-linkbutton" icon="icon-cancel" onclick="closeMemberWin()">关闭</a>
        </div>
      </div>
  </div>
</div>
     
</body>
</html>