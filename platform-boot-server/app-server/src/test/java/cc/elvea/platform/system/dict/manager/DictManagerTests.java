package cc.elvea.platform.system.dict.manager;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.system.commons.constants.SystemDictConstants;
import cc.elvea.platform.system.core.manager.DictManager;
import cc.elvea.platform.system.core.model.request.DictTypeRequest;
import cc.elvea.platform.system.core.model.vo.DictTypeVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class DictManagerTests extends BaseTests {

    @Autowired
    DictManager dictManager;

    @Test
    public void baseTest() {
        DictTypeRequest request = DictTypeRequest.builder().type(SystemDictConstants.BANNER).build();
        DictTypeVo vo = this.dictManager.getDictType(request);
        Assertions.assertNotNull(vo);
    }

}
