package game;

public class Board {
    private int[][] grid; // 0 means empty; 1,2,3 are the three players

    public Board(int gridSize) {
        if (gridSize < 3 || gridSize > 10)
            throw new IllegalArgumentException("Playfield size has to be between 3 and 10");
        this.grid = new int[gridSize][gridSize];
    }

    public int[][] getGrid() {
        return grid;
    }

    public boolean isFull() {
        boolean full = true;

        for (int i = 0; i < grid.length && full; i ++) {
            for (int j = 0; j < grid.length && full; j ++) {
                if (grid[i][j] == 0)
                    full = false;
            }
        }

        return full;
    }

    public void addMove(int player, Position pos) {
        int x = pos.getX();
        int y = pos.getY();

        if (isOutOfBounds(x) || isOutOfBounds(y))
            throw new PositionOutOfBoundsException("Chosen position is out of playfield bounds!");
        if (grid[x][y] != 0)
            throw new IllegalArgumentException("Chosen position is already filled!");

        if (player < 1 || player > 3) {
            throw new InvalidPlayerException("Player number should be between 1 and 3!");
        }

        grid[pos.getX()][pos.getY()] = player;
    }

    private boolean isOutOfBounds (int x) {
        return x < 0 || x >= grid.length;
    }
}
