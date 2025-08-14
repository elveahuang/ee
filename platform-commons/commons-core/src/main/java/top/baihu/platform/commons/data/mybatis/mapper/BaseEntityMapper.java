package top.baihu.platform.commons.data.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.repository.NoRepositoryBean;
import top.baihu.platform.commons.data.core.domain.IdEntity;

import java.io.Serializable;

/**
 * @param <T>
 * @param <K>
 * @author elvea
 * @see BaseMapper
 */
@NoRepositoryBean
public interface BaseEntityMapper<T extends IdEntity, K extends Serializable> extends BaseMapper<T> {
}
