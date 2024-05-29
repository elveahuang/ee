package cc.elvea.platform.commons.autoconfigure.extensions.coin.properties;

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
@ConfigurationProperties(CoinProperties.PREFIX)
public class CoinProperties {

    public static final String PREFIX = "platform.coin";

    private boolean enabled = false;

    @NestedConfigurationProperty
    private CoinProperties.EtherScan etherscan = CoinProperties.EtherScan.builder().build();

    @Data
    @Builder
    public static class EtherScan {
        private String apiKey;
    }

}
