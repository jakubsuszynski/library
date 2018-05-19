package com.jsuszynski.library.commands;

import com.jsuszynski.library.books.DatabaseService;
import com.jsuszynski.library.books.LibraryService;

public abstract class Command {
    final DatabaseService databaseService = new DatabaseService();
    final LibraryService libraryService = new LibraryService();

    public abstract void execute();
}
