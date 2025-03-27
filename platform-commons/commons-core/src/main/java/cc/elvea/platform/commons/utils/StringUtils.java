package cc.elvea.platform.commons.utils;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.lang.Nullable;

import java.util.UUID;

/**
 * @author elvea
 */
@Slf4j
public abstract class StringUtils extends org.springframework.util.StringUtils {

    public static boolean isEmpty(@Nullable String str) {
        return !hasText(str);
    }

    public static boolean isNotEmpty(@Nullable String str) {
        return hasText(str);
    }

    public static boolean isBlank(@Nullable String str) {
        return !hasText(str);
    }

    public static boolean isNotBlank(@Nullable String str) {
        return hasText(str);
    }

    /**
     * 生成UUID
     *
     * @return UUID
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成UUID
     *
     * @return UUID
     */
    public static String simpleUuid() {
        return IdUtil.simpleUUID();
    }

    /**
     * 生产随机字符串
     *
     * @param count 字符串长度
     * @return 随机字符串
     */
    public static String randomAlphabetic(int count) {
        return RandomStringUtils.secure().nextAlphabetic(count);
    }

    /**
     * 生产随机数字
     *
     * @param count 数字长度
     * @return 随机数字
     */
    public static String randomNumeric(int count) {
        return RandomStringUtils.secure().nextNumeric(count);
    }

    /**
     * 转大写
     */
    public static String toUpperCase(String text) {
        return isNotEmpty(text) ? text.toUpperCase() : null;
    }

    /**
     * 转小写
     */
    public static String toLowerCase(String text) {
        return isNotEmpty(text) ? text.toLowerCase() : null;
    }

}
