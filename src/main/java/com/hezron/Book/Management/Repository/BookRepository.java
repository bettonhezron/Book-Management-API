package com.hezron.Book.Management.Repository;


import com.hezron.Book.Management.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, String> {

}
