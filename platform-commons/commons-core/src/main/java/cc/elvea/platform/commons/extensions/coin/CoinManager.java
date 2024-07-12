package cc.elvea.platform.commons.extensions.coin;

import io.goodforgod.api.etherscan.EtherScanAPI;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface CoinManager {

    /**
     * @return {@see CoinConfig}
     */
    CoinConfig config();

    /**
     * @return {@see EtherScanAPI}
     */
    EtherScanAPI getEtherScanAPI();

    /**
     * @return {@see EtherScanAPI}
     */
    EtherScanAPI getEtherScanAPI(CoinConfig config);

}
