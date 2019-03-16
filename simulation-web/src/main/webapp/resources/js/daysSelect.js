$(document).ready(function () {
    $("#month_select").change(function () {
        var selected = this.value;
        if (selected === '2') {
            $("#day_selectFrom option").each(function () {
                if (this.value > 28) {
                    this.remove();
                }
            });
            $("#day_selectUntil option").each(function () {
                if (this.value > 28) {
                    this.remove();
                }
            });
        } else if (selected % 2 !== 0) {
            var lastValue = $('#day_selectFrom option:last-child').val();
            if (lastValue === '28') {
                $("#day_selectFrom").append("<option value='29'>29</option>");
                $("#day_selectFrom").append("<option value='30'>30</option>");
                $("#day_selectUntil").append("<option value='29'>29</option>");
                $("#day_selectUntil").append("<option value='30'>30</option>");
            } else {
                $("#day_selectFrom option[value='31']").remove();
                $("#day_selectUntil option[value='31']").remove();
            }
        } else {
            var lastValue = $('#day_selectFrom option:last-child').val();
            if (lastValue === '28') {
                $("#day_selectFrom").append("<option value='29'>29</option>");
                $("#day_selectFrom").append("<option value='30'>30</option>");
                $("#day_selectFrom").append("<option value='31'>31</option>");
                $("#day_selectUntil").append("<option value='29'>29</option>");
                $("#day_selectUntil").append("<option value='30'>30</option>");
                $("#day_selectUntil").append("<option value='31'>31</option>");
            } else {
                if (lastValue !== '31') {
                    $("#day_selectFrom").append("<option value='31'>31</option>");
                    $("#day_selectUntil").append("<option value='31'>31</option>");
                }
            }
        }
    });
});