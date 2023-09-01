package cn.elvea.platform.commons.core.oapis.weixin.service;

import cn.elvea.platform.commons.core.oapis.weixin.config.AppMpConfig;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface WeiXinMpService {

    WxMpConfigStorage getConfigStorage();

    WxMpConfigStorage getConfigStorage(AppMpConfig appConfig);

    WxMpService getService();

    WxMpService getService(AppMpConfig appConfig);

}
