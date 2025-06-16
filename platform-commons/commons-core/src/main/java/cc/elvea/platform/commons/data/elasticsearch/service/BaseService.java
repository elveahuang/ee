package cc.elvea.platform.commons.data.elasticsearch.service;

import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author elvea
 */
@NoRepositoryBean
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public abstract class BaseService {

    @Autowired
    protected ElasticsearchOperations operations;

    @Autowired
    protected ReactiveElasticsearchClient elasticsearchClient;

    @Autowired
    protected RestClient restClient;

}
