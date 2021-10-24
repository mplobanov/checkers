package ru.mplobanov.updaters.boardUpdaters;

import ru.mplobanov.game.board.Board;
import ru.mplobanov.game.exceptions.CellException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;

public interface BoardUpdater {
    void update(Move move, Board board) throws CellException;
}
