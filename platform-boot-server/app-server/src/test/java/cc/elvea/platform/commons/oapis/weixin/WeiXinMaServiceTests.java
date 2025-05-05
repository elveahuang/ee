package cc.elvea.platform.commons.oapis.weixin;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.oapis.weixin.service.WeiXinMaService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class WeiXinMaServiceTests extends BaseTests {

    @Autowired(required = false)
    WeiXinMaService weiXinMaService;

    @Test
    public void baseTest() throws WxErrorException {
        Assertions.assertNotNull(weiXinMaService);
        String accessToken = weiXinMaService.getService().getAccessToken();
        Assertions.assertNotNull(accessToken);
    }

}
