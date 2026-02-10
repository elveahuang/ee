package cc.wdev.platform.system.core.domain.dto;

import lombok.*;
import lombok.experimental.Accessors;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.List;

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
public class UserInfoDto implements Serializable {
    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户名
     */
    private String displayName;
    /**
     * 用户名
     */
    private String name;
    /**
     * 手机国家区号
     */
    private String mobileCountryCode;
    /**
     * 手机
     */
    private String mobileNumber;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户所属角色
     */
    private List<String> roles;
    /**
     * 用户所拥有的权限
     */
    private List<String> authorities;
}
