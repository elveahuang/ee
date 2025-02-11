package cc.elvea.platform.system.log.service;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.system.log.model.entity.OperationLogEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class OperationLogServiceTests extends BaseTests {

    @Autowired
    OperationLogService operationLogService;

    @Test
    public void baseTest() throws Exception {
        OperationLogEntity entity = OperationLogEntity.builder().build();
        this.operationLogService.save(entity);
    }

}
