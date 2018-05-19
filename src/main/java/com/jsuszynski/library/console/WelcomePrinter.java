package com.jsuszynski.library.console;

public class WelcomePrinter {

    public static final String WELCOME_MESSAGE = "Wpisz numer polecenia: " +
            "\n 1. Dodanie książki" +
            "\n 2. Usuniecie książki" +
            "\n 3. Wyszukanie po nazwie, autorze lub ISBN" +
            "\n 4. Wyszukanie książek niewypożyczonych od ostatnich x tygodni" +
            "\n 5. Wypożyczenie książki" +
            "\n 6. Zwróć książkę" +
            "\n 7. Lista aktualnych wypożyczających" +
            "\n Q - wyjście";

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }
}
