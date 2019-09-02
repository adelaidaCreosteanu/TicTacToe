package game;

/**
 * Prints a grid, including the configured player symbols (given in the
 * constructor). It prints separating lines between cells of the grid.
 * The horizontal lines ("_") are replaced with player symbols.
 */
public class BoardPrinter {
    private Player[][] grid;
    private String[] symbols;

    public BoardPrinter(String[] symbols) {
        this.symbols = symbols;
    }

    public void print(Board board) {
        this.grid = board.getGrid();
        int size = board.getSize();

        int i, j;
        for (i = 0; i < size - 1; i++) {
            for (j = 0; j < size - 1; j++) {
                printRowCol(i, j);
            }
            printRowLastCol(i, j);
        }

        for (j = 0; j < size - 1; j++) {
            printLastRowCol(i, j);
        }
        printLastRowLastCol(i, j);
    }

    private void printRowCol(int i, int j) {
        if (grid[i][j] == null) {
            System.out.print("_|");
        } else {
            printSymbol(grid[i][j]);
            System.out.print("|");
        }
    }

    private void printRowLastCol(int i, int j) {
        if (grid[i][j] == null) {
            System.out.println("_");
        } else {
            printSymbol(grid[i][j]);
            System.out.println();
        }
    }

    private void printLastRowCol(int i, int j) {
        if (grid[i][j] == null) {
            System.out.print(" |");
        } else {
            printSymbol(grid[i][j]);
            System.out.print("|");
        }
    }

    private void printLastRowLastCol(int i, int j) {
        if (grid[i][j] == null) {
            System.out.println(" ");
        } else {
            printSymbol(grid[i][j]);
            System.out.println();
        }
    }

    private void printSymbol(Player p) {
        switch (p) {
            case ONE:
                System.out.print(symbols[0]);
                break;
            case TWO:
                System.out.print(symbols[1]);
                break;
            case AI:
                System.out.print(symbols[2]);
                break;
        }
    }
}
