package cc.elvea.platform.commons.data.mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author elvea
 * @since 24.1.0
 */
@NoRepositoryBean
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public abstract class BaseService {

    @Autowired
    protected MongoTemplate mongoTemplate;

}
