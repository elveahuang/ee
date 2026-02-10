package cc.wdev.platform.system.core.service;

import cc.wdev.platform.BaseTests;
import cc.wdev.platform.system.commons.enums.LabelTypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
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
