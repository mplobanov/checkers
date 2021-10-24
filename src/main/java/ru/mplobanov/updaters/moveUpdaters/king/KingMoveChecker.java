package ru.mplobanov.updaters.moveUpdaters.king;

import ru.mplobanov.game.board.ReadonlyBoard;
import ru.mplobanov.game.exceptions.EmptyCellException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;
import ru.mplobanov.updaters.moveUpdaters.common.CommonMoveChecker;

// Проверяет ходы дамок (пустой, потому что шашки стеснены в движении относительно дамок)
public class KingMoveChecker extends CommonMoveChecker {

    @Override
    public void check(Move move, ReadonlyBoard board) throws MoveCheckerException, EmptyCellException {
       super.check(move, board);
    }

    public void checkNoObligatoryCapture(Move move, ReadonlyBoard board)
            throws MoveCheckerException, EmptyCellException {
        super.checkNoObligatoryCapture(move, board);
    }
}
