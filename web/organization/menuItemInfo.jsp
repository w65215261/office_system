<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.pmcc.soft.core.utils.UUIDGenerator"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>菜单管理</title>
<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
<script type="text/javascript" charset="utf-8" src="/EasyUiCompoment/commons.js"></script>
<script type="text/javascript">
    //新增一个菜单
    function addMenu(){
    	var one_menu = $("#menuList").treegrid("getSelected");
    	if(one_menu==null){
    		$.messager.alert('温馨提示',"请先选择要新增的菜单父级");
    	}else{
    		$('#menuFrom').form('clear');
    	//放置父级id
    	document.getElementById('parentOid').value=one_menu.id;
    	$('#openMenu').window('open');
    	}
    } 
    function saveWindow(){
    	var menuEName = $('#menuEName').val();
    	var menuCName = $('#menuCname').val();
    	var re = new RegExp("^\\w+$");/*^([a-zA-Z]*?)((?:[0-9]+(?:[0-9]|[a-zA-Z])*)*)$ */
    	var reg = new RegExp("^[\u4e00-\u9fa5]{1,20}$");
    	if (re.test(menuEName) && reg.test(menuCName)) {
    		$('#menuFrom').form('submit',{  
                url:'../MenuItemInfo/save.do',  
                onSubmit:function(){  
                    return $(this).form('validate');  
                },  
                success:function(data){
                	$('#menuFrom').form('clear');
                	$('#openMenu').window('close');
                    $("#menuList").treegrid('reload',{});
                }  
            });  
    	}
    	else {
    		$.messager.alert('温馨提示',"请输入正确的格式!"); 
    		}
    }
    
    function updateMenu(){
    	var one_menu = $("#menuList").treegrid("getSelected");
    	if(one_menu==null){
    		$.messager.alert('温馨提示',"请先选择要更新的菜单");        		
        	}else{
        	$('#openMenu').window('open');
        	$('#menuFrom').form('load', '../MenuItemInfo/find.do?id='+one_menu.id);   
        	}
    }
    function del(){
    	var one_menu = $("#menuList").treegrid("getSelected");
    	if(one_menu==null){
    		$.messager.alert('温馨提示',"请先选择要删除的菜单");        		
        	}else{
        		$.messager.confirm("温馨提示", "确定删除？", function (r) {  
    		        if (r) {  
    		        	location.href="../MenuItemInfo/delete.do?id="+one_menu.id;
    		        }  
    		    });  
    		    return false;
        	}
    	
    }
    function goBack(){
    	$('#openMenu').window('close');
	 }
       
      </script>
</head>
<body class="easyui-layout" data-options="fit:true">
  
    <div  data-options="region:'center'">
    <table class="easyui-treegrid" id="menuList"  title="菜单列表" style="height:500px;"
            data-options="idField:'id',method: 'get',fit:true,
            treeField:'menuCname',url:'../MenuItemInfo/query.do',toolbar:'#tb'">
        <thead>
            <tr>
          	    <th data-options="field:'id',checkbox:true"></th>
                <th data-options="field:'menuCname',width:160">菜单中文名</th>
                <th data-options="field:'menuOrder',width:160">排序</th>
                <th data-options="field:'menuUrl',width:260">菜单URL</th>
                <th data-options="field:'isShowMsg',width:160">是否显示</th>
            </tr>
        </thead>
    </table>

    <div id="tb" style="padding:5px;height:auto">
        <div style="margin-bottom:5px">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"onclick="addMenu()">新增</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"onclick="updateMenu()">修改</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
        </div>
    </div>
    
    <div id="openMenu" class="easyui-window" data-options="closed:true,modal:true,title:'菜单管理'" style="width:400px;height:400px;margin-left:40px;">
    <form  method="post" novalidate  id="menuFrom">
    <input type="hidden" value="" name="parentOid" id="parentOid"> 
    <input type="hidden" value="" name="id" id="id"> 
      <table cellpadding="5" border="0">
     <tr>
    <td style="text-align:right;">
    <label for="menuEName" >菜单英文名:</label></td>
    <td >
    <input style="width:200px" id="menuEName" name="menuEName" class="easyui-validatebox"  missingMessage="该项为必须输入项"  required="required"></input>
  	</td>
  	</tr>
  	<tr>
  <td style="text-align:right;">
    <label for="menuCname">菜单中文名:</label></td><td>
    <input style="width:200px" id="menuCname" name="menuCname" class="easyui-validatebox"  missingMessage="该项为必须输入项"  required="required"></input>
  </td>
  </tr>
  <tr>
  <td style="text-align:right;">
    <label for="menuUrl">菜单URL:</label></td><td>
    <input style="width:200px" id="menuUrl" name="menuUrl" class="easyui-validatebox"></input>
  </td>
  </tr>
  <tr>
  <td style="text-align:right;">
  
   <label for="menuType">菜单类型:</label></td><td>
   <select style="width:200px" id="menuType" class="easyui-combobox" name="menuType" style="width:130px;" data-options="required:true">
			<option value="0">一级菜单</option>
		    <option value="1">二级菜单</option>
		    <option value="2">三级菜单</option>
			</select>
  </td>
  </tr>
   <tr>
  <td style="text-align:right;">
   <label for="isShow">是否显示:</label></td><td>
   <select style="width:200px" id="isShow" class="easyui-combobox" name="isShow" style="width:130px;" missingMessage="该项为必须输入项" data-options="required:true">
		<option value="0">是</option>
		<option value="1">否</option>
		</select>
  </td>
  </tr>
   <tr>
  <td style="text-align:right;">
   <label for="menuOrder" >排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序:</label></td>
   <td>
    <input style="width:200px" id="menuOrder" name="menuOrder" class="easyui-numberbox"  missingMessage="该项为必须输入项" min="0" precision="0" required="required"></input>
  </td>
  </tr>
    <tr>
  <td style="text-align:right;">
   <label for="menuIcon">图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;标:</label></td><td>
   <select style="width:200px" id="menuIcon" class="easyui-combobox" name="menuIcon" style="width:130px;" missingMessage="该项为必须输入项" data-options="required:true">
		<option value="icon-caidanyiji">souji</option>
		<option value="icon-caidanerji">shouji1</option>
		<option value="icon-caidansanji">shouji2</option>
		</select>
  </td>
  </tr>
    <tr>
  <td style="text-align:right;">
   <label for="remark">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:</label></td><td>
    <input style="width:200px" id="remark" name="remark" class="easyui-validatebox" ></input>
  </td>
  </tr>

 </table>

  <div style="text-align:center;padding:5px">
  <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-ok" onclick="saveWindow()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;	        
<a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-cancel" onclick="goBack()" >返回</a>
</div>
    
    </form>
    </div>
	 </div>
</body>
</html>