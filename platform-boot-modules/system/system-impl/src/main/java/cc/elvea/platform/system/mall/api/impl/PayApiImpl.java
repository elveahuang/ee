package cc.elvea.platform.system.mall.api.impl;

import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.system.mall.api.PayApi;
import cc.elvea.platform.system.mall.model.converter.PayTypeConverter;
import cc.elvea.platform.system.mall.model.entity.PayTypeEntity;
import cc.elvea.platform.system.mall.model.vo.PayTypeVo;
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
public class PayApiImpl implements PayApi {

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
