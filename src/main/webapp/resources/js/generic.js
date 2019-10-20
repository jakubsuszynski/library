$('.carousel').carousel({
    interval: 3000
});

$(function () {
    if ($("#badLogin").text() !== "") {
        $("#login-modal").modal('show');
    }
});