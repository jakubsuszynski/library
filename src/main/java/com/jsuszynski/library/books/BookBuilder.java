package com.jsuszynski.library.books;

import java.time.LocalDate;

public class BookBuilder {

    private String title;
    private String author;
    private String isbn;
    private String lastReader;
    private Boolean lent;
    private LocalDate lastLending;

    public BookBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder withAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookBuilder withIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public BookBuilder withLastReader(String lastReader) {
        this.lastReader = lastReader;
        return this;
    }

    public BookBuilder withLastLending(LocalDate lastLending) {
        this.lastLending = lastLending;
        return this;
    }

    public BookBuilder withLent(Boolean state) {
        this.lent = state;
        return this;
    }

    public Book buildNewBook() {
        return new Book(title, author, isbn, lastReader, LocalDate.EPOCH, false);
    }

}
