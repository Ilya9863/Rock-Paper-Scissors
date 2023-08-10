package Tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import rockPaperScissors.RulesManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RulesManagerTest {

    private RulesManager rulesManager;

    @Before
    public void setUp() throws IOException {
        rulesManager = new RulesManager();
    }

    @Test
    public void parseDependencies_ValidConfigFile_Success() throws IOException {
        int[][] expectedDependencies = {
                {0, 1, -1},
                {-1, 0, 1},
                {1, -1, 0}
        };

        int[][] dependencies = rulesManager.parseDependencies("src/config/defaultConfig.txt");

        assertArrayEquals(expectedDependencies, dependencies);
    }

    @Test(expected = IOException.class)
    public void parseDependencies_InvalidConfigFile_ThrowsIOException() throws IOException {
        rulesManager.parseDependencies("src/test/resources/config/invalidConfig.txt");
    }

    @Test
    public void getResultGame_Player1Wins_Returns1() {
        List<String> firstMove = Arrays.asList("камень");
        List<String> secondMove = Arrays.asList("ножницы");

        int result = rulesManager.getResultGame(firstMove, secondMove);

        assertEquals(1, result);
    }

    @Test
    public void getResultGame_Player2Wins_ReturnsNegative1() {
        List<String> firstMove = Arrays.asList("Paper");
        List<String> secondMove = Arrays.asList("Scissors");

        int result = rulesManager.getResultGame(firstMove, secondMove);

        assertEquals(0, result);
    }

    @Test
    public void getResultGame_Tie_Returns0() {
        List<String> firstMove = Arrays.asList("Rock");
        List<String> secondMove = Arrays.asList("Rock");

        int result = rulesManager.getResultGame(firstMove, secondMove);

        assertEquals(0, result);
    }

    @Test
    public void getCurrentConfig_DefaultConfig_ReturnsDefaultConfigPath() {
        String expectedConfigPath = "src/config/defaultConfig.txt";

        String currentConfig = rulesManager.getCurrentConfig();

        assertEquals(expectedConfigPath, currentConfig);
    }

    @Test
    public void getAllConfigs_MultipleConfigs_ReturnsTotalNumberOfConfigs() {
        int expectedTotalConfigs = 2;

        int totalConfigs = rulesManager.getAllConfigs();

        assertEquals(expectedTotalConfigs, totalConfigs);
    }

}
