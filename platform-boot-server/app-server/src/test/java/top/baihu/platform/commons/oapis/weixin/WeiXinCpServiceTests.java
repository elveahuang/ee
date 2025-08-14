package top.baihu.platform.commons.oapis.weixin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;
import top.baihu.platform.commons.oapis.weixin.service.WeiXinCpService;

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
