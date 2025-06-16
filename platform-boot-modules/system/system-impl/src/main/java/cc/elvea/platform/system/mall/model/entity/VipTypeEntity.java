package cc.elvea.platform.system.mall.model.entity;

import cc.elvea.platform.commons.data.jpa.domain.BaseEntity;
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
@Table(name = "sys_vip_type")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class VipTypeEntity extends BaseEntity {
    /**
     * 编号
     */
    private String code;
    /**
     * 标题
     */
    private String title;
    /**
     * 文本
     */
    private String label;
    /**
     * 备注
     */
    private String description;
    /**
     * 是否默认
     */
    private Boolean defaultInd;
    /**
     * 是否允许试用
     */
    private Boolean trialInd;
    /**
     * 试用时长，单位是自然天
     */
    private Integer trialLimit;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 来源
     */
    private Integer source;
}
