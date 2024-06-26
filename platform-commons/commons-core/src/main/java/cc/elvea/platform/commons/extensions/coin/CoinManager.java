package cc.elvea.platform.commons.extensions.coin;

import io.goodforgod.api.etherscan.EtherScanAPI;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface CoinManager {

    /**
     * @return {@see EtherScanConfig}
     */
    EtherScanAPI getEtherScanAPI();

    /**
     * @return {@see EtherScanConfig}
     */
    EtherScanAPI getEtherScanAPI(CoinConfig config);

}
