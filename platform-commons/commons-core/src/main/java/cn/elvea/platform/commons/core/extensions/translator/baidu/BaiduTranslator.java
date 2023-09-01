package cn.elvea.platform.commons.core.extensions.translator.baidu;

import cn.elvea.platform.commons.core.extensions.translator.Translator;
import cn.elvea.platform.commons.core.extensions.translator.TranslatorConverter;
import cn.elvea.platform.commons.core.utils.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 百度翻译器
 * <a href="https://api.fanyi.baidu.com/">...</a>
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class BaiduTranslator implements Translator {

    private final Config config;

    private final TranslatorConverter converter = new BaiduTranslator.BaiduTranslatorConverter();

    public BaiduTranslator(Config config) {
        this.config = config;
    }

    /**
     * @see Translator#translate(String, String, String)
     */
    @Override
    public String translate(String sourceLang, String targetLang, String text) {
        String sourceServerLang = this.converter.convert(sourceLang);
        String targetServerLang = this.converter.convert(targetLang);

        String salt = this.generateSalt();
        String sign = this.generateSign(salt, text);
        Map<String, String> params = new HashMap<>();
        params.put("from", sourceServerLang);
        params.put("to", targetServerLang);
        params.put("q", text);
        params.put("appid", this.config.getAppId());
        params.put("salt", salt);
        params.put("sign", sign);

        log.info("BaiduTranslator translate from {} to {} start. text - [{}].", sourceLang, targetLang, text);
        try {
            String responseText = HttpComponentsUtils.get(this.config.getEndpoint(), params);
            Response response = JacksonUtils.toObject(responseText, Response.class);

            log.info("BaiduTranslator translate from {} to {}. response - [{}].", sourceLang, targetLang, GsonUtils.toJson(response));
            if (response != null && response.getError_code() != null && response.getError_code() > 0) {
                log.error("BaiduTranslator translate from {} to {} fail. error - [{}].", sourceLang, targetLang, response.getError_code());
            } else {
                String result = (response != null && CollectionUtils.isNotEmpty(response.getTrans_result())) ?
                        response.getTrans_result().get(0).getDst() : "";
                log.info("BaiduTranslator translate from {} to {} success. text - [{}]  result - [{}]", sourceLang, targetLang, text, result);
                return result;
            }
        } catch (Exception e) {
            log.error("BaiduTranslator translate from {} to {} fail. error - [{}].", sourceLang, targetLang, e.getMessage(), e);
        }
        return "";
    }

    /**
     * 获取十位随机数
     */
    public String generateSalt() {
        return RandomStringUtils.randomNumeric(10);
    }

    /**
     * 获取十位随机数
     */
    public String generateSign(String salt, String text) {
        return MD5Utils.md5(this.config.getAppId() + text + salt + this.config.getSecretKey());
    }

    /**
     * 百度翻译器配置
     */
    @Data
    @NoArgsConstructor
    public static class Config {
        private Boolean enabled = Boolean.FALSE;
        private String endpoint = "https://fanyi-api.baidu.com/api/trans/vip/translate";
        private String appId = "";
        private String secretKey = "";
    }

    /**
     * 响应结果
     */
    @Data
    @NoArgsConstructor
    public static class Response {
        /**
         * 源语言
         */
        private String from;
        /**
         * 目标语言
         */
        private String to;
        /**
         * 翻译结果
         */
        private List<TransResult> trans_result;
        /**
         * 错误码
         */
        private Integer error_code;
    }

    /**
     * 响应结果
     */
    @Data
    @NoArgsConstructor
    public static class TransResult {
        /**
         * 原文
         */
        private String src;
        /**
         * 译文
         */
        private String dst;
    }

    /**
     * 语言转换
     * <a href="https://fanyi-api.baidu.com/doc/21">...</a>
     */
    public static class BaiduTranslatorConverter implements TranslatorConverter {

        /**
         * @see TranslatorConverter#convert(String)
         */
        @Override
        public String convert(String language) {
            language = StringUtils.isNotEmpty(language) ? language.trim().toLowerCase() : "";
            return switch (language) {
                case "zh_cn", "zh" -> "zh";
                case "zh_tw" -> "cht";
                case "en_us", "en_uk", "en" -> "en";
                case "ja", "jp" -> "jp";
                case "fr" -> "fra";
                default -> language;
            };
        }

    }

    /**
     * MD5
     */
    public static class MD5Utils {

        private static final char[] hexDigits = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'
        };

        /**
         * 获得一个字符串的MD5值
         *
         * @param input 输入的字符串
         * @return 输入字符串的MD5值
         */
        public static String md5(String input) {
            if (input == null) {
                return null;
            }

            try {
                // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                // 输入的字符串转换成字节数组
                byte[] inputByteArray = input.getBytes(StandardCharsets.UTF_8);
                // inputByteArray是输入字符串转换得到的字节数组
                messageDigest.update(inputByteArray);
                // 转换并返回结果，也是字节数组，包含16个元素
                byte[] resultByteArray = messageDigest.digest();
                // 字符数组转换成字符串返回
                return byteArrayToHex(resultByteArray);
            } catch (Exception e) {
                return null;
            }
        }

        private static String byteArrayToHex(byte[] byteArray) {
            // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
            char[] resultCharArray = new char[byteArray.length * 2];
            // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
            int index = 0;
            for (byte b : byteArray) {
                resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
                resultCharArray[index++] = hexDigits[b & 0xf];
            }
            // 字符数组组合成字符串返回
            return new String(resultCharArray);
        }

    }

}
