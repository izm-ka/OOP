package ru.nsu.izmailova.snakes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.izmailova.mechanic.Point;
import ru.nsu.izmailova.mechanic.Playfield;
import ru.nsu.izmailova.mechanic.snakes.SnakeLobby;

public class SnakeLobbyTests {
    private Playfield playfield;
    private SnakeLobby snakeLobby;

    /**
     * Check tail
     */
    @Test
    @SuppressWarnings("checkstyle:magicnumber")
    public void test1() {

        playfield = new Playfield(1000, 1000, true);
        init();

        snakeLobby.update();
        snakeLobby.update();

        assertEquals(snakeLobby.getUserSnake().getPoints().size(), 3);
    }

    /**
     * Check kill
     */
    @Test
    @SuppressWarnings("checkstyle:magicnumber")
    public void test2() {
        playfield = new Playfield(880, 1000, true);
        init();

        snakeLobby.update();
        snakeLobby.update();

        assertNotEquals(snakeLobby.getSnakes().get(1).getHead(), new Point(27, 26));
    }

    private void init() {
        snakeLobby = new SnakeLobby(playfield, 1, 1);
        snakeLobby.reset();

        snakeLobby.getUserSnake().setRight();

        snakeLobby.getUserSnake().extend();
        snakeLobby.getUserSnake().extend();
        snakeLobby.getUserSnake().extend();
        snakeLobby.getUserSnake().extend();
    }
}