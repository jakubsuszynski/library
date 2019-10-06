package com.jsuszynski.library.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String lastReader;
    private Boolean lent;
    private LocalDate lastLending;

    public Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(Long id, String title, String author, String isbn, String lastReader, LocalDate lastLending, Boolean lent) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.lastReader = lastReader;
        this.lastLending = lastLending;
        this.lent = lent;
    }

    public Boolean isLent() {
        return lent;
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

    public Book lentBook(String reader) {
        return new Book(this.id, this.title, this.author, this.isbn, reader, LocalDate.now(), true);
    }

    public Book returnedBook() {
        return new Book(this.id, this.title, this.author, this.isbn, this.lastReader, this.lastLending, false);
    }
}
