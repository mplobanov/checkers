package ru.mplobanov.figure;

public enum Color {
    WHITE, BLACK;

    public static Color getOpposite(Color color) {
        return color.equals(BLACK) ? WHITE : BLACK;
    }
}
