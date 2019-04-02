//翻页
function page() {
    $.ajax({
        type: 'POST',
        url: Global.host + 'measurementstandard/selectAll1',
        data: {
            pageNum: 0,
            pageSize: 10000
        },
        success: function(res) {
            console.log(res);
            new Page({
                id: 'pagination',
                pageTotal: Math.ceil(res.listCount / 10), //必填,总页数
                pageAmount: 10,  //每页多少条
                dataTotal: res.listCount, //总共多少条数据
                curPage:1, //初始页码,不填默认为1
                pageSize: 5, //分页个数,不填默认为5
                showPageTotalFlag:true, //是否显示数据统计,不填默认不显示
                showSkipInputFlag:true, //是否支持跳转,不填默认不显示
                getPage: function (page) {
                    //获取当前页数
                   console.log(page);
                   createTable(page-1, 10)
                }
            })
        }
    })
}
page();
// 修改****************************************************************
$(".table-con").delegate('.bianji','click', function () {
    $(".alert-wrapper").css("display","block");
    $(".add-alert").css("display","block");
    console.log($(this))
    $(".add-alert .edit-ok").attr('data-id',$(this).attr('data-id'));
    $(".add-alert .measurementitem").val($(this).attr('data-measurementitem') ||'请填写');
    $(".add-alert .state").val($(this).attr('data-state')||'请填写');
    $(".add-alert .ranges").val($(this).attr('data-ranges')||'请填写');
    $(".add-alert .standards").val($(this).attr('data-standards')||'请填写');
    $(".add-alert .range1").val($(this).attr('data-range1')||'请填写');
    $(".add-alert .standard1").val($(this).attr('data-standard1')||'请填写');
    
    $(".add-alert .standard").val($(this).attr('data-standard'))
})
// 关闭弹窗
$(".add-alert .close").on('click', function () {
    $(".alert-wrapper").css("display","none");
    $(".add-alert").css("display","none");
})
// 确认修改
$(".add-alert .edit-ok").on('click', function () {
    $(".alert-wrapper").css("display","none");
    $(".add-alert").css("display","none");
    $.ajax({
        type: 'POST',
        url: Global.host + 'measurementstandard/updatebyid',
        data: {
            id: $(this).attr('data-id'),
            measurementitem: $('.add-alert').find('.measurementitem').val(),
            standard: $('.add-alert').find('.standard').val()
        },
        success: function (res) {
            console.log(res);
            console.log('修改')
            createTable(0, 10)
        }
    })
})

// 新增*******************************************************************
$(".add-project").on('click', function () {
    $(".alert-wrapper").css("display","block");
    $(".add-new").css("display","block");
})
// 关闭弹窗
$(".add-new .close").on('click', function () {
    $(".alert-wrapper").css("display","none");
    $(".add-new").css("display","none");
})
// 确认新增
$(".add-new .edit-ok").on('click', function () {
    $(".alert-wrapper").css("display","none");
    $(".add-new").css("display","none");
    if(!$('.add-new').find('.measurementitem').val()) {
        alert('测量项不能为空');
        return;
    }
    if(!$('.add-new').find('.standard').val()) {
        alert('测量标准不能为空');
        return;
    }
    $.ajax({
        type: 'POST',
        url: Global.host + 'measurementstandard/insert',
        data: {
            measurementitem: $('.add-new').find('.measurementitem').val(),
            standard: $('.add-new').find('.standard').val(),
            standard1: $('.add-new').find('.standard1').val()
        },
        success: function (res) {
            console.log(res)
            createTable(0, 10)
            page();
        }
    })
})

// 删除*******************************************************************
$(".table-con").delegate('.shanchu','click', function () {
    console.log($(this).parents().eq(1).find('.bianji').attr('data-id'))
    console.log($(this).parents().eq(1).find('.bianji').attr('data-state'))
    if($(this).parents().eq(1).find('.bianji').attr('data-state') == 1) {
        alert('此项目不能删除！')
    } else {
        var mymessage=confirm("是否删除此项目？");
        if(mymessage==true) {
            $.ajax({
                type: 'POST',
                url: Global.host + 'measurementstandard/delete',
                data: {id: $(this).parents().eq(1).find('.bianji').attr('data-id')},
                success: function (res) {
                    console.log(res);
                    createTable(0, 10);
                }
            })
            alert("删除成功");
            page();
        }
    }
})



// ***渲染列表*******************************************************************
createTable(0, 10)
function createTable(pageNum,pageSize) {
    $.ajax({
        type: 'POST',
        url: Global.host + 'measurementstandard/selectAll1',
        data: {
            pageNum: pageNum,
            pageSize: pageSize
        },
        success: function(res) {
            console.log(res);
            createDom (res.list, $(".table-con"));
            // page();
        }
    })
}
function createDom (arr, el) {
    console.log('渲染列表')
    var $body_str = '';
    var $table_head = '<thead>\
                        <tr>\
                            <th>测量项</th>\
                            <th>测量标准</th>\
                            <th width="300">操作</th>\
                        </tr>\
                    </thead>'
    for(var i = 0; i < arr.length; i++) {
        if(i == 0) {
            $body_str += '  <tr>\
                                <td>' + arr[i].measurementitem + '</td>\
                                <td>+-' + arr[i].standard + '</td>\
                                <td>\
                                    <i class="bianji" \
                                        data-id="' + arr[i].id + '" \
                                        data-measurementitem="' + arr[i].measurementitem + '"\
                                        data-state="' + arr[i].state + '"\
                                        data-ranges="' + arr[i].ranges + '"\
                                        data-standard="' + arr[i].standard + '"\
                                        data-range1="' + arr[i].range1 + '"\
                                        data-standard1="' + arr[i].standard1 + '"\
                                        data-state="' + arr[i].state + '">\
                                            <img src="../src/images/bianji.png" alt="">\
                                    </i>\
                                    <i class="shanchu">\
                                        <img src="../src/images/shanchu.png" alt="">\
                                    </i>\
                                </td>\
                            </tr>'
        } else if(i == 1 || i == 2) {
            $body_str += '  <tr>\
                                <td>' + arr[i].measurementitem + '</td>\
                                <td><=' + arr[i].standard + '</td>\
                                <td>\
                                    <i class="bianji" \
                                        data-id="' + arr[i].id + '" \
                                        data-measurementitem="' + arr[i].measurementitem + '"\
                                        data-state="' + arr[i].state + '"\
                                        data-ranges="' + arr[i].ranges + '"\
                                        data-standard="' + arr[i].standard + '"\
                                        data-range1="' + arr[i].range1 + '"\
                                        data-standard1="' + arr[i].standard1 + '"\
                                        data-state="' + arr[i].state + '">\
                                            <img src="../src/images/bianji.png" alt="">\
                                    </i>\
                                    <i class="shanchu">\
                                        <img src="../src/images/shanchu.png" alt="">\
                                    </i>\
                                </td>\
                            </tr>'
        } else {
            $body_str += '  <tr>\
                                <td>' + arr[i].measurementitem + '</td>\
                                <td>' + arr[i].standard + '</td>\
                                <td>\
                                    <i class="bianji" \
                                        data-id="' + arr[i].id + '" \
                                        data-measurementitem="' + arr[i].measurementitem + '"\
                                        data-state="' + arr[i].state + '"\
                                        data-ranges="' + arr[i].ranges + '"\
                                        data-standard="' + arr[i].standard + '"\
                                        data-range1="' + arr[i].range1 + '"\
                                        data-standard1="' + arr[i].standard1 + '"\
                                        data-state="' + arr[i].state + '">\
                                            <img src="../src/images/bianji.png" alt="">\
                                    </i>\
                                    <i class="shanchu">\
                                        <img src="../src/images/shanchu.png" alt="">\
                                    </i>\
                                </td>\
                            </tr>'
        }      
        
    }
    var $table = '<table class="gridtable" cellspacing="0">' + $table_head +'<tbody>' + $body_str + '</tbody></table>'

    el.html($table)
}





