package com.jsuszynski.library.commands;

import com.jsuszynski.library.commands.database.AddBookCommand;
import com.jsuszynski.library.commands.database.DeleteBookCommand;
import com.jsuszynski.library.commands.exit.ExitCommand;
import com.jsuszynski.library.commands.library.*;

public class CommandProvider {

    public static final String WRONG_COMMAND = "Niepoprawna komenda";

    public Command recogniseCommand(String command) {
        switch (command) {
            case "1":
                return new AddBookCommand();
            case "2":
                return new DeleteBookCommand();
            case "3":
                return new FindBookCommand();
            case "4":
                return new UnpopularBooksCommand();
            case "5":
                return new LendBookCommand();
            case "6":
                return new ReturnBookCommand();
            case "7":
                return new CurrentReadersCommand();
            case "8":
                return new ExitCommand();
            default:
                throw new RuntimeException(WRONG_COMMAND);
        }
    }
}
