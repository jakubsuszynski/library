package com.jsuszynski.library;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    private final BookRepository bookRepository = new BookRepository();


    public void addBook(Book book) {
        bookRepository.addBook(book);
    }

    public void removeBook(Book book) {
        bookRepository.removeBook(book);
    }

    public Book findByIsbn(String isbn) {

        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new RuntimeException("No book found"));
    }

    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title).orElseThrow(() -> new RuntimeException("No book found"));
    }

    public List<Book> findByAuthor(String author) {
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


}
