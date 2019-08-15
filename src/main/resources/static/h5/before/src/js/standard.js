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
        }
    })
}

function createDom (arr, el) {
    console.log('渲染列表')
    var $body_str = '';
    var $table_head = '<thead>\
                        <tr>\
                            <th>序号</th>\
                            <th>测量项</th>\
                            <th>测量标准</th>\
                        </tr>\
                    </thead>'
    for(var i = 0; i < arr.length; i++) {
        $body_str += `  <tr>
                            <td>${i+1}</td>
                            <td>${arr[i].measurementitem}</td>
                            <td>${arr[i].standard}</td>
                        </tr>`
    }
    var $table = '<table class="table table-standard">' + $table_head +'<tbody>' + $body_str + '</tbody></table>' 
    el.html($table)
}
