package ru.edmvl.tgbot.statBot.app;

import ru.edmvl.tgbot.statBot.processor.Processor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class MyTelegramBot extends TelegramLongPollingBot {

    @Value("${telegram.poputchikbot.username}")
    private String username;

    @Value("${telegram.poputchikbot.token}")
    private String token;

    private Processor processor;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        processor.process(update);
    }

    @Autowired
    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

}
