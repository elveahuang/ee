package cc.elvea.platform.system.log.model.entity;

import cc.elvea.platform.commons.data.jpa.domain.SimpleEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author elvea
 * @since 24.1.0
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_url_stat_log")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class UrlStatLogEntity extends SimpleEntity {
    /**
     * 路径
     */
    private String path;
    /**
     * 平均执行时长
     */
    private Long avgTime;
    /**
     * 最大执行时长
     */
    private Long maxTime;
    /**
     * 最小执行时长
     */
    private Long minTime;
    /**
     * 总执行时长
     */
    private Long totalTime;
    /**
     * 总执行次数
     */
    private Long totalNum;
    /**
     * 总执行成功次数
     */
    private Long totalSuccessNum;
    /**
     * 总执行失败次数
     */
    private Long totalErrorNum;
}
