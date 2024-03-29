package game;

import java.io.IOException;
import java.text.ParseException;

/**
 * This class contains the main function. After printing a welcome message,
 * it creates all necessary classes and starts the game.
 */
public class Main {
    public static void main(String[] args) {
        printWelcome();

        ConfigReader configs = new ConfigReader();
        try {
            configs.readFile("config.txt");
        } catch (ParseException | IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        int boardSize = configs.getBoardSize();
        Board board = new Board(boardSize);

        String[] symbols = configs.getSymbols();
        BoardPrinter printer = new BoardPrinter(symbols);

        AI ai = null;
        if (configs.getDifficulty() == AIDifficulty.EASY)
            ai = new RandomAI(board);
        else ai = new StrategicAI(board);

        GameController game = new GameController(board, printer, ai);
        game.play();
    }

    private static void printWelcome() {
        System.out.println(" _________  _           _________                _________                 _____       ____    ");
        System.out.println("|  _   _  |(_)         |  _   _  |              |  _   _  |               / ___ `.   .'    '.  ");
        System.out.println("|_/ | | \\_|__   .---.  |_/ | | \\_|,--.   .---.  |_/ | | \\_|.--.   .---.  |_/___) |  |  .--.  | ");
        System.out.println("    | |   [  | / /'`\\]     | |   `'_\\ : / /'`\\]     | |  / .'`\\ \\/ /__\\\\  .'____.'  | |    | | ");
        System.out.println("   _| |_   | | | \\__.     _| |_  // | |,| \\__.     _| |_ | \\__. || \\__., / /_____  _|  `--'  | ");
        System.out.println("  |_____| [___]'.___.'   |_____| \\'-;__/'.___.'   |_____| '.__.'  '.__.' |_______|(_)'.____.'  \n");
        System.out.println("How to play:\n" + "The three players take turns making moves on the grid.\n" +
                "You can win by filling in a whole row, column or diagonal.\n\n" +
                "Please choose an empty position in the format: [row number], [column number]\n" +
                "Numbering starts with 1 from the top left corner. So to make a move in the top left corner,\n" +
                "you would write: 1,1\n");
    }
}
