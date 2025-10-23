package cc.wdev.platform.commons.web.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdsReq {

    @NotEmpty(message = "ID不能为空")
    private List<Long> ids;
}
