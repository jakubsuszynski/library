package com.jsuszynski.library.commands.database;

import com.jsuszynski.library.commands.Command;

import java.util.Map;

public class DeleteBookCommand extends Command {

    private static final String WRONG_PARAMS = "Niepoprawne parametry.";
    private static final String SPACING = "Wyrazy parametrów oddzielaj myślnikiem.";
    public static final String DELETING_PARAMS = "Podaj parametr w formacie: -T<tytul> lub -I<ISBN>. ";


    @Override
    public void execute() {
        System.out.println(DELETING_PARAMS + SPACING);

        Map<String, String> args = argumentInterpreter.parseArguments();

        if (!argumentsValidator.deletingBooksParams(args)) {
            throw new RuntimeException(WRONG_PARAMS);
        }

        if (args.containsKey("-T")) {
            databaseService.deleteBook(libraryService.findBookByTitle(args.get("-T")));
        } else {
            databaseService.deleteBook(libraryService.findBookByIsbn(args.get("-I")));
        }

    }

}

