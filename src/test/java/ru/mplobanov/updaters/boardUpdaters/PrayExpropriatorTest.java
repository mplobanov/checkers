package ru.mplobanov.updaters.boardUpdaters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mplobanov.figure.Color;
import ru.mplobanov.figure.Figure;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.FigurePositionException;
import ru.mplobanov.game.board.Board;
import ru.mplobanov.game.exceptions.CellException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;

import static org.junit.jupiter.api.Assertions.*;

class PrayExpropriatorTest {
    private Board board;
    private static final PrayExpropriator expropriator = new PrayExpropriator();

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void expropriate() throws FigurePositionException, CellException {
        board.setFigure(new Figure(Color.WHITE), new FigurePosition(0, 0));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(1, 1));

        Move move = new Move(new FigurePosition(0, 0), new FigurePosition(2, 2));

        expropriator.update(move, board);

        assertEquals(board.getFigure(new FigurePosition(1, 1)).getColor(), Color.WHITE);
    }

    @Test
    void expropriateBig() throws FigurePositionException, CellException {
        board.setFigure(new Figure(Color.WHITE), new FigurePosition(0, 0));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(3, 3));

        Move move = new Move(new FigurePosition(0, 0), new FigurePosition(7, 7));

        expropriator.update(move, board);

        assertEquals(board.getFigure(new FigurePosition(3, 3)).getColor(), Color.WHITE);
    }
}