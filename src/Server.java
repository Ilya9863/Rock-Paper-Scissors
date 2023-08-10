
import sources.Game;
import sources.Player;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CopyOnWriteArrayList;


public class Server {

    class DataParser {
        // string format = {lobbyId}/{case}/{message}
        public String parseString(String response) {
            System.out.println("Пришло: "  + response);
            String[] data = response.split("/");
            if (data.length == 1 && (Integer.parseInt(data[0]) == 1 || Integer.parseInt(data[0]) == 2)) {
                return data[0];
            } else {
                try {
                    int lobbyId = Integer.parseInt(data[0]);
                    int instruction = Integer.parseInt(data[1]);
                    threadSafeLobbyList.get(lobbyId).set(instruction, data[2]);
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                }
                return null;
            }
        }
    }
    private final List<List<String>> threadSafeLobbyList = new CopyOnWriteArrayList<>();
    /////// 14 ==== лобби стат
    // 0: 1 клиент подключен
    // 1: 2 клиент подключен
    // 2: ответ первого клиента
    // 3: ответ второго клиента
    // 4: ответ сервера для первого клиента
    // 5: ответ сервера для второго клиента
    // 6: количество побед первого клиента
    // 7: количество побед второго клиента
    // 9: сообщения для всех пользователей от лобби
    // 10: служебное сообщение от первого клиента
    // 11: служебное сообщение от второго клиента
    // 12: cлужебное сообщение для первого клиента от сервера
    // 13: cлужебное сообщение для второго клиента от сервера
    // 14: сообщение для первого клиента
    // 15: сообщение для второго клиента
    private final static String GAMES_OPTIONS =
            "1. КМН\n" +
            "2. Tест\n" +
            "3. Выйти из лобби\n";
    private final static int MAX_VAlUE_CHOICE = 2;

    private DatagramSocket socket;
    private ExecutorService threadsPull;
    private ExecutorService threadOfMain;

    private final int TIMER_FOR_SHUT_DOWN = 1000;

    public void start(int port) {
        try {
            socket = new DatagramSocket(port);
            threadOfMain = Executors.newSingleThreadExecutor();
        } catch (SocketException e) {
            System.err.println("Problems with starting working socket " + e);
        }
        Runnable server = () -> {
            DataParser Parser = new DataParser();
            while (!socket.isClosed()) {
                try {
                    byte[] data = new byte[socket.getReceiveBufferSize()];
                    DatagramPacket pack = new DatagramPacket(data, data.length);
                    socket.receive(pack);
                    String message = new String(pack.getData(), pack.getOffset(), pack.getLength(), StandardCharsets.UTF_8);
                    System.out.println(message);

                    String parsedString = Parser.parseString(message);
                    if (!(parsedString == null)) {
                        handleClient(pack, parsedString); // Вызываем метод для обслуживания клиента
                    }
                } catch (IOException e) {
                    if (!socket.isClosed()) {
                        System.err.println("Error with receive message " + e);
                    }
                }
            }
        };

        threadOfMain.submit(server);
    }

    private String waitResponse(int lobbyId, int indexResponse) {
        long startTime = System.currentTimeMillis();
        while (threadSafeLobbyList.get(lobbyId).get(indexResponse).equals("0")) {
            try {
                Thread.sleep(100); // Задержка в 1 секунду
            } catch (InterruptedException e) {
                // Обработка возможного исключения
            }

            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - startTime;
            if (elapsedTime >= 10000) {
                return null; // Возвращаем null после истечения 10 секунд
            }
        }

        String res = threadSafeLobbyList.get(lobbyId).get(indexResponse);
        threadSafeLobbyList.get(lobbyId).set(indexResponse, "0");
        return res;
    }

    private void createNewLobby(int lobbyId) {
        threadOfMain = Executors.newSingleThreadExecutor();
        Runnable lobbyHandler = () -> {
            Game game;




            String choice = "Выберите игру:\n" + GAMES_OPTIONS;
            System.out.println(choice);
            threadSafeLobbyList.get(lobbyId).set(11, choice); // сделали запрос создателю лобби на выбор игры

            String gameChoose = null;
            int type = 0;

            do {
                gameChoose = waitResponse(lobbyId, 10);
                if (gameChoose != null) {
                    try{
                        type = Integer.parseInt(gameChoose);
                    } catch (NumberFormatException ex) {
                        type = -1;
                    }
                }
                if (gameChoose == null || type < 1 || type > 3) {
                    threadSafeLobbyList.get(lobbyId).set(4, "Error");
                }
            } while (gameChoose == null || type < 1 || type > 3);
            threadSafeLobbyList.get(lobbyId).set(4, "Complete");



        };
        threadOfMain.submit(lobbyHandler);
    }

    private int getFreeLobbyId() {
        return 0;
    }

    private void makeRequestToClient(String message, DatagramPacket pack) {
        pack.setData(("R" + message).getBytes(StandardCharsets.UTF_8));
        try {
            socket.send(pack);
        } catch (IOException e) {
            System.err.println("Error sending message: " + e);
        }
    }

    private void makeAnswerToClient(String message, DatagramPacket pack) {
        pack.setData(("A" + message).getBytes(StandardCharsets.UTF_8));
        try {
            socket.send(pack);
        } catch (IOException e) {
            System.err.println("Error sending message: " + e);
        }
    }

    private void handleClient(DatagramPacket pack, String message) {
        Runnable clientHandler = () -> {
            if (message.equals("1")) { /// создатель лобби
                int lobbyId = getFreeLobbyId();
                createNewLobby(lobbyId); // создали лобби
                threadSafeLobbyList.get(0).set(0, "1"); // сказали, что мы подключены к лобби

                makeAnswerToClient(String.valueOf(lobbyId), pack); // отправили lobbyId


                String requestFromLobby = waitResponse(lobbyId, 11); // получаем запрос от лобби на выбор игры
                makeRequestToClient(requestFromLobby, pack); // отправляем запрос клиенту
                do {
                    String res = waitResponse(lobbyId, 4);
                    if (res != null || res.equals("Error")) {
                        makeAnswerToClient("Complete", pack);
                        break;
                    } else {
                        makeAnswerToClient("Error", pack);
                    }
                } while (true);
            }




            if (message.equals("2")) { /// кто присоединился к лобби
                threadSafeLobbyList.get(0).set(1, "1");
                while (true) {
                    System.out.println("2 client тут");
                    pack.setData("2 client тут".getBytes(StandardCharsets.UTF_8));
                    try {
                        socket.send(pack);
                    } catch (IOException e) {
                        System.err.println("Error sending message: " + e);
                    }
                    try {
                        Thread.sleep(1000); // Задержка в 1 секунду
                    } catch (InterruptedException e) {
                        // Обработка возможного исключения
                    }
                }
            }
            String backMessage = "Hello, " + message;
            pack.setData(backMessage.getBytes(StandardCharsets.UTF_8));
            try {
                socket.send(pack);
            } catch (IOException e) {
                System.err.println("Error sending message: " + e);
            }
        };
        Executors.newSingleThreadExecutor().submit(clientHandler);
    }




    public void close() {
        socket.close();
        closeThreads(threadsPull, threadOfMain, TIMER_FOR_SHUT_DOWN);
    }

    static void closeThreads(ExecutorService threadsPull, ExecutorService threadOfMain, int timer_for_shut_down) {
        threadsPull.shutdown();
        threadOfMain.shutdown();
        try {
            if (!threadsPull.awaitTermination(timer_for_shut_down, TimeUnit.MILLISECONDS) ||
                    !threadOfMain.awaitTermination(timer_for_shut_down, TimeUnit.MILLISECONDS)) {
                System.err.println("Problems with stop working process ");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void initializeSafeList() {
        for (int i = 0; i < 1000; i++) {
            List<String> sublist = new CopyOnWriteArrayList<>();
            for (int j = 0; j < 20; j++) {
                sublist.add("0");
            }
            threadSafeLobbyList.add(sublist);
        }
    }
    
    public static void main(String[] args) {

        // Create an instance of the server
        Server server = new Server();

        server.initializeSafeList();

        // Start the server
        int port = 1234; // Specify the port on which the server will listen
        server.start(port);

        System.out.println("Server started. Press Enter to stop.");

        // Wait for user input to stop the server
        try (Scanner scanner = new Scanner(System.in)) {
            scanner.nextLine();
        }

        // Stop the server
        server.close();

        System.out.println("Server stopped.");
    }
}
