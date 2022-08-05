
$().ready(function() {
    $('.minus').click(function () {
        let input = parseInt($("#amount-input").val());
        let count = input - 1;
        count = count < 1 ? 1 : count;

        document.querySelector('input').value = count;

        return false;
    });

    $('.plus').click(function () {
        let input = parseInt($("#amount-input").val());
        let count = input + 1;

        document.querySelector('input').value = count;

        return false;
    });

    $('#remove').click(function () {
       //let box = document.getElementsByClassName("box");
       $(this).parent().parent().parent().fadeOut();
    });
});