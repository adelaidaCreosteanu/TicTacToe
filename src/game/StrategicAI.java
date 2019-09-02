package game;

import java.util.Random;

/**
 * StrategicAI is a more advanced AI which makes different moves depending on
 * the board state.
 */
public class StrategicAI extends AI {
    private int size;
    private Player[][] grid;

    public StrategicAI(Board board) {
        super(board);
        size = board.getGrid().length;
    }

    @Override
    public Position makeMove() {
        grid = board.getGrid();
        Position move = makeWin();
        if (move == null) move = makeBlock();
        if (move == null) move = makeAnyMove();

        return move;
    }

    private Position makeWin() {
        Position move = fillRowOrColumn(Player.AI);
        if (move == null) move = fillDiagonal(Player.AI);
        return move;
    }

    private Position makeBlock() {
        Position move = fillRowOrColumn(Player.ONE);
        if (move == null) move = fillRowOrColumn(Player.TWO);
        if (move == null) move = fillDiagonal(Player.ONE);
        if (move == null) move = fillDiagonal(Player.TWO);
        return move;
    }

    private Position fillRowOrColumn(Player filler) {
        Position move = null;
        for (int i = 0; i < size && move == null; i++) {
            move = fillRow(filler, i);
            if (move == null) move = fillColumn(filler, i);
        }
        return move;
    }

    private Position fillRow(Player filler, int row) {
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

        // if can win on this row
        if (symbols == size - 1 && !otherPlayer) return move;
        return null;
    }

    private Position fillColumn(Player filler, int col) {
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

        // if can win on this column
        if (symbols == size - 1 && !otherPlayer) return move;
        return null;
    }

    private Position fillDiagonal(Player filler) {
        Position move = fillPrimaryDiagonal(filler);
        if (move == null) move = fillSecondaryDiagonal(filler);

        return move;
    }

    private Position fillPrimaryDiagonal(Player filler) {
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

        // if can win on this diagonal
        if (symbols == size - 1 && !otherPlayer) return move;
        return null;
    }

    private Position fillSecondaryDiagonal(Player filler) {
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

        // if can win on this diagonal
        if (symbols == size - 1 && !otherPlayer) return move;
        return null;
    }

    private Position makeAnyMove() {
        Position move = null;
        if (centerIsEmpty()) {
            move = new Position(size / 2, size / 2);
        } else {
            move = randomMove();
        }
        return move;
    }

    private boolean centerIsEmpty() {
        return size % 2 == 1 && grid[size/2][size/2] == null;
    }

    private Position randomMove() {
        Random rnd = new Random();
        Position move = null;

        while (move == null) {
            // pick a random position
            int x = rnd.nextInt(size);
            int y = rnd.nextInt(size);

            // if it's empty, make this move
            if (grid[x][y] == null) move = new Position(x, y);
        }

        return move;
    }
}
