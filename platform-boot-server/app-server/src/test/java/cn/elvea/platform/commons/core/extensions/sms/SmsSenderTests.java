package cn.elvea.platform.commons.core.extensions.sms;

import cn.elvea.platform.BaseTests;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class SmsSenderTests extends BaseTests {

    @Autowired
    private SmsSender smsSender;

    @Test
    public void base() throws Exception {
        Map<String, Object> params = Maps.newHashMap();
        params.put("code", "123456");
        SmsBody body = SmsBody.builder().mobileNumber("13560134096").params(params).build();
        smsSender.send(body);
    }

}
