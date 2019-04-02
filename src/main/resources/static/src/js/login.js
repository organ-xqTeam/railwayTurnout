/*  登陆页面js */

$(function () {
    
	$("#login-btn").click(function () {
        console.log(444)
        myCheck();
      });
	
	
});
 function myCheck() {
    	// 验证一下
    	  // 用户名
    	  if ($("#ausername").val().trim() == "") {
    	    alert("请输入用户名")
    	    return
    	  }
    	  // 密码
    	  if ($("#apwd").val().trim() == "") {
    	    alert("请输入密码")
    	    return
    	  }
    	  // 验证一下 end
    	 $.ajax({
             type: "post",
             url: Global.host + "administrator/adminlogin",
             data: {
               ausername: $("#ausername").val(),
               apwd: $("#apwd").val(),
             },
             success: function (obj) {
            	 console.log(obj)
            	 if(obj.stats == 'fail'){
            		 alert("账号密码错误")
            	 }else if(obj.stats == 'success'){
            		 // 超级管理员
            		 if (obj.message ==  '1') {
									console.log("超级管理员******登陆成功")
            			 window.location.href = './measure-manage.html'
            		 // 普通用户
            		 }else if (obj.message ==  '2'){
									 console.log("普通用户******登陆成功")
            			 window.location.href = '../index.html'
            		 }
							 }
							 localStorage.setItem('line',obj.obj.line);
							 localStorage.setItem('site',obj.obj.site);
							 localStorage.setItem('aid',obj.obj.aid);
							 localStorage.setItem('user',obj.message);
             }
           });
    }