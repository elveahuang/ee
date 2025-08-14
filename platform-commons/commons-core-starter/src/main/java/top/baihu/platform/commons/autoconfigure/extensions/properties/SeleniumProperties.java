package top.baihu.platform.commons.autoconfigure.extensions.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import top.baihu.platform.commons.core.http.HttpProxy;
import top.baihu.platform.commons.enums.SeleniumDriverTypeEnum;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(SeleniumProperties.PREFIX)
public class SeleniumProperties {

    public static final String PREFIX = "platform.selenium";

    private boolean enabled = false;

    private SeleniumDriverTypeEnum driverType = SeleniumDriverTypeEnum.EDGE;

    private String driverVersion;

    @NestedConfigurationProperty
    private HttpProxy proxy = HttpProxy.builder().build();

}
