$("#searchButton").click(() => {
    let query = $("#searchInput").val();
    let resultsList = $("#results");
    let spinner = '<div id="loadingSpinner" style="position: absolute; top: 50%; left: 50%" class="spinner-border ml-auto" role="status" aria-hidden="true"></div>';
    $('#searchBox').append(spinner);
    let spinnerElement$ = $('#loadingSpinner');
    $.ajax({
        type: 'get',
        url: 'http://localhost:8080/findAny',
        data: {arg: query},
        dataType: 'json',
    })
        .done((data) => {
            resultsList.empty();
            data.forEach(i => resultsList.append('<li class="bookResult">Autor: ' + i.author + ', Tytuł: ' + i.title + ', ISBN: ' + i.isbn + '</li>'));
            spinnerElement$.remove();
        })
        .fail((jqXHR, textStatus, errorThrown) => {
            resultsList.replaceWith("WRONG REQUEST - " + jqXHR.status);
            spinnerElement$.remove();
        });
});