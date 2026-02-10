package cc.wdev.platform.system.message.sender;

import cc.wdev.platform.commons.oapis.lark.message.LarkMessagePayload;
import cc.wdev.platform.commons.oapis.lark.message.LarkMessageResponse;
import cc.wdev.platform.commons.oapis.lark.service.LarkService;
import cc.wdev.platform.commons.utils.ExceptionUtils;
import cc.wdev.platform.commons.utils.GsonUtils;
import cc.wdev.platform.system.message.domain.dto.SendMessageDto;
import cc.wdev.platform.system.message.service.MessageContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
public class MessageLarkSender implements MessageSender {

    private LarkService larkService;

    private MessageContentService messageContentService;

    @Override
    public void send(SendMessageDto message) {
        try {
            log.info("Send lark message. message id [{}]. message content id [{}]. start", message.getId(), message.getContentId());

            // 检查飞书服务是否已经启动
            if (this.larkService == null) {
                log.error("Send lark message [{}] failed. lark is disabled.", message.getId());
                return;
            }

            // 发送飞书消息
            LarkMessagePayload payload = GsonUtils.parse(message.getContent(), LarkMessagePayload.class);
            LarkMessageResponse resp = this.larkService.createMessage(payload);
            log.info("Send lark message. message id [{}]. message content id [{}]. result - [{}].", message.getId(), message.getContentId(), GsonUtils.toJson(resp));
            if (resp.getCode() == 0) {
                // 设置消息内容发送状态
                this.messageContentService.success(message.getContentId(), GsonUtils.toJson(resp));
                log.info("Send lark message. message id [{}]. message content id [{}]. done.", message.getId(), message.getContentId());
            } else {
                // 设置消息内容发送状态
                this.messageContentService.fail(message.getContentId(), GsonUtils.toJson(resp));
                log.info("Send lark message. message id [{}]. message content id [{}]. done.", message.getId(), message.getContentId());
            }
        } catch (Exception e) {
            // 设置消息内容发送状态
            this.messageContentService.fail(message.getContentId(), "", ExceptionUtils.getStackTraceAsString(e));
            log.error("Send lark message. message id [{}]. message content id [{}]. failed.", message.getId(), message.getContentId(), e);
        }
    }

    @Autowired(required = false)
    public void setLarkService(LarkService larkService) {
        this.larkService = larkService;
    }

    @Autowired()
    public void setMessageContentService(MessageContentService messageContentService) {
        this.messageContentService = messageContentService;
    }

}
