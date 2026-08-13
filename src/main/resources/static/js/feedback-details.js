/* For feedback Response popup */


document.addEventListener("DOMContentLoaded", function () {

    const popup = document.querySelector(".response-popup");

    if (popup) {

        // Show popup
        setTimeout(function () {
            popup.classList.add("show");
        }, 100);

        // Hide popup after 4 seconds
        setTimeout(function () {
            popup.classList.remove("show");
        }, 4000);
    }

});
