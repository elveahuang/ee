package cc.elvea.platform.commons.autoconfigure.data.properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(CustomDataProperties.PREFIX)
public class CustomDataProperties {

    public static final String PREFIX = "platform.data.core";

    public static final String JDBC_PREFIX = "platform.data.core.jdbc";

    public static final String AUDITING_PREFIX = "platform.data.core.auditing";

    private boolean enabled = false;

    @NestedConfigurationProperty
    private AuditingConfig auditing = AuditingConfig.builder().build();

    @NestedConfigurationProperty
    private JdbcConfig jdbc = JdbcConfig.builder().build();

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuditingConfig implements Serializable {
        @Builder.Default
        private boolean enabled = false;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JdbcConfig implements Serializable {
        @Builder.Default
        private boolean enabled = false;
    }

}
