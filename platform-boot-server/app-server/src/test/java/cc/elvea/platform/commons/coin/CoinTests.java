package cc.elvea.platform.commons.coin;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.extensions.coin.CoinManager;
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
        Assertions.assertNotNull(this.coinManager);
    }

}
