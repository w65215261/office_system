<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" >
  <title>群和成员管理</title>
  <script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
  <script type="text/javascript">
  var roomID ="";
  var src = "";
  function closeSelectWin(){
  	$('#selectWin').window('close');
  }
  //双击人员信息弹出详情页面
//  $(document).ready(function(){
//	  $('#noticeList1').datagrid({
//			onDblClickRow: function(rowIndex, rowData){
	//			top.openDialogWithCallback("/dlRoomAffiche/see.do?afficheid="+rowData.afficheid, top.NewGuid(), '窗体列表 - 查看详情',1000,500, null, true);
//	 			addTab("公告详情","/newsInfo/see.do?id="+rowData.id);
	            		
				
	//			}
	//		});
      /* $('#noticeList1').datagrid({
      	onDblClickRow: function(rowIndex,rowData) {
          	var afficheid = rowData.afficheid;
          	$.ajax({
          		type:'GET',
          		url:'/dlRoomAffiche/selectUser.do',
          		data : {afficheid : afficheid},
          		success : function(data){
          			if(data != "false"){
          				//动态给input赋值
          				document.getElementById('naturalName').value=data.naturalName;
          				document.getElementById('affichename').value=data.affichename;
          				document.getElementById('content').value=data.content;
          				alert(data.imagepath);
          				src = data.imagepath;
          				
          				$("#imageId").attr("src",src);
          				
          				
          				$('#selectWin').window('open');
          			
          			}
          				
          		}
          	});
        }
      }); */
 //   });
  
  
    function add(){
      self.location.href="../personManage/addPerson.jsp";
    }
    //删除人员信息
    function del(){
      var oneTest = $("#allTest").datagrid("getSelections");
      if(oneTest=='' || oneTest.length == 0){
        $.messager.alert('温馨提示',"请选择至少一条人员信息");
      }else{
        //self.location.href="../personManage/delete.do?id="+oneTest.id;
        var ids = "";
        for(var i = 0; i < oneTest.length; i++){
          ids += ',' + oneTest[i].id;
        }
        $.messager.confirm('温馨提示','是否确认删除？',function(r){
          if(r){
            // 重置
            $.ajax({
              type:"GET",
              url:'../personManage/delete.do',
              data : {
                param : ids
              },
              success:function(data){
                if(data == "success"){
                  $.messager.alert('温馨提示','删除成功！');
                  /* TreeView v;
                   var a = v.SelectedNode.Text; */
                  $("#allTest").datagrid("reload");
                }else{
                  $.messager.alert('温馨提示','操作失败！');
                }
              },
              // 调用出错执行的函数
              error: function(){
              }
            });
          }
        });
      }
    }

    //点击树查询人员列表
    function clickTree(node){
      $('#allTest').datagrid('load',{
        groupOid: node.id
      });
    }
    

    //根据组织结构(树节点)查询人员
    function clickTree(node){
      $('#personList').datagrid({
        url:'/personManage/query.do?groupOid=' + node.id,
        method:'get'
      });
    }

    //弹出添加群成员window
    function addMember(){
      var selectRoom = $('#roomList').datagrid('getSelected');
      if(selectRoom==null || selectRoom.length==0){
        $.messager.alert('温馨提示','请选择一个群组');
      }else{
        $('#memberWin').window('open');
        $('#mRoomID').val(selectRoom.roomID);
      }
    }
    //关闭添加群成员window,并重新加载群成员列表
    function closeMemberWin(){
      $('#memberWin').window('close');
      $('#memberList').datagrid({url:'/ofMucMember/findByRoomId.do?roomID='+$('#mRoomID').val(),method:'GET'});
    }

    

    //添加群成员
    function saveMember(){
      var selectPerson = $('#personList').datagrid("getSelections");
      if(selectPerson==null || selectPerson.length==0){
        $.messager.alert('温馨提示','请选择您要添加的成员')
      } else {
        var personId = '';
        for(var i = 0; i < selectPerson.length; i++){
          personId += selectPerson[i].id + ',';
        }
        $.ajax({
          type:"GET",
          url:'/ofMucMember/findByRoomAndPersonName.do',
          data : {
            personIds : personId,
            roomID : $('#mRoomID').val()
          },
          success:function(data){
            if(data == "success"){
              $.ajax({
                type:"POST",
                url:'/ofMucMember/addMember.do',
                async:false,
                data : {
                  personIds : personId,
                  roomID : $('#mRoomID').val()
                },
                success:function(data){
                  if(data == "success"){
                    $.messager.alert('温馨提示','添加成功！');
                  }else{
                    $.messager.alert('温馨提示','添加失败！');
                  }
                },
                // 调用出错执行的函数
                error: function(){
                }
              });
            }else{
              $.messager.alert('温馨提示',data);
            }
          },
          // 调用出错执行的函数
          error: function(){
          }
        });
      }
    }

    function deleteMember(){	
    	var roomid=$('#id').val();
      var selectMember = $('#noticeList').datagrid('getSelections');
      if(selectMember==null || selectMember.length==0){
        $.messager.alert('温馨提示','请选择要删除的帖子！');
      }else{
        var id = '';
        for(var i = 0; i < selectMember.length; i++){
          id += selectMember[i].id + ',';
        }
        $.ajax({
          type:"POST",
          url:'/myRoomManager/delete.do',
          data : {
            id : id
          },
          success:function(data){
            if(data == "success"){
              $.messager.alert('温馨提示','删除成功！');
              $('#noticeList').datagrid({url:'/myRoomManager/selectNoticesByRoomId.do?roomid='+ roomid  /* +"&&title=111" */ ,method:'GET'});
            }else if(data =="fail"){
            	   $.messager.alert('温馨提示','不能删除别人发的帖子！');
                   $('#noticeList').datagrid({url:'/myRoomManager/selectNoticesByRoomId.do?roomid='+ roomid  /* +"&&title=111" */ ,method:'GET'});	
            } else{
              $.messager.alert('温馨提示','删除失败!');
            }
          },
          // 调用出错执行的函数
          error: function(){
          }
        });
      }
    }

    function aaaa(){
      $('#cName').validatebox({
        required: true
      })
    }
    $(document).ready(function(){
   	$('#tt').tabs({  
	      border:false,  
	      onSelect:function(title){
	          if(title=="群帖子"){
	        	 
	        	        $('#noticeList').datagrid({url:'/myRoomManager/selectNoticesByRoomId.do?roomid='+roomID,method:'GET'}); 
	        	      
	          }else if(title =="群公告"){
	        	  
	        	        $('#noticeList1').datagrid({
		                	url:'/dlRoomAffiche/seletAfficheByRoomid.do?roomid='+roomID,
		                	method:'GET'}); 
	        	    
	          }else if(title =="群资料"){
	        	    
	                    $('#mc').datagrid({url:'/dlFilesManager/query.do?roomid='+roomID,method:'GET'});
	                
	          }else if(title =="群成员"){
	        	  $('#mperson').datagrid({url:'/ofMucMember/findByRoomId.do?roomID='+roomID,method:'GET'});
	          }
	      }  
	  });   
    });
    $(document).ready(function(){
    	   var room=$('#id').val();
    	   var title=$('#title').val();
    	   if(title =="112"){
    		   $('#tt').tabs('select','群公告');
    	    	$('#noticeList1').datagrid({
       				url : '/dlRoomAffiche/seletAfficheByRoomid.do?roomid='+room
       			}); 
   	    }
    }); 
   $(document).ready(function(){
    	   var roomid=$('#id').val();
    	   var title=$('#title').val();
    	    if(title =="111"){
    	    	$('#tt').tabs('select','群帖子');
    	     	$('#noticeList').datagrid({
    				url : '/myRoomManager/selectNoticesByRoomId.do?roomid='+roomid
    			});
    	    } 	  
    }); 
   $(document).ready(function(){
	   var roomid=$('#id').val();
	   var title=$('#title').val();
	    if(title =="113"){
	    	$('#tt').tabs('select','群资料');
	    	   $('#mc').datagrid({
					url : '/dlFilesManager/query.do?roomid='+roomid
				});
	    } 	  
}); 
    $(document).ready(function(){
    //点击左侧数据显示右侧数据
    $('#roomList').datagrid({
    	singleSelect:true,
    	url:'/ofMucRoom/selectMyRoomListByCurrentUser.do',
    	method:'get',
      onCheck: function(rowIndex,rowData) {
        roomID = rowData.roomID;
        var tab = $('#tt').tabs('getSelected');
        var index = $('#tt').tabs('getTabIndex',tab);
		if(index==0){
			 $('#noticeList').datagrid({url:'/myRoomManager/selectNoticesByRoomId.do?roomid='+roomID,method:'GET'}); 
		}else if(index==1){
			$('#noticeList1').datagrid({
            	url:'/dlRoomAffiche/seletAfficheByRoomid.do?roomid='+roomID,
            	method:'GET'});
		}else if(index==2){
			 $('#mc').datagrid({url:'/dlFilesManager/query.do?roomid='+roomID,method:'GET'});
		}else if(index==3){
			 $('#mperson').datagrid({url:'/ofMucMember/findByRoomId.do?roomID='+roomID,method:'GET'});	
		}
      $('#id').val(roomID);
      },
    onLoadSuccess : function(data){
    	var rows = $("#roomList").datagrid('getData').rows;  
        var length = rows.length;
        var rowindex=10000000000;  
        var roomid=$('#id').val();
        for (var i = 0; i < length; i++) {  
            if (rows[i]['roomID'] == roomid) {  
                rowindex = i;  
                break;  
            }  
        }  
        if(rowindex!=10000000000){
        	$('#roomList').datagrid('selectRow',rowindex);
        }
        
    }
    });
      });
    

    function update(){
    	  var selectMember = $('#noticeList').datagrid('getSelections');
          if(selectMember==null || selectMember.length!=1){
            $.messager.alert('温馨提示','请选择一条要修改的帖子！');
          }else{
        		$.ajax({
					type:"POST",
					url:'/myRoomManager/editNotice.do',
					data : {
						roomid : selectMember[0].roomid,
						id:selectMember[0].id
					},
					success:function(data){
						if(data == "success"){
							top.openDialogWithCallback("/myRoomManager/addNotice.do?roomid="+selectMember[0].roomid+"&&id="+selectMember[0].id, top.NewGuid(), '窗体列表 - 修改帖子',1000,500, function(data){
								if(data == true){
									$('#noticeList').datagrid('reload');
								}else{
									return;
								}
								
							}, true); 
							//self.location.href="/myRoomManager/addNotice.do?roomid="+selectMember[0].roomid+"&&id="+selectMember[0].id;
	       	        	}else{
	    					$.messager.alert('温馨提示','没有权限无法修改',"question");
	       	        	}
	       	        },
	       	        error: function(){
	       	        }         
	       	    });
          }
    }
    

	function formatterdate(val, row) {
	val=parseInt(val,10);//转为整形
	var date =new Date(val);//正确
	return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
	+ date.getDate() + ' ' + date.getHours() + ':'
	+ date.getMinutes() + ':' + date.getSeconds();
}
	//查询
	  function search(){ 
	  	  var sval=$("#TextValue").val();
	  		 var   roomid=$('#id').val();
	  	  $('#mc').datagrid({
				url : '/dlFilesManager/findByName.do?filename=' + sval +"&&roomid=" + roomid 
			});  	  
	  }

  //上传
  function releaseNews(){
	  var roomid = $("#roomList").datagrid("getSelections");
	  if(roomid == null || roomid.length == 0){
		  $.messager.alert('温馨提示','请选择上传群组');
	  }else{
		  top.openDialogWithCallback("/dlFilesManager/addRoom.do?roomid="+roomID, top.NewGuid(), '窗体列表 - 上传资料',600,320, function(data){
			  if(data == true){
				  $('#mc').datagrid('reload');
			  }else{
				  return;
			  }
				
			}, true); 
	  }
	  }
  
  //保存
function saveRoom(){
	 var   roomid=$('#id').val();
	  $("#roomForm").submit(function()//提交表单
			    {
			            var options = {
			                url:'../../dlFilesManager/uploadFile.do', //提交给哪个执行
			                type:'POST',
			                success: function(msg){
			                	if(msg == "false"){
			                		$.messager.alert('温馨提示','操作失败，所传文件大小已超出所在群的空间大小，如需扩展空间，请联系管理员！');
			                	}else{
			                		//$.messager.alert('温馨提示','上传成功！');
			                		window.location.href = '/myRoomManager/getShow.do?roomid='+roomid +"&&title=113";
			                	}
			                } //显示操作提示
			            };
			            $('#roomForm').ajaxSubmit(options);
			           
			            return false; //为了不刷新页面,返回false，反正都已经在后台执行完了，没事！

			    });
		$('#roomForm').submit();
	  }
  

  //删除群资料
function deleteNews(){
	 var oneTest = $("#mc").datagrid("getSelections");
	 var   roomid=$('#id').val();
	 if(oneTest==null||oneTest.length==0){
		 $.messager.alert('提示','请先选择要删除的文件');
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
							$('#mc').datagrid('reload');
							//window.location.href = "/myRoomManager/getShow.do?roomid="+roomid+"&&title=113";
	       	        	}else if(data == "no"){
	       	        		$.messager.alert('温馨提示','您不可以删除别人上传的文件！');
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
	//删除公告
  function deleteAffiches(){
    	 var oneTest = $("#noticeList1").datagrid("getSelections");
    	 var   roomid=$('#id').val();
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
    							window.location.href = "/myRoomManager/getShow.do?roomid="+roomid+"&&title=112";
    	       	        	}else if(data == "false"){
    	       	        		$.messager.alert('温馨提示','您不是群管理员，不能删除公告');
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
  function update1(){
  	var val = $('#noticeList1').datagrid('getSelections');
		if(val == null || val.length == 0){
			$.messager.alert('温馨提示','请选择要修改的公告');
		}else if(val.length != 1){
			$.messager.alert('温馨提示','每次只能修改一条数据');
		}else{
			$.ajax({
				type:'GET',
				url:'/dlRoomAffiche/toUpdate.do',
				data:{afficheid : val[0].afficheid , affichename : val[0].affichename},
				success : function(data){
					if(data == "false"){
						$.messager.alert('温馨提示','您不是群管理员，不能修改公告');
					}else{
						top.openDialogWithCallback("/dlRoomAffiche/toUpdate.do?afficheid=" + val[0].afficheid +'&affichename=' + val[0].afficheame, top.NewGuid(), '窗体列表 - 修改公告', 600, 380,function(data){
							if(data == true){
								$('#noticeList1').datagrid('reload');
							}else{
								return;
							}
							
						}, true);
						//window.location.href = "/dlRoomAffiche/toUpdate.do?afficheid=" + val[0].afficheid +'&affichename=' + val[0].afficheame;
					}
				}
			});
			//window.location.href = "/dlRoomAffiche/toUpdate.do?afficheid=" + val[0].afficheid +'&affichename=' + val[0].afficheame;
		}
  }
  // 发布群公告
  function releaseNews1(){
  	var val = $("#roomList").datagrid("getSelected");
  	if(val==null){
  		$.messager.alert('温馨提示','请选择一条数据！');
  	}else{
  		$.ajax({
			type:'GET',
			url:'/dlRoomAffiche/toSaveUI.do',
			data:{roomid : val.roomID},
			success : function(data){
				if(data == "false"){
					$.messager.alert('温馨提示','您不是群管理员，不能发布新公告');
				}else{
					  top.openDialogWithCallback("/dlRoomAffiche/toSaveUI.do?roomid=" + val.roomID, top.NewGuid(), '窗体列表 - 发布公告',600,390, function(data){
						  if(data == true){
							  $('#noticeList1').datagrid('reload');
						  }else{
							  return;
						  }
						
					}, true); 
					 //top.openDialogWithCallback("/dlRoomAffiche/toSaveUI.do?roomid=" + val.roomID, top.NewGuid(), '窗体列表 - 添加',1000,500, null, true);
					//window.location.href = "/dlRoomAffiche/toSaveUI.do?roomid=" + val.roomID;
				}
			}
		});
  	}
  }
  function closeRoomWin(){
      $('#addRoomWin').window('close');
    }
  function addNotice(){
	  	if(roomID==null||roomID==""){
	  		  $.messager.alert('温馨提示','请选择群组！');
	  	}else{	   	
	  		$.ajax({
				type:"POST",
				url:'/myRoomManager/editNotice.do',
				data : {
					roomid : roomID
				},
				success:function(data){
					if(data == "success"){
						top.openDialogWithCallback("/myRoomManager/addNotice.do?roomid="+roomID, top.NewGuid(), '窗体列表 - 添加帖子',1000,500, function(data){
							if(data == true){
								$('#noticeList').datagrid('reload');
							}else{
								return;
							}
							
						}, true); 
						  //self.location.href="/myRoomManager/addNotice.do?roomid="+roomID;
       	        	}else{
    					$.messager.alert('温馨提示','没有权限无法添加',"question");
       	        	}
       	        },
       	        error: function(){
       	        }         
       	    });
	  	}
	  }
  
  function  go(val,row){
		return '<a href="#" onclick="constructionManager(\'' + row.id+ '\')">'+val+'</a> '
		}
  
  function constructionManager(row){	
	  top.openDialogWithCallback("/myRoomManager/selectByRoomId.do?id="+row, top.NewGuid(), '窗体列表 - 添加帖子',1050,500, function(data){
		  if(data == true){
			  $('#noticeList1').datagrid('reload');
		  }else{
			  return;
		  }
			
		}, true); 
  }
  
  function  go1(val,row){
		return '<a href="#\'' + row.afficheid+ '\'" onclick="constructionManager1(\'' + row.afficheid+ '\')">'+val+'</a> '
		}
  function constructionManager1(row){	
			top.openDialogWithCallback("/dlRoomAffiche/see.do?afficheid="+row, top.NewGuid(), '窗体列表 - 查看详情',1000,500, null, true);
	  }
  
  function formatterdate1(val,row){
   	return '<a href="/dlFilesManager/downFile.do?filename=' + row.filename+'" >下载</a> '
  }
  </script>
</head>
<body class="easyui-layout">
		 <!--左侧群组管理-->
  <div  data-options="region:'west',split:true" style="width:200px;height:1000px;" title="群组管理" >
    <table class="easyui-datagrid" id="roomList" style="overflow:auto;" data-options="singleSelect:true">
      <thead>
        <tr>
          <th data-options="field:'naturalName',width:180">群名称</th>
        </tr>
      </thead>
    </table>
    </div>
  <div  data-options="region:'center'">
   <div class="easyui-tabs" data-options="fit:true" id="tt">
		<div title="群帖子" data-options="fit:true" >
  <input id="id" type="hidden"  name="id" value="${roomid}">
    <input id="title" type="hidden"  name="title" value="${title}">
  <table class="easyui-datagrid" id="noticeList"  title="帖子列表" style="height:600px;"
         data-options="checkbox:true,rownumbers:true,fit:true,singleSelect:false,pagination:true,toolbar:'#tb',url:''">
    <thead>
    <tr>
      <th data-options="field:'id',checkbox:true"></th>
      <th data-options="field:'title',width:300,formatter:go">标题</th>
      <th data-options="field:'person',width:100">发布人</th>
       <th data-options="field:'department',width:100">发布人部门</th>
      <th data-options="field:'createtime',formatter : formatterdate,width:150">创建时间</th>
    </tr>
    </thead>
  </table>
  <div id="tb" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
      <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addNotice()">发表帖子</a>
      <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteMember()">删除</a>
       <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update()">修改</a>
    </div>
</div>
		</div>
		<div title="群公告"  data-options="fit:true"> <!--  closable="true" -->
  <table class="easyui-datagrid" id="noticeList1"  title="公告列表" style="height:600px;"
         data-options="checkbox:true,rownumbers:true,fitColumns:true,fit:true,singleSelect:false,pagination:true,toolbar:'#tb4'">
    <thead>
	    <tr>
	      <th data-options="field:'roomid',checkbox:true"></th>
	      <th data-options="field:'affichename',width:180,formatter:go1">标题</th>
	      <th data-options="field:'userName',width:80">发布人姓名</th>
	      <th data-options="field:'department',width:80">发布人部门</th>
	      <th data-options="field:'createtime',width:80">日期</th>
	      
	    </tr>
    </thead>
  </table>
  <div id="tb4" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
      <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="releaseNews1()">发布公告</a>
      <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update1()">修改</a>
      <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteAffiches()">删除</a>
    </div>
  </div>
		</div>
		<div title="群资料" data-options="fit:true"> <!-- closable="true"  -->
     <table class="easyui-datagrid" id="mc"  title="列表" style="height:450px;"
            data-options="checkbox:true,rownumbers:true,fit:true,singleSelect:false,pagination:true,method:'get',toolbar:'#tb1'">
        <thead>
            <tr>
                <th data-options="field:'userorroomid',checkbox:true"></th>
                <th data-options="field:'filename',width:260">文件名称</th>
                <th data-options="field:'filesize',width:150">文件大小(M)</th>
                <th data-options="field:'createtime',width:150">上传时间</th>
                 <th data-options="field:'person',width:150">上传人姓名</th>
                  <th data-options="field:'department',width:150">部门</th>
              	<th data-options="field:'aaa',formatter : formatterdate1,width:112">下载</th>
            </tr>
        </thead>
    </table>

    <div id="tb1" style="padding:5px;height:auto">
    	 <div id="tb2" style="padding:5px;height:auto">
    		<div style="margin-bottom:5px">
    			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="releaseNews()" >上传</a>
	            &nbsp;&nbsp;
	           <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteNews()" >删除</a>
	           &nbsp;&nbsp;
	                           文件名称:&nbsp;&nbsp;
	           <input id="TextValue" type="text" style="width:200px"/>
	           &nbsp;&nbsp;
	           <input type="button" value="查询" onclick="search()" />
    		</div>
    		
    	</div> 
      
    </div>
		</div>
		<div title="群成员"  data-options="fit:true"> <!--  closable="true" -->
  <table class="easyui-datagrid" id="mperson"  title="成员列表" style="height:600px;"
         data-options="checkbox:true,rownumbers:true,fitColumns:true,fit:true,singleSelect:false,pagination:true">
    <thead>
	    <tr>
	      <th data-options="field:'roomID',checkbox:true"></th>
	      <th data-options="field:'firstName',width:80">姓名</th>
	      <th data-options="field:'department',width:80">部门</th>
	      <th data-options="field:'telephone',width:80">手机号</th> 
	      <th data-options="field:'officephone',width:80">办公电话</th> 
	      <th data-options="field:'email',width:80">邮箱</th> 
	      <th data-options="field:'qq',width:80">QQ</th> 
	      <th data-options="field:'remark',width:80">备注</th> 
	    </tr>
    </thead>
  </table>
  </div>
		</div>
	</div>
  </div>
  
  
  
</body>
</html>