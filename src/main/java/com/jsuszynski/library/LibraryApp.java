package com.jsuszynski.library;

import com.jsuszynski.library.books.DatabaseService;
import com.jsuszynski.library.books.LibraryService;
import com.jsuszynski.library.console.UserInputReader;
import com.jsuszynski.library.console.WelcomePrinter;

public class LibraryApp {

    private final LibraryService libraryService = new LibraryService();
    private final WelcomePrinter welcomePrinter = new WelcomePrinter();
    private final UserInputReader userInputReader = new UserInputReader();
    private final DatabaseService databaseService = new DatabaseService();

    public void run() {
        do {
            try {
                welcomePrinter.printWelcomeMessage();

                String command = userInputReader.getUserInput();

                switch (command) {
                    case "1":
                        databaseService.addBook();
                        break;
                    case "2":
                        databaseService.deleteBook();
                        break;
                    case "3":
                        libraryService.findBook();
                        break;
                    case "4":
//                        libraryService.findBooksNotLentLately();
                        break;
                    case "5":
//                        libraryService.lendBook();
                        break;
                    case "6":
//                        libraryService.returnBook();
                        break;
                    case "7":
                        libraryService.returnReaders();
                        break;
                    case "Q":
                        System.out.println("Żegnaj");
                        System.exit(0);
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } while (true);

    }
}
