package cc.elvea.platform.system.core.mapper;

import cc.elvea.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cc.elvea.platform.system.core.model.entity.LabelEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface LangLabelMapper extends BaseEntityMapper<LabelEntity, Long> {

    /**
     * 根据编号获取多语言文本记录
     */
    LabelEntity findByCode(String code);

}
