//翻页
function page() {
    $.ajax({
        type: "POST",
        url: Global.host + "measurementproject/selectAllpName",
        data: {
            id: localStorage.getItem('site'), // 站点id
            pageNum: 0,
            pageSize: 10000
        },
        success: function (res) {
            console.log(res);
            var $dataTotal = res.list.length;
            var $pageTotal = Math.ceil(res.list.length / 11)
            console.log($dataTotal, $pageTotal)
            new Page({
                id: 'pagination',
                pageTotal: $pageTotal, //必填,总页数
                pageAmount: 12, //每页多少条
                dataTotal: $dataTotal, //总共多少条数据
                curPage: 1, //初始页码,不填默认为1
                pageSize: 5, //分页个数,不填默认为5
                showPageTotalFlag: true, //是否显示数据统计,不填默认不显示
                showSkipInputFlag: true, //是否支持跳转,不填默认不显示
                getPage: function (page) {
                    //获取当前页数
                    console.log(page);
                    createCard(page - 1, 11)
                }
            })
        }
    })
}

// *******************************
// createSelect()
// createCard(0,11)
// *************************************
// 全局变量
var $id = '',
    $tid = '',
    $lid = ''


/**
 * 弹窗
 * 查看
 */
$(".project-wrap").delegate('.project-name', "click", function () {
    $(".alert-wrapper").css("display", "block");
    $(".alert-see").css("display", "block");
    var $id = $(this).attr('data-id')
    $.ajax({
        type: 'POST',
        url: Global.host + 'detectionresult/selectbyridpname',
        data: {
            rid: $(this).attr('data-id')
        }, // 项目id
        success: function (res) {
            console.log(res)
            console.log($id)
            create(res.list, $id)
        }
    })
})

/**
 * 弹窗
 * 渲染table
 */
function create(obj, $id) {
    console.log($id)
    var $tbody = '';
    var arr = []
    var imgArr = [];
    obj.forEach(function (item, index) {
        imgArr[index] = [];
        for (var i = 0; i < item.jsonArray.length; i++) {
            console.log(item.jsonArray)
            imgArr[index][i] = item.jsonArray[i];
        }
        var str = imgArr[index].join(",");
        console.log(str);
        console.log(index, imgArr)
        arr[index] = '<tr>\
                        <td>' + item.data.pname + '</td>\
                        <td>' + item.data.standards + '</td>\
                        <td>' + item.data.measureddata + '</td>\
                        <td class="btn see_btn"> <span data-id="' + $id + '" data-img="' + imgArr[index] + '"> 查看图片 </span></td>\
                      <td>'

        if (item.data.measuredresults == '合格') {
            arr[index] += '<span class="hege">' + item.data.measuredresults + '</span></td></tr>'
        } else if (item.data.measuredresults == '不合格') {
            arr[index] += '<span class="hege-no">' + item.data.measuredresults + '</span></td></tr>'
        }
        $tbody += arr[index];

    })
    var str = '<table class="gridtable" cellspacing="0">\
            <thead>\
                <tr>\
                    <th>检测项点</th>\
                    <th>标准</th>\
                    <th>实测数据</th>\
                    <th>查看图片</th>\
                    <th>结果</th>\
                </tr>\
            </thead>\
            <tbody>' + $tbody + '</tbody></table>'
    $('.table-wrap').html(str);
    console.log(obj)

    // var imgTable = '';
    // var imgArr = [];
    // obj.forEach(function (item, index) {
    //     imgArr[index] = '<li>\
    //                         <img src="' + Global.host + item.jsonArray + '" alt="">\
    //                     </li>'
    // })
    // $('.pic-wrap').html(imgArr)
}
/**
 * 显示图片弹窗
 */
$('.alert-see').delegate('.btn span', 'click', function () {
    $('.pic-wrap').css('display', 'block');
    // var $index = $('.see_btn').index($('.gridtable tbody tr'));
    // $.ajax({
    //     type: 'POST',
    //     url: Global.host + 'detectionresult/selectbyridpname',
    //     data: {
    //         rid: $(this).attr('data-id')
    //     }, // 项目id
    //     success: function (res) {
    //         console.log(res)
    //         console.log($index)
    //         var imgTable = '';
    //         var imgArr = [];
    //         for(var i = 0; i < res[$(this).index()].jsonArray.length; i++) {
    //             imgArr[i] = '<li>\
    //                             <img src="' + Global.host + res[$index].jsonArray[i] + '" alt="">\
    //                         </li>'
    //         }
    //         $('.pic-wrap').html(imgArr)
    //     }
    // })
    var res = $(this).attr('data-img').split(",")
    console.log(res)
    var imgArr = '';
    for (var i = 0; i < res.length; i++) {
        imgArr += '<li>\
                        <img src="' + Global.host + res[i] + '" alt="">\
                    </li>'
    }

    $('.pic-wrap').html(imgArr)
})




/** 
 * 弹窗
 * 关闭
 */
$(".alert-see .close").on("click", function () {
    $('.pic-wrap').css('display', 'none');
    $('.alert-see').css('display', 'none');
    $('.alert-wrapper').css('display', 'none');
})

/**
 * 修改
 */
$(".project-wrap").delegate('.edit-btn', 'click', function () {
    $id = $(this).attr('data-id');
    $tid = $(this).attr('data-tid');
    $lid = $(this).attr('data-lid');
    $(".add-alert .edit-paoject_name").val($(this).parent().find('.project-name').text());
    $(".alert-wrapper").css("display", "block");
    $(".add-alert").css("display", "block");
    $('.add-alert .edit-ok').attr('data-id', $(this).attr('data-id'))
})
/**
 * 修改关闭弹窗
 *  */
$(".close").on('click', function () {
    $(".alert-wrapper").css("display", "none");
    $(".add-alert").css("display", "none");
    $(".alert-see").css("display", "none");
})
/**
 *  确认修改
 *  */
$(".add-alert .edit-ok").on('click', function () {
    $(".project-name").text($('.edit-paoject_name').val())
    $(".alert-wrapper").css("display", "none");
    var formData = new FormData()
    var file = $(".add-alert .add-shuju")[0].files
    formData.append("id", $(this).attr('data-id')) // 项目id
    formData.append("aid", localStorage.getItem('aid')) // aid
    formData.append("tid", localStorage.getItem('line')) // 站点id
    formData.append("lid", localStorage.getItem('site')) // 线路id
    formData.append("projectName", $(".add-alert .edit-paoject_name").val())
    formData.append("file", $(".add-alert .add-shuju")[0].files[0])
    if (!$(".add-alert .edit-paoject_name").val()) {
        alert('项目名不能为空');
        createSelect()
        createCard(0, 11)
        return
    }
    $.ajax({
        type: "POST",
        url: Global.host + "measurementproject/multipleSave", //   // measurementproject/updatebyid
        data: formData,
        cache: false, // 上传文件无需缓存
        processData: false, // 用于对data参数进行序列化处理 这里必须false
        contentType: false, // 必须
        traditional: true,
        success: function (res) {
            console.log(res)
            init()
        }
    })
})

/**
 *  删除项目
 *  */
$(".project-wrap").delegate('.delete', 'click', function () {
    var r = confirm("是否删除此项目!");
    if (r == true) {
        console.log("用户确认删除");
        $.ajax({
            type: "POST",
            url: Global.host + "measurementproject/delete", 
            data: {
                id: $(this).attr('data-id')
            },
            success: function (res) {
                console.log(res)
                init()
            }
        })
    } else {
        console.log("用户取消删除");
    }

})
/**
 * 添加项目
 * 弹窗
 */
$(".project-wrap").delegate('.add-btn', 'click', function () {
    if (localStorage.getItem('site') == 'undefined') {
        alert('您未设置默认站点，请到站点设置页面设置')
        return
    }
    $(".add-new .edit-paoject_name").val('')
    $(".alert-wrapper").css("display", "block");
    $(".add-new").css("display", "block");
})

/**
 * 添加项目
 * 关闭弹窗
 */
$(".add-new .close").on('click', function () {
    $(".alert-wrapper").css("display", "none");
    $(".add-new").css("display", "none");
})

/**
 * 添加项目
 * 确认添加
 */
$(".add-new .edit-ok").on('click', function () {
    $(".alert-wrapper").css("display", "none");
    $(".add-new").css("display", "none");

    var formData = new FormData()
    var file = $(".add-new .add-shuju")[0].files
    formData.append("id", '')
    formData.append("aid", localStorage.getItem('aid')) // aid
    formData.append("tid", localStorage.getItem('site')) // 站点id
    formData.append("lid", localStorage.getItem('line')) // 线路id
    formData.append("projectName", $(".add-new .edit-paoject_name").val())
    formData.append("file", $(".add-shuju")[0].files[0])
    //    return
    if (!$(".add-new .edit-paoject_name").val()) {
        alert('请填写正确的项目名');
        return;
    }
    $.ajax({
        type: "POST",
        url: Global.host + "measurementproject/multipleSave",
        data: formData,
        cache: false, // 上传文件无需缓存
        processData: false, // 用于对data参数进行序列化处理 这里必须false
        contentType: false, // 必须
        traditional: true,
        success: function (res) {
            console.log(res)
            init()
        }
    });
})


/**
 * 查看所有项目
 * 渲染所有项目卡片
 */
// function createCard(num,pageSize) {
//     $.ajax({
//         type: "POST",
//         data: {
//             pageNum: num,
//             pageSize: pageSize
//         },
//         url: Global.host + "measurementproject/selectAll",
//         success: function (res) {
//             console.log(res)
//             createCardDom(res.list)
//             page();
//         }
//     });
// }


/**
 * 根据项目名筛选
 * change
 */
$("#zhandian").on("change", function () {
    $.ajax({
        type: "POST",
        url: Global.host + "measurementproject/selectBy",
        data: {
            id: localStorage.getItem('site'),
            mName: $("#zhandian").val(),
            pageNum: 0,
            pageSize: 10000
        },
        success: function (res) {
            console.log(res);
            createCardDom(res.list)
            if ($('#zhandian').val() == 'ALL') {
                console.log('all')
                init()
            }
        }
    })
})

/**
 * 根据项目名筛选
 * 渲染卡片
 */
function chooseCard(res) {
    var $str = '';
    $str = '<li class="edit-project"><div>\
                    <i>\
                        <img src="../src/images/xiamgmumingc.png" alt="">\
                    </i>\
                    <span class="project-name" \
                        data-id="' + res.id + '" \
                        data-tid="' + res.tid + '" \
                        data-lid="' + res.lid + '" \
                        data-pname="' + res.pname + '" \
                        data-ptime="' + res.ptime + '" \
                        data-warningstatistics="' + res.warningstatistics + '" \
                        data-datasources="' + res.datasources + '" \
                        data-isdelete="' + res.isdelete + '"><span>' + res.pname + '</span><i class="edit-icon"></i></span>\
                </div>\
                <div>\
                    <i>\
                        <img src="../src/images/shijian.png" alt="">\
                    </i>\
                    <span>' + res.ptime + '</span>\
                </div>\
                <div>\
                    <i>\
                        <img src="../src/images/baojing.png" alt="">\
                    </i>\
                    <span>' + res.warningstatistics + '</span>\
                </div>\
                <input class="edit-btn" \
                    data-id="' + res.id + '" \
                    data-tid="' + res.tid + '" \
                    data-lid="' + res.lid + '" \
                    data-pname="' + res.pname + '" \
                    data-ptime="' + res.ptime + '" \
                    data-warningstatistics="' + res.warningstatistics + '" \
                    data-datasources="' + res.datasources + '" \
                    data-isdelete="' + res.isdelete + '"\
                    type="submit" value="修改"></li>'

    var str1 = '<li class="add-project">\
                                <div>\
                                        <i>\
                                            <img src="../src/images/xiamgmumingc.png" alt="">\
                                        </i>\
                                        <span>项目名称</span>\
                                    </div>\
                                    <div>\
                                        <i>\
                                            <img src="../src/images/shijian.png" alt="">\
                                        </i>\
                                        <span>检测时间</span>\
                                    </div>\
                                    <div>\
                                        <i>\
                                            <img src="../src/images/baojing.png" alt="">\
                                        </i>\
                                        <span>报警统计</span>\
                                    </div>\
                                    <input class="add-btn" type="submit" value="添加">\
                                </li>'
    $str = str1 + $str;
    $(".project-wrap").html($str)
}


/**
 * 初始化
 * 查询检测报告
 */
function init() {
    $.ajax({
        type: 'POST',
        url: Global.host + "measurementproject/selectBy", // 查询检测报告【分页】
        data: {
            id: localStorage.getItem('site'), // 站点id
            mName: '', // 项目名
            pageNum: 0,
            pageSize: 10000
        },
        success: function (res) {
            console.log(res);
            createProject();
            createCardDom(res.list)
            page()
            if (localStorage.getItem('site') == 'undefined') {
                alert('您未设置默认站点，请到站点设置页面设置')
            }
        }
    })
}
init();
/**
 * 下拉框
 * 渲染
 */
function createProject() {
    $.ajax({
        type: "POST",
        url: Global.host + "measurementproject/selectAllpName",
        data: {
            id: localStorage.getItem('site'), // 站点id
            pageNum: 0,
            pageSize: 10000
        },
        success: function (res) {
            console.log(res);
            var str = '';
            for (var i = 0; i < res.list.length; i++) {
                str += '<option value="' + res.list[i] + '">' + res.list[i] + '</option>'
            }
            str = '<option value="ALL">请选择</option>' + str;
            $("#zhandian").html(str);
        }
    })
}


/**
 * 渲染卡片函数
 */
function createCardDom(res) {
    console.log(res)
    var $str = '';
    var $strArr = [];
    for (var i = 0; i < res.length; i++) {
        var $li = document.createElement('li');
        $($li).addClass('edit-project')
        if (!res[i].lAltitude) {
            res[i].lAltitude = '未填写'
        }
        if (!res[i].rAltitude) {
            res[i].rAltitude = '未填写'
        }
        $str = '<div>\
                    <i>\
                        <img src="../src/images/xiamgmumingc.png" alt="">\
                    </i>\
                    <span class="project-name" \
                        data-id="' + res[i].id + '" \
                        data-tid="' + res[i].tid + '" \
                        data-lid="' + res[i].lid + '" \
                        data-pname="' + res[i].pname + '" \
                        data-ptime="' + res[i].ptime + '" \
                        data-warningstatistics="' + res[i].warningstatistics + '" \
                        data-datasources="' + res[i].datasources + '" \
                        data-isdelete="' + res[i].isdelete + '"><em>' + res[i].pname + '</em><i class="edit-icon"></i></span>\
                </div>\
                <div>\
                    <i>\
                        <img src="../src/images/shijian.png" alt="">\
                    </i>\
                    <span>' + res[i].ptime + '</span>\
                </div>\
                <div>\
                    <i>\
                        <img src="../src/images/baojing.png" alt="">\
                    </i>\
                    <span>' + res[i].warningstatistics + '</span>\
                </div>\
                <div>\
                    <i>\
                        <img src="../src/images/lg.png" alt="">\
                    </i>\
                    <span>' + res[i].lAltitude + '</span>\
                </div>\
                <div>\
                    <i>\
                        <img src="../src/images/rg.png" alt="">\
                    </i>\
                    <span>' + res[i].rAltitude + '</span>\
                </div>\
                <input class="edit-btn" \
                    data-id="' + res[i].id + '" \
                    data-tid="' + res[i].tid + '" \
                    data-lid="' + res[i].lid + '" \
                    data-pname="' + res[i].pname + '" \
                    data-ptime="' + res[i].ptime + '" \
                    data-warningstatistics="' + res[i].warningstatistics + '" \
                    data-datasources="' + res[i].datasources + '" \
                    data-isdelete="' + res[i].isdelete + '"\
                    type="submit" value="修改">\
                    <img class="delete" data-id="' + res[i].id + '" src="../src/images/shanchu.png" alt="">'
        $($li).html($str);
        $strArr[i] = $li;
    }
    var str1 = '<li class="add-project">\
                        <div>\
                            <i>\
                                <img src="../src/images/xiamgmumingc.png" alt="">\
                            </i>\
                            <span>项目名称</span>\
                        </div>\
                        <div>\
                            <i>\
                                <img src="../src/images/shijian.png" alt="">\
                            </i>\
                            <span>检测时间</span>\
                        </div>\
                        <div>\
                            <i>\
                                <img src="../src/images/baojing.png" alt="">\
                            </i>\
                            <span>报警统计</span>\
                        </div>\
                        <input class="add-btn" type="submit" value="添加">\
                    </li>'
    $strArr.unshift(str1);
    $(".project-wrap").html($strArr)
}