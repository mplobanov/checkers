package ru.mplobanov.updaters.boardUpdaters;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mplobanov.figure.Figure;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.FigurePositionException;
import ru.mplobanov.figure.Color;
import ru.mplobanov.game.board.Board;
import ru.mplobanov.game.exceptions.CellException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;

class PrayRemoverTest {
    @Test
    public void removeTest() throws FigurePositionException, CellException {
        Board b = new Board();
        b.setFigure(new Figure(Color.WHITE), new FigurePosition(0, 0));
        b.setFigure(new Figure(Color.BLACK), new FigurePosition(1, 1));

        Move move = new Move(new FigurePosition(0, 0), new FigurePosition(2, 2));

        PrayRemover remover = new PrayRemover();
        remover.update(move, b);

        Assertions.assertThat(b.getFigureAmount()).isEqualTo(1);
        Assertions.assertThat(b.hasFigure(new FigurePosition(0, 0))).isTrue(); // PrayRemover does NOT move the figure!
        Assertions.assertThat(b.hasFigure(new FigurePosition(1, 1))).isFalse();
        Assertions.assertThat(b.hasFigure(new FigurePosition(2, 2))).isFalse();
    }

    @Test
    public void doNotTouchTest() throws FigurePositionException, CellException {
        Board b = new Board();
        b.setFigure(new Figure(Color.WHITE), new FigurePosition(0, 0));
        b.setFigure(new Figure(Color.BLACK), new FigurePosition(1, 1));
        b.setFigure(new Figure(Color.BLACK), new FigurePosition(1, 0));

        Move move = new Move(new FigurePosition(0, 0), new FigurePosition(2, 2));

        PrayRemover remover = new PrayRemover();
        remover.update(move, b);

        Assertions.assertThat(b.hasFigure(new FigurePosition(1, 0))).isTrue();
    }

    @Test
    public void bigLeapTest() throws FigurePositionException, CellException {
        Board b = new Board();
        b.setFigure(new Figure(Color.WHITE), new FigurePosition(0, 0));
        b.setFigure(new Figure(Color.BLACK), new FigurePosition(3, 3));

        Move move = new Move(new FigurePosition(0, 0), new FigurePosition(5, 5));

        PrayRemover remover = new PrayRemover();
        remover.update(move, b);

        Assertions.assertThat(b.hasFigure(new FigurePosition(3, 3))).isFalse();
    }
}