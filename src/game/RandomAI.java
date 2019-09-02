package game;

import java.util.Random;

/**
 * Represents an AI player that makes moves randomly. It extends the abstract
 * class AI.
 */
public class RandomAI extends AI {

    public RandomAI(Board board) {
        super(board);
    }

    @Override
    public Position makeMove() {
        Player[][] grid = board.getGrid();

        Random rnd = new Random();
        Position move = null;

        while (move == null) {
            // pick a random position
            int x = rnd.nextInt(grid.length);
            int y = rnd.nextInt(grid.length);

            // if it's empty, make this move
            if (grid[x][y] == null) move = new Position(x, y);
        }

        return move;
    }
}
