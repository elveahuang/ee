package cc.elvea.platform.commons.autoconfigure.data.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@ConfigurationProperties(CustomDataSourceProperties.PREFIX)
public class CustomDataSourceProperties {

    public static final String PREFIX = "platform.data.datasource";

    public static final String MASTER_DATASOURCE_PREFIX = "platform.data.datasource.master";

    public static final String SLAVE_DATASOURCE_PREFIX = "platform.data.datasource.slave";

    public static final String JOB_DATASOURCE_PREFIX = "platform.data.datasource.job";

    private boolean enabled = false;

    private boolean masterSlaveEnabled = false;

    @NestedConfigurationProperty
    private CustomDataSourceProperties.Config master = new Config();

    @NestedConfigurationProperty
    private CustomDataSourceProperties.Config slave = new Config();

    @NestedConfigurationProperty
    private CustomDataSourceProperties.Config job = new Config();

    @Data
    public static class Config {

        private boolean enabled = false;

    }

}
