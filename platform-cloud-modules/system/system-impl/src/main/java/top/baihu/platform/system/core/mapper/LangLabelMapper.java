package top.baihu.platform.system.core.mapper;

import org.springframework.stereotype.Repository;
import top.baihu.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import top.baihu.platform.system.core.domain.entity.LabelEntity;

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
