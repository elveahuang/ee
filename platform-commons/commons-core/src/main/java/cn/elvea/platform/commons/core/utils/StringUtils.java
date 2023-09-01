package cn.elvea.platform.commons.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public abstract class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 生成UUID
     *
     * @return UUID
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生产随机字符串
     *
     * @param length 字符串长度
     * @return 随机字符串
     */
    public static String randomAlphabetic(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    /**
     * 生产随机数字
     *
     * @param length 数字长度
     * @return 随机数字
     */
    public static String randomNumeric(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

}
