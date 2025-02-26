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

import java.math.BigDecimal;

/**
 * @author elvea
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_vip_item")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class VipItemEntity extends BaseEntity {
    /**
     * 会员类型ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long vipTypeId;
    /**
     * 编号
     */
    private String code;
    /**
     * 标题
     */
    private String title;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 是否自动续费
     */
    private Boolean automaticRenewalInd;
    /**
     * 划线价格
     */
    private BigDecimal listPrice;
    /**
     * 价格
     */
    private BigDecimal price;
}
