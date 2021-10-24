package ru.mplobanov.updaters.moveUpdaters.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mplobanov.figure.Color;
import ru.mplobanov.figure.Figure;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.FigurePositionException;
import ru.mplobanov.game.board.Board;
import ru.mplobanov.game.exceptions.BusyCellException;
import ru.mplobanov.game.exceptions.EmptyCellException;
import ru.mplobanov.updaters.moveUpdaters.king.KingMoveChecker;
import ru.mplobanov.updaters.moveUpdaters.move.Move;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static ru.mplobanov.utils.MoveUtils.getAllMoves;

class AllMovesTest {
    private Board board;
    private Figure figure;

    @BeforeEach
    void setUp() {
        board = new Board();
        figure = new Figure(Color.WHITE);
    }

    @Test
    void allMoves1() throws FigurePositionException, BusyCellException, EmptyCellException {
        // Board:
        // 7| _ _ _ _|
        // 6|_ _ _ _ |
        // 5| _ _ _ _|
        // 4|_ _ _ _ |
        // 3| _ _ _ _|
        // 2|_ _ b _ |
        // 1| _ w _ _|
        // 0|_ _ _ _ |
        //   01234567

        FigurePosition start = new FigurePosition(1, 3);

        board.setFigure(figure, start);
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(2, 4));

        HashSet<Move> moves = getAllMoves(start, board);

        HashSet<Move> rightMoves = new HashSet<>();
        rightMoves.add(new Move(start, new FigurePosition(3, 5)));

        assertEquals(rightMoves, moves);
    }

    @Test
    void allMoves2() throws FigurePositionException, BusyCellException, EmptyCellException {
        // Board:
        // 7| _ _ _ _|
        // 6|_ _ _ _ |
        // 5| _ _ _ _|
        // 4|_ _ _ _ |
        // 3| _ _ b _|
        // 2|_ _ _ _ |
        // 1| _ w _ _|
        // 0|_ _ _ _ |
        //   01234567

        FigurePosition start = new FigurePosition(1, 3);

        board.setFigure(figure, start);
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(3, 5));

        HashSet<Move> moves = getAllMoves(start, board);

        HashSet<Move> rightMoves = new HashSet<>();
        rightMoves.add(new Move(start, new FigurePosition(2, 4)));
        rightMoves.add(new Move(start, new FigurePosition(0, 4)));
        rightMoves.add(new Move(start, new FigurePosition(2, 2)));
        rightMoves.add(new Move(start, new FigurePosition(0, 2)));

        assertEquals(rightMoves, moves);
    }

    @Test
    void allMoves3() throws FigurePositionException, BusyCellException, EmptyCellException {
        // Board:
        // 7| _ _ _ _|
        // 6|_ _ _ _ |
        // 5| _ _ _ _|
        // 4|_ _ _ _ |
        // 3| _ _ _ _|
        // 2|_ _ _ _ |
        // 1| _ w _ _|
        // 0|_ _ b _ |
        //   01234567

        FigurePosition start = new FigurePosition(1, 3);

        board.setFigure(figure, start);
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(0, 4));

        HashSet<Move> moves = getAllMoves(start, board);

        HashSet<Move> rightMoves = new HashSet<>();
        rightMoves.add(new Move(start, new FigurePosition(2, 4)));
        rightMoves.add(new Move(start, new FigurePosition(2, 2)));
        rightMoves.add(new Move(start, new FigurePosition(0, 2)));

        assertEquals(rightMoves, moves);
    }

    @Test
    void allMoves4() throws FigurePositionException, BusyCellException, EmptyCellException {
        // Board:
        // 7| _ _ _ _|
        // 6|_ _ _ _ |
        // 5| _ _ _ _|
        // 4|_ _ _ _ |
        // 3| _ _ _ _|
        // 2|_ b b _ |
        // 1| _ w _ _|
        // 0|_ _ _ _ |
        //   01234567

        FigurePosition start = new FigurePosition(1, 3);

        board.setFigure(figure, start);
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(2, 4));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(2, 2));

        HashSet<Move> moves = getAllMoves(start, board);

        HashSet<Move> rightMoves = new HashSet<>();
        rightMoves.add(new Move(start, new FigurePosition(3, 1)));
        rightMoves.add(new Move(start, new FigurePosition(3, 5)));

        assertEquals(rightMoves, moves);
    }

    @Test
    void allKingMoves1() throws FigurePositionException, BusyCellException, EmptyCellException {
        // Board:
        // 7| _ _ _ _|
        // 6|_ _ _ _ |
        // 5| _ _ _ _|
        // 4|_ _ _ _ |
        // 3| _ _ b _|
        // 2|_ _ _ _ |
        // 1| _ w _ _|
        // 0|_ _ b _ |
        //   01234567

        FigurePosition start = new FigurePosition(1, 3);

        figure.setMoveChecker(new KingMoveChecker());

        board.setFigure(figure, start);
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(0, 4));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(3, 5));

        HashSet<Move> moves = getAllMoves(start, board);

        HashSet<Move> rightMoves = new HashSet<>();
        rightMoves.add(new Move(start, new FigurePosition(4, 6)));
        rightMoves.add(new Move(start, new FigurePosition(5, 7)));

        assertEquals(rightMoves, moves);
    }

    @Test
    void allKingMoves2() throws FigurePositionException, BusyCellException, EmptyCellException {
        // Board:
        // 7| _ _ _ _|
        // 6|_ _ _ _ |
        // 5| _ _ _ _|
        // 4|_ _ _ _ |
        // 3| _ _ b _|
        // 2|_ b b _ |
        // 1| _ w _ _|
        // 0|_ _ _ _ |
        //   01234567

        FigurePosition start = new FigurePosition(1, 3);

        figure.setMoveChecker(new KingMoveChecker());

        board.setFigure(figure, start);
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(2, 4));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(3, 5));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(2, 2));

        HashSet<Move> moves = getAllMoves(start, board);

        HashSet<Move> rightMoves = new HashSet<>();
        rightMoves.add(new Move(start, new FigurePosition(3, 1)));
        rightMoves.add(new Move(start, new FigurePosition(4, 0)));

        assertEquals(rightMoves, moves);
    }
}