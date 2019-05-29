// 判断是否登陆
if (!getCookie('user')) {
    window.location.href = './login.html'
}


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
            console.log(res);
            if (res.massage == '未登录') {
                window.location.href = './login.html'
            }
            console.log(123);
            $('.login-btn-w').css('display', 'block')
            var str = '';
            for (var i = 0; i < res.list.length; i++) {
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
            routeid: $('#line-box').val(),
            pageNum: 0,
            pageSize: 10000
        },
        success: function (res) {
            console.log(res);
            $('.login-btn-w').css('display', 'block')
            var str = '';
            for (var i = 0; i < res.list.length; i++) {
                str += `<option value="${res.list[i].id}" data-site-id="${res.list[i].id}" data-lin-id="${res.list[i].id}">${res.list[i].sitename}</option>`
            }
            $('#site-box').html(str);
            createResult($('#site-box').val(), 0, 5)
            // 图表 弃用 eChart($('#site-box').val())
            createWeather($('#site-box').val())
        }
    })
}
$('#line-box').on('change', function () {
    createSite()
    createResult($('#site-box').val(), 0, 5)
    // 图表 弃用 eChart($('#site-box').val())
    createWeather($('#site-box').val())
})
// 检测结果
function createResult(id, num, pageSize) {
    console.log(123)
    $.ajax({
        type: "POST",
        data: {
            id: id, // 站点id
            mName: '',
            pageNum: num,
            pageSize: pageSize
        },
        url: Global.host + "measurementproject/selectBy",
        success: function (res) {
            console.log(res);
            createDom(res.list, $(".table-con"))
        }
    })
}

function createDom(arr, el) {
    console.log('检测结果渲染')
    var $body_str = '';
    var $table_head = '<thead>\
                        <tr>\
                            <th>项目名称</th>\
                            <th>检测时间</th>\
                            <th>预警统计</th>\
                        </tr>\
                    </thead>'
    for (var i = 0; i < arr.length; i++) {
        $body_str += '<tr>\
                        <td>' + arr[i].pname + '</td>\
                        <td>' + arr[i].ptime + '</td>\
                        <td>' + arr[i].warningstatistics + '</td>\
                    </tr>'
    }
    var $table = '<table class="table table-bordered">' + $table_head + '<tbody>' + $body_str + '</tbody></table>'
    el.html($table)
}
// 点击更多
$('.more').on('click', function () {
    var str = `<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">检测结果</h4>
                            </div>
                            <div class="modal-body">
                                
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            </div>
                        </div>
                    </div>
                </div>`
    $('.result').append(str)
    $.ajax({
        type: "POST",
        data: {
            id: $('#site-box').val(), // 站点id
            mName: '',
            pageNum: 0,
            pageSize: 10000
        },
        url: Global.host + "measurementproject/selectBy",
        success: function (res) {
            console.log(res);
            createDom(res.list, $(".modal-body"))
        }
    })
})
/**
 * 图表
 */
function eChart(id) {
    $.ajax({
        type: 'POST',
        url: Global.host + 'measurementproject/GetHomeECharts',
        data: {
            id: id // id 108 假数据
        },
        success: function (res) {
            console.log(res);
            var dom = document.getElementById("container");
            var myChart = echarts.init(dom);
            var app = {};
            option = null;
            app.title = '统计图齐';
            option = {
                color: ['rgb(251, 212, 55)'],
                tooltip: {
                    trigger: 'axis',
                    axisPointer: { // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [{
                    type: 'category',
                    data: [], //['X1', 'X2', 'X3', 'X4', 'X5', 'X6', 'X7'],
                    axisTick: {
                        alignWithLabel: true
                    }
                }],
                yAxis: [{
                    type: 'value',
                    axisLabel: {
                        show: true,
                        interval: 'auto',
                        formatter: '{value} %'
                    },
                    show: true
                }],
                series: [{
                    name: '提示',
                    type: 'bar',
                    barWidth: '60%',
                    data: [] //[1000, 1200, 1700, 1230, 1345, 1650, 1750]
                }]
            };
            for (var i = 0; i < res.listm.length; i++) {
                option.xAxis[0].data[i] = res.listm[i].ptime;
            }
            for (var i = 0; i < res.listm.length; i++) {
                option.series[0].data[i] = res.listm[i].failrate;
            }
            console.log(option)
            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        }
    })
}

// 渲染天气

function createWeather($id) {
    $.ajax({
        type: "POST",
        data: {
            rid: $id
        },
        url: Global.host + "mainpageinformaction/selectbyrid",
        success: function (res) {
            console.log(res);
            if (!res.msg) {
                // alert('当前站点没有天气信息，请在站点设置页面添加')
                return;
            }
            var baseurl = './src/images/sunny.png'
            var imgurl = '';
            switch (res.msg.weather) {
                case '晴':
                    imgurl = '../img/duoyun@2x.png'
                    break;
                case '多云':
                    imgurl = '../img/duoyun@2x.png'
                    break;
                case '小雨':
                    imgurl = '../img/duoyun@2x.png'
                    break;
                case '小雪':
                    imgurl = '../img/duoyun@2x.png'
                    break;
                case '多云转晴':
                    imgurl = '../img/duoyun@2x.png'
                    break;
            }
            // <img src="./src/img/duoyun@2x.png" alt="">
            var str = `<div class="weather-title">
                            <div class="right">
                                <div></div>
                                <div>${res.msg.weather}</div>
                            </div>
                        </div>
                        <div class="title">
                            项目概况
                        </div>
                        <p>
                        ${res.msg.projectoverview}
                        </p>
                        <div class="title">
                            业主通知
                        </div>
                        <p>
                        ${res.msg.noticeowner}
                        </p>
                        <div class="title">
                            施工进度
                        </div>
                        <p>
                        ${res.msg.progressconstruction}
                        </p>
                        `
            $(".weather").html(str)
        }
    })
}








//设置cookie
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires + "; path=/"; //path=/是根路径
}

//获取cookie
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1);
        if (c.indexOf(name) != -1) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

//清除cookie
function clearCookie(name) {
    setCookie(name, "", -1);
}

$('#out').on('click', function () {
    window.location.href = './login.html'
})






// 新增的 table
createDomDown()
function createDomDown () {
    console.log(1234565)
    $.ajax({
        type:"get",
        dataType : 'json', 
        url:"http://120.92.10.2:81/railwayTurnout/detectionresult/getSmartCarData",
        success:function(res){
            console.log(res.msg)
            // var headStr = '<table class="table-bordered table"><thead><tr><th>编号</th><th>参数</th><th>实测参数</th><th>统计分析</th></tr></thead>';
            // var bodyStr = '';
            // res.msg.forEach((item, index) => {
            //     var json = JSON.stringify(item.list)
            //     bodyStr += `<tr>
            //                 <td>${index + 1}</td>
            //                 <td>${item.name}</td>
            //                 <td>${item.list[0].num}</td>
            //                 <td>${item.list[0].sitename}</td>
            //             </tr>`
            // });
            // bodyStr += '</table>';
            // var table = headStr + bodyStr;

            var headStr = '<table class="table-bordered table"><thead><tr><th>参数</th><th>实测参数</th><th>里程</th><th>统计分析</th></tr></thead>';
            var bodyStr = '';
            res.msg.forEach((item, index) => {
                var json = JSON.stringify(item.list)
                bodyStr += `<tr>
                            <td>${item.name}</td>
                            <td>${item.list[0].num}</td>
                            <td>${item.mileage.num}</td>
                            <td>${item.list[0].sitename}</td>
                        </tr>`
            });
            bodyStr += '</table>';
            var table = headStr + bodyStr;


            $('.table-content-add').html(table);
        }
    });
}