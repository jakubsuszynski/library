package com.jsuszynski.library.commands.database;

import com.jsuszynski.library.books.BookBuilder;
import com.jsuszynski.library.commands.Command;

import java.util.Map;

public class AddBookCommand extends Command {
    private static final String WRONG_PARAMS = "Niepoprawne parametry.";
    private static final String SPACING = "Wyrazy parametrów oddzielaj myślnikiem.";
    private static final String ADDING_PARAMS = "Podaj parametry w formacie: -T<tytul> -A<autor> -I<ISBN>. ";
    private static final String SUCCESS = "Dodano książkę do bazy danych.";


    @Override
    public void execute() {

        System.out.println(ADDING_PARAMS + SPACING);

        Map<String, String> args = argumentInterpreter.parseArguments();

        if (!argumentsValidator.addingBooksParams(args)) {
            throw new RuntimeException(WRONG_PARAMS);
        }

        databaseService.addBook(new BookBuilder()
                .withAuthor(args.get("-A"))
                .withIsbn(args.get("-I"))
                .withTitle(args.get("-T"))
                .build());

        System.out.println(SUCCESS);
    }
}
