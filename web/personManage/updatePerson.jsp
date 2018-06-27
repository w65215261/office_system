<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.pmcc.soft.core.utils.AppUtils"%>
<%@page import="org.springframework.web.util.WebUtils"%>
<%@page import="com.pmcc.soft.core.common.OnlineUser"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改考生信息</title>
<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
<script type="text/javascript" src="/EasyUiCompoment/commons.js"></script>
<script type="text/javascript">
  //获得参数的方法
    var request =
    {
    QueryString : function(val)
    {
    var uri = window.location.search;
    var re = new RegExp("" +val+ "=([^&?]*)", "ig");
    return ((uri.match(re))?(uri.match(re)[0].substr(val.length+1)):null);
    }
    }
    var sid = request.QueryString("id");
  function loadFrom(){
	  $('#updatePersonFrom').form('load', '../personManage/find.do?id='+sid);
	  $.getJSON(
			    'find.do?id='+sid,
			    function(data) {
			      var userEname = data.userEname;
			      $('#hiddenName').val(userEname);
			    }
		);
	  
  }
  
  function goBack(){
	  window.returnVal=false;
	   OpenClose();
  }
  $(document).ready(function () {
	  $("#pcombotree").combotree({
		     onChange:function(){
		         $("#puid").val($('#pcombotree').combobox('getValue'));
		     }    
		     });
	  });
  

			  
	// 表单提交数据校验
	function formSubmit(obj){
		var flag = $('#updatePersonFrom').form('validate');
		var sorting = document.getElementById("sorting").value;
		if(flag){
			  $("#updatePersonFrom").submit(function()//提交表单
					    {
					            var options = {
					                url:'../personManage/update.do', //提交给哪个执行
					                type:'POST',
					                data : {sorting : sorting},
					                success: function(msg){
					                	if(msg == "success"){
					                		 window.returnVal = true;
						                     OpenClose();
					                	}
					                } //显示操作提示
					            };
					            $('#updatePersonFrom').ajaxSubmit(options);
					           
					            return false; //为了不刷新页面,返回false，反正都已经在后台执行完了，没事！

					    });
				$('#updatePersonFrom').submit();
		}else{
			$.messager.alert('温馨提示','请按正确格式将信息填写完整！');
		}
	}	
		
	// 设置日期框不可编辑
	$(function(){
		$(".datebox :text").attr("readonly","readonly"); 
	});
	
	//检验用户英文名是否重复
	function isRepetition(){
		var inputUserEname = $('#userEname').val();
		var hiddenName = $('#hiddenName').val();
		//alert(hiddenName);
		//alert(inputUserEname);
		//alert(hiddenName);
		if(inputUserEname != hiddenName){
			//发送请求，查询已存在数据
			$.ajax({
				type : "POST",
				url : 'checkUserEname.do',
				data : {
					'inputUserEname' : inputUserEname,
				}, 
				success : function(data) {
					if(data == "fail"){
						$.messager.alert('温馨提示', '所填用户英文名已存在，请重新填写！');
						$('#userEname').val('');
					}else{
						//window.location.href = data.loginUrl;
					}
				},
			});
		}
		
	}
  
      </script>
</head>

<body onload="loadFrom()">

    <div  title="修改人员信息" style="width:100%;fit:true;">
    <form action="../personManage/update.do" method="post" novalidate  id="updatePersonFrom">
    <input type="hidden" name="id">
    <input type="hidden"  name="personUintId" id="puid">
	<table>
		<tr>
			<td width="20%" style="text-align:right">用户名:</td>
			<td width="30%">
				<input class="easyui-validatebox" style="width:70%" data-options="required:true,validType:'english'" name="userEname" id="userEname" onblur="isRepetition()" readonly >
    			<input type="hidden" class="easyui-validatebox" style="width:70%;" data-options="required:true," id="hiddenName">
			</td>
			<td width="20%" style="text-align:right">中文名:</td>
			<td width="30%"><input class="easyui-validatebox" style="width:70%" data-options="required:true,validType:'CHS'" name="userCname"></td>
			<td width="20%" style="text-align:right">所属机构:</td>
			<td width="30%">
			 <input class="easyui-combotree" data-options="url:'../organization/queryOrgCombtree.do',method:'post',required:true" style="width:120px" name="personUnitname"  missingMessage="该项为必须输入项" id="pcombotree"></input>  
			<!-- <input class="easyui-validatebox" data-options="required:true" style="width:70%" name="personUnitname"  missingMessage="该项为必须输入项" disabled="disabled"></input>  -->
            </td>           
		</tr>
		<tr>
			<td width="20%" style="text-align:right">用户职务:</td>
			<td width="30%"><input class="easyui-validatebox" style="width:70%" data-options="required:false" name="duty"></td>
			<td width="20%" style="text-align:right">用户编码:</td>
			<td width="30%"><input class="easyui-valida
			tebox" style="width:70%"  name="userCode"></td>
			<td width="20%" style="text-align:right">用户描述:</td>
			<td width="30%"><input class="easyui-validatebox" data-options="required:true," style="width:120px" name="userDescription"></td>
		</tr>
		<tr>
			<td style="text-align:right">用户手机:</td>
			<td><input class="easyui-validatebox"  style="width:70%" name="telephone"></td>
			<td width="20%" style="text-align:right">性别:</td>
			<td width="30%"><select id="sex" class="easyui-combobox" panelHeight="auto" name="userSex" style="width:118px;" data-options="required:true,">
			<option value="0">男</option>
		    <option value="1">女</option>
			</select></td>
			<td width="20%" style="text-align:right">排序:</td>
			<td width="30%"><input class="easyui-validatebox" style="width:120px" data-options="required:false," id="sorting" name="sorting"></td>
		</tr>
		<tr>
		    <td style="text-align:right">生日:</td>
			<td><input id="bb" style="width:115px" name="userBrothday" class="easyui-datebox" formatter="yyyy-MM-dd"></input></td>
			<td style="text-align:right">邮箱:</td>
			<td><input class="easyui-validatebox" style="width:70%" name="userMail"></td>
			<td width="20%" style="text-align:right">QQ:</td>
			<td width="30%"><input class="easyui-validatebox" style="width:120px" name="userQQ"></td>
		</tr>
		<tr>
		
			<td style="text-align:right;">办公电话:</td>
			<td><input class="easyui-validatebox" style="width:70%" id="officephone" name="officephone" data-options="required:true,"></input></td>
			
			<td width="20%" style="text-align:right"><label for="remark">备注:</label></td>
			<td width="30%" colspan="4">
            <textarea name="remark"style= "width:370px;resize:none" ></textarea></td>
		</tr>
	</table>	
    <div style="text-align:center;padding:15px">
    <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-ok" onclick="formSubmit(this.form);">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-cancel" onclick="goBack()" >返回</a>   

	</div>
	</form>
    </div>
     
</body>
</html>