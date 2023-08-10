import rockPaperScissors.RobotPlayer;
import rockPaperScissors.RockPaperScissorsGame;
import sources.Game;
import sources.RockPaperScissorsUser;
import sources.User;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Main class for the start
 * <p>
 * Represents a client for the game.
 */
public class Client {

    private User user;

    private static ResourceBundle bundle;

    // 1 - request
    // 2 - answer
    // 3 - message
//    private int parseResponseFromServer(String message) {
//        char type = message.charAt(0);
//        if (type == 'R') {
//            return 1;
//        }
//        if (type == 'A') {
//            return 2;
//        }
//        return 3;
//    }

//    private String makeResponseToServer(int lobbyId, int port, String message) {
//        return lobbyId + "/" + port + "/" + message;
//    }

    /**
     * Runs the client with the specified host, port, and game mode.
     *
     * @param host               the host of the server
     * @param port               the port of the server
     * @param willPlayWithFriend indicates whether the user will play with a friend
     */

    public void run(String host, int port, boolean willPlayWithFriend) {
//        if (!willPlayWithFriend || true) { /// Игра с другом на сервере недоступна (не успел реализовать корректную работу сервера)
        boolean isInfinityLoop = true;
        boolean isFirstGame = true;
        Game game = chooseGame();
        while (isInfinityLoop) {
            int choice = 1;
            if (!isFirstGame) {
                System.out.println(bundle.getString("PlayAgain"));
                System.out.println(bundle.getString("ShowStat"));
                System.out.println(bundle.getString("Out"));
                choice = makeChoice(1, 3);
            }
            isFirstGame = false;
            switch (choice) {
                case 1 -> {
                    if (game != null) {
                        game.startGame(user, new RobotPlayer());
                    }
                }
                case 2 -> user.getStatistics();
                case 3 -> isInfinityLoop = false;
            }
        }

//        } else {
//            SocketAddress socketAddress;
//            try {
//                socketAddress = new InetSocketAddress(InetAddress.getByName(host), port);
//                DatagramSocket socket = new DatagramSocket();
//                System.out.println("1: Создать лобби");
//                System.out.println("2: Войти в лобби");
//                System.out.println("3: Назад");
//
//                int choice = makeChoice(1, 3);
//                sendToServer(socket, socketAddress, String.valueOf(choice)); // говорим серверу кто мы (создатель лобби или нет)
//
//                if (choice == 1) { /// код для создателя лобби
//                    System.out.println("we are creator");
//
//                    String ServerResponse = receiveFromServer(socket);  /// поучить lobbyId
//                    int lobbyId = Integer.parseInt(ServerResponse.substring(1));
//                    System.out.println("Лобби успешно создано, лобби id = " + lobbyId);
//
//
//                    String ServerChooseMessage = receiveFromServer(socket);
//                    System.out.println(ServerChooseMessage);
//                    int num = 0;
//                    // выбор игры:
//                    do {
//                        if (num == 0) {
//                            System.out.println("Введите номер:");
//                            num++;
//                        } else {
//                            System.out.println("Неверный номер, попробуйте снова:");
//                        }
//                        sendToServer(socket, socketAddress, makeResponseToServer(lobbyId, 10, "1"));
//                        try {
//                            Thread.sleep(1000); // Задержка в 1 секунду
//                            ServerResponse = receiveFromServer(socket);
//                        } catch (InterruptedException e) {
//                            // Обработка возможного исключения
//                        }
//                    } while (!ServerResponse.equals("Complete"));
//
//                    System.out.println("123123");
//                    while (true);
//                }
//
//                if (choice == 2) { /// код для клиента, присоединяющегося к лобби
//                    while (true) {
//                        String response = receiveFromServer(socket);
//                        System.out.println(response);
//                    }
//                }
//
//                // Получение ответа от сервера
//                String response = receiveFromServer(socket);
//
//                // Вывод полученного ответа от сервера
//                System.out.println("Server response: " + response);
//
//                // Закрытие сокета после обмена сообщениями
//                socket.close();
//
//            } catch (IOException e) {
//                System.err.println("Error sending/receiving message: " + e);
//            }
//        }
    }

//    private void sendToServer(DatagramSocket socket, SocketAddress serverAddress, String message) throws IOException {
//        byte[] sendData = message.getBytes(StandardCharsets.UTF_8);
//        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress);
//        socket.send(sendPacket);
//    }
//
//    private String receiveFromServer(DatagramSocket socket) throws IOException {
//        byte[] receiveData = new byte[socket.getReceiveBufferSize()];
//        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//        socket.receive(receivePacket);
//        return new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength(), StandardCharsets.UTF_8);
//    }

    /**
     * Prompts the user to make a choice between the specified range of options.
     *
     * @param begin the beginning of the range
     * @param end   the end of the range
     * @return the user's choice
     */
    int makeChoice(int begin, int end) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (choice < begin || choice > end) {
            System.out.println(bundle.getString("SelectMenuItem"));
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < begin || choice > end) {
                    System.out.println(bundle.getString("IncorrectChooseTryAgain"));
                }
            } else {
                System.out.println(bundle.getString("IncorrectChooseTryAgain"));
                scanner.next();
            }
        }
        return choice;
    }

    /**
     * Displays the menu and handles the user's choices.
     */

    public void menu() {
        while (true) {
            System.out.println(bundle.getString("Menu"));
            System.out.println(bundle.getString("Options"));
            System.out.println(bundle.getString("GameModeSelection"));
            System.out.println(bundle.getString("Out"));

            int choice = makeChoice(1, 3);

            switch (choice) {
                case 1 -> {
                    user.getAllSettings();
                    bundle = user.getBundle();
                }
                case 2 -> {
                    System.out.println(bundle.getString("OpenSelectGameMode"));
                    chooseGameMode();
                }
                case 3 -> {
                    System.out.println(bundle.getString("EndTheGame"));
                    System.exit(0);
                }
                default -> System.out.println(bundle.getString("IncorrectChoose"));
            }
        }
    }

    /**
     * Establishes a connection to the server.
     *
     * @param willPlayWithFriend indicates whether the user will play with a friend
     */

    void connectionToServer(boolean willPlayWithFriend) {
        String host = "localhost"; // Укажите хост-сервера
        int port = 1234; // Укажите порт сервера
        run(host, port, willPlayWithFriend);
        System.out.println("Client finished.");
        System.exit(0);
    }

    private Game chooseGame() {
        System.out.println(bundle.getString("Games"));
        System.out.println(bundle.getString("RPS"));
        System.out.println(bundle.getString("Back"));

        int choice = makeChoice(1, 3);

        try {
            switch (choice) {
                case 1 -> {
                    user = new RockPaperScissorsUser(user);
                    return new RockPaperScissorsGame(user.getBundle());
                }
                case 2 -> {
                    menu();
                }
                default -> {
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Prompts the user to choose the game mode.
     */

    public void chooseGameMode() {
        boolean exit = false;
        while (!exit) {
            System.out.println(bundle.getString("Play"));
            System.out.println(bundle.getString("WithPC"));
            System.out.println(bundle.getString("Back"));

            int choice = makeChoice(1, 3);

            switch (choice) {
                case 1 -> connectionToServer(false);
                case 2 -> exit = true;
            }
        }
    }

    /**
     * The entry point of the client program.
     *
     * @param args the command-line arguments
     */

    public static void main(String[] args) {
        Client client = new Client();
        client.user = new User();
        while (!client.user.auth()) ;
        bundle = client.user.getBundle();
        client.menu();
    }
}