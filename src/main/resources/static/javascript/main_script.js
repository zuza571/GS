
$().ready(function() {
    let globalSubtotal = 0
    let total = 0
    // changed quantities after page load
    let globalQuantity = 0

    let listIds = []

    $('.add-to-cart').click(function () {
        let id = $(this).data('id')
        let isInCart = $(this).data('is_in_cart')

        // if item is already on the list, don't push it
        for (let i = 0; i < listIds.length; i++){
            if (id === listIds[i]) {
                isInCart = true
            }
        }

        if (isInCart === false) {
            listIds.push(id)
        }

        let url = `http://localhost:8080/add/cart/${id}/`
        console.log(url)
        fetch(url)
            .then(response => console.log("Success"))
            .catch(error => {
                console.log(error);
            });

        // how many items in cart
        // reading from dataset (page load value) - right after loading the page
        let count = parseInt(document.getElementById('cartCount').dataset.count)
        // dataset value + new values added after loading the page
        document.getElementById('cartCount').textContent = (listIds.length + count).toString()

        // item added to cart window
        let parent = $(this).parent().parent()
        let messageBox = parent.find("#box-wrap")
        let txt = parent.find("#box-txt")

        if (isInCart === true) {
            txt.text("You already have this item in your cart")
        }

        messageBox.show();
        setTimeout(function() {
            messageBox.fadeOut();
        }, 2000);

        // subtotal price
        let price = $(this).data('price');
        globalSubtotal += price;
    });

    function change_value(factor){
        return function() {
            let parent = $(this).parent();
            let id = $(this).data('id');
            let price = $(this).data('price');
            let subtotal = $(this).data('subtotal')
            // quantity from database
            let quantity = $(this).data('quantity');
            // current game quantity from database
            let game_quantity = $(parent.parent().find(".remove-button")).data('game_quantity');

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

            let input = parseInt(parent.find(".amount-input").val());
            let count;
            if (factor === -1 && input === 1) {
                count = input;
                quantity = quantity + globalQuantity;

            } else {
                // subtract old quantity * price
                globalSubtotal -= game_quantity * price

                // change quantity of product
                count = input + factor;
                // change cartCounter
                quantity = quantity + factor + globalQuantity;
                globalQuantity += factor;
                game_quantity += factor;

                // subtotal and total prices
                globalSubtotal += game_quantity * price
                subtotal += globalSubtotal
                total = subtotal +  15;
                document.getElementById("items-price").textContent = subtotal + " PLN";
                document.getElementById("items-price-total").textContent = total + " PLN";
            }

            parent.find("input.amount-input").val(count);
            document.getElementById("cartCount").textContent = quantity;
            // update game quantity in remove button to have current game quantity during removal
            $(parent.parent().find(".remove-button")).data("game_quantity", game_quantity);
        }
    }

    $('.minus').click(change_value(-1));
    $('.plus').click(change_value(1));

    $('.remove-button').click(function () {
        let id = $(this).data('id');
        let price = $(this).data("price");
        let subtotal = $(this).data('subtotal')
        // quantity from database
        let quantity = $(this).data('quantity');
        // current game quantity from database updated by + and -
        let game_quantity = $(this).data('game_quantity');

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

        // change cartCounter
        quantity = quantity + globalQuantity - game_quantity;
        globalQuantity -= game_quantity;
        document.getElementById("cartCount").textContent = quantity;

        // subtotal and total prices
        subtotal = (subtotal + globalSubtotal) - (game_quantity * price);
        globalSubtotal = globalSubtotal - (game_quantity * price);
        total = subtotal + 15;
        document.getElementById("items-price").textContent = subtotal + " PLN";
        document.getElementById("items-price-total").textContent = total + " PLN";
    });

    $('#submit-button').click(function () {
        let parent = $(this).parent().parent()
        let name = parent.find('#name').val()
        let surname = parent.find('#surname').val()
        let email = parent.find('#email').val()
        let phoneNumber = parent.find('#phoneNumber').val()
        let street = parent.find('#street').val()
        let postcode = parent.find('#postcode').val()
        let city = parent.find('#city').val()
        let moreInfo = parent.find('#moreInfo').val()

        let rightBar = parent.parent().parent()
        let games = rightBar.find('.checkout-box').text()
        // remove new lines
        games = games.replace(/\n/g, "")

        let orderData =
            name + " " + surname + " " + email + " " + phoneNumber + " " + street + " " + postcode + " " + city +
            " " + moreInfo + " " + games;

        // sending data to java
        $.ajax({
            type: 'POST',
            url: '/order',
            data: JSON.stringify(orderData),
            contentType: 'application/json'
        });
    })

});