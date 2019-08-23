public class Board {
    private int[][] grid; // 0 means empty; 1,2,3 are the three players

    public Board(int gridSize) {
        this.grid = new int[gridSize][gridSize];
    }

    public void addMove(int player, Position pos) {
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
