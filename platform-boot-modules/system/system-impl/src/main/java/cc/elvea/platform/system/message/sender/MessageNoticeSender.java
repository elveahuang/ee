package cc.elvea.platform.system.message.sender;

import cc.elvea.platform.commons.utils.ExceptionUtils;
import cc.elvea.platform.system.message.model.dto.SendMessageDto;
import cc.elvea.platform.system.message.model.entity.NoticeEntity;
import cc.elvea.platform.system.message.service.MessageContentService;
import cc.elvea.platform.system.message.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
public class MessageNoticeSender implements MessageSender {

    private NoticeService noticeService;

    private MessageContentService messageContentService;

    @Override
    public void send(SendMessageDto message) {
        try {
            log.info("Send notice message. message id [{}]. message content id [{}]. start", message.getId(), message.getContentId());

            // 检查邮件服务是否已经启动
            if (this.noticeService == null) {
                log.info("Send notice message. message id [{}]. message content id [{}]. failed. notice is disabled.", message.getId(), message.getContentId());
                return;
            }

            NoticeEntity entity = NoticeEntity.builder()
                    .senderId(message.getSender().getId())
                    .recipientId(message.getRecipient().getId())
                    .subject(message.getSubject())
                    .content(message.getContent())
                    .build();

            this.noticeService.save(entity);

            // 设置消息内容发送状态
            this.messageContentService.success(message.getContentId(), "Success");
            log.info("Send notice message. message id [{}]. message content id [{}]. done", message.getId(), message.getContentId());
        } catch (Exception e) {
            // 设置消息内容发送状态
            this.messageContentService.fail(message.getContentId(), "", ExceptionUtils.getStackTraceAsString(e));
            log.error("Send notice message. message id [{}]. message content id [{}]. failed.", message.getId(), message.getContentId(), e);
        }
    }

    @Autowired(required = false)
    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @Autowired
    public void setMessageContentService(MessageContentService messageContentService) {
        this.messageContentService = messageContentService;
    }

}
