<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>群和成员管理</title>
  <script type="text/javascript" src="/EasyUiCompoment/easyUiCommons.js"></script>
  <script type="text/javascript">
    function add(){
      self.location.href="../personManage/addPerson.jsp";
    }
    //修改人员信息
    function update(){
      var oneTest = $("#allTest").datagrid("getSelections");
      if(oneTest==null ||  oneTest.length != 1){
        $.messager.alert('温馨提示',"请选择一条人员信息");
      }else{
        var one = oneTest[0];
        self.location.href="updatePerson.jsp?id="+one.id;
      }
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
    //密码重置
    function passwordReset(){
      var oneTest = $("#allTest").datagrid("getSelections");
      if(oneTest==null ||  oneTest.length == 0){
        $.messager.alert('温馨提示',"请选择至少一条人员信息");
      }else{
        var ids = "";
        for(var i = 0; i < oneTest.length; i++){
          ids += ',' + oneTest[i].id;
        }
        $.messager.confirm('温馨提示','是否确认重置密码？',function(r){
          if(r){
            // 重置
            $.ajax({
              type:"GET",
              url:'../personManage/passwordReset.do',
              data : {
                param : ids
              },
              success:function(data){
                if(data == "success"){
                  $.messager.alert('温馨提示','密码重置成功！');
                  $("#allTest").datagrid("clearSelections");
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

    //根据组织结构(树节点)查询人员
    function clickTree(node){
      $('#personList').datagrid({
        url:'/personManage/query.do?groupOid=' + node.id,
        method:'get'
      });
    }

    //弹出新增群组window
    function addRoom(){
      $('#addRoomWin').window('open');
      $('#roomForm').form('clear');
      $('#roomID').val(0);
    }
    //关闭新增群组window
    function closeRoomWin(){
      $('#addRoomWin').window('close');
    }
    
    function closeupdateRoomWin(){
    	$('#updateRoomWin').window('close');
    }
    
    function closeSelectWin(){
    	$('#selectWin').window('close');
    }

    // 群组添加表单提交
    function saveRoom(){
    	
      var flag = $('#roomForm').form('validate');
//      var spacesize = $('#spacesize').val;
      if(flag){
        $.ajax({
          type:'POST',
          url:'/ofMucRoom/saveOrUpdate.do',
          data : $('#roomForm').serialize(),
          success:function(data){
            if(data == "success"){
              $.messager.alert('温馨提示','操作成功！');
              closeRoomWin();
              $('#roomList').datagrid('reload');
            }else{
              $.messager.alert('温馨提示','操作失败,群名称已存在请重新输入！');
            }
          },
          //调用出错执行的函数
          error: function(){
          }
        });
      }else{
        $.messager.alert('温馨提示',"请将内容填写完整，并确定格式正确！");
      }
    }

    //修改群组信息
    function updateRoom(){
      var selectRoom = $('#roomList').datagrid('getSelected');
      if(selectRoom==null || selectRoom.length==0){
        $.messager.alert('温馨提示','请选择要修改的群组！');
      }else{
    	  $.ajax({
    		  type : "GET",
    		  url : "/ofMucRoom/findByRoomID.do",
    		  data : {roomID : selectRoom.roomID},
    		  success : function(data){
    			  if(data != "false"){
    				//动态给input赋值
    				//数据库的空间大小单位是B页面是M   转换一下
    				var num = parseInt(data.spacesize);
    				var log = num / 1024 /1024;
    	  			document.getElementById('naturalName').value=data.naturalName;
    	  			document.getElementById('spacesize').value=log;
    	  			document.getElementById('pacename').value=data.pacename;
    	  			document.getElementById('description').value=data.description;
    	  			document.getElementById('roomID').value=data.roomID;
    	  			document.getElementById('spaceid').value=data.spaceid;
    	  			
    	  			$('#addRoomWin').window('open');
    			  }
    			
    		  }
    		  
    	  });
    	  
    	  
   
        //addRoom();
        // $('#roomForm').form('load', '/ofMucRoom/findByRoomID.do?roomID=' + selectRoom.roomID); 
      }
    }
    
    //删除群组
    function deleteRoom(){
      var selectRoom = $('#roomList').datagrid('getSelected');
      if(selectRoom==null || selectRoom.length==0){
        $.messager.alert('温馨提示','请选择要删除的群组！');
      }else{
    	  $.messager.confirm("温馨提示", "确定删除？", function (r) {  
		        if (r) {  
        $.ajax({
          type:'GET',
          url:'/ofMucRoom/delete.do',
          data:{roomID : selectRoom.roomID},
          success:function(data){
            if(data == "success"){
              $.messager.alert('温馨提示','删除成功！');
              $('#roomList').datagrid('reload');
              $('#memberList').datagrid('reload');
            }else{
              $.messager.alert('温馨提示','删除失败！');
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

    //单击群组，弹出人员信息
    $(document).ready(function(){
      $('#roomList').datagrid({
        onCheck: function(rowIndex,rowData) {
          var roomID = rowData.roomID;
          $('#memberList').datagrid({url:'/ofMucMember/findByRoomId.do?roomID='+roomID,method:'GET'});
        }
      });
    });
    
    
   
    //双击人员信息弹出详情页面
    $(document).ready(function(){
    	
        $('#memberList').datagrid({
        	onDblClickRow: function(rowIndex,rowData) {
            	var jid = rowData.jid;
            	$.ajax({
            		type:'GET',
            		url:'/ofMucRoom/selectUser.do',
            		data : {jid : jid},
            		success : function(data){
            			if(data != "false"){
            				//动态给input赋值
            				document.getElementById('userEname').value=data.USER_ENAME;
            				document.getElementById('user_Cname').value=data.USER_CNAME;
            				document.getElementById('user_Description').value=data.USER_DESCRIPTION;
            				if(data.USER_SEX == "0"){
            					document.getElementById('user_Sex').value='男';
            				}else{
            					document.getElementById('user_Sex').value='女';
            				}
            				
            				document.getElementById('org_Cname').value=data.ORG_CNAME;
            				document.getElementById('Duty').value=data.DUTY;
            				$('#selectWin').window('open');
            			
            			}
            				
            		}
            	});
          }
        });
      });
      
    
    //设置为管理员
	function setUp(){
		var selectPerson = $('#memberList').datagrid("getSelected");
		var selectRoom = $('#roomList').datagrid("getSelected");
	      if(selectPerson==null || selectPerson.length==0){
	        $.messager.alert('温馨提示','请选择您要设置的成员');
	      }else{
	    	  
	    	  var personId = selectPerson.jid;
	    	  var roomID = selectRoom.roomID;
		    	  $.messager.confirm('温馨提示','是否确认改成管理员？',function(r){
		              if(r){
				    	  $.ajax({
				              type:"GET",
				              url:'/ofMucMember/setUp.do',
				              data : {
				            	  jid : personId,
				            	  roomID : roomID
				              },
				              success:function(data){
				            	  if(data == "true"){
				            		  $.messager.alert('温馨提示','设置成功！');
				            		  $('#memberList').datagrid('reload');					            		  
				            	  }
				              }
				            });
		              }
		    	  });
	    	  
	      }
	}

    //添加群成员
    function saveMember(){
      var selectPerson = $('#personList').datagrid("getSelections");
      if(selectPerson==null || selectPerson.length==0){
        $.messager.alert('温馨提示','请选择您要添加的成员');
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
                   
                    $("#memberWin").window('close');
                    $('#memberList').datagrid('reload');
                  }else{
                    $.messager.alert('温馨提示','添加失败！');
                  }
                },
                // 调用出错执行的函数
                error: function(){
                }
              });
              //$.messager.alert('温馨提示','添加成功！');
            }else{
              $.messager.alert('温馨提示',"已经存在于改群中，请勿重复添加！");
            }
          },
          // 调用出错执行的函数
          error: function(){
          }
        });

      }
    }

    function deleteMember(){
      var selectMember = $('#memberList').datagrid('getSelections');
      
      if(selectMember==null || selectMember.length==0){
        $.messager.alert('温馨提示','请选择要删除的成员！');
      }else{
    	
        var roomID = '';
        var jid = '';
        for(var i = 0; i < selectMember.length; i++){
          roomID += selectMember[i].roomID + ',';
          jid += selectMember[i].jid+ ',';
        }
        $.messager.confirm('温馨提示','是否确认删除？',function(r){
            if(r){
        $.ajax({
          type:"POST",
          url:'/ofMucMember/delete.do',
          data : {
            roomID : roomID,
            jid : jid
          },
          success:function(data){
            if(data == "success"){
              $.messager.alert('温馨提示','删除成功！');
              $('#memberList').datagrid({url:'/ofMucMember/findByRoomId.do?roomID='+$('#roomList').datagrid('getSelected').roomID,method:'GET'});
            }else{
              $.messager.alert('温馨提示','删除失败！');
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

    function aaaa(){
      $('#cName').validatebox({
        required: true
      });
    }
    
 
  </script>
</head>
<body class="easyui-layout">

  <!--左侧群组管理-->
  <div  data-options="region:'west',split:true" style="width:200px;"  title="群组管理" >
    <table class="easyui-datagrid" id="roomList" data-options="fitColumns:true,singleSelect:true,url:'/ofMucRoom/query.do',method:'get',toolbar:'#qztb'">
      <thead>
        <tr>
           <!-- <th data-options="field:'roomID',checkbox:false"></th> --> 
          <th data-options="field:'naturalName',width:160">群名称</th>
        </tr>
      </thead>
    </table>
    <div id="qztb" style="padding:5px;height:auto">
      <div style="margin-bottom:5px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRoom()">新增</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"onclick="updateRoom()">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteRoom()">删除</a>
      </div>
    </div>
  </div>

  <!-- 新增群组 -->
  <div id="addRoomWin" class="easyui-window" data-options="closed:true,modal:true,title:'群组'" style="padding:5px;width:450px;height:250px;">
    <form id="roomForm"  method="post">
      <input type="hidden" name="roomID" id="roomID">
      <input type="hidden" id="spaceid" name="spaceid"/>
      <table cellpadding="5" width="100%">
      
        <tr>
          <td style="text-align:right">群名称:</td>
          <td><input class="easyui-validatebox" id="naturalName" name="naturalName" data-options="required:true" validType="length[0,125]" style="width:60%"></td>
        </tr>
        <tr>
          <td style="text-align:right">空间大小:</td>
          <td><input class="easyui-validatebox" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" id="spacesize" name="spacesize" data-options="required:true" validType="length[0,125]" style="width:60%">(M)请输入数字</td>
        </tr>
         <tr>
          <td style="text-align:right">空间名字:</td>
          <td><input class="easyui-validatebox" id="pacename" name="pacename" data-options="required:false" validType="length[0,125]" style="width:60%"></td>
        </tr>
        <tr>
          <td style="text-align:right">群描述:</td>
          <td><textarea class="easyui-validatebox" id="description" name="description" cols="50" validType="length[0,125]" style="width:90%" ></textarea></td>
        </tr>
      </table>
    <div style="padding: 5px; text-align: center;">
      <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-ok" onclick="saveRoom(this.form)">确认</a>&nbsp;&nbsp;&nbsp;&nbsp;
      <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-cancel" onclick="closeRoomWin()" >取消</a>
    </div>
    </form>
  </div>
  
 


  <div  data-options="region:'center'">
  <table class="easyui-datagrid" id="memberList"  title="群成员管理" style="height:450px;"
         data-options="checkbox:true,rownumbers:true,fit:true,singleSelect:false,pagination:true,toolbar:'#tb'">
    <thead>

    <tr>
      <!-- <th data-options="field:'roomID',checkbox:false"></th> -->
<!--       <th data-options="field:'jid',width:180">成员ID</th>-->      
	  <th data-options="field:'nickname',width:50">用户名</th>
      <th data-options="field:'firstName',width:50">中文名</th>
      <th data-options="field:'age',width:30">年龄</th>
      <th data-options="field:'department',width:70">所属部门</th>
      <th data-options="field:'telephone',width:100">电话</th>
      <th data-options="field:'isOrAdmin',width:80">职责</th>
      <th data-options="field:'duty',width:80">职务</th>
      <th data-options="field:'email',width:120">邮箱</th>
      <th data-options="field:'qq',width:120">QQ</th>
      <th data-options="field:'officephone',width:100">办公电话</th>
      <th data-options="field:'sort',width:50">排序</th>
    </tr>
    </thead>
  </table>

  <div id="tb" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
      <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addMember()">添加群成员</a>
      <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteMember()">删除</a>
      <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="setUp()">设为群主</a>
    </div>
  </div>
</div>

  <div id="memberWin" class="easyui-window" data-options="closed:true,modal:true,title:'群成员添加'" style="width:700px;height:400px;">
    <input type="hidden" id="mRoomID" name="roomID">
    <div class="easyui-layout" fit="true">
      <!-- 左侧组织机构树 -->
      <div region="west" split="true" style="width:200px;overflow:auto;" title="组织机构">
        <table id="personTree"
               class="easyui-tree" data-options=" url: '../organization/userQueryOrgan.do',
	                method: 'post',
	                idField: 'id',
	                treeField: 'orgCname',onClick:function(node){clickTree(node)}">
        </table>
      </div>

      <div region="center">
          <table class="easyui-datagrid" id="personList" style="height:330px;overflow:auto;"  title="人员列表" data-options="pagination:true,rownumbers:true">
            <thead>
            <tr>
              <th data-options="field:'id',checkbox:true"></th>
              <th data-options="field:'userEname',width:150">成员名称</th>
              <th data-options="field:'userCname',width:80">用户名称</th>
              <th data-options="field:'department',width:120">所属部门</th>
              <th data-options="field:'telephone',width:120">电话</th>
              <th data-options="field:'duty',width:120">职务</th>
              <th data-options="field:'age',width:150">年龄</th>
              <th data-options="field:'userMail',width:150">邮箱</th>
              <th data-options="field:'officephone',width:150">部门电话</th>
            </tr>
            </thead>
          </table>
      </div>

      <div region="south" border="false" style="text-align:right;height:30px;line-height:25px;">
        <div align="center" >
          <a class="easyui-linkbutton"  icon="icon-ok" onclick="saveMember()">添加</a>
          &nbsp;&nbsp;&nbsp;&nbsp;
          <a class="easyui-linkbutton" icon="icon-cancel" onclick="closeMemberWin()">关闭</a>
        </div>
      </div>
    </div>

  </div>


<!-- 查看详情信息 -->
  <div id="selectWin" class="easyui-window" data-options="fit:true,closed:true,modal:true,title:'详情信息'" style="padding:5px;width:450px;height:240px;">
    
      <table width="100%" id="selectTable" border="2" cellpadding="10" cellspacing="0" >
			
			
		<tr>
			<div style="text-align:center;width:100%;border:solid 0px;padding-top:20px;padding-bottom:20px;">人员信息</div>
		</tr>
		<tr>
			<td width="10%">用户英文名:</td>
			<td width="20%"><input class="easyui-validatebox"  readonly="true" style="width:70%"  id="userEname" name="userEname" onblur="isRepetition()"></td>
			<td width="10%">用户中文名:</td>
			<td width="20%"><input class="easyui-validatebox" readonly="true" style="width:70%" id="user_Cname"  name="user_Cname"></td>
			<td>用户描述:</td>
			 <td><input class="easyui-validatebox" readonly="true" style="width:150px" id="user_Description" name="user_Description"  missingMessage="该项为必须输入项"></input></td> 
		</tr>
		<tr>
			<td width="10%">性别:</td>
			<td width="20%"><input class="easyui-validatebox" readonly="true" style="width:70%" id="user_Sex"  name="user_Sex"></td>
			<td>所属部门:</td>
			<td><input class="easyui-validatebox" readonly="true" style="width:70%" id="org_Cname" name="org_Cname"></td>
			<td width="10%">职务:</td>
			<td width="20%"><input class="easyui-validatebox" readonly="true" style="width:70%" id="Duty"  name="Duty"></td>
		</tr>
		
		</table>	
	
		

    <div style="padding: 5px; text-align: center;">
      <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-cancel" onclick="closeSelectWin()" >关闭</a>
    </div>
  </div>
</body>
</html>