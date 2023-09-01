package cn.elvea.platform.commons.core.constants;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Locale;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface GlobalConstants {

    /**
     * 全局版本号
     */
    String VERSION = "2023.3.0";

    /**
     * 全局默认编码
     */
    Charset UTF8 = StandardCharsets.UTF_8;

    /**
     * 全局默认编码
     */
    String ENCODING = "UTF-8";

    /**
     * 简体中文
     */
    Locale SIMPLIFIED_CHINESE_LOCALE = Locale.SIMPLIFIED_CHINESE;
    /**
     * 繁体中文
     */
    Locale TRADITIONAL_CHINESE_LOCALE = Locale.TRADITIONAL_CHINESE;

    /**
     * 英文
     */
    Locale US_LOCALE = Locale.US;

    /**
     * 默认语言
     */
    Locale DEFAULT_LOCALE = Locale.getDefault();

    /**
     * 默认缓存周期为2个小时
     */
    Duration DEFAULT_CACHE_DURATION = Duration.ofHours(2);

    /**
     * 缓存记录数
     */
    int MAX_CACHE_SIZE = 1000;

    /**
     * 缓存批处理记录数
     */
    int MAX_BATCH_CACHE_KEY_SIZE = 20;

    /**
     * 批处理记录数
     */
    int DEFAULT_BATCH_SIZE = 1000;

    /**
     * 默认分隔符
     */
    String RELATION_DELIMITER = "~|~";

    /**
     * 默认分隔符
     */
    String STR_DELIMITER_1 = "~|~";

    /**
     * 默认分隔符
     */
    String STR_DELIMITER = ",";

    /**
     * 默认的邮件服务端口
     */
    int DEFAULT_SMTP_PORT = 25;

    /**
     * 默认的邮件服务端口
     */
    int DEFAULT_SMTP__SSL_PORT = 25;

}
