package top.baihu.platform.commons.oapis.weixin.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import top.baihu.platform.commons.oapis.weixin.config.AppMaConfig;

/**
 * @author elvea
 */
public interface WeiXinMaService {

    WxMaConfig getConfigStorage();

    WxMaConfig getConfigStorage(AppMaConfig appConfig);

    WxMaService getService();

    WxMaService getService(AppMaConfig appConfig);

}
