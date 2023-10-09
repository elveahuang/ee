package cn.elvea.platform.system.message.sender;

import cn.elvea.platform.commons.core.extensions.sms.SmsBody;
import cn.elvea.platform.commons.core.extensions.sms.SmsSender;
import cn.elvea.platform.system.message.model.dto.SendMessageDto;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Service
@AllArgsConstructor
public class MessageSmsSender implements MessageSender {

    private final SmsSender smsSender;

    @Override
    public void send(SendMessageDto message) {
        try {
            log.info("Send sms message [{}] start", message.getId());

            Map<String, Object> params = Maps.newHashMap();
            params.put("code", "123456");

            SmsBody body = SmsBody.builder()
                    .mobileNumber(message.getRecipient().getMobileNumber())
                    .params(params).build();
            smsSender.send(body);
        } catch (Exception e) {
            log.error("Send sms message [{}] failed", message.getId(), e);
        }
    }

}
