function printFetchedBooks(data) {
    let resultsContainer = $("#resultsContainer");
    resultsContainer.empty();
    resultsContainer.addClass("p-md-5 p-sm-2");
    resultsContainer.append('\n' +
        '            <table class="table table-striped table-responsive-sm table-borderless">\n' +
        '                <thead class="thead-dark">\n' +
        '                <tr>\n' +
        '                    <th scope="col">Tytuł</th>\n' +
        '                    <th scope="col">Autor</th>\n' +
        '                    <th scope="col">Gatunek</th>\n' +
        '                    <th scope="col">ISBN</th>\n' +
        '                    <th scope="col">Dostępność</th>\n' +
        '                </tr>\n' +
        '                </thead>\n' +
        '                <tbody id="tableRows">\n' +
        '                </tbody>\n' +
        '            </table>')
    let tableRows = $("#tableRows");
    tableRows.empty();
    data.forEach(i => tableRows.append(
        '<tr><td>' + i.title + '</td>' +
        '<td>' + i.author + '</td>' +
        '<td>' + i.category + '</td>' +
        '<td>' + i.isbn + '</td>' +
        '<td>' + (i.lent ? 'Nie' : 'Tak') + '</td>' +
        '</tr>'));
}

$("#searchButton").click(() => {
    let query = $("#searchInput").val();
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
            printFetchedBooks(data);
            spinnerElement.remove();
        })
        .fail((jqXHR, textStatus, errorThrown) => {
            $("#tableRows").replaceWith("WRONG REQUEST - " + jqXHR.status);
            spinnerElement.remove();
        });
});