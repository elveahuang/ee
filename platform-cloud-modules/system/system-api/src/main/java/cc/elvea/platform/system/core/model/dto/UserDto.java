package cc.elvea.platform.system.core.model.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class UserDto implements Serializable {
    /**
     * ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String displayName;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户状态
     */
    private String status;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 创建人
     */
    private Long createdBy;
    /**
     * 最后修改时间
     */
    private LocalDateTime lastModifiedAt;
    /**
     * 最后修改人
     */
    private Long lastModifiedBy;
    /**
     * 删除时间
     */
    private String deletedAt;
    /**
     * 删除人
     */
    private Long deletedBy;
}
