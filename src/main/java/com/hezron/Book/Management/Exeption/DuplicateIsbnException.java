package com.hezron.Book.Management.Exeption;

public class DuplicateIsbnException extends RuntimeException {
    public DuplicateIsbnException(String message){
        super(message);
    }
}
