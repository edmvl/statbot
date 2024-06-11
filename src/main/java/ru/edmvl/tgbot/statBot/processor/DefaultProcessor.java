package ru.edmvl.tgbot.statBot.processor;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.edmvl.tgbot.statBot.handler.MessageHandler;

@Component
public class DefaultProcessor implements Processor {

    private final MessageHandler messageHandler;

    public DefaultProcessor(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public void executeMessage(Message message) {
        messageHandler.choose(message);
    }
}
