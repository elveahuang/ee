package cn.elvea.platform.commons.core.autoconfigure.cache.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = CustomCaffeineProperties.PREFIX)
public class CustomCaffeineProperties {

    public static final String PREFIX = "platform.cache.caffeine";

    private Boolean enabled = Boolean.FALSE;

    private Boolean springCacheEnabled = Boolean.FALSE;

}
