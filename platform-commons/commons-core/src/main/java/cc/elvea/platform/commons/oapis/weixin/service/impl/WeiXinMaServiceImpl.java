package cc.elvea.platform.commons.oapis.weixin.service.impl;

import cc.elvea.platform.commons.core.cache.service.CacheService;
import cc.elvea.platform.commons.oapis.weixin.config.AppMaConfig;
import cc.elvea.platform.commons.oapis.weixin.service.WeiXinMaService;
import cc.elvea.platform.commons.oapis.weixin.storage.WxMaCacheConfigStorage;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import lombok.Getter;
import lombok.Setter;

/**
 * @author elvea
 */
@Setter
@Getter
public class WeiXinMaServiceImpl implements WeiXinMaService {

    private final CacheService cacheService;

    private final String cacheKeyPrefix;

    private AppMaConfig appConfig;

    public WeiXinMaServiceImpl(CacheService cacheService, String cacheKeyPrefix) {
        this.cacheService = cacheService;
        this.cacheKeyPrefix = cacheKeyPrefix;
    }

    /**
     * @see WeiXinMaService#getConfigStorage()
     */
    @Override
    public WxMaConfig getConfigStorage() {
        return this.getConfigStorage(this.appConfig);
    }

    /**
     * @see WeiXinMaService#getConfigStorage(AppMaConfig)
     */
    @Override
    public WxMaConfig getConfigStorage(AppMaConfig appMaConfig) {
        WxMaCacheConfigStorage config = new WxMaCacheConfigStorage(cacheService, cacheKeyPrefix);
        config.setAppid(appMaConfig.getAppId());
        config.setSecret(appMaConfig.getAppSecret());
        return config;
    }

    /**
     * @see WeiXinMaService#getService()
     */
    @Override
    public WxMaService getService() {
        return this.getService(this.appConfig);
    }

    /**
     * @see WeiXinMaService#getService(AppMaConfig)
     */
    @Override
    public WxMaService getService(AppMaConfig appMaConfig) {
        WxMaService wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(getConfigStorage(appMaConfig));
        return wxMaService;
    }

}
