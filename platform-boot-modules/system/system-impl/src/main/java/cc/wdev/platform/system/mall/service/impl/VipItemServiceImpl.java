package cc.wdev.platform.system.mall.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.commons.enums.ActiveTypeEnum;
import cc.wdev.platform.commons.enums.StatusTypeEnum;
import cc.wdev.platform.commons.utils.ObjectUtils;
import cc.wdev.platform.system.mall.domain.entity.VipItemEntity;
import cc.wdev.platform.system.mall.repository.VipItemRepository;
import cc.wdev.platform.system.mall.service.VipItemService;
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
public class VipItemServiceImpl
    extends BaseCachingEntityService<VipItemEntity, Long, VipItemRepository>
    implements VipItemService {

    /**
     * @see VipItemService#getVipItemByVipType(Long)
     */
    @Override
    public List<VipItemEntity> getVipItemByVipType(Long vipTypeId) {
        VipItemEntity condition = VipItemEntity.builder().build();
        condition.setStatus(StatusTypeEnum.ON.getValue());
        condition.setActive(ActiveTypeEnum.ENABLED.getValue());
        if (ObjectUtils.isValidId(vipTypeId)) {
            condition.setVipTypeId(vipTypeId);
        }

        Example<VipItemEntity> example = Example.of(condition);
        return this.repository.findAll(example).stream().toList();
    }

}
