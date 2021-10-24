package ru.mplobanov.figure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {
    @Test
    void getOpposite() {
        assertEquals(Color.getOpposite(Color.WHITE), Color.BLACK);
        assertEquals(Color.getOpposite(Color.BLACK), Color.WHITE);
    }
}