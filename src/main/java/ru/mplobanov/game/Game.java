package ru.mplobanov.game;

import ru.mplobanov.figure.Color;
import ru.mplobanov.figure.Figure;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.game.board.Board;
import ru.mplobanov.game.board.ReadonlyBoard;
import ru.mplobanov.game.exceptions.BusyCellException;
import ru.mplobanov.game.exceptions.CellException;
import ru.mplobanov.updaters.ClassicGameUpdater;
import ru.mplobanov.updaters.GameUpdater;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;
import ru.mplobanov.updaters.moveUpdaters.moveGroup.MoveGroup;
import ru.mplobanov.updaters.moveUpdaters.moveGroup.MoveGroupException;

public class Game {
    private final Board board = new Board();
    private static final GameUpdater UPDATER = new ClassicGameUpdater();

    public void makeMove(Move move, Color color) throws CellException, MoveCheckerException {
        if (!board.getFigure(move.getStartPosition()).getColor().equals(color)) {
            throw new CellException("You can't move your enemy's figures.", move.getStartPosition());
        }
        UPDATER.update(move, board);
    }

    public void makeMoveGroup(MoveGroup moveGroup, Color color)
            throws CellException, MoveCheckerException, MoveGroupException {
        if (!board.getFigure(moveGroup.getPosition(0)).getColor().equals(color)) {
            throw new CellException("You can't move your enemy's figures.", moveGroup.getPosition(0));
        }
        UPDATER.update(moveGroup, board);
    }

    public ReadonlyBoard getBoard() {
        return board;
    }

    public void setFigures(FigurePosition[] positions, Figure[] figures) throws BusyCellException {
        for (int i = 0; i < Math.min(positions.length, figures.length); i++) {
            board.setFigure(figures[i], positions[i]);
        }
    }
}
