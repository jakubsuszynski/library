package com.jsuszynski.library;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static BookService bookService = new BookService();

    public static void main(String[] args) {
        String choice = "";
        do {
            System.out.printf("Wybierz polecenie: " +
                    "\n 1. Dodanie książki" +
                    "\n 2. Usuniecie książki" +
                    "\n 3. Wyszukanie po nazwie, autorze lub ISBN" +
                    "\n 4. Wyszukanie książek niewypożyczonych od ostatnich x tygodni" +
                    "\n 5. Wypożyczenie książki" +
                    "\n 6. Lista aktualnych wypożyczających" +
                    "Q - wyjście");
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    System.out.println("Podaj tytul książki: ");
                    String title = scanner.nextLine();
                    System.out.println("Podaj autora książki: ");
                    String author = scanner.nextLine();
                    System.out.println("Podaj ISBN książki: ");
                    String isbn = scanner.nextLine();
                    bookService.addBook(new Book(title, author, isbn, "", LocalDate.MIN, false));
                    break;

                case "2":
                    System.out.println("1. Usun po ISBN " +
                            "\n2. Usuń po tytule");
                    choice = scanner.nextLine();

                    switch (choice) {

                        case "1":
                            System.out.println("Podaj ISBN");
                            bookService.removeBook(bookService.findByIsbn(scanner.nextLine()));
                            break;

                        case "2":
                            System.out.println("Podaj tytuł");
                            bookService.removeBook(bookService.findByTitle(scanner.nextLine()));
                            break;

                    }
                    break;

                case "3":
                    System.out.println("1. Znajdź po ISBN " +
                            "\n2. Znajdź po tytule" +
                            "\n3. Znajdź po autorze");
                    choice = scanner.nextLine();

                    switch (choice) {

                        case "1":
                            System.out.println("Podaj ISBN: ");
                            choice = scanner.nextLine();
                            System.out.println(bookService.findByIsbn(choice));
                            break;
                        case "2":
                            System.out.println("Podaj tytuł: ");
                            choice = scanner.nextLine();
                            System.out.println(bookService.findByTitle(choice));
                            break;
                        case "3":
                            System.out.println("Podaj autora: ");
                            choice = scanner.nextLine();
                            for (Book book : bookService.findByAuthor(choice)) {
                                System.out.println(book);
                            }
                            break;
                    }
                    break;

                case "4":

                    System.out.println("Podaj ilość tygodni: ");
                    int weeks = scanner.nextInt();
                    System.out.println("Książki niewypożyczone od " + weeks + "tygodni:");
                    for (Book book : bookService.notLent(weeks)) {
                        System.out.println(book + "\n");
                    }
                    break;

                case "5":
                    System.out.println("1. Znajdź po ISBN " +
                            "\n2. Znajdź po tytule" +
                            "\n3. Znajdź po autorze");
                    choice = scanner.nextLine();

                    Book book = null;
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
                    bookService.removeBook(book);
                    book.lendBook(choice);
                    bookService.addBook(book);
                    System.out.println(book);
                    break;
                case "6":
                    for (String reader : bookService.returnReaders())
                        System.out.println(reader);
                    break;


            }


        } while (!choice.equals("Q"));
    }
}
