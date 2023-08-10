package sources;

/**
 * Interface for game functionality.
 */

public interface Game {

    /**
     * Starts a game between two players.
     *
     * @param first  The first player.
     * @param second The second player.
     */

    void startGame(Player first, Player second);

}