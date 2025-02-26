package cc.elvea.platform.commons.enums;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

/**
 * 语言枚举
 *
 * @author elvea
 */
@Getter
public enum LangTypeEnum implements BaseEnum<String> {
    ZH_CN("zh_CN", "简体中文", Locale.CHINA, Lists.asList("zh_cn", new String[]{"zh"})),
    ZH_TW("zh_TW", "繁体中文", Locale.TAIWAN, Lists.asList("zh_tw", new String[]{"zh_mo", "zh_hk"})),
    EN("en", "美式英文", Locale.US, Lists.asList("en", new String[]{"en_us", "en_uk"})),
    JA("ja", "日语", Locale.JAPAN, Lists.asList("ja", new String[]{"jp"})),
    KR("kr", "韩语", Locale.KOREA, Lists.asList("kr", new String[]{"kr"})),
    FR("fr", "法语", Locale.FRANCE, Lists.asList("fr", new String[]{"fr"}));

    private final String code;
    private final String description;
    private final Locale locale;
    private final List<String> availableCodes;

    LangTypeEnum(final String code, final String description, final Locale locale, final List<String> availableCodes) {
        this.code = code;
        this.description = description;
        this.locale = locale;
        this.availableCodes = availableCodes;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return "label_lang_type__".concat(this.code.toLowerCase());
    }

    /**
     * 根据可用编号获取对应的语言类型，默认为简体中文
     */
    public static LangTypeEnum getLangType(String code) {
        EnumSet<LangTypeEnum> enumSet = EnumSet.allOf(LangTypeEnum.class);
        return enumSet.stream()
                .filter(e -> e.availableCodes.contains(code.toLowerCase()))
                .findFirst()
                .orElse(LangTypeEnum.ZH_CN);
    }

}
