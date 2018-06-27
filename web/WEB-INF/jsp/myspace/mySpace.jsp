<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
   <title>中平信通合同管理系统</title> 
   <script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
    <script type="text/javascript">
    // 查询按钮
    function search(){ 
    	  var sval=$("#TextValue").val();
    	  $('#mc').datagrid({
  			url : '/dlFilesManager/findByName.do?filename=' + sval
  		});  	  
    }

    //上传
    function releaseNews(){
    	  $('#addRoomWin').window('open');
          $('#roomForm').form('clear');
          $('#roomID').val(0);
    }
    
    //保存
    function saveRoom(){
    	 $('#roomForm').submit();
    }
    
 
    
  
    
    function deleteNews(){
   	 var oneTest = $("#mc").datagrid("getSelections");
   	
   	 if(oneTest==null||oneTest.length==0){
   		 $.messager.alert('提示','请先选择要删除的新闻');
    	}else{
    			var ids="";
        		for(var i=0;i<oneTest.length;i++){
        			ids+=oneTest[i].fileid+",";
        		}
    	  	$.messager.confirm('温馨提示','是否确认删除？',function(r){
       		if(r){
       	
       			$.ajax({
   					type:"GET",
   					url:'/dlFilesManager/delete.do',
   					data : {
   						ids : ids
   					},
   					success:function(data){
   						if(data == "success"){
   							$.messager.alert('温馨提示','操作成功！');
   							window.location.href = "/dlFilesManager/show.do";
   	       	        	}else{
   	       	        		$.messager.alert('温馨提示','操作失败！');
   	       	        	}
   	       	        },
   	       	        error: function(){
   	       	        }         
   	       	    });
       		}
   		});
    	}
    }
    
    function closeRoomWin(){
        $('#addRoomWin').window('close');
      }
    
    function formatterdate(val,row){
     	return '<a href="/dlFilesManager/downFile.do?filename=' + row.filename+'" >下载</a> '
    	
    }
     
  </script>
</head>
<body class="easyui-layout">

  <div  region="center" >
    <table class="easyui-datagrid" id="mc"  title="列表" style="height:450px;"
            data-options="checkbox:true,rownumbers:true,fit:true,singleSelect:false,pagination:true,url:'/dlFilesManager/query.do',method:'get',toolbar:'#tb1'">
        <thead>
        
            <tr>
            	
                <th data-options="field:'fileid',checkbox:true"></th>
                <th data-options="field:'filename',width:300">文件名称</th>
                <th data-options="field:'filesize',width:200">文件大小(M)</th>
                <th data-options="field:'createtime',width:150">上传时间</th>
                <th data-options="field:'spaceusesize',width:150">所剩空间大小</th>
              	<th data-options="field:'aaa',formatter : formatterdate,width:150">下载</th>
            </tr>
        </thead>
    </table>

    <div id="tb1" style="padding:5px;height:auto">
    	<div id="tb2" style="padding:5px;height:auto">
    		<div style="margin-bottom:5px">
    			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="releaseNews()" >上传</a>
	            &nbsp;&nbsp;
	           <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteNews()" >删除</a>
    		</div>
    	</div>
      <table width="100%">
        <tr>
          <td width="100%">
            文件名称:
          <div id="div_searchTextValue" style="width:200px;display:inline;">
    		<input id="TextValue" type="text" style="width:200px" />
    	   </div >
            <input type="button" value="查询" onclick="search()" />
            
          </td>
        </tr>
      </table>
    </div>
    </div>
    
  <!-- 上传 -->
  <div id="addRoomWin" class="easyui-window" data-options="closed:true,modal:true,title:'上传'" style="padding:5px;width:450px;height:240px;">
    <form id="roomForm" action="/dlFilesManager/uploadFile.do" method="post" enctype="multipart/form-data">
      <input type="hidden" name="roomID" id="roomID">    
      <table cellpadding="5" width="100%">
        <tr>
          <td>上传文件:</td>
          <td><input type="file" name="file"></td>
        </tr>
         <tr>
          <td>文件名称:</td>
          <td><input class="easyui-validatebox" id="filename" name="filename" data-options="required:false" validType="length[0,125]" style="width:60%"></td>
        </tr>
         <tr>
          <td>描述:</td>
          <td><input class="easyui-validatebox" id="bz1" name="bz1" data-options="required:false" validType="length[0,125]" style="width:60%"></td>
        </tr>
      </table>
    <div style="padding: 5px; text-align: center;">
      <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-ok" onclick="saveRoom()">确认</a>&nbsp;&nbsp;&nbsp;&nbsp;
      <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-cancel" onclick="closeRoomWin()" >取消</a>
    </div>
    </form>
  </div>
    
  
</body>
</html>