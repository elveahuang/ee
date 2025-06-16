package cc.elvea.platform.system.mall.model.entity;

import cc.elvea.platform.commons.data.jpa.domain.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
