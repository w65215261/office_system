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
$(document).ready(function(){
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


function goBack(){
	window.returnVal=false;
	OpenClose();
}

/* function doSave(){
	 var orgEname = $('#orgEname').val();//机构英文名
	 var orgCname = $('#orgCname').val();//机构中文文名
	 var orgShortCname = $('#orgShortCname').val();//机构中文简称
	 var orgShortEname = $('#orgShortEname').val();//机构英文简称
	 var description = $('#description').val();//描述
	 var orgCode = $('#orgCode').val();//机构编码
	 var remark = $('#remark').val;//备注
	 var re = new RegExp("^([a-zA-Z]*?)((?:[0-9]+(?:[0-9]|[a-zA-Z])*)*)$");
 	 var reg = new RegExp("^[\u4e00-\u9fa5]{1,20}$");
 	 
 	 var funitOid = $('#funitOid').tree();
 	 alert(funitOid.id);
 	if (re.test(orgEname) && re.test(orgShortEname) && reg.test(orgShortCname) && reg.test(orgCname)) {
    	 
         $.ajax({
             type:"POST",
             url:'../organization/saveOrgan.do',
             data : {remark : remark,orgCode : orgCode,orgEname : orgEname ,orgCname : orgCname,description : description, orgShortCname : orgShortCname,orgShortEname : orgShortEname},
             success:function(data){
            	
                 if(data == "success"){
                     $.messager.alert('温馨提示','操作成功！');
                    	goBack();
                 }else{
                     $.messager.alert('温馨提示','操作失败！');
                 }
             },
                //调用出错执行的函数
             error: function(){
             }         
         });
         //obj.submit();
     }else{
         $.messager.alert('温馨提示',"请按正确格式将信息填写完整！");
     }
} */

function doSave(){
	 var flag = $('#form1').form('validate');
	 var orgEname = $('#orgEname').val();//机构英文名
	 var orgCname = $('#orgCname').val();//机构中文文名
	/*  var orgShortCname = $('#orgShortCname').val();//机构中文简称
	 var orgShortEname = $('#orgShortEname').val();//机构英文简称
	 var orgUnit = $('#orgUnit').val(); */
	 var re = new RegExp("^([a-zA-Z]*?)((?:[0-9]+(?:[0-9]|[a-zA-Z])*)*)$");
	 var reg = new RegExp("^[\u4e00-\u9fa5]{1,20}$");
	if (re.test(orgEname) && reg.test(orgCname)) {
    if(flag){
   	 
        $.ajax({
            type:"POST",
            url:'../organization/save.do',
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
        $.messager.alert('温馨提示',"请按正确格式将信息填写完整！");
    }
}
     

// 机构编码唯一性验证
function checkOrgCode(val){
	
	if(val != "" && val != null){
		$.ajax({
	        type:"POST",
	        url:'../organization/checkOrgCode.do',
	        data : {
	        	orgCode : val
	        },
	        success:function(data){
	            if(data == "yes"){
	                $.messager.alert('温馨提示','机构编码已存在，请重新输入！');
	                $('#orgCode').val("");
	            }
	        },
	           //调用出错执行的函数
	        error: function(){
	        }         
	    });
	}
}
</script>   
</head>

<body>
    <div data-options="region:'center'" style="overflow: hidden;">
    <form  name="form1" id="form1">
    <input type="hidden" name="sort" id="sort">
         
  <table>
  <tr>
  	<td style="vertical-align:middle; text-align:right;">父机构名称:</td>
    <td colspan="3"> <input class="easyui-combotree" data-options="url:'../organization/addOrgan.do',method:'post',required:true" style="width:200px" id="funitOid" name="funitOid"  missingMessage="该项为必须输入项"></input></td>
<!--   <td >父机构名称:</td> -->
<!--     <td colspan="3"> <input class="easyui-combotree" data-options="" style="width:200px" id="funitOid" name="funitOid"  missingMessage="该项为必须输入项"></input>             </td> -->
  </tr>
  <tr>
  <input id="orgUnit" name="orgType" type="hidden" value = "1"></input>
  <td width="20%" style="vertical-align:middle; text-align:right;"><label for="testContent">英文名:</label></td>
  <td width="30%"><input class="easyui-validatebox" type="text" style="width:200px" id="orgEname" name="orgEname" missingMessage="该项为必须输入项" data-options="required:true,validType:'english'"></input></td>
  <td width="20%" style="vertical-align:middle; text-align:right;"><label for="testContent">机构英文简称:</label></td>
  <td width="40%"><input class="easyui-validatebox" type="text" style="width:200px" id="orgShortEname" name="orgShortEname" data-options="required:false"></input></td>
   </tr>
  
  <tr>
  <td width="20%" style="vertical-align:middle; text-align:right;"><label for="testContent">中文名:</label></td>
  <td width="40%"><input class="easyui-validatebox" type="text" style="width:200px" id="orgCname" name="orgCname" missingMessage="该项为必须输入项" data-options="required:true,validType:'CHS'"></input></td>
  <td width="20%" style="vertical-align:middle; text-align:right;"><label for="testContent">机构中文简称:</label></td>
  <td width="30%"><input class="easyui-validatebox" type="text" style="width:200px" id="orgShortCname" name="orgShortCname"  data-options="required:false"></input></td>
  </tr>
  <tr>
  <td width="20%" style="vertical-align:middle; text-align:right;"><label for="testContent">机构描述:</label></td>
  <td width="30%"><input class="easyui-validatebox" type="text" style="width:200px" id='description' name="description"  data-options="required:false"></input></td>
  <td width="20%" style="vertical-align:middle; text-align:right;"><label for="testContent">机构编码:</label></td>
  <td width="30%"><input class="easyui-validatebox" type="text" style="width:200px" name="orgCode" id="orgCode"  data-options="required:false" onblur="checkOrgCode(this.value)"></input></td>
  </tr>
  <tr>
  <td width="20%" style="vertical-align:middle; text-align:right;"><label for="testContent">排序:</label></td>
  <td width="30%"><input class="easyui-validatebox" type="text" style="width:200px" id="manageUnitId" name="manageUnitId"></input></td>
  <td width="20%" style="vertical-align:middle; text-align:right;"><label for="testContent">备注:</label></td>
  <td width="30%"><input class="easyui-validatebox" type="text" style="width:200px" id="remark" name="remark"></input></td>
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