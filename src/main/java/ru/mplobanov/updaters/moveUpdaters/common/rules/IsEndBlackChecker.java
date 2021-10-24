package ru.mplobanov.updaters.moveUpdaters.common.rules;

import ru.mplobanov.figure.Color;
import ru.mplobanov.game.board.ReadonlyBoard;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveChecker;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;

public class IsEndBlackChecker implements MoveChecker {
    @Override
    public void check(Move move, ReadonlyBoard board) throws MoveCheckerException {
        if (move.getEndPosition().getColor() != Color.BLACK) {
            throw new MoveCheckerException("The end cell must be black.");
        }
    }
}


