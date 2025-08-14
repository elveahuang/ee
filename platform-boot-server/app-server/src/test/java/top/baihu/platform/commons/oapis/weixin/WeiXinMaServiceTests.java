package top.baihu.platform.commons.oapis.weixin;

import me.chanjar.weixin.common.error.WxErrorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;
import top.baihu.platform.commons.oapis.weixin.service.WeiXinMaService;

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
