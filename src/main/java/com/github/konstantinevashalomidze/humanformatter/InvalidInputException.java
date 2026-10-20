package com.github.konstantinevashalomidze.humanformatter;

public class InvalidInputException extends IllegalArgumentException {
    public InvalidInputException(String message) {
        super(message);
    }
}
