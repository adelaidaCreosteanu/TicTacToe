package test;

import game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GameControllerTest {
    private GameController gameC;

    @BeforeEach
    void setUp() {
        Board board = new Board(5);
        BoardPrinter printer = new BoardPrinter();
        this.gameC = new GameController(board, printer);
    }

    @Test
    @DisplayName("parsePlayerMove correct")
    void parsePlayerMoveCorrect() {
        HashMap<String, Position> tests = new HashMap<>();
        tests.put("2, 3", new Position(1, 2));
        tests.put("1,1", new Position(0, 0));
        tests.put(" 4 , 3 ", new Position(3, 2));
        tests.put("1,5,", new Position(0, 4));

        try {
            // call parsePlayerMove() through reflection, because the method is private
            Method parsePlayerMove = gameC.getClass().getDeclaredMethod("parsePlayerMove", String.class);
            parsePlayerMove.setAccessible(true);

            for (String input : tests.keySet()) {
                System.out.println("Input is:" + input);
                Position expected = tests.get(input);
                Position actual = (Position) parsePlayerMove.invoke(gameC, input);
                assertEquals(expected, actual, "Parsed position incorrectly!");
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}