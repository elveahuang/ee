package cc.wdev.platform.system.mall.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import cc.wdev.platform.system.commons.enums.BizGroupTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum AccountVipLogType implements BaseBizTypeEnum<Integer> {
    ORDER(1, "购买开通"),
    REGISTER(2, "注册试用"),
    INVITE(3, "邀请赠送"),
    SYSTEM(4, "手工赠送");

    private final Integer value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.ACCOUNT_VIP_LOG_TYPE.getValue();
    }

}
