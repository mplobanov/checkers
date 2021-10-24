package ru.mplobanov.game.exceptions;

import ru.mplobanov.figure.FigurePosition;

public class BusyCellException extends CellException {

    private static String defaultMessage = "You are interacting with a busy cell that is supposed to be empty";

    public BusyCellException(String message, FigurePosition position) {
        super(message, position);
    }

    public BusyCellException(FigurePosition position) {
        super(defaultMessage, position);
    }
}
