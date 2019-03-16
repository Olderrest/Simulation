$(document).ready(function () {
    var error = $("#error").val();
    if (error === "true") {
        $("#main_menu").toggle();
        $("#use_file").toggle();
    }
});

$(document).ready(function () {
    $('form input[type=file]').change(function () {
        if ($('form input[type=file]').val() != '') {
            $("#error_text").toggle();
        }
    });
});

$(document).ready(function () {
   var filename = $("#uploaded_file").val();
   if (filename != ''){
       $("#main_menu").toggle();
       $("#start_simulation").toggle();
   }
});

function backToChoseFromFile() {
    $("#use_file").toggle();
    $("#main_menu").toggle();
}
function backToChoseFromStart() {
    $("#start_simulation").toggle();
    $("#main_menu").toggle();
}

function start() {
    $.ajax({
        url: 'start',
        method: 'GET',
        //async: false,
        complete: function (data) {
            console.log(data.responseText);
        }
    });

    $("#start_simulation").toggle();
    $("#Progress_Status").toggle();

    var element = document.getElementById("myprogressBar");
    var width = 1;
    var identity = setInterval(scene, 100);

    function scene() {
        if (width >= 100) {
            clearInterval(identity);
            $("#complete").toggle();
        } else {
            width++;
            element.style.width = width + '%';
            element.innerHTML = width * 1 + '%';
        }
    }
}

function restart() {
    $.ajax({
        url: 'restart',
        method: 'GET',
        //async: false,
        complete: function (data) {
            console.log(data.responseText);
        }
    });

    $("#start_simulation").toggle();
    $("#Progress_Status").toggle();

    var element = document.getElementById("myprogressBar");
    var width = 1;
    var identity = setInterval(scene, 100);

    function scene() {
        if (width >= 100) {
            clearInterval(identity);
            $("#complete").toggle();
        } else {
            width++;
            element.style.width = width + '%';
            element.innerHTML = width * 1 + '%';
        }
    }
}

function file() {
    $("#main_menu").toggle();
    $("#use_file").toggle();
}

function useDefault() {
    $("#main_menu").toggle();
    $("#start_simulation").toggle();
}
