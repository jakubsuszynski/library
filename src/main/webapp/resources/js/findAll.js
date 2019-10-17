let allData;

let chosenFilters = new Set();

function printBooks(data) {
    let resultsContainer = $("#resultsContainer");
    resultsContainer.empty();
    resultsContainer.append('\n' +
        '            <table class=" table table-striped table-responsive-sm table-borderless">\n' +
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
        '            </table>');
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
            Array.from(new Set(allData.map(i => i.category))).filter(i => i != null && i !== "Pozostałe").sort().forEach(i => navigation.append(navigation.append('<a><div class="btn shadow btn-light m-1">' + i + '</div></a>')));
            if (data.map(i => i.category).includes(null)) {
                navigation.append('<a><div class="btn shadow btn-light m-1">Pozostałe</div></a>')
            }
            printBooks(allData);
            spinnerElement.remove();
        })
        .fail((jqXHR, textStatus, errorThrown) => {
            let tableRows = $("#tableRows");
            tableRows.replaceWith("WRONG REQUEST - " + jqXHR.status);
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
