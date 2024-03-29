package game;

import java.util.Scanner;

/**
 * GameController holds the main logic of the game. It pulls all the pieces
 * together to create the gameplay. GameController gets input from human players
 * and AI, creates Positions and gives them to the Board. It calls the
 * BoardPrinter at relevant points and check the game-end conditions.
 */
public class GameController {
    private Board board;
    private BoardPrinter printer;
    private AI ai;
    private PlayerManager playerManager;
    private Scanner scanner;

    /**
     * The constructor adds its arguments as private attributes and creates
     * a Scanner and PlayerManager.
     *
     * @param board   the Board this game will be played on
     * @param printer the BoardPrinter to print the playing field
     * @param ai      the AI player
     */
    public GameController(Board board, BoardPrinter printer, AI ai) {
        this.board = board;
        this.printer = printer;
        this.ai = ai;

        scanner = new Scanner(System.in);
        playerManager = new PlayerManager();
    }

    /**
     * Calling this function starts the game. It checks for game-end conditions
     * and adds moves to the Board.
     */
    public void play() {
        Player player = playerManager.currentPlayer();
        System.out.println("First to play is player " + player);
        showBoard();

        while (!gameTie()) {
            addMove(player);
            showBoard();

            if (hasWon(player)) {
                System.out.println("Player " + player + " has won!");
                break;
            }

            player = playerManager.nextPlayer();
        }

        System.out.println("Game over!");
    }

    private boolean gameTie() {
        return board.isFull();
    }

    private void addMove(Player player) {
        Position move = null;
        if (player == Player.AI) {
            System.out.println("Player AI made the move:");
            move = ai.makeMove();
            board.addMove(player, move);
        } else {
            System.out.println("Player " + player + ", make your move!");
            addMoveHumanPlayer(player);
        }
    }

    private void addMoveHumanPlayer(Player player) {
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

    private void showBoard() {
        printer.print(board);
    }

    private boolean hasWon(Player player) {
        return board.hasWon(player);
    }
}
