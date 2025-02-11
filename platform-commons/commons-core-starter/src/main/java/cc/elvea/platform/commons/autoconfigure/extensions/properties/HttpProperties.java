package cc.elvea.platform.commons.autoconfigure.extensions.properties;

import cc.elvea.platform.commons.extensions.http.HttpConfig;
import cc.elvea.platform.commons.extensions.http.enums.HttpClientTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(HttpProperties.PREFIX)
public class HttpProperties {

    public static final String PREFIX = "platform.http";

    private boolean enabled = true;

    private HttpClientTypeEnum type = HttpClientTypeEnum.OKHTTP;

    @NestedConfigurationProperty
    private HttpConfig.HttpDebug debug = HttpConfig.HttpDebug.builder().build();

    @NestedConfigurationProperty
    private HttpConfig.HttpProxy proxy = HttpConfig.HttpProxy.builder().build();

}
