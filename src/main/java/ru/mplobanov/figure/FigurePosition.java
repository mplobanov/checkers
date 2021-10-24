package ru.mplobanov.figure;

import java.util.Objects;

public final class FigurePosition {
    private final int row;
    private final int column;
    private int rowLimit = 8; // row < rowLimit
    private int colLimit = 8; // col < colLimit

    public FigurePosition(int row, int column) throws FigurePositionException {
        this.row = row;
        this.column = column;
        checkPosition();
    }

    public FigurePosition(int row, int column, int rowLimit, int colLimit) throws FigurePositionException {
        this.row = row;
        this.column = column;
        this.rowLimit = rowLimit;
        this.colLimit = colLimit;
        checkPosition();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    private void checkPosition() throws FigurePositionException {
        if (column < 0 || column >= colLimit) {
            throw new FigurePositionException("Incorrect column");
        }
        if (row < 0 || row >= rowLimit) {
            throw new FigurePositionException("Incorrect row");
        }
    }

    @Override
    public String toString() {
        return "FigurePosition{"
                + "row=" + row
                + ", column=" + column
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FigurePosition that = (FigurePosition) o;
        return row == that.row && column == that.column && rowLimit == that.rowLimit && colLimit == that.colLimit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, rowLimit, colLimit);
    }

    public int getRowLimit() {
        return rowLimit;
    }

    public int getColLimit() {
        return colLimit;
    }

    public Color getColor() {
        return (row + column) % 2 == 0 ? Color.BLACK : Color.WHITE;
    }
}
