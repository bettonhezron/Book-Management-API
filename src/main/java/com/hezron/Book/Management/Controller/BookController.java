package com.hezron.Book.Management.Controller;

import com.hezron.Book.Management.Repository.BookRepository;
import com.hezron.Book.Management.model.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book Management", description = "APIs for managing books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieves a list of all books in the system")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all books" )
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
}
