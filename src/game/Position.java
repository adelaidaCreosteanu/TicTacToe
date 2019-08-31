package game;

/**
 * Represents a pair of coordinates on the playing field. A Position can be
 * created through the constructor or through the static parse() function.
 */
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

    /**
     * Parses a Position from a String. It decrements each coordinate because
     * the program uses 0-indexing.
     *
     * @param input the String containing a Position
     * @return the Position
     * @throws IllegalArgumentException if the input is not in the correct format
     * @throws NumberFormatException if the input does not contain two numbers
     */
    public static Position parse(String input) {
        String[] numbers = input.split(",");

        if (numbers.length != 2)
            throw new IllegalArgumentException("Please choose a position in the format: [x coordinate], [y coordinate]");

        int x = 0, y = 0;
        try {
            x = Integer.parseInt(numbers[0].trim());
            y = Integer.parseInt(numbers[1].trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Please choose a position in the format: [x coordinate], [y coordinate]");
        }

        // subtract 1 because the users use 1-indexing, but the program uses 0-indexing
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
}
