package ru.mplobanov.updaters;

import ru.mplobanov.figure.Figure;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.game.board.Board;
import ru.mplobanov.game.exceptions.CellException;
import ru.mplobanov.game.exceptions.EmptyCellException;
import ru.mplobanov.updaters.boardUpdaters.BoardUpdater;
import ru.mplobanov.updaters.boardUpdaters.ClassicBoardUpdater;
import ru.mplobanov.updaters.boardUpdaters.PrayExpropriator;
import ru.mplobanov.updaters.boardUpdaters.PrayRemover;
import ru.mplobanov.updaters.boardUpdaters.FigureMover;
import ru.mplobanov.updaters.figureUpdaters.ClassicFigureUpdater;
import ru.mplobanov.updaters.figureUpdaters.FigureUpdater;
import ru.mplobanov.updaters.moveUpdaters.move.Move;
import ru.mplobanov.updaters.moveUpdaters.move.MoveCheckerException;
import ru.mplobanov.updaters.moveUpdaters.moveGroup.MoveGroup;
import ru.mplobanov.updaters.moveUpdaters.moveGroup.MoveGroupException;
import ru.mplobanov.updaters.moveUpdaters.common.CommonMoveChecker;
import ru.mplobanov.updaters.moveUpdaters.common.rules.EnemyChecker;

public class ClassicGameUpdater implements GameUpdater {
    private static final BoardUpdater BOARD_UPDATER = new ClassicBoardUpdater();
    private static final FigureUpdater FIGURE_UPDATER = new ClassicFigureUpdater();

    private static final PrayRemover PRAY_REMOVER = new PrayRemover();
    private static final PrayExpropriator PRAY_EXPROPRIATOR = new PrayExpropriator();
    private static final FigureMover FIGURE_MOVER = new FigureMover();
    private static final EnemyChecker ENEMY_CHECKER = new EnemyChecker();

    @Override
    public void update(Move move, Board board) throws CellException, MoveCheckerException {
        if (!board.hasFigure(move.getStartPosition())) {
            throw new EmptyCellException("There is no figure on the start position of the move.",
                    move.getStartPosition());
        }
        Figure figure = board.getFigure(move.getStartPosition());



        figure.getMoveChecker().check(move, board);
        int enemyCount = ENEMY_CHECKER.getEnemyCount(move, board);
        if (enemyCount == 0) {
            for (FigurePosition position : board.getRawMap().keySet()) {
                if (board.getFigure(position).getColor().equals(figure.getColor())) {
                    CommonMoveChecker.alertCaptureMoves(position, board);
                }
            }
        }
        FIGURE_UPDATER.update(move, board);
        BOARD_UPDATER.update(move, board);

        if (enemyCount == 1) {
            CommonMoveChecker.alertCaptureMoves(move.getEndPosition(), board);
        }
    }

    @Override
    public void update(MoveGroup moveGroup, Board board)
            throws CellException, MoveCheckerException, MoveGroupException {
        Figure figure = board.getFigure(moveGroup.getPosition(0));

        // making a turn
        for (Move move : moveGroup) {
            if (!board.hasFigure(move.getStartPosition())) {
                throw new EmptyCellException("There is no figure on the start position of the move.",
                        move.getStartPosition());
            }

            figure.getMoveChecker().check(move, board);
            if (ENEMY_CHECKER.getEnemyCount(move, board) != 1) {
                throw new MoveGroupException("You can only capture in move group.");
            }
            FIGURE_UPDATER.update(move, board);
            PRAY_EXPROPRIATOR.update(move, board);
            FIGURE_MOVER.update(move, board);
        }

        CommonMoveChecker.alertCaptureMoves(moveGroup.getPosition(moveGroup.getPositionsAmount() - 1), board);

        // removing enemy's figures
        for (Move move : moveGroup) {
            PRAY_REMOVER.update(move, board);
        }
    }
}
