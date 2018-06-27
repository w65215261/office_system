<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>试题</title>
<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
<script type="text/javascript" src="/EasyUiCompoment/commons.js"></script>
<script type="text/javascript">
/* $(document).ready(function(){
	$('#funitOid').combotree({
		url:'../organization/addOrgan.do',method:'post',required:true,
		onClick: function(node){
			$.ajax({
	 			type : "GET",
	 			url : "../organization/selectSorting.do",
	 			data: {organId : node.id},
	 			success : function(data){
	 				if(data.manageUnitId != undefined){
	 					document.getElementById("manageUnitId").value=data.manageUnitId;
	 					document.getElementById("sort").value=data.manageUnitId;
	 				}else{
	 					document.getElementById("manageUnitId").value=1;
	 					document.getElementById("sort").value=1;
	 				}
	 			},
	 	        error: function (msg) {
	 	            alert(msg);
	 	        }
	 		});
		}
	});

});
 */


     

 function goBack(){
		window.returnVal=false;
		OpenClose();
	}



	function doSave(){
		 var flag = $('#form1').form('validate');
		 var name = $('#name').val();//公众号
		 var uri = $('#uri').val();//地址
		 var token = $('#token').val();//token
		 var appId = $('#appId').val();//appId
		/*  var orgShortCname = $('#orgShortCname').val();//机构中文简称
		 var orgShortEname = $('#orgShortEname').val();//机构英文简称
		 var orgUnit = $('#orgUnit').val(); */
		 
		if (name,uri,token,appId != null && "" != name,uri,token,appId) {
	    if(flag){
	   	 
	        $.ajax({
	            type:"POST",
	            url:'../organization/saveWeChat.do',
	            data : $('#form1').serialize(),
	            success:function(data){
	           	
	                if(data == "success"){
	                    $.messager.alert('温馨提示','操作成功！');
	                	window.returnVal=true;
	                	OpenClose();
	                }else{
	                    $.messager.alert('温馨提示','操作失败！');
	                }
	            },
	               //调用出错执行的函数
	            error: function(){
	            }         
	        });
	   }
	        //obj.submit();
	    }else{
	        $.messager.alert('温馨提示',"选项均为必输项，请填写完整！");
	    }
	}
</script>   
</head>

<body>
    <div data-options="region:'center'" style="overflow: hidden;">
    <form  name="form1" id="form1">
<input type="hidden" id="orgId" name="orgId"  value="<%=request.getParameter("orgId")%>"/>
  <table>
  <tr>
  <td><label for="testContent">公众号:</label></td>
  <td><input class="easyui-validatebox" type="text" style="width:200px" id='name' name="name" data-options="required:true" value="<%=request.getParameter("name")%>"></input></td>
  <td  style="vertical-align:middle; text-align:right;"><label for="testContent">地址:</label></td>
  <td ><input class="easyui-validatebox" type="text" style="width:200px" name="uri" id="uri"     data-options="required:true" value="<%=request.getParameter("uri")%>"></input></td>
  </tr>
  <tr>
  <td><label for="testContent">Token:</label></td>
  <td><input class="easyui-validatebox" type="text" style="width:200px" id="token" name="token"   data-options="required:true" value="<%=request.getParameter("token")%>"></input></td>
  <td  style="vertical-align:middle; text-align:right;"><label for="testContent">AppId:</label></td>
  <td><input class="easyui-validatebox" type="text" style="width:200px" id="appId" name="appId"    data-options="required:true" value="<%=request.getParameter("appId")%>"></input></td>
  </tr>
  <tr><td colspan="4">&nbsp;</td></tr>
 <tr>
 	<td colspan="4" align="center">
		<div align="center">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="doSave();" style="width:80px">保存</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="goBack()" style="width:80px">返回</a>
    	</div>       
 	</td>
 </tr>
</table>         
</form>
</div>
	    
            
       
</body>
</html>