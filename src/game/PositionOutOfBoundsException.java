package game;

/**
 * A wrapper to IndexOutOfBoundsException. It's an unchecked Exception.
 */
public class PositionOutOfBoundsException extends IndexOutOfBoundsException {
    public PositionOutOfBoundsException(String s) {
        super(s);
    }
}
