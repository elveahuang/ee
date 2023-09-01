package cn.elvea.platform.commons.core.data.elasticsearch.callback;

import cn.elvea.platform.commons.core.data.domain.IdEntity;
import cn.elvea.platform.commons.core.sequence.Sequence;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.Ordered;
import org.springframework.data.elasticsearch.core.event.BeforeConvertCallback;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

/**
 * @author elvea
 * @since 0.0.1
 */
public class ElasticSearchBeforeSaveCallback<T extends IdEntity> implements BeforeConvertCallback<T>, Ordered {

    private final Sequence sequence;

    public ElasticSearchBeforeSaveCallback(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public int getOrder() {
        return 100;
    }

    @Override
    public @NotNull T onBeforeConvert(T entity, @NotNull IndexCoordinates index) {
        if (entity.getId() == null) {
            entity.setId(this.sequence.nextId());
        }
        return entity;
    }

}
