package test;

import game.*;

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
            Executable functionCall = () -> board.addMove(Player.ONE, input);
            assertThrows(PositionOutOfBoundsException.class, functionCall, "Adding a move to an out of bound " +
                    "position should throw PositionOutOfBoundsException!");
        }
    }

    @Test
    @DisplayName("Throw IllegalArgumentException")
    void throwIllegalArgumentException() {
        Board board = new Board(6);

        // Same Position object
        Position pos = new Position(5, 5);
        board.addMove(Player.ONE, pos);
        Executable functionCall = () -> board.addMove(Player.ONE, pos);
        assertThrows(IllegalArgumentException.class, functionCall, "Adding a move to an already filled position " +
                "should throw IllegalArgumentException!");

        // Different object with same values
        board.addMove(Player.TWO, new Position(2, 3));
        functionCall = () -> board.addMove(Player.AI, new Position(2, 3));
        assertThrows(IllegalArgumentException.class, functionCall, "Adding a move to an already filled position " +
                "should throw IllegalArgumentException!");
    }

    @Test
    @DisplayName("hasWon true")
    void hasWonTrue() {
        Board board = new Board(3);

        Player[][][] tests = new Player[4][][];
        tests[0] = new Player[][]{{Player.ONE, Player.ONE, Player.ONE}, // row
                                  {Player.TWO, Player.AI, null}, 
                                  {null, Player.AI, Player.TWO}};
        tests[1] = new Player[][]{{Player.ONE, Player.TWO, Player.AI},  // column
                                  {Player.ONE, Player.AI, null},
                                  {Player.ONE, null, Player.TWO}};
        tests[2] = new Player[][]{{Player.ONE, Player.TWO, Player.AI},  // primary diagonal
                                  {Player.TWO, Player.ONE, null},
                                  {Player.AI, Player.TWO, Player.ONE}};
        tests[3] = new Player[][]{{Player.TWO, Player.AI, Player.ONE},  // secondary diagonal
                                  {Player.AI, Player.ONE, Player.TWO},
                                  {Player.ONE, null, null}};

        for (Player[][] grid : tests) {
            board.setGrid(grid);

            assertTrue(board.hasWon(Player.ONE), "Should show win for Player 1!");

            assertFalse(board.hasWon(Player.TWO), "Should not show win for Player 2!");
            assertFalse(board.hasWon(Player.AI), "Should not show win for Player 3!");
        }
    }

    @Test
    @DisplayName("hasWon false")
    void hasWonFalse() {
        Board board = new Board(3);

        Player[][][] tests = new Player[3][][];
        tests[0] = new Player[][]{{null, null, null},
                                  {null, null, null},
                                  {null, null, null}};
        tests[1] = new Player[][]{{Player.ONE, Player.TWO, Player.AI},
                                  {Player.AI, Player.TWO, null},
                                  {Player.ONE, null, Player.TWO}};
        tests[2] = new Player[][]{{Player.ONE, Player.AI, Player.TWO},
                                  {Player.TWO, null, Player.TWO},
                                  {null, Player.TWO, null}};

        for (Player[][] grid : tests) {
            board.setGrid(grid);
            for (Player p : Player.values()) {
                assertFalse(board.hasWon(p), "Should not show win for Player " + p);
            }
        }
    }
}