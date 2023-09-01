package cn.elvea.platform.system.core.mapper;

import cn.elvea.platform.commons.core.data.mybatis.mapper.BaseEntityMapper;
import cn.elvea.platform.system.core.model.entity.DictionaryTypeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 0.0.1
 */
@Mapper
@Repository
public interface DictionaryTypeMapper extends BaseEntityMapper<DictionaryTypeEntity, Long> {
}
