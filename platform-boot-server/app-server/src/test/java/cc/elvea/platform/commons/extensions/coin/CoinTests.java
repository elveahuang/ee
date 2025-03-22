package cc.elvea.platform.commons.extensions.coin;

import cc.elvea.platform.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class CoinTests extends BaseTests {

    @Autowired
    CoinManager coinManager;

    @Test
    public void bastTest() {
        Assertions.assertNotNull(this.coinManager);
    }

}
