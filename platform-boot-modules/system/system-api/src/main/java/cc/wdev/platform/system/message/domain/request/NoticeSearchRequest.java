package cc.wdev.platform.system.message.domain.request;

import cc.wdev.platform.commons.web.request.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author elvea
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class NoticeSearchRequest extends PageRequest {

    @Schema(title = "用户ID", defaultValue = "0")
    @Builder.Default
    private Long userId = 0L;

}
