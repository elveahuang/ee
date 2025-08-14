package top.baihu.platform.system.core.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
public class UserForgetPasswordVo implements Serializable {
    private String username;
    private String email;
}
