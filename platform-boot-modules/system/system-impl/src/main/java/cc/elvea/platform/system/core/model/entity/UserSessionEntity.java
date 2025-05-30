package cc.elvea.platform.system.core.model.entity;

import cc.elvea.platform.commons.annotations.DateTimeFormat;
import cc.elvea.platform.commons.annotations.JsonFormat;
import cc.elvea.platform.commons.constants.DateTimeConstants;
import cc.elvea.platform.commons.data.jpa.domain.BaseEntity;
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
@Table(name = "sys_user_session")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class UserSessionEntity extends BaseEntity {
    /**
     * 会话标识
     */
    private String sessionId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 登录主机
     */
    private String host;
    /**
     * User Agent
     */
    private String ua;
    /**
     * 客户端编号
     */
    private String clientId;
    /**
     * 客户端名称
     */
    private String clientName;
    /**
     * 客户端版本
     */
    private String clientVersion;
    /**
     * 会话开始时间
     */
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime startDatetime;
    /**
     * 最后访问时间
     */
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime lastAccessDatetime;
    /**
     * 会话结束时间
     */
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime endDatetime;
    /**
     * 是否成功登录
     */
    private Boolean success;
}
