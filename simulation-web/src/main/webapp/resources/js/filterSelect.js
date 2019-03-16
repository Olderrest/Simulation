$(document).ready(function () {
    $("#filterSelector").change(function () {
        var selected = this.value;
        var busFilter = $("#bus_filter");
        var stopFilter = $("#stop_filter");
        var routeFilter = $("#route_filter");
        if (selected === 'route') {
            busFilter.addClass("hidden_div");
            stopFilter.addClass("hidden_div");
            routeFilter.removeClass("hidden_div");
            setNegativeValue("bus_filter");
            setNegativeValue("stop_filter");
            setZero("route_filter");
        } else if (selected === 'bus') {
            routeFilter.addClass("hidden_div");
            stopFilter.addClass("hidden_div");
            busFilter.removeClass("hidden_div");
            setNegativeValue("stop_filter");
            setNegativeValue("route_filter");
            setZero("bus_filter");
        } else {
            routeFilter.addClass("hidden_div");
            stopFilter.removeClass("hidden_div");
            busFilter.addClass("hidden_div");
            setNegativeValue("bus_filter");
            setNegativeValue("route_filter");
            setZero("stop_filter");
        }
    })
});

function setNegativeValue(divName) {
    $("#"+ divName + " select option:first-child").val("-1");
}
function setZero(divName) {
    $("#"+ divName + " select option:first-child").val("0");
}