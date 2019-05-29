//翻页
function page(id) {
    $.ajax({
        type: "POST",
        data: {
            id: id, // 站点id
            mName: '',
            pageNum: 0,
            pageSize: 10000
        },
        url: Global.host + "measurementproject/selectBy",
        success: function (res) {
            console.log('渲染分页');
            console.log(res);
            new Page({
                id: 'pagination',
                pageTotal: Math.ceil(res.listCount / 4), //必填,总页数
                pageAmount: 4,  //每页多少条
                dataTotal: res.listCount, //总共多少条数据
                curPage:1, //初始页码,不填默认为1
                pageSize: 5, //分页个数,不填默认为5
                showPageTotalFlag:true, //是否显示数据统计,不填默认不显示
                showSkipInputFlag:true, //是否支持跳转,不填默认不显示
                getPage: function (page) {
                    //获取当前页数
                   console.log(page);
                   createTable($('#zhandian-choose').val(),page-1,4);
                }
            })
        }
    })
}

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
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis : [
                    {
                        type : 'category',
                        data : [],//['X1', 'X2', 'X3', 'X4', 'X5', 'X6', 'X7'],
                        axisTick: {
                            alignWithLabel: true
                        }
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        axisLabel: { 
                            show: true, 
                            interval: 'auto', 
                            formatter: '{value} %' 
                        }, 
                        show: true 
                    }
                ],
                series : [
                    {
                        name:'提示',
                        type:'bar',
                        barWidth: '60%',
                        data: []//[1000, 1200, 1700, 1230, 1345, 1650, 1750]
                    }
                ]
            };
            for(var i = 0; i < res.listm.length; i++) {
                option.xAxis[0].data[i] = res.listm[i].ptime;
            }
            for(var i = 0; i < res.listm.length; i++) {
                option.series[0].data[i] = res.listm[i].failrate;
            }
            console.log(option)
            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        }
    })
}

// **********************************************************************
// 页面跳转
// $("#shouye").on("click", function () {
//     window.location.href = './index.html'
// })
$("#project").on("click", function () {
    window.location.href = './page/measure-project.html'
})
$("#archives").on("click", function () {
    window.location.href = './page/measure-archives.html'
})
$("#standard").on("click", function () {
    window.location.href = './page/measure-standard.html'
})
$("#site").on("click", function () {
    window.location.href = './page/measure-site.html'
})
$("#setup").on("click", function () {
    window.location.href = './page/measure-setup.html'
})
$("#manage").on("click", function () {
    window.location.href = './page/measure-manage.html'
})
$("#log").on("click", function () {
    window.location.href = './page/measure-log.html'
})
$("#count").on("click", function () {
    window.location.href = './page/measure-count.html'
})
$("#line").on("click", function () {
    window.location.href = './page/measure-lin.html'
})
// **********************************************************************
var flag = false;
$('ul li.title').on('click', function () {
    if(flag) {
    console.log('hide')
        $(".child-wrap").slideUp();
        $(".icon-title").removeClass("active");
    }
    if(!flag) {
        console.log('show')
        $(".child-wrap").slideDown();
        $(".icon-title").addClass("active");
    }
    flag = !flag;
})

// 渲染表格**************************************************************************************
// createTable(0, 4)
function createTable(id,num,pageSize) {
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
            page($("#zhandian-choose").val());
        }
    })
}
function createDom (arr, el) {
    console.log('渲染列表')
    var $body_str = '';
    var $table_head = '<thead>\
                        <tr>\
                            <th>项目名称</th>\
                            <th>检测时间</th>\
                            <th>预警统计</th>\
                        </tr>\
                    </thead>'
    for(var i = 0; i < arr.length; i++) {
        $body_str += '<tr>\
                        <td>' + arr[i].pname + '</td>\
                        <td>' + arr[i].ptime + '</td>\
                        <td>' + arr[i].warningstatistics + '</td>\
                    </tr>'
    }
    var $table = '<table class="gridtable" cellspacing="0">' + $table_head +'<tbody>' + $body_str + '</tbody></table>' 
    el.html($table)
}
// 线路下拉****************************************
$('#xianlu-choose').on('change', function () {
    console.log('change');
    createZhandian();
})
$('#zhandian-choose').on('change', function () {
    console.log('change');
    createWeather($('#zhandian-choose').val());
    // eChart($("#zhandian-choose").val());
    page($("#zhandian-choose").val());
})
createSelect();
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
            if(res.massage == '未登录') {
                alert('您未登录，请点击退出登陆到登陆页面登陆')
            }
            var str = '';
            for(var i = 0; i < res.list.length; i++) {
                str += '<option value="' + res.list[i].id + '">' + res.list[i].routename + '</option>'
            }
            $("#xianlu-choose").html(str).val(localStorage.getItem('line'));
            createZhandian();
        }
    })
}
// 站点下拉****************************************
// createZhandian();
function createZhandian() {
    $.ajax({
        type: "GET",
        url: Global.host + "linesite/selectbyrouteid",
        data: {
            routeid:$('#xianlu-choose').val(),
            pageNum: 0,
            pageSize: 10000
        },
        success: function (res) {
            console.log(res);
            var str = '';
            for(var i = 0; i < res.list.length; i++) {
                str += '<option value="' + res.list[i].id + '" data-id=" ' + res.list[i].id + '">' + res.list[i].sitename + '</option>'
            }
            $("#zhandian-choose").html(str).val(localStorage.getItem('site'));
            // createWeather(1);
            // createTable(0, 4)
            createWeather($('#zhandian-choose').val());
            createTable($('#zhandian-choose').val(),0,4);
            // eChart($("#zhandian-choose").val())
        }
    })
}
$('#zhandian-choose').on('change', function () {
    console.log('change');
    createWeather($('#zhandian-choose').val());
    createTable($('#zhandian-choose').val(),0,4);
})


// 渲染天气**************************************************************************************
// createWeather($('#zhandian-choose').val());
function createWeather ($id) {
    $.ajax({
        type: "POST",
        data: {rid: $id},
        url: Global.host + "mainpageinformaction/selectbyrid",
        success: function (res) {
            console.log(res);
            if(!res.msg) {
                alert('当前站点没有天气信息，请在站点设置页面添加')
                return;
            }
            var baseurl = './src/images/sunny.png'
            var imgurl = '';
            switch(res.msg.weather) {
                case '晴':
                    imgurl = './src/images/weather/晴.png'
                    break;
                case '多云':
                    imgurl = './src/images/weather/多云.png'
                    break;
                case '小雨':
                    imgurl = './src/images/weather/小雨.png'
                    break;
                case '小雪':
                    imgurl = './src/images/weather/小雪.png'
                    break;
                case '多云转晴':
                    imgurl = './src/images/weather/多云转晴.png'
                    break;
            }
            // var str = `<div class="weather">
            //                 <i class="icon-weather">
            //                     <img src="${imgurl}" alt="">
            //                 </i>
            //                 <div class="weather-con">
            //                     <span class="hot">${weatherArr[0]}</span>
            //                     <div class="weather-info">
            //                         <div>℃</div>
            //                         <div>${weatherArr[2]}</div>
            //                     </div>
            //                 </div>
            //                 <div class="tishi">
            //                     <div>${weatherArr[3]}</div>
            //                     <div>${weatherArr[4]}</div>
            //                     <div>${weatherArr[5]}</div>
            //                 </div>
            //             </div>`
            var str = '<div class="weather">\
                            <i class="icon-weather">\
                                <img src="${imgurl}" alt="">\
                            </i>\
                            <div class="tishi">\
                                <div>' + res.msg.weather + '</div>\
                            </div>\
                        </div>\
                            <ul class="gaikuang-info">\
                                <li>\
                                    <div class="title">项目概况</div>\
                                    <p>' + res.msg.projectoverview + '</p>\
                                </li>\
                                <li>\
                                    <div class="title">业主通知</div>\
                                    <p> ' + res.msg.noticeowner + '</p>\
                                </li>\
                                <li>\
                                    <div class="title">地理位置</div>\
                                    <p> ' + res.msg.geographical + '</p>\
                                </li>\
                                <li>\
                                    <div class="title">施工进度</div>\
                                    <p>' + res.msg.progressconstruction + '</p>\
                                </li>\
                            </ul>'
                $(".gaikuang").html(str)
        }
    })
}


$("#dropout-index").click(function(){
    var result = confirm("确认退出？")
    if (!result) {
        return
    }
    $.ajax({
        type:"get",
        url:Global.host+"administrator/dropout",
        success:function(data){
            location.href="./page/login.html";
        }
    });
});




createDomDown()
setInterval(function () {
    createDomDown()
}, 10000)
// ***渲染列表*******************************************************************
function createDomDown () {
    $.ajax({
        type:"get",
        dataType : 'json', 
        url:"http://120.92.10.2:81/railwayTurnout/detectionresult/getSmartCarData",
        success:function(res){
            console.log(res.msg)
            var headStr = '<table><thead><tr><th>编号</th><th>参数</th><th>实测参数</th><th>里程</th><th>统计分析</th></tr></thead>';
            var bodyStr = '';
            res.msg.forEach((item, index) => {
                var json = JSON.stringify(item.list)
                bodyStr += `<tr>
                            <td>${index + 1}</td>
                            <td>${item.name}</td>
                            <td>${item.list[0].num}</td>
                            <td>${item.mileage.num}</td>
                            <td>${item.list[0].sitename}</td>
                        </tr>`
            });
            bodyStr += '</table>';
            var table = headStr + bodyStr;
            $('.table-con-2').html(table);
        }
    });
}
// 关闭弹窗 弃用
// $('.alert-box').delegate('.close-alert-img', 'click', function () {
//     $('.alert-box').css('display','none')
// })

// alert-box 关闭所有弹窗
// $('.alert-box').on('click', function (event) {
//     event.preventDefault();
//     $('.alert-box').css('display','none')
// })


// 弃用
// $('.table-con-2').delegate('.alert-json', 'click', function () {
//     console.log($(this).attr('data-json'))
//     var arr = JSON.parse($(this).attr('data-json'));
//     console.log(arr)
//     var tableHead = '<div class="close">\
//                             <img class="close-alert-img" src="./src/images/close.png" alt="">\
//                         </div>\
//                         <table>\
//                             <thead>\
//                                 <tr>\
//                                     <th>编号</th>\
//                                     <th>实测参数</th>\
//                                     <th>分析</th>\
//                                 </tr>\
//                             </thead>';
//     var tableBody = '';
//     arr.forEach((item, index) =>{
//         tableBody += `<tbody>
//                         <tr>
//                             <td>${index + 1}</td>
//                             <td>${item.num}</td>
//                             <td>${item.sitename}</td>
//                         </tr>
//                     </tbody>`
//     })
//     var table = tableHead + tableBody + '</table>';
//     $('.table-con-3').html(table);
//     $('.alert-box').css('display','block')
// })