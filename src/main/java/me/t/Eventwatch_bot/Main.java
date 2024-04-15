package me.t.Eventwatch_bot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            Bot bot = new Bot();
            botsApi.registerBot(bot);
        }
        catch (TelegramApiException e) {
            // record logs here
        }
    }
}