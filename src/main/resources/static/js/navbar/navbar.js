let y = document.getElementById("NotificationButton");
let x = document.getElementById("navbarMain");
let z = document.getElementById("logoutButton");

function myFunction() {
    let arrayWithNavBarElements = [x, y, z];
    for (const NavBarElement of arrayWithNavBarElements) {
        if (NavBarElement) {
            if (NavBarElement.classList.contains("unresponsive")) {
                NavBarElement.classList.add("responsive");
                NavBarElement.classList.remove("unresponsive");
                NavBarElement.classList.add("d-block");
            } else {
                NavBarElement.classList.add("unresponsive");
                NavBarElement.classList.remove("responsive");
                NavBarElement.classList.remove("d-block");
            }
        }
    }
}
$(document).mouseup(function(e){
    var container = $(".container-Notification");
    if(!container.is(e.target) && container.has(e.target).length === 0){
        container.hide();
    }
});

$("#buttonWithNotifations").click(function () {
    $(".container-Notification").toggle();

});



