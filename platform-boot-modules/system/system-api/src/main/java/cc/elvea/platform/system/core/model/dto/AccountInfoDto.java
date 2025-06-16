package cc.elvea.platform.system.core.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountInfoDto implements Serializable {
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
