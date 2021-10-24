package ru.mplobanov.updaters.moveUpdaters.common.rules;

import ru.mplobanov.game.board.ReadonlyBoard;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveChecker;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;

public class IsDiagonalChecker implements MoveChecker {
    @Override
    public void check(Move move, ReadonlyBoard board) throws MoveCheckerException {
        int rowDelta = Math.abs(move.getStartPosition().getRow() - move.getEndPosition().getRow());
        int colDelta = Math.abs(move.getStartPosition().getColumn() - move.getEndPosition().getColumn());

        if (rowDelta != colDelta) {
            throw new MoveCheckerException("Move is not diagonal");
        }
    }
}
