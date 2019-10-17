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
    private String category;
    public Book() {
    }

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public Book(String title, String author, String isbn, String lastReader, Boolean lent, LocalDate lastLending, String category) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.lastReader = lastReader;
        this.lent = lent;
        this.lastLending = lastLending;
        this.category = category;
    }

    public Long getId() {
        return id;
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

    public String getCategory() {
        return category;
    }

    public Boolean getLent() {
        return lent;
    }

    public Book lendBook(String reader) {
        this.lent = true;
        this.lastReader = reader;
        return this;
    }

    public Book returnBook() {
        this.lent = false;
        return this;
    }
}
