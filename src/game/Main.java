package game;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        printWelcome();

        ConfigReader configReader = null;
        try {
            configReader = new ConfigReader("config.txt");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int boardSize = configReader.getBoardSize();
        Board board = new Board(boardSize);

        String[] symbols = configReader.getSymbols();
        BoardPrinter printer = new BoardPrinter(symbols);

        GameController game = new GameController(board, printer);
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
