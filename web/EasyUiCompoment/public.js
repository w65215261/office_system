  function logOut(){
    	   $.ajax({
	           type:"GET",
	           url:'/authority/logOut.do',
	           success:function(data){
			    	if(data.success){
			    		window.top.location = "/";
			    		//window.top.location = "http://210.76.0.98:8080/";
			    	}   
	           },
	           //调用执行后调用的函数
	           complete: function(XMLHttpRequest, textStatus){
	           },
	           //调用出错执行的函数
	           error: function(){
	           }         
	        });
    }