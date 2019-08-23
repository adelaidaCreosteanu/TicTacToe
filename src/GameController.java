
public class GameController {
    private Board board;
    private static Printer printer = new Printer();

    public GameController() {
        board = new Board(3);
    }

    public void play() {
        System.out.println("The current state of the playing field is:");
        showBoard();
        System.out.println("Player 1 made a move to 2, 2!");
        board.addMove(1, new Position(1, 1));
        showBoard();
        System.out.println("Player 2 made a move to 1, 2");
        board.addMove(2, new Position(0, 1));
        showBoard();
    }

    public void showBoard() {
        printer.print(board.getGrid());
    }
}
