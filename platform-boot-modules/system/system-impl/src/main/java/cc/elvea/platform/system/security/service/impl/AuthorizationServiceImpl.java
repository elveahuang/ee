package cc.elvea.platform.system.security.service.impl;

import cc.elvea.platform.commons.core.cache.CacheKey;
import cc.elvea.platform.commons.data.core.domain.IdEntity;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.commons.utils.ObjectUtils;
import cc.elvea.platform.commons.utils.SecurityUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.system.security.cache.AuthorizationCacheKeyGenerator;
import cc.elvea.platform.system.security.model.entity.AuthorizationEntity;
import cc.elvea.platform.system.security.model.entity.AuthorizationEntity_;
import cc.elvea.platform.system.security.repository.AuthorizationRepository;
import cc.elvea.platform.system.security.service.AuthorizationService;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @see AuthorizationService
 */
@Service
public class AuthorizationServiceImpl extends BaseCachingEntityService<AuthorizationEntity, Long, AuthorizationRepository> implements AuthorizationService {

    private final AuthorizationCacheKeyGenerator cacheKeyGenerator = new AuthorizationCacheKeyGenerator();

    @Override
    public AuthorizationCacheKeyGenerator getCacheKeyGenerator() {
        return this.cacheKeyGenerator;
    }

    @Override
    public void updateByUuid(AuthorizationEntity entity) {
        Specification<AuthorizationEntity> spec = (root, query, builder) -> builder.equal(root.get(AuthorizationEntity_.UUID), entity.getUuid());
        List<AuthorizationEntity> entityList = this.repository.findAll(spec);
        if (CollectionUtils.isNotEmpty(entityList)) {
            entityList = entityList.stream().peek((e) -> ObjectUtils.copyNotNullProperties(entity, e)).toList();
            this.saveBatch(entityList);
        } else {
            this.save(entity);
        }
    }

    @Override
    public void deleteByUuid(String uuid) {
        AuthorizationEntity entity = this.findByUuid(uuid);
        if (entity != null) {
            entity.setActive(Boolean.FALSE);
            entity.setDeletedAt(getCurLocalDateTime());
            entity.setDeletedBy(SecurityUtils.getUid());
        }
        this.save(entity);
        this.deleteCache(entity);
    }

    @Override
    public AuthorizationEntity findByUuid(String uuid) {
        return getCacheService().get(getCacheKeyGenerator().keyByUuid(uuid), k -> {
            Specification<AuthorizationEntity> specification = (root, query, builder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                predicates.add(builder.equal(root.get(AuthorizationEntity_.UUID), uuid));
                return builder.and(predicates.toArray(new Predicate[0]));
            };
            return this.repository.findOne(specification).orElse(null);
        });
    }

    @Override
    public AuthorizationEntity findByState(String state) {
        CacheKey cacheKey = getCacheKeyGenerator().keyByState(state);
        return getCacheService().get(cacheKey, k -> {
            Specification<AuthorizationEntity> specification = (root, query, builder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                predicates.add(builder.equal(root.get(AuthorizationEntity_.STATE), state));
                return builder.and(predicates.toArray(new Predicate[0]));
            };
            return this.repository.findOne(specification).orElse(null);
        });
    }

    @Override
    public AuthorizationEntity findByAuthorizationCodeValue(String authorizationCodeValue) {
        CacheKey cacheKey = getCacheKeyGenerator().keyByAuthorizationCodeValue(authorizationCodeValue);
        return getCacheService().get(cacheKey, k -> {
            Specification<AuthorizationEntity> specification = (root, query, builder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                predicates.add(builder.equal(root.get(AuthorizationEntity_.AUTHORIZATION_CODE_VALUE), authorizationCodeValue));
                return builder.and(predicates.toArray(new Predicate[0]));
            };
            return this.repository.findOne(specification).orElse(null);
        });
    }

    @Override
    public AuthorizationEntity findByOidcIdTokenValue(String oidcIdTokenValue) {
        CacheKey cacheKey = getCacheKeyGenerator().keyByOidcIdTokenValue(oidcIdTokenValue);
        return getCacheService().get(cacheKey, k -> {
            Specification<AuthorizationEntity> specification = (root, query, builder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                predicates.add(builder.equal(root.get(AuthorizationEntity_.OIDC_ID_TOKEN_VALUE), oidcIdTokenValue));
                return builder.and(predicates.toArray(new Predicate[0]));
            };
            return this.repository.findOne(specification).orElse(null);
        });
    }

    @Override
    public AuthorizationEntity findByAccessTokenValue(String accessTokenValue) {
        CacheKey cacheKey = getCacheKeyGenerator().keyByAccessTokenValue(accessTokenValue);
        return getCacheService().get(cacheKey, k -> {
            Specification<AuthorizationEntity> specification = (root, query, builder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                predicates.add(builder.equal(root.get(AuthorizationEntity_.ACCESS_TOKEN_VALUE), accessTokenValue));
                return builder.and(predicates.toArray(new Predicate[0]));
            };
            return this.repository.findOne(specification).orElse(null);
        });
    }

    @Override
    public AuthorizationEntity findByRefreshTokenValue(String refreshTokenValue) {
        CacheKey cacheKey = getCacheKeyGenerator().keyByAccessTokenValue(refreshTokenValue);
        return getCacheService().get(cacheKey, k -> {
            Specification<AuthorizationEntity> specification = (root, query, builder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                predicates.add(builder.equal(root.get(AuthorizationEntity_.REFRESH_TOKEN_VALUE), refreshTokenValue));
                return builder.and(predicates.toArray(new Predicate[0]));
            };
            return this.repository.findOne(specification).orElse(null);
        });
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void setCache(AuthorizationEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().set(getCacheKeyGenerator().keyById(model.getId()), model);
            }
            if (!StringUtils.isEmpty(model.getUuid())) {
                getCacheService().set(getCacheKeyGenerator().keyByUuid(model.getUuid()), model);
            }
            if (StringUtils.isNotEmpty(model.getState())) {
                getCacheService().set(getCacheKeyGenerator().keyByState(model.getState()), model);
            }
            if (StringUtils.isNotEmpty(model.getAuthorizationCodeValue())) {
                getCacheService().set(getCacheKeyGenerator().keyByAuthorizationCodeValue(model.getAccessTokenValue()), model);
            }
            if (StringUtils.isNotEmpty(model.getOidcIdTokenValue())) {
                getCacheService().set(getCacheKeyGenerator().keyByOidcIdTokenValue(model.getOidcIdTokenValue()), model);
            }
            if (StringUtils.isNotEmpty(model.getAccessTokenValue())) {
                getCacheService().set(getCacheKeyGenerator().keyByAccessTokenValue(model.getAccessTokenValue()), model);
            }
            if (StringUtils.isNotEmpty(model.getRefreshTokenValue())) {
                getCacheService().set(getCacheKeyGenerator().keyByRefreshTokenValue(model.getRefreshTokenValue()), model);
            }
        }
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void deleteCache(AuthorizationEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().delete(getCacheKeyGenerator().keyById(model.getId()));
            }
            if (!StringUtils.isEmpty(model.getUuid())) {
                getCacheService().delete(getCacheKeyGenerator().keyByUuid(model.getUuid()));
            }
            if (StringUtils.isNotEmpty(model.getState())) {
                getCacheService().delete(getCacheKeyGenerator().keyByState(model.getState()));
            }
            if (StringUtils.isNotEmpty(model.getAuthorizationCodeValue())) {
                getCacheService().delete(getCacheKeyGenerator().keyByAuthorizationCodeValue(model.getAccessTokenValue()));
            }
            if (StringUtils.isNotEmpty(model.getOidcIdTokenValue())) {
                getCacheService().delete(getCacheKeyGenerator().keyByOidcIdTokenValue(model.getOidcIdTokenValue()));
            }
            if (StringUtils.isNotEmpty(model.getAccessTokenValue())) {
                getCacheService().delete(getCacheKeyGenerator().keyByAccessTokenValue(model.getAccessTokenValue()));
            }
            if (StringUtils.isNotEmpty(model.getRefreshTokenValue())) {
                getCacheService().delete(getCacheKeyGenerator().keyByRefreshTokenValue(model.getRefreshTokenValue()));
            }
        }
    }

}
