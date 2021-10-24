package ru.mplobanov.updaters.figureUpdaters;

import ru.mplobanov.figure.Figure;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.Color;
import ru.mplobanov.game.board.ImmutableBoard;
import ru.mplobanov.game.exceptions.EmptyCellException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.king.KingMoveChecker;

public class KingMaker implements FigureUpdater {
    private static void makeKing(Figure figure) {
        figure.setMoveChecker(new KingMoveChecker());
    }

    private static boolean checkKing(Figure figure, FigurePosition finalPosition) {
        boolean whiteKing = figure.getColor() == Color.WHITE
                && finalPosition.getRow() == finalPosition.getRowLimit() - 1;
        boolean blackKing = figure.getColor() == Color.BLACK && finalPosition.getRow() == 0;
        return whiteKing || blackKing;
    }

    @Override
    public void update(Move move, ImmutableBoard board) throws EmptyCellException {
        if (checkKing(board.getFigure(move.getStartPosition()), move.getEndPosition())) {
            makeKing(board.getFigure(move.getStartPosition()));
        }
    }
}
