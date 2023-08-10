package sources;

import java.util.*;

/**
 * Represents a user in the game, implementing the Settings, Authorization, and Player interfaces.
 */

public class User implements Settings, Authorization, Player {
    private final String DEFAULT_LANGUAGE = "ru";
    protected ResourceBundle bundle = ResourceBundle.getBundle("bundle.UsageResourceBundle_" + DEFAULT_LANGUAGE);

    protected String login;

    protected  String url = "jdbc:sqlite:src/sqlite_config/gameDataBase";

    protected final Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a User object based on another User object.
     *
     * @param other the other User object to copy the login and bundle from
     */
    public User(User other) {
        this.login = other.login;
        this.bundle = other.bundle;
    }

    /**
     * Default constructs a User object.
     */
    public User() {

    }

    /**
     * Retrieves the login of the user.
     *
     * @return the user's login
     */

    public String GetLogin() {
        return login;
    }



    @Override
    public boolean auth() {
        System.out.println(getBundle().getString("WriteLogin"));
        Scanner scanner = new Scanner(System.in);
        login = scanner.nextLine();
        return true;
    }

    private void chooseLanguage() {
        String SelectedOfUserLanguage = greetingOfTheGame();
        this.bundle = ResourceBundle.getBundle("bundle.UsageResourceBundle_" + SelectedOfUserLanguage);
    }

    private static String greetingOfTheGame() {
        System.out.println("Select language: \n");
        System.out.println("ru \uD83C\uDDF7\uD83C\uDDFA");
        System.out.println("en \uD83C\uDDEC\uD83C\uDDE7");
        System.out.println("cn \uD83C\uDDE8\uD83C\uDDF3");
        return checkingChooseLanguage();
    }

    private static String checkingChooseLanguage() {
        Scanner selectedLanguage = new Scanner(System.in);
        String language;
        while (true) {
            language = selectedLanguage.nextLine().toLowerCase();

            if (!Objects.equals(language, "ru") &&
                    !Objects.equals(language, "en") &&
                    !Objects.equals(language, "cn") &&
                    !Objects.equals(language, "es") &&
                    !Objects.equals(language, "ar")) {
                System.out.println("Incorrectly selected language or this language does not exist, try again: ");
                continue;
            }
            break;
        }

        return language;
    }

    @Override
    public void getGameStat() {
        /// подключение к бд и сохранение
    }


    @Override
    public void updateStat(int win) {

    }

    @Override
    public void getAllSettings() {
        System.out.println(bundle.getString("AvailablesSettings"));
        System.out.println(bundle.getString("ChangeLanguage"));
        System.out.println(bundle.getString("Back"));
        System.out.println(bundle.getString("Choose"));

        boolean isCorrectInput = false;
        while (!isCorrectInput) {
            String choice = scanner.next();
            scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.println(bundle.getString("ChooseChangeLanguage"));
                    chooseLanguage();
                    isCorrectInput = true;
                }
                case "2" -> {
                    System.out.println(bundle.getString("ChooseBack"));
                    isCorrectInput = true;
                }
                default -> System.out.println(bundle.getString("IncorrectChooseTryAgain"));
            }
        }
    }

    @Override
    public ResourceBundle getBundle() {
        return bundle;
    }

    @Override
    public List<String> makeMove(List<String> moves) {
        return null;
    }

    @Override
    public void getStatistics() {

    }
}
