package cc.elvea.platform.system.core.model.dto;

import cc.elvea.platform.commons.enums.ActionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSessionDto implements Serializable {
    /**
     * 操作类型
     */
    private ActionTypeEnum actionType;
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
    private LocalDateTime startDatetime;
    /**
     * 最后访问时间
     */
    private LocalDateTime lastAccessDatetime;
    /**
     * 会话结束时间
     */
    private LocalDateTime endDatetime;
    /**
     * 是否成功登录
     */
    private Boolean success;
}
