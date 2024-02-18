package com.shortenurl.shortenurl.exception;

public class DuplicateShortURLException extends RuntimeException {

    public DuplicateShortURLException(String message) {
        super(message);
    }
}
