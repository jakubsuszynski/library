package com.jsuszynski.library;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BookService {

    private final BookRepository bookRepository = new BookRepository();
    private final Scanner scanner = new Scanner(System.in);
    private final IsbnValidator isbnValidator = new IsbnValidator();

    public void addBook() {

        System.out.println("Podaj tytul książki: ");
        String title = scanner.nextLine();
        System.out.println("Podaj autora książki: ");
        String author = scanner.nextLine();
        System.out.println("Podaj ISBN książki: ");
        String isbn = getIsbn();
        Book book = new BookBuilder()
                .withTitle(title)
                .withAuthor(author)
                .withIsbn(isbn)
                .build();
        bookRepository.addBook(book);

    }

    public void deleteBook() {
        System.out.println("1. Usun po ISBN " +
                "\n2. Usuń po tytule");
        String choice = scanner.nextLine();

        switch (choice) {

            case "1":
                String isbn = getIsbn();
                bookRepository.removeBook(findByIsbn(isbn));
                break;

            case "2":
                System.out.println("Podaj tytuł");
                bookRepository.removeBook(findByTitle(scanner.nextLine()));
                break;

        }
    }

    public void findBook() {
        System.out.println("1. Znajdź po ISBN " +
                "\n2. Znajdź po tytule" +
                "\n3. Znajdź po autorze");

        String choice = scanner.nextLine();

        switch (choice) {

            case "1":
                System.out.println("Podaj ISBN: ");
                String isbn = getIsbn();
                System.out.println(findByIsbn(isbn));
                break;
            case "2":
                System.out.println("Podaj tytuł: ");

                System.out.println(findByTitle(scanner.nextLine()));
                break;
            case "3":
                System.out.println("Podaj autora: ");
                for (Book ksiazka : findByAuthor(scanner.nextLine())) {
                    System.out.println(ksiazka);
                }
                break;
        }
    }

    public void findBooksNotLentLately(){
        System.out.println("Podaj ilość tygodni: ");
        int weeks = scanner.nextInt();
        System.out.println("Książki niewypożyczone od " + weeks + "tygodni:");
        for (Book ksiazka : notLent(weeks)) {
            System.out.println(ksiazka);
        }
    }

    private Book findByIsbn(String isbn) {

        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new RuntimeException("No book found"));
    }

    private Book findByTitle(String title) {
        return bookRepository.findByTitle(title).orElseThrow(() -> new RuntimeException("No book found"));
    }

    private List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> notLent(int weeks) {
        return bookRepository.returnAllBooks().stream()
                .filter(s -> s.getLastLending()
                        .isBefore(LocalDate.now()
                                .minusWeeks(weeks)))
                .collect(Collectors.toList());
    }

    public List<String> returnReaders() {
        return bookRepository.returnAllBooks().stream().filter(Book::getState).map(Book::getLastReader).collect(Collectors.toList());
    }

    private String getIsbn() {
        String isbn;
        while (true) {
            isbn = scanner.nextLine();
            if (isbnValidator.isValid(isbn))
                break;
            System.out.println("ISBN niepoprawny. Musi składać się z 13 lub 10 cyfr");
        }
        return isbn;
    }


}
