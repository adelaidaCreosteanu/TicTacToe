package game;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * This class is used by StrategicAI to find strategic positions.
 */
public class PositionFinder {
    private Player[][] grid;
    private int size;

    /**
     * Constructor. A new PositionFinder should be created if the board is
     * updated.
     *
     * @param board
     */
    public PositionFinder(Board board) {
        this.grid = board.getGrid();
        this.size = board.getSize();
    }

    public Position fillRowOrColumn() {
        return completeRowOrColumn(p -> p == Player.AI);
    }

    public Position fillDiagonal() {
        return completeDiagonal(p -> p == Player.AI);
    }

    public Position blockRowOrColumn() {
        Position move = completeRowOrColumn(p -> p == Player.ONE);
        if (move == null) move = completeRowOrColumn(p -> p == Player.TWO);
        return move;
    }

    public Position blockDiagonal() {
        Position move = completeDiagonal(p -> p == Player.ONE);
        if (move == null) move = completeDiagonal(p -> p == Player.TWO);
        return move;
    }

    private Position completeRowOrColumn(Predicate<Player> onlyFiller) {
        Position move = null;
        for (int i = 0; i < size && move == null; i++) {
            move = completeRow(onlyFiller, i);
            if (move == null) move = completeColumn(onlyFiller, i);
        }
        return move;
    }

    private Position completeRow(Predicate<Player> onlyFiller, int row) {
        long count = filterAndCount(grid[row], onlyFiller);
        if (count == size - 1) {
            for (int j = 0; j < size; j++) {
                if (grid[row][j] == null) return new Position(row, j);
            }
        }
        return null;
    }

    private long filterAndCount(Player[] data, Predicate<Player> pred) {
        return Arrays.stream(data).filter(pred).count();
    }

    private Position completeColumn(Predicate<Player> onlyFiller, int col) {
        long count = filterAndCount(getColumn(col), onlyFiller);
        if (count == size - 1) {
            for (int i = 0; i < size; i++) {
                if (grid[i][col] == null) return new Position(i, col);
            }
        }
        return null;
    }

    private Player[] getColumn(int column) {
        Player[] columnData = new Player[size];
        for (int i = 0; i < size; i++) {
            columnData[i] = grid[i][column];
        }
        return columnData;
    }

    private Position completeDiagonal(Predicate<Player> onlyFiller) {
        Position move = completePrimaryDiagonal(onlyFiller);
        if (move == null) move = completeSecondaryDiagonal(onlyFiller);
        return move;
    }

    private Position completePrimaryDiagonal(Predicate<Player> onlyFiller) {
        long count = filterAndCount(getPrimaryDiagonal(), onlyFiller);
        if (count == size - 1) {
            for (int i = 0; i < size; i++) {
                if (grid[i][i] == null) return new Position(i, i);
            }
        }
        return null;
    }

    private Player[] getPrimaryDiagonal() {
        Player[] diagonal = new Player[size];
        for (int i = 0; i < size; i++) {
            diagonal[i] = grid[i][i];
        }
        return diagonal;
    }

    private Position completeSecondaryDiagonal(Predicate<Player> onlyFiller) {
        long count = filterAndCount(getSecondaryDiagonal(), onlyFiller);
        if (count == size - 1) {
            for (int i = 0; i < size; i++) {
                int j = size - i - 1;
                if (grid[i][j] == null) return new Position(i, j);
            }
        }
        return null;
    }

    private Player[] getSecondaryDiagonal() {
        Player[] diagonal = new Player[size];
        for (int i = 0; i < size; i++) {
            diagonal[i] = grid[i][size - i - 1];
        }
        return diagonal;
    }
}
