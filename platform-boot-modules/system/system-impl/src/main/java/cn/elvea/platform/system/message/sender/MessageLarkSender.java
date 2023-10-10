package cn.elvea.platform.system.message.sender;

import cn.elvea.platform.commons.core.oapis.lark.message.LarkMessagePayload;
import cn.elvea.platform.commons.core.oapis.lark.service.LarkService;
import cn.elvea.platform.commons.core.utils.GsonUtils;
import cn.elvea.platform.system.message.model.dto.SendMessageDto;
import com.lark.oapi.service.im.v1.enums.CreateMessageReceiveIdTypeEnum;
import com.lark.oapi.service.im.v1.model.CreateMessageReq;
import com.lark.oapi.service.im.v1.model.CreateMessageReqBody;
import com.lark.oapi.service.im.v1.model.CreateMessageResp;
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
public class MessageLarkSender implements MessageSender {

    private final LarkService larkService;

    @Override
    public void send(SendMessageDto message) {
        Long messageId = message.getId();
        try {
            log.info("Send lark message [{}] start", messageId);
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
            CreateMessageResp messageResp = larkService.getImService().message().create(messageReq);
            if (messageResp.getCode() != 0) {
                log.error("Send lark message [{}] failed.", message.getId());
            } else {
                log.info("Send lark message [{}] done.", message.getId());
            }
        } catch (Exception e) {
            log.error("Send lark message [{}] failed.", message.getId(), e);
        }
    }

}
