package cc.wdev.platform.commons.data.elasticsearch.domain;

import cc.wdev.platform.commons.data.core.domain.TenantEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author elvea
 */
@Setter
@Getter
public abstract class BaseTenantEntity extends BaseEntity implements TenantEntity {
    /**
     * 租户ID
     */
    protected Long tenantId;
}
