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
            Position move = parsePlayerMove(input);
            try {
                board.addMove(players[currentP], move);
                askForInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("Player " + players[currentP] + ", make another move!");
                // Will ask for input again
            }
        }
    }

    private Position parsePlayerMove(String input) {
        String[] numbers = input.split(",");
        int x = Integer.parseInt(numbers[0].trim());
        int y = Integer.parseInt(numbers[1].trim());

        // subtract 1 because the users use 1 indexing, but the program uses 0 indexing
        return new Position(x - 1, y - 1);
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
