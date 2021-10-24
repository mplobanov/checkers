package ru.mplobanov.updaters;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mplobanov.figure.Color;
import ru.mplobanov.figure.Figure;
import ru.mplobanov.figure.FigurePosition;
import ru.mplobanov.figure.FigurePositionException;

public class FigureTest {
    @Test
    void equalityWorks() throws FigurePositionException {
        Assertions.assertThat(new FigurePosition(0, 0).equals(new FigurePosition(0, 0))).isTrue();
    }

    @Test
    void cellColor() throws FigurePositionException {
        FigurePosition blackPosition = new FigurePosition(0, 0);
        Assertions.assertThat(blackPosition.getColor()).isEqualTo(Color.BLACK);

        FigurePosition whitePosition = new FigurePosition(1, 0);
        Assertions.assertThat(blackPosition.getColor()).isEqualTo(Color.BLACK);

        blackPosition = new FigurePosition(5, 3);
        Assertions.assertThat(blackPosition.getColor()).isEqualTo(Color.BLACK);
    }

    @Test
    void invertColorTest() {
        Figure figure = new Figure(Color.BLACK);
        figure.invertColor();

        Assertions.assertThat(figure.getColor()).isEqualTo(Color.WHITE);
    }

    @Test
    void setColorTest() {
        Figure figure = new Figure(Color.BLACK);

        figure.setColor(Color.WHITE);
        Assertions.assertThat(figure.getColor()).isEqualTo(Color.WHITE);

        figure.setColor(Color.BLACK);
        Assertions.assertThat(figure.getColor()).isEqualTo(Color.BLACK);
    }
}
