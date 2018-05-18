package com.jsuszynski.library;

import java.time.LocalDate;
import java.util.Objects;

public class Book {

    private final String title;
    private final String author;
    private final String isbn;
    private final String lastReader;
    private final Boolean state;
    private final LocalDate lastLending;


    public Boolean getState() {
        return state;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getLastReader() {
        return lastReader;
    }

    public LocalDate getLastLending() {
        return lastLending;
    }


    public Book(String title, String author, String isbn, String lastReader, LocalDate lastLending, Boolean state) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.lastReader = lastReader;
        this.lastLending = lastLending;
        this.state = state;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, author, isbn);
    }

    @Override
    public String toString() {
        return "Tytuł: " + title +
                "\nAutor: " + author +
                "\nISBN: " + isbn +
                "\nOstatnie wypożyczenie: " + lastLending +
                "\nOstatni czytelnik: " + lastReader;
    }

    public Book lendBook(String lastReader) {
        return new Book(title, author, isbn, lastReader, LocalDate.now(), true);

    }
}
