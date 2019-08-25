package game;

import java.util.Objects;

public class Position {
    private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Position parse(String input) {
        String[] numbers = input.split(",");

        int x = 0, y = 0;
        try {
            x = Integer.parseInt(numbers[0].trim());
            y = Integer.parseInt(numbers[1].trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Please choose a position in the format: [x coordinate], [y coordinate]");
        }

        // subtract 1 because the users use 1 indexing, but the program uses 0 indexing
        return new Position(x - 1, y - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
