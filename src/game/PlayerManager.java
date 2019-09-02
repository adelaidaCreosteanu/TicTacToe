package game;

import java.util.Random;

/**
 * Keeps track of the order of players. PlayerManager first shuffles
 * the players, so that the order is random.
 */
public class PlayerManager {
    private Player[] players;
    private int turn;

    public PlayerManager() {
        players = Player.values();
        shufflePlayers();
        turn = 0;   // it's the first player's turn
    }

    /**
     * Returns the relevant player.
     *
     * @return the Player whose turn it is
     */
    public Player currentPlayer() {
        return players[turn];
    }

    /**
     * Increments the turn and returns the relevant player.
     *
     * @return the Player who is next
     */
    public Player nextPlayer() {
        turn++;
        if (turn == players.length) turn = 0;

        return players[turn];
    }

    // Only testing purpose
    public void setPlayers(Player[] players) {
        if (players.length != this.players.length)
            throw new IllegalArgumentException("Cannot set differently sized players array!");
        this.players = players;
    }

    // Implementing Fisher–Yates shuffle
    private void shufflePlayers() {
        Random rnd = new Random();

        int lastPlayer = players.length - 1;
        for (int i = lastPlayer; i > 0; i--) {
            int pick = rnd.nextInt(i + 1); // up to and including i

            // swap
            Player temp = players[pick];
            players[pick] = players[i];
            players[i] = temp;
        }
    }
}
