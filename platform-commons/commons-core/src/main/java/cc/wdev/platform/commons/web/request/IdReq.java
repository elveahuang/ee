package cc.wdev.platform.commons.web.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdReq {

    @NotNull(message = "ID不能为空")
    private Long id;
}
