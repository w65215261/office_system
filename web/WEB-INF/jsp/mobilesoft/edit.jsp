<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
<link rel="stylesheet" type="text/css" href="/publicresource/btn.css">
<script type="text/javascript" src="/EasyUiCompoment/commons.js"></script>
<script type="text/javascript">
$.extend($.fn.validatebox.defaults.rules, {
    //验证汉子
    CHS: {
        validator: function (value) {
            return /^([0-9.]+)$/.test(value);
        },
        message: '只能输入数字'
    }
}) 

function saveWindow(){
    var flag = $('#roomForm').form('validate');
	if(flag){
		     $("#roomForm").submit(function()//提交表单
					    {
					            var options = {
					                url:'/mobilesoft/save.do', //提交给哪个执行
					                type:'POST',
					                success: function(msg){
					                	if(msg == "success"){
					                		window.returnVal = true;
					            	         OpenClose();    
					                	}
					                } //显示操作提示
					            };
					            $("#roomForm").ajaxSubmit(options);
					          
					            return false; //为了不刷新页面,返回false，反正都已经在后台执行完了，没事！
					           
					    });
		      $("#roomForm").submit();	
		}
	
}
$(function() {
	var uu=$('#versionid').val();
});
	function back() {
	window.returnVal=false;
	OpenClose();
	}
	function formatterdate(val, row) {
	 	if (val != null) {
			var date = new Date(val);
			return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
					+ date.getDate() + ' ' + date.getHours() + ':'
					+ date.getMinutes() + ':' + date.getSeconds();
		} 
		/* val=parseInt(val,10);//转为整形
		var date =new Date(val);//正确
		return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
		+ date.getDate() + ' ' + date.getHours() + ':'
		+ date.getMinutes() + ':' + date.getSeconds(); */
	}
</script>
<style type="text/css">
.file-box{ position:relative;width:340px}
.txt{ height:22px; border:1px solid #cdcdcd; width:180px;}
.btn{ background-color:#FFF; border:1px solid #CDCDCD;height:24px; width:70px;}
.file{ position:absolute; top:0; right:80px; height:24px; filter:alpha(opacity:0);opacity: 0;width:260px }
</style>
</head>
<body class="easyui-layout" data-options="fit:true">

    <form id="roomForm"  enctype="multipart/form-data"   method="post" action="/mobilesoft/save.do">
    <input type="hidden" id="versionid" name="versionid" value="${dlsoftversion.versionid}">
 <table align="center" id="tzsq" >  
            <tr>  
                <td width="40%" style="text-align:right;">  
                   编码：  
                </td>  
                <td width="60%" >  
                    <input class="easyui-validatebox" required="true"  validType="CHS"  name="versioncode" type="text" id="BM" value="${dlsoftversion.versioncode}" > 
                </td>  
            </tr> 
            <tr>  
                <td width="40%" style="text-align:right;">  
                    名称：  
                </td>  
                <td width="60%" >  
                    <input class="easyui-validatebox" required="true"  class="easyui-validatebox"   name="versionname" id="MC" value="${dlsoftversion.versionname}">  
                </td>  
            </tr> 
            <tr>  
                <td width="40%" style="text-align:right;">  
                  备注：  
                </td>  
                <td width="60%">  
                    <input class="easyui-validatebox" required="true"  class="easyui-validatebox"  name="versiondescription" id="YY" value="${dlsoftversion.versiondescription}">   
                </td>  
            </tr>  
             <tr>  
         <td width="40%" style="text-align:right;">
         上传：  </td>
           <td width="60%">  <div class="file-box"> 
						<input type='text' name='textfield' id='textfield' class='txt' value="${dlsoftversion.downloadurl}"/> 
						<input type='button' class='btn' value='浏览...' /> 
						<input type="file" name="file" class="file" id="file" size="28" onchange="document.getElementById('textfield').value=this.value" /> 	
					</div> 
             </td>
                 </tr>  
                 <tr>
                 	<td colspan="2">
					<div style="padding-left:110px;margin-top:10px;" >
						<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="saveWindow()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;	
			            <a href="#" class="easyui-linkbutton" icon="icon-cancel" onclick="back()" >返回</a>
					</div>
					</td>
				</tr>
        </table>  
<!-- 					<div colspan="4" align="center"> -->
<!-- 					<input type="button"  class="easyui-linkbutton"  value="保存" onclick="saveWindow();"  />&nbsp;&nbsp;  -->
<!-- 					<input type="button"  class="easyui-linkbutton"  value="重置" onclick="clearWindow();" />&nbsp;&nbsp;  -->
<!-- 					<input type="button"   class="easyui-linkbutton" value="返回" onclick="back();" />&nbsp;&nbsp;   -->
<!-- 					</div> -->
        </form>

</body>
</html>