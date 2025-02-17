package com.hezron.Book.Management.Repository;


import com.hezron.Book.Management.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, String> {
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);
    Page<Book> findByGenre(String genre, Pageable pageable);
    List<Book> findByYear(Integer year);
    boolean existsByIsbn(String isbn);

}
