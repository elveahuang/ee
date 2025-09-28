package cc.wdev.platform.system.core.service;

import cc.wdev.platform.BaseTests;
import cc.wdev.platform.system.log.domain.entity.OperationLogEntity;
import cc.wdev.platform.system.log.service.OperationLogService;
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
