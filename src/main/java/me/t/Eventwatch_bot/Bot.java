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
        userId = user.getId();

        System.out.println(user.getFirstName() + " wrote " + msg.getText());
        System.out.println(update);
        sendText(userId, "Hello crypto enthusiast! Now I can only send this message, but more functionality is coming.");
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