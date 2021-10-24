package ru.mplobanov.updaters.moveUpdaters.move;

import ru.mplobanov.game.board.ReadonlyBoard;
import ru.mplobanov.game.exceptions.EmptyCellException;

// Специальный интерфейс для сборки проверок. Используется чтобы проверять ходы определенных типов фигур.
public interface CompositeMoveChecker extends MoveChecker {
    void checkNoObligatoryCapture(Move move, ReadonlyBoard board)
            throws MoveCheckerException, EmptyCellException;
}
