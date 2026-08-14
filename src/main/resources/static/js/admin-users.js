function searchUsers() {

    const input =
        document.getElementById("userSearch");

    const filter =
        input.value.toLowerCase();

    const rows =
        document.querySelectorAll("#usersTable tbody tr");

    rows.forEach(function(row) {

        const text =
            row.textContent.toLowerCase();

        row.style.display =
            text.includes(filter) ? "" : "none";

    });

}


document.addEventListener("DOMContentLoaded", function () {

    const successPopup = document.querySelector(".success-popup");

    if (successPopup) {

        setTimeout(function () {

            successPopup.style.opacity = "0";
            successPopup.style.transform = "translateX(40px)";
            successPopup.style.transition = "opacity 0.4s ease, transform 0.4s ease";

            setTimeout(function () {
                successPopup.remove();
            }, 400);

        }, 4000); // 4 seconds
    }

});