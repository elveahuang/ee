package cc.elvea.platform.system.dict.api;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.system.commons.constants.SystemDictConstants;
import cc.elvea.platform.system.dict.model.request.DictTypeRequest;
import cc.elvea.platform.system.dict.model.vo.DictTypeVo;
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
