package test;

import game.Board;
import game.Position;
import game.PositionOutOfBoundsException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class BoardTest {

    @Test
    @DisplayName("Constructor")
    void constructor() {
        for (int i = 3; i < 11; i++) {
            Board b = new Board(i);
            assertEquals(i, b.getGrid().length, "Grid length should be equal to constructor argument!");
        }

        int[] tests = new int[]{-1, 0, 2, 11, 100};

        for (int input : tests) {
            Executable functionCall = () -> new Board(input);
            assertThrows(IllegalArgumentException.class, functionCall, "Should throw IllegalArgumentException " +
                    "if gridSize is not between 3 and 10!");
        }
    }

    @Test
    @DisplayName("Throw PositionOutOfBoundsException")
    void throwPositionOutOfBoundsException() {
        Board board = new Board(5);
        Position[] tests = new Position[]{new Position(-3, 2), new Position(9, 3), new Position(5, 5)};

        for (Position input : tests) {
            Executable functionCall = () -> board.addMove(1, input);
            assertThrows(PositionOutOfBoundsException.class, functionCall, "Should throw PositionOutOfBoundsException!");
        }
    }

}