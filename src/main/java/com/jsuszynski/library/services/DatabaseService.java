package com.jsuszynski.library.services;

import com.jsuszynski.library.domain.Book;
import com.jsuszynski.library.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class DatabaseService {

    @Autowired
    BooksRepository booksRepository;

    public void addBook(Book book) {
        booksRepository.save(book);
    }

    public void deleteBook(Book book) {
        booksRepository.delete(book);
    }

    public void deleteBookById(Long id) {
        booksRepository.deleteById(id);
    }

    public List<Book> getAllBooks() {
        return (List<Book>) booksRepository.findAll();
    }

    public List<Book> findUnpopularBooks(int weeks) {
        return ((List<Book>) booksRepository.findAll()).stream()
                .filter(s -> s.getLastLending().isBefore(LocalDate.now().minusWeeks(weeks)))
                .collect(Collectors.toList());
    }

    public Map<String, Long> returnCurrentReaders() {
        return ((List<Book>) booksRepository.findAll()).stream()
                .filter(Book::isLent)
                .collect(groupingBy(Book::getLastReader, counting()));
    }

    public List<Book> findByIsbn(String isbn) {
        return booksRepository.findByIsbnIgnoreCase(isbn);
    }

    public List<Book> findByTitle(String title) {
        return booksRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> findByAuthor(String author) {
        return booksRepository.findByAuthorContainingIgnoreCase(author);
    }

    public void returnBookById(Long id) {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isPresent() && book.get().isLent()) {
            book.get().returnBook();
            booksRepository.save(book.get());
        }
    }

    public void lendBook(Book book, String reader) {
        if (!book.isLent()) {
            book.lendBook(reader);
            booksRepository.save(book);
        }
    }

    public void lendBookById(Long id, String reader) {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isPresent() && !book.get().isLent()) {
            book.get().lendBook(reader);
            booksRepository.save(book.get());
        }
    }

    public Set<Book> findBookByAnything(String arg) {
        Set<Book> books = new HashSet<>(booksRepository.findByAuthorContainingIgnoreCase(arg));
        books.addAll(booksRepository.findByIsbnIgnoreCase(arg));
        books.addAll(booksRepository.findByTitleContainingIgnoreCase(arg));
        return books;
    }

    private void swapBooks(Book toDelete, Book toPut) {
        deleteBook(toDelete);
        addBook(toPut);
    }
}
