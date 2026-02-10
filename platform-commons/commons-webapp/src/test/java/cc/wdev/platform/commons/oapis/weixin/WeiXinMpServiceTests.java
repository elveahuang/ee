package cc.wdev.platform.commons.oapis.weixin;

import cc.wdev.dev.webapp.BaseTests;
import cc.wdev.platform.commons.oapis.weixin.service.WeiXinMpService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class WeiXinMpServiceTests extends BaseTests {

    @Autowired(required = false)
    WeiXinMpService weiXinMpService;

    @Test
    public void baseTest() throws WxErrorException {
        Assertions.assertNotNull(weiXinMpService);
        String accessToken = weiXinMpService.getService().getAccessToken();
        Assertions.assertNotNull(accessToken);
        String jsapiTicket = weiXinMpService.getService().getJsapiTicket();
        Assertions.assertNotNull(jsapiTicket);
    }

}
