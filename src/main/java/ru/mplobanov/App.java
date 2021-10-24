package ru.mplobanov;

import ru.mplobanov.figure.*;
import ru.mplobanov.game.Game;
import ru.mplobanov.game.board.ReadonlyBoard;
import ru.mplobanov.game.exceptions.CellException;
import ru.mplobanov.input.BoardInputConverter;
import ru.mplobanov.input.BoardInputException;
import ru.mplobanov.updaters.moveUpdaters.king.KingMoveChecker;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;
import ru.mplobanov.updaters.moveUpdaters.moveGroup.MoveGroupException;

import java.util.Scanner;

public final class App {
    private App() {
    }
    public static void main(String[] args) {
        try (Scanner scn = new Scanner(System.in)) {

            Game game = new Game();
            BoardInputConverter.placeFigures(scn.nextLine(), game, Color.WHITE);
            BoardInputConverter.placeFigures(scn.nextLine(), game, Color.BLACK);
            while (scn.hasNextLine()) {
                String movesString = scn.nextLine();
                BoardInputConverter.makeMoves(movesString, game);
            }

            // простите меня за такое, я честно не хотел
            ReadonlyBoard board = game.getBoard();
            for (Color color : Color.values()) {
                for (int col = 0; col < 8; col++) {
                    for (int row = 0; row < 8; row++) {
                        FigurePosition position = new FigurePosition(row, col);
                        if (board.hasFigure(position)) {
                            LockedFigure figure = board.getLockedFigure(position);
                            if (figure.getColor().equals(color) && figure.getMoveChecker() instanceof KingMoveChecker) {
                                System.out.printf("%s ", BoardInputConverter.convert(new FigurePosition(row, col),
                                        game.getBoard().getLockedFigure(new FigurePosition(row, col))));
                            }
                        }
                    }
                }
                for (int col = 0; col < 8; col++) {
                    for (int row = 0; row < 8; row++) {
                        FigurePosition position = new FigurePosition(row, col);
                        if (board.hasFigure(position)) {
                            LockedFigure figure = board.getLockedFigure(position);
                            if (figure.getColor().equals(color)
                                    && !(figure.getMoveChecker() instanceof KingMoveChecker)) {
                                System.out.printf("%s ", BoardInputConverter.convert(new FigurePosition(row, col),
                                        game.getBoard().getLockedFigure(new FigurePosition(row, col))));
                            }
                        }
                    }
                }
                System.out.println("");
            }
        } catch (FigurePositionException | MoveGroupException | BoardInputException | CellException e) {
            System.out.println("error");
        } catch (MoveCheckerException e) {
            if (e.getMessage().equals("You must capture a figure if you can.")) {
                System.out.println("invalid move");
            } else if (e.getMessage().equals("The end cell must be black.")) {
                System.out.println("white cell");
            } else if (e.getMessage().equals("The end cell of the move is taken.")) {
                System.out.println("busy cell");
            }
        }
    }
}
