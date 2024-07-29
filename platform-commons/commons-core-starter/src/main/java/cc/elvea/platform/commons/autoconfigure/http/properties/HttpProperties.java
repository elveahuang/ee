package cc.elvea.platform.commons.autoconfigure.http.properties;

import cc.elvea.platform.commons.http.HttpDebug;
import cc.elvea.platform.commons.http.HttpProxy;
import cc.elvea.platform.commons.http.HttpType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(HttpProperties.PREFIX)
public class HttpProperties {

    public static final String PREFIX = "platform.http";

    private boolean enabled = true;

    private HttpType type = HttpType.OKHTTP;

    @NestedConfigurationProperty
    private HttpDebug debug = HttpDebug.builder().build();

    @NestedConfigurationProperty
    private HttpProxy proxy = HttpProxy.builder().build();

}
