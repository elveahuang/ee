package cn.elvea.platform.system.core.mapper;

import cn.elvea.platform.commons.core.data.mybatis.mapper.BaseEntityMapper;
import cn.elvea.platform.system.core.model.entity.TagRelationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @see BaseEntityMapper
 * @since 0.0.1
 */
@Mapper
@Repository
public interface TagRelationMapper extends BaseEntityMapper<TagRelationEntity, Long> {
}
