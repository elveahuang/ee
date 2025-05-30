package cc.elvea.platform.commons.autoconfigure.core.properties;

import cc.elvea.platform.commons.constants.DateTimeConstants;
import cc.elvea.platform.commons.constants.GlobalConstants;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;
import java.time.ZoneId;

/**
 * @author elvea
 */
@Data
@ConfigurationProperties(CoreProperties.PREFIX)
public class CoreProperties implements Serializable {

    public static final String PREFIX = "platform";

    /**
     * 应用标题
     */
    private String applicationName = GlobalConstants.NAME;

    /**
     * 应用版本
     */
    private String applicationVersion = GlobalConstants.VERSION;

    /**
     * 调试模式
     */
    @NestedConfigurationProperty
    private Debug debug = Debug.builder().build();

    /**
     * 消息队列
     */
    @NestedConfigurationProperty
    private Amqp amqp = Amqp.builder().build();

    /**
     * 应用主页配置
     */
    @NestedConfigurationProperty
    private Home home = Home.builder().build();

    /**
     * 指定用户时区
     */
    private ZoneId userZoneId = DateTimeConstants.ZONE_ID_DEFAULT;

    /**
     * 指定系统时区
     */
    private ZoneId systemZoneId = DateTimeConstants.ZONE_ID_DEFAULT;

    /**
     * 指定系统语言
     */
    private String language = "zh_CN";

    @Data
    @Builder
    public static class Debug {
        /**
         * 是否启用调试模式
         */
        @Builder.Default
        private boolean enabled = false;
    }

    @Data
    @Builder
    public static class Amqp {
        /**
         * 是否启用消息队列
         */
        @Builder.Default
        private boolean enabled = true;
    }

    @Data
    @Builder
    public static class Home {
        /**
         * 默认主页
         */
        private String main;
        /**
         * 管理端主页
         */
        private String admin;
        /**
         * 电脑端主页
         */
        private String webapp;
        /**
         * 移动端主页
         */
        private String mobile;
    }

}
