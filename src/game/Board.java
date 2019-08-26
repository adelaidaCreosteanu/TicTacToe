package game;

public class Board {
    private int[][] grid; // 0 means empty; 1,2,3 are the three players
    private int size;

    public Board(int gridSize) {
        if (gridSize < 3 || gridSize > 10)
            throw new IllegalArgumentException("Playfield size has to be between 3 and 10");

        this.size = gridSize;
        this.grid = new int[gridSize][gridSize];
    }

    public int[][] getGrid() {
        return grid;
    }

    public boolean isFull() {
        boolean full = true;

        for (int i = 0; i < size && full; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == 0) {
                    full = false;
                    break;
                }
            }
        }

        return full;
    }

    public void addMove(int player, Position pos) {
        int x = pos.getX();
        int y = pos.getY();

        if (isOutOfBounds(x, y))
            throw new PositionOutOfBoundsException("Chosen position is out of playfield bounds!");
        if (grid[x][y] != 0)
            throw new IllegalArgumentException("Chosen position is already filled!");

        if (player < 1 || player > 3)
            throw new InvalidPlayerException("Player number should be between 1 and 3!");

        grid[pos.getX()][pos.getY()] = player;
    }

    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= size || y < 0 || y >= size;
    }

    public boolean hasWon(int player) {
        if (player < 1 || player > 3)
            throw new InvalidPlayerException("Player number should be between 1 and 3!");

        return rowOrColumnFilled(player) || diagonalFilled(player);
    }

    private boolean rowOrColumnFilled(int p) {
        for (int i = 0; i < size; i++) {
            if (checkRow(p, i)) return true;
            if (checkColumn(p, i)) return true;
        }
        return false;
    }

    private boolean checkRow(int p, int row) {
        for (int j = 0; j < size; j++) {
            if (grid[row][j] != p) return false;
        }
        return true;
    }

    private boolean checkColumn(int p, int col) {
        for (int i = 0; i < size; i++) {
            if (grid[i][col] != p) return false;
        }
        return true;
    }

    private boolean diagonalFilled(int p) {
        return checkPrimaryDiagonal(p) || checkSecondaryDiagonal(p);
    }

    private boolean checkPrimaryDiagonal(int p) {
        for (int i = 0; i < size; i++) {
            if (grid[i][i] != p) return false;
        }
        return true;
    }

    private boolean checkSecondaryDiagonal(int p) {
        for (int i = 0; i < size; i++) {
            int j = size - i - 1;
            if (grid[i][j] != p) return false;
        }
        return true;
    }
}
