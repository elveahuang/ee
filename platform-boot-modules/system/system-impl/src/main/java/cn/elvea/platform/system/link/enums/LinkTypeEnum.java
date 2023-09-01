package cn.elvea.platform.system.link.enums;

import cn.elvea.platform.commons.core.enums.BaseEnum;
import cn.elvea.platform.system.link.provider.UserLinkProvider;
import lombok.Getter;

/**
 * @author elvea
 * @since 0.0.1
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
