package me.t.Eventwatch_bot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        String botToken = Utilities.getSecretProperty("botToken");
        try {
            Bot bot = new Bot(botToken);
            botsApi.registerBot(bot);
        }
        catch (TelegramApiException e) {
            // record logs here
        }
    }
}