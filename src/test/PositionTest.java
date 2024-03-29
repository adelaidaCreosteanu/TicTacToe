package test;

import game.Position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the static parse() function of Position. It tests that it parses
 * correctly, throws IllegalArgumentException if the input is not in the
 * correct format, and throws NumberFormatException if the input does not
 * contain 2 numbers.
 */
class PositionTest {

    @Test
    @DisplayName("Correct parsing")
    void correctParse() {
        HashMap<String, Position> tests = new HashMap<>();
        tests.put("2, 3", new Position(1, 2));
        tests.put("1,1", new Position(0, 0));
        tests.put(" 4 , 3 ", new Position(3, 2));
        tests.put("1,5,", new Position(0, 4));

        for (String input : tests.keySet()) {
            Position expected = tests.get(input);
            Position actual = Position.parse(input);
            assertEquals(expected, actual, "Parsed position incorrectly!");
        }
    }

    @Test
    @DisplayName("Throw IllegalArgumentException")
    void throwIllegalArgumentException() {
        String[] tests = new String[]{"", "h,e,l,l,o", "1234", "hey-you"};

        for (String input : tests) {
            Executable functionCall = () -> Position.parse(input);
            assertThrows(IllegalArgumentException.class, functionCall, "Should throw IllegalArgumentException!");
        }
    }

    @Test
    @DisplayName("Throw NumberFormatException")
    void throwNumberFormatException() {
        String[] tests = new String[]{"4, hey", "hello, bye", "hey, 2"};

        for (String input : tests) {
            Executable functionCall = () -> Position.parse(input);
            assertThrows(NumberFormatException.class, functionCall, "Should throw NumberFormatException!");
        }
    }
}