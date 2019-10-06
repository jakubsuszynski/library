package com.jsuszynski.library.services;

import com.jsuszynski.library.domain.Book;
import com.jsuszynski.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class DatabaseService {

    @Autowired
    private final BookRepository bookRepository;


    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public List<Book> findUnpopularBooks(int weeks) {
        return ((List<Book>) bookRepository.findAll()).stream()
                .filter(s -> s.getLastLending().isBefore(LocalDate.now().minusWeeks(weeks)))
                .collect(Collectors.toList());
    }

    public Map<String, Long> returnCurrentReaders() {
        return ((List<Book>) bookRepository.findAll()).stream()
                .filter(Book::isLent)
                .collect(groupingBy(Book::getLastReader, counting()));
    }

    public List<Book> findByIsbn(String isbn) {
        return bookRepository.findByIsbnIgnoreCase(isbn);
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    public void returnBook(Book book) {
        if (book.isLent()) {
            Book returnedBook = book.returnedBook();
            swapBooks(book, returnedBook);
        }
    }

    public void lendBook(Book book, String reader) {
        if (!book.isLent()) {
            Book lentBook = book.lentBook(reader);
            swapBooks(book, lentBook);
        }
    }

    public void returnBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent() && book.get().isLent()) {
            bookRepository.returnBook(id);
        }
    }

    public void lendBookById(Long id, String reader) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent() && !book.get().isLent()) {
            bookRepository.lendBook(id, reader);
        }
    }

    private void swapBooks(Book toDelete, Book toPut) {
        deleteBook(toDelete);
        addBook(toPut);
    }
}
