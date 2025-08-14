package top.baihu.platform.system.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.baihu.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import top.baihu.platform.system.core.domain.entity.DictionaryGroupEntity;

/**
 * @author elvea
 */
@Mapper
@Repository
public interface DictionaryGroupMapper extends BaseEntityMapper<DictionaryGroupEntity, Long> {
}
