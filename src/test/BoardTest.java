package test;

import game.Board;
import game.InvalidPlayerException;
import game.Position;
import game.PositionOutOfBoundsException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;


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

    @Test
    @DisplayName("Throw IllegalArgumentException")
    void throwIllegalArgumentException() {
        Board board = new Board(6);

        // Same Position object
        Position pos = new Position(5, 5);
        board.addMove(1, pos);
        Executable functionCall = () -> board.addMove(1, pos);
        assertThrows(IllegalArgumentException.class, functionCall, "Should throw IllegalArgumentException!");

        // Different object with same values
        board.addMove(2, new Position(2, 3));
        functionCall = () -> board.addMove(3, new Position(2, 3));
        assertThrows(IllegalArgumentException.class, functionCall, "Should throw IllegalArgumentException!");
    }

    @Test
    @DisplayName("Throw InvalidPlayerException")
    void throwInvalidPlayerException() {
        Board board = new Board(4);

        int[] tests = new int[]{-1, 0, 4, 30};

        for (int player : tests) {
            Executable functionCall = () -> board.addMove(player, new Position(0, 1));
            assertThrows(InvalidPlayerException.class, functionCall, "Should throw InvalidPlayerException!");

            functionCall = () -> board.hasWon(player);
            assertThrows(InvalidPlayerException.class, functionCall, "Should throw InvalidPlayerException!");
        }
    }

    @Test
    @DisplayName("hasWon true")
    void hasWonTrue() {
        Board board = new Board(3);

        int[][][] tests = new int[4][3][3];
        tests[0] = new int[][]{{1, 1, 1}, {2, 3, 0}, {0, 3, 2}};  // row
        tests[1] = new int[][]{{1, 2, 3}, {1, 3, 0}, {1, 0, 2}};  // column
        tests[2] = new int[][]{{1, 2, 3}, {2, 1, 0}, {3, 2, 1}};  // primary diagonal
        tests[3] = new int[][]{{2, 3, 1}, {3, 1, 2}, {1, 0, 0}};  // secondary diagonal

        for (int[][] grid : tests) {
            board.setGrid(grid);

            assertTrue(board.hasWon(1), "Should show win for Player 1!");
            System.out.println("tested");

            assertFalse(board.hasWon(2), "Should not show win for Player 2!");
            assertFalse(board.hasWon(3), "Should not show win for Player 3!");
        }
    }

    @Test
    @DisplayName("hasWon false")
    void hasWonFalse() {
        Board board = new Board(3);

        int[][][] tests = new int[3][][];
        tests[0] = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        tests[1] = new int[][]{{1, 2, 2}, {2, 2, 0}, {1, 0, 2}};
        tests[2] = new int[][]{{1, 2, 2}, {2, 1, 2}, {0, 2, 0}};

        for (int[][] grid : tests) {
            board.setGrid(grid);
            for (int i = 1; i < 4; i++) {
                assertFalse(board.hasWon(i), "Should not show win for Player " + i);
            }
        }
    }
}