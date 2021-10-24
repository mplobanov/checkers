package ru.mplobanov.game.exceptions;

import ru.mplobanov.figure.FigurePosition;

public class CellException extends Exception {
    private FigurePosition position;
    private static String defaultMessage = "Something wrong with the cell";

    public CellException(String message, FigurePosition position) {
        super(message);
        this.position = position;
    }

    public CellException(FigurePosition position) {
        super(defaultMessage);
        this.position = position;
    }
}
