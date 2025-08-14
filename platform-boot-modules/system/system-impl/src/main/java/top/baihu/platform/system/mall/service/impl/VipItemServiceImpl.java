package top.baihu.platform.system.mall.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.commons.utils.ObjectUtils;
import top.baihu.platform.system.commons.enums.ActiveTypeEnum;
import top.baihu.platform.system.commons.enums.StatusTypeEnum;
import top.baihu.platform.system.mall.domain.entity.VipItemEntity;
import top.baihu.platform.system.mall.repository.VipItemRepository;
import top.baihu.platform.system.mall.service.VipItemService;

import java.util.List;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class VipItemServiceImpl
    extends BaseCachingEntityService<VipItemEntity, Long, VipItemRepository>
    implements VipItemService {

    /**
     * @see VipItemService#getVipItemByVipType(Long)
     */
    @Override
    public List<VipItemEntity> getVipItemByVipType(Long vipTypeId) {
        VipItemEntity condition = VipItemEntity.builder().build();
        condition.setStatus(StatusTypeEnum.ENABLED.getValue());
        condition.setActive(ActiveTypeEnum.ACTIVE.getValue());
        if (ObjectUtils.isValidId(vipTypeId)) {
            condition.setVipTypeId(vipTypeId);
        }

        Example<VipItemEntity> example = Example.of(condition);
        return this.repository.findAll(example).stream().toList();
    }

}
