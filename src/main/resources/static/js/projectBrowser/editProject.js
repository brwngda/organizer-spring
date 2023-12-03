$('.dropdown-toggle').dropdown();

$('#myEditProject').modal({
    keyboard: false
});


function getIMG(avatar)
{
    $('#avatar').attr('value','icons/'+avatar);
    $('#img_edit_menu1').attr('src','img/icons/'+avatar);

}

jQuery(document).ready(function(e) {
    $(".clsmodal").on("click", function() {

            $('#myEditProject').modal('show');

    });
});