package top.baihu.platform.commons.oapis.weixin.service;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import top.baihu.platform.commons.oapis.weixin.config.AppMpConfig;

/**
 * @author elvea
 */
public interface WeiXinMpService {

    WxMpConfigStorage getConfigStorage();

    WxMpConfigStorage getConfigStorage(AppMpConfig appConfig);

    WxMpService getService();

    WxMpService getService(AppMpConfig appConfig);

}
