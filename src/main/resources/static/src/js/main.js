if(localStorage.getItem('user') == 1) { // 超级管理员
    $('#shouye').css('display','none');
    $('#project').css('display','none');
    $('#archives').css('display','none');
    $('#standard').css('display','none');
    $('#count').css('display','none');
    $('#line').css('display','none');
    $('#site').css('display','none');
    $('#setup').css('display','none');
    $('#log').css('display','none');
    $('.left-nav .title').css('display','none');

} else if(localStorage.getItem('user') == 2) { // 普通用户
    $('#manage').css('display','none');
}

$("#shouye").on("click", function () {
    window.location.href = '../index.html'
})
$("#project").on("click", function () {
    window.location.href = './measure-project.html'
})
$("#archives").on("click", function () {
    window.location.href = './measure-archives.html'
})
$("#standard").on("click", function () {
    window.location.href = './measure-standard.html'
})
$("#count").on("click", function () {
    window.location.href = './measure-count.html'
})
$("#site").on("click", function () {
    window.location.href = './measure-site.html'
})
$("#setup").on("click", function () {
    window.location.href = './measure-setup.html'
})
$("#manage").on("click", function () {
    window.location.href = './measure-manage.html'
})
$("#log").on("click", function () {
    window.location.href = './measure-log.html'
})
$("#line").on("click", function () {
    window.location.href = './measure-lin.html'
})
$("#jianche").on("click", function () {
    window.location.href = './jianche.html'
})

// $('.out').on('click', function () {
//     window.location.href = './login.html'
// })


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