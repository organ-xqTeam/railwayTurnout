
createSelect();
// 获取线路
function createSelect() {
    $.ajax({
        type: "POST",
        url: Global.host + "trainroute/selectAll",
        data: {
            pageNum: 0,
            pageSize: 10000
        },
        success: function (res) {
            if(res.msg == "未登录") {
                window.location.href = './login.html'
            }
            console.log(res);
            var str = '';
            for(var i = 0; i < res.list.length; i++) {
                str += `<option value="${res.list[i].id}" data-lin-id="${res.list[i].id}">${res.list[i].routename}</option>`
            }
            $('#line-box').html(str);
            createSite()
        }
    })
}

// 获取站点
function createSite() {
    $.ajax({
        type: "POST",
        url: Global.host + "linesite/selectbyrouteid",
        data: {
            routeid:$('#line-box').val(),
            pageNum: 0,
            pageSize: 10000
        },
        success: function (res) {
            console.log(res);
            var str = '';
            for(var i = 0; i < res.list.length; i++) {
                str += `<option value="${res.list[i].id}" data-site-id="${res.list[i].id}" data-lin-id="${res.list[i].id}">${res.list[i].sitename}</option>`
            }
            $('#site-box').html(str);
            createTable($("#site-box").val(),0,100)
        }
    })
}
$('#line-box').on('change', function () {
    createSite()
    createProject($('#site-box').val())
})
$('#project-box').on('change', function () {
    createProject($('#site-box').val())
})

// 获取项目名
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
                str += `<option value="${res.list}">${res.list[i]}</option>`
            }
            $('#project-box').html(str);
        }
    })
}

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
            createDom(res.list, $(".result-box"));
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
                            <th>操作</th>\
                        </tr>\
                    </thead>'
    for(var i = 0; i < arr.length; i++) {
        $body_str += '  <tr>\
                            <td>'+arr[i].pname+ '</td>\
                            <td>'+arr[i].ptime+ '</td>\
                            <td>'+arr[i].warningstatistics+ '</td>\
                            <td>'+arr[i].ausername+ '</td>\
                            <td>\
                                <span data-toggle="modal" data-target="#myModal" class="see-btn" data-id="'+arr[i].id+ '" data-lid="'+arr[i].lid+ '" data-tid="'+arr[i].tid+ '" data-aid="'+arr[i].aid+ '">查看</span>\
                            </td>\
                        </tr>'
    }
    var $table = '<table class="table table-result">' + $table_head +'<tbody>' + $body_str + '</tbody></table>'
    el.html($table)
}





console.log('查看')
$('.result-box').delegate('.see-btn','click', function () {

    var str = `<!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">查看</h4>
                </div>
                <div class="modal-body">
                    
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
    <!-- Modal END -->`
    $('.result-box').append(str)
    console.log('查看')
    $.ajax({
        type: 'POST',
        url: Global.host + 'detectionresult/selectbyridpname', // 点击查看按钮
        data: {
            rid: $(this).attr('data-id')
        },
        success: function (res) {
            console.log(res.list);
            var $body_str = '';
            var $table_head = '<thead>\
                                <tr> \
                                    <th>检测项点</th>\
                                    <th>标准</th>\
                                    <th>实测数据</th>\
                                    <th>结果</th>\
                                </tr>\
                            </thead>'
            for(var i = 0; i < res.list.length; i++) {
                $body_str += '  <tr>\
                                    <td>'+res.list[i].data.pname+ '</td>\
                                    <td>'+res.list[i].data.standards+ '</td>\
                                    <td>'+res.list[i].data.measureddata+ '</td>\
                                    <td>'+res.list[i].data.measuredresults+ '</td>\
                                </tr>'
            }
            var $table = '<table class="table table-result">' + $table_head +'<tbody>' + $body_str + '</tbody></table>'
            $('.modal-body').html($table)
        }
    })
})