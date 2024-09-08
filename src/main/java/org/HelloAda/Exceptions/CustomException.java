package org.HelloAda.Exceptions;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}