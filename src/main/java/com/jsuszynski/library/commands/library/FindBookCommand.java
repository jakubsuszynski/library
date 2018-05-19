package com.jsuszynski.library.commands.library;

import com.jsuszynski.library.books.Book;
import com.jsuszynski.library.commands.Command;

import java.util.Map;

public class FindBookCommand extends Command {

    private static final String WRONG_PARAMS = "Niepoprawne parametry.";


    @Override
    public void execute() {

        System.out.println("Podaj parametr w formacie: -T<tytul>, -I<ISBN> lub -A<autor>");


        Map<String, String> args = argumentInterpreter.parseArguments();

        if (argumentsValidator.findingBooksParams(args)) {
            throw new RuntimeException(WRONG_PARAMS);
        }
        if (args.containsKey("-T")) {
            byTitlePrinter(args);
        } else if (args.containsKey("-I")) {
            byIsbnPrinter(args);
        } else {
            booksPrinter(args);
        }
    }

    private void byIsbnPrinter(Map<String, String> args) {
        System.out.println(libraryService.findBookByIsbn(args.get("-I")));
    }

    private void byTitlePrinter(Map<String, String> args) {
        System.out.println(libraryService.findBookByTitle(args.get("-T")));
    }

    private void booksPrinter(Map<String, String> args) {
        for (Book book : libraryService.findBooksByAuthor(args.get("-A"))) {
            System.out.println(book);
        }
    }
}
