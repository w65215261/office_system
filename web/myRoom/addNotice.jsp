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
<script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="/EasyUiCompoment/commons.js"></script>

<script type="text/javascript">
	var ue=UE.getEditor('editor');
	
	 function goBack(){
		 window.returnVal = false;
         OpenClose();   
	 }
	
	 function getContent() {
	       var id=$('#id').val();
	     var content=    UE.getEditor('editor').getContent();
	     var contentTxt=    UE.getEditor('editor').getContentTxt();
	     var title=$('#title').val();
	     var roomid=${room.roomID}; 
	     if(title ==null||title ==""){
	    	 $.messager.alert('温馨提示','帖子标题不能为空',"question");
	     }else{
	    	 $.ajax({
					type : "POST",
					url : '/myRoomManager/insert.do',
					data:{
						id:id,
						title:title,
		      			content:content,
		      			contentTxt:contentTxt,
		      			roomid:roomid,
		      			type:'${type}'
		             },
					success : function(data) {
						if (data == "success") {	
					 /* 	alert("操作成功");  */
					 window.location.href = "/myRoomManager/getShow.do?roomid="+roomid+"&&title=111"; 
							$.messager.alert('温馨提示','操作成功',"info");
							window.returnVal = true;
							OpenClose();
				 	
						} else if(data == "fail"){
						/* 	alert("帖子标题不能为空");  */
						$.messager.alert('温馨提示','帖子标题不能为空',"question");
						 	window.location.href = '/myRoomManager/getShow.do?roomid='+roomid+'&&title=111'; 				 	
						}else {
							$.messager.alert('温馨提示','操作失败',"error");
						}
					},
					//调用出错执行的函数
					error : function() {
					}
				}); 
	     }   
	    }
	</script>
</head>

<body class="easyui-layout" >
	<div data-options="fit:true">
	<input type="hidden" id="id" name="id" value="${dlroompostWithBLOBs.id }">
		<table>
			<tr>
				<td>
					<div data-options="region:'north',height:30px" title="新闻标题：">
						标题：<input class="easyui-validatebox" name="title"
							style="width: 30%" validtype="length[0,50]" id="title" value="${ dlroompostWithBLOBs.title}">
					</div>

				</td>
			</tr>
			<tr>
				<td>群组：${room.naturalName }
				</td>
			</tr>

			<tr>
				<td>
					<div style="height:400px; overflow:auto;text-align: center;">
						<script id="editor"  type="text/plain" style="width: 970px; height: 350px;">${dlroompostWithBLOBs.content }</script>
					</div>
					<div style="text-align:center;margin-top:13px;">
					<a class="easyui-linkbutton"  icon="icon-ok" onclick="getContent()">保存</a>
          &nbsp;&nbsp;&nbsp;&nbsp;
					<a class="easyui-linkbutton" icon="icon-cancel" onclick="goBack()">返回</a>
					</div>
				</td>
			</tr>
				
			
		</table>
	
	</div>
</body>
</html>