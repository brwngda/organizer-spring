package com.gbarwinski.organizerspring.exception;

public class NoUserFoundException extends RuntimeException {
    public NoUserFoundException(Long id) {
        super(String.format("User with following id (%d) has not been found", id));
    }
}
