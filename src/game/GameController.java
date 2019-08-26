package game;

import java.util.Scanner;

public class GameController {
    private Board board;
    private BoardPrinter printer;
    private int[] players;
    private Scanner scanner;

    public GameController(Board board, BoardPrinter printer) {
        this.board = board;
        this.printer = printer;
        scanner = new Scanner(System.in);

        players = new int[]{1, 2, 3};
    }

    public void play() {
        int currentP = 0;
        System.out.println("First to play is player " + players[currentP]);
        showBoard();

        while (!gameTie()) {
            System.out.println("Player " + players[currentP] + ", make your move!");
            addPlayerMove(currentP);
            showBoard();

            if (hasWon(currentP)) {
                System.out.println("Player " + players[currentP] + " has won!");
                break;
            }

            currentP++;
            if (currentP == players.length) currentP = 0;
        }

        System.out.println("Game over!");
    }

    private void addPlayerMove(int currentP) {
        boolean askForInput = true;

        while (askForInput) {
            String input = scanner.nextLine();
            try {
                Position move = Position.parse(input);
                board.addMove(players[currentP], move);
                askForInput = false;
            } catch (IllegalArgumentException | PositionOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println("Player " + players[currentP] + ", make a different move!");
                // Will ask for input again
            }
        }
    }

    private boolean hasWon(int currentP) {
        return board.hasWon(players[currentP]);
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
