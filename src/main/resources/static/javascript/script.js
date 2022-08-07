
$().ready(function() {
    $('.minus').click(function () {
        var parent = $(this).closest(".amount");
        var input = parseInt(parent.find(".amount-input").val());
        console.log(input);
        var count = input - 1;
        console.log(count);

        // to zle dziala, ale w konsoli widac, ze dobrze zczytuje oba
        // wpisuje w zle miejsce
        //input['value'] = count;
        document.querySelector("input").value = count;
    });

    $('.plus').click(function () {
        var parent = $(this).closest(".amount");
        var input = parseInt(parent.find(".amount-input").val());
        console.log(input);
        var count = input + 1;
        console.log(count);

        //input['value'] = count;
        document.querySelector("input").value = count;
    });

    $('#remove').click(function () {
       $(this).parent().parent().parent().remove();
    });

});