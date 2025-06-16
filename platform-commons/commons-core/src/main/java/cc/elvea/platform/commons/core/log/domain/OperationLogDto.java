package cc.elvea.platform.commons.core.log.domain;

import cc.elvea.platform.commons.annotations.DateTimeFormat;
import cc.elvea.platform.commons.annotations.JsonFormat;
import cc.elvea.platform.commons.constants.DateTimeConstants;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OperationLogDto implements Serializable {
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
