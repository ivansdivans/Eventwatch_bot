package me.t.Eventwatch_bot;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

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
        if (update.hasMessage() && update.getMessage().hasText()) {
            var msg = update.getMessage();
            var chatId = msg.getChatId();
            var usersMessage = msg.getText();
            var usersFirstName = msg.getChat().getFirstName();

//            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//            keyboardMarkup.setResizeKeyboard(true);
//            List<KeyboardRow> keyboardRows = new ArrayList<>();
//
//            KeyboardRow row1 = new KeyboardRow();
//            row1.add("My tracked events");
//            keyboardRows.add(row1);
//
//            KeyboardRow row2 = new KeyboardRow();
//            row2.add("Help");
//            row2.add("Settings");
//            keyboardRows.add(row2);
//
//            keyboardMarkup.setKeyboard(keyboardRows);
//            msg.setReplyMarkup(keyboardMarkup);

            if (msg.isCommand()) {
                String command = msg.getText();
                switch (command) {
                    case "/start":
                        startCommandReceived(chatId, usersFirstName);
                        break;
                    case "/help":
                        sendMessage(chatId, "This bot supports the following commands: /start, /help and /showallevents.");
                        break;
                    case "/settings":
                        sendMessage(chatId, "WIP settings default location and crypto topics.");
                        break;
                    case "/showallevents":
                        sendMessage(chatId, "WIP list of events.");
                        break;
                    default:
                        sendMessage(chatId, "Such command does not exist.");
                        break;
                }
            }
            else {
                sendMessage(chatId, "I see that you wrote '" + usersMessage + "'. Right now, I don't have a reply for that.");
            }
        }
    }

    public void sendMessage(Long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void startCommandReceived(long chatId, String name) {
        String answer = "Hi, " + name + "! This bot can show you a list of crypto events. Simply press menu button 'Show events'.";
        sendMessage(chatId, answer);
    }
}