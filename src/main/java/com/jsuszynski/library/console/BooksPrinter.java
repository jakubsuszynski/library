package com.jsuszynski.library.console;

import com.jsuszynski.library.books.Book;

import java.util.List;

public class BooksPrinter {
    public void printBooks(List<Book> books){
        books.forEach(System.out::println);
    }
}
