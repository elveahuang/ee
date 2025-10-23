package cc.wdev.platform.system.commons.biz;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.compress.utils.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * 附件类型配置
 *
 * @author elvea
 */
@Data
@Builder
public class AttachmentTypeConfig implements Serializable {
    @Builder.Default
    private List<String> extensions = Lists.newArrayList();
    @Builder.Default
    private List<String> fileTypes = Lists.newArrayList();
    @Builder.Default
    private Boolean multiple = false;
}
