package top.baihu.platform.system.core.enums;

import lombok.Getter;
import top.baihu.platform.commons.enums.BaseEnum;
import top.baihu.platform.system.core.provider.UserLinkProvider;

/**
 * @author elvea
 */
@Getter
public enum LinkTypeEnum implements BaseEnum<String> {
    USER("user", "label_link_type__user", "用户主页短链接", UserLinkProvider.class);

    private final String code;
    private final String label;
    private final String description;
    private final Class<?> provider;

    LinkTypeEnum(String code, String label, String description, Class<?> provider) {
        this.code = code;
        this.label = label;
        this.description = description;
        this.provider = provider;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

}
