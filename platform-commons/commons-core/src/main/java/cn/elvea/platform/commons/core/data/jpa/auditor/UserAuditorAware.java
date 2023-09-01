package cn.elvea.platform.commons.core.data.jpa.auditor;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author elvea
 * @see AuditorAware
 * @since 0.0.1
 */
public class UserAuditorAware implements AuditorAware<Long> {

    /**
     * @see AuditorAware#getCurrentAuditor()
     */
    public @NotNull Optional<Long> getCurrentAuditor() {
        return Optional.of(1L);
    }

}
