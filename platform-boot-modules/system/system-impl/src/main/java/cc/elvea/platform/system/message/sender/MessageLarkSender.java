package cc.elvea.platform.system.message.sender;

import cc.elvea.platform.commons.oapis.lark.message.LarkMessagePayload;
import cc.elvea.platform.commons.oapis.lark.service.LarkService;
import cc.elvea.platform.commons.utils.ExceptionUtils;
import cc.elvea.platform.commons.utils.GsonUtils;
import cc.elvea.platform.system.message.model.dto.SendMessageDto;
import cc.elvea.platform.system.message.service.MessageContentService;
import com.lark.oapi.service.im.v1.enums.CreateMessageReceiveIdTypeEnum;
import com.lark.oapi.service.im.v1.model.CreateMessageReq;
import com.lark.oapi.service.im.v1.model.CreateMessageReqBody;
import com.lark.oapi.service.im.v1.model.CreateMessageResp;
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
            CreateMessageReqBody messageReqBody = CreateMessageReqBody.newBuilder()
                    .content(GsonUtils.toJson(message.getContent()))
                    .msgType(payload.getType())
                    .receiveId(message.getRecipient().getUsername())
                    .build();
            CreateMessageReq messageReq = CreateMessageReq.newBuilder()
                    .createMessageReqBody(messageReqBody)
                    .receiveIdType(CreateMessageReceiveIdTypeEnum.USER_ID)
                    .build();
            CreateMessageResp resp = larkService.getImService().message().create(messageReq);

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
