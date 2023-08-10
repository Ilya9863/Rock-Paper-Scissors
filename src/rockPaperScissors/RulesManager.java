package rockPaperScissors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The RulesManager class implements the GameRules interface and manages the game rules and configurations for Rock-Paper-Scissors.
 */
public class RulesManager implements GameRules {

    private final String DEFAULT_CONFIG = "src/config/defaultConfig.txt";
    private String currentConfig = "src/config/defaultConfig.txt";

    private int[][] dependencies;
    private String[] shapesForTheGame;

    private List<String> allConfigs;

    private final String PATH_CONFIG = "src/config/";

    /**
     * Constructs a RulesManager object and applies the default configuration.
     *
     * @throws IOException If an I/O error occurs while applying the default configuration.
     */

    public RulesManager() throws IOException {
        applyDefaultConfig();
    }

    /**
     * Fills list of all available configurations.
     */

    private void applyDefaultConfig() throws IOException {
        try {
            currentConfig = DEFAULT_CONFIG;
            shapesForTheGame = new String[3];
            dependencies = parseDependencies(DEFAULT_CONFIG);
            fillAllConfigs();
        } catch (IOException ex) {
            System.out.println("Нельзя изменять базовый конфиг!");
            throw new IOException();
        }
    }


    void fillAllConfigs() {
        allConfigs = new ArrayList<>();
        File directory = new File(PATH_CONFIG);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".txt")) {
                        allConfigs.add(file.getAbsolutePath());
                    }
                }
            }
        }
    }

    private static String[] firstLineOfConfig(BufferedReader reader) throws IOException {
        String firstLine = reader.readLine();
        return firstLine.split(", ");
    }

    /**
     * Parses the dependencies from the given configuration file.
     *
     * @param filePath The path to the configuration file.
     * @return The parsed dependencies matrix.
     * @throws IOException If an I/O error occurs while parsing the dependencies.
     */


    public int[][] parseDependencies(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            shapesForTheGame = firstLineOfConfig(reader);
            int n = shapesForTheGame.length;
            dependencies = new int[n][n];
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() > 3) {
                    String[] dependency = line.split(" > ");
                    try {
                        int i = indexOf(shapesForTheGame, dependency[0]);
                        int j = indexOf(shapesForTheGame, dependency[1]);

                        // Проверка на недопустимые правила типа А > А
                        if (i == j || dependencies[i][j] == -1 || dependencies[j][i] == 1) {
                            throw new IllegalArgumentException("Invalid rule: " + line);
                        }

                        dependencies[i][j] = 1;
                        dependencies[j][i] = -1;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        throw new IllegalArgumentException();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Incorrect Data in config: ");
                        System.out.println("Use this format: ");
                        System.out.println("A, B, C");
                        System.out.println("A > B");
                        System.out.println("B > C");
                        System.out.println("C > A");
                        throw new IndexOutOfBoundsException();
                    } catch (NullPointerException e) {
                        System.out.println();
                        throw new NullPointerException();
                    }
                }
            }
            return dependencies;
        }
    }

    private static int indexOf(String[] array, String value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Calculates the result of the game based on the moves of the first and second players.
     *
     * @param firstMove  The move of the first player.
     * @param secondMove The move of the second player.
     * @return The result of the game: 1 if the first player wins, -1 if the second player wins, 0 if it's a tie.
     */

    @Override
    public int getResultGame(List<String> firstMove, List<String> secondMove) {
        int idxFirs = 0, idxSecond = 0;
        for (int i = 0; i < shapesForTheGame.length; i++) {
            if (shapesForTheGame[i].equals(firstMove.get(0))) {
                idxFirs = i;
            }
            if (shapesForTheGame[i].equals(secondMove.get(0))) {
                idxSecond = i;
            }
        }
        return dependencies[idxFirs][idxSecond];
    }

    /**
     * Returns the path to the current configuration file.
     *
     * @return The path to the current configuration file.
     */

    @Override
    public String getCurrentConfig() {
        return currentConfig;
    }


    /**
     * Displays the list of all available configurations and returns the total number of configurations.
     *
     * @return The total number of configurations.
     */
    @Override
    public int getAllConfigs() {
        fillAllConfigs();
        String[] configName;
        for (int i = 1; i < allConfigs.size() + 1; i++) {
            configName = allConfigs.get(i - 1).split("/");
            System.out.println(i + " - " + configName[configName.length - 1]);
        }
        return allConfigs.size();
    }

    /**
     * Chooses the configuration based on the user's selection.
     *
     * @param choose The user's selection.
     * @throws IOException If an I/O error occurs while choosing the configuration.
     */
    @Override
    public void chooseConfig(int choose) throws IOException {
        try {
            currentConfig = allConfigs.get(choose - 1);
            parseDependencies(currentConfig);
        } catch (IOException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException e) {
            applyDefaultConfig();
            System.out.println("Using standard Config");
        }
    }

    /**
     * Returns the array of shapes available for the game.
     *
     * @return The array of shapes available for the game.
     */
    @Override
    public String[] getShapesForTheGame() {
        return shapesForTheGame;
    }

}