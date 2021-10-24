package ru.mplobanov.updaters.moveUpdaters;

import org.junit.jupiter.api.Test;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.FigurePositionException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.moveGroup.MoveGroup;
import ru.mplobanov.updaters.moveUpdaters.moveGroup.MoveGroupException;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MoveGroupTest {
    @Test
    void iterateMoveGroup() throws FigurePositionException, MoveGroupException {
        FigurePosition position1 = new FigurePosition(0, 0);
        FigurePosition position2 = new FigurePosition(2, 2);
        FigurePosition position3 = new FigurePosition(0, 4);

        ArrayList<FigurePosition> positions = new ArrayList<>();
        positions.add(position1);
        positions.add(position2);
        positions.add(position3);

        MoveGroup moveGroup = new MoveGroup(positions);

        Iterator<Move> it = moveGroup.iterator();

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Move(position1, position2));

        assertTrue(it.hasNext());
        assertEquals(it.next(), new Move(position2, position3));

        assertFalse(it.hasNext());

    }

    @Test
    void smallMoveGroup() throws FigurePositionException, MoveGroupException {
        FigurePosition position1 = new FigurePosition(0, 0);
        FigurePosition position2 = new FigurePosition(2, 2);

        ArrayList<FigurePosition> positions = new ArrayList<>();
        assertThrows(MoveGroupException.class, () -> new MoveGroup(positions), "MoveGroup should consist of 2 positions at least.");

        positions.add(position1);

        assertThrows(MoveGroupException.class, () -> new MoveGroup(positions), "MoveGroup should consist of 2 positions at least.");

        positions.add(position2);

        assertDoesNotThrow(() -> new MoveGroup(positions));


    }

}