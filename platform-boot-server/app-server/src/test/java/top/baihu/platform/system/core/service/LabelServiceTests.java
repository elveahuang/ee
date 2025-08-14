package top.baihu.platform.system.core.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;
import top.baihu.platform.system.commons.enums.LabelTypeEnum;

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
