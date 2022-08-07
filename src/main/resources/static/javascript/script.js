

$().ready(function() {
    let subtotal = 0;

    $('.add-to-cart').click(function () {
        let id = $(this).data('id');
        let add_buttons = document.querySelectorAll(".btn-area")
        for (const btn of add_buttons){
            btn.addEventListener("click", event => {
                let url = `http://localhost:8080/add/cart/${id}/`
                console.log(url)
                fetch(url)
                    .then(response => console.log("Success") )
                    .catch(error => {
                        console.log(error);
                    });

            })
        }
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