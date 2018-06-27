<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="/publicresource/btn.css">

<script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
<script type="text/javascript" src="/EasyUiCompoment/commons.js"></script>
<script type="text/javascript">
//保存
function saveRoom(){
	 var   roomid=$('#roomid').val();
	  $("#roomForm").submit(function()//提交表单
			    {
			            var options = {
			                url:'../../dlFilesManager/uploadFile.do', //提交给哪个执行
			                type:'POST',
			                success: function(data){
			                	if(data == "false"){
			                		$.messager.alert('温馨提示','操作失败，所传文件大小已超出所在群的空间大小，如需扩展空间，请联系管理员！');
			                	}else{
			                		window.returnVal = true;
			                        OpenClose();  
			                	}
			                } //显示操作提示
			            };
			            $('#roomForm').ajaxSubmit(options);
			           
			            return false; //为了不刷新页面,返回false，反正都已经在后台执行完了，没事！

			    });
		$('#roomForm').submit();
	  }
	  
	  function closeRoomWin(){
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
	 <div id="addRoomWin" data-options="modal:true,title:'上传'" style="padding:5px;width:450px;height:450px;">
    <form id="roomForm" action=""  method="post" enctype="multipart/form-data">
     <input type="hidden" name="roomid" id="roomid" value="${roomid}"/>
      <table cellpadding="1" width="100%">
        <tr>
          <td>
             <div class="file-box"> 
						上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;传：<input type='text' name='textfield' id='textfield' class='txt' value="${dlnoticemanage.name}"/> 
						<input type='button' class='btn' value='浏览...' /> 
						<input type="file" name="file" class="file" id="file" size="28" onchange="document.getElementById('textfield').value=this.value" /> 	
					</div> 
             </td>
        </tr>
         <tr>
          <td>文件名称:
         <input class="easyui-validatebox" id="filename" name="filename" data-options="required:false" validType="length[0,125]" style="width:60%"></td>
        </tr>
         <tr>
            <td>描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：<textarea id="bz1" rows=5 name="bz1"
						style="width: 130%; height: 180px;"
						class="textarea easyui-validatebox"></textarea>
				</td>
       <!--    <td>描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述:
          <input class="easyui-validatebox" id="bz1" name="bz1" data-options="required:false" validType="length[0,125]" style="width:320%;height:300px"></td>
           -->
        </tr>
      </table>
    <div style="text-align:center;height:35px; margin-left:30%;margin-top:15px;" >
      <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-ok" onclick="saveRoom()">确认</a>&nbsp;&nbsp;&nbsp;&nbsp;
      <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-cancel" onclick="closeRoomWin()" >取消</a>
    
    
    </div>
    </form>
  </div>
</body>
</html>