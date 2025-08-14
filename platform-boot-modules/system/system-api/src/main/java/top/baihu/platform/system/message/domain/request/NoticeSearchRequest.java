package top.baihu.platform.system.message.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import top.baihu.platform.commons.web.request.PageRequest;

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
