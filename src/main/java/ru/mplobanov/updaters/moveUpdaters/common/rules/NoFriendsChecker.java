package ru.mplobanov.updaters.moveUpdaters.common.rules;

import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.LockedFigure;
import ru.mplobanov.game.board.ReadonlyBoard;
import ru.mplobanov.game.exceptions.EmptyCellException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveChecker;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;

public class NoFriendsChecker implements MoveChecker {
    @Override
    public void check(Move move, ReadonlyBoard board) throws MoveCheckerException, EmptyCellException {
        LockedFigure figure = board.getLockedFigure(move.getStartPosition());
        for (FigurePosition position : move) {
            if (position == move.getEndPosition() || position == move.getStartPosition()) {
                continue;
            }
            if (board.hasFigure(position)) {
                if (board.getLockedFigure(position).getColor() == figure.getColor()) {
                    throw new MoveCheckerException("There is a figure of your color on the way.");
                }
            }
        }
    }
}
