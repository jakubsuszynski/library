package com.jsuszynski.library.books;

import java.time.LocalDate;
import java.util.Objects;

public class Book {

    private static final String AVAILABLE = "Dostępna";
    private static final String UNAVAILABLE = "Wypożyczona";
    private static final String NEVER_LENT = "Książka nigdy nie wypożyczona";
    private static final String NO_LAST_READER = "Brak ostatniego czytelnika";

    private final String title;
    private final String author;
    private final String isbn;
    private final String lastReader;
    private final Boolean isLent;
    private final LocalDate lastLending;


    public Boolean getIsLent() {
        return isLent;
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


    public Book(String title, String author, String isbn, String lastReader, LocalDate lastLending, Boolean isLent) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.lastReader = lastReader;
        this.lastLending = lastLending;
        this.isLent = isLent;
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

        String state = AVAILABLE;
        String lastLending = this.lastLending.toString();
        String lastReader = this.lastReader;

        if (this.isLent)
            state = UNAVAILABLE;

        if (this.lastLending.equals(LocalDate.EPOCH))
            lastLending = NEVER_LENT;

        if (lastReader == null)
            lastReader = NO_LAST_READER;


        return "Tytuł: " + title +
                "\nAutor: " + author +
                "\nISBN: " + isbn +
                "\nStatus: " + state +
                "\nOstatnie wypożyczenie: " + lastLending +
                "\nOstatni czytelnik: " + lastReader +
                "\n";
    }


}
