package me.t.Eventwatch_bot;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    public Bot() {
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/showevents", "Open web app"));
        listOfCommands.add(new BotCommand("/settings", "Set your default settings"));
        listOfCommands.add(new BotCommand("/help", "List of supported commands"));
        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            // log exception
        }
    }

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

            if (msg.isCommand()) {
                String command = msg.getText();
                switch (command) {
                    case "/start":
                        startCommandReceived(chatId, usersFirstName);
                        break;
                    case "/showevents":
                        sendMessage(chatId, "List of events.");
                        break;
                    case "/settings":
                        sendMessage(chatId, "Set your default location and crypto topics.");
                        break;
                    case "/help":
                        sendMessage(chatId, "This bot supports the following commands: /start, /help and /showallevents.");
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
        String answer = "Hi, " + name + "! This bot can show you a list of crypto events. Simply press menu button and select 'Show events'.";
        sendMessage(chatId, answer);
    }
}