

$().ready(function() {
    let subtotal = 0;
    let listIds = [];

    $('.add-to-cart').click(function () {
        let id = $(this).data('id');
        let add_buttons = document.querySelectorAll(".btn-area")
        for (const btn of add_buttons){
            btn.addEventListener("click", event => {
                if (listIds.length === 0) {
                    listIds.push(id)
                    let url = `http://localhost:8080/add/cart/${id}/`
                    console.log(url)
                    fetch(url)
                        .then(response => console.log("Success"))
                        .catch(error => {
                            console.log(error);
                        });
                } else {
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
                    }
                }
            })
        }
    });

    function change_value(factor){
        return function(){
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
        $(this).parent().parent().parent().remove();
    });

});