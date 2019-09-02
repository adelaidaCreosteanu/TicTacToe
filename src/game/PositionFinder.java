package game;

/**
 * This class is used by StrategicAI to find strategic positions.
 */
public class PositionFinder {
    private Player[][] grid;
    private int size;

    public PositionFinder(Board board) {
        this.grid = board.getGrid();
        this.size = board.getSize();
    }

    public Position fillRowOrColumn() {
        return completeRowOrColumn(Player.AI);
    }

    public Position fillDiagonal() {
        return completeDiagonal(Player.AI);
    }

    public Position blockRowOrColumn() {
        Position move = completeRowOrColumn(Player.ONE);
        if (move == null) move = completeRowOrColumn(Player.TWO);
        return move;
    }

    public Position blockDiagonal() {
        Position move = completeDiagonal(Player.ONE);
        if (move == null) move = completeDiagonal(Player.TWO);
        return move;
    }

    private Position completeRowOrColumn(Player filler) {
        Position move = null;
        for (int i = 0; i < size && move == null; i++) {
            move = completeRow(filler, i);
            if (move == null) move = completeColumn(filler, i);
        }
        return move;
    }

    private Position completeRow(Player filler, int row) {
        int symbols = 0;
        boolean otherPlayer = false;
        Position move = null;

        for (int j = 0; j < size; j++) {
            if (grid[row][j] == filler) {
                symbols++;
            } else if (grid[row][j] != null) { // Another player on this row
                otherPlayer = true;
            } else {
                move = new Position(row, j);
            }
        }

        if (symbols == size - 1 && !otherPlayer) return move;
        return null;
    }

    private Position completeColumn(Player filler, int col) {
        int symbols = 0;
        boolean otherPlayer = false;
        Position move = null;

        for (int i = 0; i < size; i++) {
            if (grid[i][col] == filler) {
                symbols++;
            } else if (grid[i][col] != null) { // Another player on this row
                otherPlayer = true;
            } else {
                move = new Position(i, col);
            }
        }

        if (symbols == size - 1 && !otherPlayer) return move;
        return null;
    }

    private Position completeDiagonal(Player filler) {
        Position move = completePrimaryDiagonal(filler);
        if (move == null) move = completeSecondaryDiagonal(filler);

        return move;
    }

    private Position completePrimaryDiagonal(Player filler) {
        int symbols = 0;
        boolean otherPlayer = false;
        Position move = null;

        for (int i = 0; i < size; i++) {
            if (grid[i][i] == filler) {
                symbols++;
            } else if (grid[i][i] != null) {
                otherPlayer = true;
            } else {
                move = new Position(i, i);
            }
        }

        if (symbols == size - 1 && !otherPlayer) return move;
        return null;
    }

    private Position completeSecondaryDiagonal(Player filler) {
        int symbols = 0;
        boolean otherPlayer = false;
        Position move = null;

        for (int i = 0; i < size; i++) {
            int j = size - i - 1;
            if (grid[i][j] == filler) {
                symbols++;
            } else if (grid[i][j] != null) {
                otherPlayer = true;
            } else {
                move = new Position(i, j);
            }
        }

        if (symbols == size - 1 && !otherPlayer) return move;
        return null;
    }
}
