package cn.elvea.platform.system.message.sender;

import cn.elvea.platform.commons.core.extensions.sms.SmsBody;
import cn.elvea.platform.commons.core.extensions.sms.SmsSender;
import cn.elvea.platform.commons.core.utils.ExceptionUtils;
import cn.elvea.platform.system.message.model.dto.SendMessageDto;
import cn.elvea.platform.system.message.service.MessageContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Service
public class MessageSmsSender implements MessageSender {

    private SmsSender smsSender;

    private MessageContentService messageContentService;

    @Override
    public void send(SendMessageDto message) {
        try {
            log.info("Send sms message. message id [{}]. message content id [{}]. start.", message.getId(), message.getContentId());

            // 检查短信服务是否已经启动
            if (this.smsSender == null) {
                log.info("Send sms message. message id [{}]. message content id [{}]. failed. sms is disabled.", message.getId(), message.getContentId());
                return;
            }

            SmsBody body = SmsBody.builder()
                    .mobileNumber(message.getRecipient().getMobileNumber())
                    .params(message.getParams()).build();
            smsSender.send(body);

            // 设置消息内容发送状态
            this.messageContentService.success(message.getContentId(), "Success");
            log.info("Send sms message. message id [{}]. message content id [{}]. done.", message.getId(), message.getContentId());
        } catch (Exception e) {
            // 设置消息内容发送状态
            this.messageContentService.fail(message.getContentId(), "", ExceptionUtils.getStackTraceAsString(e));
            log.error("Send sms message. message id [{}]. message content id [{}]. failed.", message.getId(), message.getContentId(), e);
        }
    }

    @Autowired(required = false)
    public void setSmsSender(SmsSender smsSender) {
        this.smsSender = smsSender;
    }

    @Autowired
    public void setMessageContentService(MessageContentService messageContentService) {
        this.messageContentService = messageContentService;
    }

}
