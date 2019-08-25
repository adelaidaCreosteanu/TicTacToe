package game;

public class Board {
    private int[][] grid; // 0 means empty; 1,2,3 are the three players

    public Board(int gridSize) {
        this.grid = new int[gridSize][gridSize];
    }

    public void addMove(int player, Position pos) {
        int x = pos.getX();
        int y = pos.getY();

        if (x >= grid.length || y >= grid.length)
            throw new IllegalArgumentException("Chosen position is out of playfield bounds!");
        if (grid[x][y] != 0)
            throw new IllegalArgumentException("Chosen position is already filled!");

        grid[pos.getX()][pos.getY()] = player;
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
}
