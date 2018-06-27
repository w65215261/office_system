<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改字典值</title>
<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
<script type="text/javascript">
	//获得参数的方法
	var request = {
		QueryString : function(val) {
			var uri = window.location.search;
			var re = new RegExp("" + val + "=([^&?]*)", "ig");
			return ((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 1))
					: null);
		}
	}

	var sid = request.QueryString("id");
	function loadFrom() {
		$('#dictFrom').form('load', 'findDict.do?id=' + sid);
	}

	function goBack() {
		self.location.href = "show.do";
	}
	
	//将父节点ID下拉框的id值赋给隐藏框
	 $(document).ready(function () {
		  $("#parentId").combotree({
			     onChange:function(){
			         $("#puid").val($('#parentId').combobox('getValue'));
			     }    
			     });
	});
</script>
</head>
<body onload="loadFrom()">
	<div title="修改字典值" style="width: 90%; fit: true; margin: 300 30 30 30;">
		<form action="update.do" method="post" novalidate id="dictFrom">
			<br> <br>
			<table width="100%" border="2" cellpadding="10" cellspacing="0">
				<input type="hidden" name="id">
				 <input type="hidden"  name="parentId" id="puid">
				<tr>
					<td width="10%">字典名称:</td>
					<td width="20%"><input class="easyui-validatebox"
						style="width: 80%" data-options="required:true," name="dictName"></td>
					<td width="10%">编码:</td>
					<td width="20%"><input class="easyui-validatebox"
						style="width: 80%" data-options="required:true," name="dictId"></td>
				</tr>
				<tr>
					<td>编码类别:</td>
					<td><input class="easyui-validatebox"
						data-options="required:true," style="width: 80%" name="dictType"></td>
					<td>父节点:</td>
					<td><input class="easyui-combotree" style="width:200px"
						data-options="url:'getParentAll.do',method:'post',required:true"
					 	 name="parentName" missingMessage="该项为必须输入项" id="parentId"></td>
				</tr>
				<tr>
					<td>等级:</td>
					<td><input class="easyui-validatebox"
						data-options="required:true," style="width: 80%" name="rank"></td>
					<td>排序:</td>
					<td><input class="easyui-validatebox"
						data-options="required:true," style="width: 80%" name="sortNo"></td>
				</tr>
				<tr>
					<td>PY:</td>
					<td><input class="easyui-validatebox"
						data-options="required:false," style="width: 80%" name="py"></td>
					<td>状态:</td>
					<td><select id="cc" class="easyui-combobox" name="status"
						style="width: 200px;" data-options="required:true">
							<option value="0">有效</option>
							<option value="1">无效</option>
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