package ru.mplobanov.updaters.moveUpdaters.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mplobanov.figure.Color;
import ru.mplobanov.figure.Figure;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.FigurePositionException;
import ru.mplobanov.game.board.Board;
import ru.mplobanov.game.exceptions.BusyCellException;
import ru.mplobanov.updaters.moveUpdaters.common.rules.EnemyChecker;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;

import static org.junit.jupiter.api.Assertions.*;

class EnemyCheckerTest {
    static final EnemyChecker checker = new EnemyChecker();

    Board board;
    Move move;

    @BeforeEach
    void setUp() throws FigurePositionException, BusyCellException {
        board = new Board();
        board.setFigure(new Figure(Color.WHITE), new FigurePosition(0, 0));
        move = new Move(new FigurePosition(0, 0), new FigurePosition(7, 7));
    }

    @Test
    void checkNoEnemies() {
        assertDoesNotThrow(() -> checker.check(move, board));
    }

    @Test
    void checkOneEnemy() throws FigurePositionException, BusyCellException {
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(2, 2));

        assertDoesNotThrow(() -> checker.check(move, board));
    }

    @Test
    void checkManyEnemies() throws FigurePositionException, BusyCellException {
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(2, 2));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(4, 4));

        assertThrows(MoveCheckerException.class, () -> checker.check(move, board), "You must capture 0 or 1 enemy figure at one move.");
    }
}