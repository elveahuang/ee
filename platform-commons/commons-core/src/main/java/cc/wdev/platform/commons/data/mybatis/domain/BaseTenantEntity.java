package cc.wdev.platform.commons.data.mybatis.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(title = "租户ID", description = "租户ID")
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long tenantId;
}
