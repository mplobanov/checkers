package ru.mplobanov.updaters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mplobanov.figure.Color;
import ru.mplobanov.figure.Figure;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.FigurePositionException;
import ru.mplobanov.game.board.Board;
import ru.mplobanov.game.exceptions.CellException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;
import ru.mplobanov.updaters.moveUpdaters.moveGroup.MoveGroup;
import ru.mplobanov.updaters.moveUpdaters.moveGroup.MoveGroupException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClassicGameUpdaterTest {
    private Board board;
    private static final ClassicGameUpdater updater = new ClassicGameUpdater();

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void move1() throws FigurePositionException, CellException, MoveCheckerException {
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
        Figure figure = new Figure(Color.WHITE);

        board.setFigure(figure, new FigurePosition(1, 3));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(2, 4));

        Move move = new Move(new FigurePosition(1, 3), new FigurePosition(3, 5));

        updater.update(move, board);

        assertFalse(board.hasFigure(new FigurePosition(2, 4)));
        assertEquals(board.getFigure(new FigurePosition(3, 5)), figure);
        // Board:
        // 7| _ _ _ _|
        // 6|_ _ _ _ |
        // 5| _ _ _ _|
        // 4|_ _ _ _ |
        // 3| _ _ w _|
        // 2|_ _ _ _ |
        // 1| _ _ _ _|
        // 0|_ _ _ _ |
        //   01234567
    }

    @Test
    void move2() throws FigurePositionException, CellException, MoveCheckerException {
        // Board:
        // 7| _ _ _ _|
        // 6|_ _ _ _ |
        // 5| _ _ _ _|
        // 4|_ _ b _ |
        // 3| _ _ _ _|
        // 2|_ _ b _ |
        // 1| _ w _ _|
        // 0|_ _ _ _ |
        //   01234567
        Figure figure = new Figure(Color.WHITE);

        board.setFigure(figure, new FigurePosition(1, 3));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(2, 4));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(4, 4));

        Move move = new Move(new FigurePosition(1, 3), new FigurePosition(3, 5));

        assertThrows(MoveCheckerException.class, () -> updater.update(move, board), "You must capture a figure if you can.");
        // Board:
        // 7| _ _ _ _|
        // 6|_ _ _ _ |
        // 5| _ _ _ _|
        // 4|_ _ b _ |
        // 3| _ _ w _|
        // 2|_ _ _ _ |
        // 1| _ _ _ _|
        // 0|_ _ _ _ |
        //   01234567
    }

    @Test
    void move3() throws FigurePositionException, CellException, MoveCheckerException {
        // king transform

        // Board:
        // 7| _ _ _ _|
        // 6|_ b _ _ |
        // 5| w _ b _|
        // 4|_ _ _ _ |
        // 3| _ _ _ _|
        // 2|_ _ _ _ |
        // 1| _ _ _ _|
        // 0|_ _ _ _ |
        //   01234567
        Figure figure = new Figure(Color.WHITE);

        board.setFigure(figure, new FigurePosition(5, 1));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(6, 2));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(5, 5));

        Move move = new Move(new FigurePosition(5, 1), new FigurePosition(7, 3));

        assertThrows(MoveCheckerException.class, () -> updater.update(move, board), "You must capture a figure if you can.");
        // Board:
        // 7| _ W _ _|
        // 6|_ _ _ _ |
        // 5| _ _ b _|
        // 4|_ _ _ _ |
        // 3| _ _ _ _|
        // 2|_ _ _ _ |
        // 1| _ _ _ _|
        // 0|_ _ _ _ |
        //   01234567
    }

    @Test
    void moveGroup1() throws FigurePositionException, CellException, MoveCheckerException, MoveGroupException {
        // king transform

        // Board:
        // 7| _ _ _ _|
        // 6|_ b _ _ |
        // 5| w _ b _|
        // 4|_ _ _ _ |
        // 3| _ _ _ _|
        // 2|_ _ _ _ |
        // 1| _ _ _ _|
        // 0|_ _ _ _ |
        //   01234567
        Figure figure = new Figure(Color.WHITE);

        board.setFigure(figure, new FigurePosition(5, 1));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(6, 2));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(5, 5));

        ArrayList<FigurePosition> positions = new ArrayList<>();
        positions.add(new FigurePosition(5, 1));
        positions.add(new FigurePosition(7, 3));
        positions.add(new FigurePosition(3, 7));

        MoveGroup moveGroup = new MoveGroup(positions);

        assertDoesNotThrow(() -> updater.update(moveGroup, board));
        assertFalse(board.hasFigure(new FigurePosition(6, 2)));
        assertFalse(board.hasFigure(new FigurePosition(5, 5)));
        assertEquals(board.getFigure(new FigurePosition(3, 7)), figure);
        // Board:
        // 7| _ _ _ _|
        // 6|_ _ _ _ |
        // 5| _ _ b _|
        // 4|_ _ _ _ |
        // 3| _ _ _ W|
        // 2|_ _ _ _ |
        // 1| _ _ _ _|
        // 0|_ _ _ _ |
        //   01234567
    }

    @Test
    void moveGroup2() throws FigurePositionException, CellException, MoveCheckerException, MoveGroupException {
        // Board:
        // 7| _ _ _ _|
        // 6|_ _ _ _ |
        // 5| _ b b _|
        // 4|_ _ _ _ |
        // 3| _ b b _|
        // 2|_ _ w _ |
        // 1| _ _ _ _|
        // 0|_ _ _ _ |
        //   01234567
        Figure figure = new Figure(Color.WHITE);

        board.setFigure(figure, new FigurePosition(2, 4));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(3, 3));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(3, 5));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(5, 3));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(5, 5));

        ArrayList<FigurePosition> positions = new ArrayList<>();
        positions.add(new FigurePosition(2, 4));
        positions.add(new FigurePosition(4, 2));
        positions.add(new FigurePosition(6, 4));
        positions.add(new FigurePosition(4, 6));
        positions.add(new FigurePosition(2, 4));

        MoveGroup moveGroup = new MoveGroup(positions);

        assertDoesNotThrow(() -> updater.update(moveGroup, board));
        assertFalse(board.hasFigure(new FigurePosition(3, 3)));
        assertFalse(board.hasFigure(new FigurePosition(3, 5)));
        assertFalse(board.hasFigure(new FigurePosition(5, 3)));
        assertFalse(board.hasFigure(new FigurePosition(5, 5)));
        assertEquals(board.getFigure(new FigurePosition(2, 4)), figure);
        // Board:
        // 7| _ _ _ _|
        // 6|_ _ _ _ |
        // 5| _ _ _ _|
        // 4|_ _ _ _ |
        // 3| _ _ _ _|
        // 2|_ _ w _ |
        // 1| _ _ _ _|
        // 0|_ _ _ _ |
        //   01234567
    }

    @Test
    void moveGroup3() throws FigurePositionException, CellException, MoveCheckerException, MoveGroupException {
        // Board:
        // 7| _ _ _ _|
        // 6|_ _ _ _ |
        // 5| _ b b _|
        // 4|_ _ _ _ |
        // 3| _ b b _|
        // 2|_ _ w _ |
        // 1| _ _ _ _|
        // 0|_ _ _ _ |
        //   01234567
        Figure figure = new Figure(Color.WHITE);

        board.setFigure(figure, new FigurePosition(2, 4));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(3, 3));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(3, 5));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(5, 3));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(5, 5));

        ArrayList<FigurePosition> positions = new ArrayList<>();
        positions.add(new FigurePosition(2, 4));
        positions.add(new FigurePosition(4, 2));
        positions.add(new FigurePosition(6, 4));
        positions.add(new FigurePosition(4, 6));

        MoveGroup moveGroup = new MoveGroup(positions);

        assertThrows(MoveCheckerException.class, () -> updater.update(moveGroup, board), "You must capture a figure if you can.");
        // Board:
        // 7| _ _ _ _|
        // 6|_ _ _ _ |
        // 5| _ _ _ _|
        // 4|_ _ _ w |
        // 3| _ _ b _|
        // 2|_ _ _ _ |
        // 1| _ _ _ _|
        // 0|_ _ _ _ |
        //   01234567
    }

    @Test
    void moveGroup4() throws FigurePositionException, CellException, MoveCheckerException, MoveGroupException {
        // Board:
        // 7| _ _ _ _|
        // 6|_ _ _ _ |
        // 5| _ b b _|
        // 4|_ _ _ _ |
        // 3| _ b b _|
        // 2|_ _ w _ |
        // 1| _ _ _ _|
        // 0|_ _ _ _ |
        //   01234567
        Figure figure = new Figure(Color.WHITE);

        board.setFigure(figure, new FigurePosition(2, 4));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(3, 3));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(3, 5));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(5, 3));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(5, 5));

        ArrayList<FigurePosition> positions = new ArrayList<>();
        positions.add(new FigurePosition(2, 4));
        positions.add(new FigurePosition(4, 2));
        positions.add(new FigurePosition(6, 4));
        positions.add(new FigurePosition(4, 6));
        positions.add(new FigurePosition(3, 7));

        MoveGroup moveGroup = new MoveGroup(positions);

        assertThrows(MoveCheckerException.class, () -> updater.update(moveGroup, board), "You must capture a figure if you can.");
        // Board:
        // 7| _ _ _ _|
        // 6|_ _ _ _ |
        // 5| _ _ _ _|
        // 4|_ _ _ _ |
        // 3| _ _ b w|
        // 2|_ _ _ _ |
        // 1| _ _ _ _|
        // 0|_ _ _ _ |
        //   01234567
    }

    @Test
    void moveGroup5() throws FigurePositionException, CellException, MoveCheckerException, MoveGroupException {
        // Board:
        // 7| _ _ _ _|
        // 6|_ _ _ _ |
        // 5| _ b b _|
        // 4|_ _ _ _ |
        // 3| _ b _ _|
        // 2|_ _ w _ |
        // 1| _ _ _ _|
        // 0|_ _ _ _ |
        //   01234567
        Figure figure = new Figure(Color.WHITE);

        board.setFigure(figure, new FigurePosition(2, 4));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(3, 3));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(5, 3));
        board.setFigure(new Figure(Color.BLACK), new FigurePosition(5, 5));

        ArrayList<FigurePosition> positions = new ArrayList<>();
        positions.add(new FigurePosition(2, 4));
        positions.add(new FigurePosition(4, 2));
        positions.add(new FigurePosition(6, 4));
        positions.add(new FigurePosition(4, 6));
        positions.add(new FigurePosition(3, 7));

        MoveGroup moveGroup = new MoveGroup(positions);

        assertThrows(MoveGroupException.class, () -> updater.update(moveGroup, board), "You can only capture in move group.");
        // Board:
        // 7| _ _ _ _|
        // 6|_ _ _ _ |
        // 5| _ _ _ _|
        // 4|_ _ _ _ |
        // 3| _ _ _ w|
        // 2|_ _ _ _ |
        // 1| _ _ _ _|
        // 0|_ _ _ _ |
        //   01234567
    }

}