package com.jsuszynski.library.commands;

public class FindBookCommand extends Command{


    @Override
    public void execute() {
        libraryService.findBook();
    }
}
