package cc.elvea.platform.system.mall.manager;

import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.system.mall.domain.converter.PayTypeConverter;
import cc.elvea.platform.system.mall.domain.entity.PayTypeEntity;
import cc.elvea.platform.system.mall.domain.vo.PayTypeVo;
import cc.elvea.platform.system.mall.service.PayTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
