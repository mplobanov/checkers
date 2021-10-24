package ru.mplobanov.updaters.moveUpdaters.common;

import org.junit.jupiter.api.Test;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.FigurePositionException;
import ru.mplobanov.game.board.Board;
import ru.mplobanov.updaters.moveUpdaters.common.rules.IsDiagonalChecker;
import ru.mplobanov.updaters.moveUpdaters.common.rules.IsEndBlackChecker;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;

import static org.junit.jupiter.api.Assertions.*;

class IsDiagonalCheckerTest {
    @Test
    void makeDiagonalOK() throws FigurePositionException, MoveCheckerException {
        Board b = new Board();
        Move move = new Move(new FigurePosition(0, 0), new FigurePosition(1, 1));

        IsEndBlackChecker isEndBlackChecker = new IsEndBlackChecker();
        IsDiagonalChecker isDiagonalChecker = new IsDiagonalChecker();

        assertDoesNotThrow(() -> isDiagonalChecker.check(move, b));
        assertDoesNotThrow(() -> isEndBlackChecker.check(move, b));
    }

    @Test
    void makeNotDiagonalFail() throws FigurePositionException, MoveCheckerException {
        Board b = new Board();
        Move move = new Move(new FigurePosition(0, 0), new FigurePosition(0, 1));

        IsEndBlackChecker isEndBlackChecker = new IsEndBlackChecker();
        IsDiagonalChecker isDiagonalChecker = new IsDiagonalChecker();

        assertThrows(MoveCheckerException.class, () -> isDiagonalChecker.check(move, b), "Move is not diagonal");
        assertThrows(MoveCheckerException.class, () -> isEndBlackChecker.check(move, b), "The end cell must be black.");
    }
}