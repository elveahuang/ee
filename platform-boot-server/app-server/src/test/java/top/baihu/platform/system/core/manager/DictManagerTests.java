package top.baihu.platform.system.core.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;
import top.baihu.platform.system.commons.constants.SystemDictConstants;
import top.baihu.platform.system.core.domain.request.DictTypeRequest;
import top.baihu.platform.system.core.domain.vo.DictTypeVo;

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
