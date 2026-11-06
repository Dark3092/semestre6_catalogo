package com.tads20262.catalogo.service.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }
}
