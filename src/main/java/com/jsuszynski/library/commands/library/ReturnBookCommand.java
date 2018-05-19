package com.jsuszynski.library.commands.library;

import com.jsuszynski.library.books.Book;
import com.jsuszynski.library.commands.Command;

import java.util.Map;

public class ReturnBookCommand extends Command {

    private static final String WRONG_PARAMS = "Niepoprawne parametry.";
    public static final String RETURN_BOOK_PARAMS = "Podaj parametr w formacie: -T<tytuł> lub -I<ISBN>";

    @Override
    public void execute() {
        System.out.println(RETURN_BOOK_PARAMS);
        Map<String, String> args = argumentInterpreter.parseArguments();

        if (!argumentsValidator.returnBookParams(args)) {
            throw new RuntimeException(WRONG_PARAMS);
        }


        if (args.containsKey("-T")) {
            Book book = libraryService.findBookByTitle(args.get("-T"));
            swapBooks(book);

        } else {
            Book book = libraryService.findBookByIsbn(args.get("-I"));
            swapBooks(book);

        }

    }

    private void swapBooks(Book book) {
        Book returnedBook = book.returnedBook();
        databaseService.deleteBook(book);
        databaseService.addBook(returnedBook);
    }
}
