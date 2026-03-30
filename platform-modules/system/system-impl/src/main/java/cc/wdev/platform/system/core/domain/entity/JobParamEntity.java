package cc.wdev.platform.system.core.domain.entity;

import cc.wdev.platform.commons.data.jpa.domain.AbstractEntity;
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
@Table(name = "sys_job_param")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class JobParamEntity extends AbstractEntity {
    /**
     * 任务ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long jobId;
    /**
     * 参数名
     */
    private String paramName;
    /**
     * 参数值
     */
    private String paramValue;
}
