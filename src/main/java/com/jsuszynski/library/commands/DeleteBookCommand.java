package com.jsuszynski.library.commands;

public class DeleteBookCommand extends Command {

    @Override
    public void execute() {
        databaseService.deleteBook();
    }
}
