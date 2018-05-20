package com.jsuszynski.library.commands.database;

import com.jsuszynski.library.arguments.Params;
import com.jsuszynski.library.books.Book;
import com.jsuszynski.library.commands.Command;
import com.jsuszynski.library.console.DashReplacer;

import java.util.Map;

public class DeleteBookCommand extends Command {

    private static final String PROMPT = "Podaj parametr w formacie: -T<tytul> lub -I<ISBN>. " +
            "\nWyrazy parametrów oddzielaj myślnikiem.";
    private static final String DELETE_BY_TITLE = "Usunięto książkę pod tytułem %s";
    private static final String DELETE_BY_ISBN = "Usunięto książkę o ISBN %s";


    @Override
    public void execute() {
        System.out.println(PROMPT);

        Map<Params, String> args = argumentInterpreter.parseArguments();

        if (!argumentsValidator.deletingBooksParams(args)) {
            throw new RuntimeException(WRONG_PARAMS);
        }

        if (args.containsKey(Params.T)) {
            databaseService.deleteBook(libraryService.findBy(Book::getTitle, args.get(Params.T)));

            System.out.println(DashReplacer
                    .deleteDash(String
                            .format(DELETE_BY_TITLE, args.get(Params.T))));
        } else {
            databaseService.deleteBook(libraryService.findBy(Book::getIsbn, args.get(Params.I)));

            System.out.println(DashReplacer
                    .deleteDash(String
                            .format(DELETE_BY_ISBN, args.get(Params.I))));
        }

    }

}

