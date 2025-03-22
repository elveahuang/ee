package cc.elvea.platform.system.message.sender;

import cc.elvea.platform.commons.core.mail.MailSender;
import cc.elvea.platform.commons.core.mail.model.MailBody;
import cc.elvea.platform.commons.utils.ExceptionUtils;
import cc.elvea.platform.system.message.model.dto.SendMessageDto;
import cc.elvea.platform.system.message.service.MessageContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
public class MessageMailSender implements MessageSender {

    private MailSender mailSender;

    private MessageContentService messageContentService;

    @Override
    public void send(SendMessageDto message) {
        try {
            log.info("Send mail message. message id [{}]. message content id [{}]. start", message.getId(), message.getContentId());

            // 检查邮件服务是否已经启动
            if (this.mailSender == null) {
                log.info("Send mail message. message id [{}]. message content id [{}]. failed. mail is disabled.", message.getId(), message.getContentId());
                return;
            }

            // 构建邮件内容
            MailBody body = MailBody.builder()
                    .subject(message.getSubject())
                    .content(message.getContent())
                    .to(message.getRecipient().getEmail())
                    .build();

            mailSender.send(body);

            // 设置消息内容发送状态
            this.messageContentService.success(message.getContentId(), "Success");
            log.info("Send mail message. message id [{}]. message content id [{}]. done", message.getId(), message.getContentId());
        } catch (Exception e) {
            // 设置消息内容发送状态
            this.messageContentService.fail(message.getContentId(), "", ExceptionUtils.getStackTraceAsString(e));
            log.error("Send mail message. message id [{}]. message content id [{}]. failed.", message.getId(), message.getContentId(), e);
        }
    }

    @Autowired(required = false)
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Autowired
    public void setMessageContentService(MessageContentService messageContentService) {
        this.messageContentService = messageContentService;
    }

}
