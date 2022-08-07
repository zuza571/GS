

$().ready(function() {
    let subtotal = 0;

    $('.add-to-cart').click(function () {
        const id = $(this).data('id');
        alert("id gry do koszyka: " + id + ", tytuł: ");
        // zrobic funkcje - jakies powiazania z baza danych
        const title = getTitle(id);
        const price = getPrice(id);
        const image = getImage(id);
    });


    $('.minus').click(function () {
        /*
        var parent = $(this).parent().parent();
        var input = parseInt(parent.find(".amount-input").val());
        var count = input - 1;
        //$(this).parent().parent().closest("input").value = count;
        parent.closest("input.amount-input").value = count;
         */

        // + before $ changes value form String to int
        if ($(this).next().val() > 1) {
            let quantity = $(this).next().val(+$(this).next().val() - 1);

            quantity = quantity.val();
            let subtotal = document.querySelector("#items-price").textContent = (quantity * 100).toString() + ' PLN';
            let total = document.querySelector("#items-price-total").textContent = ((quantity * 100) + 15).toString() + ' PLN';
            console.log(quantity);
            console.log(subtotal + ", " + total);
        }

    });

    $('.plus').click(function () {
        /*
        var parent = $(this).parent().parent();
        var input = parseInt(parent.find(".amount-input").val());
        var count = input + 1;
        parent.closest("input.amount-input").value = count;
        */

        let quantity = $(this).prev().val(+$(this).prev().val() + 1);

        quantity = quantity.val();
        let subtotal = document.querySelector("#items-price").textContent = (quantity * 100).toString() + ' PLN';
        let total = document.querySelector("#items-price-total").textContent = ((quantity * 100) + 15).toString() + ' PLN';
        console.log(quantity)
        console.log(subtotal + ", " + total);


    });

    $('.remove-button').click(function () {
        $(this).parent().parent().parent().remove();
    });

});