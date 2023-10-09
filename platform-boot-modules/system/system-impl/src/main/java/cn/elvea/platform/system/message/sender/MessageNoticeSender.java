package cn.elvea.platform.system.message.sender;

import cn.elvea.platform.system.message.model.dto.SendMessageDto;
import cn.elvea.platform.system.message.model.entity.NoticeEntity;
import cn.elvea.platform.system.message.service.NoticeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Service
@AllArgsConstructor
public class MessageNoticeSender implements MessageSender {

    private final NoticeService noticeService;

    @Override
    public void send(SendMessageDto message) {
        try {
            log.info("Send notice message [{}] start", message.getId());

            NoticeEntity entity = NoticeEntity.builder()
                    .senderId(message.getSender().getId())
                    .recipientId(message.getRecipient().getId())
                    .subject(message.getSubject())
                    .content(message.getContent())
                    .build();

            this.noticeService.save(entity);
        } catch (Exception e) {
            log.error("Send notice message [{}] failed", message.getId(), e);
        }
    }

}
