package test;

import game.GameController;
import game.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {
    private GameController gameC;

    @BeforeEach
    void setUp() {
        gameC = new GameController();
    }

    @Test
    void testParsePlayerMove () {
        HashMap<String, Position> testCases = new HashMap<>();
        testCases.put("2, 3", new Position(1, 2));
        testCases.put("1,1", new Position(0, 0));

        try {
            Method privateMethod = gameC.getClass().getDeclaredMethod("parsePlayerMove", String.class);
            privateMethod.setAccessible(true);
            Position returnValue = (Position) privateMethod.invoke(gameC, "2, 3");
            System.out.println("returnValue = " + returnValue.getX() + " " + returnValue.getY());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}