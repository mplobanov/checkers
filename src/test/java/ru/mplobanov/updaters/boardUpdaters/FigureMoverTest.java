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


class FigureMoverTest {

    @Test
    void update() throws FigurePositionException, CellException {
        Board b = new Board();
        b.setFigure(new Figure(Color.WHITE), new FigurePosition(0, 0));

        Move move = new Move(new FigurePosition(0, 0), new FigurePosition(2, 2));
        FigureMover mover = new FigureMover();

        mover.update(move, b);

        Assertions.assertThat(b.getFigureAmount()).isEqualTo(1);
        Assertions.assertThat(b.hasFigure(new FigurePosition(2, 2))).isTrue();
        Assertions.assertThat(b.hasFigure(new FigurePosition(0, 0))).isFalse();
    }
}