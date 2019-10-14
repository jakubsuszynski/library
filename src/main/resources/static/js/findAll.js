let allData;
let resultsContainer = $("#resultsContainer");
let chosenFilters = new Set();

function printBooks(data) {
    resultsContainer.empty();
    resultsContainer.append(
        '<div id="cardDeck" class="card-columns"></div>'
    );
    let cardDeck = $("#cardDeck");
    data.forEach(i => cardDeck.append('<div class="card shadow" >' +
        '<div class="card-body"><h5 class="card-title"><b>' + i.title + '</b></h5> ' +
        '<p class="card-text">' + (i.description ? i.description : '') + '</p><ul class="list-group list-group-flush">' +
        '    <li class="list-group-item"><b>Autor:</b> ' + i.author + '</li>\n' +
        '    <li class="list-group-item"><b>Gatunek:</b> ' + (i.category === null ? "Pozostałe" : i.category) + '</li>\n' +
        '    <li class="list-group-item"><b>ISBN:</b> ' + i.isbn + '</li>\n' +
        '    <li class="list-group-item"><b>Dostępność:</b> ' + (i.lent ? 'Nie' : 'Tak') + '</li>\n' + '  </ul> </div>'));
}

$(function () {
    let spinner = '<div id="loadingSpinner" style="position: absolute; top: 50%; left: 50%" class="spinner-border ml-auto" role="status" aria-hidden="true"></div>';
    $('#searchBox').append(spinner);
    let spinnerElement = $('#loadingSpinner');
    let navigation = $('#navigation');

    $.ajax({
        type: 'get',
        url: 'http://localhost:8080/all',
        dataType: 'json',
    })
        .done((data) => {
            allData = data;
            Array.from(new Set(allData.map(i => i.category))).filter(i => i != null && i !== "Pozostałe").sort().forEach(i => navigation.append(navigation.append('<a><div class="btn btn-light m-1">' + i + '</div></a>')));
            if (data.map(i => i.category).includes(null)) {
                navigation.append('<a><div class="btn btn-light m-1">Pozostałe</div></a>')
            }
            printBooks(allData);
            spinnerElement.remove();
        })
        .fail((jqXHR, textStatus, errorThrown) => {
            resultsContainer.replaceWith("WRONG REQUEST - " + jqXHR.status);
            spinnerElement.remove();
        });
});

$("#navigation").on('click', 'a', function (event) {
    $("#navigation > a > div").removeClass("btn-dark").addClass("btn-light");
    const cat = $(this).text().trim();
    if (chosenFilters.has(cat))
        chosenFilters.delete(cat);
    else {
        chosenFilters.add(cat);
    }
    if (cat === "Wszystkie" && chosenFilters.size > 0) {
        chosenFilters = new Set(["Wszystkie"])
    }
    if (chosenFilters.size === 0) {
        chosenFilters.add("Wszystkie")
    } else if (chosenFilters.size > 1) {
        chosenFilters.delete("Wszystkie")
    }

    if (chosenFilters.has("Wszystkie")) {
        printBooks(allData)
    } else {
        let books = new Set();
        chosenFilters.forEach(filter => {
            let parsedFilter = filter === "Pozostałe" ? null : filter;
            allData.filter(book => book.category === parsedFilter).forEach(book => books.add(book))
        });
        printBooks(books)
    }
    chosenFilters.forEach(i => $('#navigation > a > div:contains(' + i + ')').removeClass("btn-light").addClass("btn-dark"));
    event.preventDefault();
});
