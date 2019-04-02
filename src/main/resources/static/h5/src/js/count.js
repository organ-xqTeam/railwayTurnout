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
            if (res.msg == "未登录") {
                window.location.href = './login.html'
            }
            console.log(res);
            var str = '';
            for (var i = 0; i < res.list.length; i++) {
                str += `<option value="${res.list[i].id}" data-lin-id="${res.list[i].id}">${res.list[i].routename}</option>`
            }
            $('#line-box').html(str);
            createEchart($('#line-box').val())
        }
    })
}



$('#line-box').on('change', function () {
    createEchart($('#line-box').val())
})


/**
 * Echart
 */
function createEchart($id) {
    $.ajax({
        type: 'GET',
        url: Global.host + "measurementproject/GetPageECharts", // ?id=47
        data: {
            id: $id
        },
        success: function (res) {
            console.log(res);
            createEchartDom(res)
        }
    })
}

function createEchartDom(res) {
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        title: {
            text: '测量统计'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: [] // ['new站点','new2']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: [] // ['一月','二月','三月','四月','五月','六月','七月']
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                show: true,
                interval: 'auto',
                formatter: '{value} %'
            },
            show: true
        },
        series: []
    };
    console.log(res)
    for (var i = 0; i < res.listm.length; i++) {

        var len = 0;
        for (var z = 0; z < res.listm.length; z++) {
            if (len < res.listm[z].jsonArray.length) {
                len = res.listm[z].jsonArray.length
            }
        }
        console.log('len', len)




        console.log(res.listm)
        option.legend.data[i] = res.listm[i].lidname;
        option.series[i] = {}
        option.series[i].name = res.listm[i].lidname;
        option.series[i].type = 'line';
        option.series[i].stack = '总量';
        option.xAxis.data = []; // 数据
        option.series[i].data = [];


        var timeArr = [];
        for (var x = 0; x < res.listm[i].jsonArray.length; x++) {
            console.log(res.listm[i].jsonArray[x].ptime)
            timeArr.push(res.listm[i].jsonArray[x].ptime)
        }
        console.log('timeArr', timeArr)


        for (var j = 0; j < res.listm[i].jsonArray.length; j++) {

            if (i > 0) {
                if (res.listm[i].jsonArray.length > res.listm[i - 1].jsonArray.length) {
                    option.xAxis.data[j] = res.listm[i].jsonArray[j].ptime;
                }
            } else {
                option.xAxis.data[j] = res.listm[i].jsonArray[j].ptime;
            }

            option.series[i].data[j] = res.listm[i].jsonArray[j].failrate
            console.log('series[i].data[j] :', option.series[i].data)
            console.log('res.listm[i].jsonArray[j].failrate :', res.listm[i].jsonArray[j].failrate)
        }
    }

    console.log(option.xAxis.data)
    console.log(option)
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}