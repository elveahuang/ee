package top.baihu.platform.commons.data.core.auditor;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import top.baihu.platform.commons.utils.SecurityUtils;

import java.util.Optional;

/**
 * @author elvea
 * @see AuditorAware
 */
public class UserAuditorAware implements AuditorAware<Long> {

    /**
     * @see AuditorAware#getCurrentAuditor()
     */
    public @NotNull Optional<Long> getCurrentAuditor() {
        return Optional.of(SecurityUtils.getUid());
    }

}
