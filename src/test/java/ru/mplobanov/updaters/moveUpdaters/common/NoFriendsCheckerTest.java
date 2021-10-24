package ru.mplobanov.updaters.moveUpdaters.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mplobanov.figure.Color;
import ru.mplobanov.figure.Figure;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.FigurePositionException;
import ru.mplobanov.game.board.Board;
import ru.mplobanov.game.exceptions.BusyCellException;
import ru.mplobanov.updaters.moveUpdaters.common.rules.NoFriendsChecker;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;

import static org.junit.jupiter.api.Assertions.*;

class NoFriendsCheckerTest {
    private Board b;
    private Move move;
    private Move bigMove;

    private NoFriendsChecker checker = new NoFriendsChecker();

    @BeforeEach
    void setUp() throws FigurePositionException, BusyCellException {
        b = new Board();
        b.setFigure(new Figure(Color.WHITE), new FigurePosition(0, 0));

        move = new Move(new FigurePosition(0, 0), new FigurePosition(2, 2));
        bigMove = new Move(new FigurePosition(7, 0), new FigurePosition(0, 7));

        checker = new NoFriendsChecker();
    }

    @Test
    void checkFriendsOK() throws FigurePositionException, BusyCellException {

        b.setFigure(new Figure(Color.WHITE), new FigurePosition(0, 2));

        assertDoesNotThrow(() -> checker.check(move, b));
    }

    @Test
    void checkFriendsFail() throws FigurePositionException, BusyCellException {

        b.setFigure(new Figure(Color.WHITE), new FigurePosition(1, 1));

        assertThrows(MoveCheckerException.class, () -> checker.check(move, b), "There is a figure of your color on the way.");
    }

    @Test
    void checkBigFriendsFail() throws FigurePositionException, BusyCellException {

        b.setFigure(new Figure(Color.WHITE), new FigurePosition(7, 0));
        b.setFigure(new Figure(Color.WHITE), new FigurePosition(5, 2));
        b.setFigure(new Figure(Color.WHITE), new FigurePosition(3, 4));

        assertThrows(MoveCheckerException.class, () -> checker.check(bigMove, b), "There is a figure of your color on the way.");
    }

}