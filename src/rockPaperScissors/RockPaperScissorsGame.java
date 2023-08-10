package rockPaperScissors;

import sources.Game;
import sources.Player;
import sources.Settings;

import java.io.IOException;
import java.util.*;

/**
 * The RockPaperScissorsGame class represents the game logic for Rock-Paper-Scissors.
 */

public class RockPaperScissorsGame implements Game, Settings {
    private final ResourceBundle bundle;
    private final GameRules rules;

    /**
     * Constructs a RockPaperScissorsGame object with the provided global resource bundle.
     *
     * @param globalBundle The global resource bundle for the game.
     * @throws IOException If an I/O error occurs while initializing the game.
     */

    public RockPaperScissorsGame(ResourceBundle globalBundle) throws IOException {
        this.bundle = globalBundle;
        rules = new RulesManager();
    }

    /**
     * Starts the game between two players.
     *
     * @param first  The first player.
     * @param second The second player.
     */
    @Override
    public void startGame(Player first, Player second) {
        getAllSettings();
        List<String> firstMove = first.makeMove(List.of(rules.getShapesForTheGame()));
        List<String> secondMove = second.makeMove(List.of(rules.getShapesForTheGame()));
        System.out.println("Первый игрок сделал ход: " + firstMove.get(0));
        System.out.println("Второй игрок сделал ход: " + secondMove.get(0));
        first.updateStat(rules.getResultGame(firstMove, secondMove));
        second.updateStat(rules.getResultGame(secondMove, firstMove));
    }

    /**
     * Gets the game statistics.
     */

    @Override
    public void getGameStat() {

    }

    /**
     * Displays and manages all game settings.
     */
    @Override
    public void getAllSettings() {
        System.out.println(bundle.getString("CurrentConfigIs") + rules.getCurrentConfig());
        Scanner scanner = new Scanner(System.in);

        boolean isCorrectInput = false;
        while (!isCorrectInput) {
            System.out.println(bundle.getString("ChengConfig"));
            System.out.println(bundle.getString("Continue"));
            System.out.print(bundle.getString("Choose"));

            String choice = scanner.next();
            scanner.nextLine();
            switch (choice) {
                case "1" -> {
                    System.out.println(bundle.getString("ChengConfig"));
                    int quantityOfConfigs = rules.getAllConfigs();
                    int numberOfConfig = -1;
                    while (numberOfConfig < 1 || numberOfConfig > quantityOfConfigs) {
                        System.out.println(bundle.getString("WriteQuantity") + quantityOfConfigs);
                        try {
                            numberOfConfig = scanner.nextInt();
                        } catch (NumberFormatException | InputMismatchException e) {
                            System.out.println(bundle.getString("NotCorrectFormat"));
                            break;
                        }
                    }
                    try {
                        rules.chooseConfig(numberOfConfig);
                    } catch (IOException | NullPointerException | InputMismatchException e) {
                        System.out.println(bundle.getString("NotCorrectFormat"));
                    }
                }
                case "2" -> {
                    System.out.println(bundle.getString("Continue"));
                    isCorrectInput = true;
                }
                default -> System.out.println(bundle.getString("IncorrectChoose"));
            }
        }
    }

    /**
     * Returns the resource bundle for the game.
     *
     * @return The resource bundle.
     */

    @Override
    public ResourceBundle getBundle() {
        return bundle;
    }

}
