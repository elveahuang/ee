package cc.wdev.platform.commons.data.elasticsearch.service;

import cc.wdev.platform.commons.service.AbstractService;
import lombok.Getter;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author elvea
 */
@Getter
@NoRepositoryBean
public abstract class BaseService extends AbstractService {

    @Autowired
    protected ElasticsearchOperations operations;

    @Autowired
    protected ElasticsearchTemplate template;

    @Autowired
    protected ReactiveElasticsearchClient elasticsearchClient;

    @Autowired
    protected RestClient restClient;

}
