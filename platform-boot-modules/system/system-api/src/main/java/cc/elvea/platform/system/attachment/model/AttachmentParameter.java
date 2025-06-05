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
public class AttachmentParameter implements Serializable {
    /**
     * 附件类型
     */
    private String type;
    /**
     * 是否裁剪
     */
    @Builder.Default
    private boolean crop = false;
    /**
     * 裁剪X坐标
     */
    @Builder.Default
    private int x = 0;
    /**
     * 裁剪Y坐标
     */
    @Builder.Default
    private int y = 0;
    /**
     * 裁剪宽度
     */
    @Builder.Default
    private int width = 0;
    /**
     * 裁剪高度
     */
    @Builder.Default
    private int height = 0;
}
