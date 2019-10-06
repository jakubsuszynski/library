package com.jsuszynski.library.repositories;

import com.jsuszynski.library.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends CrudRepository<Book, Long> {
    public List<Book> findByTitleContainingIgnoreCase(String title);

    public List<Book> findByIsbnIgnoreCase(String isbn);

    public List<Book> findByTitleIgnoreCase(String title);

    public List<Book> findByAuthorIgnoreCase(String author);

    public List<Book> findByAuthorContainingIgnoreCase(String author);

    public void deleteById(Long id);

}
