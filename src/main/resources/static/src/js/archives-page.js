//翻页
function createBar (id) {
    $.ajax({
        type: 'POST',
        url: Global.host + "measurementproject/selectBy",
        data: {
            id:	id,
            mName: '',
            pageNum: 0,
            pageSize: 10000
        },
        success: function (res) {
            console.log(res);
            new Page({
                id: 'pagination',
                pageTotal: Math.ceil(res.listCount / 10) , //必填,总页数
                pageAmount: 10,  //每页多少条
                dataTotal: res.listCount, //总共多少条数据
                curPage:1, //初始页码,不填默认为1
                pageSize: 5, //分页个数,不填默认为5
                showPageTotalFlag:true, //是否显示数据统计,不填默认不显示
                showSkipInputFlag:true, //是否支持跳转,不填默认不显示
                getPage: function (page) {
                    //获取当前页数
                   console.log(page);
                   createTable($('#zhandian-choose').val(), page-1, 10);
                }
            })
        }
    })
}


/**
 * 弹窗
 * 查看
 */
$(".table-con").delegate('.see-btn',"click", function (){
    $(".alert-wrapper").css('display', 'block');
    $(".alert-see").css('display', 'block');
    $.ajax({
        type: 'POST',
        url: Global.host + 'detectionresult/selectbyridpname',
        data: {
            rid: $(this).attr('data-id')
        }, // 项目id
        success: function (res) {
            console.log(res)
            create(res.list)
        }
    })
})

/**
 * 弹窗
 * 渲染
 */
function create(obj) {
    var $tbody = '';
    var arr = []
    obj.forEach(function (item, index) {
        arr[index] = '<tr>\
                        <td>' +item.data.pname+ '</td>\
                        <td>' +item.data.standards+ '</td>\
                        <td>' +item.data.measureddata+ '</td>\
                      <td>'
        if(item.data.measuredresults == '合格') {
            arr[index] += '<span class="hege">' + item.data.measuredresults + '</span></td></tr>'
        } else if(item.data.measuredresults == '不合格') {
            arr[index] += '<span class="hege-no">' + item.data.measuredresults + '</span></td></tr>'
        }
        $tbody += arr[index];
    })
    var str = '<!--startprint-->\
                <table class="gridtable" cellspacing="0">\
                <thead>\
                    <tr>\
                        <th>检测项点</th>\
                        <th>标准</th>\
                        <th>实测数据</th>\
                        <th>结果</th>\
                    </tr>\
                </thead>\
                <tbody>' + $tbody + '</tbody></table>\
                <!--endprint-->'
    $('.alert-table-wrap').html(str);
}


/**
 * 弹窗
 * 关闭弹窗
 */
$('.alert-wrapper .close').on('click', function () {
    $('.alert-wrapper').css('display','none');
    $('.alert-wrapper .add-new').css('display','none');
    $('.alert-wrapper .alert-see').css('display','none');
})


// 线路下拉****************************************
$('#xianlu-choose').on('change', function () {
    console.log('change');
    createZhandian();
})
$('#zhandian-choose').on('change', function () {
    console.log('change');
    createTable($('#zhandian-choose').val(), 0, 10);
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
                str += '<option value="'+ res.list[i].id+'">'+res.list[i].routename+'</option>'
            }
            $("#xianlu-choose").html(str).val(localStorage.getItem('line'));
            createZhandian();
        }
    })
}
// 站点下拉****************************************
function createZhandian() {
    $.ajax({
        type: "GET",
        url: Global.host + "linesite/selectbyrouteid",
        data: {
            routeid:$('#xianlu-choose').val(), // 线路id
            pageNum: 0,
            pageSize: 10000
        },
        success: function (res) {
            console.log(res);
            var str = '';
            for(var i = 0; i < res.list.length; i++) {
                str += '<option value="' + res.list[i].id+ '">'+res.list[i].sitename+'</option>'
            }
            $("#zhandian-choose").html(str).val(localStorage.getItem('site'));
            createTable($('#zhandian-choose').val(), 0, 10);
            createProject($('#zhandian-choose').val());
            createBar($('#zhandian-choose').val());
        }
    })
}
// 项目下拉****************************************
// createProject($('#zhandian-choose').val());
function createProject(id) {
    $.ajax({
        type: "POST",
        url: Global.host + "measurementproject/selectAllpName",
        data: {
            id: id // 站点id
        },
        data: {
            id:id,
            pageNum: 0,
            pageSize: 10000
        },
        success: function (res) {
            console.log(res);
            var str = '';
            for(var i = 0; i < res.list.length; i++) {
                str += '<option value="' + res.list[i] + '">' + res.list[i] + '</option>'
            }
            str = '<option value="ALL">请选择</option>' + str;
            $("#project-choose").html(str);
        }
    })
}




// ***渲染列表*******************************************************************
// createTable($('#zhandian-choose').val(), 0, 10);
function createTable(id,pageNum,pageSize) {
    $.ajax({
        type: 'POST',
        url: Global.host + "measurementproject/selectBy", // 查询检测报告【分页】
        data: {
            id:	id,  // 站点id
            mName: '', // 项目名
            pageNum: pageNum,
            pageSize: pageSize
        },
        success: function (res) {
            console.log(res);
            createDom(res.list, $(".table-con"));
        }
    })
}
function createDom (arr, el) {
    var $body_str = '';
    var $table_head = '<thead>\
                        <tr> \
                            <th>项目名称</th>\
                            <th>检测时间</th>\
                            <th>综合预警</th>\
                            <th>提交人</th>\
                            <th>联系电话</th>\
                            <th>操作</th>\
                        </tr>\
                    </thead>'
    for(var i = 0; i < arr.length; i++) {
        $body_str += '  <tr>\
                            <td>'+arr[i].pname+ '</td>\
                            <td>'+arr[i].ptime+ '</td>\
                            <td>'+arr[i].warningstatistics+ '</td>\
                            <td>'+arr[i].ausername+ '</td>\
                            <td>未填写</td>\
                            <td>\
                                <span class="see-btn" data-id="'+arr[i].id+ '" data-lid="'+arr[i].lid+ '" data-tid="'+arr[i].tid+ '" data-aid="'+arr[i].aid+ '">查看</span>\
                                <span class="download-btn" data-id="'+arr[i].id+ '" data-lid="'+arr[i].lid+ '" data-tid="'+arr[i].tid+ '" data-aid="'+arr[i].aid+ '">下载</span>\
                                <span class="printing-btn" data-id="'+arr[i].id+ '" data-lid="'+arr[i].lid+ '" data-tid="'+arr[i].tid+ '" data-aid="'+arr[i].aid+ '">打印</span>\
                            </td>\
                        </tr>'
    }
    var $table = '<table class="gridtable" cellspacing="0">' + $table_head +'<tbody>' + $body_str + '</tbody></table>' 
    el.html($table)
}




createProject($('#zhandian-choose').val());
/**
 * 根据项目下拉查询
 */
$("#project-choose").on('change', function () {
    $.ajax({
        type: 'POST',
        url: Global.host + "measurementproject/selectBy", // 查询检测报告【分页】
        data: {
            id:	$("#zhandian-choose").val(),  // 站点id
            mName: $("#project-choose").val(), // 项目名
            pageNum: 0,
            pageSize: 10000
        },
        success: function (res) {
            console.log(res);
            if($("#project-choose").val() != 'ALL') {
                createDom(res.list, $(".table-con"));
            }
        }
    })
})

/**
 * 下载表格
 */
$('body').delegate('.download-btn','click', function () {
    $('.alert-wrapper .add-new').css('display','block')
    $('.alert-wrapper').css('display','block')
    $('.down-ok').attr('data-id',$(this).attr('data-id'))
    // window.open(Global.host + 'detectionresult/getexcle?id=' + $(this).attr('data-id'));
})
$('.down-ok').on('click', function () {
    console.log($(this).attr('data-id'))
    // $.ajax({
    //     type: 'POST',
    //     url: Global.host + "detectionresult/getexcle", 
    //     data: {
    //         id:	$(this).attr('data-id'),
    //         filename: $('.measurementitem').val()
    //     },
    //     success: function (res) {
    //         console.log(res);
    //         // window.open(res)
    //     }
    // })
    window.open(Global.host + 'detectionresult/getexcle?id=' + $(this).attr('data-id')+ '&filename=' + $('.measurementitem').val()) 
    // window.location.href = Global.host + 'detectionresult/getexcle?id=' + $(this).attr('data-id') + '&filename=' + $('.measurementitem').val()
    $('.alert-wrapper .add-new').css('display','none')
    $('.alert-wrapper').css('display','none')
})
/**
 * 打印页面
 */
$('body').delegate('.printing-btn','click', function () {
    $.ajax({
        type: 'POST',
        url: Global.host + 'detectionresult/selectbyridpname',
        data: {
            rid: $(this).attr('data-id')
        }, // 项目id
        success: function (res) {
            console.log(res)
            create(res.list)


            var tr = $("tr", $('div.table-con'));  
            for(var i=0;i<tr.length;i++){    
                    for(var j=0;j<tr.eq(i).children().length;j++)  {
                        //获取td元素，也就是页面上的单元格  
                    var td = tr.eq(i).children().eq(j);    
                    td.removeClass("b0");    
                    td.addClass("b1");    
                    }     
                }  
        
        
            // 打印页面
            bdhtml=window.document.body.innerHTML;   
            sprnstr="<!--startprint-->";   
            eprnstr="<!--endprint-->";   
            prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);   
            prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));   
            window.document.body.innerHTML=prnhtml;  
            window.print();   
            // 还原页面
            window.document.body.innerHTML = bdhtml
            window.location.reload()
        }
    })
})
