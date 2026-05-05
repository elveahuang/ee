package cc.wdev.platform.system.mall.api;

import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.system.mall.domain.converter.PayTypeConverter;
import cc.wdev.platform.system.mall.domain.entity.PayTypeEntity;
import cc.wdev.platform.system.mall.domain.vo.PayTypeVo;
import cc.wdev.platform.system.mall.service.PayTypeService;
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
