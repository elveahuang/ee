package cc.elvea.platform.system.core.mapper;

import cc.elvea.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cc.elvea.platform.system.core.model.entity.DictionaryItemEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Mapper
@Repository
public interface DictionaryItemMapper extends BaseEntityMapper<DictionaryItemEntity, Long> {
}
