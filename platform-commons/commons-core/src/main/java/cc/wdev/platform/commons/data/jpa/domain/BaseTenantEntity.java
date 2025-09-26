package cc.wdev.platform.commons.data.jpa.domain;

import cc.wdev.platform.commons.data.jpa.tenant.TenantId;
import cc.wdev.platform.commons.data.mybatis.domain.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

/**
 * @author elvea
 */
@Setter
@Getter
public abstract class BaseTenantEntity extends BaseEntity {

    /**
     * 租户ID
     */
    @TenantId
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long tenantId;

}
