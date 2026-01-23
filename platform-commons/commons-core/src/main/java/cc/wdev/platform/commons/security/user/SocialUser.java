package cc.wdev.platform.commons.security.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialUser implements Serializable {

    /**
     * socialType
     */
    private String socialType;

    /**
     * openId
     */
    private String openId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String headImgUrl;

    /**
     * 性别
     * 1为男性，2为女性
     */
    private Integer sex;

    /**
     * unionId
     */
    private String unionId;

    /**
     * rawInfo
     */
    private Map<String, Object> rawInfo;
}
