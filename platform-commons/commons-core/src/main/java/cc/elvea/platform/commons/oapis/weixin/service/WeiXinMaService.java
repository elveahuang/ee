package cc.elvea.platform.commons.oapis.weixin.service;

import cc.elvea.platform.commons.oapis.weixin.config.AppMaConfig;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.WxMaConfig;

/**
 * @author elvea
 */
public interface WeiXinMaService {

    WxMaConfig getConfigStorage();

    WxMaConfig getConfigStorage(AppMaConfig appConfig);

    WxMaService getService();

    WxMaService getService(AppMaConfig appConfig);

}
