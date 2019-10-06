package com.jsuszynski.library.services;

import com.jsuszynski.library.repositories.BooksRepository;
import com.jsuszynski.library.domain.Book;
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

    public void returnBook(Book book) {
        if (book.isLent()) {
            book.returnBook();
            booksRepository.save(book);
        }
    }

    public void lendBook(Book book, String reader) {
        if (!book.isLent()) {
            book.lendBook(reader);
            booksRepository.save(book);
        }
    }

    public void returnBookById(Long id) {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isPresent() && book.get().isLent()) {
            booksRepository.returnBook(id);
        }
    }

    public void lendBookById(Long id, String reader) {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isPresent() && !book.get().isLent()) {
            booksRepository.lendBook(reader, id);
        }
    }

    private void swapBooks(Book toDelete, Book toPut) {
        deleteBook(toDelete);
        addBook(toPut);
    }
}
