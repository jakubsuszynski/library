$("#searchButton").click(() => {
    let query = $("#searchInput").val();
    let resultsContainer = $("#resultsContainer");
    let spinner = '<div id="loadingSpinner" style="position: absolute; top: 50%; left: 50%" class="spinner-border ml-auto" role="status" aria-hidden="true"></div>';
    $('#searchBox').append(spinner);
    let spinnerElement = $('#loadingSpinner');
    $.ajax({
        type: 'get',
        url: 'http://localhost:8080/findAny',
        data: {arg: query},
        dataType: 'json',
    })
        .done((data) => {
            resultsContainer.empty();
            resultsContainer.append(
                '<div id="cardDeck" class="card-columns"></div>'
            );
            let cardDeck = $("#cardDeck");
            data.forEach(i => cardDeck.append('<div class="card" >' +
                '<div class="card-body"><h5 class="card-title"><b>' + i.title + '</b></h5> ' +
                '<p class="card-text">' + (i.description ? i.description : '') + '</p><ul class="list-group list-group-flush">' +
                '    <li class="list-group-item"><b>Autor:</b> ' + i.author + '</li>\n' +
                '    <li class="list-group-item"><b>Gatunek:</b> ' + i.category + '</li>\n' +
                '    <li class="list-group-item"><b>ISBN:</b> ' + i.isbn + '</li>\n' +
                '    <li class="list-group-item"><b>Dostępność:</b> ' + (i.lent ? 'Tak' : 'Nie') + '</li>\n' + '  </ul> </div>'));
            spinnerElement.remove();
        })
        .fail((jqXHR, textStatus, errorThrown) => {
            resultsContainer.replaceWith("WRONG REQUEST - " + jqXHR.status);
            spinnerElement.remove();
        });
});

$( "body" ).click(function( event ) {
    console.log( "clicked: " + event.target.nodeName );
});
