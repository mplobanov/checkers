package ru.mplobanov.updaters;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.FigurePositionException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;

import java.util.Iterator;

public class MoveTest {
    private void checkMoveIterator(FigurePosition start, FigurePosition end, int rowStep, int colStep) throws FigurePositionException {
        Move move = new Move(start, end);
        int row = start.getRow();
        int col = start.getColumn();

        Iterator<FigurePosition> it = move.iterator();
        int stepCount = 0;

        Assertions.assertThat(it.hasNext()).isTrue();
        FigurePosition pos = it.next();
        Assertions.assertThat(pos).isEqualTo(new FigurePosition(row, col));

        while (!(row == end.getRow() && col == end.getColumn())) {
            row += rowStep;
            col += colStep;
            stepCount += 1;

            Assertions.assertThat(it.hasNext()).isTrue();
            pos = it.next();
            Assertions.assertThat(pos).isEqualTo(new FigurePosition(row, col));

            if (stepCount > 8) {
                Assertions.fail("You have faulty tests");
            }
        }

        Assertions.assertThat(it.hasNext()).isFalse();

    }

    @Test
    public void iterateDumbTest() throws FigurePositionException {
        Move move = new Move(new FigurePosition(0, 0), new FigurePosition(2, 2));
        Iterator<FigurePosition> it = move.iterator();

        Assertions.assertThat(it.hasNext()).isTrue();
        FigurePosition pos = it.next();
        Assertions.assertThat(pos).isEqualTo(new FigurePosition(0, 0));

        Assertions.assertThat(it.hasNext()).isTrue();
        pos = it.next();
        Assertions.assertThat(pos).isEqualTo(new FigurePosition(1, 1));

        Assertions.assertThat(it.hasNext()).isTrue();
        pos = it.next();
        Assertions.assertThat(pos).isEqualTo(new FigurePosition(2, 2));

        Assertions.assertThat(it.hasNext()).isFalse();
    }

    @Test
    void iterateTestDownLeft() throws FigurePositionException {
        checkMoveIterator(new FigurePosition(2, 2), new FigurePosition(0, 0), -1, -1);
    }

    @Test
    void iterateTestUpRight() throws FigurePositionException {
        checkMoveIterator(new FigurePosition(0, 0), new FigurePosition(3, 3), 1, 1);
    }

    @Test
    void iterateTestDownRight() throws FigurePositionException {
        checkMoveIterator(new FigurePosition(6, 0), new FigurePosition(0, 6), -1, 1);
    }

    @Test
    void iterateTestUpLeft() throws FigurePositionException {
        checkMoveIterator(new FigurePosition(0, 5), new FigurePosition(5, 0), 1, -1);
    }

    @Test
    void iterateTestNoMove() throws FigurePositionException {
        checkMoveIterator(new FigurePosition(0, 0), new FigurePosition(0, 0), 1, -1);
    }
}
