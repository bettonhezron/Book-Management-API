package com.hezron.Book.Management.Controller;

import com.hezron.Book.Management.Repository.BookRepository;
import com.hezron.Book.Management.model.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("{id}")
    @Operation(summary = "Get a book by ID", description = "Retrieves a specific book by its ID" )
    @ApiResponse(responseCode = "200", description = "Book updated successfully" )
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<Book> getBookById(@PathVariable String id){
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    @Operation(summary = "Create a new book", description = "Adds a new book to the system")
    @ApiResponse(responseCode = "200", description = "Book created successfully")

    public Book createBook(@RequestBody Book book){
        return bookRepository.save(book);
    }
}
