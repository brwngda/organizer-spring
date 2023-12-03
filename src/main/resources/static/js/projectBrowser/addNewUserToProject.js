
let myInput = document.getElementById('myInput');
let submitUser = document.getElementById('submitUser');

submitUser.addEventListener("click", (event) => {
    let url = document.createElement('a');
    url.setAttribute('href', window.location);
    let projectId = url.search.substr(4);
    let selectedUser = myInput.value;
    let userId;
    $.get({
        url: "/dashboard/allusers",
        success: function (users) {

            for (let i = 0; i < users.length; i++) {

                if (selectedUser === users[i].email) {
                    userId = users[i].idUser;
                    break;
                }
            }

            $.get({
                url: "/project" + '/' + projectId + '/' + userId,
                success: function (result) {
                    console.log(result);
                }
            });
        }
    });
    myInput.value = "";
});

var listWithUsers;
let usersCounter;
let isUsersCounterChanged;

myInput.addEventListener("input", () => {

    $.get({
        url: "/dashboard/allusers",
        success: function (users) {

            isUsersCounterChanged = CheckIsUsersCounterChanged(usersCounter, users.length);

            if (isUsersCounterChanged) {
                usersCounter = users.length;
                listWithUsers = findAllUsers(users);
            }
            autocomplete(document.getElementById("myInput"), listWithUsers);
        }
    });

});

function CheckIsUsersCounterChanged(usersCounter, dataLength) {
    if (usersCounter == null) {
        return true;
    } else if (usersCounter === dataLength) {
        return false;
    } else {
        return true;
    }
}


function findAllUsers(users) {
    listWithUsers = [];
    for (let i = 0; i < usersCounter; i++) {
        listWithUsers.push(users[i].email);
    }
    return listWithUsers;
}


