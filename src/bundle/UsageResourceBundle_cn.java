package bundle;

import java.util.ListResourceBundle;

public class UsageResourceBundle_cn extends ListResourceBundle {
    final Object[][] data = new Object[][]{
            {"WriteLogin", "请输入登录名: "},
            {"AvailablesSettings", "可用设置: "},
            {"ChangeLanguage", "1. 更改语言 "},
            {"Choose", "请选择: "},
            {"ChooseChangeLanguage", "已选择: 更改语言 "},
            {"ChooseBack", "已选择: 返回"},
            {"ChengConfig", "1: 更改游戏配置"},
            {"WriteQuantity", "输入数量从1到 "},
            {"NotCorrectFormat", "格式不正确"},
            {"CurrentConfigIs", "当前配置为: "},
            {"Continue", "2: 继续 "},
            {"PlayAgain", "1: 再玩一次 "},
            {"ShowStat", "2: 显示统计信息 "},
            {"rockPaperScissors", "石头，剪刀，布！ \n"},
            {"makeMove", "轮到你了 \n"},
            {"computerGoingMove", "电脑正在行动 \n"},
            {"draw", "平局"},
            {"YouWin", " 你赢了 🥳"},
            {"YouLose", " 你输了 😔"},
            {"beats", " 击败 "},
            {"WIN", "胜利"},
            {"LOSE", "失败"},
            {"DRAW", "平局"},
            {"ALL_GAMES", "所有游戏"},
            {"WIN_RATE", "胜率"},
            {"nextGame", "再玩一次?"},
            {"ServerStarting", "服务器已启动，等待客户端连接... \n"},
            {"GetMessageForClient", "收到来自客户端的消息: \n"},
            {"ServerGetMessage", "服务器收到您的消息: \n"},
            {"ClientDisconnect", "客户端已断开连接。 \n"},
            {"IncorrectChooseTryAgain", "选择不正确，请重试... \n"},
            {"OpenSelectGameMode", "已打开游戏模式选择。 \n"},
            {"EndTheGame", "退出程序。 \n"},
            {"IncorrectChoose", "选择不正确。 "},
            {"SelectMenuItem", "请选择菜单项: "},
            {"Menu", "菜单:"},
            {"Options", "1: 选项"},
            {"GameModeSelection", "2: 游戏模式选择"},
            {"Out", "3: 退出"},
            {"Games", "游戏: "},
            {"WithPC", "1: 与电脑对战\n"},
            {"Play", "开始游戏:"},
            {"RPS", "1: 石头剪刀布"},
            {"Back", "2: 返回"},
    };

    @Override
    protected Object[][] getContents() {
        return this.data;
    }

}
