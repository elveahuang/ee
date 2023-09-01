package cn.elvea.platform.commons.core.data.elasticsearch.service;

import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author elvea
 * @since 0.0.1
 */
@NoRepositoryBean
public abstract class BaseService {

    @Autowired
    protected ElasticsearchOperations operations;

    @Autowired
    protected ReactiveElasticsearchClient elasticsearchClient;

    @Autowired
    protected RestClient restClient;

}
