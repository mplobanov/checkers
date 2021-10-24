package ru.mplobanov.updaters.moveUpdaters.move;

import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.FigurePositionException;

import java.util.Iterator;
import java.util.Objects;

public final class Move implements Iterable<FigurePosition> {
    private final FigurePosition startPosition;
    private final FigurePosition endPosition;

    public Move(FigurePosition startPosition, FigurePosition endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public FigurePosition getStartPosition() {
        return startPosition;
    }

    public FigurePosition getEndPosition() {
        return endPosition;
    }

    @Override
    public Iterator<FigurePosition> iterator() {
        return new Iterator<>() {
            private FigurePosition position = null;

            private boolean between(int x, int a, int b) {
                return Math.min(a, b) < x && x < Math.max(a, b);
            }

            @Override
            public boolean hasNext() {
                if (position == null) {
                    return true;
                }
                if (getStartPosition().equals(getEndPosition())) {
                    return false;
                }
                if (position.equals(getStartPosition())) {
                    return true;
                }

                return between(position.getRow(), getStartPosition().getRow(), getEndPosition().getRow())
                        && between(position.getColumn(), getStartPosition().getColumn(), getEndPosition().getColumn());
            }

            @Override
            public FigurePosition next() {
                if (position == null) {
                    position = getStartPosition();
                    return position;
                }

                int rowStep = Math.round(Math.signum(getEndPosition().getRow() - getStartPosition().getRow()));
                int colStep = Math.round(Math.signum(getEndPosition().getColumn() - getStartPosition().getColumn()));
                try {
                    position = new FigurePosition(position.getRow() + rowStep, position.getColumn() + colStep);
                } catch (FigurePositionException e) {
                    return null;
                }
                return position;
            }
        };
    }

    public int getMoveSize() {
        return Math.max(
                Math.abs(startPosition.getColumn() - endPosition.getColumn()),
                Math.abs(startPosition.getRow() - endPosition.getRow())
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move that = (Move) o;
        return startPosition.equals(that.startPosition) && endPosition.equals(that.endPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPosition, endPosition);
    }

    @Override
    public String toString() {
        return "Move{" + startPosition
                + " -> " + endPosition
                + '}';
    }
}
