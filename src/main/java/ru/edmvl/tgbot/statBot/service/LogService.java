package ru.edmvl.tgbot.statBot.service;

import org.springframework.stereotype.Service;
import ru.edmvl.tgbot.statBot.entity.Log;
import ru.edmvl.tgbot.statBot.message_executor.MessageExecutor;
import ru.edmvl.tgbot.statBot.repo.LogRepo;

import java.time.LocalDateTime;
import java.util.logging.Level;

@Service
@lombok.extern.java.Log
public class LogService {

    private final LogRepo logRepo;

    private final MessageExecutor messageExecutor;

    public LogService(LogRepo logRepo, MessageExecutor messageExecutor) {
        this.logRepo = logRepo;
        this.messageExecutor = messageExecutor;
    }

    public void save(
            String chatId, String chatName, String userId, String userName, LocalDateTime dateTime,
            String text, String photo, String documentId, String sticker
    ) {
        log.log(Level.INFO, chatName + " " + userName + " " + " " + text);
        Log log = new Log();
        log.setChatId(chatId);
        log.setChatName(chatName);
        log.setUserId(userId);
        log.setUserName(userName);
        log.setDateTime(dateTime);
        log.setText(text);
        log.setPhoto(photo);
        log.setDocument(documentId);
        log.setStiker(sticker);
        logRepo.save(log);
    }
}
