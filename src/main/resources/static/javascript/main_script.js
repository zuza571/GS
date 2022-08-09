
$().ready(function() {
    let subtotal = 0;
    let listIds = [];

    // jak miec dostep do rozmiaru listy????
    //$('#cartCount').textContent =

    $('.add-to-cart').click(function () {
        let id = $(this).data('id');
        listIds.push(id)
        let url = `http://localhost:8080/add/cart/${id}/`
        console.log(url)
        fetch(url)
            .then(response => console.log("Success"))
            .catch(error => {
                console.log(error);
            });

        // todo: jak sie kilka razy doda to samo to sie nalicza
        // how many items in cart
        // reading from dataset (database value) - right after loading the page
        let count = parseInt(document.getElementById('cartCount').dataset.count)
        // dataset value + new values added after loading the page
        document.getElementById('cartCount').textContent = (listIds.length + count).toString()

        // item added to cart window
        let parent = $(this).parent().parent()
        let box = parent.find("#box-wrap")

        box.show();
        setTimeout(function() {
            box.fadeOut();
        }, 2000);


        let price = $(this).data('price');
        subtotal += price;
    });

    function change_value(factor){
        return function() {
            let id = $(this).data('id');

            if (factor === 1) {
                let url = `http://localhost:8080/add/quantity/${id}/`
                console.log(url)
                fetch(url)
                    .then(response => console.log("Success"))
                    .catch(error => {
                        console.log(error);
                    });
            }

            if (factor  === -1) {
                let url = `http://localhost:8080/subtract/quantity/${id}/`
                console.log(url)
                fetch(url)
                    .then(response => console.log("Success"))
                    .catch(error => {
                        console.log(error);
                    });
            }

            let parent = $(this).parent();
            let input = parseInt(parent.find(".amount-input").val());
            let count;
            if (factor === -1 && input === 1) {
                count = input;
            } else {
                count = input + factor;
            }
            parent.find("input.amount-input").val(count);
        }
    }

    $('.minus').click(change_value(-1));
    $('.plus').click(change_value(1));

    $('.remove-button').click(function () {
        let id = $(this).data('id');
        let price = $(this).data("price")
        subtotal -= price
        listIds.push(id)

        let url = `http://localhost:8080/remove/cart/${id}/`
        console.log(url)
        fetch(url)
            .then(response => console.log("Success"))
            .catch(error => {
                console.log(error);
            });
        $(this).parent().parent().parent().remove()

        // after removing the last element showing new content
        let boxCount = document.querySelectorAll(".cart-box")
        if (boxCount.length === 0) {
            document.getElementById("right-bar").style.display = "none"
            document.getElementById("empty-cart").style.display = "block"

        }

        // how many items in cart
        document.getElementById('cartCount').textContent = document.querySelectorAll(".cart-box").length.toString();
        // document.getElementById("#items-price").textContent=subtotal.toString() + " PLN";
    });

});