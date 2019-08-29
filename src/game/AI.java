package game;

public abstract class AI {
    protected Board board;

    public AI(Board board) {
        this.board = board;
    }

    public abstract Position makeMove();
}
