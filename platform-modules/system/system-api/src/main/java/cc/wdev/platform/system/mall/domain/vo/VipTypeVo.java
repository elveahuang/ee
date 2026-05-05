package cc.wdev.platform.system.mall.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.List;

/**
 * @author elvea
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class VipTypeVo implements Serializable {
    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 编号
     */
    private String code;
    /**
     * 标题
     */
    private String title;
    /**
     * 文本
     */
    private String label;
    /**
     * 会员套餐
     */
    private List<VipItemVo> items;
    /**
     * 账号会员信息
     */
    private AccountVipVo accountVip;
}
