// 编辑弹窗
$(".table-con").delegate('.bianji','click', function () {
    $(".alert-wrapper").css("display","block");
    $(".add-alert").css("display","block");
})

// 关闭弹窗
$(".close").on('click', function () {
    $(".alert-wrapper").css("display","none");
    $(".add-alert").css("display","none");
    $(".alert-see").css("display","none");
    $(".add-new").css("display","none");
})
// 确认修改
$(".edit-ok").on('click', function () {
    $("_this_count .user-name").text($('.edit_user-name').val())
    $(".alert-wrapper").css("display","none");
})

//翻页********************************************************************
window.onload = function () {
    $.ajax({
        type: 'POST',
        url: Global.host + 'Systemlog/selectAlllog',
        data: {
            pageNum: 0,
            pageSize: 10
        },
        success: function (res) {
             console.log(res)
             new Page({
                 id: 'pagination',
                 pageTotal: Math.ceil(res.count / 10), //必填,总页数
                 pageAmount: 10,  //每页多少条
                 dataTotal: res.count, //总共多少条数据
                 curPage:1, //初始页码,不填默认为1
                 pageSize: 5, //分页个数,不填默认为5
                 showPageTotalFlag:true, //是否显示数据统计,不填默认不显示
                 showSkipInputFlag:true, //是否支持跳转,不填默认不显示
                 getPage: function (page) {
                     //获取当前页数
                    console.log(page);
                    createLog(page-1, 10);
                 }
             })
         }
    })
}

// **********************************************************************
// 渲染列表
function createDom (arr, el) {
    console.log(123)
    var $body_str = '';
    var $table_head = '<thead>\
                        <tr>\
                            <th>日志</th>\
                            <th>说明</th>\
                        </tr>\
                    </thead>'
    for(var i = 0; i < arr.length; i++) {
        $body_str += '  <tr>\
                            <td>' + arr[i].optDate + '</td>\
                            <td>' + arr[i].methodRemark + '</td>\
                        </tr>'
    }
    var $table = '<table class="gridtable" cellspacing="0">' + $table_head +'<tbody>' + $body_str + '</tbody></table>' 
    el.html($table)
}
createLog(0, 10);
function createLog(pageNum,pageSize) {
    $.ajax({
        type: 'POST',
        url: Global.host + 'Systemlog/selectAlllog',
        data: {
            pageNum: pageNum,
            pageSize: pageSize
        },
        success: function (res) {
             console.log(res)
             createDom (res.list, $(".table-con"))
         }
    })
}