package ru.mplobanov.input;

public class BoardInputException extends Exception {
    public BoardInputException() {
        super("Something's wrong with the input.");
    }

    public BoardInputException(String message) {
        super(message);
    }
}
