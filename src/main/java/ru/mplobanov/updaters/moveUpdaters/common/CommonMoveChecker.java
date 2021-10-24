package ru.mplobanov.updaters.moveUpdaters.common;

import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.game.board.ReadonlyBoard;
import ru.mplobanov.game.exceptions.EmptyCellException;
import ru.mplobanov.updaters.moveUpdaters.common.rules.*;
import ru.mplobanov.updaters.moveUpdaters.move.CompositeMoveChecker;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;

import java.util.List;

import static ru.mplobanov.utils.MoveUtils.getPossibleMoves;


// Проверяет общие для всех (дамок и обычных шашек) правила
public class CommonMoveChecker implements CompositeMoveChecker {
    static final IsStartBusyChecker IS_START_BUSY_CHECKER = new IsStartBusyChecker();
    static final IsEndEmptyChecker IS_END_EMPTY_CHECKER = new IsEndEmptyChecker();
    static final NonZeroChecker NON_ZERO_CHECKER = new NonZeroChecker();
    static final IsEndBlackChecker IS_END_BLACK_CHECKER = new IsEndBlackChecker();
    static final IsDiagonalChecker IS_DIAGONAL_CHECKER = new IsDiagonalChecker();
    static final NoFriendsChecker NO_FRIENDS_CHECKER = new NoFriendsChecker();
    static final EnemyChecker ENEMY_CHECKER = new EnemyChecker();

    @Override
    public void check(Move move, ReadonlyBoard board) throws MoveCheckerException, EmptyCellException {
        checkNoObligatoryCapture(move, board);

        if (ENEMY_CHECKER.getEnemyCount(move, board) == 0) {
            alertCaptureMoves(move.getStartPosition(), board);
        }
    }

    @Override
    public void checkNoObligatoryCapture(Move move, ReadonlyBoard board)
            throws MoveCheckerException, EmptyCellException {
        IS_START_BUSY_CHECKER.check(move, board);
        IS_END_EMPTY_CHECKER.check(move, board);
        IS_END_BLACK_CHECKER.check(move, board);
        NON_ZERO_CHECKER.check(move, board);
        IS_DIAGONAL_CHECKER.check(move, board);
        NO_FRIENDS_CHECKER.check(move, board);
        ENEMY_CHECKER.check(move, board);
    }

    public static boolean checkCaptureMoves(FigurePosition position, ReadonlyBoard board) throws EmptyCellException {
        // checking if there is possible capture
        CompositeMoveChecker figureMoveChecker = board.getLockedFigure(position).getMoveChecker();
        List<Move> possibleMoves = getPossibleMoves(position);

        for (Move possibleMove : possibleMoves) {
            try {
                figureMoveChecker.checkNoObligatoryCapture(possibleMove, board);
                if (ENEMY_CHECKER.getEnemyCount(possibleMove, board) == 0) {
                    continue;
                }
            } catch (MoveCheckerException e) {
                continue;
            }
            return true;
        }
        return false;
    }

    public static void alertCaptureMoves(FigurePosition position, ReadonlyBoard board)
            throws EmptyCellException, MoveCheckerException {
        if (checkCaptureMoves(position, board)) {
            throw new MoveCheckerException("You must capture a figure if you can.");
        }
    }
}
