package top.baihu.platform.commons.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;

import java.util.Locale;

/**
 * @author elvea
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
