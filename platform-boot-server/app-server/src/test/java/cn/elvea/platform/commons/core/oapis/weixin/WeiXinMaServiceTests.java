package cn.elvea.platform.commons.core.oapis.weixin;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.core.oapis.weixin.service.WeiXinMaService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 0.0.1
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
