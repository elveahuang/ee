package cc.elvea.platform.system.core.service;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.system.commons.enums.LabelTypeEnum;
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
