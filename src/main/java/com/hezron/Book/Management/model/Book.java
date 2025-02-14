package com.hezron.Book.Management.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@Schema(description = "Book entity representing a book in the system")
@Data
public class Book {
    @Id
    @Schema(description = "Unique identifier of the book")
    private Long id;

    private String title;

    private String author;

    private String isbn;

    private Integer year;
}
