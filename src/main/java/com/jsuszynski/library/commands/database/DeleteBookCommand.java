package com.jsuszynski.library.commands.database;

import com.jsuszynski.library.books.Book;
import com.jsuszynski.library.commands.Command;

import java.util.Map;

public class DeleteBookCommand extends Command {

    public static final String PROMPT = "Podaj parametr w formacie: -T<tytul> lub -I<ISBN>. " +
            "\nWyrazy parametrów oddzielaj myślnikiem.";
    public static final String DELETE_BY_TITLE = "Usunięto książkę pod tytułem %s";
    public static final String DELETE_BY_ISBN = "Usunięto książkę o ISBN %s";


    @Override
    public void execute() {
        System.out.println(PROMPT);

        Map<String, String> args = argumentInterpreter.parseArguments();

        if (!argumentsValidator.deletingBooksParams(args)) {
            throw new RuntimeException(WRONG_PARAMS);
        }

        if (args.containsKey("-T")) {
            databaseService.deleteBook(libraryService.findBy(Book::getTitle, args.get("-T")));
            System.out.println(String.format(DELETE_BY_TITLE, args.get("-T")));
        } else {
            databaseService.deleteBook(libraryService.findBy(Book::getIsbn, args.get("-I")));
            System.out.println(String.format(DELETE_BY_ISBN, args.get("-I")));
        }

    }

}

