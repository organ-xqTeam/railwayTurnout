// 翻页
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
// ***********************************************

// 线路下拉**********************************************************************
createLine()
function createLine () {
    $.ajax({
        type: 'POST',
        url: Global.host + 'trainroute/selectAll',
        data: {
            pageNum: 0,
            pageSize: 10000
        },
        success: function (res) {
            console.log(res);
            var str = '';
            for(var i = 0; i < res.list.length; i++) {
                str += '<option value='+ res.list[i].id + '>'+ res.list[i].routename + '</option>'
            }
            $('#zhandian-choose').html(str).val(localStorage.getItem('line'))
            createZhandian()
        }
    })
}
$('#zhandian-choose').on('change', function () {
    createZhandian();
})
$('#xianlu-choose').on('change', function () {
    changeDom($("#xianlu-choose").val())
})

// 站点下拉**********************************************************************
function createZhandian () {
    $.ajax({
        type: 'POST',
        url: Global.host + 'linesite/selectbyrouteid',
        data: {
            routeid : $('#zhandian-choose').val(),
            pageNum: 0,
            pageSize: 10000
        },
        success: function (res) {
            console.log(res);
            var str = '';
            for(var i = 0; i < res.list.length; i++) {
                str += '<option value=' + res.list[i].id + '>' + res.list[i].sitename + '</option>'
            }
            $('#xianlu-choose').html(str).val(localStorage.getItem('site'))
            changeDom($("#xianlu-choose").val())
        }
    })
}

/**
 * 提交
 */
var userCtrl = ''
$('.edit-ok').on('click', function () {
    // $postData = { 
    //     id: $('.edit-ok').attr('data-id'),
    //     rid: $('#xianlu-choose').val(),
    //     weather: $('.weather').val(),
    //     projectoverview: $('.projectoverview').val(),
    //     progressconstruction: $('.progressconstruction').val(),
    //     noticeowner: $('.noticeowner').val(),
    // }
    if(userCtrl == 'edit') { // 修改
        console.log('修改');
        $.ajax({
            type: 'POST',
            url: Global.host + 'mainpageinformaction/updatebyid',
            data: {
                id: $('.edit-ok').attr('data-id'),
                rid: $('#xianlu-choose').val(),
                weather: $('.weather').val(),
                projectoverview: $('.projectoverview').val(),
                progressconstruction: $('.progressconstruction').val(),
                noticeowner: $('.noticeowner').val(),
            },
            success: function (res) {
                console.log(res);
                alert('修改成功')
                localStorage.setItem('line',$('#zhandian-choose').val());
                localStorage.setItem('site',$('#xianlu-choose').val());
            }
        })
    } else if(userCtrl == 'add') { // 添加
        console.log('添加');
        $.ajax({
            type: 'POST',
            url: Global.host + 'mainpageinformaction/insert',
            data: {
                rid: $('#xianlu-choose').val(),
                weather: $('.weather').val(),
                projectoverview: $('.projectoverview').val(),
                progressconstruction: $('.progressconstruction').val(),
                noticeowner: $('.noticeowner').val(),
            },
            success: function (res) {
                console.log(res);
                alert('提交成功！')
                localStorage.setItem('line',$('#zhandian-choose').val());
                localStorage.setItem('site',$('#xianlu-choose').val());
            }
        })
    }

// 如果第一次登陆修改，保存site line
    if(!localStorage.getItem('line')) {
        localStorage.setItem('line', $('#zhandian-choose').val())
    }
    if(!localStorage.getItem('site')) {
        localStorage.setItem('line', $('#xianlu-choose').val())
    }
})

function createInp(res) {
    if(res.msg) {
        console.log('edit')
        userCtrl = 'edit';
        $('.content .weather').val(res.msg.weather);
        $('.content .projectoverview').val(res.msg.projectoverview);
        $('.content .progressconstruction').val(res.msg.progressconstruction);
        $('.content .noticeowner').val(res.msg.noticeowner);
    } else {
        console.log('add')
        userCtrl = 'add';
        $('.content .weather').val('请输入');
        $('.content .projectoverview').val('请输入');
        $('.content .progressconstruction').val('请输入');
        $('.content .noticeowner').val('请输入');
    }
}

function changeDom(id) {
    $.ajax({
        type: 'POST',
        url: Global.host + 'mainpageinformaction/selectbyrid',
        data: {
            rid: id
        },
        success: function (res) {
            console.log(res);
            createInp(res);
            $('.edit-ok').attr('data-id', res.msg.id)
        }
    })
}