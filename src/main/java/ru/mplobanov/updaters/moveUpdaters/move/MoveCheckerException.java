package ru.mplobanov.updaters.moveUpdaters.move;

public class MoveCheckerException extends Exception {
    public MoveCheckerException() {
        super("Something is wrong with the move");
    }

    public MoveCheckerException(String message) {
        super(message);
    }
}
