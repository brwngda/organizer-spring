package com.gbarwinski.organizerspring.exception;

public class NoSprintFoundException extends RuntimeException {

    public NoSprintFoundException(Long id) {
        super(String.format("Sprint with following id (%d) has not been found" + id));
    }
}
