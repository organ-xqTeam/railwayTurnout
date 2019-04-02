
// 全局变量*****************
var $page = 0,
    $pageSize = 8,
    $pagenum = 10,
    $table_over = false

// 初始化**********************************
createDom ($(".table-con"), $page, $pageSize)
console.log(123321)

// 列表渲染*******************************************************
function createDom (el, page, pageSize) {
    $.ajax({
        type: "POST",
        url: Global.host + "administrator/selectAlladmin",
        data: {
            pageNum: page,
            pageSize: pageSize
        },
        success: function (res) {
            $table_over = true;
            console.log(res)
            $pagenum = Math.ceil(res.listconut / pageSize);
            console.log($pagenum)
            $(".list-count").text($pagenum)
            var obj = res.array;
            var $body_str = '';
            var $table_head = '<thead>\
                                <tr>\
                                    <th>角色</th>\
                                    <th>人员姓名</th>\
                                    <th width="300">操作</th>\
                                </tr>\
                            </thead>'
            for(var i = 0; i < obj.length; i++) {
                $body_str +=    '<tr>\
                                    <td class="jiaoshe">' + obj[i].title + '</td>\
                                    <td class="user-name" data-id="' + obj[i].id + '">' + obj[i].name + '</td>\
                                    <td>\
                                        <i class="bianji">\
                                            <img src="../src/images/bianji.png" alt="">\
                                        </i>\
                                        <i class="delete-alert">\
                                            <img src="../src/images/shanchu.png" alt="">\
                                        </i>\
                                    </td>\
                                </tr>'
            }
            var $table = '<table class="gridtable" cellspacing="0">' + $table_head +'<tbody>' + $body_str + '</tbody></table>' 
            el.html($table)
        }
    });
}

// *弹窗*************************************************
// 编辑弹窗
$(".table-con").delegate('.bianji','click', function () {
    $(".alert-wrapper").css("display","block");
    $(".add-new").css("display","block");
    var $_this = $(this).parents().eq(1); // tr jquery 对象
    $(".add-new").find('.user-name').val($_this.find('.user-name').text());
    $(".add-new").find('.user-name').attr("data-id",$_this.find('.user-name').attr("data-id"));
})

// 新增 -- 确认修改
$(".edit-ok").on('click', function () {
    $(".alert-wrapper").css("display","none");
    console.log($(".add-new .user-name").val())
    var posData = {
        jurisdiction: '高级',
        ausername: $(".add-new .user-name").val(),
        aid: $(".add-new .user-name").attr("data-id"),
        apwd: $(".add-new .password").val()
    }
    console.log(posData)
    if(!$(".add-new .user-name").val()) {
        alert('账号不能为空')
        return;
    }
    $.ajax({
        type: "POST",
        url: Global.host + "administrator/update",
        data: posData,
        success: function (res) {
            console.log(posData)
            console.log(res)
            // 再次渲染列表
            createDom ($(".table-con"), 0 , 10);
            page()
        }
    })
})


// 关闭弹窗
$(".close").on('click', function () {
    $(".alert-wrapper").css("display","none");
    $(".add-alert").css("display","none");
    $(".alert-see").css("display","none");
    $(".add-new").css("display","none");
})


// 新增弹窗
$(".add-project").on('click', function () {
    $(".alert-wrapper").css("display","block");
    $(".add-alert").css("display","block");

})

// 新增 -- 确认修改
$(".add-ok").on('click', function () {
    $(".alert-wrapper").css("display","none");
    console.log($(".quanxian").val() , $(".edit_user-name").val())
    var posData = {
        jurisdiction: $(".quanxian").val(),
        ausername: $(".edit_user-name").val()
    }
    $.ajax({
        type: "get",
        url: Global.host + "administrator/insertadmin",
        data: posData,
        success: function (res) {
            console.log(posData)
            console.log(res)
            // alert(res.message)
            // 再次渲染列表
            $(".edit_user-name").val('')
            createDom ($(".table-con"), $page, $pageSize)
            page()
        }
    })
})


// 删除
$(".table-con").delegate('.delete-alert','click', function () {
    console.log('delete')
    console.log($(this).parents().eq(1).find('.user-name').attr('data-id'))
    $.ajax({
        type: "get",
        url: Global.host + "administrator/delete",
        data: {id: $(this).parents().eq(1).find('.user-name').attr('data-id')},
        success: function (res) {
            console.log(res)
            // 再次渲染列表
            createDom ($(".table-con"), $page, $pageSize);
            $(".edit_user-name").val('')
            page()
        }
    })

})

//翻页
function page() {
    $.ajax({
        type: "POST",
        url: Global.host + "administrator/selectAlladmin",
        data: {
            pageNum: 0,
            pageSize: $pageSize
        },
        success: function (res) {
            new Page({
                id: 'pagination',
                pageTotal: Math.ceil(res.listconut / $pageSize) , //必填,总页数
                pageAmount: $pageSize,  //每页多少条
                dataTotal: res.listconut, //总共多少条数据
                curPage:1, //初始页码,不填默认为1
                pageSize: 5, //分页个数,不填默认为5
                showPageTotalFlag:true, //是否显示数据统计,不填默认不显示
                showSkipInputFlag:true, //是否支持跳转,不填默认不显示
                getPage: function (page) {
                    //获取当前页数
                   console.log(page);
                   createDom ($(".table-con"), page-1, $pageSize);
                }
            })
        }
    })
}
page()