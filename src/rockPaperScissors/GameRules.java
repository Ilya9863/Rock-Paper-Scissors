package rockPaperScissors;

import java.io.IOException;
import java.util.List;

/**
 * The GameRules interface defines the rules and configurations of a game.
 */
public interface GameRules {
    /**
     * Gets the result of the game based on the moves of the two players.
     *
     * @param firstMove  The list of moves made by the first player.
     * @param secondMove The list of moves made by the second player.
     * @return The result of the game (-1 for player 1 wins, 0 for a draw, 1 for player 2 wins).
     */
    int getResultGame(List<String> firstMove, List<String> secondMove);

    /**
     * Gets the current configuration of the game.
     *
     * @return The current configuration of the game.
     */
    String getCurrentConfig();


    /**
     * Gets the total number of available configurations for the game.
     *
     * @return The total number of available configurations for the game.
     */
    int getAllConfigs();

    /**
     * Chooses a specific configuration for the game.
     *
     * @param choose The index of the chosen configuration.
     * @throws IOException If an I/O error occurs while choosing the configuration.
     */

    void chooseConfig(int choose) throws IOException;

    /**
     * Gets the shapes available for the game.
     *
     * @return An array of strings representing the available shapes for the game.
     */

    String[] getShapesForTheGame();


}
