package ru.mplobanov.updaters.moveUpdaters.moveGroup;

import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.updaters.moveUpdaters.move.Move;

import java.util.ArrayList;
import java.util.Iterator;

// used only for capture!
public class MoveGroup implements Iterable<Move> {
    private final ArrayList<FigurePosition> positions;

    public MoveGroup(ArrayList<FigurePosition> positions) throws MoveGroupException {
        if (positions.size() < 2) {
            throw new MoveGroupException("MoveGroup should consist of 2 positions at least.");
        }
        this.positions = positions;
    }

    public FigurePosition getPosition(int index) {
        return positions.get(index);
    }

    @Override
    public Iterator<Move> iterator() {
        return new Iterator<Move>() {
            private int index = -1;

            @Override
            public boolean hasNext() {
                return index + 2 < positions.size();
            }

            @Override
            public Move next() {
                index++;
                return new Move(positions.get(index), positions.get(index + 1));
            }
        };
    }

    public int getPositionsAmount() {
        return positions.size();
    }
}
