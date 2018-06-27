<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="UTF-8">
<title>添加帖子页面</title>
<link rel="stylesheet" type="text/css" href="/publicresource/btn.css">
<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
<script type="text/javascript" src="/EasyUiCompoment/commons.js"></script>
<!--  附件上传 -->
<script type="text/javascript" src="/EasyUiCompoment/upload.js"></script>
<script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>


<script type="text/javascript">

	$(document).ready(function(){	
		var ue=UE.getEditor('editor');	
	});
	
	 function getContent() {
		  var file = document.getElementsByName('file')[0].value;	
	     var content=    UE.getEditor('editor').getContent();
	     var contentTxt=    UE.getEditor('editor').getContentTxt();
	     if (content == ''||contentTxt == '') {
				$.messager.alert('提示', '请填写内容！');
				return;
			}
	     if ($('#title').val() == '') {
				$.messager.alert('提示', '请填写标题！');
				return;
			}
	   
	   
	     $("#content").val(content);
	     $("#bz3").val(contentTxt);
	     $("#addNotice1").submit(function()//提交表单
				    {
				            var options = {
				                url:'/noticeManage/insert.do', //提交给哪个执行
				                type:'POST',
				                success: function(msg){
				                	if(msg == "success"){
				                		//$.messager.alert('温馨提示','上传成功！');	
				                		/*  window.location.href = "/noticeManage/show.do"; */
				                
				                		 window.returnVal = true;
				            	         OpenClose();   
				            	        
				                	}
				                	
				                } //显示操作提示
				            };
				            $("#addNotice1").ajaxSubmit(options);
				          
				            return false; //为了不刷新页面,返回false，反正都已经在后台执行完了，没事！
				           
				    });
	     $("#addNotice1").submit();
	    }
	 
	 function goBack(){
		 window.returnVal = false;
         OpenClose();   
	 }
	 
	 
	 
	 
	 
	</script>
</head>

<body class="easyui-layout">
	<div data-options="fit:true">
		<form id="addNotice1" action="/noticeManage/insert.do" method="post"
			enctype="multipart/form-data" >
			<input type="hidden" id="content" name="content">
			<input type="hidden"  id="bz3" name="bz3">
		<table width="100%" border="0" cellspacing="1" cellpadding="1">
			<tr>
				<td>
					<div data-options="region:'north',height:30px" title="新闻标题：">
						标&nbsp;&nbsp;&nbsp;&nbsp;题：<input class="easyui-validatebox" name="title" data-options="required:true"
							style="width: 60%" validtype="length[0,50]" id="title">
					</div>

				</td>
			</tr>
			<tr>
					<td>
						上传文件:<input type="file" name="file" id="file">
					</td>
				</tr>
			<tr>
				<td>
					<div>
						<script id="editor"  type="text/plain" style="width: 100%; height: 280px;"></script>
					</div>
				</td>
			</tr>
		</table>
		</form>
		<div style="text-align: center;">
		<a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-ok" onclick="getContent()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;	
            <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-cancel" onclick="goBack()" >返回</a>
		</div>
	</div>
</body>
</html>