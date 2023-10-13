package cn.elvea.platform.commons.core.autoconfigure.ds.properties;

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
@ConfigurationProperties(CustomDataSourceProperties.PREFIX)
public class CustomDataSourceProperties {

    public static final String PREFIX = "platform.datasource";

    public static final String DS_MASTER_PREFIX = "platform.datasource.master";

    public static final String DS_SLAVE_PREFIX = "platform.datasource.slave";

    public static final String DS_JOB_PREFIX = "platform.datasource.job";

    private Boolean enabled = Boolean.FALSE;

    private Boolean masterSlaveEnabled = Boolean.FALSE;

    @NestedConfigurationProperty
    private CustomDataSourceProperties.Master master = CustomDataSourceProperties.Master.builder().build();

    @NestedConfigurationProperty
    private CustomDataSourceProperties.Slave slave = CustomDataSourceProperties.Slave.builder().build();

    @NestedConfigurationProperty
    private CustomDataSourceProperties.Job job = CustomDataSourceProperties.Job.builder().build();

    @Data
    @Builder
    public static class Master {
        @Builder.Default
        private Boolean enabled = Boolean.FALSE;
    }

    @Data
    @Builder
    public static class Slave {
        @Builder.Default
        private Boolean enabled = Boolean.FALSE;
    }

    @Data
    @Builder
    public static class Job {
        @Builder.Default
        private Boolean enabled = Boolean.FALSE;
    }

}
