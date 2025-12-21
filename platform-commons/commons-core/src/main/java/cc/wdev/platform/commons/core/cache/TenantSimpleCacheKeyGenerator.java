package cc.wdev.platform.commons.core.cache;

import lombok.Builder;
import org.jspecify.annotations.NonNull;

/**
 * @author elvea
 */
@Builder
public record TenantSimpleCacheKeyGenerator(String prefix) implements TenantCacheKeyGenerator {

    @NonNull
    @Override
    public String getPrefix() {
        return this.prefix;
    }

    public static TenantSimpleCacheKeyGenerator byClassSimpleName(Class<?> o) {
        return new TenantSimpleCacheKeyGenerator(o.getSimpleName().toLowerCase());
    }

}
