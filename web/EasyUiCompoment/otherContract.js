 //付款方式***************************************开始****************//
	    $(function(){
       	//创建datagrid编辑器
           $('#dg').edatagrid({
               //url: '/paymentWay/query.do',
               method: 'get',
               //saveUrl: '/paymentWay/saveOrUpdate.do',
               //updateUrl: 'saveOrUpdate.do',
               //destroyUrl: 'delete.do',
               
       	});
           $('#dg').edatagrid({
	            onsave:function(){
	            	//alert(1);
	            	var res = evt.target.responseText;
	            	//alert(res);
	            	if(res != null && res != ""){
	              		//document.getElementById('uuid').setValue(res);
	              		$('#uuid').val(res);
	              	}
	            }
           });
	   }); 
	   
	 //新增配置项
      function newRow(){
		 var uuid = $('#uuid').val();
  		  //添加列
		  $('#dg').edatagrid('addRow',{
		  		row:{
		  			//id : Math.random(),
		            pName:'付款方式：', 
		            pSum:'金额:',
		            uuid:uuid
		  		}
	         }); 
		    //取消选中
			$('#dg').datagrid('clearSelections');
       }
	 
	   //结束编辑
      function endEdit(){
          var rows = $('#dg').datagrid('getRows');
          for ( var i = 0; i < rows.length; i++) {
          	$('#dg').datagrid('endEdit', i);
          }
      }
	 	
	   //保存付款方式
	 	function saveData(){
	 		endEdit();
	 		 var rows = $('#dg').datagrid('getRows');//获取datagrid所有行
	 		 var rowsString = '';
	 		 var uuid = '';
		      //遍历所有行
		      $.each(rows,function(i,val){
		    	  rowsString = rows[i].id + '-' + rows[i].paymentName + '-' + rows[i].paymentSum 
		    	  				+ ',' + rowsString ;
		    	  uuid = rows[i].uuid;				
		      });
		      //alert(rowsString);
		      $.ajax({
					type:"POST",
					url:'/paymentWay/saveOrUpdate.do',
					data : {
						paymentData : rowsString,
						uuid : uuid
					},
					success:function(data){
						if(data != ''){
							//alert('操作成功！');
							$('#uuid').val(data);
							//alert($('#uuid').val());
							var qurl =  '/paymentWay/query.do?contractOid=' + $('#uuid').val();
							$('#dg').datagrid({url:qurl});
	       	        	}else{
	       	        		$.messager.alert('温馨提示','操作失败！');
	       	        	}
	       	        },
	       	        // 调用出错执行的函数
	       	        error: function(){
	       	        }         
	       	    });
	 	}
	 	
	 	//删除
      function deleteRow(){
   		var rows = $("#dg").datagrid("getSelections");
   		//判断是否选择行
   		if (!rows || rows.length == 0) {
	    		$.messager.alert('温馨提示', '请选择要删除的数据!');
	    		return;
   		} else {
   			var ids = '';
   			$.each(rows,function(i,val){
					ids += ',' + rows[i].id;
		        }); 
   			//alert(ids);
  				$.ajax({
  					type:"GET",
  					url:'/paymentWay/delete.do',
  					data : {
  						id : ids,
  					},
  					success:function(data){
  						if(data == "1"){
  							//alert('操作成功！');
  							$('#dg').datagrid('reload');
  	       	        	}else{
  	       	        		$.messager.alert('操作失败！');
  	       	        	}
  	       	        },
  	       	        // 调用出错执行的函数
  	       	        error: function(){
  	       	        }         
  	       	    });
   		} 
   	} 
	 	
	   //付款方式***************************************结束****************//
	   
	   
	   //附件信息***************************************开始****************//
	    $(function(){
       	//创建datagrid编辑器
           $('#fj').edatagrid({
               //url: '/paymentWay/query.do',
               method: 'get',
               //saveUrl: '/paymentWay/saveOrUpdate.do',
               //updateUrl: 'saveOrUpdate.do',
               //destroyUrl: 'delete.do',
               
       	});
           $('#fj').edatagrid({
	            onsave:function(){
	            	alert(1);
	            	var res = evt.target.responseText;
	            	alert(res);
	            	if(res != null && res != ""){
	              		//document.getElementById('uuid').setValue(res);
	              		$('#uuid').val(res);
	              	}
	            }
           });
	   }); 
	   
	 //新增配置项
      function newRow1(){
		 var uuid = $('#uuid').val();
  		  //添加列
		  $('#fj').edatagrid('addRow',{
		  		row:{
		  			//id : Math.random(),
		            aName:'附件名称：', 
		            aSum:'附件份数:',
		            uuid:uuid
		  		}
	         }); 
		    //取消选中
			$('#fj').datagrid('clearSelections');
       }
	 
	   //结束编辑
      function endEdit1(){
          var rows = $('#fj').datagrid('getRows');
          for ( var i = 0; i < rows.length; i++) {
          	$('#fj').datagrid('endEdit', i);
          }
      }
	 	
	   //保存付款方式
	 	function saveData1(){
	 		endEdit1();
	 		 var rows = $('#fj').datagrid('getRows');//获取datagrid所有行
	 		 var rowsString = '';
	 		 var uuid = '';
		      //遍历所有行
		      $.each(rows,function(i,val){
		    	  rowsString = rows[i].id + '-' + rows[i].attachmentName + '-' + rows[i].attachmentSum 
		    	  				+ ',' + rowsString ;
		    	  uuid = rows[i].uuid;				
		      });
		      $.ajax({
					type:"POST",
					url:'/attachmentInfo/saveOrUpdate.do',
					data : {
						paymentData : rowsString,
						uuid : uuid
					},
					success:function(data){
						if(data != ''){
							//alert('操作成功！');
							$('#uuid').val(data);
							//alert($('#uuid').val());
							var qurl =  '/attachmentInfo/query.do?contractOid=' + $('#uuid').val();
							$('#fj').datagrid({url:qurl});
	       	        	}else{
	       	        		$.messager.alert('温馨提示','操作失败！');
	       	        	}
	       	        },
	       	        // 调用出错执行的函数
	       	        error: function(){
	       	        }         
	       	    });
	 	}
	 	
	 	//删除
      function deleteRow1(){
   		var rows = $("#fj").datagrid("getSelections");
   		//判断是否选择行
   		if (!rows || rows.length == 0) {
	    		$.messager.alert('温馨提示', '请选择要删除的数据!');
	    		return;
   		} else {
   			var ids = '';
   			$.each(rows,function(i,val){
					ids += ',' + rows[i].id;
		        }); 
   			//alert(ids);
  				$.ajax({
  					type:"GET",
  					url:'/attachmentInfo/delete.do',
  					data : {
  						id : ids,
  					},
  					success:function(data){
  						if(data == "1"){
  							//alert('操作成功！');
  							$('#fj').datagrid('reload');
  	       	        	}else{
  	       	        		$.messager.alert('操作失败！');
  	       	        	}
  	       	        },
  	       	        // 调用出错执行的函数
  	       	        error: function(){
  	       	        }         
  	       	    });
   		} 
   	} 
	 	
	   //附件信息***************************************结束****************//   