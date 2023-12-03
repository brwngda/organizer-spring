function openModal() {
    $('#myModalHomePage').modal('show');
    currentSlide();
}

function currentSlide(n) {
    $('.carousel1').carousel('pause');

    $('.carousel2').carousel(n);
}

$('#myModalHomePage').on('hidden.bs.modal', function () {
    $('.carousel1').carousel('cycle');
})