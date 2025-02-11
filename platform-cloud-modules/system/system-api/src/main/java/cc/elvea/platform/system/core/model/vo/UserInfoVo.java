package cc.elvea.platform.system.core.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户登录信息
 */
@Data
@NoArgsConstructor
public class UserInfoVo {
    private Long id;
    private String username;
    private List<String> authorities;
}
