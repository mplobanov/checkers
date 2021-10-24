package ru.mplobanov.figure;

import ru.mplobanov.updaters.moveUpdaters.move.CompositeMoveChecker;

public final class Figure extends LockedFigure {
    public Figure(Color color) {
        super(color);
    }

    public void setMoveChecker(CompositeMoveChecker moveChecker) {
        super.setMoveChecker(moveChecker);
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
    }

    public void invertColor() {
        setColor(Color.getOpposite(getColor()));
    }
}
