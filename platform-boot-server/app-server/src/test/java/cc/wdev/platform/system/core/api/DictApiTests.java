package cc.wdev.platform.system.core.feign;

import cc.wdev.platform.BaseTests;
import cc.wdev.platform.system.commons.constants.SystemDictConstants;
import cc.wdev.platform.system.core.api.DictApi;
import cc.wdev.platform.system.core.domain.request.DictTypeRequest;
import cc.wdev.platform.system.core.domain.vo.DictTypeVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class DictApiTests extends BaseTests {

    @Autowired
    DictApi dictApi;

    @Test
    public void baseTest() {
        DictTypeRequest request = DictTypeRequest.builder().type(SystemDictConstants.BANNER).build();
        DictTypeVo vo = this.dictApi.getDictType(request);
        Assertions.assertNotNull(vo);
    }

}
