package cc.elvea.platform.commons.data.mongodb.callback;

import cc.elvea.platform.commons.core.sequence.Sequence;
import cc.elvea.platform.commons.data.core.domain.IdEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.Ordered;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;

/**
 * @author elvea
 */
public class MongoBeforeSaveCallback<T extends IdEntity> implements BeforeConvertCallback<T>, Ordered {

    private final Sequence sequence;

    public MongoBeforeSaveCallback(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public int getOrder() {
        return 100;
    }

    @Override
    public @NotNull T onBeforeConvert(T entity, @NotNull String collection) {
        if (entity.getId() == null) {
            entity.setId(this.sequence.nextId());
        }
        return entity;
    }

}
