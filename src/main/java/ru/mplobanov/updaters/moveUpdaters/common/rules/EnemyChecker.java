package ru.mplobanov.updaters.moveUpdaters.common.rules;

import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.LockedFigure;
import ru.mplobanov.game.board.ReadonlyBoard;
import ru.mplobanov.game.exceptions.EmptyCellException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveChecker;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;

public class EnemyChecker implements MoveChecker {

    public int getEnemyCount(Move move, ReadonlyBoard board) throws EmptyCellException {
        LockedFigure figure = board.getLockedFigure(move.getStartPosition());
        int enemyCount = 0;
        for (FigurePosition position : move) {
            if (board.hasFigure(position) && board.getLockedFigure(position).getColor() != figure.getColor()) {
                enemyCount++;
            }
        }
        return enemyCount;
    }

    @Override
    public void check(Move move, ReadonlyBoard board) throws MoveCheckerException, EmptyCellException {
        if (getEnemyCount(move, board) > 1) {
            throw new MoveCheckerException("You must capture 0 or 1 enemy figure at one move.");
        }
    }
}
