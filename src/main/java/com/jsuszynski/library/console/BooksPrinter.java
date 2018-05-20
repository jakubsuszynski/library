package com.jsuszynski.library.console;

import com.jsuszynski.library.books.Book;

import java.util.List;

public class BooksPrinter {
    public void printBooks(List<Book> books){
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
