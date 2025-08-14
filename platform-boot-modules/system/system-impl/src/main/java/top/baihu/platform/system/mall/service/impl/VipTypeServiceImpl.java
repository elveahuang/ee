package top.baihu.platform.system.mall.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.system.commons.enums.ActiveTypeEnum;
import top.baihu.platform.system.commons.enums.StatusTypeEnum;
import top.baihu.platform.system.mall.domain.entity.VipTypeEntity;
import top.baihu.platform.system.mall.repository.VipTypeRepository;
import top.baihu.platform.system.mall.service.VipTypeService;

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
        condition.setStatus(StatusTypeEnum.ENABLED.getValue());
        condition.setActive(ActiveTypeEnum.ACTIVE.getValue());
        Example<VipTypeEntity> example = Example.of(condition);
        return this.repository.findAll(example).stream().toList();
    }

}
