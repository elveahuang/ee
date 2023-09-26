package cn.elvea.platform.system.message.sender;

import cn.elvea.platform.commons.core.extensions.mail.MailBody;
import cn.elvea.platform.commons.core.extensions.mail.MailSender;
import cn.elvea.platform.system.message.model.dto.SendMessageDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 系统消息发送器
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Service
@AllArgsConstructor
public class MessageMailSender implements MessageSender {

    private final MailSender mailSender;

    @Override
    public void send(SendMessageDto message) {
        try {
            log.info("Send mail message [{}] start", message.getId());

            MailBody body = MailBody.builder()
                    .subject(message.getSubject())
                    .content(message.getContent())
                    .to(message.getRecipient().getEmail())
                    .build();
            mailSender.send(body);
        } catch (Exception e) {
            log.error("Send mail message [{}] failed", message.getId(), e);
        }
    }

}
