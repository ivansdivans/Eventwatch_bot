package me.t.Eventwatch_bot;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    //================================================================================
    // Variables
    //================================================================================
    private Long userId;

    //================================================================================
    // Setting up bot
    //================================================================================
    @Override
    public String getBotUsername() {
        return "Eventwatch_bot";
    }

    @Override
    public String getBotToken() {
        return Utilities.getSecretProperty("botToken");
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();
        var userId = user.getId();
        var usersMessage = msg.getText();

        if (msg.isCommand()) {
            String command = msg.getText();
            switch (command) {
                case "/start":
                    sendText(userId, "This bot can show you a list of crypto events. Simply type '/showallevents'.");
                    break;
                case "/help":
                    sendText(userId, "This bot supports the following commands: /start, /help and /showallevents.");
                    break;
                case "/settings":
                    sendText(userId, "WIP settings default location and crypto topics.");
                    break;
                case "/showallevents":
                    sendText(userId, "WIP list of events.");
                    break;
                default:
                    sendText(userId, "Such command does not exist.");
                    break;
            }
        }
        else {
            sendText(userId, "I see that you wrote '" + usersMessage + "'. Right now, I don't have a reply for that.");
        }
    }

    //================================================================================
    // Bot functionality
    //================================================================================

    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what).build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}