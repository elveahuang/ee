package cn.elvea.platform.commons.core.utils;

import java.util.regex.Pattern;

public abstract class RegexUtils {

    /**
     * 验证Email
     *
     * @param email email地址
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkEmail(String email) {
        String regex = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
        return Pattern.matches(regex, email);
    }

    public static boolean checkMobile(String mobile) {
        String regex = "(\\+\\d+)?1[3-9]\\d{9}$";
        return Pattern.matches(regex, mobile);
    }

}
