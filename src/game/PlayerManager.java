package game;

import java.util.Random;

public class PlayerManager {
    private int[] players;
    private int turn;

    public PlayerManager() {
        players = new int[]{1, 2, 3};
        shufflePlayers();
        turn = 0;
    }

    public int getCurrentPlayer() {
        return players[turn];
    }

    public int getNextPlayer() {
        turn++;
        if (turn == players.length) turn = 0;

        return players[turn];
    }

    // Implementing Fisher–Yates shuffle
    private void shufflePlayers() {
        Random rnd = new Random();

        int lastPlayer = players.length - 1;
        for (int i = lastPlayer; i > 0; i--) {
            int pick = rnd.nextInt(i + 1); // up to and including i

            // swap
            int temp = players[pick];
            players[pick] = players[i];
            players[i] = temp;
        }
    }
}
