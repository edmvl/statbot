package ru.edmvl.tgbot.statBot.message_executor;

import ru.edmvl.tgbot.statBot.app.MyTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Objects;

@Service
public class MessageExecutorImpl implements MessageExecutor {

    private MyTelegramBot tgBot;

    @Override
    public Integer sendMessage(SendMessage sendMessage) {
        try {
            Message execute = tgBot.execute(sendMessage);
            return execute.getMessageId();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer sendMessage(String chatId, String text, Integer replyMessageId) {
        SendMessage sendMessage = new SendMessage();
        if (Objects.nonNull(replyMessageId)) {
            sendMessage.setReplyToMessageId(replyMessageId);
        }
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        return sendMessage(sendMessage);
    }

    @Override
    public Integer sendMessage(Long chatId, String text, Integer replyMessageId) {
        return sendMessage(String.valueOf(chatId), text, replyMessageId);
    }

    @Override
    public Integer sendMessage(String chatId, String text) {
        return sendMessage(chatId, text, null);
    }

    @Override
    public Integer sendMessage(Long chatId, String text) {
        return sendMessage(String.valueOf(chatId), text, null);
    }

    @Override
    public void deleteMessage(Long chatId, Integer messageId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(String.valueOf(chatId));
        deleteMessage.setMessageId(messageId);
        try {
            tgBot.execute(deleteMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public void setMyTelegramBot(MyTelegramBot tgBot) {
        this.tgBot = tgBot;
    }

}
