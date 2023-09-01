package cn.elvea.platform.commons.core.data.mybatis.mapper;

import cn.elvea.platform.commons.core.data.domain.IdEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @param <T>
 * @param <K>
 * @author elvea
 * @see BaseMapper
 * @since 0.0.1
 */
@NoRepositoryBean
public interface BaseEntityMapper<T extends IdEntity, K extends Serializable> extends BaseMapper<T> {
}
