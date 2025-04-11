package cc.elvea.platform.system.mall.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author elvea
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class VipItemVo implements Serializable {
    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 会员类型ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long vipTypeId;
    /**
     * 编号
     */
    private String code;
    /**
     * 标题
     */
    private String title;
    /**
     * 是否自动续费
     */
    private Boolean automaticRenewalInd;
    /**
     * 划线价格
     */
    private BigDecimal listPrice;
    /**
     * 价格
     */
    private BigDecimal price;
}
