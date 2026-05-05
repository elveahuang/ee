package cc.wdev.platform.system.mall.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.commons.enums.ActiveTypeEnum;
import cc.wdev.platform.commons.enums.StatusTypeEnum;
import cc.wdev.platform.system.mall.domain.entity.PayTypeEntity;
import cc.wdev.platform.system.mall.repository.PayTypeRepository;
import cc.wdev.platform.system.mall.service.PayTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class PayTypeServiceImpl
    extends BaseCachingEntityService<PayTypeEntity, Long, PayTypeRepository>
    implements PayTypeService {

    /**
     * @see PayTypeService#getTypeList()
     */
    @Override
    public List<PayTypeEntity> getTypeList() {
        PayTypeEntity condition = PayTypeEntity.builder().build();
        condition.setStatus(StatusTypeEnum.ON.getValue());
        condition.setActive(ActiveTypeEnum.ENABLED.getValue());
        Example<PayTypeEntity> example = Example.of(condition);
        return this.repository.findAll(example).stream().toList();
    }

}
