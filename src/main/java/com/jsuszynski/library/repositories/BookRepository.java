package com.jsuszynski.library.repositories;

import com.jsuszynski.library.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    public List<Book> findByTitleContainingIgnoreCase(String title);

    public List<Book> findByIsbnIgnoreCase(String isbn);

    public List<Book> findByTitleIgnoreCase(String title);

    public List<Book> findByAuthorIgnoreCase(String author);

    public List<Book> findByAuthorContainingIgnoreCase(String author);

    public void deleteById(Long id);

    @Query("UPDATE books SET lent=true, lastReader = ?2 WHERE id = ?1")
    public void lendBook(Long id, String reader);

    @Query("UPDATE books SET lent=false WHERE id= ?1")
    public void returnBook(Long id);
}
