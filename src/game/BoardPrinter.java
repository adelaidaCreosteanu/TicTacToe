package game;

public class BoardPrinter {
    private int[][] grid;

    public BoardPrinter() {
    }

    public void print(int [][] grid) {
        this.grid = grid;

        int i, j;
        for (i = 0; i < grid.length - 1; i++) {
            for (j = 0; j < grid.length - 1; j++) {
                printRowCol(i, j);
            }
            printRowLastCol(i, j);
        }

        for (j = 0; j < grid.length - 1; j++) {
            printLastRowCol(i, j);
        }
        printLastRowLastCol(i, j);
    }

    private void printRowCol(int i, int j) {
        if (grid[i][j] == 0) {
            System.out.print("_|");
        } else {
            System.out.print(grid[i][j] + "|");
        }
    }

    private void printRowLastCol(int i, int j) {
        if (grid[i][j] == 0) {
            System.out.println("_");
        } else {
            System.out.println(grid[i][j]);
        }
    }

    private void printLastRowCol(int i, int j) {
        if (grid[i][j] == 0) {
            System.out.print(" |");
        } else {
            System.out.print(grid[i][j] + "|");
        }
    }

    private void printLastRowLastCol(int i, int j) {
        if (grid[i][j] == 0) {
            System.out.println(" ");
        } else {
            System.out.println(grid[i][j]);
        }
    }
}
