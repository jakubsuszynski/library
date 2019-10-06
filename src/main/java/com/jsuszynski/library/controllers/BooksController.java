package com.jsuszynski.library.controllers;

import com.jsuszynski.library.domain.Book;
import com.jsuszynski.library.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BooksController {
    @Autowired
    private DatabaseService databaseService;

    @GetMapping("/all")
    public List<Book> returnAllBooks() {
        return databaseService.getAllBooks();
    }

    @PostMapping("/add")
    public void addBook(Book book) {
        databaseService.addBook(book);
    }

    @DeleteMapping("/delete")
    public void deleteBook(Book book) {
        databaseService.deleteBook(book);
    }



}
