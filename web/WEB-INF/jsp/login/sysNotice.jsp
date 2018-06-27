<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.pmcc.soft.core.utils.AppUtils"%>
<%@page import="org.springframework.web.util.WebUtils"%>
<%@page import="com.pmcc.soft.core.common.OnlineUser"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	    <title>中平信通合同管理系统</title>      
	    <script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
	    <!--  附件上传 -->
    	<script type="text/javascript" src="/EasyUiCompoment/upload.js"></script>
    	<script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
	    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"> </script>
	    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>

		<!--     <style>
                  body {
                    background-image: url(/publicresource/image/beijing.png);
                    width:50%;
                    margin: auto;
                    position:relative ;
                    background-repeat: no-repeat;}
	     
	     </style>-->
 	<script type="text/javascript">
	
			function goBack(){
				history.go(-1);
			}
			 $(document).ready(function(){
	        $('#mc').datagrid({
        		onDblClickRow: function(rowIndex, rowData){
        			top.openDialogWithCallback("/newsInfo/detail.do?id="+rowData.id, top.NewGuid(), '窗体列表 - 查看详情',1000,500, null, true);
        			top.openDialogWithCallback("/newsInfo/see.do?id="+rowData.id, top.NewGuid(), '窗体列表 - 查看详情',1000,500, null, true);
     			addTab("公告详情","/newsInfo/see.do?id="+rowData.id);
	                		
					
        			}
       		});
			 });
		</script>
		
	</head>  
	<body border="0" style="margin:0; padding:0;">


	<table class="easyui-datagrid" id="mc"  title="公告列表"
		   data-options="fit:true,checkbox:true,singleSelect:false,pagination:true,url:'/newsInfo/query.do',method:'get'">
		<thead>
		<tr>
			<th data-options="field:'title',width:300">标题</th>
			<th data-options="field:'rptPerson',width:70">发布人</th>
			<th data-options="field:'rptDate',width:150">发布时间</th>

		</tr>
		</thead>
	</table>


<!--	<div  id="Layer1">
	<img src="/publicresource/image/welcome.png"  style="background-size:cover; width:100%; height:100%; filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='/publicresource/image/welcome.png', sizingMethod='scale');
 "/>
	</div>
 	
	

<div style="background:url(/publicresource/image/welcome.png) 0 0 no-repeat; width:100%; height:100%;position="absolve"></div> 
-->
   </body> 



	
</html>