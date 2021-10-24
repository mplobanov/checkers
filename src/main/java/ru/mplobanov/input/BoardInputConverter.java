package ru.mplobanov.input;

import ru.mplobanov.figure.*;
import ru.mplobanov.game.Game;
import ru.mplobanov.game.exceptions.BusyCellException;
import ru.mplobanov.game.exceptions.CellException;
import ru.mplobanov.updaters.moveUpdaters.king.KingMoveChecker;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;
import ru.mplobanov.updaters.moveUpdaters.moveGroup.MoveGroup;
import ru.mplobanov.updaters.moveUpdaters.moveGroup.MoveGroupException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class BoardInputConverter {
    private static final Pattern FIGURE_POSITION_REGEX = Pattern.compile("(?<position>[a-hA-H][1-8])");
    private static final Pattern FIGURE_MOVE_REGEX = Pattern.compile("(?<move>([a-hA-H][1-8][:\\-]?){2,})");

    private BoardInputConverter() {
    }

    public static void placeFigures(String input, Game game, Color color)
            throws FigurePositionException, BusyCellException {
        Matcher matcher = FIGURE_POSITION_REGEX.matcher(input);
        while (matcher.find()) {
            String position = matcher.group("position");
            Figure figure = new Figure(color);
            if (isKing(position)) {
                figure.setMoveChecker(new KingMoveChecker());
            }
            game.setFigures(new FigurePosition[]{convert(position)}, new Figure[]{figure});
        }
    }

    public static FigurePosition convert(String input) throws FigurePositionException {
        String lower = input.toLowerCase();
        char c = lower.charAt(0);
        return new FigurePosition(input.charAt(1) - '1', c - 'a');
    }

    public static String convert(FigurePosition position, LockedFigure figure) {
        boolean isKing = figure.getMoveChecker() instanceof KingMoveChecker;
        String s = "" + String.valueOf((char) ((isKing ? 'A' : 'a') + position.getColumn()));
        s += String.valueOf((char) ('1' + position.getRow()));
        return s;
    }

    public static boolean isKing(String input) {
        return !input.toLowerCase().equals(input);
    }

    public static String parseMove(String input, Color color) throws BoardInputException {
        Matcher matcher = FIGURE_MOVE_REGEX.matcher(input);
        if (!matcher.find()) {
            throw new BoardInputException("There is no move for white");
        }
        String whiteMove = matcher.group("move");

        if (!matcher.find()) {
            throw new BoardInputException("There is no move for black");
        }
        String blackMove = matcher.group("move");

        return color.equals(Color.BLACK) ? blackMove : whiteMove;

    }

    public static ArrayList<FigurePosition> convertToPositions(String move)
            throws FigurePositionException {
        ArrayList<FigurePosition> positions = new ArrayList<>();
        Matcher whiteMoveMatcher = FIGURE_POSITION_REGEX.matcher(move);
        while (whiteMoveMatcher.find()) {
            positions.add(convert(whiteMoveMatcher.group("position")));
        }
        return positions;
    }

    public static boolean isCapture(String move) {
        return move.indexOf(':') != -1;
    }

    public static void makeMoves(String input, Game game)
            throws BoardInputException, FigurePositionException, MoveGroupException,
            MoveCheckerException, CellException {
        makeSingleMove(input, game, Color.WHITE);
        makeSingleMove(input, game, Color.BLACK);
    }

    public static void makeSingleMove(String input, Game game, Color color)
            throws BoardInputException, FigurePositionException, MoveGroupException,
            MoveCheckerException, CellException {
        String moveString = parseMove(input, color);
        var positions = convertToPositions(moveString);
        if (isCapture(moveString)) {
            MoveGroup moveGroup = new MoveGroup(positions);
            game.makeMoveGroup(moveGroup, color);
        } else {
            if (positions.size() > 2) {
                throw new BoardInputException("Only one move for non sequential capture.");
            }
            Move move = new Move(positions.get(0), positions.get(1));
            game.makeMove(move, color);
        }
    }


}
