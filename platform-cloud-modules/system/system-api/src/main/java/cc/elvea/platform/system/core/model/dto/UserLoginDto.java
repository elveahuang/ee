package cc.elvea.platform.system.core.model.dto;

import lombok.*;
import lombok.experimental.Accessors;

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
public class UserLoginDto implements Serializable {
    /**
     * ID
     */
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
     * 用户所属组织
     */
    private List<OrganizationDto> organizations;
    /**
     * 用户所属岗位
     */
    private List<PositionDto> positions;
    /**
     * 用户所属角色
     */
    private List<RoleDto> roles;
    /**
     * 用户所拥有的权限
     */
    private List<AuthorityDto> authorities;
}
