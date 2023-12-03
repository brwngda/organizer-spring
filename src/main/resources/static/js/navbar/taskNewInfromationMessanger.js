$('.edittaskbtn').on("click", sentText);

function sentText() {
    let taskId;

    let editButtonId = $('.edittaskbtn').attr('id');
    let strings = editButtonId.split('_');
    console.log(editButtonId)
    if (strings.length > 0) {
        taskId = strings[1];
        let idUser = strings[2];
        let path = "/sendtaskInformation/" + idUser+"?taskId="+taskId+"&"+"update=UPDATE";
        console.log(path)
        $.get(path, {}, JSON.stringify({
            "taskId": taskId,
            "userId": idUser,
        }));

    }
}



