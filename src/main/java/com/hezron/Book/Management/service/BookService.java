package com.hezron.Book.Management.service;


import com.hezron.Book.Management.Exeption.BookNotFoundException;
import com.hezron.Book.Management.Repository.BookRepository;
import com.hezron.Book.Management.dto.BookDTO;
import com.hezron.Book.Management.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.zip.DataFormatException;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    //return all  pageable books
    public Page<Book> findAll(Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    //find a book by id
    public Book findById(String id){
        return bookRepository.findById(id)
                .orElseThrow( ()-> new BookNotFoundException("Book not found with id" + id));
    }

    public Book create(BookDTO bookDTO) throws DataFormatException {
        if (bookRepository.existsByIsbn(bookDTO.getIsbn())){
            throw new DataFormatException("Book with ISBN" + bookDTO.getIsbn() + "already exists");
        }

        Book book = new Book();
        updateBookFromDTO(book, bookDTO);
        return bookRepository.save(book);
    }


    //update book by id
    public Book update(String id, BookDTO bookDTO){
        Book book = findById(id);
        updateBookFromDTO(book, bookDTO);
        return bookRepository.save(book);
    }

    private void updateBookFromDTO(Book book, BookDTO dto) {
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setYear(dto.getYear());
    }

    //search by Title
    public Page<Book> searchByTitle(String title, Pageable pageable){
        return bookRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    //search by Author
    public Page<Book> searchByAuthor(String author, Pageable pageable){
        return bookRepository.findByAuthorContainingIgnoreCase(author, pageable);
    }

    //search by Genre
    public  Page<Book> searchByGenre(String genre, Pageable pageable){
        return bookRepository.findByGenre(genre, pageable);
    }

    public Page<Book> findByGenre(String genre, PageRequest of) {
        return null;
    }

    public void deleteById(String id) {
        if (!bookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
        bookRepository.deleteById(id);
    }
}
