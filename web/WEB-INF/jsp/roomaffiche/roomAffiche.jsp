<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>群和成员管理</title>
  <script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
  <script type="text/javascript">

	//删除公告
    function deleteAffiches(){
      	 var oneTest = $("#noticeList").datagrid("getSelections");
      	
      	 if(oneTest==null||oneTest.length==0){
      		 $.messager.alert('提示','请先选择要删除的数据');
       	}else{
       			var ids="";
           		for(var i=0;i<oneTest.length;i++){
           			ids+=oneTest[i].afficheid+",";
           		}
       	  	$.messager.confirm('温馨提示','是否确认删除？',function(r){
          		if(r){
          	
          			$.ajax({
      					type:"GET",
      					url:'/dlRoomAffiche/delete.do',
      					data : {
      						ids : ids
      					},
      					success:function(data){
      						if(data == "success"){
      							$.messager.alert('温馨提示','操作成功！');
      							window.location.href = "/dlRoomAffiche/show.do";
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
    
  
    
    //修改
    function update(){
    	var val = $('#noticeList').datagrid('getSelections');
		if(val == null || val.length == 0){
			$.messager.alert('温馨提示','请选择要修改的公告');
		}else if(val.length != 1){
			
			$.messager.alert('温馨提示','每次只能修改一条数据');
		}else{
			window.location.href = "/dlRoomAffiche/toUpdate.do?afficheid=" + val[0].afficheid +'&affichename=' + val[0].afficheame;
		}
    }
 
    
    //点击左侧数据显示右侧数据
    $(document).ready(function(){
        $('#roomList').datagrid({
        	singleSelect:true,
        	url:'/ofMucRoom/selectMyRoomListByCurrentUser.do',
        	method:'get',
          onCheck: function(rowIndex,rowData) {
            roomID = rowData.roomID;
            $('#noticeList').datagrid({
            	url:'/dlRoomAffiche/seletAfficheByRoomid.do?roomid='+roomID,
            	method:'GET',
            	onDblClickRow: function(rowIndex, rowData){
        			var openUrl = "/dlRoomAffiche/detail.do?afficheid="+rowData.afficheid;//弹出窗口的url
        			var iWidth=800; //弹出窗口的宽度;
        			var iHeight=600; //弹出窗口的高度;
        			var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
        			var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
        			 window.open(openUrl,"","scrollbars=yes,resizable=yes,location=no,status=no,height="+iHeight+", width="+iWidth+", top="+iTop+", left="+iLeft); 
        		}
            });
          }
        });
      });
    
    // 发布群公告
    function releaseNews(){
    	var val = $("#roomList").datagrid("getSelected");
    	if(val==null){
    		$.messager.alert('温馨提示','请选择一条数据！');
    	}else{
    		$.ajax({
    			type:'GET',
    			url:'/dlRoomAffiche/checkSaveUI.do',
    			data:{roomid : val.roomID},
    			success : function(data){
    				if(data == "false"){
    					$.messager.alert('温馨提示','您不是群管理员，不能发布新公告');
    				}else{
    					window.location.href = "/dlRoomAffiche/toSaveUI.do?roomid=" + val.roomID;
    				}
    			}
    		});
    	
    	}
    }



  </script>
</head>
<body class="easyui-layout">

  <!--左侧群组管理-->
  <div  data-options="region:'west',split:true" style="width:200px;" title="群组管理" >
    <table class="easyui-datagrid" id="roomList" data-options="">
      <thead>
        <tr>
          <th data-options="field:'roomID',checkbox:true"></th> 
          <th data-options="field:'naturalName',width:160">群名称</th>
        </tr>
      </thead>
    </table>

  </div>
  <div  data-options="region:'center'">
  <table class="easyui-datagrid" id="noticeList"  title="公告列表" style="height:450px;"
         data-options="checkbox:true,rownumbers:true,fit:true,singleSelect:false,pagination:true,toolbar:'#tb'">
    <thead>

	    <tr>
	      <th data-options="field:'roomid',checkbox:true"></th>
	      <th data-options="field:'affichename',width:180">标题</th>
	      <th data-options="field:'userName',width:150">发布人</th>
	      <th data-options="field:'createtime',width:150">发布时间</th>
	    </tr>
    </thead>
  </table>

  <div id="tb" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
      <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="releaseNews()">发布公告</a>
      <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update()">修改</a>
      <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteAffiches()">删除</a>
    </div>
  </div>
</div>
</body>
</html>