package cn.elvea.platform.commons.core.oapis.weixin.service;

import cn.elvea.platform.commons.core.oapis.weixin.config.AppCpConfig;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface WeiXinCpService {

    WxCpConfigStorage getConfigStorage();

    WxCpConfigStorage getConfigStorage(AppCpConfig appConfig);

    WxCpService getService();

    WxCpService getService(AppCpConfig appConfig);

}
