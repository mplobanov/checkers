package ru.mplobanov.game.exceptions;

import ru.mplobanov.figure.FigurePosition;

public class EmptyCellException extends CellException {

    private static String defaultMessage = "You are interacting with an empty cell that is supposed to be busy";

    public EmptyCellException(String message, FigurePosition position) {
        super(message, position);
    }

    public EmptyCellException(FigurePosition position) {
        super(defaultMessage, position);
    }
}
