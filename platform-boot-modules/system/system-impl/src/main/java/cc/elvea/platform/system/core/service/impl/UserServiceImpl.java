package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.commons.data.core.domain.IdEntity;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.ObjectUtils;
import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.system.core.cache.UserCacheKeyGenerator;
import cc.elvea.platform.system.core.model.converter.UserConverter;
import cc.elvea.platform.system.core.model.entity.UserEntity;
import cc.elvea.platform.system.core.model.entity.UserEntity_;
import cc.elvea.platform.system.core.model.form.UserForm;
import cc.elvea.platform.system.core.model.request.UserCheckRequest;
import cc.elvea.platform.system.core.repository.UserRepository;
import cc.elvea.platform.system.core.service.UserService;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @see UserService
 * @see BaseCachingEntityService
 */
@Service
public class UserServiceImpl extends BaseCachingEntityService<UserEntity, Long, UserRepository> implements UserService {

    private final UserCacheKeyGenerator cacheKeyGenerator = new UserCacheKeyGenerator();

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see UserService#check(UserCheckRequest)
     */
    @Override
    public boolean check(UserCheckRequest request) {
        if (StringUtils.isNotEmpty(request.getUsername())) {
            Specification<UserEntity> specification = (root, query, builder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                if (StringUtils.isNotEmpty(request.getUsername())) {
                    predicates.add(builder.equal(root.get(UserEntity_.USERNAME), request.getUsername()));
                }
                if (request.getId() != null && request.getId() > 0) {
                    predicates.add(builder.equal(root.get(UserEntity_.ID), request.getId()));
                }
                return builder.and(predicates.toArray(new Predicate[0]));
            };
            return this.repository.count(specification) <= 0;
        } else if (StringUtils.isNotEmpty(request.getMobileNumber())) {
            Specification<UserEntity> specification = (root, query, builder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                if (StringUtils.isNotEmpty(request.getEmail())) {
                    predicates.add(builder.equal(root.get(UserEntity_.MOBILE_NUMBER), request.getMobileNumber()));
                }
                if (request.getId() != null && request.getId() > 0) {
                    predicates.add(builder.equal(root.get(UserEntity_.ID), request.getId()));
                }
                return builder.and(predicates.toArray(new Predicate[0]));
            };
            return this.repository.count(specification) <= 0;
        } else if (StringUtils.isNotEmpty(request.getEmail())) {
            Specification<UserEntity> specification = (root, query, builder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                if (StringUtils.isNotEmpty(request.getUsername())) {
                    predicates.add(builder.equal(root.get(UserEntity_.EMAIL), request.getEmail()));
                }
                if (request.getId() != null && request.getId() > 0) {
                    predicates.add(builder.equal(root.get(UserEntity_.ID), request.getId()));
                }
                return builder.and(predicates.toArray(new Predicate[0]));
            };
            return this.repository.count(specification) <= 0;
        }
        return false;
    }

    /**
     * @see UserService#findByUsername(String)
     */
    @Override
    public UserEntity findByUsername(String username) {
        return this.findByCacheKey(cacheKeyGenerator.byUsername(username), key -> {
            UserEntity condition = UserEntity.builder().username(username).build();
            Example<UserEntity> example = Example.of(condition);
            UserEntity entity = this.repository.findOne(example).orElse(null);
            this.setCache(entity);
            return entity;
        });
    }

    /**
     * @see UserService#findByEmail(String)
     */
    @Override
    public UserEntity findByEmail(String email) {
        return this.findByCacheKey(cacheKeyGenerator.byEmail(email), key -> {
            UserEntity condition = UserEntity.builder().email(email).build();
            Example<UserEntity> example = Example.of(condition);
            UserEntity entity = this.repository.findOne(example).orElse(null);
            this.setCache(entity);
            return entity;
        });
    }

    /**
     * @see UserService#findByMobile(String, String)
     */
    @Override
    public UserEntity findByMobile(String mobileCountryCode, String mobile) {
        return this.findByCacheKey(cacheKeyGenerator.byMobile(mobileCountryCode, mobile), key -> {
            UserEntity condition = UserEntity.builder().mobileCountryCode(mobileCountryCode).mobileNumber(mobile).build();
            Example<UserEntity> example = Example.of(condition);
            UserEntity entity = this.repository.findOne(example).orElse(null);
            this.setCache(entity);
            return entity;
        });
    }

    /**
     * @see UserService#getSystemAdministrator()
     */
    @Override
    public UserEntity getSystemAdministrator() {
        return this.findCacheById(1L);
    }

    /**
     * @see UserService#saveUser(UserForm)
     */
    @Override
    public void saveUser(UserForm form) {
        UserEntity entity;
        if (form.getId() != null && form.getId() > 0) {
            entity = this.findById(form.getId());
            ObjectUtils.copyNotNullProperties(form, entity);
        } else {
            entity = SpringUtils.getBean(UserConverter.class).formToEntity(form);
        }
        entity.setActive(Boolean.TRUE);
        this.save(entity);
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void setCache(UserEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().set(this.cacheKeyGenerator.keyById(model.getId()), model);
            }
            if (StringUtils.isNotEmpty(model.getUsername())) {
                getCacheService().set(this.cacheKeyGenerator.byUsername(model.getUsername()), model);
            }
            if (StringUtils.isNotEmpty(model.getEmail())) {
                getCacheService().set(this.cacheKeyGenerator.byEmail(model.getEmail()), model);
            }
            if (StringUtils.isNotEmpty(model.getMobileCountryCode()) && StringUtils.isNotEmpty(model.getMobileNumber())) {
                getCacheService().set(this.cacheKeyGenerator.byMobile(model.getMobileCountryCode(), model.getMobileNumber()), model);
            }
        }
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void deleteCache(UserEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().delete(this.cacheKeyGenerator.keyById(model.getId()));
            }
            if (StringUtils.isNotEmpty(model.getUsername())) {
                getCacheService().delete(this.cacheKeyGenerator.byUsername(model.getUsername()));
            }
            if (StringUtils.isNotEmpty(model.getEmail())) {
                getCacheService().delete(this.cacheKeyGenerator.byEmail(model.getEmail()));
            }
            if (StringUtils.isNotEmpty(model.getMobileCountryCode()) && StringUtils.isNotEmpty(model.getMobileNumber())) {
                getCacheService().delete(this.cacheKeyGenerator.byMobile(model.getMobileCountryCode(), model.getMobileNumber()));
            }
        }
    }

}
