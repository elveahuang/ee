package cc.elvea.platform.system.security.service.impl;

import cc.elvea.platform.commons.core.cache.CacheKey;
import cc.elvea.platform.commons.data.core.domain.IdEntity;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.ObjectUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.system.security.cache.ClientCacheKeyGenerator;
import cc.elvea.platform.system.security.model.entity.ClientEntity;
import cc.elvea.platform.system.security.model.entity.ClientEntity_;
import cc.elvea.platform.system.security.repository.ClientRepository;
import cc.elvea.platform.system.security.service.ClientService;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @see ClientService
 */
@Slf4j
@Service
public class ClientServiceImpl extends BaseCachingEntityService<ClientEntity, Long, ClientRepository> implements ClientService {

    private final ClientCacheKeyGenerator cacheKeyGenerator = new ClientCacheKeyGenerator();

    @Override
    public ClientCacheKeyGenerator getCacheKeyGenerator() {
        return this.cacheKeyGenerator;
    }

    /**
     * @see ClientService#findClientById(Long)
     */
    @Override
    public ClientEntity findClientById(Long id) {
        return this.findCacheById(id);
    }

    /**
     * @see ClientService#findClientByClientId(String)
     */
    @Override
    public ClientEntity findClientByClientId(String clientId) {
        CacheKey cacheKey = getCacheKeyGenerator().keyByClientId(clientId);
        return getCacheService().get(cacheKey, k -> {
            Specification<ClientEntity> specification = (root, query, builder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                predicates.add(builder.equal(root.get(ClientEntity_.CLIENT_ID), clientId));
                return builder.and(predicates.toArray(new Predicate[0]));
            };
            return this.repository.findOne(specification).orElse(null);
        });
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void setCache(ClientEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().set(getCacheKeyGenerator().keyById(model.getId()), model);
            }
            if (StringUtils.isNotEmpty(model.getClientId())) {
                getCacheService().set(getCacheKeyGenerator().keyByClientId(model.getClientId()), model);
            }
        }
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void deleteCache(ClientEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().delete(getCacheKeyGenerator().keyById(model.getId()));
            }
            if (StringUtils.isNotEmpty(model.getClientId())) {
                getCacheService().delete(getCacheKeyGenerator().keyByClientId(model.getClientId()));
            }
        }
    }

}
