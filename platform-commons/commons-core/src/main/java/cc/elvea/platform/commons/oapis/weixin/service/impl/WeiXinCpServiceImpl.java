package cc.elvea.platform.commons.oapis.weixin.service.impl;

import cc.elvea.platform.commons.core.cache.service.CacheService;
import cc.elvea.platform.commons.oapis.weixin.config.AppCpConfig;
import cc.elvea.platform.commons.oapis.weixin.service.WeiXinCpService;
import cc.elvea.platform.commons.oapis.weixin.storage.WxCpCacheConfigStorage;
import cc.elvea.platform.commons.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.api.WxCpMessageService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.WxCpUserService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;

/**
 * @author elvea
 */
@Getter
@Setter
public class WeiXinCpServiceImpl implements WeiXinCpService {

    private final CacheService cacheService;

    private final String cacheKeyPrefix;

    private AppCpConfig appConfig;

    public WeiXinCpServiceImpl(CacheService cacheService, String cacheKeyPrefix) {
        this.cacheService = cacheService;
        this.cacheKeyPrefix = cacheKeyPrefix;
    }

    /**
     * @see WeiXinCpService#getConfigStorage()
     */
    @Override
    public WxCpConfigStorage getConfigStorage() {
        return this.getConfigStorage(this.appConfig);
    }

    /**
     * @see WeiXinCpService#getConfigStorage(AppCpConfig)
     */
    @Override
    public WxCpConfigStorage getConfigStorage(AppCpConfig appCpConfig) {
        WxCpCacheConfigStorage config = new WxCpCacheConfigStorage(cacheService, cacheKeyPrefix);
        config.setCorpId(appCpConfig.getCorpId());
        config.setCorpSecret(appCpConfig.getCorpSecret());
        config.setAgentId(appCpConfig.getAgentId());
        if (StringUtils.isNotEmpty(appCpConfig.getToken())) {
            config.setToken(appCpConfig.getToken());
        }
        return config;
    }

    /**
     * @see WeiXinCpService#getService()
     */
    @Override
    public WxCpService getService() {
        return this.getService(this.appConfig);
    }

    /**
     * @see WeiXinCpService#getService(AppCpConfig)
     */
    @Override
    public WxCpService getService(AppCpConfig appCpConfig) {
        WxCpService wxCpService = new WxCpServiceImpl();
        wxCpService.setWxCpConfigStorage(getConfigStorage(appCpConfig));
        return wxCpService;
    }

    /**
     * @see WeiXinCpService#getMessageService(AppCpConfig)
     */
    @Override
    public WxCpMessageService getMessageService() {
        return this.getMessageService(this.getAppConfig());
    }

    /**
     * @see WeiXinCpService#getMessageService(AppCpConfig)
     */
    @Override
    public WxCpMessageService getMessageService(AppCpConfig appConfig) {
        return this.getService(appConfig).getMessageService();
    }

    /**
     * @see WeiXinCpService#getUserService(AppCpConfig)
     */
    @Override
    public WxCpUserService getUserService() {
        return this.getUserService(this.getAppConfig());
    }

    /**
     * @see WeiXinCpService#getUserService(AppCpConfig)
     */
    @Override
    public WxCpUserService getUserService(AppCpConfig appConfig) {
        return this.getService(appConfig).getUserService();
    }

}
