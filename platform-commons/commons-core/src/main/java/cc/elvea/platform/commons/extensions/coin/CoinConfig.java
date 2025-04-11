package cc.elvea.platform.commons.extensions.coin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoinConfig implements Serializable {

    @Builder.Default
    private EtherScan etherScan = EtherScan.builder().build();

    @Builder.Default
    private CoinGecko coinGecko = CoinGecko.builder().build();

    @Builder.Default
    private CryptoRank cryptoRank = CryptoRank.builder().build();

    @Builder.Default
    private CoinMarketCap coinMarketCap = CoinMarketCap.builder().build();

    @Builder.Default
    private Alchemy alchemy = Alchemy.builder().build();

    @Data
    @Builder
    public static class EtherScan {
        private String apiKey;
    }

    @Data
    @Builder
    public static class CoinGecko {
        private String apiKey;
    }

    @Data
    @Builder
    public static class CoinMarketCap {
        private String apiKey;
    }

    @Data
    @Builder
    public static class CryptoRank {
        private String apiKey;
    }

    @Data
    @Builder
    public static class Alchemy {
        private String apiKey;
    }

}
