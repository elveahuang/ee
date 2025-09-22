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
@ConfigurationProperties(TenancyProperties.PREFIX)
public class TenancyProperties implements Serializable {

    public static final String PREFIX = "platform.tenancy";

    private boolean enabled = false;

    private List<String> ignoreTables = List.of();

}
