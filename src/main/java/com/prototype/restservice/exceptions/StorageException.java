package com.prototype.restservice.exceptions;

public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String meesage, Throwable cause) {
        super(meesage, cause);
    }
}
