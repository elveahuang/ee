package cc.elvea.platform.system.log.model.entity;

import cc.elvea.platform.commons.annotations.DateTimeFormat;
import cc.elvea.platform.commons.annotations.JsonFormat;
import cc.elvea.platform.commons.constants.DateTimeConstants;
import cc.elvea.platform.commons.data.jpa.domain.SimpleEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @author elvea
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_operation_log")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class OperationLogEntity extends SimpleEntity {
    /**
     * 类名
     */
    private String className;
    /**
     * 函数名
     */
    private String methodName;
    /**
     * 请求IP
     */
    private String requestIp;
    /**
     * 请求UA
     */
    private String requestUa;
    /**
     * 请求地址
     */
    private String requestUri;
    /**
     * 请求类型
     */
    private String httpMethod;
    /**
     * 请求参数
     */
    private String requestParams;
    /**
     * 请求头参数
     */
    private String requestHeaderParams;
    /**
     * 注解参数
     */
    private String annotationParams;
    /**
     * 日志的开始时间
     */
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime startTime;
    /**
     * 日志的开始时间
     */
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime endTime;
    /**
     * 执行时长
     */
    private Long execTime;
    /**
     * 日志详情
     */
    private String details;
    /**
     * 错误日志详细信息
     */
    private String exception;
}
