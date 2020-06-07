var getList = false;//是否已经拿到数据

var stopList = [];  //已停止传感器列表

var flashTime = 3000

var timer;
$(document).ready(function () {
    timer = setInterval(getNewData, flashTime)
})

function getNewData() {
    $.ajax({
        type: "POST",
        url: "/getdata",
        contentType: 'application/json',
        success: function (response) {
            if(response!=null && !getList){
                console.log(response)
                getList = true
                getNameList();
            }
            for (let i = 0; i < response.length; i++) {
                $('#dataTable').append($("<tr/>", {
                    "id": response[i].name
                }))

                if(stopList.includes(response[i].name)){
                    $('#'+response[i].name).addClass("danger")
                }

                $('#'+response[i].name).append($("<td/>", {
                    "text": response[i].name
                })).append($("<td/>", {
                    "text": response[i].temperature
                })).append($("<td/>", {
                    "text": response[i].humidity
                })).append($("<td/>", {
                    "text": response[i].pressure
                })).append($("<td/>", {
                    "text": response[i].concentrationOfCO2
                })).append($("<td/>", {
                    "text": response[i].time
                }))
                setTimeout(function () {
                    $('#'+response[i].name).remove()
                },flashTime-100)
            }
        },
        dataType: "json"
    });
}
function getNameList() {
    $.ajax({
        type: "POST",
        url: "/getdata",
        contentType: 'application/json',
        success: function (response) {
            for (let i = 0; i < response.length; i++) {
                let name = response[i].name
                $('#closeList').append('<li><a onclick="stop(\''+name+'\')">'+name+'</a></li>')
                $('#historyList').append('<li><a onclick="getHistory(\''+name+'\')">'+name+'</a></li>')
            }
        },
        dataType: "json"
    });
}
function stop(name) {
    stopList.push(name)
    $.ajax({
        type: "GET",
        url: "/stop/"+name,
        contentType: 'application/json',
        success: function (response) {
        },
        dataType: "json"
    });
}

function changeTime() {
    var time = $('#flashTime').val();

    flashTime = time*1000
    clearInterval(timer)
    timer = setInterval(getNewData, flashTime)

    $.ajax({
        type: "GET",
        url: "/change",
        data:{
            "time":time
        },
        success: function (response) {
        },
        dataType: "json"
    });
}

function getHistory(name) {
    $('#historyTable').remove()
    $.ajax({
        type: "GET",
        url: "/gethistory",
        contentType: 'application/json',
        data:{
            "name":name
        },
        success: function (response) {
            $('#forTable').append("<table class=\"table table-bordered\" id=\"historyTable\" style=\"height: 500px;font-size: 20px\">\n" +
                "        <tr>\n" +
                "            <th style=\"width: 15%\">名称</th>\n" +
                "            <th style=\"width: 15%\">温度</th>\n" +
                "            <th style=\"width: 15%\">湿度</th>\n" +
                "            <th style=\"width: 15%\">压力</th>\n" +
                "            <th style=\"width: 15%\">二氧化碳浓度</th>\n" +
                "            <th style=\"width: 15%\">时间</th>\n" +
                "        </tr>\n" +
                "    </table>")
            for (let i = 0; i < response.length; i++) {
                $('#historyTable').append($("<tr/>", {
                    "id": 'h'+response[i].name+i
                }))

                $('#h'+response[i].name+i).append($("<td/>", {
                    "text": response[i].name
                })).append($("<td/>", {
                    "text": response[i].temperature
                })).append($("<td/>", {
                    "text": response[i].humidity
                })).append($("<td/>", {
                    "text": response[i].pressure
                })).append($("<td/>", {
                    "text": response[i].concentrationOfCO2
                })).append($("<td/>", {
                    "text": response[i].time
                }))
            }
        },
        dataType: "json"
    });
}