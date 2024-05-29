package cc.elvea.platform.commons.utils;

import cn.hutool.core.util.DesensitizedUtil;

/**
 * @author elvea
 * @since 24.1.0
 */
public abstract class SensitiveUtils {

    /**
     * 身份证脱敏
     */
    public static String idCard(String idCard) {
        return StringUtils.isNotEmpty(idCard) ? DesensitizedUtil.idCardNum(idCard, 1, 2) : idCard;
    }

    /**
     * 手机号码脱敏
     */
    public static String mobileNumber(String mobileNumber) {
        return StringUtils.isNotEmpty(mobileNumber) ? DesensitizedUtil.mobilePhone(mobileNumber) : mobileNumber;
    }

    /**
     * 电子邮箱脱敏
     */
    public static String email(String email) {
        return StringUtils.isNotEmpty(email) ? DesensitizedUtil.email(email) : email;
    }

}
