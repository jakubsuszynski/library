package com.jsuszynski.library.commands;

import com.jsuszynski.library.arguments.ArgumentInterpreter;
import com.jsuszynski.library.arguments.ArgumentsValidator;
import com.jsuszynski.library.books.DatabaseService;
import com.jsuszynski.library.books.LibraryService;
import com.jsuszynski.library.console.DashReplacer;

public abstract class Command {

    protected final DatabaseService databaseService = new DatabaseService();
    protected final LibraryService libraryService = new LibraryService();
    protected final ArgumentInterpreter argumentInterpreter = new ArgumentInterpreter();
    protected final ArgumentsValidator argumentsValidator = new ArgumentsValidator();
    protected final DashReplacer dashReplacer = new DashReplacer();
    protected static final String WRONG_PARAMS = "Niepoprawne parametry.";

    public abstract void execute();
}
