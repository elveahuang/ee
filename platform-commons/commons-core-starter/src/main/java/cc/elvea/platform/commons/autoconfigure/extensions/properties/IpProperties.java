package cc.elvea.platform.commons.autoconfigure.extensions.properties;

import cc.elvea.platform.commons.extensions.ip.LocationEnum;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(IpProperties.PREFIX)
public class IpProperties {

    public static final String PREFIX = "platform.ip";

    private boolean enabled = true;

    @NestedConfigurationProperty
    private IpRegion region = IpRegion.builder().build();

    @NestedConfigurationProperty
    private GeoLite geoLite = GeoLite.builder().build();

    @Data
    @Builder
    public static class GeoLite {
        @Builder.Default
        private boolean enabled = false;
        @Builder.Default
        private LocationEnum location = LocationEnum.CLASSPATH;
        @Builder.Default
        private String path = "ip/GeoLite2-City.mmdb";
    }

    @Data
    @Builder
    public static class IpRegion {
        @Builder.Default
        private boolean enabled = false;
        @Builder.Default
        private LocationEnum location = LocationEnum.CLASSPATH;
        @Builder.Default
        private String path = "ip/ip2region.xdb";
    }

}
