package game;

/**
 * Represents an AI player. Subclasses can have different play strategies.
 * All subclasses make moves based on the current state of the board.
 */
public abstract class AI {
    protected Board board;

    public AI(Board board) {
        this.board = board;
    }

    public abstract Position makeMove();
}
