package cc.wdev.platform.commons.data.jpa.domain;

import cc.wdev.platform.commons.data.jpa.tenant.TenantId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * @author elvea
 */
@Getter
@Setter
@MappedSuperclass
public abstract class SimpleTenantEntity extends SimpleEntity {
    /**
     * 租户ID
     */
    @TenantId
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long tenantId;
}
