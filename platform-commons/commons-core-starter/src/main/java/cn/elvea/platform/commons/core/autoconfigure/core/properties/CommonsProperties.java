package cn.elvea.platform.commons.core.autoconfigure.core.properties;

import cn.elvea.platform.commons.core.constants.DateTimeConstants;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;
import java.time.ZoneId;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(CommonsProperties.PREFIX)
public class CommonsProperties implements Serializable {

    public static final String PREFIX = "platform";

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

        @Builder.Default
        private Boolean enabled = Boolean.FALSE;

    }

    @Data
    @Builder
    public static class Amqp {

        @Builder.Default
        private Boolean enabled = Boolean.FALSE;

    }

}
