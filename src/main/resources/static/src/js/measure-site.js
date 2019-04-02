//翻页
var $page = 1;
function page($id, page) {
    $.ajax({
        type: 'POST',
        url: Global.host + 'linesite/selectbyrouteid',
        data: {
            routeid: $id,  // page($("#xianlu-choose").val())
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
                curPage: page, //初始页码,不填默认为1
                pageSize: 5, //分页个数,不填默认为5
                showPageTotalFlag:true, //是否显示数据统计,不填默认不显示
                showSkipInputFlag:true, //是否支持跳转,不填默认不显示
                getPage: function (page) {
                    //获取当前页数
                   console.log(page);
                   $page = page;
                   createTable($('#xianlu-choose').val(),page-1, 10)
                }
            })
        }
    })
}

// 渲染列表**********************************************************************
// createTable($('#xianlu-choose').val(),0,10);
function createDom (arr, el) {
    console.log('渲染table')
    var $body_str = '';
    var $table_head = '<thead>\
                        <tr>\
                            <th>站点名称</th>\
                            <th width="300">操作</th>\
                        </tr>\
                    </thead>'
    for(var i = 0; i < arr.length; i++) {
        $body_str += '  <tr>\
                            <td>'+ arr[i].sitename + '</td>\
                            <td>\
                                <i class="bianji" \
                                    data-name="'+ arr[i].sitename + '"\
                                    data-id="'+ arr[i].id + '">\
                                    <img src="../src/images/bianji.png" alt="">\
                                </i>\
                                <i class="shanchu" \
                                    data-id="'+ arr[i].id + '"\
                                    data-lid="'+ arr[i].routeid + '">\
                                    <img src="../src/images/shanchu.png" alt="">\
                                </i>\
                            </td>\
                        </tr>'
    }
    var $table = '<table class="gridtable" cellspacing="0">' + $table_head +'<tbody>' + $body_str + '</tbody></table>' 
    el.html($table)
}
/**
 * 渲染表格 
 * ajax
 */
function createTable (id,pageNum,pageSize) {
    $.ajax({
        type: 'POST',
        url: Global.host + '/linesite/selectbyrouteid', 
        data: {
            routeid:id,   // 线路id
            pageNum: pageNum,
            pageSize: pageSize
        },  
        success: function (res) {
            console.log(res)
            createDom (res.list, $(".table-con"))
            if(res.list.length > 0) {
                page($("#xianlu-choose").val(), $page);
                $("#pagination").css('display','block');
            } else {
                $("#pagination").css('display','none');
            }
        }
    })
}
// 添加线路**********************************************************************************
$(".add-site-btn").on("click", function () {
    $(".alert-wrapper").css("display","block");
    $(".add-line").css("display","block");
})
// 关闭弹窗
$(".add-site .close").on("click", function () {
    $(".alert-wrapper").css("display","none");
    $(".add-line").css("display","none");
})
// 却认添加
// $(".add-site .ok").on("click", function () {
//     $(".alert-wrapper").css("display","none");
//     $(".add-line").css("display","none");
//     console.log($('#zhandian').val())
//     $.ajax({
//         type: 'POST',
//         url: Global.host + 'trainroute/insert',
//         data: {
//             routeid : $("#zhandian").val(),
//             routename: $('#zhandian').val()
//         },
//         success: function (res) {
//             console.log(res)
//             createTable($('#xianlu-choose').val(),0,10);
//         }
//     })
// })


/***
 *  查看所有线路
 *  下拉
 *  */ 
$('#xianlu-choose').on('change', function () {
    console.log('change');
    createTable($('#xianlu-choose').val(),0,10);
})
createXianlu();
function createXianlu() {
    $.ajax({
        type: "POST",
        url: Global.host + "trainroute/selectAll",
        data: {
            pageNum: 0,
            pageSize: 10000
        },
        success: function (res) {
            console.log(res);
            var str = '';
            for(var i = 0; i < res.list.length; i++) {
                str += '<option value="' + res.list[i].id + '">' + res.list[i].routename + '</option>'
            }
            $("#xianlu-choose").html(str).val(localStorage.getItem('line'));
            createTable($('#xianlu-choose').val(),0,10);
        }
    })
}
/***
 *  查看所有线路
 *  下拉 弹窗
 *  */ 
$(".add-site-btn").on("click", function () {
    $(".alert-wrapper").css("display","block");
    $(".add-site").css("display","block");
    $.ajax({
        type: 'POST',
        url: Global.host + 'trainroute/selectAll',
        data: {
            pageNum: 0,
            pageSize: 10000
        },
        success: function (res) {
            console.log(res)
            var str = '';
            for(var i = 0; i < res.list.length; i++) {
                str += '<option value="'+res.list[i].id+'">'+ res.list[i].routename + '</option>'
            }
            $("#zhandian").html(str)
            $("#zhandian").val($("#xianlu-choose").val())
        }
    })
})
/***
 *  查看所有线路
 *  关闭弹窗
 *  */ 
$(".add-site .close").on("click", function () {
    $(".alert-wrapper").css("display","none");
    $(".add-site").css("display","none");
})


/***
 *  站点
 *  确认添加
 *  */ 
$(".add-site .ok").on("click", function () {
    $(".alert-wrapper").css("display","none");
    $(".add-site").css("display","none");
    if($('.add-site .sitename').val()) {
        $.ajax({
            type: 'POST',
            url: Global.host + 'linesite/insert',
            data: {
                routeid: $("#zhandian").val(),
                sitename: $('.add-site .sitename').val()
            },
            success: function (res) {
                console.log(res)
                createTable($('#xianlu-choose').val(),0,10);
            }
        })
    } else {
        alert('请输入正确的站点')
        return
    }
    $('.add-site .sitename').val('');
})

/***
 *  站点
 *  修改
 *  */ 
$(".table-wrap").delegate(' .bianji',"click", function () {
    $(".alert-wrapper").css("display","block");
    $(".edit-wrap").css("display","block");
    $('.edit-wrap .edit-ok').attr('data-id',$(this).attr('data-id'));
    $('.edit-wrap .edit-ok').attr('data-lid',$(this).attr('data-lid'));
    $('.edit-wrap .zhandian').val($(this).attr('data-name'));
})
$('.edit-wrap .edit-ok').on('click', function () {
    $(".alert-wrapper").css("display","none");
    $(".edit-wrap").css("display","none");
    $postData = {
        id: $(this).attr('data-id'),
        routeid: $('#xianlu-choose').val(), // 线路id
        sitename: $('.edit-wrap .zhandian').val()
    }
    console.log($postData)
    console.log($(this).attr('data-lid'))
    if($('.edit-wrap .zhandian').val()) {
        $.ajax({
            type: 'POST',
            url: Global.host + 'linesite/updatebyid',
            data: $postData,
            success: function (res) {
                console.log(res);
                createTable($('#xianlu-choose').val(),0,10);
            }
        })
    } else {
        alert('请输入正确的站点')
        return
    }
})
$('.edit-wrap .close').on('click', function () {
    $(".alert-wrapper").css("display","none");
    $('.edit-wrap').css('display','none')
})
// 删除站点***********************************************************************
$(".table-wrap").delegate('.shanchu','click', function () {
    console.log('删除')
    var mymessage=confirm("是否删除此站点？");
    if(mymessage==true) {
        $.ajax({
            type: 'POST',
            url: Global.host + 'linesite/delete',
            data: {
                id: $(this).attr('data-id'),
                rid: $(this).attr('data-lid')
            },
            success: function (res) {
                console.log(res);
                createTable($('#xianlu-choose').val(),0,10);
            }
        })
    }
})
