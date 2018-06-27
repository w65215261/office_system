<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.pmcc.soft.core.utils.AppUtils"%>
<%@page import="org.springframework.web.util.WebUtils"%>
<%@page import="com.pmcc.soft.core.common.OnlineUser"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>人员管理</title>
<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
<script type="text/javascript" src="/EasyUiCompoment/commons.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#personUintId').combotree({
		url:'../organization/queryOrgCombtree.do',method:'post',required:true,
		onClick: function(node){
			$.ajax({
	 			type : "GET",
	 			url : "../personManage/selectSorting.do",
	 			data: {personUintId : node.id},
	 			success : function(data){
	 				if(data.sorting != undefined){
	 					document.getElementById("sorting").value=data.sorting;
	 					document.getElementById("sort").value=data.sorting;
	 				}else{
	 					document.getElementById("sorting").value=1;
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
	  
	//返回列表页面
	function goBack(){
		//window.location.href = "../personManage/personGrid.jsp";
		 window.returnVal=false;
		OpenClose();
	 }
			  
	// 表单提交数据校验
	function formSubmit(obj){
		var flag = $('#personManageFrom').form('validate');
		var sorting = document.getElementById("sorting").value;
		var sort = document.getElementById("sort").value;
		
		if(flag){
			if(sorting <= sort){
				$.ajax({
					type : "POST",
					url : 'vUserEname.do',
					data : {
						'inputUserEname' : $('#userEname').val(),
					}, 
					success : function(data) {
						if(data == "fail"){
							$.messager.alert('温馨提示', '所填用户英文名已存在，请重新填写！');
							$('#userEname').val('');
						}else{
							  $("#personManageFrom").submit(function()//提交表单
									    {
									            var options = {
									                url:'../personManage/save.do', //提交给哪个执行
									                type:'POST',
									                success: function(msg){
									                	if(msg == "success"){
									                		 window.returnVal = true;
										                     OpenClose();
									                	}
									                } //显示操作提示
									            };
									            $('#personManageFrom').ajaxSubmit(options);
									           
									            return false; //为了不刷新页面,返回false，反正都已经在后台执行完了，没事！

									    });
								$('#personManageFrom').submit();
						}
					},
				});
			}else{
				$.messager.alert('温馨提示', '当前序号只能填写到'+sort);
			}
			
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
		//发送请求，查询已存在数据
		$.ajax({
			type : "POST",
			url : 'checkUserEname.do',
			data : {
				'inputUserEname' : $('#userEname').val(),
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
</script>
</head>



<body>
    <div  title="人员管理" style="width:90%;fit:true;margin:300 30 30 30;">
    <form action="../personManage/save.do" method="post"  id="personManageFrom">
    <input type="hidden" name="sort" id="sort">
	<table>
		<tr>
			<td colspan="6"><div style="text-align:center;"><h2>用户基本信息</h2></div></td>
		</tr>
		<tr>
			<td width="10%" style="text-align:right;">用户名:</td>
			<td width="20%"><input class="easyui-validatebox" style="width:70%" data-options="required:true,validType:'englishnum'"  id="userEname" name="userEname" ></td>
			<td width="10%" style="text-align:right;">姓名:</td>
			<td width="20%"><input class="easyui-validatebox" style="width:70%" data-options="required:true,validType:'CHS'" name="userCname"></td>
			<td style="text-align:right;">部门:</td>
			 <td><input class="easyui-combotree" data-options="" style="width:150px" id="personUintId" name="personUintId"  missingMessage="该项为必须输入项"></input></td> 
			<%--  <td>
			 	<input class="easyui-validatebox" data-options="required:true" style="width:70%" value="<%=reportUnit %>" name="prt"  disabled="disabled" missingMessage="该项为必须输入项"></input>
			 	<input type="hidden" class="easyui-validatebox" data-options="required:true" style="width:200px" value="<%=unitId %>" name="personUintId"  missingMessage="该项为必须输入项"></input>
			 </td> --%>
		</tr>
		<tr>
		<td style="text-align:right;">办公电话:</td>
			<td><input class="easyui-validatebox" style="width:70%" id="officephone" name="officephone" data-options="required:true,"></input></td>
			<td style="text-align:right;">用户密码:</td>
			<td><input class="easyui-validatebox" data-options="required:true," style="width:70%" name="md5Pwd"></td>
			<td style="text-align:right;">出生日期:</td>
			<td><input id="bb" name="userBrothday" style="width:150px" class="easyui-datebox" formatter="yyyy-MM-dd" data-options="required:true,"></input></td>
		</tr>
		<tr>
			<td style="text-align:right;">用户描述:</td>
			<td><input class="easyui-validatebox" data-options="required:true," style="width:70%" name="userDescription"></td>
			<td style="text-align:right;">手机号:</td>
			<td><input class="easyui-validatebox" style="width:70%" name="telephone" data-options="required:true," missingMessage="该项为必须输入项"></td>
			<td style="text-align:right;">邮箱:</td>
			<td><input class="easyui-validatebox" style="width:150px" name="userMail" missingMessage="该项为必须输入项" data-options="required:true,"></td>
			
		</tr>
		<tr>
			<td width="10%" style="text-align:right;">性别:</td>
			<td width="25%"><select id="sex" class="easyui-combobox" name="userSex" panelHeight="auto" style="width:118px;" missingMessage="该项为必须输入项"  data-options="required:true,">
			<option value="0">男</option>
		    <option value="1">女</option>
			</select></td>
			<td width="10%" style="text-align:right;">用户编码:</td>
			<td width="20%"><input class="easyui-validatebox" style="width:70%"  name="userCode"></td>
		    <td style="text-align:right;">QQ:</td>
			<td><input class="easyui-validatebox" style="width:150px" name="userQQ"></td>
			
		</tr>
		<tr>
			<td width="10%" style="text-align:right;">用户职务:</td>
			<td width="20%"><input class="easyui-validatebox" style="width:70%" data-options="required:false," name="duty"></td>
			<td style="text-align:right;">年龄:</td>
			<td><input class="easyui-validatebox" style="width:70%" id="age" name="age"></td>
			<td width="10%" style="text-align:right;">排序:</td>
			<td width="20%"><input class="easyui-validatebox" style="width:150px" data-options="required:false," id="sorting" name="sorting"></td>
		</tr>
		
		<tr>
			<td style="text-align:right;"><label for="remark">备注:</label></td><td>
			<textarea name="remark"style= "width: 70%" ></textarea>
		</tr>
		</table>	
		
<div style="text-align:center;padding:20px;margin-left:50px;">
<a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-ok" onclick="formSubmit(this.form);">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-cancel" onclick="goBack()" >返回</a>     
	
        </div>
	
	</form>
    </div>
     
</body>
</html>