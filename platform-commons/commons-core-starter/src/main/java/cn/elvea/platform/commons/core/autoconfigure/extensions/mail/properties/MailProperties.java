package cn.elvea.platform.commons.core.autoconfigure.extensions.mail.properties;

import cn.elvea.platform.commons.core.extensions.mail.MailServer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(MailProperties.PREFIX)
public class MailProperties {

    public static final String PREFIX = "platform.mail";

    private Boolean enabled = Boolean.FALSE;

    @NestedConfigurationProperty
    private MailServer server = new MailServer();

}
