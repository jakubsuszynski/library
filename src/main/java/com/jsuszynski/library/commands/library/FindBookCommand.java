package com.jsuszynski.library.commands.library;

import com.jsuszynski.library.books.Book;
import com.jsuszynski.library.commands.Command;

import java.util.Map;

public class FindBookCommand extends Command {

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
        System.out.println(libraryService.findBookByIsbn(args.get("-I")));
    }

    private void byTitlePrinter(Map<String, String> args) {
        System.out.println(libraryService.findBookByTitle(args.get("-T")));
    }

    private void byAuthorPrinter(Map<String, String> args) {
        for (Book book : libraryService.findBooksByAuthor(args.get("-A"))) {
            System.out.println(book);
        }
    }
}
