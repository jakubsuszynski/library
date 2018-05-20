package com.jsuszynski.library.books;

import com.jsuszynski.library.database.JsonOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BookRepository {


    private List<Book> books = new ArrayList<>();
    private static BookRepository instance;
    private final JsonOperator jsonOperator = new JsonOperator();
    private BookRepository() {
    }

    public static BookRepository getRepository() {
        if (instance == null) {
            synchronized (BookRepository.class) {
                if (instance == null) {
                    instance = new BookRepository();
                }
            }
        }
        return instance;
    }

    public void addBook(Book book) {
        books.add(book);
        jsonOperator.addBook(book);
    }


    public <T> Optional<Book> findBy(Function<Book, T> selector, T value) {
        return books.stream()
                .filter(s -> selector.apply(s).equals(value))
                .findFirst();
    }

    public List<Book> findByAuthor(String author) {
        return books.stream()
                .filter(s -> s.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public void removeBook(Book book) {
        books.remove(book);
    }
}
