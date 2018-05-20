package com.jsuszynski.library.books;

import com.jsuszynski.library.file.JsonOperator;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BookRepository {


    private final JsonOperator jsonOperator = JsonOperator.getInstance();
    private final List<Book> books = jsonOperator.getAllBooks();

    public void addBook(Book book) {
        jsonOperator.addBook(book);
    }


    public Optional<Book> findBy(Function<Book, String> selector, String value) {
        return books.stream()
                .filter(s -> selector.apply(s).equalsIgnoreCase(value))
                .findFirst();
    }

    public List<Book> findSimilarBooks(Function<Book, String> selector, String value) {
        return books.stream()
                .filter(s -> selector.apply(s).toLowerCase().contains(value.toLowerCase()))
                .collect(Collectors.toList());
    }


    public List<Book> getAllBooks() {
        return books;
    }

    public void removeBook(Book book) {
        jsonOperator.deleteBook(book);
    }
}
