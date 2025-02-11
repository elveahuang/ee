package cc.elvea.platform.system.attachment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentFile implements Serializable {
    /**
     * 附件类型
     */
    private String type;
    /**
     * 附件链接
     */
    private String url;
}
