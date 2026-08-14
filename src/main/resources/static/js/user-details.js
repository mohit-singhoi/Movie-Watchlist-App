// Delete User Completely 

function confirmDeleteUser() {

    const userNameElement =
        document.querySelector('[data-user-name]');

    const userName =
        userNameElement
            ? userNameElement.textContent.trim()
            : 'this user';

    return confirm(
        "⚠️ Are you sure you want to permanently delete this user?\n\n" +
        "User: " + userName +
        "\n\n" +
        "This will also delete:\n" +
        "• All movies\n" +
        "• All feedback\n" +
        "• All activities\n\n" +
        "This action cannot be undone."
    );
}


function getUserName() {
    const userElement = document.querySelector("[data-user-name]");

    if (userElement) {
        return userElement.textContent.trim();
    }

    return "this user";
}


// =====================================================
// DELETE ALL MOVIES
// =====================================================

function confirmDeleteMovies() {

    const userName = getUserName();

    return confirm(
        "⚠️ Are you sure you want to delete all movies?\n\n" +
        "User: " + userName +
        "\n\n" +
        "All movies in this user's watchlist will be permanently deleted.\n\n" +
        "This action cannot be undone."
    );
}


// =====================================================
// DELETE ALL FEEDBACK
// =====================================================

function confirmDeleteFeedback() {

    const userName = getUserName();

    return confirm(
        "⚠️ Are you sure you want to delete all feedback?\n\n" +
        "User: " + userName +
        "\n\n" +
        "All feedback submitted by this user will be permanently deleted.\n\n" +
        "This action cannot be undone."
    );
}


// =====================================================
// DELETE ALL ACTIVITIES
// =====================================================

function confirmDeleteActivities() {

    const userName = getUserName();

    return confirm(
        "⚠️ Are you sure you want to delete all activities?\n\n" +
        "User: " + userName +
        "\n\n" +
        "All activities recorded for this user will be permanently deleted.\n\n" +
        "This action cannot be undone."
    );
}


// Automatically hide success msg  after 3 seconds
document.addEventListener("DOMContentLoaded", function () {

    const successPopup = document.querySelector(".success-popup");

    if (successPopup) {

        setTimeout(function () {

            successPopup.style.opacity = "0";
            successPopup.style.transform = "translateX(30px)";
            successPopup.style.transition = "0.3s ease";

            setTimeout(function () {
                successPopup.remove();
            }, 300);

        }, 3000);
    }

});

// =====================================================
// DELETE SPECIFIC MOVIE
// =====================================================

function confirmDeleteMovie(movieTitle) {

    return confirm(
        "⚠️ Delete Movie?\n\n" +
        "Movie: " + movieTitle + "\n\n" +
        "This movie will be permanently deleted " +
        "from the user's watchlist.\n\n" +
        "This action cannot be undone."
    );
}


// =====================================================
// DELETE SPECIFIC FEEDBACK
// =====================================================

function confirmDeleteFeedbackItem(category) {

    return confirm(
        "⚠️ Delete Feedback?\n\n" +
        "Category: " + category + "\n\n" +
        "This feedback will be permanently deleted.\n\n" +
        "This action cannot be undone."
    );
}


// =====================================================
// DELETE SPECIFIC ACTIVITY
// =====================================================

function confirmDeleteActivity(action) {

    return confirm(
        "⚠️ Delete Activity?\n\n" +
        "Activity: " + action + "\n\n" +
        "This activity will be permanently deleted.\n\n" +
        "This action cannot be undone."
    );
}



