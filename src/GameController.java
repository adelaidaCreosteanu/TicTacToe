
public class GameController {
    Board board;

    public GameController() {
        board = new Board(3);
    }

    public void play () {
        System.out.println("Let's play!");
    }
}
