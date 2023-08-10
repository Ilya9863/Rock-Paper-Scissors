package bundle;

import java.util.ListResourceBundle;

public class UsageResourceBundle_ru extends ListResourceBundle {

    final Object[][] data = new Object[][]{
            {"WriteLogin", "Введите логин: "},
            {"AvailablesSettings", "Доступные настройки: "},
            {"ChangeLanguage", "1. Сменить язык "},
            {"Choose", "Выберите: "},
            {"ChooseChangeLanguage", "Выбрано: сменить язык "},
            {"ChooseBack", "Выбрано: назад"},
            {"ChengConfig", "1: сменить конфиг игры"},
            {"WriteQuantity", "Введи количество от 1 до "},
            {"NotCorrectFormat", "не верный формат"},
            {"CurrentConfigIs", "Текущий конфиг: "},
            {"Continue", "2: продолжить "},
            {"PlayAgain", "1: сыграть еще раз "},
            {"ShowStat", "2: показать статистику "},
            {"rockPaperScissors", "КАМЕНЬ, НОЖНИЦЫ, БУМАГА! \n"},
            {"makeMove", "сделайте ход \n"},
            {"computerGoingMove", "ход компьютера \n"},
            {"draw", "Ничья"},
            {"YouWin", " Вы выиграли 🥳"},
            {"YouLose", " Вы проиграли 😔"},
            {"beats", " бьет "},
            {"WIN", "ПОБЕДА"},
            {"LOSE", "ПОРАЖЕНИЕ"},
            {"DRAW", "НИЧЬЯ"},
            {"ALL_GAMES", "ВСЕГО ИГР"},
            {"WIN_RATE", "% ПОБЕД"},
            {"nextGame", "Хотите сыграть еще раз?"},
            {"ServerStarting", "Сервер запущен. Ожидание подключения клиента... \n"},
            {"GetMessageForClient","Получено сообщение от клиента: \n"},
            {"ServerGetMessage","Сервер получил ваше сообщение: \n"},
            {"ClientDisconnect","Клиент отключился. \n"},
            {"IncorrectChooseTryAgain","Некорректный выбор. Попробуйте еще раз...\n"},
            {"OpenSelectGameMode", "Открыт выбор режима игры. \n"},
            {"EndTheGame", "Выход из программы. \n"},
            {"IncorrectChoose", "Некорректный выбор. "},
            {"SelectMenuItem", "Выберите пункт меню: "},
            {"Menu", "Меню:"},
            {"Options", "1: Настройки"},
            {"GameModeSelection", "2: Выбор режима игры"},
            {"Out", "3: Выход"},
            {"Games", "Игры: "},
            {"WithPC", "1: Против компьютера\n"},
            {"Play", "Играть:"},
            {"RPS", "1: КМН"},
            {"Back", "2: Назад"},
    };

    @Override
    protected Object[][] getContents() {
        return this.data;
    }
}
