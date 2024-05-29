package cc.elvea.platform.commons.data.jpa.auditor;

import cc.elvea.platform.commons.utils.SecurityUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author elvea
 * @see AuditorAware
 * @since 24.1.0
 */
public class UserAuditorAware implements AuditorAware<Long> {

    /**
     * @see AuditorAware#getCurrentAuditor()
     */
    public @NotNull Optional<Long> getCurrentAuditor() {
        return Optional.of(SecurityUtils.getUid());
    }

}
