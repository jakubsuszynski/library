package com.jsuszynski.library.commands.library;

import com.jsuszynski.library.arguments.Params;
import com.jsuszynski.library.books.Book;
import com.jsuszynski.library.commands.Command;
import com.jsuszynski.library.console.BooksPrinter;

import java.util.List;
import java.util.Map;

public class FindBookCommand extends Command {
    private final BooksPrinter booksPrinter = new BooksPrinter();
    private static final String PROMPT = "Podaj parametr w formacie: -T<tytul>, -A<autor> lub -I<ISBN>. " +
            "\nWyrazy parametrów oddzielaj myślnikiem.";

    @Override
    public void execute() {

        System.out.println(PROMPT);


        Map<Params, String> args = argumentInterpreter.parseArguments();

        if (argumentsValidator.findingBooksParams(args)) {
            throw new RuntimeException(WRONG_PARAMS);
        }
        if (args.containsKey(Params.T)) {
            byTitlePrinter(args);
        } else if (args.containsKey(Params.I)) {
            byIsbnPrinter(args);
        } else {
            byAuthorPrinter(args);
        }
    }

    private void byIsbnPrinter(Map<Params, String> args) {
        List<Book> books = libraryService.findSimilarBooks(Book::getIsbn, args.get(Params.I));
        booksPrinter.printBooks(books);
    }


    private void byTitlePrinter(Map<Params, String> args) {
        List<Book> books = libraryService.findSimilarBooks(Book::getTitle, args.get(Params.T));

        booksPrinter.printBooks(books);
    }

    private void byAuthorPrinter(Map<Params, String> args) {
        List<Book> books = libraryService.findSimilarBooks(Book::getAuthor, args.get(Params.A));
        booksPrinter.printBooks(books);
    }
}
