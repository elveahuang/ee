package cn.elvea.platform.commons.core.autoconfigure.scheduling.quartz.properties;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(CustomQuartzProperties.PREFIX)
public class CustomQuartzProperties {

    public static final String PREFIX = "platform.quartz";

    public static final String CLUSTER_PREFIX = "platform.quartz.cluster";

    private Boolean enabled = Boolean.FALSE;

    @NestedConfigurationProperty
    private CustomQuartzProperties.Cluster cluster = CustomQuartzProperties.Cluster.builder().build();

    @Data
    @Builder
    public static class Cluster {
        @Builder.Default
        private Boolean enabled = Boolean.FALSE;
    }

}
