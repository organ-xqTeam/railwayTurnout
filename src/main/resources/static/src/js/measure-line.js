var $page = 1;   
//翻页
function page(page) {
    $.ajax({
        type: 'POST',
        url: Global.host + 'trainroute/selectAll',
        data: {
            pageNum: 0,
            pageSize: 10000
        },
        success: function (res) {
            console.log(res)
            new Page({
                id: 'pagination',
                pageTotal: Math.ceil(res.list.length / 10), //必填,总页数
                pageAmount: 10,  //每页多少条
                dataTotal: res.list.length, //总共多少条数据
                curPage:  page, // 1, //初始页码,不填默认为1
                pageSize: 5, //分页个数,不填默认为5
                showPageTotalFlag:true, //是否显示数据统计,不填默认不显示
                showSkipInputFlag:true, //是否支持跳转,不填默认不显示
                getPage: function (page) {
                    //获取当前页数
                    $page = page;
                   console.log(page);
                   createTable(page-1, 10)
                }
            })
        }
    })
}

// 渲染列表**********************************************************************
createTable(0,10);
function createDom (arr, el) {
    console.log('渲染table')
    var $body_str = '';
    var $table_head = '<thead>\
                        <tr>\
                            <th>线路名称</th>\
                            <th width="300">操作</th>\
                        </tr>\
                    </thead>'
    for(var i = 0; i < arr.length; i++) {
        $body_str += '  <tr>\
                            <td>' + arr[i].routename + '</td>\
                            <td>\
                                <i class="bianji" \
                                    data-name="' + arr[i].routename + '"\
                                    data-lid="' + arr[i].id + '">\
                                    <img src="../src/images/bianji.png" alt="">\
                                </i>\
                                <i class="shanchu" data-lid="' + arr[i].id + '">\
                                    <img src="../src/images/shanchu.png" alt="">\
                                </i>\
                            </td>\
                        </tr>'
    }
    var $table = '<table class="gridtable" cellspacing="0">' + $table_head +'<tbody>' + $body_str + '</tbody></table>' 
    el.html($table)
}
function createTable (pageNum,pageSize) {
    $.ajax({
        type: 'POST',
        data: {
            pageNum: pageNum,
            pageSize: pageSize
        },
        url: Global.host + 'trainroute/selectAll',
        success: function (res) {
            console.log(res)
            createDom (res.list, $(".table-con"))
            if(res.list.length > 0) {
                page($page)
                $("#pagination").css('display','block');
            } else {
                $("#pagination").css('display','none');
            }
        }
    })
}



// 编辑***********************************************************************************************
$(".table-con").delegate('.bianji',"click", function () {
    $(".alert-wrapper").css("display","block");
    $(".add-alert").css("display","block");
    $(".edit-wrap .line").val($(this).attr('data-name'));
})
// 关闭弹窗
$(".close").on("click", function () {
    $(".alert-wrapper").css("display","none");
})
// 确认修改
$(".ok").on("click", function () {
    $(".alert-wrapper").css("display","none");
})

// 添加线路
$(".add-line-btn").on("click", function () {
    $(".alert-wrapper").css("display","block");
    $(".add-line").css("display","block");
    $('.add-line-con').val('');
})
// 关闭弹窗
$(".add-line .close").on("click", function () {
    $(".alert-wrapper").css("display","none");
    $(".add-line").css("display","none");
})
// 却认添加
$(".add-line .ok").on("click", function () {
    $(".alert-wrapper").css("display","none");
    $(".add-line").css("display","none");
    if($('.add-line .add-line-con').val()) {
        $.ajax({
            type: 'POST',
            url: Global.host + 'trainroute/insert',
            data: {routename: $('.add-line .add-line-con').val()},
            success: function (res) {
                console.log(res)
                createTable(0,10);
            }
        })
    } else {
        alert('请输入正确的线路')
        return
    }
})

// 添加站点***********************************************************************
$(".add-site-btn").on("click", function () {
    $(".alert-wrapper").css("display","block");
    $(".add-site").css("display","block");
    $.ajax({
        type: 'POST',
        url: Global.host + 'trainroute/selectAlltrainAndline',
        data: {
            pageNum: 0,
            pageSize: 10000
        },
        success: function (res) {
            console.log(res)
            var str = '';
            for(var i = 0; i < res.map.length; i++) {
                str += '<option value=' + res.map[i].lid + '">' + res.map[i].routename + '</option>'
            }
            $("#zhandian").html(str)
        }
    })
})
// 关闭弹窗
$(".add-site .close").on("click", function () {
    $(".alert-wrapper").css("display","none");
    $(".add-site").css("display","none");
})
// 确认添加
$(".add-site .ok").on("click", function () {
    $(".alert-wrapper").css("display","none");
    $(".add-site").css("display","none");
    $.ajax({
        type: 'POST',
        url: Global.host + 'linesite/insert',
        data: {
            routeid: $("#zhandian").val(),
            sitename: $('.add-site .sitename').val()
        },
        success: function (res) {
            console.log(res)
            createTable(0,10);
        }
    })
})

// 修改***********************************************************************
$(".table-wrap").delegate('.bianji',"click", function () {
    $(".alert-wrapper").css("display","block");
    $(".edit-wrap").css("display","block");
    $('.edit-wrap .edit-ok').attr('data-lid',$(this).attr('data-lid'));
})
$('.edit-wrap .edit-ok').on('click', function () {
    $(".alert-wrapper").css("display","none");
    $(".edit-wrap").css("display","none");
    console.log($(this).attr('data-lid'))
    if($('.edit-wrap .line').val()) {
        $.ajax({
            type: 'POST',
            url: Global.host + 'trainroute/updatebyid',
            data: {
                id: $(this).attr('data-lid'),
                routename: $('.edit-wrap .line').val()
            },
            success: function (res) {
                console.log(res);
                createTable(0,10);
            }
        })
    } else {
        alert('请输入正确的线路')
        return
    }
})

// 删除***********************************************************************
$(".table-wrap").delegate('.shanchu', 'click', function () {
    console.log($(this).attr('data-lid'))
    var mymessage=confirm("是否删除此线路？");
    if(mymessage==true) {
        $.ajax({
            type: 'POST',
            url: Global.host + 'trainroute/delete',
            data: {
                id: $(this).attr('data-lid'),
            },
            success: function (res) {
                console.log(res);
                createTable(0,10);
                alert(res.message)
            }
        })
    }
})
