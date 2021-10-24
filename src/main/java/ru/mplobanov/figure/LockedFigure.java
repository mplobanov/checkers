package ru.mplobanov.figure;

import ru.mplobanov.updaters.moveUpdaters.move.CompositeMoveChecker;
import ru.mplobanov.updaters.moveUpdaters.regular.RegularMoveChecker;

public class LockedFigure {
    private CompositeMoveChecker moveChecker = new RegularMoveChecker();

    protected void setColor(Color color) {
        this.color = color;
    }

    private Color color;

    protected void setMoveChecker(CompositeMoveChecker moveChecker) {
        this.moveChecker = moveChecker;
    }

    public LockedFigure(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public CompositeMoveChecker getMoveChecker() {
        return moveChecker;
    }
}
