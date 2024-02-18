package com.shortenurl.shortenurl.exception;

public class ShortURLNotFoundException extends RuntimeException {

    public ShortURLNotFoundException(String message){
        super(message);
    }
}
