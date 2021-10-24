package ru.mplobanov.updaters.moveUpdaters.move;

import ru.mplobanov.game.board.ReadonlyBoard;
import ru.mplobanov.game.exceptions.EmptyCellException;

public interface MoveChecker {
    void check(Move move, ReadonlyBoard board) throws MoveCheckerException, EmptyCellException;
}
