


// 弹窗
// 查看
$('.table-con').delegate('.see-btn','click', function () {
    $(".alert-wrapper").css('display', 'block');
    $(".alert-see").css('display', 'block');
})
// 关闭弹窗
$(".close").on('click', function () {
    $(".alert-wrapper").css('display', 'none');
    $(".alert-see").css('display', 'none');
})





// **********************************************************************
// 渲染列表
var arr = [
    {
        title: '数据1234',
        biaozun: '数据4515123414',
        xing: '✩✩✩✩✩ 95',
        a: 'asdgablsigbal',
        b: 'agloiuhliu'
    },{
        title: '数据1234',
        biaozun: '数据4515123414' 
    },{
        title: '数据1234',
        biaozun: '数据4515123414' 
    },{
        title: '数据1234',
        biaozun: '数据4515123414' 
    },{
        title: '数据1234',
        biaozun: '数据4515123414' 
    }
]
// createDom (arr, $(".table-con"))
function createDom (arr, el) {
    console.log(123)
    var $body_str = '';
    var $table_head = `<thead>
                        <tr>                            
                            <th>项目名称</th>
                            <th>检测时间</th>
                            <th>综合预警</th>
                            <th>提交人</th>
                            <th>联系电话</th>
                            <th>操作</th>
                        </tr>
                    </thead>`
    for(var i = 0; i < arr.length; i++) {
        $body_str += `  <tr>
                            <td>${arr[i].title}</td>
                            <td>${arr[i].biaozun}</td>
                            <td>${arr[i].xing}</td>
                            <td>${arr[i].a}</td>
                            <td>${arr[i].b}</td>
                            <td>
                                <span class="see-btn">查看</span>
                            </td>
                        </tr>`
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
    createEchart($("#xianlu-choose").val())
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
            // createDom($("#zhandian-choose").val(), $(".table-con"))
            createEchart($("#xianlu-choose").val())
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
            routeid:id,
            pageNum: 0,
            pageSize: 10000
        },
        success: function (res) {
            console.log(res);
            var str = '';
            for(var i = 0; i < res.list.length; i++) {
                str += '<option value="' + res.list[i] + '">' + res.list[i] + '</option>'
            }
            $("#project-choose").html(str);
        }
    })
}

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
        series: [
            // {
            //     name:'new站点',
            //     type:'line',
            //     stack: '总量',
            //     data:[33.33, 16.67, 33.33]
            // },
            // {
            //     name:'站点2',
            //     type:'line',
            //     stack: '总量',
            //     data:[33.33]
            // },
            // {
            //     name:'站点3',
            //     type:'line',
            //     stack: '总量',
            //     data:[150, 232, 201, 154, 190, 330, 410]
            // },
            // {
            //     name:'站点4',
            //     type:'line',
            //     stack: '总量',
            //     data:[320, 332, 301, 334, 390, 330, 320]
            // },
            // {
            //     name:'站点5',
            //     type:'line',
            //     stack: '总量',
            //     data:[820, 932, 901, 934, 1290, 1330, 1320]
            // }
        ]
    };
    console.log(res)
    for(var i = 0; i < res.listm.length; i++) {

        var len = 0;
        for(var z = 0; z < res.listm.length; z++) {
            if(len < res.listm[z].jsonArray.length) {
                len = res.listm[z].jsonArray.length
            }
        }
        console.log('len' , len)




        console.log(res.listm)
        option.legend.data[i] = res.listm[i].lidname;
        option.series[i] = {}
        option.series[i].name = res.listm[i].lidname;
        option.series[i].type = 'line';
        option.series[i].stack = '总量';
        option.xAxis.data = [];   // 数据
        option.series[i].data = [];


        var timeArr = [];
        for(var x= 0; x < res.listm[i].jsonArray.length; x ++) {
            console.log(res.listm[i].jsonArray[x].ptime)
            timeArr.push(res.listm[i].jsonArray[x].ptime)
        }
        console.log('timeArr',timeArr)










        for(var j = 0; j < res.listm[i].jsonArray.length; j++) {

            if(i > 0) {
                if(res.listm[i].jsonArray.length > res.listm[i-1].jsonArray.length) {
                    option.xAxis.data[j] = res.listm[i].jsonArray[j].ptime;
                }
            } else {
                option.xAxis.data[j] = res.listm[i].jsonArray[j].ptime;
            }

            option.series[i].data[j] = res.listm[i].jsonArray[j].failrate
            console.log('series[i].data[j] :' , option.series[i].data)
            console.log('res.listm[i].jsonArray[j].failrate :' , res.listm[i].jsonArray[j].failrate)
        }
    }

    console.log(option.xAxis.data)
    console.log(option)
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}