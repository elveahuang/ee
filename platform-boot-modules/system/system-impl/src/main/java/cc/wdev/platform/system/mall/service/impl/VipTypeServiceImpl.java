package cc.wdev.platform.system.mall.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.commons.enums.ActiveTypeEnum;
import cc.wdev.platform.commons.enums.StatusTypeEnum;
import cc.wdev.platform.system.mall.domain.entity.VipTypeEntity;
import cc.wdev.platform.system.mall.repository.VipTypeRepository;
import cc.wdev.platform.system.mall.service.VipTypeService;
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
public class VipTypeServiceImpl
    extends BaseCachingEntityService<VipTypeEntity, Long, VipTypeRepository>
    implements VipTypeService {

    /**
     * @see VipTypeService#getTypeList()
     */
    @Override
    public List<VipTypeEntity> getTypeList() {
        VipTypeEntity condition = VipTypeEntity.builder().build();
        condition.setStatus(StatusTypeEnum.ON.getValue());
        condition.setActive(ActiveTypeEnum.ENABLED.getValue());
        Example<VipTypeEntity> example = Example.of(condition);
        return this.repository.findAll(example).stream().toList();
    }

}
