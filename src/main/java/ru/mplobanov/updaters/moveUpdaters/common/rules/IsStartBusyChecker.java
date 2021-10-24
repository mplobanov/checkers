package ru.mplobanov.updaters.moveUpdaters.common.rules;

import ru.mplobanov.game.board.ReadonlyBoard;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveChecker;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;

public class IsStartBusyChecker implements MoveChecker {
    @Override
    public void check(Move move, ReadonlyBoard board) throws MoveCheckerException {
        if (!board.hasFigure(move.getStartPosition())) {
            throw new MoveCheckerException("The start cell of the move is empty.");
        }
    }
}
