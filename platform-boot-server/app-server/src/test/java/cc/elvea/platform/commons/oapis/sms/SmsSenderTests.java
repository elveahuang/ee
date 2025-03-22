package cc.elvea.platform.commons.oapis.sms;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.autoconfigure.core.properties.TestProperties;
import cc.elvea.platform.commons.oapis.sms.model.SmsBody;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author elvea
 */
@Slf4j
public class SmsSenderTests extends BaseTests {

    @Autowired
    private TestProperties properties;

    @Autowired
    private SmsFactory smsFactory;

    @Test
    public void base() throws Exception {
        Map<String, Object> params = Maps.newHashMap();
        params.put("code", "123456");
        SmsBody body = SmsBody.builder()
                .mobileNumber(this.properties.getMobile())
                .params(params).build();
        this.smsFactory.getAliyunSmsSender().send(body);
    }

}
