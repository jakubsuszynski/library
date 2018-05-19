package com.jsuszynski.library;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class BookRepository {


    Set<Book> books = new HashSet<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public Optional<Book> findByIsbn(String isbn) {
        return books.stream().filter(s -> s.getIsbn().equals(isbn)).findFirst();
    }

    public Optional<Book> findByTitle(String title) {
        return books.stream().filter(s -> s.getTitle().equals(title)).findFirst();
    }

    public List<Book> findByAuthor(String author) {
        return books.stream().filter(s -> s.getAuthor().equals(author)).collect(Collectors.toList());
    }
    public List<Book> findByDate(LocalDate date) {
        return books.stream().filter(s -> s.getLastLending().equals(date)).collect(Collectors.toList());
    }
    public Set<Book> returnAllBooks(){
        return books;
    }

    public void removeBook(Book book) {

        books.remove(book);
    }
}
