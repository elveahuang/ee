package cn.elvea.platform.commons.core.autoconfigure.extensions.swagger.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@Component
@ConfigurationProperties("swagger")
public class SwaggerProperties {

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
