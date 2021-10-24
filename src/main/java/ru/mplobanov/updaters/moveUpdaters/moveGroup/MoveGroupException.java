package ru.mplobanov.updaters.moveUpdaters.moveGroup;

public class MoveGroupException extends Exception {
    public MoveGroupException() {
        super("Something's wrong with the MoveGroup");
    }

    public MoveGroupException(String message) {
        super(message);
    }
}
