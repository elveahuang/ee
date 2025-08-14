package top.baihu.platform.commons.oapis.weixin.service;

import me.chanjar.weixin.cp.api.WxCpMessageService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.WxCpUserService;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import top.baihu.platform.commons.oapis.weixin.config.AppCpConfig;

/**
 * @author elvea
 */
public interface WeiXinCpService {

    WxCpConfigStorage getConfigStorage();

    WxCpConfigStorage getConfigStorage(AppCpConfig appConfig);

    WxCpService getService();

    WxCpService getService(AppCpConfig appConfig);

    WxCpMessageService getMessageService();

    WxCpMessageService getMessageService(AppCpConfig appConfig);

    WxCpUserService getUserService();

    WxCpUserService getUserService(AppCpConfig appConfig);

}
