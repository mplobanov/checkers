package ru.mplobanov.updaters.figureUpdaters;

import ru.mplobanov.game.board.ImmutableBoard;
import ru.mplobanov.game.exceptions.EmptyCellException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;

public class ClassicFigureUpdater implements FigureUpdater {
    static final KingMaker KING_MAKER = new KingMaker();

    @Override
    public void update(Move move, ImmutableBoard board) throws EmptyCellException {
        KING_MAKER.update(move, board);
    }
}
