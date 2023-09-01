package cn.elvea.platform.commons.core.oapis.weixin.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.elvea.platform.commons.core.oapis.weixin.config.AppMaConfig;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface WeiXinMaService {

    WxMaConfig getConfigStorage();

    WxMaConfig getConfigStorage(AppMaConfig appConfig);

    WxMaService getService();

    WxMaService getService(AppMaConfig appConfig);

}
