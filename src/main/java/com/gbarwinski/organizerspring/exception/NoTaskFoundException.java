package com.gbarwinski.organizerspring.exception;

public class NoTaskFoundException extends RuntimeException {

    public NoTaskFoundException(Long id) {
        super(String.format("Task with following id (%d) has not been found" + id));
    }
}
