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
@Table(name = "sys_order_log")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class OrderLogEntity extends BaseEntity {
    /**
     * 操作人ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private String userId;
    /**
     * 订单ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private String orderId;
    /**
     * 日志详情
     */
    private String details;
}
