package cc.elvea.platform.system.job.model.entity;

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
@Table(name = "sys_job")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class JobEntity extends BaseEntity {
    /**
     * 编号
     */
    private String code;
    /**
     * 任务类名
     */
    private String classname;
    /**
     * 描述说明
     */
    private String description;
    /**
     * 类型
     */
    private String type;
    /**
     * 单位
     */
    private String unit;
    /**
     * 周期
     */
    private Integer period;
    /**
     * 时
     */
    private Integer hour;
    /**
     * 分
     */
    private Integer minute;
    /**
     * 表达式
     */
    private String cron;
    /**
     * 状态
     */
    private Integer status;
}
