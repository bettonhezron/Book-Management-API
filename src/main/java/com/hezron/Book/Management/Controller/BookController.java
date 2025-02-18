package com.hezron.Book.Management.Controller;
import com.hezron.Book.Management.dto.BookDTO;
import com.hezron.Book.Management.model.Book;
import com.hezron.Book.Management.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.zip.DataFormatException;


@RestController
@RequestMapping("/api/books")
@Tag(name = "Book Management", description = "APIs for managing books")
public class BookController {
    private final BookService bookService;

    // Constructor to inject the BookService
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Retrieve all books with pagination
    @GetMapping
    @Operation(summary = "Get all books (paginated)", description = "Retrieves a list of all books in the system")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all books")
    public Page<Book> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return bookService.findAll(PageRequest.of(page, size));
    }

    // Search books by title or author with optional filters
    @GetMapping("/search")
    @Operation(summary = "Search books by title or author")
    public Page<Book> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        if (title != null) {
            return bookService.searchByTitle(title, PageRequest.of(page, size));
        } else if (author != null) {
            return bookService.searchByAuthor(author, PageRequest.of(page, size));
        }
        return bookService.findAll(PageRequest.of(page, size));
    }

    // Retrieve books by genre with pagination
    @GetMapping("/genre/{genre}")
    @Operation(summary = "Find a book by genre")
    public Page<Book> findByGenre(
            @PathVariable("genre") String genre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return bookService.findByGenre(genre, PageRequest.of(page, size));
    }

    // Retrieve a book by its ID
    @GetMapping("/{id}")
    @Operation(summary = "Get a book by ID", description = "Retrieves a specific book by its ID")
    @ApiResponse(responseCode = "200", description = "Book found")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<Book> getBookById(@PathVariable("id") String id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    // Create a new book entry
    @PostMapping
    @Operation(summary = "Create a new book", description = "Adds a new book to the system")
    @ApiResponse(responseCode = "200", description = "Book created successfully")
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookDTO bookDTO) throws DataFormatException {
        return ResponseEntity.ok(bookService.create(bookDTO));
    }

    // Update an existing book's details
    @PutMapping("/{id}")
    @Operation(summary = "Update a book", description = "Update an existing book's information")
    @ApiResponse(responseCode = "200", description = "Book updated successfully!")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<Book> updateBook(
            @PathVariable("id") String id,
            @Valid @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.update(id, bookDTO));
    }

    // Delete a book by its ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book by ID", description = "Removes a book from the system")
    @ApiResponse(responseCode = "204", description = "Book deleted successfully!")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") String id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build(); // Returns 204 No Content if deletion is successful
    }
}
