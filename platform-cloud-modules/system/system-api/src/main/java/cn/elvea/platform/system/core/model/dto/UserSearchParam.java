package cn.elvea.platform.system.core.model.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * UserSearchParam
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class UserSearchParam implements Serializable {

    private String keyword;

    private String username;

    private Boolean usernameExactMatch;

    private String nickname;

    private Boolean nicknameExactMatch;

}
