package cn.elvea.platform.commons.core.oapis.dingtalk.service;

import cn.elvea.platform.commons.core.oapis.dingtalk.bean.JsapiSignature;
import cn.elvea.platform.commons.core.oapis.dingtalk.config.AppConfig;

/**
 * 钉钉接口服务
 * <a href="https://open.dingtalk.com/document/org</a>-roadmap">*</a>
 *
 * @author elvea
 * @since 0.0.1
 */
public interface DingTalkService {

    /**
     * 企业自建应用 - 获取访问凭证
     */
    String getAccessToken() throws Exception;

    /**
     * 企业自建应用 - 获取访问凭证
     */
    String getAccessToken(AppConfig config) throws Exception;

    /**
     * 企业自建应用 - 获取临时凭证
     */
    String getJsapiTicket() throws Exception;

    /**
     * 企业自建应用 - 获取临时凭证
     */
    String getJsapiTicket(AppConfig config) throws Exception;

    /**
     * @see JsapiSignature
     */
    JsapiSignature createJsapiSignature(String url) throws Exception;

    /**
     * @see JsapiSignature
     */
    JsapiSignature createJsapiSignature(AppConfig config, String url) throws Exception;

    /**
     *
     */
    void getUserByCode(String code) throws Exception;

    /**
     *
     */
    void getUserByCode(AppConfig config, String code) throws Exception;

}
