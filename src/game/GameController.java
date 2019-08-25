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

        while (!gameEnd()) {
            System.out.println("Player " + players[currentP] + ", make your move!");
            addPlayerMove(currentP);
            showBoard();

            currentP ++;
            if (currentP == players.length) currentP = 0;
        }

        System.out.println("Game has ended");
    }

    private boolean gameEnd() {
        return board.isFull();
    }

    private void showBoard() {
        printer.print(board.getGrid());
    }

    private void addPlayerMove(int currentP) {
        boolean askForInput = true;

        while (askForInput) {
            String input = scanner.nextLine();
            try {
                Position move = Position.parse(input);
                board.addMove(players[currentP], move);
                askForInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("Player " + players[currentP] + ", make a different move!");
                // Will ask for input again
            }
        }
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
