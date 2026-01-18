package cc.wdev.platform.system.commons.biz;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.compress.utils.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * 支付类型配置
 *
 * @author elvea
 */
@Data
@Builder
public class PayTypeConfig implements Serializable {
    @Builder.Default
    private List<String> extensions = Lists.newArrayList();
    @Builder.Default
    private List<String> fileTypes = Lists.newArrayList();
    @Builder.Default
    private String iconName = "";
    @Builder.Default
    private String iconColor = "";
    @Builder.Default
    private String coinInd = "";
    @Builder.Default
    private String coinWallet = "";
    @Builder.Default
    private String callback = "";
}
