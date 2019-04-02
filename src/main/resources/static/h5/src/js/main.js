var Global = (function () {
    var host = "http://120.92.10.2:81/railwayTurnout/";
    var url = "http://120.92.10.2:81/railwayTurnout/";
    return {
        host: host,
        url: url
    }
})()


$("#index").on('click', function () {
    window.location.href = './index.html'
})
$("#archives").on('click', function () {
    window.location.href = './archives.html'
})
$("#standatd").on('click', function () {
    window.location.href = './standard.html'
})
$("#count").on('click', function () {
    window.location.href = './count.html'
})


