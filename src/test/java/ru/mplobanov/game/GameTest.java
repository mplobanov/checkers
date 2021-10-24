package ru.mplobanov.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mplobanov.figure.Figure;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.FigurePositionException;
import ru.mplobanov.figure.Color;
import ru.mplobanov.game.exceptions.CellException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;

public class GameTest {
    @Test
    void saveFigure() throws FigurePositionException, CellException {
        Game g = new Game();
        g.setFigures(new FigurePosition[]{new FigurePosition(0, 0)}, new Figure[]{new Figure(Color.WHITE)});

//        g.makeMove(move);

        Assertions.assertEquals(g.getBoard().getFigureAmount(), 1);
        Assertions.assertTrue(g.getBoard().hasFigure(new FigurePosition(0, 0)));
    }

    @Test
    void moveFigure() throws FigurePositionException, CellException, MoveCheckerException {
        Game g = new Game();
        g.setFigures(new FigurePosition[]{new FigurePosition(0, 0)}, new Figure[]{new Figure(Color.WHITE)});
        Move move = new Move(new FigurePosition(0, 0), new FigurePosition(1, 1));

        g.makeMove(move, Color.WHITE);

        Assertions.assertEquals(g.getBoard().getFigureAmount(), 1);
        Assertions.assertTrue(g.getBoard().hasFigure(new FigurePosition(1, 1)));
    }
}
