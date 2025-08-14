package top.baihu.platform.commons.data.elasticsearch.callback;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.Ordered;
import org.springframework.data.elasticsearch.core.event.BeforeConvertCallback;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import top.baihu.platform.commons.core.sequence.Sequence;
import top.baihu.platform.commons.data.core.domain.IdEntity;

/**
 * @author elvea
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
