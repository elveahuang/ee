package cn.elvea.platform.commons.core.data.jdbc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

/**
 * @param <T> 实体
 * @param <K> 主键
 * @author elvea
 * @see Repository
 * @see CrudRepository
 * @see PagingAndSortingRepository
 * @since 0.0.1
 */
@NoRepositoryBean
public interface BaseEntityRepository<T, K> extends CrudRepository<T, K>, PagingAndSortingRepository<T, K> {
}
