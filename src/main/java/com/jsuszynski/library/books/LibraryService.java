package com.jsuszynski.library.books;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class LibraryService {

    private static final String WRONG_PARAMS = "Niepoprawne parametry";
    private static final String NO_AUTHORS_BOOKS = "Nie znaleziono książek dla podanego autora.";
    private static final String ALL_BOOKS_AVAILABLE = "Żadna książka nie jest obecnie wypożyczona.";
    private static final String NO_BOOKS_FOUND = "Nie znaleziono książek niewypożyczonych od %s tygodni";

    private final BookRepository bookRepository = BookRepository.getInstance();


    public List<Book> findBooksByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);
        if (books.isEmpty())
            throw new RuntimeException(NO_AUTHORS_BOOKS);
        return books;
    }

    public Book findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> findUnpopularBooks(int weeks) {
        List<Book> unpopularBooks = bookRepository.getAllBooks().stream()
                .filter(s -> s.getLastLending().isBefore(LocalDate.now().minusWeeks(weeks)))
                .collect(Collectors.toList());

        if (unpopularBooks.isEmpty()) {
            throw new RuntimeException(String.format(NO_BOOKS_FOUND, weeks));
        }
        return unpopularBooks;
    }


    public Map<String, Long> returnCurrentReaders() {

        Map<String, Long> readers = bookRepository.getAllBooks().stream()
                .filter(Book::getIsLent)
                .collect(groupingBy(Book::getLastReader, counting()));

        if (readers.isEmpty()) {
            throw new RuntimeException(ALL_BOOKS_AVAILABLE);
        }
        return readers;
    }


}
