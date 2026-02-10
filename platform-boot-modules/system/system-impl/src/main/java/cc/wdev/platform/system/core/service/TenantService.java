package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.system.core.domain.entity.TenantEntity;

/**
 * @author elvea
 */
public interface TenantService extends CachingEntityService<TenantEntity, Long> {

    /**
     * 根据租户编码查找租户
     */
    TenantEntity findByCode(String code);

}
