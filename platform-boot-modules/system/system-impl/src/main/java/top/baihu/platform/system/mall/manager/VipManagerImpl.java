package top.baihu.platform.system.mall.manager;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.utils.CollectionUtils;
import top.baihu.platform.commons.utils.SecurityUtils;
import top.baihu.platform.commons.utils.SpringUtils;
import top.baihu.platform.system.mall.domain.converter.AccountVipConverter;
import top.baihu.platform.system.mall.domain.converter.VipItemConverter;
import top.baihu.platform.system.mall.domain.converter.VipTypeConverter;
import top.baihu.platform.system.mall.domain.entity.AccountVipEntity;
import top.baihu.platform.system.mall.domain.entity.VipItemEntity;
import top.baihu.platform.system.mall.domain.entity.VipTypeEntity;
import top.baihu.platform.system.mall.domain.vo.VipTypeVo;
import top.baihu.platform.system.mall.service.AccountVipService;
import top.baihu.platform.system.mall.service.VipItemService;
import top.baihu.platform.system.mall.service.VipTypeService;

import java.util.List;
import java.util.Optional;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class VipManagerImpl implements VipManager {

    private final VipTypeService vipTypeService;

    private final VipItemService vipItemService;

    private final AccountVipService accountVipService;

    @Override
    public List<VipTypeVo> getTypeList() {
        List<VipTypeVo> vipTypeList = Lists.newArrayList();
        // 获取会员类型
        List<VipTypeEntity> vipTypeEntityList = this.vipTypeService.getTypeList();
        if (CollectionUtils.isNotEmpty(vipTypeEntityList)) {
            vipTypeList.addAll(vipTypeEntityList.stream().map(SpringUtils.getBean(VipTypeConverter.class)::entity2Vo).toList());
        }

        // 获取会员类型套餐信息
        if (CollectionUtils.isNotEmpty(vipTypeList)) {
            List<VipItemEntity> vipItemEntityList = this.vipItemService.getVipItemByVipType(0L);
            vipTypeList.forEach((vipType -> vipType.setItems(vipItemEntityList.stream()
                .filter((e) -> e.getVipTypeId().longValue() == vipType.getId().longValue())
                .map(SpringUtils.getBean(VipItemConverter.class)::entity2Vo)
                .toList())));
        }

        // 获取账号会员信息
        if (CollectionUtils.isNotEmpty(vipTypeList) && SecurityUtils.isAuthenticated()) {
            List<AccountVipEntity> accountVipEntityList = this.accountVipService.getAccountVip(SecurityUtils.getUid());
            if (CollectionUtils.isNotEmpty(accountVipEntityList)) {
                vipTypeList.forEach((vipType -> {
                    Optional<AccountVipEntity> result = accountVipEntityList.stream().filter((e) -> e.getVipTypeId().longValue() == vipType.getId().longValue()).findFirst();
                    result.ifPresent(entity -> vipType.setAccountVip(SpringUtils.getBean(AccountVipConverter.class).entity2Vo(entity)));
                }));
            }
        }
        return vipTypeList;
    }

}
