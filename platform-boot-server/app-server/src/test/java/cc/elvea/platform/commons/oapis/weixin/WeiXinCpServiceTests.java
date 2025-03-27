package cc.elvea.platform.commons.oapis.weixin;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.oapis.weixin.service.WeiXinCpService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class WeiXinCpServiceTests extends BaseTests {

    @Autowired(required = false)
    WeiXinCpService weiXinCpService;

    @Test
    public void baseTest() throws Exception {
        Assertions.assertNotNull(weiXinCpService);
        String accessToken = weiXinCpService.getService().getAccessToken();
        Assertions.assertNotNull(accessToken);
        String jsapiTicket = weiXinCpService.getService().getJsapiTicket();
        Assertions.assertNotNull(jsapiTicket);
    }

}
