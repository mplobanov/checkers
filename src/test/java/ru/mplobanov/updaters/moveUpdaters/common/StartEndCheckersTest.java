package ru.mplobanov.updaters.moveUpdaters.common;

import org.junit.jupiter.api.Test;
import ru.mplobanov.figure.Figure;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.FigurePositionException;
import ru.mplobanov.figure.Color;
import ru.mplobanov.game.board.Board;
import ru.mplobanov.game.exceptions.BusyCellException;
import ru.mplobanov.updaters.moveUpdaters.common.rules.IsEndEmptyChecker;
import ru.mplobanov.updaters.moveUpdaters.common.rules.IsStartBusyChecker;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;

import static org.junit.jupiter.api.Assertions.*;

class StartEndCheckersTest {
    @Test
    void checkOK() throws FigurePositionException, BusyCellException {
        Board board = new Board();
        FigurePosition start = new FigurePosition(0, 0);
        FigurePosition end = new FigurePosition(1, 1);
        board.setFigure(new Figure(Color.WHITE), start);

        Move move = new Move(start, end);

        IsStartBusyChecker isStartBusyChecker = new IsStartBusyChecker();
        IsEndEmptyChecker isEndEmptyChecker = new IsEndEmptyChecker();

        assertDoesNotThrow(() -> isStartBusyChecker.check(move, board));
        assertDoesNotThrow(() -> isEndEmptyChecker.check(move, board));
    }

    @Test
    void checkFail() throws FigurePositionException, BusyCellException {
        Board board = new Board();
        FigurePosition start = new FigurePosition(0, 0);
        FigurePosition end = new FigurePosition(1, 1);
        board.setFigure(new Figure(Color.WHITE), end);

        Move move = new Move(start, end);

        IsStartBusyChecker isStartBusyChecker = new IsStartBusyChecker();
        IsEndEmptyChecker isEndEmptyChecker = new IsEndEmptyChecker();

        assertThrows(MoveCheckerException.class, () -> isStartBusyChecker.check(move, board), "The start cell of the move is empty.");
        assertThrows(MoveCheckerException.class, () -> isEndEmptyChecker.check(move, board), "The end cell of the move is taken.");

    }
}