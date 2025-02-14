package com.hezron.Book.Management.Repository;


import com.hezron.Book.Management.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, Long> {
}
