package cn.elvea.platform.system.core.mapper;

import cn.elvea.platform.commons.core.data.mybatis.mapper.BaseEntityMapper;
import cn.elvea.platform.system.core.model.entity.LabelEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface LangLabelMapper extends BaseEntityMapper<LabelEntity, Long> {

    /**
     * 根据编号获取多语言文本记录
     */
    LabelEntity findByCode(String code);

}
