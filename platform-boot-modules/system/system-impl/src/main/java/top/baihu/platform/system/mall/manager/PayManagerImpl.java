package top.baihu.platform.system.mall.manager;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.utils.CollectionUtils;
import top.baihu.platform.commons.utils.SpringUtils;
import top.baihu.platform.system.mall.domain.converter.PayTypeConverter;
import top.baihu.platform.system.mall.domain.entity.PayTypeEntity;
import top.baihu.platform.system.mall.domain.vo.PayTypeVo;
import top.baihu.platform.system.mall.service.PayTypeService;

import java.util.Collections;
import java.util.List;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class PayManagerImpl implements PayManager {

    private PayTypeService payTypeService;

    @Override
    public List<PayTypeVo> getPayTypeList() {
        List<PayTypeEntity> typeEntityList = this.payTypeService.getTypeList();
        if (CollectionUtils.isNotEmpty(typeEntityList)) {
            return SpringUtils.getBean(PayTypeConverter.class).entityList2VoList(typeEntityList);
        }
        return Collections.emptyList();
    }

}
