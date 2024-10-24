package com.onlinebanking.userservice.exception;

public class MatchingPasswordException extends RuntimeException {
    public MatchingPasswordException(String message) {
        super(message);
    }
}
