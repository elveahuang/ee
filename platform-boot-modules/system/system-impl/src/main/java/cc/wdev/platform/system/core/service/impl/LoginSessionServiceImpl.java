package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.core.cache.CacheKeyGenerator;
import cc.wdev.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.system.commons.constants.SystemCacheConstants;
import cc.wdev.platform.system.core.domain.entity.LoginSessionEntity;
import cc.wdev.platform.system.core.domain.entity.LoginSessionEntity_;
import cc.wdev.platform.system.core.repository.LoginSessionRepository;
import cc.wdev.platform.system.core.service.LoginSessionService;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @see LoginSessionService
 * @see BaseCachingEntityService
 */
@Service
public class LoginSessionServiceImpl extends BaseCachingEntityService<LoginSessionEntity, Long, LoginSessionRepository>
    implements LoginSessionService {

    private final CacheKeyGenerator cacheKeyGenerator = SimpleCacheKeyGenerator.builder().prefix(SystemCacheConstants.USER_SESSION).build();

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see LoginSessionService#findBySessionId(String)
     */
    @Override
    public LoginSessionEntity findBySessionId(String sessionId) {
        Specification<LoginSessionEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(LoginSessionEntity_.SESSION_ID), sessionId));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findOne(specification).orElse(null);
    }

}
