package cc.elvea.platform.commons.data.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

/**
 * @param <T> 实体
 * @param <K> 主键
 * @author elvea
 * @see MongoRepository
 * @see PagingAndSortingRepository
 * @see CrudRepository
 * @see Repository
 */
@NoRepositoryBean
public interface BaseEntityRepository<T, K> extends MongoRepository<T, K> {
}
