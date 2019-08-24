package game;

public class Main {
    public static void main(String[] args) {
        printWelcome();
        Board board = new Board(3);
        BoardPrinter printer = new BoardPrinter();
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
        System.out.println("How to play:");
        System.out.println("You make a move by deciding on the coordinates of your next mark.");
        System.out.println("Please put the row number first, then a comma, then the column number.");
        System.out.println("Numbering starts from 1.\n");
    }
}
