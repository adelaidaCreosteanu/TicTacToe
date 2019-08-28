package game;

public class Board {
    private Player[][] grid;
    private int size;

    public Board(int gridSize) {
        if (gridSize < 3 || gridSize > 10)
            throw new IllegalArgumentException("Playfield size has to be between 3 and 10");

        this.size = gridSize;
        this.grid = new Player[gridSize][gridSize];
    }

    public Player[][] getGrid() {
        return grid;
    }

    // Only testing purpose
    public void setGrid(Player[][] newGrid) {
        if (newGrid.length != size) throw new IllegalArgumentException("Cannot set differently sized grid!");
        grid = newGrid;
    }

    public boolean isFull() {
        boolean full = true;

        for (int i = 0; i < size && full; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == null) {
                    full = false;
                    break;
                }
            }
        }

        return full;
    }

    public void addMove(Player player, Position pos) {
        int x = pos.getX();
        int y = pos.getY();

        if (isOutOfBounds(x, y))
            throw new PositionOutOfBoundsException("Chosen position is out of playfield bounds!");
        if (grid[x][y] != null)
            throw new IllegalArgumentException("Chosen position is already filled!");

        grid[pos.getX()][pos.getY()] = player;
    }

    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= size || y < 0 || y >= size;
    }

    public boolean hasWon(Player player) {
        return rowOrColumnFilled(player) || diagonalFilled(player);
    }

    private boolean rowOrColumnFilled(Player p) {
        for (int i = 0; i < size; i++) {
            if (checkRow(p, i)) return true;
            if (checkColumn(p, i)) return true;
        }
        return false;
    }

    private boolean checkRow(Player p, int row) {
        for (int j = 0; j < size; j++) {
            if (grid[row][j] != p) return false;
        }
        return true;
    }

    private boolean checkColumn(Player p, int col) {
        for (int i = 0; i < size; i++) {
            if (grid[i][col] != p) return false;
        }
        return true;
    }

    private boolean diagonalFilled(Player p) {
        return checkPrimaryDiagonal(p) || checkSecondaryDiagonal(p);
    }

    private boolean checkPrimaryDiagonal(Player p) {
        for (int i = 0; i < size; i++) {
            if (grid[i][i] != p) return false;
        }
        return true;
    }

    private boolean checkSecondaryDiagonal(Player p) {
        for (int i = 0; i < size; i++) {
            int j = size - i - 1;
            if (grid[i][j] != p) return false;
        }
        return true;
    }
}
