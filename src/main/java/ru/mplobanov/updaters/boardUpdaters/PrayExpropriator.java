package ru.mplobanov.updaters.boardUpdaters;

import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.game.board.Board;
import ru.mplobanov.game.exceptions.CellException;
import ru.mplobanov.updaters.moveUpdaters.move.Move;


// does NOT rely on the moving figure place
public class PrayExpropriator implements BoardUpdater {
    @Override
    public void update(Move move, Board board) throws CellException {
        for (FigurePosition position : move) {
            if (position.equals(move.getStartPosition()) || position.equals(move.getEndPosition())) {
                continue;
            }
            if (board.hasFigure(position)) {
                // грязный хак: чтобы во время посследовательного взятия отдельно не рассматривать
                // уже побитые шашки противника (см. "турецкий удар"), красим их своими
                board.getFigure(position).invertColor();
            }
        }
    }
}
