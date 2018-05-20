package com.jsuszynski.library.books;

import com.jsuszynski.library.file.JsonOperator;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BookRepository {


    private final JsonOperator jsonOperator = new JsonOperator();
    private final List<Book> books = jsonOperator.getAllBooks();

    public void addBook(Book book) {
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
        jsonOperator.deleteBook(book);
    }
}