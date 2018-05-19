package com.jsuszynski.library;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static BookService bookService = new BookService();

    public static void main(String[] args) {
        String choice = "";
        Book book = null;
        do {
            System.out.printf("Wybierz polecenie: " +
                    "\n 1. Dodanie książki" +
                    "\n 2. Usuniecie książki" +
                    "\n 3. Wyszukanie po nazwie, autorze lub ISBN" +
                    "\n 4. Wyszukanie książek niewypożyczonych od ostatnich x tygodni" +
                    "\n 5. Wypożyczenie książki" +
                    "\n 6. Zwróć książkę" +
                    "\n 7. Lista aktualnych wypożyczających" +
                    "Q - wyjście");
            String command = scanner.nextLine();

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
                    System.out.println("1. Znajdź po ISBN " +
                            "\n2. Znajdź po tytule");
                    choice = scanner.nextLine();


                    switch (choice) {
                        case "1":
                            System.out.println("Podaj ISBN: ");
                            choice = scanner.nextLine();
                            book = bookService.findByIsbn(choice);
                            break;
                        case "2":
                            System.out.println("Podaj tytuł: ");
                            choice = scanner.nextLine();
                            book = bookService.findByTitle(choice);
                            break;
                    }

                    System.out.println("Podaj imię i nazwisko wypożyczającego: ");
                    choice = scanner.nextLine();
                    book.lendBook(choice);
                    break;
                case "6":
                    System.out.println("1. Znajdź po ISBN " +
                            "\n2. Znajdź po tytule");
                    choice = scanner.nextLine();

                    switch (choice) {
                        case "1":
                            System.out.println("Podaj ISBN: ");
                            choice = scanner.nextLine();
                            book = bookService.findByIsbn(choice);
                            break;
                        case "2":
                            System.out.println("Podaj tytuł: ");
                            choice = scanner.nextLine();
                            book = bookService.findByTitle(choice);
                            break;
                    }

                    book.returnBook();
                    break;
                case "7":
                    for (String reader : bookService.returnReaders())
                        System.out.println(reader);
                    break;


            }


        } while (true);
    }
}
