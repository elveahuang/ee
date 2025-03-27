package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.commons.data.core.domain.IdEntity;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.ObjectUtils;
import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.system.core.cache.AccountCacheKeyGenerator;
import cc.elvea.platform.system.core.model.converter.AccountConverter;
import cc.elvea.platform.system.core.model.dto.AccountDto;
import cc.elvea.platform.system.core.model.entity.AccountEntity;
import cc.elvea.platform.system.core.model.entity.AccountEntity_;
import cc.elvea.platform.system.core.model.form.AccountForm;
import cc.elvea.platform.system.core.model.request.AccountCheckRequest;
import cc.elvea.platform.system.core.model.request.AccountSearchRequest;
import cc.elvea.platform.system.core.repository.AccountRepository;
import cc.elvea.platform.system.core.service.AccountService;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @see AccountService
 * @see BaseCachingEntityService
 */
@Service
public class AccountServiceImpl
        extends BaseCachingEntityService<AccountEntity, Long, AccountRepository>
        implements AccountService {

    private final AccountCacheKeyGenerator cacheKeyGenerator = new AccountCacheKeyGenerator();

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see AccountService#check(AccountCheckRequest)
     */
    @Override
    public boolean check(AccountCheckRequest request) {
        if (StringUtils.isNotEmpty(request.getUsername())) {
            Specification<AccountEntity> specification = (root, query, builder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                if (StringUtils.isNotEmpty(request.getUsername())) {
                    predicates.add(builder.equal(root.get(AccountEntity_.USERNAME), request.getUsername()));
                }
                if (request.getId() != null && request.getId() > 0) {
                    predicates.add(builder.equal(root.get(AccountEntity_.ID), request.getId()));
                }
                return builder.and(predicates.toArray(new Predicate[0]));
            };
            return this.repository.count(specification) <= 0;
        } else if (StringUtils.isNotEmpty(request.getMobileNumber())) {
            Specification<AccountEntity> specification = (root, query, builder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                if (StringUtils.isNotEmpty(request.getEmail())) {
                    predicates.add(builder.equal(root.get(AccountEntity_.MOBILE_NUMBER), request.getMobileNumber()));
                }
                if (request.getId() != null && request.getId() > 0) {
                    predicates.add(builder.equal(root.get(AccountEntity_.ID), request.getId()));
                }
                return builder.and(predicates.toArray(new Predicate[0]));
            };
            return this.repository.count(specification) <= 0;
        } else if (StringUtils.isNotEmpty(request.getEmail())) {
            Specification<AccountEntity> specification = (root, query, builder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                if (StringUtils.isNotEmpty(request.getUsername())) {
                    predicates.add(builder.equal(root.get(AccountEntity_.EMAIL), request.getEmail()));
                }
                if (request.getId() != null && request.getId() > 0) {
                    predicates.add(builder.equal(root.get(AccountEntity_.ID), request.getId()));
                }
                return builder.and(predicates.toArray(new Predicate[0]));
            };
            return this.repository.count(specification) <= 0;
        }
        return false;
    }

    /**
     * @see AccountService#search(AccountSearchRequest)
     */
    @Override
    public Page<AccountDto> search(AccountSearchRequest request) {
        return null;
    }

    /**
     * @see AccountService#findByUsername(String)
     */
    @Override
    public AccountEntity findByUsername(String username) {
        return this.findByCacheKey(cacheKeyGenerator.cacheKeyByUsername(username), key -> {
            AccountEntity condition = AccountEntity.builder().username(username).build();
            Example<AccountEntity> example = Example.of(condition);
            AccountEntity entity = this.repository.findOne(example).orElse(null);
            this.setCache(entity);
            return entity;
        });
    }

    /**
     * @see AccountService#findByUsername(String)
     */
    @Override
    public AccountEntity findById(Long id) {
        return this.findByCacheKey(cacheKeyGenerator.keyById(id), key -> {
            AccountEntity entity = this.repository.findById(id).orElse(null);
            this.setCache(entity);
            return entity;
        });
    }

    /**
     * @see AccountService#findByEmail(String)
     */
    @Override
    public AccountEntity findByEmail(String email) {
        return this.findByCacheKey(cacheKeyGenerator.cacheKeyByEmail(email), key -> {
            AccountEntity condition = AccountEntity.builder().email(email).build();
            Example<AccountEntity> example = Example.of(condition);
            AccountEntity entity = this.repository.findOne(example).orElse(null);
            this.setCache(entity);
            return entity;
        });
    }

    /**
     * @see AccountService#findByMobile(String, String)
     */
    @Override
    public AccountEntity findByMobile(String mobileCountryCode, String mobileNumber) {
        return this.findByCacheKey(cacheKeyGenerator.cacheKeyByMobile(mobileCountryCode, mobileNumber), key -> {
            AccountEntity condition = AccountEntity.builder().mobileCountryCode(mobileCountryCode).mobileNumber(mobileNumber).build();
            Example<AccountEntity> example = Example.of(condition);
            AccountEntity entity = this.repository.findOne(example).orElse(null);
            this.setCache(entity);
            return entity;
        });
    }

    /**
     * @see AccountService#saveAccount(AccountForm)
     */
    @Override
    public void saveAccount(AccountForm form) {
        AccountEntity entity;
        if (form.getId() != null && form.getId() > 0) {
            entity = this.findById(form.getId());
            ObjectUtils.copyNotNullProperties(form, entity);
        } else {
            entity = SpringUtils.getBean(AccountConverter.class).formToEntity(form);
        }
        entity.setActive(Boolean.TRUE);
        this.save(entity);
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void setCache(AccountEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().set(this.cacheKeyGenerator.keyById(model.getId()), model);
            }
            if (StringUtils.isNotEmpty(model.getUsername())) {
                getCacheService().set(this.cacheKeyGenerator.cacheKeyByUsername(model.getUsername()), model);
            }
            if (StringUtils.isNotEmpty(model.getEmail())) {
                getCacheService().set(this.cacheKeyGenerator.cacheKeyByEmail(model.getEmail()), model);
            }
            if (StringUtils.isNotEmpty(model.getMobileCountryCode()) && StringUtils.isNotEmpty(model.getMobileNumber())) {
                getCacheService().set(this.cacheKeyGenerator.cacheKeyByMobile(model.getMobileCountryCode(), model.getMobileNumber()), model);
            }
        }
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void deleteCache(AccountEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().delete(this.cacheKeyGenerator.keyById(model.getId()));
            }
            if (StringUtils.isNotEmpty(model.getUsername())) {
                getCacheService().delete(this.cacheKeyGenerator.cacheKeyByUsername(model.getUsername()));
            }
            if (StringUtils.isNotEmpty(model.getEmail())) {
                getCacheService().delete(this.cacheKeyGenerator.cacheKeyByEmail(model.getEmail()));
            }
            if (StringUtils.isNotEmpty(model.getMobileCountryCode()) && StringUtils.isNotEmpty(model.getMobileNumber())) {
                getCacheService().delete(this.cacheKeyGenerator.cacheKeyByMobile(model.getMobileCountryCode(), model.getMobileNumber()));
            }
        }
    }

}
