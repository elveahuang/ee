package cn.elvea.platform.commons.core.oapis.lark.service.impl;

import cn.elvea.platform.commons.core.oapis.lark.GlobalCacheManager;
import cn.elvea.platform.commons.core.oapis.lark.GlobalTicketManager;
import cn.elvea.platform.commons.core.oapis.lark.GlobalTokenManager;
import cn.elvea.platform.commons.core.oapis.lark.bean.JsapiSignature;
import cn.elvea.platform.commons.core.oapis.lark.cache.Cache;
import cn.elvea.platform.commons.core.oapis.lark.config.AppConfig;
import cn.elvea.platform.commons.core.oapis.lark.service.LarkService;
import cn.elvea.platform.commons.core.oapis.lark.token.TicketManager;
import cn.elvea.platform.commons.core.oapis.lark.token.TokenManager;
import cn.elvea.platform.commons.core.utils.EncryptUtils;
import cn.elvea.platform.commons.core.utils.StringUtils;
import com.lark.oapi.Client;
import com.lark.oapi.core.Config;

/**
 * @author elvea
 * @since 0.0.1
 */
public class LarkServiceImpl implements LarkService {

    private final TokenManager tokenManager;

    private final TicketManager ticketManager;

    private final Cache cache;

    private AppConfig appConfig;

    public LarkServiceImpl(Cache cache, String cacheKeyPrefix) {
        this.cache = cache;
        this.tokenManager = new TokenManager(cache, cacheKeyPrefix);
        this.ticketManager = new TicketManager(cache, cacheKeyPrefix);
        //
        GlobalTicketManager.setTicketManager(this.ticketManager);
        GlobalTokenManager.setTokenManager(this.tokenManager);
        GlobalCacheManager.setCache(this.cache);
    }

    /**
     * @see LarkService#getClient()
     */
    @Override
    public Client getClient() {
        return this.getClient(appConfig);
    }

    /**
     * @see LarkService#getClient(AppConfig)
     */
    @Override
    public Client getClient(AppConfig appConfig) {
        return Client.newBuilder(appConfig.getAppId(), appConfig.getAppSecret())
                .appType(appConfig.getAppType())
                .tokenCache(cache)
                .build();
    }

    /**
     * @see LarkService#getAppAccessToken()
     */
    @Override
    public Config getConfig() {
        return this.getConfig(appConfig);
    }

    /**
     * @see LarkService#getAppAccessToken()
     */
    @Override
    public Config getConfig(AppConfig appConfig) {
        Config config = new Config();
        config.setAppType(appConfig.getAppType());
        config.setAppId(appConfig.getAppId());
        config.setAppSecret(appConfig.getAppSecret());
        config.setCache(this.cache);
        return config;
    }

    /**
     * @see LarkService#getAppAccessToken()
     */
    @Override
    public String getAppAccessToken() throws Exception {
        return this.getAppAccessToken(this.appConfig);
    }

    /**
     * @see LarkService#getAppAccessToken(AppConfig)
     */
    @Override
    public String getAppAccessToken(AppConfig appConfig) throws Exception {
        return this.tokenManager.getAppAccessToken(this.appConfig.getConfig());
    }

    /**
     * @see LarkService#getTenantAccessToken()
     */
    @Override
    public String getTenantAccessToken() throws Exception {
        return this.getTenantAccessToken(this.appConfig);
    }

    /**
     * @see LarkService#getTenantAccessToken(AppConfig)
     */
    @Override
    public String getTenantAccessToken(AppConfig config) throws Exception {
        return this.tokenManager.getTenantAccessToken(this.appConfig.getConfig(), null);
    }

    /**
     * @see LarkService#getJsapiTicket()
     */
    @Override
    public String getJsapiTicket() throws Exception {
        return this.getJsapiTicket(this.appConfig);
    }

    /**
     * @see LarkService#getJsapiTicket(AppConfig)
     */
    @Override
    public String getJsapiTicket(AppConfig config) throws Exception {
        return this.ticketManager.getJsapiTicket(config);
    }

    /**
     * @see LarkService#createJsapiSignature(String)
     */
    @Override
    public JsapiSignature createJsapiSignature(String url) throws Exception {
        return this.createJsapiSignature(this.appConfig, url);
    }

    /**
     * @see LarkService#createJsapiSignature(AppConfig, String)
     */
    @Override
    public JsapiSignature createJsapiSignature(AppConfig config, String url) throws Exception {
        long timestamp = System.currentTimeMillis();
        String noncestr = StringUtils.randomAlphabetic(12);
        String jsapiTicket = getJsapiTicket(config);
        String signature = EncryptUtils.sha(
                "jsapi_ticket=" + jsapiTicket,
                "noncestr=" + noncestr,
                "timestamp=" + timestamp,
                "url=" + url
        );
        JsapiSignature jsapiSignature = new JsapiSignature();
        jsapiSignature.setAppId(config.getAppId());
        jsapiSignature.setTimestamp(timestamp);
        jsapiSignature.setNonceStr(noncestr);
        jsapiSignature.setUrl(url);
        jsapiSignature.setSignature(signature);
        return jsapiSignature;
    }

    public Cache getCache() {
        return cache;
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }

    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

}
