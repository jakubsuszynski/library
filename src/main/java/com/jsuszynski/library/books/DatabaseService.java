package com.jsuszynski.library.books;

public class DatabaseService {

    private final BookRepository bookRepository = new BookRepository();


    public void addBook(Book book) {
        bookRepository.addBook(book);
    }


    public void deleteBook(Book book) {
        bookRepository.removeBook(book);
    }

}
