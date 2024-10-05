package cc.elvea.platform.commons.context;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.core.Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

/**
 * @author elvea
 * @since 24.1.0
 */
public class ContextTests extends BaseTests {

    @Autowired
    Context context;

    @Test
    public void bastTest() throws Exception {
        Assertions.assertNotNull(context);
        String text = this.context.getMessage("system__label__app_title", Locale.CHINA);
        Assertions.assertNotNull(text);
    }

}
