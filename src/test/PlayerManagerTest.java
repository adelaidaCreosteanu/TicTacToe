package test;

import game.Player;
import game.PlayerManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests that PlayerManager returns the correct Player with currentPlayer()
 * and nextPlayer(), including wrapping around and reaching the first Player
 * again.
 */
class PlayerManagerTest {
    private PlayerManager playerManager;

    @BeforeEach
    void setUp() {
        playerManager = new PlayerManager();
        playerManager.setPlayers(new Player[]{Player.ONE, Player.TWO, Player.AI});
    }

    @Test
    @DisplayName("Test currentPlayer()")
    void currentPlayer() {
        Player expected = Player.ONE;
        Player actual = playerManager.currentPlayer();
        assertEquals(expected, actual, "Wrong current player!");
    }

    @Test
    @DisplayName("Test nextPlayer()")
    void nextPlayer() {
        Player expected = Player.TWO;
        Player actual = playerManager.nextPlayer();
        assertEquals(expected, actual, "Wrong next player!");
    }

    @Test
    void wrapAround() {
        Player[] expected = new Player[]{Player.ONE, Player.TWO, Player.AI, Player.ONE};

        for (int i = 1; i < expected.length; i++) {
            Player actual = playerManager.nextPlayer();
            assertEquals(expected[i], actual, "Wrong next player!");
        }
    }
}