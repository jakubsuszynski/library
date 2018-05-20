package com.jsuszynski.library.commands.database;

import com.jsuszynski.library.books.BookBuilder;
import com.jsuszynski.library.commands.Command;

import java.util.Map;

public class AddBookCommand extends Command {
    private static final String ADDING_PARAMS = "Podaj parametry w formacie: -T<tytul> -A<autor> -I<ISBN>. " +
            "\nWyrazy parametrów oddzielaj myślnikiem.";
    private static final String SUCCESS = "Dodano książkę do bazy danych.";
    private static final String NO_LAST_READER = "Brak ostatniego czytelnika";

    @Override
    public void execute() {

        System.out.println(ADDING_PARAMS);

        Map<String, String> args = argumentInterpreter.parseArguments();

        if (!argumentsValidator.addingBooksParams(args)) {
            throw new RuntimeException(WRONG_PARAMS);
        }

        databaseService.addBook(new BookBuilder()
                .withAuthor(args.get("-A"))
                .withIsbn(args.get("-I"))
                .withTitle(args.get("-T"))
                .withLastReader(NO_LAST_READER)
                .buildNewBook());

        System.out.println(SUCCESS);
    }
}
