package sources;

import java.util.List;

/**
 * Interface for a player in a game.
 */

public interface Player {

    /**
     * Makes a move in the game based on the available moves.
     *
     * @param moves The available moves.
     * @return The chosen move.
     */

    List<String> makeMove(List<String> moves);

    /**
     * Retrieves the statistics of the player.
     */

    void getStatistics();

    /**
     * Updates the player's statistics based on the game result.
     *
     * @param win The result of the game (1 if the player won, 0 if it was a draw, -1 if the player lost).
     */

    void updateStat(int win);
}
