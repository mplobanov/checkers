package ru.mplobanov.updaters.moveUpdaters.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.FigurePositionException;
import ru.mplobanov.game.board.Board;
import ru.mplobanov.updaters.moveUpdaters.common.rules.NonZeroChecker;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;

import static org.junit.jupiter.api.Assertions.*;

class NonZeroCheckerTest {

    private Board board;
    private Move move;
    static private final NonZeroChecker checker = new NonZeroChecker();

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void zeroMoveFail() throws FigurePositionException {
        move = new Move(new FigurePosition(5, 4), new FigurePosition(5, 4));

        assertThrows(MoveCheckerException.class, () -> checker.check(move, board), "The starter point of the move can't be its end point.");
    }

    @Test
    void nonZeroMoveOK() throws FigurePositionException {
        move = new Move(new FigurePosition(5, 4), new FigurePosition(4, 5));

        assertDoesNotThrow(() -> checker.check(move, board));
    }
}