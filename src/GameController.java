import java.util.Scanner;

public class GameController {
    private Board board;
    private static Printer printer = new Printer();
    private int[] players;

    public GameController() {
        board = new Board(3);

        players = new int[]{1, 2, 3};
    }

    public void play() {
        int currentP = 0;
        System.out.println("First to play is player: " + players[currentP]);
        showBoard();

        Scanner scanner = new Scanner(System.in);

        while (!gameEnd()) {
            System.out.println("Player " + players[currentP] + ", make your move!");
            String input = scanner.nextLine();
            Position move = parsePlayerMove(input);
            board.addMove(players[currentP], move);
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

    private Position parsePlayerMove(String input) {
        String[] numbers = input.split(",");
        int x = Integer.parseInt(numbers[0].trim());
        int y = Integer.parseInt(numbers[1].trim());

        // subtract 1 because the users use 1 indexing, but the program uses 0 indexing
        return new Position(x - 1, y - 1);
    }
}
