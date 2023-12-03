let empty_card_class = document.querySelectorAll('.empty_card');

function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);

    [].forEach.call(empty_card_class, function (el) {
        if(el.id) {
            console.log('slice: ' + el.id.slice(6) + ' target.id: ' + ev.target.id);
            if (!(ev.target.id).includes(el.id.slice(6))) {
                el.style.display = 'block';
            }
        }
    });
}

let idUserFromNavbar = $('#userId').attr('name');

function drop(ev) {
    ev.preventDefault();
    let data = ev.dataTransfer.getData("text");
    if (data ) {
        let newContainerName = ev.target.id.slice(6);

        let container = document.getElementById('container_' + newContainerName);

        container.append(document.getElementById(data));
        let el_id = data.replace(/(.*)_/, '');
        document.getElementById(data).setAttribute('id', newContainerName + '_' + el_id);
        $.get({
            url: "/task/" + el_id + "/" + newContainerName,
            success: function (data) {
                let path = "/sendtaskInformation/" + idUserFromNavbar + "?taskId=" + el_id + "&" + "update=UPDATE_PROGRESS";
                $.get(path, {}, JSON.stringify({
                    "taskId": el_id,
                    "userId": idUserFromNavbar,
                }));
            }
        });
        [].forEach.call(empty_card_class, function (el) {
            el.style.display = 'none'
        });
    }
}

function hideAll() {
    [].forEach.call(empty_card_class, function (el) {
        el.style.display = 'none'
    });
}

$('.dropdown-toggle').dropdown();
