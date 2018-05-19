package com.jsuszynski.library.commands.library;

import com.jsuszynski.library.arguments.ArgumentInterpreter;
import com.jsuszynski.library.arguments.ArgumentsValidator;
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
            System.out.println(libraryService.findBookByTitle(args.get("-T")));
        } else if (args.containsKey("-I")) {
            System.out.println(libraryService.findBookByIsbn(args.get("-I")));
        } else if (args.containsKey("-A")) {
            for (Book book : libraryService.findBooksByAuthor(args.get("-A"))) {
                System.out.println(book);
            }
        }
    }
}
