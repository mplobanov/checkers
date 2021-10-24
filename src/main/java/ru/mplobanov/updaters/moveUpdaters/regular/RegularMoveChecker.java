package ru.mplobanov.updaters.moveUpdaters.regular;

import ru.mplobanov.game.board.ReadonlyBoard;
import ru.mplobanov.game.exceptions.EmptyCellException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;
import ru.mplobanov.updaters.moveUpdaters.common.CommonMoveChecker;

// Проверяет ходы обычных шашек - просто длину хода
public class RegularMoveChecker extends CommonMoveChecker {
    static final MoveSizeChecker MOVE_SIZE_CHECKER = new MoveSizeChecker();

    @Override
    public void check(Move move, ReadonlyBoard board) throws MoveCheckerException, EmptyCellException {
        super.check(move, board);

        innerCheck(move, board);
    }

    @Override
    public void checkNoObligatoryCapture(Move move, ReadonlyBoard board)
            throws MoveCheckerException, EmptyCellException {
        super.checkNoObligatoryCapture(move, board);

        innerCheck(move, board);
    }

    private void innerCheck(Move move, ReadonlyBoard board) throws MoveCheckerException, EmptyCellException {
        MOVE_SIZE_CHECKER.check(move, board);
    }
}
