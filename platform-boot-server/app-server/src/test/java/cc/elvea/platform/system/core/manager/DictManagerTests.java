package cc.elvea.platform.system.core.manager;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.system.commons.constants.SystemDictConstants;
import cc.elvea.platform.system.core.domain.request.DictTypeRequest;
import cc.elvea.platform.system.core.domain.vo.DictTypeVo;
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
