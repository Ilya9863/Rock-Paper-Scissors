package sources;

import java.sql.*;
import java.util.Collections;
import java.util.List;

/**
 * Represents a Rock-Paper-Scissors user. Extends the User class.
 */

public class RockPaperScissorsUser extends User {

    /**
     * Constructs a RockPaperScissorsUser object with the given super user.
     *
     * @param superUser the superuser for the RockPaperScissorsUser
     */

    public RockPaperScissorsUser(User superUser) {
        super(superUser);
        createTable();
    }

    /**
     * Prompts the user to make a move from the given list of moves and returns the selected move.
     *
     * @param moves the list of available moves
     * @return the user's selected move
     */

    @Override
    public List<String> makeMove(List<String> moves) {
        for (String move : moves) {
            System.out.print(move + " ");
        }
        System.out.println("?");
        while (true) {
            String playerMove = scanner.next();
            for (String move : moves) {
                if (playerMove.equals(move)) {
                    return Collections.singletonList(move);
                }
            }
            System.out.println(bundle.getString("NotCorrectFormat"));
        }
    }

    /**
     * Updates the user's statistics based on the game result.
     *
     * @param win the game result (-1 for lose, 0 for draw, 1 for win)
     */
    @Override
    public void updateStat(int win) {
        switch (win) {
            case 1 -> System.out.println(bundle.getString("YouWin"));
            case 0 -> System.out.println(bundle.getString("draw"));
            case -1 -> System.out.println(bundle.getString("YouLose"));
        }
        try (Connection connection = DriverManager.getConnection(url)) {
            // Проверяем, существует ли пользователь в таблице statistics
            String checkUserSql = "SELECT COUNT(*) FROM statistics WHERE login = ?";
            try (PreparedStatement checkUserStatement = connection.prepareStatement(checkUserSql)) {
                checkUserStatement.setString(1, login);
                ResultSet resultSet = checkUserStatement.executeQuery();

                if (resultSet.getInt(1) == 0) {
                    // Пользователь не существует, создаем новую запись
                    String insertUserSql = "INSERT INTO statistics (login, win, lose, total) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement insertUserStatement = connection.prepareStatement(insertUserSql)) {
                        insertUserStatement.setString(1, login);
                        insertUserStatement.setInt(2, win == 1 ? 1 : 0);  // Устанавливаем win в 1, если win равен 1, иначе в 0
                        insertUserStatement.setInt(3, win == -1 ? 1 : 0); // Устанавливаем lose в 1, если win равен -1, иначе в 0
                        insertUserStatement.setInt(4, 1);  // Устанавливаем total в 1
                        insertUserStatement.executeUpdate();
                    }
                } else {
                    // Пользователь существует, обновляем существующую запись
                    String updateStatSql = "UPDATE statistics SET win = win + ?, lose = lose + ?, total = total + 1 WHERE login = ?";
                    try (PreparedStatement updateStatStatement = connection.prepareStatement(updateStatSql)) {
                        updateStatStatement.setInt(1, win == 1 ? 1 : 0);  // Увеличиваем win на 1, если win равен 1
                        updateStatStatement.setInt(2, win == -1 ? 1 : 0); // Увеличиваем lose на 1, если win равен -1
                        updateStatStatement.setString(3, login);
                        updateStatStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the user's statistics.
     */

    @Override
    public void getStatistics() {
        try (Connection connection = DriverManager.getConnection(url)) {
            String sql = "SELECT * FROM statistics WHERE login = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, login);
                ResultSet resultSet = statement.executeQuery();

                System.out.println("-------------------------------------------------------");
                System.out.println("|   Login   |   Win   |   Lose  |   Draw  |   Total  |");
                System.out.println("-------------------------------------------------------");

                while (resultSet.next()) {
                    String retrievedLogin = resultSet.getString("login");
                    int win = resultSet.getInt("win");
                    int lose = resultSet.getInt("lose");
                    int total = resultSet.getInt("total");
                    int draw = total - win - lose;

                    System.out.printf("|%10s|%8d|%9d|%9d|%9d|%n", retrievedLogin, win, lose, draw, total);
                }

                System.out.println("-------------------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the statistics table in the database if it doesn't exist.
     */

    public void createTable() {
        try (Connection connection = DriverManager.getConnection(url)) {
            String sql = "CREATE TABLE IF NOT EXISTS statistics (" +
                    "login TEXT PRIMARY KEY," +
                    "win INTEGER," +
                    "lose INTEGER," +
                    "total INTEGER" +
                    ");";

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
