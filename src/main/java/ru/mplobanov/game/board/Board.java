package ru.mplobanov.game.board;

import ru.mplobanov.figure.Figure;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.game.exceptions.BusyCellException;
import ru.mplobanov.game.exceptions.EmptyCellException;

import java.util.HashMap;

public final class Board extends ImmutableBoard {

    public void setFigure(Figure figure, FigurePosition position) throws BusyCellException {
        if (super.getRawMap().containsKey(position)) {
            throw new BusyCellException("You are trying to set a figure on bust cell", position);
        }
        super.getRawMap().put(position, figure);
    }

    public void removeFigure(FigurePosition position) throws EmptyCellException {
        checkFigureExistence(position);
        super.getRawMap().remove(position);
    }

    @Override
    public HashMap<FigurePosition, Figure> getRawMap() {
        return super.getRawMap();
    }
}
