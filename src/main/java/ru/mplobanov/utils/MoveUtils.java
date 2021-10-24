package ru.mplobanov.utils;

import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.FigurePositionException;
import ru.mplobanov.game.board.Board;
import ru.mplobanov.game.exceptions.EmptyCellException;
import ru.mplobanov.updaters.moveUpdaters.move.CompositeMoveChecker;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public final class MoveUtils {
    private MoveUtils() {
    }

    public static List<Move> getPossibleMoves(FigurePosition start) {
        List<Move> possibleMoves = new LinkedList<>();
        int[] directions = new int[]{-1, 1};
        for (int rowDirection : directions) {
            for (int colDirection : directions) {
                for (int i = 1; i < Math.max(start.getRowLimit(), start.getColLimit()); i++) {
                    try {
                        possibleMoves.add(new Move(start,
                                new FigurePosition(
                                        start.getRow() + i * rowDirection,
                                        start.getColumn() + i * colDirection
                                ))
                        );
                    } catch (FigurePositionException e) {
                        break;
                    }
                }
            }
        }
        return possibleMoves;
    }

    public static HashSet<Move> getAllMoves(FigurePosition start, Board board)
            throws FigurePositionException, EmptyCellException {
        HashSet<Move> movePositions = new HashSet<>();

        CompositeMoveChecker checker = board.getFigure(start).getMoveChecker();

        for (int i = 0; i < start.getRowLimit(); i++) {
            for (int j = 0; j < start.getColLimit(); j++) {
                FigurePosition position = new FigurePosition(i, j);
                Move move = new Move(start, position);
                try {
                    checker.check(move, board);
                } catch (MoveCheckerException e) {
                    continue;
                }
                movePositions.add(move);
            }
        }

        return movePositions;
    }
}
