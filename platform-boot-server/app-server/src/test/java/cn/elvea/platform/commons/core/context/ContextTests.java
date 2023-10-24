package cn.elvea.platform.commons.core.context;

import cn.elvea.platform.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

/**
 * @author dev
 * @since 0.0.1
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
