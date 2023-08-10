package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import rockPaperScissors.RobotPlayer;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RobotPlayerTest {

    private RobotPlayer robotPlayer;

    @Before
    public void setUp() {
        robotPlayer = new RobotPlayer();
    }

    @Test
    public void makeMove_ValidMoves_ReturnsSingleMove() {
        List<String> moves = Arrays.asList("Rock", "Paper", "Scissors");

        List<String> move = robotPlayer.makeMove(moves);

        assertEquals(1, move.size());
        assertTrue(moves.contains(move.get(0)));
    }

    @Test
    public void getStatistics_NoImplementation_DoesNotThrowException() {
        robotPlayer.getStatistics();
    }

    @Test
    public void updateStat_NoImplementation_DoesNotThrowException() {
        robotPlayer.updateStat(1);
        robotPlayer.updateStat(0);
        robotPlayer.updateStat(-1);
    }
}
