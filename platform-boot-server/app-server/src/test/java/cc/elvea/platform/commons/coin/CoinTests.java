package cc.elvea.platform.commons.coin;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.extensions.coin.CoinManager;
import io.goodforgod.api.etherscan.EtherScanAPI;
import io.goodforgod.api.etherscan.model.Balance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 24.1.0
 */
public class CoinTests extends BaseTests {

    @Autowired
    CoinManager coinManager;

    @Test
    public void bastTest() {
        EtherScanAPI getEtherScanAPI = this.coinManager.getEtherScanAPI();
        Balance balance = getEtherScanAPI.account().balance("0x388C818CA8B9251b393131C08a736A67ccB19297");
        Assertions.assertNotNull(balance);
    }

}
