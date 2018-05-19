package com.jsuszynski.library;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class BookService {

    private final BookRepository bookRepository = new BookRepository();
    private final Scanner scanner = new Scanner(System.in);
    private final IsbnValidator isbnValidator = new IsbnValidator();

    public void addBook() {
        System.out.println("Podaj parametry w formacie: -T<tytul> -A<autor> -I<ISBN> ");

        String input = scanner.nextLine();
        String[] params = input.split(" ");
        BookBuilder book = new BookBuilder();
        for (String param : params) {
            if (param.startsWith("-T")) {
                book.withTitle(param.substring(2));
            } else if (param.startsWith("-A")) {
                book.withAuthor(param.substring(2));
            } else if (param.startsWith("-I")) {
                if (isbnValidator.isValid(param.substring(2)))
                    book.withIsbn(param.substring(2));
            } else {
                throw new RuntimeException("Niepoprawne lub niewystarczające parametry");
            }
        }
        bookRepository.addBook(book.build());


    }

    public void deleteBook() {
        System.out.println("Podaj parametr w formacie: -T<tytul> lub -I<ISBN> ");
        String param = scanner.nextLine();

        if (param.startsWith("-T")) {
            bookRepository.removeBook(bookRepository.findByTitle(scanner.nextLine()));
        } else if (param.startsWith("-I")) {
            if (isbnValidator.isValid(param.substring(2)))
                bookRepository.removeBook(bookRepository.findByIsbn(param.substring(2)));
        } else {
            throw new RuntimeException("Niepoprawny parametr");
        }
    }

    public void findBook() {
        System.out.println("Podaj parametr w formacie: -T<tytul>, -I<ISBN> lub -A<autor>");
        String param = scanner.nextLine();


        if (param.startsWith("-T")) {
            System.out.println(bookRepository.findByTitle(param.substring(2)));
        } else if (param.startsWith("-I")) {
            System.out.println(bookRepository.findByIsbn(param.substring(2)));
        } else if (param.startsWith("-A")) {
            for (Book book : bookRepository.findByAuthor(param.substring(2))) {
                System.out.println(book);
            }
        } else {
            throw new RuntimeException("Niepoprawny parametr");
        }

    }

    public void findBooksNotLentLately() {
        System.out.println("Podaj ilość tygodni: ");
        int weeks = scanner.nextInt();

        for (Book book : notLent(weeks)) {
            System.out.println(book);
        }
    }

    public void lendBook() {
        System.out.println("Podaj parametry w formacie: -W<imie i nazwisko wypożyczającego> i -T<tytul> lub -I<ISBN>");
        String[] params = scanner.nextLine().split(" ");
        Book book = null;
        String reader = "";
        for (String param : params) {
            if (param.startsWith("-T")) {
                book = bookRepository.findByTitle(param.substring(2));
            } else if (param.startsWith("-I")) {
                book = bookRepository.findByIsbn(param.substring(2));
            } else if (param.startsWith("-W")) {
                reader = param.substring(2);
            } else
                throw new RuntimeException("Niepoprawny parametr");
        }
        book.lendBook(reader);
    }


    public void returnBook() {
        System.out.println("Podaj parametr w formacie: -T<tytul> lub -I<ISBN>");
        String param = scanner.nextLine();

        if (param.startsWith("-T")) {
            bookRepository.findByTitle(param.substring(2)).returnBook();
        } else if (param.startsWith("-I")) {
            bookRepository.findByIsbn(param.substring(2)).returnBook();
        } else {
            throw new RuntimeException("Niepoprawny parametr");
        }
    }

    public void returnReaders() {


        Map<String, Long> readers = bookRepository.getAllBooks().stream()
                .filter(Book::getState)
                .collect(groupingBy(Book::getLastReader, counting()));


        for (Map.Entry<String, Long> reader : readers.entrySet())
            System.out.println(reader.getKey() + ": " + reader.getValue() + " książek");
    }

    public List<Book> notLent(int weeks) {
        return bookRepository.getAllBooks().stream()
                .filter(s -> s.getLastLending().isBefore(LocalDate.now().minusWeeks(weeks)))
                .collect(Collectors.toList());
    }



}
