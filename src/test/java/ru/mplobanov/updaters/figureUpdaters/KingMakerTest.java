package ru.mplobanov.updaters.figureUpdaters;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mplobanov.figure.Figure;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.FigurePositionException;
import ru.mplobanov.figure.Color;
import ru.mplobanov.game.board.Board;
import ru.mplobanov.game.exceptions.BusyCellException;
import ru.mplobanov.game.exceptions.EmptyCellException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.king.KingMoveChecker;

class KingMakerTest {

    @Test
    void makeWhiteKing() throws FigurePositionException, BusyCellException, EmptyCellException {
        Board b = new Board();
        b.setFigure(new Figure(Color.WHITE), new FigurePosition(6, 0));

        Move mv = new Move(new FigurePosition(6, 0), new FigurePosition(7, 1));

        KingMaker maker = new KingMaker();

        maker.update(mv, b);

        // KingMaker does not move the figure!
        Assertions.assertThat(b.getFigure(new FigurePosition(6, 0)).getMoveChecker()).isInstanceOf(KingMoveChecker.class);
    }

    @Test
    void makeBlackKing() throws FigurePositionException, BusyCellException, EmptyCellException {
        Board b = new Board();
        b.setFigure(new Figure(Color.BLACK), new FigurePosition(1, 0));

        Move mv = new Move(new FigurePosition(1, 0), new FigurePosition(0, 1));

        KingMaker maker = new KingMaker();

        maker.update(mv, b);

        // KingMaker does not move the figure!
        Assertions.assertThat(b.getFigure(new FigurePosition(1, 0)).getMoveChecker()).isInstanceOf(KingMoveChecker.class);
    }

    @Test
    void notMakeWhiteKing() throws FigurePositionException, BusyCellException, EmptyCellException {
        Board b = new Board();
        b.setFigure(new Figure(Color.WHITE), new FigurePosition(1, 0));

        Move mv = new Move(new FigurePosition(1, 0), new FigurePosition(0, 1));

        KingMaker maker = new KingMaker();

        maker.update(mv, b);

        // KingMaker does not move the figure!
        Assertions.assertThat(b.getFigure(new FigurePosition(1, 0)).getMoveChecker()).isNotInstanceOf(KingMoveChecker.class);
    }

    @Test
    void notMakeBlackKing() throws FigurePositionException, BusyCellException, EmptyCellException {
        Board b = new Board();
        b.setFigure(new Figure(Color.BLACK), new FigurePosition(6, 0));

        Move mv = new Move(new FigurePosition(6, 0), new FigurePosition(7, 1));

        KingMaker maker = new KingMaker();

        maker.update(mv, b);

        // KingMaker does not move the figure!
        Assertions.assertThat(b.getFigure(new FigurePosition(6, 0)).getMoveChecker()).isNotInstanceOf(KingMoveChecker.class);
    }

}