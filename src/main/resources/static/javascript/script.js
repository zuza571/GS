
$().ready(function() {
    let subtotal = 0;
    let listIds = [];

    // jak miec dostep do rozmiaru listy????
    //$('#cartCount').textContent =

    $('.add-to-cart').click(function () {
        let id = $(this).data('id');
        let add_buttons = document.querySelectorAll(".btn-area")
        for (const btn of add_buttons) {
            btn.addEventListener("click", event => {
                let helper = 0;
                for (let i = 0; i < listIds.length; i++) {
                    if (listIds[i] === id) {
                        helper = 1;
                    }
                }
                if (helper === 0) {
                    listIds.push(id)
                    let url = `http://localhost:8080/add/cart/${id}/`
                    console.log(url)
                    fetch(url)
                        .then(response => console.log("Success"))
                        .catch(error => {
                            console.log(error);
                        });
                    let price = $(this).data('price');
                    subtotal += price;
                }
            })
        }
    });

    function change_value(factor){
        return function(){
            let id = $(this).data('id');

            let add_quantity_btns = document.querySelectorAll(".plus")
            let subtract_quantity_btns = document.querySelectorAll(".minus")

            for (const btn of add_quantity_btns) {
                btn.addEventListener("click", event => {
                    let url = `http://localhost:8080/add/quantity/${id}/`
                    console.log(url)
                    fetch(url)
                        .then(response => console.log("Success"))
                        .catch(error => {
                            console.log(error);
                        });
                })
            }

            for (const btn of subtract_quantity_btns) {
                btn.addEventListener("click", event => {
                    let url = `http://localhost:8080/subtract/quantity/${id}/`
                    console.log(url)
                    fetch(url)
                        .then(response => console.log("Success"))
                        .catch(error => {
                            console.log(error);
                        });
                })
            }

            let parent = $(this).parent();
            let input = parseInt(parent.find(".amount-input").val());
            let count;
            if (factor===-1 && input === 1) {
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
        let remove_button = document.querySelectorAll(".btn-area")
        for (const btn of remove_button){
            btn.addEventListener("click", event => {
                let removeHelper = 0;
                for (let i = 0; i < listIds.length; i++) {
                    if (listIds[i] === id) {
                        removeHelper = 1;
                    }
                }

                if (removeHelper === 0) {
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
                    let boxCount = document.querySelectorAll(".box")
                    if (boxCount.length === 0) {
                        document.getElementById("right-bar").style.display = "none"
                        document.getElementById("empty-cart").style.display = "block"

                    }
                    // document.getElementById("#items-price").textContent=subtotal.toString() + " PLN";
                }

            })
        }
    });
});