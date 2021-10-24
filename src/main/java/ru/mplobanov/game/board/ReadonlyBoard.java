package ru.mplobanov.game.board;

import ru.mplobanov.figure.*;
import ru.mplobanov.game.exceptions.EmptyCellException;
import ru.mplobanov.updaters.moveUpdaters.king.KingMoveChecker;

import java.util.HashMap;

public class ReadonlyBoard {
    private final HashMap<FigurePosition, Figure> figures = new HashMap<>();

    protected HashMap<FigurePosition, Figure> getRawMap() {
        return figures;
    }

    void checkFigureExistence(FigurePosition position) throws EmptyCellException {
        if (!hasFigure(position)) {
            throw new EmptyCellException("There is no figure on this cell", position);
        }
    }

    public LockedFigure getLockedFigure(FigurePosition position) throws EmptyCellException {
        checkFigureExistence(position);
        return figures.get(position);
    }

    public boolean hasFigure(FigurePosition position) {
        return figures.containsKey(position);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 7; i >= 0; i--) {
            s += i + " |";
            for (int j = 0; j < 8; j++) {
                try {
                    if (figures.containsKey(new FigurePosition(i, j))) {
                        Figure figure = figures.get(new FigurePosition(i, j));
                        String f = figure.getColor().equals(Color.WHITE) ? "w" : "b";
                        if (figure.getMoveChecker() instanceof KingMoveChecker) {
                            f = f.toUpperCase();
                        }
                        s += f;
                    } else {
                        if ((i + j) % 2 == 0) {
                            s += '_';
                        } else {
                            s += ' ';
                        }
                    }
                } catch (FigurePositionException e) {
                    // do nothing
                }
            }
            s += "|\n";
        }
        s += "   01234567\n";
        return s;
    }

    public int getFigureAmount() {
        return figures.size();
    }
}
