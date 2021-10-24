package ru.mplobanov.updaters;

import ru.mplobanov.game.board.Board;
import ru.mplobanov.game.exceptions.CellException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;
import ru.mplobanov.updaters.moveUpdaters.moveGroup.MoveGroup;
import ru.mplobanov.updaters.moveUpdaters.moveGroup.MoveGroupException;

public interface GameUpdater {
    void update(Move move, Board board) throws CellException, MoveCheckerException;

    void update(MoveGroup moveGroup, Board board) throws CellException, MoveCheckerException, MoveGroupException;
}
