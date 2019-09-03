package test;

import game.Board;
import game.Player;
import game.Position;
import game.PositionFinder;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PositionFinderTest {

    @Test
    void fillRowOrColumn() {
        Board board = new Board(3);

        HashMap<Player[][], Position> tests = new HashMap<>(2);
        Player[][] testRow = new Player[][]{{Player.AI, Player.AI, null},
                {null, Player.TWO, null},
                {Player.ONE, null, Player.ONE}};
        tests.put(testRow, new Position(0,2));
        Player[][] testCol = new Player[][]{{Player.AI, Player.TWO, null},
                {null, Player.ONE, null},
                {Player.AI, null, Player.TWO}};
        tests.put(testCol, new Position(1,0));

        for (Player[][] grid : tests.keySet()) {
            board.setGrid(grid);
            PositionFinder finder = new PositionFinder(board);
            Position actual = finder.fillRowOrColumn();
            assertEquals(tests.get(grid), actual, "Wrong position found!");
        }
    }

    @Test
    void fillDiagonal() {
        Board board = new Board(3);

        HashMap<Player[][], Position> tests = new HashMap<>(2);
        Player[][] testPrimary = new Player[][]{{Player.AI, Player.ONE, null},
                {Player.TWO, null, null},
                {Player.TWO, null, Player.AI}};
        tests.put(testPrimary, new Position(1,1));
        Player[][] testSecondary = new Player[][]{{null, Player.TWO, Player.AI},
                {Player.ONE, Player.AI, null},
                {null, null, Player.TWO}};
        tests.put(testSecondary, new Position(2,0));

        for (Player[][] grid : tests.keySet()) {
            board.setGrid(grid);
            PositionFinder finder = new PositionFinder(board);
            Position actual = finder.fillDiagonal();
            assertEquals(tests.get(grid), actual, "Wrong position found!");
        }
    }

    @Test
    void blockRowOrColumn() {
        Board board = new Board(3);

        HashMap<Player[][], Position> tests = new HashMap<>(2);
        Player[][] testRow = new Player[][]{{Player.TWO, Player.TWO, null},
                {Player.ONE, null, null},
                {Player.ONE, null, Player.AI}};
        tests.put(testRow, new Position(0,2));
        Player[][] testCol = new Player[][]{{Player.ONE, Player.TWO, null},
                {null, Player.AI, null},
                {Player.ONE, null, Player.TWO}};
        tests.put(testCol, new Position(1,0));

        for (Player[][] grid : tests.keySet()) {
            board.setGrid(grid);
            PositionFinder finder = new PositionFinder(board);
            Position actual = finder.blockRowOrColumn();
            assertEquals(tests.get(grid), actual, "Wrong position found!");
        }
    }

    @Test
    void blockDiagonal() {
        Board board = new Board(3);

        HashMap<Player[][], Position> tests = new HashMap<>(2);
        Player[][] testPrimary = new Player[][]{{Player.ONE, Player.AI, null},
                {Player.TWO, null, null},
                {Player.TWO, null, Player.ONE}};
        tests.put(testPrimary, new Position(1,1));
        Player[][] testSecondary = new Player[][]{{null, Player.AI, Player.TWO},
                {Player.ONE, Player.TWO, null},
                {null, null, Player.AI}};
        tests.put(testSecondary, new Position(2,0));

        for (Player[][] grid : tests.keySet()) {
            board.setGrid(grid);
            PositionFinder finder = new PositionFinder(board);
            Position actual = finder.blockDiagonal();
            assertEquals(tests.get(grid), actual, "Wrong position found!");
        }
    }
}