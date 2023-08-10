package rockPaperScissors;

import sources.Player;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The RobotPlayer class represents a player controlled by a computer program.
 */
public class RobotPlayer implements Player {

    /**
     * Generates a move for the robot player.
     *
     * @param moves The available moves for the game.
     * @return A list containing the selected move.
     */
    @Override
    public List<String> makeMove(List<String> moves) {
        Random random = new Random();
        int index = random.nextInt(moves.size());
        return Collections.singletonList(moves.get(index));
    }

    /**
     * Gets the statistics of the robot player.
     */
    @Override
    public void getStatistics() {

    }

    /**
     * Updates the statistics of the robot player based on the game result.
     *
     * @param win The result of the game (1 for a win, 0 for a draw, -1 for a loss).
     */

    @Override
    public void updateStat(int win) {

    }
}
