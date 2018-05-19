package com.jsuszynski.library.books;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class LibraryService {

    private static final String WRONG_PARAMS = "Niepoprawne parametry";

    private final BookRepository bookRepository = BookRepository.getInstance();


    public List<Book> findBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public Book findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> findUnpopularBooks(int weeks) {
        return bookRepository.getAllBooks().stream()
                .filter(s -> s.getLastLending().isBefore(LocalDate.now().minusWeeks(weeks)))
                .collect(Collectors.toList());
    }



    public Map<String, Long> returnCurrentReaders() {

       return bookRepository.getAllBooks().stream()
                .filter(Book::getIsLent)
                .collect(groupingBy(Book::getLastReader, counting()));

    }


}
