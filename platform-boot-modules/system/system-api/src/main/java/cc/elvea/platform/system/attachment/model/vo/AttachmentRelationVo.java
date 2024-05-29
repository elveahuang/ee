package cc.elvea.platform.system.attachment.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentRelationVo implements Serializable {
    String url;
}
