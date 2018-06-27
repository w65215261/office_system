<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>增加字典值</title>
<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
<script type="text/javascript">
	function checkNull() {
		var dictName = document.getElementById("dictName").value;
		var dictId = document.getElementById("dictId").value;
		var dictType = document.getElementById("dictType").value;
		var rank = document.getElementById("rank").value;
		var sortNo = document.getElementById("sortNo").value;
		var astatus = $("#statusss").val();
		var parentId = $("#parentId").combotree("getText");
		var re = new RegExp("^[0-9]*$");
		if (dictName != "" && dictId != "" && dictType != "" && rank != ""
				&& sortNo != "" && astatus != "" && parentId != "" && re.test(sortNo) && reg.test(rank)) {//
			return true;
		}else{
		$.messager.alert('温馨提示',"请将内容填写完整！");
		return false;
		}
	}

	function goBack() {
		self.location.href = "show.do";
	}
	
	//检验用户英文名是否重复
	function isRepetition(){
		//发送请求，查询已存在数据
		$.ajax({
			type : "POST",
			url : 'checkId.do',
			data : {
				'inputId' : $('#inputId').val(),
			}, 
			success : function(data) {
				if(data == "fail"){
					$.messager.alert('温馨提示', '所填ID已存在，请重新填写！');
					$('#inputId').val('');
				}else{
					//window.location.href = data.loginUrl;
				}
			},
		});
	}
</script>
</head>
<body>
	<div title="新增字典值" style="width: 90%; fit: true; margin: 300 30 30 30;">
		<form action="insert.do" method="post" novalidate id="dictFrom"
			onsubmit="return checkNull()">
			<br> <br>
			<table width="100%" border="2" cellpadding="10" cellspacing="0">
				<tr>
					<td width="10%">ID:</td>
					<td width="20%"><input id="inputId"
						class="easyui-validatebox" style="width: 80%"
						 name="id" onblur="isRepetition()"></td>
					<td width="10%">字典名称:</td>
					<td width="20%"><input id="dictName"
						class="easyui-validatebox" style="width: 80%"
						data-options="required:true," name="dictName"></td>
				</tr>
				<tr>
					<td width="10%">编码:</td>
					<td width="20%"><input id="dictId" class="easyui-validatebox"
						style="width: 80%" data-options="required:true," name="dictId"></td>
					<td>编码类别:</td>
					<td><input id="dictType" class="easyui-validatebox"
						data-options="required:true," style="width: 80%" name="dictType"></td>
				</tr>
				<tr>
					<td>父节点:</td>
					<td><input class="easyui-combotree" style="width:200px"
						data-options="url:'getParentAll.do',method:'post',required:true"
						 name="parentId" id="parentId" missingMessage="该项为必须输入项"></td>
					<td>等级:(只能填写数字)</td>
					<td><input id="rank" class="easyui-validatebox"
						data-options="required:true," style="width: 80%" name="rank"></td>
				</tr>
				<tr>
					<td>排序:(只能填写数字)</td>
					<td><input id="sortNo" class="easyui-validatebox"
						data-options="required:true," style="width: 80%" name="sortNo"></td>
					<td>PY:</td>
					<td><input class="easyui-validatebox"
						data-options="required:false," style="width: 80%" name="py"></td>
				</tr>
				<tr>
					<td>状态:</td>
					<td><select id="statusss" class="selector"
						name="status" style="width: 200px;" data-options="required:true" >
							<option value="1">无效</option>
							<option selected value="0">有效</option>

					</select></td>
				</tr>
			</table>
			<div style="text-align: center; padding: 5px">
				<input type="submit" value="保存"> <input type="reset"
					value="重置"> <input type="button" value="返回"
					onclick="goBack()">
			</div>
		</form>
	</div>

</body>
</html>