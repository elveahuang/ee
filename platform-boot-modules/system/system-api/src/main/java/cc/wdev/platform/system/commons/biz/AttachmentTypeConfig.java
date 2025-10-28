package cc.wdev.platform.system.commons.biz;

import cc.wdev.platform.commons.enums.BaseBizTypeConfig;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

/**
 * 附件类型配置
 *
 * @author elvea
 */
@Data
@Builder
public class AttachmentTypeConfig implements BaseBizTypeConfig {
    @Builder.Default
    private List<String> extensions = Lists.newArrayList();
    @Builder.Default
    private List<String> fileTypes = Lists.newArrayList();
    @Builder.Default
    private Boolean multiple = false;
}
