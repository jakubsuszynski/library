package com.jsuszynski.library.commands;

public class AddBookCommand extends Command {

    @Override
    public void execute() {
        databaseService.addBook();
    }
}
