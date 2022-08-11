
$().ready(function() {
    let subtotal = 0;
    let listIds = [];
    let globalQuantity = 0;

    $('.add-to-cart').click(function () {
        let id = $(this).data('id');

        // todo: po odswiezeniu strony tez mozna ponownie dodac rzeczy ktore sa w koszyku
        // if item is already on the list, don't push it
        let isOnTheList = 0
        for (let i = 0; i < listIds.length; i++){
            if (id === listIds[i]) {
                isOnTheList = 1
            }
        }

        if (isOnTheList === 0) {
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
        // reading from dataset (database value) - right after loading the page
        let count = parseInt(document.getElementById('cartCount').dataset.count)
        // dataset value + new values added after loading the page
        document.getElementById('cartCount').textContent = (listIds.length + count).toString()

        // item added to cart window
        let parent = $(this).parent().parent()
        let messageBox = parent.find("#box-wrap")
        let txt = parent.find("#box-txt")

        if (isOnTheList === 1) {
            txt.text("You already have this item in your cart")
        }

        messageBox.show();
        setTimeout(function() {
            messageBox.fadeOut();
        }, 2000);

        // subtotal price
        let price = $(this).data('price');
        subtotal += price;
    });

    function change_value(factor){
        return function() {
            let id = $(this).data('id');
            let quantity = $(this).data('quantity');
            let game_quantity = $(document.getElementsByClassName("remove-button")).data('data_quantity');

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
                quantity = quantity + globalQuantity;
            } else {
                count = input + factor;
                quantity = quantity + factor + globalQuantity;
                globalQuantity += factor;
                game_quantity += factor;
            }
            parent.find("input.amount-input").val(count);
            document.getElementById("cartCount").textContent = quantity;
            $(document.getElementsByClassName("remove-button")).data("game_quantity", game_quantity);
        }
    }

    $('.minus').click(change_value(-1));
    $('.plus').click(change_value(1));

    $('.remove-button').click(function () {
        let id = $(this).data('id');
        let price = $(this).data("price");
        let quantity = $(this).data('quantity');
        let game_quantity = $(this).data('game_quantity');
        subtotal -= price

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
        console.log(quantity)
        console.log(game_quantity)
        quantity = quantity + globalQuantity - game_quantity;
        globalQuantity -= game_quantity;
        document.getElementById("cartCount").textContent = quantity;

        // document.getElementById('cartCount').textContent = document.querySelectorAll(".cart-box").length.toString();
        // document.getElementById("#items-price").textContent=subtotal.toString() + " PLN";
    });

});