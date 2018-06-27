<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
<script type="text/javascript">
	//关闭窗口
	function goBack(){
	history.go(-1);
	}
	
	  $(document).ready(function(){
		  var roomid=$('#id').val();
		  	$('#ssss').datagrid({
				url : '/noticePerson/showView1.do?id='+roomid
			});
	  });
/* 	/noticePerson/showView.do */
</script>

</head>
<body>
	<!-- 查看详情页面 -->
	<div id="viewWin" class="easyui-window" data-options="title:'查看信息详情'" style="width: 850px; height: 230px;">
	<input type="hidden" id="id" name="id" value="${id }"/>
		<table class="easyui-datagrid" id="ssss"  data-options="checkbox:true,rownumbers:true,fit:true,singleSelect:false,pagination:true,method:'get'">
		   <thead> 
		       <tr>   
		          	<th data-options="field:'id',checkbox:true"></th>
		          	<th data-options="field:'name',align:'center'">收件人姓名</th>
					<!-- <th data-options="field:'createid',align:'center'">收件人姓名</th> -->
					<th data-options="field:'createtime',align:'center'">发送时间</th>
					<th data-options="field:'checktime',align:'center'">查看时间</th>
		       </tr>
		   </thead> 
    	</table>
    	<div style="padding: 5px; text-align: center;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" style="align:'center'" plain="true" onclick="goBack()">关闭</a>
		</div>
	</div>
</body>
</html>