package test;

import game.PlayerManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PlayerManagerTest {
    private PlayerManager playerManager;

    @BeforeEach
    void setUp() {
        playerManager = new PlayerManager();
        playerManager.setPlayers(new int[]{1, 2, 3});
    }

    @Test
    @DisplayName("Test currentPlayer()")
    void currentPlayer() {
        int expected = 1;
        int actual = playerManager.currentPlayer();
        assertEquals(expected, actual, "Wrong current player!");
    }

    @Test
    @DisplayName("Test nextPlayer()")
    void nextPlayer() {
        int expected = 2;
        int actual = playerManager.nextPlayer();
        assertEquals(expected, actual, "Wrong next player!");
    }

    @Test
    void wrapAround() {
        int[] expected = new int[]{1, 2, 3, 1};

        for (int i = 1; i < expected.length; i++) {
            int actual = playerManager.nextPlayer();
            assertEquals(expected[i], actual, "Wrong next player!");
        }
    }
}