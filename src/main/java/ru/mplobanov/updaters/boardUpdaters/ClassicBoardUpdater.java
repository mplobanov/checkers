package ru.mplobanov.updaters.boardUpdaters;

import ru.mplobanov.game.board.Board;
import ru.mplobanov.game.exceptions.CellException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;

public class ClassicBoardUpdater implements BoardUpdater {
    private static final BoardUpdater FIGURE_MOVER = new FigureMover();
    private static final PrayRemover PRAY_REMOVER = new PrayRemover();

    @Override
    public void update(Move move, Board board) throws CellException {
        FIGURE_MOVER.update(move, board);
        PRAY_REMOVER.update(move, board);
    }
}
