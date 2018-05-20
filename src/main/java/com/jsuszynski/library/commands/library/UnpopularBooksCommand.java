package com.jsuszynski.library.commands.library;

import com.jsuszynski.library.books.Book;
import com.jsuszynski.library.commands.Command;
import com.jsuszynski.library.console.UserInputReader;

import java.util.List;

public class UnpopularBooksCommand extends Command {

    private static final String NOT_INTEGER = "Nie podano liczby całkowitej!";
    private final UserInputReader userInputReader = new UserInputReader();
    private final BooksPrinter booksPrinter = new BooksPrinter();

    @Override
    public void execute() {

        System.out.println("Podaj ilość tygodni: ");
        try {
            Integer weeks = Integer.valueOf(userInputReader.getUserInput());
            List<Book> books = libraryService.findUnpopularBooks(weeks);

            booksPrinter.printBooks(books);

        } catch (Exception e) {
            throw new RuntimeException(NOT_INTEGER);
        }
    }

}

