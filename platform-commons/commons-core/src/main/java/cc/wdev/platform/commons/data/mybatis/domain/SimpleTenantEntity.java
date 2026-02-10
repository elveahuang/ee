package cc.wdev.platform.commons.data.mybatis.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

/**
 * @author elvea
 */
@Getter
@Setter
public abstract class SimpleTenantEntity extends SimpleEntity {
    /**
     * 租户ID
     */
    @Schema(description = "租户ID")
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long tenantId;
}
