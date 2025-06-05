package cc.elvea.platform.commons.core.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.Duration;

/**
 * @author elvea
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CacheKey {

    @NonNull
    private String key;

    private Duration expire;

    public CacheKey(final @NonNull String key) {
        this.key = key;
    }

}
