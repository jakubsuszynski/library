package com.jsuszynski.library;

import java.time.LocalDate;

public class Book {

    private final String title;
    private final String author;
    private final String isbn;
    private final String lastReader;
    private final LocalDate lastLending;


    public Book(String title, String author, String isbn, String lastReader, LocalDate lastLending) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.lastReader = lastReader;
        this.lastLending = lastLending;
    }
}
