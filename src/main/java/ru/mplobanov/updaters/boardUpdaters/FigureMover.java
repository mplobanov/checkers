package ru.mplobanov.updaters.boardUpdaters;

import ru.mplobanov.figure.Figure;
import ru.mplobanov.game.board.Board;
import ru.mplobanov.game.exceptions.CellException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;

public class FigureMover implements BoardUpdater {
    @Override
    public void update(Move move, Board board) throws CellException {
        Figure figure = board.getFigure(move.getStartPosition());
        board.removeFigure(move.getStartPosition());
        board.setFigure(figure, move.getEndPosition());
    }
}
