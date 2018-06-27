<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加帖子页面</title>
<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
<!--  附件上传 -->
<script type="text/javascript" src="/EasyUiCompoment/upload.js"></script>
<script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="/EasyUiCompoment/commons.js"></script>
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
	    /*   if(file == ""){
	    		$.messager.alert('提示', '上传文件不能为空！');
				return;
	     }  */
	     $("#content").val(content);
	   /*   $("#bz3").val(contentTxt); */
	     
	     $("#addNotice1").submit(function()//提交表单
				    {
				            var options = {
				                url:'/noticeManage/update.do', //提交给哪个执行
				                type:'POST',
				                success: function(data){
				                	if(data == "success"){
				            	         OpenClose();   
				            	         window.returnVal = true;
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
		<style type="text/css">
.file-box{ position:relative;width:340px}
.txt{ height:22px; border:1px solid #cdcdcd; width:180px;}
.btn{ background-color:#FFF; border:1px solid #CDCDCD;height:24px; width:70px;}
.file{ position:absolute; top:0; right:80px; height:24px; filter:alpha(opacity:0);opacity: 0;width:260px }
</style>
</head>

<body class="easyui-layout">
	<div data-options="fit:true">
		<form id="addNotice1" action="/noticeManage/update.do" method="post"
			enctype="multipart/form-data" >
			<input type="hidden" id="id" name="id" value="${dlnoticemanage.id}">
			<input type="hidden" id="content" name="content">
			<!-- <input type="hidden"  id="bz3" name="bz3"> -->
		<table width="100%" border="0" cellspacing="1" cellpadding="1">
			<tr>
				<td>
					<div data-options="region:'north',height:30px" title="新闻标题：">
						标题：<input class="easyui-validatebox" name="title" data-options="required:true"
							style="width: 60%" validtype="length[0,50]" id="title" value="${dlnoticemanage.title}">
					</div>

				</td>
			</tr>
			<tr>
				 <td>
             <div class="file-box"> 
						上传：<input type='text' name='textfield' id='textfield' class='txt' value="${dlnoticemanage.name}"/> 
						<input type='button' class='btn' value='浏览...' /> 
						<input type="file" name="file" class="file" id="file" size="28" onchange="document.getElementById('textfield').value=this.value" /> 	
					</div> 
             </td>
				</tr>
				 <tr>
				<td>
					<div align="center"; style="height:400px; overflow:auto;">
						<script id="editor"  type="text/plain" style="width: 970px; height: 360px;">${dlnoticemanage.content}</script>
					</div>
					<div style="text-align: center;">
			<a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-ok" onclick="getContent()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;	
            <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-cancel" onclick="goBack()" >返回</a>
			
		</div>
				</td>
			</tr>
		</table>
		</form>
		
	</div>
</body>
</html>