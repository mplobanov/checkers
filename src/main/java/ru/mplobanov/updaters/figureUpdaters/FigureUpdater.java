package ru.mplobanov.updaters.figureUpdaters;

import ru.mplobanov.game.board.ImmutableBoard;
import ru.mplobanov.game.exceptions.EmptyCellException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;

public interface FigureUpdater {
    void update(Move move, ImmutableBoard board) throws EmptyCellException;
}
