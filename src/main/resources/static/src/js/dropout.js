$(function(){
	$("#dropout").click(function(){
		var result = confirm("确认退出？")
	    if (!result) {
	      return
	    }
		$.ajax({
			type:"get",
			url:Global.host+"administrator/dropout",
			success:function(data){
				location.href="./login.html";
			}
		});
	});
});