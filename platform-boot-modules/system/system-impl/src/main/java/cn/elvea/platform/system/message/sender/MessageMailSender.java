package cn.elvea.platform.system.message.sender;

import cn.elvea.platform.commons.core.extensions.mail.MailBody;
import cn.elvea.platform.commons.core.extensions.mail.MailSender;
import cn.elvea.platform.system.message.model.dto.SendMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Service
public class MessageMailSender implements MessageSender {

    private MailSender mailSender;

    @Override
    public void send(SendMessageDto message) {
        try {
            log.info("Send mail message [{}] start", message.getId());

            // 检查邮件服务是否已经启动
            if (this.mailSender == null) {
                log.error("Send mail message [{}] failed. mail is disabled.", message.getId());
                return;
            }

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

    @Autowired(required = false)
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    
}
