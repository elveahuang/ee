package cc.elvea.platform.commons.autoconfigure.job.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@ConfigurationProperties(CustomQuartzProperties.PREFIX)
public class CustomQuartzProperties {

    public static final String PREFIX = "platform.quartz";

    /**
     * 是否开启定时任务
     */
    private boolean enabled = true;

}
