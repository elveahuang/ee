package top.baihu.platform.commons.autoconfigure.extensions.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import top.baihu.platform.commons.core.http.HttpDebug;
import top.baihu.platform.commons.core.http.HttpProxy;
import top.baihu.platform.commons.core.http.enums.HttpClientTypeEnum;

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
    private HttpDebug debug = HttpDebug.builder().build();

    @NestedConfigurationProperty
    private HttpProxy proxy = HttpProxy.builder().build();

}
