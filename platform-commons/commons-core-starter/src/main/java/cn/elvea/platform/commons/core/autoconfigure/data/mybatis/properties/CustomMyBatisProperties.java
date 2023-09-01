package cn.elvea.platform.commons.core.autoconfigure.data.mybatis.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(CustomMyBatisProperties.PREFIX)
public class CustomMyBatisProperties {

    public static final String PREFIX = "platform.data.mybatis";

    private Boolean enabled = Boolean.FALSE;

}
