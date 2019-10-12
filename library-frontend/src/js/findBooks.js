$("#searchButton").click(() => {
    let query = $("#searchInput").val();
    let resultsContainer = $("#resultsContainer");
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
            console.log(data)
            resultsContainer.empty();
            resultsContainer.append(
                '<div id="cardDeck" class="card-columns"></div>'
            );
            let cardDeck = $("#cardDeck");
            data.forEach(i => cardDeck.append('<div class="card" style="width: 18rem;">' +
                '<div class="card-body"><h5 class="card-title">' + i.title + '</h5> ' +
                '<p class="card-text">' + (i.description ? i.description : '') + '</p><ul class="list-group list-group-flush">' +
                '    <li class="list-group-item">Autor: ' + i.author + '</li>\n' +
                '    <li class="list-group-item">Gatunek: ' + i.category + '</li>\n' +
                '    <li class="list-group-item">ISBN: ' + i.isbn + '</li>\n' +                '  </ul> </div>'));
            spinnerElement$.remove();
        })
        .fail((jqXHR, textStatus, errorThrown) => {
            resultsContainer.replaceWith("WRONG REQUEST - " + jqXHR.status);
            spinnerElement$.remove();
        });
});