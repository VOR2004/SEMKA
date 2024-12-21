document.addEventListener("DOMContentLoaded", function() {
    var dialog = document.getElementById("dialog");
    var openDialogBtn = document.getElementById("openDialogBtn");
    var closeDialogBtn = document.getElementById("closeDialogBtn");

    openDialogBtn.onclick = function() {
        dialog.style.display = "block";
    }
    closeDialogBtn.onclick = function() {
        dialog.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target === dialog) {
            dialog.style.display = "none";
        }
    }
});