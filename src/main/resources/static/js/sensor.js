$(document).ready(function () {
    setInterval(getNewData, 3000)
})

function getNewData() {
    $.ajax({
        type: "POST",
        url: "/getdata",
        contentType: 'application/json',
        success: function (response) {

            for (let i = 0; i < response.length; i++) {
                $('#dataTable').append($("<tr/>", {
                    "id": response[i].name
                }))
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
                }))
                setTimeout(function () {
                    $('#'+response[i].name).remove()
                },2900)
            }
        },
        dataType: "json"
    });
}

function stop(name) {
    $.ajax({
        type: "GET",
        url: "/stop/"+name,
        contentType: 'application/json',
        success: function (response) {
        },
        dataType: "json"
    });
}