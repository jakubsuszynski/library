package com.jsuszynski.library.commands.library;

import com.jsuszynski.library.books.Book;
import com.jsuszynski.library.commands.Command;
import com.jsuszynski.library.console.BooksPrinter;

import java.util.List;
import java.util.Map;

public class FindBookCommand extends Command {
    private final BooksPrinter booksPrinter = new BooksPrinter();
    private static final String PROMPT = "Podaj parametry w formacie: -T<tytul>, -A<autor> lub -I<ISBN>. " +
            "\nWyrazy parametrów oddzielaj myślnikiem.";

    @Override
    public void execute() {

        System.out.println(PROMPT);


        Map<String, String> args = argumentInterpreter.parseArguments();

        if (argumentsValidator.findingBooksParams(args)) {
            throw new RuntimeException(WRONG_PARAMS);
        }
        if (args.containsKey("-T")) {
            byTitlePrinter(args);
        } else if (args.containsKey("-I")) {
            byIsbnPrinter(args);
        } else {
            byAuthorPrinter(args);
        }
    }

    private void byIsbnPrinter(Map<String, String> args) {
        List<Book> books = libraryService.findSimilarBooks(Book::getIsbn, args.get("-I"));
        booksPrinter.printBooks(books);
    }


    private void byTitlePrinter(Map<String, String> args) {
        List<Book> books = libraryService.findSimilarBooks(Book::getTitle, args.get("-T"));

        booksPrinter.printBooks(books);
    }

    private void byAuthorPrinter(Map<String, String> args) {
        List<Book> books = libraryService.findSimilarBooks(Book::getAuthor, args.get("-A"));
        booksPrinter.printBooks(books);
    }
}
