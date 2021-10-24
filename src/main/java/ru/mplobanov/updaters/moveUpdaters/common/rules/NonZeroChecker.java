package ru.mplobanov.updaters.moveUpdaters.common.rules;

import ru.mplobanov.game.board.ReadonlyBoard;
import ru.mplobanov.game.exceptions.EmptyCellException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveChecker;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;

public class NonZeroChecker implements MoveChecker {
    @Override
    public void check(Move move, ReadonlyBoard board) throws MoveCheckerException, EmptyCellException {
        if (move.getStartPosition().equals(move.getEndPosition())) {
            throw new MoveCheckerException("The starter point of the move can't be its end point.");
        }
    }
}
