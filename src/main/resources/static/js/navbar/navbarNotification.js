let buttonWithNotifations = document.getElementById("buttonWithNotifations");
let idUser = $('#userId').attr('name');
let value;

window.onload = function () {
    setInterval(checkNumberOfInformation, 5000);
}

function checkNumberOfInformation() {
    let badgeInfo = document.getElementById("badgeInfo");
    $.get("/newInformationCounter/" + idUser,
        function onsuccess(NumberOfInformation) {
            badgeInfo.innerText = NumberOfInformation;
            value = NumberOfInformation;
        })
}

buttonWithNotifations.addEventListener("click", () => {
    let length;
    if (value > 5)
        length = 5;
    else
        length = value;

    let idUser = $('#userId').attr('name');

    for (let i = 0; i < length; i++) {

        $.get("/receivetaskInformation/" + idUser,
            function onsuccess(MessageString) {
                if (MessageString !== "Nie ma nowych wiadomości") {
                    let newMessagesList = document.getElementById('newMessages');
                    let dropdown_item = document.createElement('a');

                    dropdown_item.setAttribute('class', 'btn dropdown-item w-100 dropdown-item-Notifications font-weight-bold');
                    dropdown_item.innerHTML = MessageString;

                    deleteLastNodeFromNewMessages();

                    deleteLastNodeFromOldMessages();
                    deleteLastNodeFromOldMessages();


                    newMessagesList.insertBefore(dropdown_item, newMessagesList.childNodes[0]);
                }
            })
    }
})

function deleteLastNodeFromOldMessages() {
    let oldMessagesList = document.getElementById('oldMessages');

    if (oldMessagesList.childNodes.length > 0) {
        oldMessagesList.lastChild.remove()
    }
    ;
}

function deleteLastNodeFromNewMessages() {
    let newMessagesList = document.getElementById('newMessages');

    if (newMessagesList.childNodes.length >= 5) {
        newMessagesList.lastChild.remove();
    }
}