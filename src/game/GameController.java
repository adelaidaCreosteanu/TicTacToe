package game;

import java.util.Scanner;

public class GameController {
    private Board board;
    private BoardPrinter printer;
    private PlayerManager playerManager;
    private Scanner scanner;

    public GameController(Board board, BoardPrinter printer) {
        this.board = board;
        this.printer = printer;
        scanner = new Scanner(System.in);

        playerManager = new PlayerManager();
    }

    public void play() {
        Player player = playerManager.currentPlayer();
        System.out.println("First to play is player " + player);
        showBoard();

        while (!gameTie()) {
            System.out.println("Player " + player + ", make your move!");
            addPlayerMove(player);
            showBoard();

            if (hasWon(player)) {
                System.out.println("Player " + player + " has won!");
                break;
            }

            player = playerManager.nextPlayer();
        }

        System.out.println("Game over!");
    }

    private void addPlayerMove(Player player) {
        boolean askForInput = true;

        while (askForInput) {
            String input = scanner.nextLine();
            try {
                Position move = Position.parse(input);
                board.addMove(player, move);
                askForInput = false;
            } catch (IllegalArgumentException | PositionOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println("Player " + player + ", make a different move!");
                // Will ask for input again
            }
        }
    }

    private boolean hasWon(Player player) {
        return board.hasWon(player);
    }

    private boolean gameTie() {
        return board.isFull();
    }

    private void showBoard() {
        printer.print(board.getGrid());
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
