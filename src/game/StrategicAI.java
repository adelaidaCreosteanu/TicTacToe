package game;

import java.util.Random;

/**
 * StrategicAI is a more advanced AI which makes different moves depending on
 * the board state.
 */
public class StrategicAI extends AI {
    private Player[][] grid;
    private int size;
    private PositionFinder finder;

    public StrategicAI(Board board) {
        super(board);
        size = board.getSize();
    }

    @Override
    public Position makeMove() {
        grid = board.getGrid();
        finder = new PositionFinder(board);

        Position move = makeWin();
        if (move == null) move = makeBlock();
        if (move == null) move = makeAnyMove();

        return move;
    }

    private Position makeWin() {
        Position move = finder.fillRowOrColumn();
        if (move == null) move = finder.fillDiagonal();
        return move;
    }

    private Position makeBlock() {
        Position move = finder.blockRowOrColumn();
        if (move == null) move = finder.blockDiagonal();
        return move;
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
        return size % 2 == 1 && grid[size / 2][size / 2] == null;
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
