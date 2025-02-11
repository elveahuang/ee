package cc.elvea.platform.system.core.model.vo;

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
