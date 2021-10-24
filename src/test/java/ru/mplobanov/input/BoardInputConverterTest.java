package ru.mplobanov.input;

import org.junit.jupiter.api.Test;
import ru.mplobanov.figure.Color;
import ru.mplobanov.figure.Figure;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.FigurePositionException;
import ru.mplobanov.game.Game;
import ru.mplobanov.game.exceptions.BusyCellException;
import ru.mplobanov.game.exceptions.CellException;
import ru.mplobanov.game.exceptions.EmptyCellException;
import ru.mplobanov.updaters.moveUpdaters.king.KingMoveChecker;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;
import ru.mplobanov.updaters.moveUpdaters.moveGroup.MoveGroupException;


import static org.junit.jupiter.api.Assertions.*;

class BoardInputConverterTest {
    @Test
    void convert() throws FigurePositionException {
        assertEquals(BoardInputConverter.convert("b3"), new FigurePosition(2, 1));

        assertEquals(BoardInputConverter.convert("G6"), new FigurePosition(5, 6));

        assertEquals(BoardInputConverter.convert("a1"), new FigurePosition(0, 0));
    }

    @Test
    void King() {
        assertTrue(BoardInputConverter.isKing("A3"));

        assertFalse(BoardInputConverter.isKing("b8"));
    }

    @Test
    void placeFigures() throws BusyCellException, FigurePositionException, EmptyCellException {
        Game game = new Game();
        BoardInputConverter.placeFigures("a1 d2 E8", game, Color.WHITE);
        assertTrue(game.getBoard().hasFigure(new FigurePosition(0, 0)));
        assertTrue(game.getBoard().hasFigure(new FigurePosition(1, 3)));
        assertTrue(game.getBoard().hasFigure(new FigurePosition(7, 4)));

        assertTrue(game.getBoard().getLockedFigure(new FigurePosition(7, 4)).getMoveChecker() instanceof KingMoveChecker);
    }

    @Test
    void makeMove()
            throws BoardInputException, FigurePositionException, MoveGroupException, MoveCheckerException, CellException {
        Game game = new Game();
        game.setFigures(new FigurePosition[]{
                new FigurePosition(0, 0),
                new FigurePosition(5, 1),
        }, new Figure[]{
                new Figure(Color.WHITE),
                new Figure(Color.BLACK),
        });
        BoardInputConverter.makeMoves("a1-b2 b6-c5", game);
        System.out.println(game.getBoard());
    }
}