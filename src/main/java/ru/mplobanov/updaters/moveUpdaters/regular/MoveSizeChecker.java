package ru.mplobanov.updaters.moveUpdaters.regular;

import ru.mplobanov.game.board.ReadonlyBoard;
import ru.mplobanov.game.exceptions.EmptyCellException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveChecker;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;
import ru.mplobanov.updaters.moveUpdaters.common.rules.EnemyChecker;

public class MoveSizeChecker implements MoveChecker {

    private static final EnemyChecker ENEMY_CHECKER = new EnemyChecker();

    @Override
    public void check(Move move, ReadonlyBoard board) throws MoveCheckerException, EmptyCellException {
        if (move.getMoveSize() > 2) {
            throw new MoveCheckerException("Too big move for regular figure.");
        }
        if (ENEMY_CHECKER.getEnemyCount(move, board) == 0 && move.getMoveSize() == 2) {
            throw new MoveCheckerException("Too big move for regular figure (no capture).");
        }
    }
}
