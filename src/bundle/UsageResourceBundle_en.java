package bundle;

import java.util.ListResourceBundle;

public class UsageResourceBundle_en extends ListResourceBundle {
    final Object[][] data = new Object[][]{
            {"WriteLogin", "Enter login: "},
            {"AvailablesSettings", "Available settings: "},
            {"ChangeLanguage", "1. Change language "},
            {"Choose", "Choose: "},
            {"ChooseChangeLanguage", "Selected: change language "},
            {"ChooseBack", "Selected: back"},
            {"ChengConfig", "1: change game config"},
            {"WriteQuantity", "Enter quantity from 1 to "},
            {"NotCorrectFormat", "incorrect format"},
            {"CurrentConfigIs", "Current config: "},
            {"Continue", "2: continue "},
            {"PlayAgain", "1: play again "},
            {"ShowStat", "2: show statistics "},
            {"rockPaperScissors", "ROCK, PAPER, SCISSORS! \n"},
            {"makeMove", "Make your move \n"},
            {"computerGoingMove", "Computer's turn \n"},
            {"draw", "Draw"},
            {"YouWin", " You win 🥳"},
            {"YouLose", " You lose 😔"},
            {"beats", " beats "},
            {"WIN", "WIN"},
            {"LOSE", "LOSE"},
            {"DRAW", "DRAW"},
            {"ALL_GAMES", "ALL GAMES"},
            {"WIN_RATE", "% WIN RATE"},
            {"nextGame", "Play again?"},
            {"ServerStarting", "Server started. Waiting for client connection... \n"},
            {"GetMessageForClient", "Received message from client: \n"},
            {"ServerGetMessage", "Server received your message: \n"},
            {"ClientDisconnect", "Client disconnected. \n"},
            {"IncorrectChooseTryAgain", "Incorrect choice. Please try again... \n"},
            {"OpenSelectGameMode", "Game mode selection opened. \n"},
            {"EndTheGame", "Exiting the program. \n"},
            {"IncorrectChoose", "Incorrect choice. "},
            {"SelectMenuItem", "Select a menu item: "},
            {"Menu", "Menu:"},
            {"Options", "1: Options"},
            {"GameModeSelection", "2: Game mode selection"},
            {"Out", "3: Exit"},
            {"Games", "Games: "},
            {"WithPC", "1: Against the computer\n"},
            {"Play", "Play:"},
            {"RPS", "1: RPS"},
            {"Back", "2: Back"},
    };

    @Override
    protected Object[][] getContents() {
        return this.data;
    }
}
