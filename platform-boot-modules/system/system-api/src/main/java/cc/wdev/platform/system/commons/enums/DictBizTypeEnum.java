package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 字典类型
 * BANNER - 宣传栏类型
 * ANNOUNCEMENT - 资讯类型
 * NOTICE - 通知类型
 * ACCOUNT - 账号标识
 * USER - 用户标识
 * DEPARTMENT - 部门标识
 * POSITION - 岗位标识
 * LINK - 友情链接
 * LINK_CATALOG - 友情链接分类
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum DictBizTypeEnum implements BaseBizTypeEnum<String> {
    BANNER("BANNER", "宣传栏类型"),
    ANNOUNCEMENT("ANNOUNCEMENT", "资讯类型"),
    NOTICE("NOTICE", "通知类型"),
    ACCOUNT("ACCOUNT", "账号标识"),
    USER("USER", "用户标识"),
    DEPARTMENT("DEPARTMENT", "部门标识"),
    POSITION("POSITION", "岗位标识"),
    LINK("LINK", "友情链接"),
    LINK_CATALOG("LINK_CATALOG", "友情链接分类"),
    NONE("NONE", "NONE");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.DICT_TYPE.getValue().toUpperCase();
    }

}
