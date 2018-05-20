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
    private final Boolean lent;
    private final LocalDate lastLending;


    public Boolean isLent() {
        return lent;
    }

    public String getTitle() {
        return title;
    }

    public String getLowerCaseTitle() {
        return title.toLowerCase();
    }

    public String getLowerCaseAuthor() {
        return author.toLowerCase();
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

    public Book lentBook(String reader) {
        return new Book(this.title, this.author, this.isbn, reader, LocalDate.now(), true);
    }

    public Book returnedBook() {
        return new Book(this.title, this.author, this.isbn, this.lastReader, this.lastLending, false);
    }


    public Book(String title, String author, String isbn, String lastReader, LocalDate lastLending, Boolean lent) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.lastReader = lastReader;
        this.lastLending = lastLending;
        this.lent = lent;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(lastReader, book.lastReader) &&
                Objects.equals(lastLending, book.lastLending) &&
                Objects.equals(lent, book.lent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, author, isbn, lastReader, lastReader, isbn);
    }

    @Override
    public String toString() {

        String state = AVAILABLE;
        String lastLending = this.lastLending.toString();
        String lastReader = this.lastReader;

        if (this.lent)
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
