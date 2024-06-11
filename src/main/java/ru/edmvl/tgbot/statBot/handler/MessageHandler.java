package ru.edmvl.tgbot.statBot.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;
import ru.edmvl.tgbot.statBot.message_executor.MessageExecutor;
import ru.edmvl.tgbot.statBot.service.LogService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class MessageHandler implements Handler<Message> {

    private final MessageExecutor messageExecutor;
    private final LogService logService;

    private final Logger logger = Logger.getLogger("MessageHandler");

    @Value("${telegram.poputchikbot.username}")
    private String botUsername;

    @Lazy
    public MessageHandler(MessageExecutor messageExecutor, LogService logService) {
        this.messageExecutor = messageExecutor;
        this.logService = logService;
    }

    @Override
    public void choose(Message message) {
        String text = message.getText();
        System.out.println(text);
        logService.save(
                message.getChat().getId().toString(),
                message.getChat().getTitle(),
                message.getFrom().getId().toString(),
                message.getFrom().getUserName(),
                LocalDateTime.now(),
                message.getText(),
                getMaxSizePhoto(message),
                getDocumentId(message),
                getSticker(message)
        );
    }

    private String getSticker(Message message) {
        Sticker sticker = message.getSticker();
        return Objects.nonNull(sticker) ? sticker.getFileId() : null;
    }

    private String getDocumentId(Message message) {
        Document document = message.getDocument();
        if (Objects.isNull(document)) {
            return null;
        }
        return document.getFileId();
    }

    private String getMaxSizePhoto(Message message) {
        List<PhotoSize> photo = message.getPhoto();
        if (Objects.isNull(photo)) {
            return null;
        }
        Optional<PhotoSize> max = photo.stream().max(Comparator.comparingInt(PhotoSize::getFileSize));
        return max.map(PhotoSize::getFileId).orElse(null);
    }

}
