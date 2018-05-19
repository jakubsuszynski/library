package com.jsuszynski.library;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static BookService bookService = new BookService();

    public static void main(String[] args) {
        String command = null;
        do {
            try {
                System.out.printf("Wybierz polecenie: " +
                        "\n 1. Dodanie książki" +
                        "\n 2. Usuniecie książki" +
                        "\n 3. Wyszukanie po nazwie, autorze lub ISBN" +
                        "\n 4. Wyszukanie książek niewypożyczonych od ostatnich x tygodni" +
                        "\n 5. Wypożyczenie książki" +
                        "\n 6. Zwróć książkę" +
                        "\n 7. Lista aktualnych wypożyczających" +
                        "\n Q - wyjście");
                command = scanner.nextLine();

                switch (command) {
                    case "1":
                        bookService.addBook();
                        break;

                    case "2":
                        bookService.deleteBook();
                        break;

                    case "3":
                        bookService.findBook();
                        break;

                    case "4":
                        bookService.findBooksNotLentLately();
                        break;

                    case "5":
                        bookService.lendBook();
                        break;
                    case "6":
                        bookService.returnBook();
                        break;
                    case "7":
                        bookService.returnReaders();
                        break;
                    case "Q":
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } while (true);
    }
}
