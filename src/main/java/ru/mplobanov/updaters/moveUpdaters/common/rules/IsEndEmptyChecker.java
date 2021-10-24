package ru.mplobanov.updaters.moveUpdaters.common.rules;

import ru.mplobanov.game.board.ReadonlyBoard;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveChecker;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;

public class IsEndEmptyChecker implements MoveChecker {
    @Override
    public void check(Move move, ReadonlyBoard board) throws MoveCheckerException {
        if (board.hasFigure(move.getEndPosition())) {
            throw new MoveCheckerException("The end cell of the move is taken.");
        }
    }
}
