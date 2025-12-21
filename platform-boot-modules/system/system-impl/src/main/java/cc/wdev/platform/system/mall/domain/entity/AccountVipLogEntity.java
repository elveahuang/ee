package cc.wdev.platform.system.mall.domain.entity;

import cc.wdev.platform.commons.data.jpa.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

/**
 * @author elvea
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_account_vip_log")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class AccountVipLogEntity extends BaseEntity {
    /**
     * 会员类型ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long vipTypeId;
    /**
     * 账号ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;
    /**
     * 订单ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;
}
