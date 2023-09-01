package cn.elvea.platform.commons.core.data.mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author elvea
 * @since 0.0.1
 */
public abstract class BaseService {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    protected MongoTemplate mongoTemplate;

}
