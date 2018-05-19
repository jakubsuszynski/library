package com.jsuszynski.library.commands.library;

import com.jsuszynski.library.books.Book;
import com.jsuszynski.library.commands.Command;
import com.jsuszynski.library.console.UserInputReader;

public class UnpopularBooksCommand extends Command {

    private static final String NOT_INTEGER = "Nie podano liczby całkowitej!";
    private final UserInputReader userInputReader = new UserInputReader();

    @Override
    public void execute() {

        System.out.println("Podaj ilość tygodni: ");
        try {
            Integer weeks = Integer.valueOf(userInputReader.getUserInput());
            booksPrinter(weeks);

        } catch (Exception e) {
            throw new RuntimeException(NOT_INTEGER);
        }
    }

    private void booksPrinter(Integer weeks) {
        for (Book book : libraryService.findUnpopularBooks(weeks)) {
            System.out.println(book);
        }
    }
}
