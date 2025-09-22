package cc.wdev.platform.commons.autoconfigure.core.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(TenantProperties.PREFIX)
public class TenantProperties implements Serializable {

    public static final String PREFIX = "platform.tenant";

    private boolean enabled = false;

    private List<String> ignoreTables = List.of();

}
