package cc.elvea.platform.system.i18n.service;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.system.i18n.enums.LabelTypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 24.1.0
 */
public class LabelServiceTests extends BaseTests {

    @Autowired
    LabelService labelService;

    @Test
    public void translateTest() {
        labelService.translate();
    }

    @Test
    public void generateTest() throws Exception {
        labelService.generate(LabelTypeEnum.JSON);
        labelService.generate(LabelTypeEnum.PROPERTIES);
    }

}
