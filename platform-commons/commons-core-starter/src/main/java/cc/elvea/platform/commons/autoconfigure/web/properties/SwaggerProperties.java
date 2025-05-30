package cc.elvea.platform.commons.autoconfigure.web.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(SwaggerProperties.PREFIX)
public class SwaggerProperties {

    public static final String PREFIX = "platform.swagger";

    /**
     * 是否开启
     */
    private boolean enabled = false;

    /**
     * 标题
     **/
    private String title = "";

    /**
     * 描述
     **/
    private String description = "";

    /**
     * 版本
     **/
    private String version = "";

}
