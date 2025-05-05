package cc.elvea.platform.commons.extensions.sensitive;

import lombok.AllArgsConstructor;

import java.util.function.Function;

/**
 * @author elvea
 */
@AllArgsConstructor
public enum SensitiveStrategy {
    /**
     * 身份证
     */
    ID_CARD(SensitiveUtils::idCard),
    /**
     * 手机号码
     */
    MOBILE(SensitiveUtils::mobileNumber),
    /**
     * 电子邮箱
     */
    EMAIL(SensitiveUtils::email);

    private final Function<String, String> handler;

    public Function<String, String> handler() {
        return handler;
    }

}
