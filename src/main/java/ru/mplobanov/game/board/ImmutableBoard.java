package ru.mplobanov.game.board;

import ru.mplobanov.figure.Figure;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.game.exceptions.EmptyCellException;

public class ImmutableBoard extends ReadonlyBoard {

    public Figure getFigure(FigurePosition position) throws EmptyCellException {
        if (super.getRawMap().containsKey(position)) {
            return super.getRawMap().get(position);
        }
        throw new EmptyCellException("There is no figure on this cell", position);
    }
}
