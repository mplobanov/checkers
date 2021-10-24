package ru.mplobanov.updaters.moveUpdaters.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mplobanov.figure.Color;
import ru.mplobanov.figure.Figure;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.FigurePositionException;
import ru.mplobanov.game.board.Board;
import ru.mplobanov.game.exceptions.BusyCellException;
import ru.mplobanov.updaters.moveUpdaters.move.CompositeMoveChecker;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;

import static org.junit.jupiter.api.Assertions.*;

class CommonMoveCheckerTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();

    }


    @Test
    void captureIsObligatoryFail() throws FigurePositionException, BusyCellException {
        // Board:
        // 7| _ _ _ _|
        // 6|_ _ _ _ |
        // 5| _ _ _ _|
        // 4|_ _ _ _ |
        // 3| _ _ _ _|
        // 2|_ _ b _ |
        // 1| _ w _ _|
        // 0|_ _ _ _ |
        //   01234567

        Figure figure = new Figure(Color.WHITE);
        CompositeMoveChecker checker = figure.getMoveChecker();

        board.setFigure(figure, new FigurePosition(1, 3));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(2, 4));

        Move moveFail = new Move(new FigurePosition(1, 3), new FigurePosition(2, 2));
        Move moveOK = new Move(new FigurePosition(1, 3), new FigurePosition(3, 5));

        assertDoesNotThrow(() -> checker.checkNoObligatoryCapture(moveFail, board));

        assertThrows(MoveCheckerException.class, () -> checker.check(moveFail, board), "You must capture a figure if you can.");
        assertDoesNotThrow(() -> checker.check(moveOK, board));
    }
}