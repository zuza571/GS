$().ready(function () {
    // after removing the last element showing new content
    let boxCount = document.querySelectorAll(".cart-box")
    if (boxCount.length > 0) {
        document.getElementById("empty-cart").style.display = "none"
        document.getElementById("right-bar").style.display = "block"

    } else {
        document.getElementById("empty-cart").style.display = "block"
        document.getElementById("right-bar").style.display = "none"
    }

});