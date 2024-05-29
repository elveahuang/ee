package cc.elvea.platform.commons.data.mybatis.mapper;

import cc.elvea.platform.commons.data.domain.IdEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @param <T>
 * @param <K>
 * @author elvea
 * @see BaseMapper
 * @since 24.1.0
 */
@NoRepositoryBean
public interface BaseEntityMapper<T extends IdEntity, K extends Serializable> extends BaseMapper<T> {
}
