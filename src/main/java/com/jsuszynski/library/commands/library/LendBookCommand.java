package com.jsuszynski.library.commands.library;

import com.jsuszynski.library.books.Book;
import com.jsuszynski.library.commands.Command;

import java.util.Map;

public class LendBookCommand extends Command {

    private static final String SPACING = "Wyrazy parametrów oddzielaj myślnikiem.";
    private static final String LENDING_PARAMS = "Podaj parametry w formacie: " +
            "-W<imię i nazwisko wypożyczającego> i -T<tytuł> lub -I<ISBN>. ";
    private static final String WRONG_PARAMS = "Niepoprawne parametry.";

    @Override
    public void execute() {


        System.out.println(LENDING_PARAMS + SPACING);

        Map<String, String> args = argumentInterpreter.parseArguments();

        if (!argumentsValidator.lendingBooksParams(args)) {
            throw new RuntimeException(WRONG_PARAMS);
        }
        if (args.containsKey("-T")) {
            Book book = libraryService.findBookByTitle(args.get("-T"));
            swapBooks(args.get("-W"), book);

        } else {
            Book book = libraryService.findBookByIsbn(args.get("-I"));
            swapBooks(args.get("-W"), book);
        }


    }

    private void swapBooks(String reader, Book book) {
        Book lentBook = book.lentBook(reader);
        databaseService.deleteBook(book);
        databaseService.addBook(lentBook);
    }
}
