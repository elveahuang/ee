package cc.elvea.platform.commons.autoconfigure.extensions.properties;

import cc.elvea.platform.commons.extensions.coin.CoinConfig;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(CoinProperties.PREFIX)
public class CoinProperties {

    public static final String PREFIX = "platform.coin";

    private boolean enabled = false;

    @NestedConfigurationProperty
    private CoinConfig.EtherScan etherscan = CoinConfig.EtherScan.builder().build();

    @NestedConfigurationProperty
    private CoinConfig.CoinGecko coinGecko = CoinConfig.CoinGecko.builder().build();

    @NestedConfigurationProperty
    private CoinConfig.CoinMarketCap coinMarketCap = CoinConfig.CoinMarketCap.builder().build();

    @NestedConfigurationProperty
    private CoinConfig.CryptoRank cryptoRank = CoinConfig.CryptoRank.builder().build();

    @NestedConfigurationProperty
    private CoinConfig.Alchemy alchemy = CoinConfig.Alchemy.builder().build();

}
