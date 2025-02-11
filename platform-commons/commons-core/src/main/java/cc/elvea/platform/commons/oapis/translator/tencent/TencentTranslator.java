package cc.elvea.platform.commons.oapis.translator.tencent;

import cc.elvea.platform.commons.oapis.translator.Translator;
import cc.elvea.platform.commons.oapis.translator.TranslatorConverter;
import cc.elvea.platform.commons.utils.GsonUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.tmt.v20180321.TmtClient;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateRequest;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

/**
 * 腾讯云机器翻译
 *
 * @author elvea
 */
@Slf4j
public class TencentTranslator implements Translator {

    private final Config config;

    private final TranslatorConverter converter = new TencentTranslatorConverter();

    public TencentTranslator(Config config) {
        this.config = config;
    }

    /**
     * @see Translator#translate(String, String, String)
     */
    @Override
    public String translate(String sourceLang, String targetLang, String text) {
        String sourceServerLang = this.converter.convert(sourceLang);
        String targetServerLang = this.converter.convert(targetLang);

        try {
            log.info("TencentTranslator translate from {} to {} start. text - [{}].", sourceLang, targetLang, text);

            TmtClient client = createClient(this.config);

            TextTranslateRequest request = new TextTranslateRequest();
            request.setSource(sourceServerLang);
            request.setTarget(targetServerLang);
            request.setSourceText(text);

            TextTranslateResponse response = client.TextTranslate(request);

            log.info("TencentTranslator translate from {} to {}. response - [{}].", sourceLang, targetLang, GsonUtils.toJson(response));
            if (StringUtils.isNotEmpty(response.getTargetText())) {
                String result = response.getTargetText();
                log.info("TencentTranslator translate from {} to {} success. text - [{}]  result - [{}]", sourceLang, targetLang, text, result);
                return result;
            }
        } catch (Exception e) {
            log.error("TencentTranslator translate from {} to {} fail. error - [{}].", sourceLang, targetLang, e.getMessage(), e);
        }
        return "";
    }

    /**
     * 阿里云翻译器配置
     */
    @Data
    @NoArgsConstructor
    public static class Config {
        private Boolean enabled = Boolean.FALSE;
        private String endpoint = "mt.aliyuncs.com";
        private String region;
        private String appId;
        private String secretId;
        private String secretKey;
    }

    @NonNull
    private static TmtClient createClient(Config config) throws Exception {
        Credential credential = new Credential(config.getSecretId(), config.getSecretKey());

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setReqMethod("POST");
        httpProfile.setConnTimeout(60);
        httpProfile.setEndpoint(config.getEndpoint());

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        return new TmtClient(credential, config.getRegion(), clientProfile);
    }

    public static class TencentTranslatorConverter implements TranslatorConverter {

        /**
         * @see TranslatorConverter#convert(String)
         */
        @Override
        public String convert(String language) {
            language = StringUtils.isNotEmpty(language) ? language.trim().toLowerCase() : "";
            return switch (language) {
                case "zh_cn", "zh" -> "zh";
                case "zh_tw" -> "zh-tw";
                case "en_us", "en_uk", "en" -> "en";
                case "ja", "jp" -> "ja";
                case "fr" -> "fr";
                default -> language;
            };
        }

    }

}
