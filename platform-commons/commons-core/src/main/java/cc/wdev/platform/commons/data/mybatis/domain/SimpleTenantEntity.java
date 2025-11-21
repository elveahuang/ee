package cc.wdev.platform.commons.data.mybatis.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

/**
 * @author elvea
 */
@Getter
@Setter
public abstract class SimpleTenantEntity extends SimpleEntity {
    /**
     * 租户ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long tenantId;
}
