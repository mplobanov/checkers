package ru.mplobanov.updaters.moveUpdaters.regular;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mplobanov.figure.Color;
import ru.mplobanov.figure.Figure;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.FigurePositionException;
import ru.mplobanov.game.board.Board;
import ru.mplobanov.game.exceptions.BusyCellException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;

import static org.junit.jupiter.api.Assertions.*;

class MoveSizeCheckerTest {
    Board board;
    Move move;

    static final MoveSizeChecker checker = new MoveSizeChecker();

    @BeforeEach
    void setUp() throws FigurePositionException, BusyCellException {
        board = new Board();
        board.setFigure(new Figure(Color.WHITE), new FigurePosition(3, 3));
    }

    @Test
    void tooBigMove() throws FigurePositionException {
        move = new Move(new FigurePosition(3, 3), new FigurePosition(0, 6));

        assertThrows(MoveCheckerException.class, () -> checker.check(move, board), "Too big move for regular figure.");
    }

    @Test
    void okCaptureMove() throws FigurePositionException, BusyCellException {
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(2, 4));

        move = new Move(new FigurePosition(3, 3), new FigurePosition(1, 5));

        assertDoesNotThrow(() -> checker.check(move, board));
    }

    @Test
    void okSmallMove() throws FigurePositionException, BusyCellException {
        move = new Move(new FigurePosition(3, 3), new FigurePosition(2, 4));

        assertDoesNotThrow(() -> checker.check(move, board));
    }
}