let iconStars = document.querySelectorAll('.iconStar');
for (let iconStar of iconStars) {
    iconStar.addEventListener('click', function () {
        let title = iconStar.title
        let split = title.split(':');
        let id = split[1];

        $.get({
            url: "/star/" + id,
            success: function (data) {
                let starred = data.starred;
                changePairOfStarsClass(starred, id);
            }
        });
    });
}

function changePairOfStarsClass(starred, id) {
    let iconsStarsWithTheSameName = document.getElementsByName(id);

    for (let iconStar of iconsStarsWithTheSameName) {
        switch (starred) {
            case true: {
                iconStar.classList.remove('far', 'fa-star');
                iconStar.classList.add('fas', 'fa-star', 'star');
                break;
            }
            case false: {
                iconStar.classList.remove('fas', 'fa-star', 'star');
                iconStar.classList.add('far', 'fa-star');
                break;
            }
        }
    }
}