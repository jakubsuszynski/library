package com.jsuszynski.library.commands.library;

import com.jsuszynski.library.arguments.Params;
import com.jsuszynski.library.books.Book;
import com.jsuszynski.library.commands.Command;
import com.jsuszynski.library.console.DashReplacer;

import java.util.Map;

public class ReturnBookCommand extends Command {


    private static final String PROMPT = "Podaj parametry w formacie: -T<tytul> lub -I<ISBN>. " +
            "\nWyrazy parametrów oddzielaj myślnikiem.";
    private static final String BOOK_RETURNED = "Zwrócono książkę pod tytulem %s, ISBN %s";

    @Override
    public void execute() {
        System.out.println(PROMPT);
        Map<Params, String> args = argumentInterpreter.parseArguments();

        if (!argumentsValidator.returnBookParams(args)) {
            throw new RuntimeException(WRONG_PARAMS);
        }


        if (args.containsKey(Params.T)) {
            Book book = libraryService.findBy(Book::getTitle, args.get(Params.T));
            swapBooks(book);

        } else {
            Book book = libraryService.findBy(Book::getIsbn, args.get(Params.I));
            swapBooks(book);
        }

    }

    private void swapBooks(Book book) {
        Book returnedBook = book.returnedBook();

        System.out.println(DashReplacer
                .deleteDash(String.format(BOOK_RETURNED, returnedBook.getTitle(), returnedBook.getIsbn())));

        databaseService.deleteBook(book);
        databaseService.addBook(returnedBook);
    }
}
