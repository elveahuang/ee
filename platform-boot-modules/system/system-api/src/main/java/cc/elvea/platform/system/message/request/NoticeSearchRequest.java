package cc.elvea.platform.system.message.request;

import cc.elvea.platform.commons.web.request.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @author elvea
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class NoticeSearchRequest extends PageRequest {

    @Schema(title = "用户ID", defaultValue = "0")
    @Builder.Default
    private Long userId = 0L;

}
